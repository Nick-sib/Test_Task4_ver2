package com.nickolay.testtask4ver2.data.model

sealed class DataResult {
    class Success<out T>(val data: T) : DataResult()
    class Error(val error: Throwable) : DataResult()
}