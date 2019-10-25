package onoffrice.weatherhelp.data

import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.data.repositories.WeatherRepositoryImplementation
import onoffrice.weatherhelp.ui.home.StateCitiesViewModel
import onoffrice.weatherhelp.ui.states.StatesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinInjector {

    val splashModule = module {}

    val stateModule = module {
        viewModel { StatesViewModel() }
    }

    val stateCitiesModule = module {
        single<WeatherRepository> { WeatherRepositoryImplementation }
        viewModel { StateCitiesViewModel(get()) }
    }
}