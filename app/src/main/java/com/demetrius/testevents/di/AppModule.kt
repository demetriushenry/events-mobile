package com.demetrius.testevents.di

import com.demetrius.testevents.repository.EventRepository
import com.demetrius.testevents.service.ApiService
import com.demetrius.testevents.view.viewmodel.EventViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}

val repositoryModule = module {
    single { EventRepository(get()) }
}

val viewModelModule = module {
    viewModel { EventViewModel(get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)