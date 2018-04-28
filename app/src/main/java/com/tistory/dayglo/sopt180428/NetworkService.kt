package com.tistory.dayglo.sopt180428

import com.tistory.dayglo.sopt180428.get.GetBoardResponse
import com.tistory.dayglo.sopt180428.post.PostBoardResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by user on 2018-04-28.
 */
interface NetworkService {
    @Multipart // for file transfer
    @POST("board")
    fun postBoard(
            @Part boardImg : MultipartBody.Part?,
            @Part("user_id") id : RequestBody,
            @Part("board_title") title : RequestBody,
            @Part("board_content") content : RequestBody
            ) : Call<PostBoardResponse>

    @GET("board")
    fun getContent() : Call<GetBoardResponse>
}