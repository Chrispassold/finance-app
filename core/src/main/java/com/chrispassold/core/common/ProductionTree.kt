package com.chrispassold.core.common

import android.util.Log
import timber.log.Timber

/**
 * A Timber tree for release builds that sends log messages as non-fatal reports to Crashlytics.
 *
 * This tree is intended to be used in production environments where detailed logging is typically
 * disabled for performance and security reasons. It captures important log messages and
 * exceptions and reports them to Crashlytics for monitoring and debugging of production issues.
 */
class ProductionTree : Timber.Tree() {

    /**
     * Logs a message with the specified priority, tag, message, and throwable.
     *
     * This implementation sends log messages with priority [Log.ERROR] or higher, and any
     * associated Throwables, as non-fatal crash reports to Firebase Crashlytics.
     * Lower priority messages are ignored.
     *
     * @param priority The priority of the log message.
     * @param tag The tag of the log message.
     * @param message The message to log.
     * @param t An optional Throwable associated with the log message.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // Only log errors and higher to Crashlytics in production
        if (priority < Log.ERROR) {
            return
        }

        // You would typically use FirebaseCrashlytics.getInstance() here
        // to log the message and throwable.
        // Since I don't have your Crashlytics setup, this is a placeholder.
        // Uncomment and implement this part based on your Firebase setup.

        /*
        val crashlytics = FirebaseCrashlytics.getInstance()

        // You can add custom keys to provide more context to the crash report
        crashlytics.setCustomKey("log_priority", priority)
        if (tag != null) {
            crashlytics.setCustomKey("log_tag", tag)
        }
        crashlytics.setCustomKey("log_message", message)

        if (t != null) {
            // Log the exception
            crashlytics.recordException(t)
        } else {
            // If there's no exception, you can log a non-fatal error with a custom message
            // Crashlytics allows logging custom errors/messages
            // One way is to create a synthetic exception
            crashlytics.log("Priority: $priority, Tag: $tag, Message: $message")
        }
        */
    }
}