package com.example.horecatest.models

import com.google.gson.annotations.SerializedName

data class Dialog(

    @SerializedName("messages") val messages: ArrayList<Message>

)