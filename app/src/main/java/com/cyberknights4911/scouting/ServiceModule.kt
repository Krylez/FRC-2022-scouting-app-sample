package com.cyberknights4911.scouting

import android.content.Context
import com.google.net.cronet.okhttptransport.CronetInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.chromium.net.CronetEngine
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
    fun providesOkHttpClient(
        interceptor: AuthInterceptor,
        cache: Cache,
        cronetEngine: CronetEngine
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(CronetInterceptor.newBuilder(cronetEngine).build())
            .build()
    }

    @Provides
    fun providesCronetEngine(@ApplicationContext context: Context): CronetEngine {
        return CronetEngine.Builder(context)
            .enableQuic(true) // Not actually sure if TBA supports this
            .setUserAgent("CyberKnights sample scouting app")
            .build()
    }

    @Provides
    fun providesCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, (5 * 1024 * 1024).toLong())
    }
}
