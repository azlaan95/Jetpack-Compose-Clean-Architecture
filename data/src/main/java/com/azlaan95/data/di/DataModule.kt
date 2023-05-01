package com.azlaan95.data.di

import com.azlaan95.data.datasource.network.NetworkDatasource
import com.azlaan95.data.datasource.network.NetworkDatasourceImpl

object DataModule {
    fun getNetworkModule(): NetworkDatasource {
        return NetworkDatasourceImpl()
    }

    fun getDatabaseModule(): NetworkDatasource {
        return NetworkDatasourceImpl()
    }
}