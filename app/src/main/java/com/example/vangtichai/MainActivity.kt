package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var amountString: String = ""

    private lateinit var tvTaka: TextView
    private lateinit var tv500: TextView
    private lateinit var tv100: TextView
    private lateinit var tv50: TextView
    private lateinit var tv20: TextView
    private lateinit var tv10: TextView
    private lateinit var tv5: TextView
    private lateinit var tv2: TextView
    private lateinit var tv1: TextView

    companion object {
        private const val KEY_AMOUNT = "KEY_AMOUNT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTaka = findViewById(R.id.tvTaka)
        tv500 = findViewById(R.id.tv500)
        tv100 = findViewById(R.id.tv100)
        tv50 = findViewById(R.id.tv50)
        tv20 = findViewById(R.id.tv20)
        tv10 = findViewById(R.id.tv10)
        tv5 = findViewById(R.id.tv5)
        tv2 = findViewById(R.id.tv2)
        tv1 = findViewById(R.id.tv1)

        if (savedInstanceState != null) {
            amountString = savedInstanceState.getString(KEY_AMOUNT, "")
        }

        setupKeypad()
        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_AMOUNT, amountString)
    }

    private fun setupKeypad() {
        val digitButtons = mapOf(
            R.id.btn0 to "0",
            R.id.btn1 to "1",
            R.id.btn2 to "2",
            R.id.btn3 to "3",
            R.id.btn4 to "4",
            R.id.btn5 to "5",
            R.id.btn6 to "6",
            R.id.btn7 to "7",
            R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        for ((id, digit) in digitButtons) {
            findViewById<Button>(id)?.setOnClickListener {
                onDigitClicked(digit)
            }
        }

        findViewById<Button>(R.id.btnClear)?.setOnClickListener {
            onClearClicked()
        }
    }

    private fun onDigitClicked(digit: String) {
        if (amountString.isEmpty() && digit == "0") {
            return
        }
        amountString += digit
        updateUI()
    }

    private fun onClearClicked() {
        amountString = ""
        updateUI()
    }

    private fun updateUI() {
        tvTaka.text = "Taka: $amountString"

        val total = amountString.toLongOrNull() ?: 0L
        if (total <= 0L) {
            tv500.text = "500: 0"
            tv100.text = "100: 0"
            tv50.text = "50: 0"
            tv20.text = "20: 0"
            tv10.text = "10: 0"
            tv5.text = "5: 0"
            tv2.text = "2: 0"
            tv1.text = "1: 0"
        } else {
            var remainder = total

            val count500 = remainder / 500
            remainder %= 500

            val count100 = remainder / 100
            remainder %= 100

            val count50 = remainder / 50
            remainder %= 50

            val count20 = remainder / 20
            remainder %= 20

            val count10 = remainder / 10
            remainder %= 10

            val count5 = remainder / 5
            remainder %= 5

            val count2 = remainder / 2
            remainder %= 2

            val count1 = remainder

            tv500.text = "500: $count500"
            tv100.text = "100: $count100"
            tv50.text = "50: $count50"
            tv20.text = "20: $count20"
            tv10.text = "10: $count10"
            tv5.text = "5: $count5"
            tv2.text = "2: $count2"
            tv1.text = "1: $count1"
        }
    }
}
