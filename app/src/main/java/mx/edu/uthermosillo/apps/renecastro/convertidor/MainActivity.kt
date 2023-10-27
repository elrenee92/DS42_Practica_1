package mx.edu.uthermosillo.apps.renecastro.convertidor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()
{

    private var conversion: Int = 0
    private var result: Double = 0.0
    private var value: String = ""
    private lateinit var  total: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.convertir)
        val options = resources.getStringArray(R.array.convert_options)
        val selectedTemp = findViewById<Spinner>(R.id.temp_options)
        total = findViewById<TextView>(R.id.resultado)
        val temp = findViewById<EditText>(R.id.cantidad)

        if (selectedTemp != null)
        {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                options
            )
            selectedTemp.adapter = adapter

            selectedTemp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long)
                {
                    Toast.makeText(this@MainActivity,
                        "Opción Seleccionada: " + position, Toast.LENGTH_SHORT)
                        .show()

                    conversion = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?)
                {
                    TODO("Not yet implemented")
                }
            }
        }
        button.setOnClickListener{
            value = temp.text.toString()
            converter(conversion)
        }
    }
        fun converter(_conversion: Int) {
            when(_conversion){
                0 -> {
                    //°C a °F = (°C × 9/5) + 32
                    result = (value.toDouble() * 9/5) + 32
                    total.text = result.toString()
                }
                1 -> {
                    //°C a °K = °C + 273.15
                    result = value.toDouble() + 273.15
                    total.text = result.toString()
                }

                2 ->{
                    //°F a °C = (°F - 32) × 5/9
                    result = (value.toDouble() - 32) * 5/9
                    total.text = result.toString()
                }

                3 ->{
                    //°F a °k = (°F - 32) * 5/9 + 273.15
                    result = (value.toDouble() - 32) * 5/9 + 273.15
                    total.text = result.toString()
                }

                4 -> {
                    //°K a °C = °K - 273.15
                    result = value.toDouble() - 273.15
                    total.text = result.toString()
                }

                5 -> {
                    //°K a °F = (°K - 273.15) * 9/5 + 32
                    result = (value.toDouble() - 273.15) * 1.8 + 32
                    total.text = result.toString()
                }
                else -> {
                    total.text = "Selección no válida"
                }
            }
        }
}
