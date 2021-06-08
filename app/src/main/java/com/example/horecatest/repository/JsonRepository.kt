package com.example.horecatest.repository

import android.content.Context
import com.example.horecatest.models.Dialog
import com.example.horecatest.models.Message
import com.google.gson.Gson

object JsonRepository {

    private const val PAGE_COUNT = 20

    private val messages = ArrayList<Message>()

    /**
     * Считывает Json из файл из ассетов
     * Возвращает модель Dialog
     */
    fun readMessages(context: Context?) {
        try {
            messages.clear()
            messages.addAll(
                Gson().fromJson(
                    context!!.assets.open("strings.json")
                        .bufferedReader()
                        .use { it.readText() },
                    Dialog::class.java
                ).messages
            )
        } catch (e: Exception)
        {
            e.printStackTrace()
            null
        }
    }

    fun getPage(page: Int) : ArrayList<Message> {
        val pageList = ArrayList<Message>()
        for (i in page * PAGE_COUNT until (page + 1) * PAGE_COUNT)
            pageList.add(messages[i])
        return pageList
    }

    fun isLastPage(page: Int) = page >= messages.count()/PAGE_COUNT - 1

}
