package no.uio.adasam.adasam_oblig1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.math.RoundingMode

class ConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val spinn : Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.maale_enheter,
            android.R.layout.simple_spinner_item
        ).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinn.adapter = adapter
        }

        //Setter konverterknapp så den reagerer ved trykk
        val button: Button = findViewById(R.id.button_one)

        button.setOnClickListener {
            this.konverter(spinn)
        }

        //Setter knapp nr 2 så den reagerer ved trykk
        val buttonNextActivity: Button = findViewById(R.id.button_next)
        buttonNextActivity.setOnClickListener {
            this.sendTilNesteAktivitet2()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun konverter(spinnerInn : Spinner){
        val maal = spinnerInn.selectedItem
        val maalText = maal.toString()

        val editText = findViewById<EditText> (R.id.text_input_konverter)
        val textInn = editText.text.toString()

        this.taVekkKeyBoard()

        val svarText = findViewById<TextView> (R.id.textView2)
        svarText.text = getString(R.string.blank)

        if (textInn.isNotEmpty()) {
            val tallInn = textInn.toDouble()
            var etterKonvertering = 0.0

            when (maalText) {
                "Fluid ounce (fl oz)" -> {
                    etterKonvertering = tallInn * (0.02957)
                }
                "Cup (cp)" -> {
                    etterKonvertering = tallInn * (0.23659)
                }
                "Gallon (gal)" -> {
                    etterKonvertering = tallInn * (3.78541)
                }
                "Hogshead" -> {
                    etterKonvertering = tallInn * (238.481)
                }
            }

            val toDesimaler = etterKonvertering.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)
            svarText.text = "$toDesimaler L"
        } else {
            this.feilMelding()
        }
    }

    //Funksjon som tar vekk keyboard
    private fun taVekkKeyBoard(){
        val view = this.currentFocus
        if (view != null){
            val taVekk = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            taVekk.hideSoftInputFromWindow(view.windowToken, 0)
        }
        else{
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }

    private fun feilMelding(){
        val text = resources.getString(R.string.feilMld)
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun sendTilNesteAktivitet2(){
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }
}