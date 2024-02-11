package com.example.chernykh.data.network.model

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("country") val name: String
    )