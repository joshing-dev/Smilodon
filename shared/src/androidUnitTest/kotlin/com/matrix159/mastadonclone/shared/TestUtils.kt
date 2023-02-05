package com.matrix159.mastadonclone.shared

import com.matrix159.mastadonclone.shared.datalayer.MastadonRepository

actual fun getTestRepository(): MastadonRepository = MastadonRepository()