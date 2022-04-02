package com.keixxdd.fitnesskittest.data.repository

import com.keixxdd.fitnesskittest.data.ResponseWrapper
import com.keixxdd.fitnesskittest.data.source.retrofit.FitnessKitApiService
import com.keixxdd.fitnesskittest.domain.model.training.Training
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service : FitnessKitApiService
): FitnessKitRepository {

    private fun <T> wrapResult(response: Response<T>): ResponseWrapper<T?> {
        return if(response.isSuccessful){
            ResponseWrapper.Success(response.body())
        }else{
            ResponseWrapper.Failure(response.message())
        }
    }

    override suspend fun getTrainingsData(): ResponseWrapper<Training?> =
        try {
            val response = service.getTrainingsData()
            wrapResult(response)
        }catch (e : HttpException){
            ResponseWrapper.Failure(e.localizedMessage)
        }catch (e: IOException){
            ResponseWrapper.Failure("An unexpected error occurred.")
        }
}