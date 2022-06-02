package cl.accenture.integrador_not_bored.data.bored

import cl.accenture.integrador_not_bored.data.bored.dto.BoredActivity
import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryError
import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryResponse
import cl.accenture.integrador_not_bored.data.util.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoredActivityDataSource {

    fun getRandomActivity(participants: Int, listener: ResponseListener<BoredActivity>) {
        val service = RetrofitService.instance.create(BoredActivityService::class.java).getRandomActivity(participants)

        service.enqueue(object: Callback<BoredActivity> {
            override fun onResponse(call: Call<BoredActivity>, response: Response<BoredActivity>) {
                val post = response.body()
                if (response.isSuccessful && post != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = post
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "The app request could not reach destination",
                            code = response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<BoredActivity>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Unexpected error",
                        code = -1,
                    )
                )
            }

        })

    }

    fun getActivityByType(type: String, listener: ResponseListener<BoredActivity>) {
        val service = RetrofitService.instance.create(BoredActivityService::class.java).getActivityByType(type)

        service.enqueue(object: Callback<BoredActivity> {
            override fun onResponse(call: Call<BoredActivity>, response: Response<BoredActivity>) {
                val post = response.body()
                if (response.isSuccessful && post != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = post
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "The app request could not reach destination",
                            code = response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<BoredActivity>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Unexpected error",
                        code = -1,
                    )
                )
            }

        })

    }
}