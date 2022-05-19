package com.arthas.lefrango.ui.menu.home.chatbotadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.data.model.chatbot.ChatBotMessage

class ChatBotMessageAdapter() :
    RecyclerView.Adapter<ChatBotMessageAdapter.MessageViewHolder>() {

    val messageList = mutableListOf<ChatBotMessage>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                messageList.removeAt(bindingAdapterPosition)
                notifyItemRemoved(bindingAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chatbot_message_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]
        val tvMessage = holder.itemView.findViewById<TextView>(R.id.tvMessage)
        val tvBotMessage = holder.itemView.findViewById<TextView>(R.id.tvBotMessage)

        when (currentMessage.id) {
            Constants.SharedPref.sendID -> {
                tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                tvBotMessage.visibility = View.GONE
            }
            Constants.SharedPref.receiveID -> {
                tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                tvMessage.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: ChatBotMessage) {
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
        notifyDataSetChanged()
    }
}