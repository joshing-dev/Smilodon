package com.matrix159.mastadonclone.shared

import com.matrix159.mastadonclone.shared.datalayer.Repository
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

//expect fun runBlockingTest(block: suspend CoroutineScope.()-> Unit)
//expect val testCoroutineContext: CoroutineContext

expect fun getTestRepository() : Repository