package com.example.devquiz.repository

import com.example.devquiz.model.ExerciseModel
import com.example.devquiz.service.APIService
import retrofit2.Call
import retrofit2.http.GET

class ExerciseRepository(val apiService: APIService) {

    fun getPythonExercises(): Call<ExerciseModel>{
        return  apiService.getPythonExercises()
    }

    fun getJavascriptExercises(): Call<ExerciseModel>{
        return  apiService.getJavascriptExercises()
    }

    fun getCExercises(): Call<ExerciseModel>{
        return  apiService.getCExercises()
    }

    fun getJavaExercises(): Call<ExerciseModel>{
        return  apiService.getJavaExercises()
    }

    fun getCPlusExercises(): Call<ExerciseModel>{
        return  apiService.getCPlusExercises()
    }

    fun getCSharpExercises(): Call<ExerciseModel>{
        return  apiService.getCSharpExercises()
    }

    fun getRubyExercises(): Call<ExerciseModel>{
        return  apiService.getRubyExercises()
    }

    fun getSwiftExercises(): Call<ExerciseModel>{
        return  apiService.getSwiftExercises()
    }

    fun getPhpExercises(): Call<ExerciseModel>{
        return  apiService.getPhpExercises()
    }

    fun getHtmlAndCssExercises(): Call<ExerciseModel>{
        return  apiService.getHtmlAndCssExercises()
    }

    fun getGoExercises(): Call<ExerciseModel>{
        return  apiService.getGoExercises()
    }

    fun getKotlinExercises(): Call<ExerciseModel>{
        return  apiService.getKotlinExercises()
    }

    fun getRustExercises(): Call<ExerciseModel>{
        return  apiService.getRustExercises()
    }

    fun getTypescriptExercises(): Call<ExerciseModel>{
        return  apiService.getTypescriptExercises()
    }

    fun getSQLExercises(): Call<ExerciseModel>{
        return  apiService.getSQLExercises()
    }
}