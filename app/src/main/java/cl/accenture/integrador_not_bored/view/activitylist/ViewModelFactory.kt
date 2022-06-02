package cl.accenture.integrador_not_bored.view.activitylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.accenture.integrador_not_bored.data.bored.BoredActivityDataSource
import cl.accenture.integrador_not_bored.data.bored.BoredActivityRepository

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val boredActivityDataSource = BoredActivityDataSource()
        val boredActivityRepository = BoredActivityRepository(boredActivityDataSource)

        return ActivityListViewModel(boredActivityRepository) as T
    }
}