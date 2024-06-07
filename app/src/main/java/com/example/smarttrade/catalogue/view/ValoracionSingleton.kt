package com.example.smarttrade.catalogue.view
object ValoracionesRepository {
    private val valoraciones = mutableListOf<Valoracion>()

    fun addValoracion(valoracion: Valoracion) {
        valoraciones.add(valoracion)
    }

    fun getValoraciones(): List<Valoracion> {
        return valoraciones.toList()
    }
}

data class Valoracion(val estrellas: Int, val opinion: String)
