package com.example.smarttrade.catalogue.view
object RatingsState {
    private val ratings = mutableListOf<Rating>()

    fun addValoracion(valoracion: Rating) {
        ratings.add(valoracion)
    }

    fun getValoraciones(): List<Rating> {
        return ratings.toList()
    }
}

data class Rating(val stars: Int, val opinion: String)