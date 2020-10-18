package com.nickolay.testtask4ver2.ui.base

import androidx.lifecycle.ViewModel
import com.nickolay.testtask4ver2.data.DataProviderImpl
import com.nickolay.testtask4ver2.data.provider.DataProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
open class BaseViewModel<T> : ViewModel(), CoroutineScope {

    val dataProvider: DataProvider by lazy {
        DataProviderImpl()
    }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }


    private val dataChannel = BroadcastChannel<T>(Channel.CONFLATED)
    private val errorChannel = Channel<Throwable>()

    fun getData(): ReceiveChannel<T> = dataChannel.openSubscription()


    fun getErrorChannel(): ReceiveChannel<Throwable> = errorChannel


    protected fun setData(data: T) = launch {
        dataChannel.send(data)
    }


    protected fun setError(e: Throwable) = launch {
        errorChannel.send(e)
    }


    override fun onCleared() {
        dataChannel.close()
        errorChannel.close()
        coroutineContext.cancel()
        super.onCleared()
    }
}