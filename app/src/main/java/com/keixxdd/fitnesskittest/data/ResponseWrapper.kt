package com.keixxdd.fitnesskittest.data

sealed class ResponseWrapper<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : ResponseWrapper<T>(data, null)
    class Failure<T>(message: String?) : ResponseWrapper<T>(null, message)
}