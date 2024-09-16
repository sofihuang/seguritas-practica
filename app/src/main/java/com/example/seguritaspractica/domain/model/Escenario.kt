package com.example.seguritaspractica.domain.model

data class Escenario(
    val id: Int,
    val imageUrl: String,
    val puntos: List<Punto>
)