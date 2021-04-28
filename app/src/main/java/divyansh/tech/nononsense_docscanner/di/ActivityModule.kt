package divyansh.tech.nononsense_docscanner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import divyansh.tech.nononsense_docscanner.home.pdf.PdfFragment
import divyansh.tech.nononsense_docscanner.home.scan.ScanFragment
import divyansh.tech.nononsense_docscanner.home.settings.SettingsFragment

/*
* Dependecy provider for activities
* */
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    /*
    * Provide Scan Fragment
    * @returns [ScanFragment]
    * */
    @Provides
    @ActivityScoped
    fun provideScanFragment(): ScanFragment = ScanFragment()

    /*
    * Provide PDF Fragment
    * @returns [PDFFragment]
    * */
    @Provides
    @ActivityScoped
    fun providePdfFragment(): PdfFragment = PdfFragment()

    /*
    * Provide Settings Fragment
    * @returns [SettingsFragment]
    * */
    @Provides
    @ActivityScoped
    fun provideSettingsFragment(): SettingsFragment = SettingsFragment()
}