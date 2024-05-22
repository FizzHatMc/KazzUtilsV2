package com.kazzutilsv2.errors

class CommandError(message: String, cause: Throwable) : Error(message, cause)