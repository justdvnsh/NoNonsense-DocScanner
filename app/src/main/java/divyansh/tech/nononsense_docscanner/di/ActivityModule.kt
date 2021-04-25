package divyansh.tech.nononsense_docscanner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import divyansh.tech.nononsense_docscanner.home.fragments.HomeFragment

/*
* Dependecy provider for activities
* */
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    /*
    * Provide Home Fragment
    * @returns [HomeFragment]
    * */
    @Provides
    @ActivityScoped
    fun provideHomeFragment(): HomeFragment = HomeFragment()
}