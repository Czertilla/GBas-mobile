package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserRead (
    @SerializedName("id")
    @Expose
    val id: UUID,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("password")
    @Expose
    val password: String,

    @SerializedName("is_active")
    @Expose
    val isActive: Boolean,

    @SerializedName("is_superuser")
    @Expose
    val isSuperuser: Boolean,

    @SerializedName("is_verified")
    @Expose
    val isVerified: Boolean,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("photo_url")
    @Expose
    val photoUrl: String?
)
