package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class NewServicePageRequest (

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("photo_url")
    @Expose
    val imageUrl: String,

    @SerializedName("price")
    @Expose
    val price: Double,

    @SerializedName("description")
    @Expose
    val description: String
)