package cl.accenture.integrador_not_bored.view.activitydetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import cl.accenture.integrador_not_bored.databinding.ActivityDetailBinding
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding
import cl.accenture.integrador_not_bored.view.activitylist.ActivityListActivity

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.run {
            binding.participantsCount.text = getString("activityParticipants")
            binding.toolbar2.title = getString("activityTitle")
        }

        binding.tryAgainBtn.setOnClickListener {
            onTryAnotherClick()
        }
    }

    fun onTryAnotherClick() {
        val intentTryAnother = Intent(this, ActivityDetail::class.java).apply {
            //Logica de llamada a API para realizar llamada usando el parámetro de entrada participantes.
            //Se guardan los resultados de la llamada para generar la proxima actividad.
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//Deshabilitar flag si se quiere guardar el stack de ActivityDetail generados.
        }
        startActivity(intentTryAnother)
    }

    fun onBackBtnClick() {
        val intentReturn = Intent(this, ActivityListActivity::class.java).apply {
            putExtra("participants", intent.extras?.getString("activityParticipants") ?: "0")
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
}