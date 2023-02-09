package com.matrix159.mastadonclone.shared.di

import com.matrix159.mastadonclone.shared.data.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal object RepositoryComponent: KoinComponent {
  val repository: Repository by inject()
}
