package com.matrix159.mastadonclone.shared

import com.matrix159.mastadonclone.shared.data.MastadonRepository

actual fun getTestRepository(): MastadonRepository = MastadonRepository()