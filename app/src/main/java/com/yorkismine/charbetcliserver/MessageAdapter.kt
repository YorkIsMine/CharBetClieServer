package com.yorkismine.charbetcliserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {
    private val listOfMessages = mutableListOf<Message>()

    fun setData(message: Message) {
        listOfMessages.add(message)
        notifyDataSetChanged()
    }

    class MessageHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(message: Message) {
            view.findViewById<TextView>(R.id.message_tv).text = message.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)

        return MessageHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfMessages.size
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(listOfMessages[position])
    }
}