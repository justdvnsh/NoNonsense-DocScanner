package divyansh.tech.nononsense_docscanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DocScanner: Application() {
    override fun onCreate() = super.onCreate()
}