package com.matrix159.mastadonclone.shared.viewmodel

import android.content.Context
import com.matrix159.mastadonclone.shared.datalayer.Repository


fun DKMPViewModel.Factory.getAndroidInstance(context : Context) : DKMPViewModel {
    //val sqlDriver = AndroidSqliteDriver(LocalDb.Schema, context, "Local.db")
    val repository = Repository()
    return DKMPViewModel(repository)
}