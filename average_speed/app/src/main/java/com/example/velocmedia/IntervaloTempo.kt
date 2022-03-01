package com.example.velocmedia

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class IntervaloTempo : AppCompatActivity() {

    private lateinit var textDistancia3 : EditText
    private lateinit var textVelocidade3 : EditText
    private lateinit var textResultado3s: EditText
    private lateinit var textResultado3m: EditText
    private lateinit var textResultado3h: EditText
    private lateinit var textOr1 : TextView
    private lateinit var textOr2 : TextView

    private lateinit var btnCalcular3 : Button
    private lateinit var btnLimpar3 : Button
    private lateinit var btnInfo3 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intervalo_tempo)

        textDistancia3 = findViewById(R.id.addDist3)
        textVelocidade3 = findViewById(R.id.addVelo3)
        textResultado3s = findViewById(R.id.resCalc3_s)
        textResultado3m = findViewById(R.id.resCalc3_m)
        textResultado3h = findViewById(R.id.resCalc3_h)
        textOr1 = findViewById(R.id.textoOu_1)
        textOr2 = findViewById(R.id.textoOu_2)

        btnCalcular3 = findViewById(R.id.btnCalc3)
        btnCalcular3.setOnClickListener{calcular()}

        btnLimpar3 = findViewById(R.id.btnClear3)
        btnLimpar3.setOnClickListener{limpar()}

        btnInfo3 = findViewById(R.id.butnInfo3)
        btnInfo3.setOnClickListener{info()}

        textDistancia3.setText("0")
        textVelocidade3.setText("0")
    }

    fun velocidadeTela(view: android.view.View) {
        val intent = Intent(this, VelocidadeMedia::class.java).apply {
        }
        startActivity(intent)
    }

    fun distanciaTela(view: android.view.View) {
        val intent = Intent(this, IntervaloDistancia::class.java).apply {
        }
        startActivity(intent)
    }

    fun Fragment.esconderTeclado() {
        view?.let { activity?.esconderTeclado(it) }
    }

    fun Activity.esconderTeclado() {
        if (currentFocus == null) View(this) else currentFocus?.let { esconderTeclado(it) }
    }

    fun Context.esconderTeclado(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun limpar(){
        textDistancia3.setText("0")
        textVelocidade3.setText("0")
        textResultado3s.setText("")
        textResultado3m.setText("")
        textResultado3h.setText("")
        textOr1.text = ""
        textOr2.text = ""
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Por favor, insira os valores de distância e velocidade de acordo com o Sistema Internacional de Unidades: Metro(m) e Metro por segundo(m/s), respectivamente. \n\nO cálculo é feito com a fórmula: ∆T = ∆S/Vm \n\nSendo que: \n- Vm(Velocidade Média) \n- ∆S (Intervalo de distância) \n- ∆T (Intervalo de tempo).")
        info.setPositiveButton("Entendi!"){ _, _ ->  }

        val dialogo: AlertDialog = info.create()
        dialogo.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calcular(){
        val distancia = textDistancia3.text.toString().toFloat()
        val velocidade = textVelocidade3.text.toString().toFloat()

        val intervaloTempS = (distancia / velocidade)
        val intervaloTempM = (distancia / velocidade) / 60
        val intervaloTempH = (distancia / velocidade) / 3600

        textOr1.text = "Ou"
        textOr2.text = "Ou"
        textResultado3s.setText("$intervaloTempS segundos")
        textResultado3m.setText("$intervaloTempM minutos")
        textResultado3h.setText("$intervaloTempH horas")
        esconderTeclado()
    }
}
