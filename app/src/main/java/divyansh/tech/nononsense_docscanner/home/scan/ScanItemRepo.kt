package divyansh.tech.nononsense_docscanner.home.scan

import android.net.Uri
import androidx.core.net.toUri
import io.reactivex.rxjava3.core.Observable
import java.io.File
import javax.inject.Inject

/*
* ScanItemRepo to provide support functions to scan view model
* */
class ScanItemRepo @Inject constructor() {

    /*
    * Function to iterate over all the files in the scan items directory
    * @param externalMediaDirs -> Array<Files> -> Get the external media dir
    * @param filename: String -> Get the dir where the scanned docs are saved
    * @returns an Observable list of uris
    * */
    fun getScannedDocs(externalMediaDirs: Array<File>, fileName: String): Observable<List<Uri>> {
        val list = mutableListOf<Uri>()
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, fileName).apply { mkdirs() }
        }
        if (mediaDir != null && mediaDir.exists()) {
            for (file in mediaDir.listFiles()) {
                list.add(file.absolutePath.toUri())
            }
        }
        return Observable.fromArray(list.toList())
    }
}