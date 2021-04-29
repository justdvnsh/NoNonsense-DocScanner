package divyansh.tech.nononsense_docscanner.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import divyansh.tech.nononsense_docscanner.home.scan.callbacks.ScannedItemClick
import divyansh.tech.nononsense_docscanner.home.scan.epoxy.ScanItemsController

/*
* Module for fragments
* */
@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    /*
    * Provide Scan Items controller.
    * @param callbacks: ScanItemClick
    * @returns instance of ScanItemsController
    * */
    @Provides
    @FragmentScoped
    fun provideScanItemsController(
        callback: ScannedItemClick
    ): ScanItemsController = ScanItemsController(callback)

    /*
    * Provides the Scanned Item click callback
    * @param fragment: Fragment -> Fragment which has implemented the ScannedItemClick
    * @returns ScannedItemClick interface
    * */
    @Provides
    @FragmentScoped
    fun provideScannedItemClick(fragment: Fragment): ScannedItemClick =
        fragment as ScannedItemClick
}