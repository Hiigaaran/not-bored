package cl.accenture.integrador_not_bored.view.activitylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.accenture.integrador_not_bored.data.bored.BoredActivityRepository
import cl.accenture.integrador_not_bored.data.bored.ResponseListener
import cl.accenture.integrador_not_bored.data.bored.dto.BoredActivity
import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryError
import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryResponse

class ActivityListViewModel(private val repository: BoredActivityRepository): ViewModel() {
    val activity = MutableLiveData<BoredActivity?>(null)
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String?>(null)

    fun getRandomActivity(participants: Int) {
        error.value = null
        activity.value = null
        loading.value = true

        repository.getRandomActivity(participants, object: ResponseListener<BoredActivity> {
            override fun onResponse(response: RepositoryResponse<BoredActivity>) {
                val responseActivity = response.data

                loading.value = false
                error.value = null
                activity.value = responseActivity
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.code})"

                loading.value = false
                error.value = message
                activity.value = null
            }

        })
    }

    fun getActivityByType(type: String) {
        error.value = null
        activity.value = null
        loading.value = true

        repository.getActivityByType(type, object: ResponseListener<BoredActivity> {
            override fun onResponse(response: RepositoryResponse<BoredActivity>) {
                val responseActivity = response.data

                loading.value = false
                error.value = null
                activity.value = responseActivity
            }

            override fun onError(repositoryError: RepositoryError) {
                val message = "${repositoryError.message} (code: ${repositoryError.code})"

                loading.value = false
                error.value = message
                activity.value = null
            }

        })

    }
}