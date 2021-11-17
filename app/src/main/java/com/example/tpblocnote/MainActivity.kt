package com.example.tpblocnote

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var note: EditText
    lateinit var preview: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        note = findViewById(R.id.note)
        preview = findViewById(R.id.preview)
        note.addTextChangedListener(textWatcher)

        var radioButton1: RadioButton = findViewById(R.id.radio1)
        textColor(radioButton1, "black")

        var radioButton2: RadioButton = findViewById(R.id.radio2)
        textColor(radioButton2, "blue")

        var radioButton3: RadioButton = findViewById(R.id.radio3)
        textColor(radioButton3, "red")

        var buttonB: Button = findViewById(R.id.bold)
        textMode(buttonB, "b")

        var buttonI: Button = findViewById(R.id.italic)
        textMode(buttonI, "i")


        var buttonU: Button = findViewById(R.id.underline)
        textMode(buttonU, "u")

        var smiley2: Button = findViewById(R.id.smiley2)
        smiley2.setOnClickListener() {
            var builder: StringBuilder = StringBuilder()
            builder.append(note.text)
                .append(" ",ImageSpan(this,R.drawable.souriant),0)
            note.setText(builder.toString() as CharSequence)

        }

        var menuHider = false
        var hider: Button = findViewById(R.id.hider)
        hider.setOnClickListener() {
            val params = hider.layoutParams as ConstraintLayout.LayoutParams
            if (!menuHider) {
                findViewById<LinearLayout>(R.id.menu).isVisible = false
                menuHider = true
                params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                hider.requestLayout()
                hider.text = "Afficher"
            } else {
                findViewById<LinearLayout>(R.id.menu).isVisible = true
                menuHider = false
                params.topToTop = ConstraintLayout.LayoutParams.UNSET
                hider.text = "Cacher"
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            preview.setText(s.toString())
        }
    }

    private fun textMode(b: Button, mode: String) {
        b.setOnClickListener() {
            preview.setText(Html.fromHtml("<" + mode + ">" + preview.text + "<" + mode + ">"))
        }
    }

    private fun textColor(b: Button, color: String) {
        b.setOnClickListener() {
            when (color) {
                "red" -> preview.setTextColor(Color.RED)
                "black" -> preview.setTextColor(Color.BLACK)
                "blue" -> preview.setTextColor(Color.BLUE)
            }
        }
    }
}