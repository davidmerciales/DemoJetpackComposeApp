package com.example.androidexamtest.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

abstract class BaseViewModel<S : UiState, in E : UiEvent> : ViewModel() {
    private val _event = MutableSharedFlow<E>()
    val event: SharedFlow<@UnsafeVariance E> = _event.asSharedFlow()
    abstract val state: S

    fun setEvent(event: E) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
}