package com.example.aqiclient.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("fields")
    val fields: List<Field>,
    @SerializedName("records")
    val records: List<Record>,
    @SerializedName("total")
    val total: String
)