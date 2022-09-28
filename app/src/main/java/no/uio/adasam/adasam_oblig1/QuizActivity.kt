package no.uio.adasam.adasam_oblig1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    var poeng = 0
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val spmText =findViewById<TextView> (R.id.textViewSpm)
        spmText.text = getString(R.string.blank)

        val poengText =findViewById<TextView> (R.id.textViewPoeng)
        poengText.text = getString(R.string.poeng)

        val buttonFleip: Button = findViewById(R.id.buttonFleip)
        val buttonFakta: Button = findViewById(R.id.buttonFakta)

        val spm1 = Question( "Vann er et grunnstoff", false)
        val spm2 = Question( "Huden til isbjørnen er svart", true)
        val spm3 = Question( "Jupiter er den femte planeten fra solen", true)

        val liste : MutableList<Question> = mutableListOf(spm1, spm2, spm3)

        fun settTransparent(){
            buttonFleip.background.alpha = 20
            buttonFakta.background.alpha = 20
        }

        fun visNesteAktivitet(innIndex : Int){
            if (innIndex < 3){
                val naaSpm = liste[innIndex]
                spmText.text = naaSpm.spoorsmaal
            } else {
                spmText.text = getString(R.string.ferdig)
                settTransparent()
            }
        }

        visNesteAktivitet(index)

        fun settFarger(){
            buttonFleip.background.alpha = 255
            buttonFakta.background.alpha = 255
        }

        fun sjekkFakta(view: TextView, i : Int){
            index += 1
            if (index <= 3){
                val s = liste[i]
                if (s.svar){
                    poeng += 1
                }
                var str = getString(R.string.poengStart)
                str += poeng
                str += getString(R.string.maxPoeng)
                view.text = str
                visNesteAktivitet(this.index)
            }
            return
        }

        fun sjekkFleip(view:TextView, i:Int){
            index += 1
            if (index <= 3){
                val s = liste[i]
                if (!s.svar){
                    poeng += 1
                }
                var str = getString(R.string.poengStart)
                str += poeng
                str += getString(R.string.maxPoeng)
                view.text = str
                visNesteAktivitet(this.index)
            }
            return
        }

        buttonFakta.setOnClickListener {
            sjekkFakta(poengText, index)
        }

        buttonFleip.setOnClickListener {
            sjekkFleip(poengText, index)
        }

        fun nullStill(){
            poeng = 0
            index = 0
            poengText.text = getString(R.string.poeng)
            settFarger()
            visNesteAktivitet(index)
        }

        val buttonNullStill: Button = findViewById(R.id.buttonNullStill)
        buttonNullStill.setOnClickListener {
            nullStill()
        }


    }



}

