package com.chrispassold.data

import com.chrispassold.domain.models.ApiException
import com.chrispassold.domain.models.AuthenticationException
import com.chrispassold.domain.models.DomainException
import com.chrispassold.domain.models.NetworkException
import com.chrispassold.domain.models.UnkownException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID

internal fun newLocalId(): String = UUID.randomUUID().toString()

internal fun <T> flowCatching(block: suspend FlowCollector<T>.() -> Unit): Flow<T> = flow(
    block = block,
).catch { error: Throwable ->
    val exception = when (error) {
        is DomainException -> error
        is IOException -> NetworkException(error)
        is HttpException -> if (error.code() == 401) {
            throw AuthenticationException("Invalid credentials", error)
        } else {
            throw ApiException(error.code(), error.message(), error)
        }

        else -> UnkownException(error)
    }
    throw exception
}