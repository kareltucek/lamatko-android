package cz.ktweb.lamatkoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import lamatko.Principal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessDigits(view: View) {
        val tbCode: EditText = findViewById(R.id.tbCode)
        val tbDigits: EditText = findViewById(R.id.tbDigits)

        val code: String = tbCode.text.toString()
        val digits: String = Principal.guessDigitDescription(code)

        tbDigits.setText(digits)
    }

    fun onDecipher(view: View) {
        val tvResults: TextView = findViewById(R.id.tvOutput)
        val tbCode: EditText = findViewById(R.id.tbCode)
        val tbDigits: EditText = findViewById(R.id.tbDigits)
        val cbShuffleDigits: CheckBox = findViewById(R.id.cbShuffleOrder)
        val cbShuffleCoding: CheckBox = findViewById(R.id.cbShuffleCoding)

        val solutions = Principal.solve(
            codedText = tbCode.text.toString(),
            digitDescription = tbDigits.text.toString(),
            shuffleDigitOrder = cbShuffleDigits.isChecked,
            shuffleDigitCoding = cbShuffleCoding.isChecked,
        )

        val solutionString = solutions.map { it.describe() }.joinToString("\n")

        tvResults.text = solutionString
    }
}