package com.chrispassold.core

interface Mapper<From, To> {
    fun mapTo(from: From): To
    fun mapToNullable(from: From?): To? = from?.let { mapTo(it) }
    fun mapToList(from: List<From>): List<To> = from.map { mapTo(it) }
}