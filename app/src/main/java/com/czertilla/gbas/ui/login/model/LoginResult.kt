package com.czertilla.gbas.ui.login.model

import com.czertilla.gbas.data.model.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUser? = null,
    val error: Int? = null
)