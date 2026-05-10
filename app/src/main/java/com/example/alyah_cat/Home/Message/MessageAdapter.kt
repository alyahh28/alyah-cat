package com.example.alyah_cat.Home.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.alyah_cat.R

class MessageAdapter(context: Context, private val dataSource: List<MessageModel>) :
    ArrayAdapter<MessageModel>(context, R.layout.item_message, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)

        val item = getItem(position)

        val imgAvatar = view.findViewById<ImageView>(R.id.imgAvatar)
        val tvName = view.findViewById<TextView>(R.id.tvSenderName)
        val tvMsg = view.findViewById<TextView>(R.id.tvMessageText)

        item?.let {
            tvName.text = it.senderName
            tvMsg.text = it.messageText
            imgAvatar.setImageResource(it.avatarResId)
        }

        return view
    }
}
