package com.example.devquiz.view

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.devquiz.R
import com.example.devquiz.databinding.ActivityExerciseBinding
import com.example.devquiz.repository.ExerciseRepository
import com.example.devquiz.service.APIService
import com.example.devquiz.viewmodel.ExerciseViewModel
import com.example.devquiz.viewmodel.ExerciseViewModelFactory

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private val retrofitService = APIService.getInstance()
    private lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ExerciseViewModelFactory(ExerciseRepository(retrofitService))).get(ExerciseViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        val intent = intent
        var languageType = intent.getStringExtra("type")


        if (languageType != null) {
            viewModel.getTypelanguage(languageType)

            changeLanguageTypeColors(languageType)

            initExercise()
            viewModel.getExercise()
        } else {
            turnProgressBarInvisible()
            binding.errorCenterLayout.visibility = View.VISIBLE
            binding.internetErrorText.text = "Não foi possivel carregar o exercício"
            onTryAgainButtonClick()
        }
        verifyAPIError()
        onButtonCloseClick()
    }

    private fun onTryAgainButtonClick() {
        binding.tryAgainButton.setOnClickListener {
            binding.errorCenterLayout.visibility = View.INVISIBLE
            turnProgressBarVisible()
            viewModel.getExercise()
        }
    }

    private fun updateQuestionNumber() {
        viewModel.currencyQuestion.observe(this, Observer {
            binding.questionNumber.text = it.toString()
        })
    }

    private fun turnProgressBarInvisible() {
        binding.centerProgressbar.visibility = View.INVISIBLE
    }

    private fun turnProgressBarVisible() {
        binding.centerProgressbar.visibility = View.VISIBLE
    }

    private fun verifyAPIError(){
        viewModel.errorSignal.observe(this, Observer {
            if (it == 1) {
                turnProgressBarInvisible()
                binding.errorCenterLayout.visibility = View.VISIBLE
                binding.internetErrorText.text = "Não foi possivel carregar o exercício"
                onTryAgainButtonClick()
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun initExercise() {
        viewModel.exercise.observe(this, Observer { exercise ->

            updateQuestionNumber()

            turnProgressBarInvisible()
            binding.okCenterLayout.visibility = View.VISIBLE

            val button1 = binding.buttonOption1
            val button2 = binding.buttonOption2
            val button3 = binding.buttonOption3

            button1.setBackgroundColor(resources.getColor(R.color.white))
            button2.setBackgroundColor(resources.getColor(R.color.white))
            button3.setBackgroundColor(resources.getColor(R.color.white))

            button1.isEnabled = true
            button2.isEnabled = true
            button3.isEnabled = true

            val randomNumber = generateRandomNumber()

            binding.questionText.text = exercise.question

            if (randomNumber <= 300){
                button1.text = exercise.options[0]
                button2.text = exercise.options[1]
                button3.text = exercise.options[2]
            }

            if (randomNumber in 301..600){
                button1.text = exercise.options[1]
                button2.text = exercise.options[0]
                button3.text = exercise.options[2]
            }

            if (randomNumber > 600){
                button1.text = exercise.options[2]
                button2.text = exercise.options[1]
                button3.text = exercise.options[0]
            }

            button1.setOnClickListener {
                button2.isEnabled = false
                button3.isEnabled = false
                if (button1.text == exercise.options[0]) {
                    button1.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    button1.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }

            button2.setOnClickListener {
                button1.isEnabled = false
                button3.isEnabled = false
                if (button2.text == exercise.options[0]) {
                    button2.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    button2.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }

            button3.setOnClickListener {
                button1.isEnabled = false
                button2.isEnabled = false
                if (button3.text == exercise.options[0]) {
                    button3.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    button3.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }
        })
    }

    private fun getNewExerciseWithDelay(){
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getExercise()
        }, 250)
    }

    private fun onButtonCloseClick() {
        binding.closeButton.setOnClickListener{
            finish()
        }
    }

    private fun generateRandomNumber(): Int {
        return (0..1000).random()
    }

    private fun playCorrectAnswerSound(){
        val correctAnswerMediaPlayer = MediaPlayer.create(this, R.raw.correctansweraudio)

        if (correctAnswerMediaPlayer.isPlaying){
            correctAnswerMediaPlayer.stop()
        }
        correctAnswerMediaPlayer.start()
    }

    private fun vibrate(){
        val vibrate = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT > 26){
            vibrate.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrate.vibrate(100)
        }
    }

    private fun changeLanguageTypeColors(languageType: String) {
        val typelanguageText = binding.typeLenguageTextview
        val buttonClose = binding.closeButton
        typelanguageText.text = languageType

        when (languageType){
            "Python" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }

            "Javascript" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.yellow))
            }

            "C" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }

            "Java" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.orange))
            }

            "C++" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }

            "C#" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.purple))
            }

            "Ruby" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.red))
            }

            "Swift" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.orange))
            }
            "Php" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }
            "Web" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }
            "Go" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }
            "Kotlin" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.purple))
            }
            "Rust" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.red))
            }
            "Typescript" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }
            "Sql" -> {
                typelanguageText.setBackgroundColor(resources.getColor(R.color.blue))
            }
        }
    }
}