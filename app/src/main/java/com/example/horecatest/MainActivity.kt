package com.example.horecatest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.horecatest.models.Dialog
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        readAssets()
    }

    private fun readAssets() {
        val count = readJson()?.messages?.count()
        Log.e("Grino", count.toString())
    }

    // Считывает Json из файл из ассетов
    // Возвращает модель Dialog
    private fun readJson(): Dialog? =
        try {
            Gson().fromJson(
                assets.open("strings.json")
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