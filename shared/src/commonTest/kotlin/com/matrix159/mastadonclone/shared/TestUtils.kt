package com.matrix159.mastadonclone.shared

import com.matrix159.mastadonclone.shared.datalayer.MastadonRepository

//expect fun runBlockingTest(block: suspend CoroutineScope.()-> Unit)
//expect val testCoroutineContext: CoroutineContext

expect fun getTestRepository() : MastadonRepository