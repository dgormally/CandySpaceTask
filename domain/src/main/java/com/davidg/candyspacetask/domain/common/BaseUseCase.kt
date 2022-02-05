package com.davidg.candyspacetask.domain.common

abstract class BaseUseCase<in Params, out Type> {

    abstract fun execute(params: Params, pageSize: Int): Type
}
