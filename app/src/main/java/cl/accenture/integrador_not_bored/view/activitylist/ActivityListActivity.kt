package cl.accenture.integrador_not_bored.view.activitylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.data.ActivityItemDatasource
import cl.accenture.integrador_not_bored.data.ActivityItemRepository
import cl.accenture.integrador_not_bored.databinding.ActivityListBinding

class ActivityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activityListAdapter = ActivityListAdapter()
        val activityItemRepository = ActivityItemRepository(ActivityItemDatasource())

        binding.activityList.layoutManager = LinearLayoutManager(this)
        activityListAdapter.setActivitiesItems(activityItemRepository.getActivities())
    }
}