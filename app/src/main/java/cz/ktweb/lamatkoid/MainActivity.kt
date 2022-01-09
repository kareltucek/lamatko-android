package cz.ktweb.lamatkoid

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import lamatko.Lamatko


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onGuessDigits(view: View) {
        val tbCode: EditText = findViewById(R.id.tbCode)
        val tbDigits: EditText = findViewById(R.id.tbDigits)

        val code: String = tbCode.text.toString()
        val digits: String = Lamatko.guessDigitDescription(code)

        tbDigits.setText(digits)
    }

    fun onDecipher(view: View) {
        val tvResults: TextView = findViewById(R.id.tvOutput)
        val tbCode: EditText = findViewById(R.id.tbCode)
        val tbDigits: EditText = findViewById(R.id.tbDigits)
        val cbShuffleDigits: CheckBox = findViewById(R.id.cbShuffleOrder)
        val cbShuffleCoding: CheckBox = findViewById(R.id.cbShuffleCoding)
        val cbDetail: CheckBox = findViewById(R.id.cbDetail)


        try {
            val solutions = Lamatko.solve(
                codedText = tbCode.text.toString(),
                digitDescription = tbDigits.text.toString(),
                shuffleDigitOrder = cbShuffleDigits.isChecked,
                shuffleDigitCoding = cbShuffleCoding.isChecked,
                timeoutMillis = 10000,
                resultCount = 100
            )
            val solutionString = solutions.map {
                if (cbDetail.isChecked) it.describe() else it.result
            }.joinToString("\n")
            tvResults.text = solutionString
        } catch (t: Throwable) {
            tvResults.text = "Failed because of ${t.javaClass.simpleName} (${t.message})."
        }

    }
}