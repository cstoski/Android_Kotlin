package br.com.stoski.algoritmosclassicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var initialNumber = 0
        var quantityResults : Int =0

        //Leitura do SeekBar
        skbResultQuantity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                txtValueProgress.text = progress.toString()
                quantityResults = progress

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        }
        )

        //Clique do Botao Calcular
        btnCalculate.setOnClickListener{



        try{
            initialNumber = txtInitialNumber.text.toString().toInt()
            var provider : String = ""
            if (initialNumber==0 ||initialNumber==null){
                val toast = Toast.makeText(getApplicationContext(), "Insira um número Inicial Maior que 0!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
            } else if (quantityResults<=0 || quantityResults==null){
                val toast = Toast.makeText(getApplicationContext(), "Insira uma Quantidade Maior que 0!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
            } else if ( (!rbPrime.isChecked) && (!rbFibonacci.isChecked) && (!rbBoth.isChecked)){
                val toast = Toast.makeText(getApplicationContext(), "Selecione Pelo menos uma opção de Cálculo!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
                toast.show()
            }else{
                if (rbPrime.isChecked || rbBoth.isChecked){
                    //Calculo dos Números Primos
                    var listPrimes = calculatePrimes(initialNumber,quantityResults)
                    provider = "\n Números Primos: " + listPrimes.toString()
                }

                if(rbFibonacci.isChecked || rbBoth.isChecked){
                    //Calculos da Sequencia de Fibonacci
                    var listFibonacci = calculateFibonacci(initialNumber,quantityResults)
                    provider = provider + "\n\n Sequência Fibonacci : " + listFibonacci.toString()
                }

                // Preparar os dados para abrir na próxima tela
                val intent = Intent(this, ResultActivity::class.java )
                intent.putExtra("provider_key", provider)
                startActivity(intent)
            }
        }catch (e : Exception){
            val toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
        }
        }
    }
}

/***
 * Função que Calcular os Números Primos
 */
fun calculatePrimes (value : Int, quantity: Int): ArrayList<Int> {

    var listPrimes = ArrayList<Int>()
    var count =0
    var i = value

    while (count<quantity){
        var prime = 0

        for (j in 1..i) {
            if ((i % j) == 0) {
                prime++
            }
        }
        //Adiciona os numetos primos a lista
        if (prime == 2) {
            listPrimes.add(i)
            count++
        }
        i++
    }
  return listPrimes
}

/***
 * Função que Calcular a Sequencia de Fibonacci
 */
fun calculateFibonacci (value : Int, quantity: Int): ArrayList<Int> {

    var listFibonacci = ArrayList<Int>()

    var a =0
    var b =1
    var auxiliar = 0
    var count =0

    while (count<quantity){
            auxiliar = a+b
            a=b
            b=auxiliar

            if (auxiliar>=value){
                listFibonacci.add(auxiliar)
                count++
            }
    }
    return listFibonacci
}



