package com.davidg.candyspacetask.domain.common

interface DomainMappable<R> {
    fun asDomain(): R
}
