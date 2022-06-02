package cl.accenture.integrador_not_bored.data.bored

import cl.accenture.integrador_not_bored.data.bored.dto.BoredActivity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredActivityService {

    @GET("/api/activity")
    fun getRandomActivity(@Query("participants") participants: Int): Call<BoredActivity>

    @GET("/api/activity")
    fun getActivityByType(@Query("type") type: String): Call<BoredActivity>
}