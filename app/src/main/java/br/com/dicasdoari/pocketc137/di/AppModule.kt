package br.com.dicasdoari.pocketc137.di

import br.com.dicasdoari.pocketc137.data.network.API
import br.com.dicasdoari.pocketc137.data.repository.CharacterRepository
import br.com.dicasdoari.pocketc137.data.repository.CharacterRepositoryImpl
import br.com.dicasdoari.pocketc137.ui.characters.CharactersViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single { HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single { OkHttpClient.Builder()
        .addInterceptor(get<HttpLoggingInterceptor>())
        .build()
    }

    single { Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build() }

    single { Retrofit.Builder()
        .client(get())
        .baseUrl("https://rickandmortyapi.com")
        .addConverterFactory(MoshiConverterFactory.create(get()))
        .build()
    }

    single { get<Retrofit>().create(API::class.java) }

    single { Dispatchers.IO }

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }

    viewModel { CharactersViewModel(get()) }

}