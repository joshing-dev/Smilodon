package com.matrix159.mastadonclone.shared.data.sources.localdb.countries

//fun LocalDb.getCountriesList() : List<CountryListData> {
//    return countriesQueries.getCountriesList(mapper = ::CountryListData).executeAsList()
//}
//
//fun LocalDb.setCountriesList(list : List<CountryListData>) {
//    countriesQueries.transaction {
//        list.forEach {
//            countriesQueries.upsertCountry(
//                name = it.name,
//                population = it.population,
//                first_doses = it.firstDoses,
//                fully_vaccinated = it.fullyVaccinated,
//            )
//        }
//    }
//}
//
//fun LocalDb.toggleFavoriteCountry(country : String) {
//    countriesQueries.updateFavorite(country)
//}
//
//fun LocalDb.getFavoriteCountriesMap() : Map<String,Boolean> {
//    return countriesQueries.getFavorites().executeAsList().associateBy({it}, {true})
//}