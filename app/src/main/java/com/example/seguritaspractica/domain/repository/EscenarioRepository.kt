package com.example.seguritaspractica.domain.repository

import androidx.compose.ui.geometry.Offset
import com.example.seguritaspractica.domain.model.Escenario
import com.example.seguritaspractica.domain.model.Punto

interface EscenarioRepository {
    fun getEscenario(): Escenario
    fun addPunto(puntos: List<Punto>, id: Int, coordenada: Offset): List<Punto>
    fun removePunto(puntos: List<Punto>, id: Int): List<Punto>
    fun updatePunto(puntos: List<Punto>, updatedPunto: Punto): List<Punto>
}