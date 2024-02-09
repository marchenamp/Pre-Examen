package mx.edu.potros.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textViewSuperior: TextView
    private lateinit var textViewInferior: TextView
    private lateinit var textViewError: TextView

    private var primerNumero = ""
    private var segundoNumero = ""
    private var operacion = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSuperior = findViewById(R.id.textViewSuperior)
        textViewInferior = findViewById(R.id.textViewInferior)
        textViewError = findViewById(R.id.textViewError)

        val btnNumeros = arrayOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        val btnOperaciones = arrayOf(
            findViewById<Button>(R.id.btnSumar),
            findViewById<Button>(R.id.btnRestar),
            findViewById<Button>(R.id.btnMultiplicar),
            findViewById<Button>(R.id.btnDividir)
        )

        val btnResultado = findViewById<Button>(R.id.btnResultado)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)

        for (i in btnNumeros.indices) {
            val numero = i.toString()
            btnNumeros[i].setOnClickListener {
                agregarNumero(numero)
            }
        }

        for (btn in btnOperaciones) {
            btn.setOnClickListener {
                operacion = btn.text.toString()
                textViewSuperior.text = "$primerNumero $operacion"
                textViewInferior.text = "0"
            }
        }

        btnResultado.setOnClickListener {
            calcularResultado()
        }

        btnBorrar.setOnClickListener {
            borrar()
        }
    }

    private fun agregarNumero(numero: String) {
        if (operacion.isEmpty()) {
            primerNumero += numero
            textViewInferior.text = primerNumero
        } else {
            segundoNumero += numero
            textViewInferior.text = segundoNumero
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calcularResultado() {
        if (primerNumero.isNotEmpty() && segundoNumero.isNotEmpty()) {
            val num1 = primerNumero.toInt()
            val num2 = segundoNumero.toInt()
            val resultado = when (operacion) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 != 0) {
                        num1 / num2
                    } else {
                        textViewError.text = "Error: División por cero"
                        return
                    }
                }
                else -> 0
            }
            textViewSuperior.text = resultado.toString()
            textViewInferior.text = ""
            primerNumero = resultado.toString()
            segundoNumero = ""
            operacion = ""
        } else {
            textViewError.text = "Error: Faltan operandos"
        }
    }

    private fun borrar() {
        primerNumero = ""
        segundoNumero = ""
        operacion = ""
        textViewSuperior.text = ""
        textViewInferior.text = ""
        textViewError.text = ""
    }
}
