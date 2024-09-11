package com.example.seguritaspractica.domain

import com.example.seguritaspractica.domain.Punto

data class Escenario(
    val id: Int,
    val imageUrl: String,
    val puntos: MutableList<Punto>
)