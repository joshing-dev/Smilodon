package com.matrix159.mastadonclone.shared.mvi


typealias ActionReducer<S, A> = (state: S, action: A) -> S
