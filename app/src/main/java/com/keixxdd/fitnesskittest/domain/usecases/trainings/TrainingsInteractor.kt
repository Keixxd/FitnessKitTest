package com.keixxdd.fitnesskittest.domain.usecases.trainings

import com.keixxdd.fitnesskittest.data.ResponseWrapper
import com.keixxdd.fitnesskittest.data.repository.RepositoryImpl
import com.keixxdd.fitnesskittest.domain.model.training.Training
import javax.inject.Inject

class TrainingsInteractor @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : TrainingsUsecases {

    override suspend fun getPlan(): ResponseWrapper<Training?> = repositoryImpl.getTrainingsData()

}