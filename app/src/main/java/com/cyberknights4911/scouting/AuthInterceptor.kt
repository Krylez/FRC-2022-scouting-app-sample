package com.cyberknights4911.scouting

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("X-TBA-Auth-Key", "GET_YOUR_OWN_TBA_API_KEY_AND_PUT_IT_HERE")
                .build()
        )
    }
}