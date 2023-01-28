package com.matrix159.mastadonclone.shared.datalayer.sources.runtimecache

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object CacheObjects {
    // here is the repository data we decide to just cache temporarily (for the runtime session),
    // rather than caching it permanently in the local db or local settings

    //internal val countryExtraData: MutableMap<String, CountryExtraData> by lazy { mutableMapOf() }

}