package com.davidg.candyspacetask.common.extensions


infix fun <T : Comparable<T>> T?.isGreaterThan(other: T?): Boolean? =
    if (this != null && other != null) this > other else null
