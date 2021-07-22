package com.example.moviecatalogue.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
class AppExecutors constructor(
    private val diskIO: Executor
) {
    constructor() : this(Executors.newSingleThreadExecutor())
    fun diskIO(): Executor = diskIO

}