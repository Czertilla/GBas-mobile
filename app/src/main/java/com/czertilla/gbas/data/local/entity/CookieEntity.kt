package com.czertilla.gbas.data.local.entity

import androidx.room.Entity
import okhttp3.Cookie

@Entity(
    tableName = "cookie",
    primaryKeys = ["domain", "key"]
)
class CookieEntity(
    val domain: String,
    val key: String,
    val value: String,
    val expiresAt: Long,
    val path: String,
    val isSecure: Boolean,
    val isHttpOnly: Boolean,
    val isPersistent: Boolean,
    val isHostOnly: Boolean
)
{
    constructor(schema: Cookie) : this(
        schema.domain,
        schema.name,
        schema.value,
        schema.expiresAt,
        schema.path,
        schema.secure,
        schema.httpOnly,
        schema.persistent,
        schema.hostOnly
    )
}


fun CookieEntity.toSchema(): Cookie {
    val builder = Cookie.Builder()
    builder
        .name(key)
        .value(value)
        .expiresAt(expiresAt)
        .path(path)
    if (isHostOnly) builder.hostOnlyDomain(domain)
    else builder.domain(domain)
    if (isSecure) builder.secure()
    if (isHostOnly) builder.httpOnly()
    return builder.build()
}