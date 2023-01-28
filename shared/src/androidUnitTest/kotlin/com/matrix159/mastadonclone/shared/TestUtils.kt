package com.matrix159.mastadonclone.shared

import com.matrix159.mastadonclone.shared.datalayer.Repository

actual fun getTestRepository(): Repository = Repository()