package com.example.devquiz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.devquiz.R
import com.example.devquiz.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        onPythonButtonClick()
        onJavascriptButtonClick()
        onCButtonClick()
        onJavaButtonClick()
        onCPlusButtonClick()
        onCSharpButtonClick()
        onRubyButtonClick()
        onSwifButtonClick()
        onPhpButtonClick()
        onWebButtonClick()
        onGoButtonClick()
        onKoltinButtonClick()
        onRustButtonClick()
        onTypescriptButtonClick()
        onSqlButtonClick()
        onBackButtonClick()
    }

    private fun onBackButtonClick() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun onPythonButtonClick() {
        binding.phytonButton.setOnClickListener {
            goToNextAcivity("Python")
        }
    }

    private fun onJavascriptButtonClick() {
        binding.javascriptButton.setOnClickListener {
            goToNextAcivity("Javascript")
        }
    }

    private fun onCButtonClick() {
        binding.cButton.setOnClickListener {
            goToNextAcivity("C")
        }
    }

    private fun onJavaButtonClick() {
        binding.javaButton.setOnClickListener {
            goToNextAcivity("Java")
        }
    }

    private fun onCPlusButtonClick() {
        binding.cplusButton.setOnClickListener {
            goToNextAcivity("C++")
        }
    }

    private fun onCSharpButtonClick() {
        binding.csharpButton.setOnClickListener {
            goToNextAcivity("C#")
        }
    }

    private fun onRubyButtonClick() {
        binding.rubyButton.setOnClickListener {
            goToNextAcivity("Ruby")
        }
    }

    private fun onSwifButtonClick() {
        binding.swiftButton.setOnClickListener {
            goToNextAcivity("Swift")
        }
    }

    private fun onPhpButtonClick() {
        binding.phpButton.setOnClickListener {
            goToNextAcivity("Php")
        }
    }

    private fun onWebButtonClick() {
        binding.webButton.setOnClickListener {
            goToNextAcivity("Web")
        }
    }

    private fun onGoButtonClick() {
        binding.goButton.setOnClickListener {
            goToNextAcivity("Go")
        }
    }

    private fun onKoltinButtonClick() {
        binding.kotlinButton.setOnClickListener {
            goToNextAcivity("Kotlin")
        }
    }

    private fun onRustButtonClick() {
        binding.rustButton.setOnClickListener {
            goToNextAcivity("Rust")
        }
    }

    private fun onTypescriptButtonClick() {
        binding.typescriptButton.setOnClickListener {
            goToNextAcivity("Typescript")
        }
    }

    private fun onSqlButtonClick() {
        binding.sqlButton.setOnClickListener {
            goToNextAcivity("Sql")
        }
    }

    private fun goToNextAcivity(type: String) {
        val intent = Intent(this, ExerciseActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}