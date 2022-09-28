package no.uio.adasam.adasam_oblig1


import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setter knapp nr 1 så den reagerer ved trykk
        val button: Button = findViewById(R.id.button_one)
        button.setOnClickListener {
            this.sendSvar()
        }


        //Setter knapp nr 2 så den reagerer ved trykk
        val buttonTwo: Button = findViewById(R.id.button_two)
        buttonTwo.setOnClickListener {
            this.sendTilNesteAktivitet()
        }
    }
    //Skriv ut svaret
    private fun sendSvar() {
        val editText = findViewById<EditText> (R.id.text_input)
        val textInn = editText.text.toString()

        val svarText = findViewById<TextView> (R.id.textView)
        svarText.text = getString(R.string.blank)

        if (erPalindrom(textInn)){
            svarText.text = getString(R.string.palindrom)
        } else{
            svarText.text = getString(R.string.ikkePalindrom)
        }
        //Fikse edit text hintet og keyboard
        editText.text.clear()
        editText.hint = getString(R.string.input)
        taVekkKeyBoard()
    }

    //Funksjon som sjekker om input er et palindrom
    private fun erPalindrom(innText: String) : Boolean{
        val baklengs = innText.reversed()
        if (baklengs.equals(innText, ignoreCase = true)){
            return true
        }
        return false
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

    private fun sendTilNesteAktivitet(){
        val intent = Intent(this, ConverterActivity::class.java)
        startActivity(intent)
    }
}