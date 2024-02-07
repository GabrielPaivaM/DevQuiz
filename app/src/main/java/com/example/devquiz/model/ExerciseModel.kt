package com.example.devquiz.model

data class ExerciseModel(
    val id: Int,
    val options: List<String>,
    val question: String
)