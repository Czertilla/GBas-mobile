package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FirebaseLoginResponse(

    @SerializedName("user_id")
    @Expose
    val userId: String,

    @SerializedName("access_token")
    @Expose
    val accessToken: String
)