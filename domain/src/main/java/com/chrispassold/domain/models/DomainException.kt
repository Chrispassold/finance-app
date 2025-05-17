@file:Suppress("CanBeParameter")

package com.chrispassold.domain.models

sealed class DomainException(message: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)

class UnkownException(cause: Throwable? = null) :
    DomainException("An unexpected error occurred", cause)

class NetworkException(cause: Throwable? = null) : DomainException("Network error occurred", cause)

class ApiException(val errorCode: Int, message: String? = null, cause: Throwable? = null) :
    DomainException(message ?: "API error with code $errorCode", cause)

class DatabaseException(cause: Throwable? = null) :
    DomainException("Database operation failed", cause)

class AuthenticationException(message: String? = null, cause: Throwable? = null) :
    DomainException(message ?: "Authentication failed", cause)

class UserNotFoundException(message: String? = null, cause: Throwable? = null) :
    DomainException(message ?: "Invalid credentials", cause)

class DataNotFoundException(message: String? = null, cause: Throwable? = null) :
    DomainException(message ?: "Data not found", cause)