package com.example.horecatest.models

import com.google.gson.annotations.SerializedName

data class Message(

    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("name") val name: String

)