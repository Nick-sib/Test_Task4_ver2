package com.nickolay.testtask4ver2.ui.base


import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
abstract class BaseFragment<T> : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    abstract val viewModel: BaseViewModel<T>

    private lateinit var dataJob: Job
    private lateinit var errorJob: Job


    @ObsoleteCoroutinesApi
    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getData().consumeEach {
                renderData(it)
            }
        }

        errorJob = launch {
            viewModel.getErrorChannel().consumeEach {
                renderError(it)
            }
        }
    }


    override fun onStop() {
        dataJob.cancel()
        errorJob.cancel()
        super.onStop()
    }


    abstract fun renderData(data: T)


    private fun renderError(error: Throwable) {
        error.message?.let {
            showError(it)
        }
    }


    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}