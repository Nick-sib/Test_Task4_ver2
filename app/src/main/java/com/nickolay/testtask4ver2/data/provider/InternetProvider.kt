package com.nickolay.testtask4ver2.data.provider


import com.google.gson.Gson
import com.nickolay.testtask4ver2.data.entity.Employees
import com.nickolay.testtask4ver2.data.model.DataResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class InternetProvider {

    //Можно конечно сделать через Retrofit или Okhttp но пока достаточно этого и не стоит пладить зависимостей где это не требуется
    //Ошибка Cleartext HTTP traffic not permitted - пока решена добавлением `s` к протоколу более верно дописать android:networkSecurityConfig

    private val url = URL("http://gitlab.65apps.com/65gb/static/raw/master/testTask.json")
    private val timeout = 10000
    private val method = "GET"


    fun getInternetData(): ReceiveChannel<DataResult> = Channel<DataResult>(
        Channel.CONFLATED
    ).apply {
        //TODO: Check connection, if have not internet show "no intenet view"

        Thread {
            val connection: HttpURLConnection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = method
                connectTimeout = timeout
            }

            try {
                val input = InputStreamReader(connection.inputStream)
                val inStream = BufferedReader(input)
                var result = inStream.readLine()
                inStream.forEachLine {
                    result = "$result $it"
                }
                val value = result?.let {
                    val jsonData = Gson().fromJson(it, Employees::class.java)
                    DataResult.Success(jsonData.response)
                } ?: {
                    DataResult.Error(Throwable("EMPTY RESULT"))
                }

                offer(value as DataResult)

            } catch (e: Exception) {
                offer(DataResult.Error(e))
            } finally {
                connection.disconnect()
            }
        }.start()
    }


}
