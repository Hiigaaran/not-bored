package cl.accenture.integrador_not_bored.view.activitylist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cl.accenture.integrador_not_bored.R
import cl.accenture.integrador_not_bored.databinding.ActivityItemBinding
import cl.accenture.integrador_not_bored.view.activitydetail.ActivityDetail

class ActivityListAdapter: RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var activities: List<ActivityItem>

    fun setActivitiesItems(newActivityItem: List<ActivityItem>) {
        this.activities = newActivityItem
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemBinding.inflate(layoutInflater,parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(this.activities[position])
    }

    override fun getItemCount(): Int {
        return this.activities.size
    }

    class ActivityViewHolder(private val binding: ActivityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: ActivityItem) {
            this.binding.activityTitle.text = activity.activityName
            this.binding.button.setOnClickListener {
                val intent = Intent(it.context, ActivityDetail::class.java).apply {
                    var participants: String? = "0"
                    this.extras?.run { participants = getString(R.string.main_activity_lbl_participants.toString()) }
                    putExtra("activityParticipants", participants)
                    putExtra("activityTitle", binding.activityTitle.text)
                }
                ContextCompat.startActivity(it.context, intent, null)
            }
        }
    }
}