package com.azlaan95.domain.base

import com.azlaan95.data.datasource.network.NetworkDatasource
import com.azlaan95.data.di.DataModule

open class BaseRepository {
    fun getNetworkSource(): NetworkDatasource {
        return DataModule.getNetworkModule()
    }
}