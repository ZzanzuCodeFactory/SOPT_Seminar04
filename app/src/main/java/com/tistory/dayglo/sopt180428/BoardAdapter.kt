package com.tistory.dayglo.sopt180428


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.tistory.dayglo.sopt180428.get.GetBoardResponseData


/**
 * Created by user on 2018-04-28.
 */
class BoardAdapter(var boardItems : ArrayList<GetBoardResponseData>, var requestManager: RequestManager) : RecyclerView.Adapter<BoardViewHolder>() {
    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder!!.boardContent.text = boardItems[position].board_content
        holder!!.boardId.text = boardItems[position].user_id
        holder!!.boardTime.text = boardItems[position].board_write_time
        holder!!.boardTitle.text = boardItems[position].board_title

        requestManager.load(boardItems[position].board_photo).into(holder.boardProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.board_item, parent, false)

        return BoardViewHolder(mainView)
    }

    override fun getItemCount(): Int = boardItems.size
}