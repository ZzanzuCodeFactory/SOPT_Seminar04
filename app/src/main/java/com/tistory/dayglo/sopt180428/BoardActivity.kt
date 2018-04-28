package com.tistory.dayglo.sopt180428

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.tistory.dayglo.sopt180428.post.PostBoardResponse
import kotlinx.android.synthetic.main.activity_board.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class BoardActivity : AppCompatActivity() {

    lateinit var netWorkService : NetworkService
    private val REQ_CODE_SELECT_IMAGE = 100
    private var image : MultipartBody.Part? = null
    lateinit var data : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        netWorkService = ApplicationController.instance.networkService

        write_image_btn.setOnClickListener {
            changeImage()
        }

        write_post_btn.setOnClickListener {
            postBoard()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    image = MultipartBody.Part.createFormData("photo", photo.name, photoBody) /// name : POST할 때의 key값

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(write_image) // imageview에 띄워서 확인하기

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI // 갤러리에 접근해서 사진의 URI를 가져옴

        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    fun postBoard() {
        val title = RequestBody.create(MediaType.parse("text/plain"), write_title_tv.text.toString())
        val content = RequestBody.create(MediaType.parse("text/plain"), write_content_tv.text.toString())
        val id = RequestBody.create(MediaType.parse("text/plain"), "zzanzu")

        val postBoardResponse = netWorkService.postBoard(image, title, content, id)

        postBoardResponse.enqueue(object : Callback<PostBoardResponse>{
            override fun onFailure(call: Call<PostBoardResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PostBoardResponse>?, response: Response<PostBoardResponse>?) {
                if(response!!.isSuccessful) {
                    startActivity(Intent(applicationContext, MainActivity::class.java)) // 메인 액티비티로 이동

                    finish() // BoardActivity 완전 종료
                }
            }

        })
    }
}
