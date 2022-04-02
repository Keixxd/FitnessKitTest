package com.keixxdd.fitnesskittest.data.source.retrofit

import com.keixxdd.fitnesskittest.domain.model.training.Training
import com.keixxdd.fitnesskittest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FitnessKitApiService {

    @GET("schedule/get_v3/")
    suspend fun getTrainingsData(
        @Query("club_id") clubId: Int = Constants.defaultClubId
    ): Response<Training>

}