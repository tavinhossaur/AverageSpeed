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
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.FieldPosition

open class VelocidadeMedia : AppCompatActivity(){

    private lateinit var textDistancia : EditText
    private lateinit var textTempo : EditText
    private lateinit var textResultadoms: EditText
    private lateinit var textResultadokmh: EditText
    private lateinit var textOr : TextView

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpar : Button
    private lateinit var btnInfo : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.velocidade_media)

        textDistancia = findViewById(R.id.addDist1)
        textTempo = findViewById(R.id.addTemp1)
        textResultadoms = findViewById(R.id.resCalc1_ms)
        textResultadokmh = findViewById(R.id.resCalc1_kmh)
        textOr = findViewById(R.id.textoOu)

        btnCalcular = findViewById(R.id.btnCalc1)
        btnCalcular.setOnClickListener{calcular()}

        btnLimpar = findViewById(R.id.btnClear1)
        btnLimpar.setOnClickListener{limpar()}

        btnInfo = findViewById(R.id.butnInfo1)
        btnInfo.setOnClickListener{info()}

        textDistancia.setText("0")
        textTempo.setText("0")
    }

    fun distanciaTela(view: android.view.View) {
        val intent = Intent(this, IntervaloDistancia::class.java).apply {
        }
        startActivity(intent)
    }

    fun tempoTela(view: android.view.View) {
        val intent = Intent(this, IntervaloTempo::class.java).apply {
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
        textDistancia.setText("0")
        textTempo.setText("0")
        textResultadoms.setText("")
        textResultadokmh.setText("")
        textOr.text = ""
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Por favor, insira os valores de distância e tempo de acordo com o Sistema Internacional de Unidades: Metro(m) e Segundos(s), respectivamente. \n\nO cálculo é feito com a fórmula: Vm = ∆S/∆T \n\nSendo que: \n- Vm(Velocidade Média) \n- ∆S (Intervalo de distância) \n- ∆T (Intervalo de tempo).")
        info.setPositiveButton("Entendi!"){ _, _ ->  }

        val dialogo: AlertDialog = info.create()
        dialogo.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calcular(){
        val distancia = textDistancia.text.toString().toFloat()
        val tempo = textTempo.text.toString().toFloat()

        val velocidadeMediaMs = (distancia / tempo)
        val velocidadeMediaKmh = (distancia / tempo) * 3.6

        textOr.text = "Ou"
        textResultadoms.setText("$velocidadeMediaMs m/s")
        textResultadokmh.setText("$velocidadeMediaKmh km/h")
        esconderTeclado()
    }
}