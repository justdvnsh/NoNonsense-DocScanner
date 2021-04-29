package divyansh.tech.nononsense_docscanner.home.scan

import android.net.Uri
import android.util.Log
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
        Log.i("REPO", "Inside getScanned repo")
        val list = mutableListOf<Uri>()
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, fileName).apply { mkdirs() }
        }
        Log.i("REPO", mediaDir.toString())
        if (mediaDir != null && mediaDir.exists()) {
            Log.i("REPO", "YES it does exists")
            for (file in mediaDir.listFiles()) {
                Log.i("REPO", file.absolutePath)
                list.add(file.absolutePath.toUri())
            }
        }
        return Observable.fromArray(list.toList())
    }
}