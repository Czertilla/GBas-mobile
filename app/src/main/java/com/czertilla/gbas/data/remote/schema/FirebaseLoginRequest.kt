package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FirebaseLoginRequest(
    @SerializedName("firebase_token")
    @Expose
    val firebaseToken: String
)
