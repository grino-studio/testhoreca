package com.example.horecatest.fragments.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horecatest.R
import com.example.horecatest.models.Message
import kotlinx.android.synthetic.main.item_message.view.*

class DialogRecyclerAdapter(

    private val messages: ArrayList<Message>

) : RecyclerView.Adapter<DialogRecyclerAdapter.DialogHolder>() {

    class DialogHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(message: Message) {
            itemView.name.text = message.name
            itemView.text.text = message.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DialogHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false)
        )

    override fun onBindViewHolder(holder: DialogHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.count()

    fun addData(messages: ArrayList<Message>) {
        var size = this.messages.size
        this.messages.addAll(messages)
        var sizeNew = this.messages.size
        notifyItemRangeChanged(size, sizeNew)
    }

}