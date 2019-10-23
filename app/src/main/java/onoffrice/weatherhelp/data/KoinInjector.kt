package onoffrice.weatherhelp.data

import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.data.repositories.WeatherRepositoryImplementation
import onoffrice.weatherhelp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinInjector {

    val splashModule = module {}

    val homeModule = module {
        single<WeatherRepository> { WeatherRepositoryImplementation }
        viewModel { HomeViewModel(get()) }
    }
}