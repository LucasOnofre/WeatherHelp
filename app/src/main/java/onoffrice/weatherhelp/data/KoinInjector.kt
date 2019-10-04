package onoffrice.weatherhelp.data

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinInjector {

    val splashModule = module {}

//    val homeModule = module {
//        single<ConsultationRepository> { ConsultationRepositoryImplementation }
//        factory { ConsultAdapter(context = get(), onClickListener = null) }
//        viewModel { HomeViewModel(get()) }
//    }
}