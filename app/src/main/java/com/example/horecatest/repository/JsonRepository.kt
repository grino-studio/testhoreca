package com.example.horecatest.repository

import android.content.Context
import com.example.horecatest.models.Dialog
import com.example.horecatest.models.Message
import com.google.gson.Gson


object JsonRepository{

    /**
     * Считывает Json из файл из ассетов
     * Возвращает модель Dialog
     */
    fun readMessages(context: Context?): ArrayList<Message>? =
        try {
            Gson().fromJson(
                context!!.assets.open("strings.json")
                    .bufferedReader()
                    .use { it.readText() },
                Dialog::class.java
            ).messages
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

}
