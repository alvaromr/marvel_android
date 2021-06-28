package com.alvaromr.marvel.utils

interface Logger {
    fun e(tag: String?, block: (() -> String)?)
}