package com.tistory.dayglo.sopt180428

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 2018-04-28.
 */
class ApplicationController : Application() {

    lateinit var networkService : NetworkService

    private val baseUrl = "http://13.125.118.111:3009/" // 'http://', 포트번호 뒤에 '/' 써주기

    // static 유사 개념
    companion object {
        lateinit var instance : ApplicationController
    }

    override fun onCreate() {
        super.onCreate()

        instance = this // ㄷㅅ

        buildNetwork()
    }

    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //

        networkService = retrofit.create(NetworkService::class.java)

    }
}