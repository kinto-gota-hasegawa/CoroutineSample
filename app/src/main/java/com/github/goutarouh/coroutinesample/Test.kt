package com.github.goutarouh.coroutinesample

import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {

    // Job or SupervisorJob ?
    // Jobの場合子供は全てキャンセルします。
    // Jobの場合、TopLevelでなければ親にエラーを伝搬します。
    // Jobの場合、TopLevelであれば、CoroutineExceptionHandler、登録されていなければスレッドのデフォルトハンドラーに処理を渡します。
    // SupervisorJobの場合、他の子供はキャンセルしません。
    // SupervisorJobの場合、TopLevelでなければ親にエラーを伝搬しません。
    // SupervisorJobの場合、TopLevelであれば、CoroutineExceptionHandler、登録されていなければスレッドのデフォルトハンドラーに処理を渡します。
    val topLevelJob = launch() {
        println("start1")
        coroutineScope {
            launch {
                println("start2")
                supervisorScope {
                    launch {
                        process(3) {
                            throw Exception("error3")
                        }
                    }
                    launch {
                        process(4)
                    }
                }
                println("end2")
            }

            launch {
                process(5)
            }
        }
        println("end1")
    }
    Thread.sleep(3000)

}


suspend fun test(): Unit = coroutineScope {
    throw IllegalStateException()
}