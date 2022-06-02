package cl.accenture.integrador_not_bored.data.bored

import cl.accenture.integrador_not_bored.data.bored.dto.BoredActivity

class BoredActivityRepository(private val boredActivityDataSource: BoredActivityDataSource) {

    fun getRandomActivity(participants: Int, listener: ResponseListener<BoredActivity>) {
        this.boredActivityDataSource.getRandomActivity(participants, listener)
    }

    fun getActivityByType(type: String, listener: ResponseListener<BoredActivity>) {
        this.boredActivityDataSource.getActivityByType(type, listener)
    }
}