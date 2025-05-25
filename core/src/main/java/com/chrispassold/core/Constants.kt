package com.chrispassold.core

import timber.log.Timber
import java.util.Locale

val appLogger: Timber.Tree
    get() = Timber.tag("APP_LOGGER")

val DEFAULT_CURRENCY_LOCALE = Locale("pt", "BR")