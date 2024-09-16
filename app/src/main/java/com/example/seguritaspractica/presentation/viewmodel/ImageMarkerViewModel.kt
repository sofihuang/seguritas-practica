package com.example.seguritaspractica.presentation.viewmodel

import androidx.compose.runtime.State
import com.example.seguritaspractica.domain.repository.EscenarioRepository
import com.example.seguritaspractica.domain.usecase.AddPunto
import com.example.seguritaspractica.domain.usecase.RemovePunto
import com.example.seguritaspractica.domain.usecase.UpdatePunto
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.seguritaspractica.domain.model.Escenario
import com.example.seguritaspractica.domain.model.Punto


class ImageMarkerViewModel(
    private val addPuntoUseCase: AddPunto,
    private val removePuntoUseCase: RemovePunto,
    private val updatePuntoUseCase: UpdatePunto,
    private val repository: EscenarioRepository
) : ViewModel() {

    private val _escenario = mutableStateOf(repository.getEscenario())
    val escenario: State<Escenario> get() = _escenario

    private val _puntos = mutableStateOf(_escenario.value.puntos)
    val puntos: State<List<Punto>> get() = _puntos

    private val _selectedId = mutableStateOf<Int?>(null)
    val selectedId: State<Int?> get() = _selectedId

    private var idCounter = 1

    fun addPunto() {
        _puntos.value = addPuntoUseCase(puntos.value, idCounter, Offset(x = 150f, y = 150f))
        _selectedId.value = idCounter
        idCounter++
    }

    fun removePunto() {
        _selectedId.value?.let { id ->
            _puntos.value = removePuntoUseCase(puntos.value, id)
            _selectedId.value = null
        }
    }

    fun updatePunto(updatedPunto: Punto) {
        _puntos.value = updatePuntoUseCase(puntos.value, updatedPunto)
    }

    fun selectPunto(id: Int) {
        _selectedId.value = id
    }
}
