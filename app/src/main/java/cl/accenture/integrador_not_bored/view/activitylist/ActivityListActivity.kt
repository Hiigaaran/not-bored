package cl.accenture.integrador_not_bored.view.activitylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.data.ActivityItemDatasource
import cl.accenture.integrador_not_bored.data.ActivityItemRepository
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding
import cl.accenture.integrador_not_bored.view.activitydetail.ActivityDetail
import cl.accenture.integrador_not_bored.view.splash.MainActivity

class ActivityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //codigo agregado fco
        this.setTitle("Activities")
        //codigo agregado fco
        val activityListAdapter = ActivityListAdapter(intent)
        val cantidadParticipantes = intent.extras?.getString("participants", "NO")

        val activityItemRepository = ActivityItemRepository(ActivityItemDatasource())

        binding.activityList.layoutManager = LinearLayoutManager(this)
        binding.activityList.adapter = activityListAdapter
        activityListAdapter.setActivitiesItems(activityItemRepository.getActivities())

    }

    fun onRandomCallClick() {
        val intentRandomCall = Intent(this, ActivityDetail::class.java).apply {
            //Logica para obtener actividad aleatoria de la api de actividades.
            putExtra("activityTitle", "Random")
        }
    }
    /////////nuevo fco//////////////
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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
    //codigo agregado fco
}