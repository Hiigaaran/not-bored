package cl.accenture.integrador_not_bored.data

import cl.accenture.integrador_not_bored.view.activitylist.ActivityItem

class ActivityItemDatasource {
    private val activities = listOf(ActivityItem("Education"),
        ActivityItem("Recreational"),
        ActivityItem("Social"),
        ActivityItem("Diy"),
        ActivityItem("Charity"),
        ActivityItem("Cooking"),
        ActivityItem("Relaxation"),
        ActivityItem("Music"),
        ActivityItem("Busywork"))

    fun get(): List<ActivityItem> {
        return activities
    }
}