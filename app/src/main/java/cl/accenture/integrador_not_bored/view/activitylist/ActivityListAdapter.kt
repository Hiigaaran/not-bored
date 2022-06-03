package cl.accenture.integrador_not_bored.view.activitylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import cl.accenture.integrador_not_bored.databinding.ActivityItemBinding
import cl.accenture.integrador_not_bored.view.activitydetail.ActivityDetail

class ActivityListAdapter(splashIntent: Intent, viewModel: ViewModel): RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var activities: List<ActivityItem>
    private val mainIntent = splashIntent
    private val viewModel = viewModel

    fun setActivitiesItems(newActivityItem: List<ActivityItem>) {
        this.activities = newActivityItem
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemBinding.inflate(layoutInflater,parent, false)

        return ActivityViewHolder(binding, mainIntent, viewModel)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(this.activities[position])
    }

    override fun getItemCount(): Int {
        return this.activities.size
    }

    class ActivityViewHolder(private val binding: ActivityItemBinding, listIntent: Intent, viewModel: ViewModel): RecyclerView.ViewHolder(binding.root) {
        val itemIntent = listIntent
        val viewModel: ActivityListViewModel = viewModel as ActivityListViewModel
        fun bind(activity: ActivityItem) {
            this.binding.activityTitle.text = activity.activityName
            this.binding.button.setOnClickListener {
                viewModel.getActivityByType(this.binding.activityTitle.text.toString().lowercase())
                var participants = itemIntent.extras.let { it -> it?.getString("participants") }
                    ?: kotlin.run { "0" }
                    this@ActivityViewHolder.binding.root.findViewTreeLifecycleOwner()
                        ?.let { it1 -> viewModel.activity.observe(it1) {
                            value ->
                            val intent = Intent(it.context, ActivityDetail::class.java).apply {
                            putExtra("activityCategory", "")
                            putExtra("activityPrice", value?.price.toString())
                            putExtra("activityName", value?.activity)
                            putExtra("activityParticipants", value?.participants)
                            putExtra("activityTitle", binding.activityTitle.text)
                        }
                            ContextCompat.startActivity(it.context, intent, null)
                        }
                    //putExtra("activityParticipants", participants)
                    //putExtra("activityTitle", binding.activityTitle.text)
                }
            }
        }
    }
}