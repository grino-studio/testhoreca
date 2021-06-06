package com.example.horecatest.fragments.dialog

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.horecatest.R
import com.example.horecatest.models.Dialog
import com.example.horecatest.models.Message
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_scroll.*

class DialogFragment : Fragment() {

    private val messages = ArrayList<Message>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?):
            View = inflater.inflate(R.layout.fragment_scroll, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reloadMessages()

        recyclerDialog.layoutManager = LinearLayoutManager(context)
        recyclerDialog.adapter = DialogRecyclerAdapter(messages)
    }



    private fun reloadMessages() {
        messages.clear()
        readJson()?.messages?.let { messages.addAll(it) }
    }

    // Считывает Json из файл из ассетов
    // Возвращает модель Dialog
    private fun readJson(): Dialog? =
        try {
            Gson().fromJson(
                activity!!.assets.open("strings.json")
                    .bufferedReader()
                    .use { it.readText() },
                Dialog::class.java
            )
        } catch (e: Exception) {
            // Ошибка считавыния из файла
            e.printStackTrace()
            null
        }

}