package com.example.devquiz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devquiz.model.ExerciseModel
import com.example.devquiz.repository.ExerciseRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseViewModel constructor(private val exerciseRepository: ExerciseRepository): ViewModel() {

    var exercise = MutableLiveData<ExerciseModel>()
    var errorSignal = MutableLiveData<Int>(0)
    var typeLanguage = ""
    var currencyQuestion = MutableLiveData<Int>(0)

    fun getTypelanguage(type: String) {
       typeLanguage = type
    }

    fun getExercise() {
        when (typeLanguage){
            "Python" -> getPythonExercise()
            "Javascript" -> getJavascriptExercise()
            "C" -> getCExercise()
            "Java" -> getJavaExercise()
            "C++" -> getCPlusExercise()
            "C#" -> getCSharpExercise()
            "Ruby" -> getRubyExercise()
            "Swift" -> getSwiftExercise()
            "Php" -> getPhpExercise()
            "Web" -> getHtmlAndCssExercise()
            "Go" -> getGoExercise()
            "Kotlin" -> getKotlinExercise()
            "Rust" -> getRustExercise()
            "Typescript" -> getTypescriptExercise()
            "Sql" -> getSQLExercise()
        }
    }

    private fun getPythonExercise() {
        exerciseRepository.getPythonExercises().enqueue(createCallback())
    }

    private fun getJavascriptExercise() {
        exerciseRepository.getJavascriptExercises().enqueue(createCallback())
    }

    private fun getCExercise() {
        exerciseRepository.getCExercises().enqueue(createCallback())
    }

    private fun getJavaExercise() {
        exerciseRepository.getJavaExercises().enqueue(createCallback())
    }

    private fun getCPlusExercise() {
        exerciseRepository.getCPlusExercises().enqueue(createCallback())
    }

    private fun getCSharpExercise() {
        exerciseRepository.getCSharpExercises().enqueue(createCallback())
    }

    private fun getRubyExercise() {
        exerciseRepository.getRubyExercises().enqueue(createCallback())
    }

    private fun getSwiftExercise() {
        exerciseRepository.getSwiftExercises().enqueue(createCallback())
    }

    private fun getPhpExercise() {
        exerciseRepository.getPhpExercises().enqueue(createCallback())
    }

    private fun getHtmlAndCssExercise() {
        exerciseRepository.getHtmlAndCssExercises().enqueue(createCallback())
    }

    private fun getGoExercise() {
        exerciseRepository.getGoExercises().enqueue(createCallback())
    }

    private fun getKotlinExercise() {
        exerciseRepository.getKotlinExercises().enqueue(createCallback())
    }

    private fun getRustExercise() {
        exerciseRepository.getRustExercises().enqueue(createCallback())
    }

    private fun getTypescriptExercise() {
        exerciseRepository.getTypescriptExercises().enqueue(createCallback())
    }

    private fun getSQLExercise() {
        exerciseRepository.getSQLExercises().enqueue(createCallback())
    }

    private fun createCallback(): Callback<ExerciseModel> {
        return object : Callback<ExerciseModel> {
            override fun onResponse(call: Call<ExerciseModel>, response: Response<ExerciseModel>) {
                exercise.postValue(response.body())
                currencyQuestion.postValue(currencyQuestion.value?.plus(1))
            }

            override fun onFailure(call: Call<ExerciseModel>, t: Throwable) {
                errorSignal.postValue(1)
            }
        }
    }
}