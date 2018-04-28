package com.tistory.dayglo.sopt180428

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by user on 2018-04-28.
 */
class BoardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    var boardProfile : ImageView = itemView!!.findViewById(R.id.board_item_profile) // as ImageView
    var boardTitle : TextView = itemView!!.findViewById(R.id.board_item_title)
    var boardContent : TextView = itemView!!.findViewById(R.id.board_item_content)
    var boardTime : TextView = itemView!!.findViewById(R.id.board_item_time)
    var boardId : TextView = itemView!!.findViewById(R.id.board_item_id)
}