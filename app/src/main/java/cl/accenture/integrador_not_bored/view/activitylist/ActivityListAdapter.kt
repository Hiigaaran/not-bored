package cl.accenture.integrador_not_bored.view.activitylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import cl.accenture.integrador_not_bored.databinding.ActivityItemBinding
import cl.accenture.integrador_not_bored.view.activitydetail.ActivityDetail

class ActivityListAdapter(splashIntent: Intent): RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var activities: List<ActivityItem>
    private val mainIntent = splashIntent

    fun setActivitiesItems(newActivityItem: List<ActivityItem>) {
        this.activities = newActivityItem
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemBinding.inflate(layoutInflater,parent, false)
        return ActivityViewHolder(binding, mainIntent)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(this.activities[position])
    }

    override fun getItemCount(): Int {
        return this.activities.size
    }

    class ActivityViewHolder(private val binding: ActivityItemBinding, listIntent: Intent): RecyclerView.ViewHolder(binding.root) {
        val itemIntent = listIntent
        fun bind(activity: ActivityItem) {
            this.binding.activityTitle.text = activity.activityName
            this.binding.button.setOnClickListener {
                var participants = itemIntent.extras.let { it -> it?.getString("participants") }
                    ?: kotlin.run { "0" }
                val intent = Intent(it.context, ActivityDetail::class.java).apply {
                    putExtra("activityParticipants", participants)
                    putExtra("activityTitle", binding.activityTitle.text)
                }
                ContextCompat.startActivity(it.context, intent, null)
            }
        }
    }
}