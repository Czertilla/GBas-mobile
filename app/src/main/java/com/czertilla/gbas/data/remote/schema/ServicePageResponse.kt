package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ServicePageResponse (
    @SerializedName("id")
    @Expose
    val id: UUID,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("author")
    @Expose
    val author: UserRead,

    @SerializedName("photo_url")
    @Expose
    val imageUrl: String,

    @SerializedName("price")
    @Expose
    val price: Double,

    @SerializedName("description")
    @Expose
    val description: String,
)