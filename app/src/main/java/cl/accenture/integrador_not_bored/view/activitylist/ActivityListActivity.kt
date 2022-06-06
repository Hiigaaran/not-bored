package cl.accenture.integrador_not_bored.view.activitylist

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.data.ActivityItemDatasource
import cl.accenture.integrador_not_bored.data.ActivityItemRepository
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding
import cl.accenture.integrador_not_bored.view.activitydetail.ActivityDetail
import cl.accenture.integrador_not_bored.view.splash.MainActivity

class ActivityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val viewModel: ActivityListViewModel by viewModels(
        factoryProducer = { ViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //codigo agregado fco
        this.setTitle("Activities")
        //codigo agregado fco
        val activityListAdapter = ActivityListAdapter(object: ActivityListAdapter.ActivityListener {
            override fun select(activityName: String) {
                onActivityTypeCall(activityName)
            }

        } )
        val cantidadParticipantes = intent.extras?.getString("participants", "NO")

        val activityItemRepository = ActivityItemRepository(ActivityItemDatasource())

        binding.activityList.layoutManager = LinearLayoutManager(this)
        binding.activityList.adapter = activityListAdapter
        activityListAdapter.setActivitiesItems(activityItemRepository.getActivities())

    }

    fun onActivityTypeCall(categoryName: String) {
        viewModel.getActivityByType(categoryName)
        viewModel.activity.observe(this) {
            value ->
            if (null != value) {
                val intent = Intent(this, ActivityDetail::class.java).apply {
                    putExtra("activityCategory", "")
                    putExtra("activityPrice", value.price.toString())
                    putExtra("activityName", value.activity)
                    putExtra("activityParticipants", value.participants.toString())
                    putExtra("activityTitle", value.type)
                }
                startActivity(intent)
            }
        }
    }

    fun onRandomCallClick() {
        //Logica para obtener actividad aleatoria de la api de actividades.
        var activityCategory: String = ""
        var activityPrice: String = ""
        var activityName: String = ""
        var activityParticipants: String = ""
        viewModel.getRandomActivity(intent.extras?.getString("participants").toString().toInt())
        viewModel.activity.observe(this) { value ->
            if(null != value) {
                activityCategory = value.type
                activityPrice = value.price.toString()
                activityName = value.activity
                activityParticipants = value.participants.toString()

                val intentRandomCall = Intent(this, ActivityDetail::class.java).apply {
                    putExtra("activityCategory", activityCategory)
                    putExtra("activityPrice", activityPrice)
                    putExtra("activityName", activityName)
                    putExtra("activityParticipants", activityParticipants)
                    putExtra("activityTitle", "Random")
                }
                startActivity(intentRandomCall)
            }
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
                //onBackPressed()
                onRandomCallClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //codigo agregado fco


}