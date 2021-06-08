package com.example.horecatest.fragments.dialog
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.horecatest.R
import com.example.horecatest.models.Message
import kotlinx.android.synthetic.main.item_message.view.*

class QAdapter(

    messages: ArrayList<Message>

) : BaseQuickAdapter<Message, QAdapter.QHolder>(

    R.layout.item_message,
    messages

) {

    override fun convert(holder: QHolder, message: Message) {
        holder.bind(message)
    }

    class QHolder(view: View) : BaseViewHolder(view) {
        fun bind(message: Message) {
            itemView.name.text = message.name
            itemView.text.text = message.text
        }
    }

}