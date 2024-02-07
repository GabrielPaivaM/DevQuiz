package com.example.devquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.devquiz.model.ExerciseModel
import com.example.devquiz.repository.ExerciseRepository

class ExerciseViewModelFactory constructor(private val exerciseRepository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            ExerciseViewModel(this.exerciseRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}