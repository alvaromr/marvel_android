package com.alvaromr.marvel.utils

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggerImpl @Inject constructor() : Logger {
    override fun e(tag: String?, block: (() -> String)?) {
        Log.e(tag, block?.invoke().orEmpty())
    }
}