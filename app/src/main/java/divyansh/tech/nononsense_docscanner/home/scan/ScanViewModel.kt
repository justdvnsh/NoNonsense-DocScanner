package divyansh.tech.nononsense_docscanner.home.scan

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import javax.inject.Inject

/*
* View model for Scan Fragment
* */
@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scanItemRepo: ScanItemRepo
): ViewModel() {

    //TODO: Add RxJava to fetch files in a directory. Background task.
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val _uris: MutableLiveData<List<Uri>> = MutableLiveData()
    val uris: LiveData<List<Uri>> = _uris

    /*
    * Get all the scanned docs from dir
    * @param externalMediaDirs -> Array<Files> -> Get the external media dir
    * @param filename: String -> Get the dir where the scanned docs are saved
    * */
    fun getAllScannedDocuments(
        externalMediaDir: Array<File>,
        filename: String
    ) = disposable.add(scanItemRepo.getScannedDocs(externalMediaDir, filename)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            _uris.value = it
        })

    /*
    * Clear of the disposable.
    * */
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}