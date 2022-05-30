package cl.accenture.integrador_not_bored.data

import cl.accenture.integrador_not_bored.view.activitylist.ActivityItem

class ActivityItemRepository(val activityItemDatasource: ActivityItemDatasource) {
    fun getActivities(): List<ActivityItem> {
        return activityItemDatasource.get()
    }
}