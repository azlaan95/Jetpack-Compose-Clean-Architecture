package com.azlaan95.domain.di

import com.azlaan95.domain.repository.ConversionRepository
import com.azlaan95.domain.repository.ConversionRepositoryImpl
import com.azlaan95.domain.usecase.ConversionUseCase
import com.azlaan95.domain.usecase.ConversionUseCaseImpl

object DomainModule {
    fun getConversionRepository(): ConversionRepository {
        return ConversionRepositoryImpl()
    }

    fun getConversionUseCase(): ConversionUseCase {
        return ConversionUseCaseImpl()
    }
}