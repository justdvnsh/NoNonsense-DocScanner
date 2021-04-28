package divyansh.tech.nononsense_docscanner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import divyansh.tech.nononsense_docscanner.home.scan.epoxy.ScanItemsController

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    @FragmentScoped
    fun provideScanItemsController(): ScanItemsController = ScanItemsController()
}