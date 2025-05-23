package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserCreate (
    @SerializedName("id")
    @Expose
    val id: UUID? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("password")
    @Expose
    private var password: String? = null,

    @SerializedName("is_active")
    @Expose
    val active: Boolean? = null,

    @SerializedName("is_superuser")
    @Expose
    val superuser: Boolean? = null,

    @SerializedName("is_verified")
    @Expose
    val verified: Boolean? = null,

    @SerializedName("username")
    @Expose
    var username: String? = null
)