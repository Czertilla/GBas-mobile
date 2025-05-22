package com.czertilla.gbas.ui.login.model

/**
 * Data validation state of the btn_login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)