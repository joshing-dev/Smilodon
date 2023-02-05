package com.matrix159.mastadonclone.shared.viewmodel

import android.content.Context
import com.matrix159.mastadonclone.shared.datalayer.MastadonRepository


fun DKMPViewModel.Factory.getAndroidInstance(context : Context) : DKMPViewModel {
    //val sqlDriver = AndroidSqliteDriver(LocalDb.Schema, context, "Local.db")
    val repository = MastadonRepository()
    return DKMPViewModel(repository)
}