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
import org.w3c.dom.Text

class IntervaloDistancia : AppCompatActivity() {

    private lateinit var textVelocidade2 : EditText
    private lateinit var textTempo2 : EditText
    private lateinit var textResultado2cm: EditText
    private lateinit var textResultado2m: EditText
    private lateinit var textResultado2km: EditText
    private lateinit var textOr1 : TextView
    private lateinit var textOr2 : TextView

    private lateinit var btnCalcular2 : Button
    private lateinit var btnLimpar2 : Button
    private lateinit var btnInfo2 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intervalo_distancia)

        textVelocidade2 = findViewById(R.id.addVelo2)
        textTempo2 = findViewById(R.id.addTemp2)
        textResultado2cm = findViewById(R.id.resCalc2_cm)
        textResultado2m = findViewById(R.id.resCalc2_m)
        textResultado2km = findViewById(R.id.resCalc2_km)
        textOr1 = findViewById(R.id.textoOu_1)
        textOr2 = findViewById(R.id.textoOu_2)

        btnCalcular2 = findViewById(R.id.btnCalc2)
        btnCalcular2.setOnClickListener{calcular()}

        btnLimpar2 = findViewById(R.id.btnClear2)
        btnLimpar2.setOnClickListener{limpar()}

        btnInfo2 = findViewById(R.id.butnInfo2)
        btnInfo2.setOnClickListener{info()}

        textVelocidade2.setText("0")
        textTempo2.setText("0")
    }

    fun velocidadeTela(view: android.view.View) {
        val intent = Intent(this, VelocidadeMedia::class.java).apply {
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
        textVelocidade2.setText("0")
        textTempo2.setText("0")
        textResultado2cm.setText("")
        textResultado2m.setText("")
        textResultado2km.setText("")
        textOr1.text = ""
        textOr2.text = ""
    }

    private fun info(){
        val info = AlertDialog.Builder(this)
        info.setTitle("Informações")
        info.setIcon(R.drawable.info)
        info.setMessage("Por favor, insira os valores de velocidade e tempo de acordo com o Sistema Internacional de Unidades: Metro por segundo(m/s) e Segundos(s), respectivamente. \n\nO cálculo é feito com a fórmula: ∆S = Vm * ∆T \n\nSendo que: \n- Vm(Velocidade Média) \n- ∆S (Intervalo de distância) \n- ∆T (Intervalo de tempo).")
        info.setPositiveButton("Entendi!"){ _, _ ->  }

        val dialogo: AlertDialog = info.create()
        dialogo.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calcular(){
        val velocidade = textVelocidade2.text.toString().toFloat()
        val tempo = textTempo2.text.toString().toFloat()

        val intervaloDistCm = (velocidade * tempo) * 100
        val intervaloDistM = (velocidade * tempo)
        val intervaloDistKm = (velocidade * tempo) / 1000

        textOr1.text = "Ou"
        textOr2.text = "Ou"
        textResultado2cm.setText("$intervaloDistCm centímetros")
        textResultado2m.setText("$intervaloDistM metros")
        textResultado2km.setText("$intervaloDistKm quilômetros")
        esconderTeclado()
    }
}
