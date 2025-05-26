package com.czertilla.gbas.data.remote.client

import android.util.Log
import com.czertilla.gbas.IoDispatcher
import com.czertilla.gbas.data.local.dao.CookieDao
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.local.entity.CookieEntity
import com.czertilla.gbas.data.local.entity.toSchema
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject

class CookieJarImpl @Inject constructor(
    private val cookieDao: CookieDao
)
: CookieJar {

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieDao.insert(cookies.map { CookieEntity(it) })
        Log.d("Cookies", "cookies saved: $cookies")
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieDao.getCookies(url.host).map {it.toSchema()}
    }
}
