package com.chrispassold.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Mapper<From, To> {
    fun mapTo(from: From): To
    fun mapToNullable(from: From?): To? = from?.let { mapTo(it) }
    fun mapToNullable(from: Flow<From?>): Flow<To?> = from.map { mapToNullable(it) }
    fun mapToList(from: Flow<List<From>>): Flow<List<To>> = from.map { mapToList(it) }
    fun mapToList(from: List<From>): List<To> = from.map { mapTo(it) }
}
