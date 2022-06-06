package cl.accenture.integrador_not_bored.view.activitydetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.databinding.ActivityDetailBinding
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding
import cl.accenture.integrador_not_bored.view.activitylist.ActivityListActivity
import cl.accenture.integrador_not_bored.view.activitylist.ActivityListViewModel
import cl.accenture.integrador_not_bored.view.activitylist.ViewModelFactory

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: ActivityListViewModel by viewModels(
        factoryProducer = { ViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        this.setTitle("Loading")
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /////////nuevo//////////////
        this.setTitle(intent.extras?.getString("activityTitle").toString())
        binding.detailTitle.text = intent.extras?.getString("activityName")
        binding.categoryText.text = intent.extras?.getString("activityCategory") ?: ""
        binding.priceCount.text = intent.extras?.getString("activityPrice")
        /////////nuevo fco//////////////

        intent.extras?.run {
            binding.participantsCount.text = getString("activityParticipants")
            //comentado fco////
            //binding.toolbar2.title = getString("activityTitle")
        }

        binding.tryAgainBtn.setOnClickListener {
            onTryAnotherClick()
        }
    }

    fun onTryAnotherClick() {
        if (binding.categoryText.text.isNullOrBlank()) {
            onActivityCall(this.title.toString())
        } else {
            onRandomCall(binding.participantsCount.text.toString().toInt())
        }
    }

    fun onBackBtnClick() {
        val intentReturn = Intent(this, ActivityListActivity::class.java).apply {
            putExtra("participants", intent.extras?.getString("activityParticipants") ?: "0")
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    /////////nuevo fco//////////////
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){

            R.id.Back -> {

                onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }
    /////////nuevo//////////////

    fun onRandomCall(participants: Int) {
        viewModel.getRandomActivity(participants)
        viewModel.activity.observe(this) { value ->
            val intentTryAnother = Intent(this, ActivityDetail::class.java).apply {
                //Logica de llamada a API para realizar llamada usando el parámetro de entrada participantes.
                //Se guardan los resultados de la llamada para generar la proxima actividad.
                putExtra("activityCategory", "")
                putExtra("activityPrice", value?.price.toString())
                putExtra("activityName", value?.activity.toString())
                putExtra("activityParticipants", value?.participants.toString())
                putExtra("activityTitle", this@ActivityDetail.title.toString())
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//Deshabilitar flag si se quiere guardar el stack de ActivityDetail generados.
            }
            startActivity(intentTryAnother)
        }
    }

    fun onActivityCall(type: String) {
        viewModel.getActivityByType(type.lowercase())
        viewModel.activity.observe(this) { value ->
            val intentTryAnother = Intent(this, ActivityDetail::class.java).apply {
                //Logica de llamada a API para realizar llamada usando el parámetro de entrada participantes.
                //Se guardan los resultados de la llamada para generar la proxima actividad.
                putExtra("activityCategory", value?.type.toString())
                putExtra("activityPrice", value?.price.toString())
                putExtra("activityName", value?.activity.toString())
                putExtra("activityParticipants", value?.participants.toString())
                putExtra("activityTitle", "Random")
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//Deshabilitar flag si se quiere guardar el stack de ActivityDetail generados.
            }
            startActivity(intentTryAnother)
        }
    }

}