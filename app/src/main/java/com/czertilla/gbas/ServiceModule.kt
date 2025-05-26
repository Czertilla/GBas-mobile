package com.czertilla.gbas

import android.content.Context
import androidx.room.Room
import com.czertilla.gbas.data.ServiceRepositoryImpl
import com.czertilla.gbas.data.local.dao.ServiceDao
import com.czertilla.gbas.data.local.datasource.ServiceLocalDataSource
import com.czertilla.gbas.data.local.datasource.ServiceLocalDataSourceImpl
import com.czertilla.gbas.data.local.db.AppDatabase
import com.czertilla.gbas.data.remote.api.ServiceApi
import com.czertilla.gbas.data.remote.datasource.ServiceRemoteDataSource
import com.czertilla.gbas.data.remote.datasource.ServiceRemoteDataSourceImpl
import com.czertilla.gbas.domain.repository.ServiceRepository
import com.czertilla.gbas.domain.usecase.GetServicesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideLocalDataSource(dao: ServiceDao): ServiceLocalDataSource {
        return ServiceLocalDataSourceImpl(dao)
    }

    @Provides
    fun provideRemoteDataSource(api: ServiceApi): ServiceRemoteDataSource {
        return ServiceRemoteDataSourceImpl(api)
    }

    @Provides
    fun provideServiceRepository(
        localDataSource: ServiceLocalDataSource,
        remoteDataSource: ServiceRemoteDataSource
    ): ServiceRepository {
        return ServiceRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetServicesUseCase(repository: ServiceRepository): GetServicesUseCase {
        return GetServicesUseCase(repository)
    }

}