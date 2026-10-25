package com.aistra.hail.utils

class PrivateSearch(query: String?) {
    private val prefix = "/private "
    val isPrivate = query?.startsWith(prefix) == true
    val keyword = if (isPrivate) query!!.removePrefix(prefix) else query.orEmpty()

    fun includes(hidden: Boolean): Boolean = hidden == isPrivate
}
