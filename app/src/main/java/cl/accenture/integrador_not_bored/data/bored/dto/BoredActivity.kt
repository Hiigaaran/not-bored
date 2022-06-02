package cl.accenture.integrador_not_bored.data.bored.dto

import com.google.gson.annotations.SerializedName

data class BoredActivity(
    @SerializedName("activity")
    val activity: String,
    @SerializedName("accessibility")
    val accessibility: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("link")
    val link: String,
    @SerializedName("key")
    val key: String
)
