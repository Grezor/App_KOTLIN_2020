package fr.duplessigeoffrey.choptaphoto.ppe_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // quand on clic sur l'evenement
        validateCodeEventButton.setOnClickListener {
            val codeEvent = codeEventeditText.text.toString()
            // on vérifie qui a au moins 1 caractère
            if(!codeEvent.isNullOrBlank()) {
                // ouverture de la photolist en lui passant le code evenelent saisi
                val intent = Intent(this, PhotoListActivity::class.java)
                intent.putExtra("CODE_EVENT", codeEvent)
                this.startActivity(intent)
            }
        }
    }
}
