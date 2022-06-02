package cl.accenture.integrador_not_bored.data.bored

import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryError
import cl.accenture.integrador_not_bored.data.bored.dto.RepositoryResponse

interface ResponseListener<T> {

    fun onResponse(response: RepositoryResponse<T>)

    fun onError(repositoryError: RepositoryError)

}