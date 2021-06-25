package com.alvaromr.marvel.ui

data class DataState<T : BaseViewState>(
    val loading: Boolean = false,
    val viewState: T,
)