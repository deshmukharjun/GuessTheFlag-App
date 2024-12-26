package com.example.guesstheflag

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Country code to country name and flag mapping
    private val countryFlags = mapOf(
        "ad" to Pair("Andorra", R.drawable.ad),
        "ae" to Pair("United Arab Emirates", R.drawable.ae),
        "af" to Pair("Afghanistan", R.drawable.af),
        "ag" to Pair("Antigua and Barbuda", R.drawable.ag),
        "ai" to Pair("Anguilla", R.drawable.ai),
        "al" to Pair("Albania", R.drawable.al),
        "am" to Pair("Armenia", R.drawable.am),
        "zw" to Pair("Zimbabwe", R.drawable.zw)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView1)
        val nextButton = findViewById<ImageButton>(R.id.imageButton1)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        var correctAnswer: String? = null

        // Function to set a random flag and options
        fun setRandomFlagAndOptions() {
            // Select a random correct answer
            val randomEntry = countryFlags.entries.random()
            val correctCountryCode = randomEntry.key
            val correctCountryName = randomEntry.value.first
            val correctFlagDrawable = randomEntry.value.second
            correctAnswer = correctCountryName

            // Set the flag image
            imageView.setImageResource(correctFlagDrawable)

            // Get three incorrect options
            val incorrectOptions = countryFlags.entries
                .filter { it.key != correctCountryCode } // Exclude the correct answer
                .shuffled() // Shuffle the list
                .take(3) // Take three incorrect options
                .map { it.value.first } // Get their names

            // Combine correct and incorrect options and shuffle them
            val allOptions = (incorrectOptions + correctCountryName).shuffled()

            // Set options to buttons
            button1.text = allOptions[0]
            button2.text = allOptions[1]
            button3.text = allOptions[2]
            button4.text = allOptions[3]
        }

        // Function to check the answer
        fun checkAnswer(selectedOption: String) {
            if (selectedOption == correctAnswer) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong! Correct answer: $correctAnswer", Toast.LENGTH_SHORT).show()
            }
        }

        // Set listeners for buttons
        button1.setOnClickListener { checkAnswer(button1.text.toString()) }
        button2.setOnClickListener { checkAnswer(button2.text.toString()) }
        button3.setOnClickListener { checkAnswer(button3.text.toString()) }
        button4.setOnClickListener { checkAnswer(button4.text.toString()) }

        // Set a random flag and options initially
        setRandomFlagAndOptions()

        // Change the flag and options on clicking the "Next" button
        nextButton.setOnClickListener {
            setRandomFlagAndOptions()
        }
    }
}
