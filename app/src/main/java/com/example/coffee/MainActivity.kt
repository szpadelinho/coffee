package com.example.coffee

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var sugar = "Cukier nie został wybrany."
    var milk = "Mleko nie zostało wybrane."
    var quantity = 0;
    var checkedCoffee = "Nie wybrano żadnej kawy."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val image = findViewById<ImageView>(R.id.the_image)
        radioGroup.setOnCheckedChangeListener { _, isChecked ->
            val radioButton = findViewById<RadioButton>(isChecked)
            checkedCoffee = radioButton.text.toString()

            if(checkedCoffee == "Espresso"){
                image.setImageResource(R.drawable.espresso)
            }
            else if(checkedCoffee == "Capuccino"){
                image.setImageResource(R.drawable.capuccino)
            }
            else{
                image.setImageResource(R.drawable.latte)
            }
        }
        val checkedSugar = findViewById<CheckBox>(R.id.sugar)
        val checkedMilk = findViewById<CheckBox>(R.id.milk)

        checkedSugar.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                sugar = "Cukier został dobrany."
            }
        }
        checkedMilk.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                milk = "Mleko zostało dobrane."
            }
        }

        val seekBar = findViewById<SeekBar>(R.id.quantity)
        val quantityText = findViewById<TextView>(R.id.quantity_text)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, value: Int, p2: Boolean) {
                quantity = value
                quantityText.text = quantity.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener{
            Toast.makeText(this, "Pomyślnie zamówiłeś $checkedCoffee. $milk $sugar Zamówiłes kawy w ilości $quantity.", Toast.LENGTH_LONG).show()
        }
    }
}