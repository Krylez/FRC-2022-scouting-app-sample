package com.cyberknights4911.scouting

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesBlueAllianceService(
        factory : Converter.Factory,
        client: OkHttpClient
    ) : BlueAllianceService {
        return Retrofit.Builder()
            .addConverterFactory(factory)
            .baseUrl("https://www.thebluealliance.com/api/v3/")
            .client(client)
            .build()
            .create(BlueAllianceService::class.java)
    }

    @Provides
    fun providesConverterFactory() : Converter.Factory {
        return MoshiConverterFactory.create();
    }

    @Provides
    fun providesOkHttpClient(interceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}
