package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyRequest(
    @SerializedName("token")
    @Expose
    var token: String?
)