package com.example.devquiz.service

import com.example.devquiz.model.ExerciseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface APIService {

    @GET("/random_exercise_language/phyton")
    fun getPythonExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/javascript")
    fun getJavascriptExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/c")
    fun getCExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/java")
    fun getJavaExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/c++")
    fun getCPlusExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/c#")
    fun getCSharpExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/ruby")
    fun getRubyExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/swift")
    fun getSwiftExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/php")
    fun getPhpExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/html&css")
    fun getHtmlAndCssExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/go")
    fun getGoExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/kotlin")
    fun getKotlinExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/rust")
    fun getRustExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/typescript")
    fun getTypescriptExercises(): Call<ExerciseModel>

    @GET("/random_exercise_language/sql")
    fun getSQLExercises(): Call<ExerciseModel>

    companion object{
        private const val BASE_URL = "https://dev-quiz.onrender.com"

        private val apiService: APIService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(APIService::class.java)
        }

        fun getInstance(): APIService{
            return apiService
        }
    }
}