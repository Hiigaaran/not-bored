package cl.accenture.integrador_not_bored.view.activitylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.accenture.integrador_not_bored.databinding.ActivityItemBinding

class ActivityListAdapter(private val listener: ActivityListener): RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var activities: List<ActivityItem>

    interface ActivityListener {
        fun select(activityName: String)
    }

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

    inner class ActivityViewHolder(private val binding: ActivityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: ActivityItem) {
            this.binding.activityTitle.text = activity.activityName
            this.binding.button.setOnClickListener {
                listener.select(this.binding.activityTitle.text.toString().lowercase())
            }
        }
    }
}