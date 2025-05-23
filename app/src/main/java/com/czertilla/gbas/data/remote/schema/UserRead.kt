package com.czertilla.gbas.data.remote.schema

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserRead (
    private var id: UUID? = null,

    private var email: String? = null,

    private var password: String? = null,

    @SerializedName("is_active")
    @Expose
    private var isActive: Boolean? = null,

    @SerializedName("is_superuser")
    @Expose
    private var isSuperuser: Boolean? = null,

    @SerializedName("is_verified")
    @Expose
    private var isVerified: Boolean? = null,

    private var username: String? = null
)
