package com.tistory.dayglo.sopt180428.get

/**
 * Created by user on 2018-04-28.
 */
data class GetBoardResponseData (
    var board_idx : Int,
    var board_title : String?,
    var board_content : String?,
    var board_views : Int,
    var board_write_time : String?,
    var user_id : String,
    var board_photo : String?
)