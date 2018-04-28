package com.tistory.dayglo.sopt180428

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.tistory.dayglo.sopt180428.get.GetBoardResponse
import com.tistory.dayglo.sopt180428.get.GetBoardResponseData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var networkService : NetworkService
    lateinit var boardAdapter : BoardAdapter
    lateinit var boardItems : ArrayList<GetBoardResponseData>
    lateinit var requestManager : RequestManager // 이미지 처리, Glide도 있는데 사용하는 이유 -

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instance.networkService
        requestManager = Glide.with(this)

//        main_board_list.layoutManager = LinearLayoutManager(this)
        main_write_btn.setOnClickListener {
            startActivity(Intent(applicationContext, BoardActivity::class.java))
        }

        val getBoardResponse = networkService.getContent()
        getBoardResponse.enqueue(object : Callback<GetBoardResponse>{ // Call한 것에 대한 응답, 성공 - onResponse, 실패 - onFailure
            override fun onFailure(call: Call<GetBoardResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<GetBoardResponse>?, response: Response<GetBoardResponse>?) {
                if (response!!.isSuccessful) {
                    boardItems = response.body().data
                    boardAdapter = BoardAdapter(boardItems, requestManager)

                    main_board_list.adapter = boardAdapter // 게시 글 받아오기 완성
                    main_board_list.layoutManager = LinearLayoutManager(applicationContext)
                }
            }
        })
    }
}
