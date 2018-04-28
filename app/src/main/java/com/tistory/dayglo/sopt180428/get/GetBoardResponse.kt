package com.tistory.dayglo.sopt180428.get

/**
 * Created by user on 2018-04-28.
 */
data class GetBoardResponse (
    var message : String,
    var data : ArrayList<GetBoardResponseData>
)