package com.antoniocostadossantos.git_repos_jetpackcompose.di

import android.app.Application
import com.antoniocostadossantos.git_repos_jetpackcompose.data.GitHubRestApi
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class StartDependencyInjection : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@StartDependencyInjection)
            modules(network, apiModule, viewModelModule)
        }
    }

    private val network = module {
        single {
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        isLenient = true

                    })
                }
            }
        }
    }

    private val apiModule = module {
        single { GitHubRestApi(get()) }
    }

    private val viewModelModule = module {
        viewModel { HomeViewModel(api = get()) }
    }


}