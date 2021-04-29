package divyansh.tech.nononsense_docscanner.home.scan.callbacks

import android.net.Uri

interface ScannedItemClick {

    fun onClick(uri: Uri)
}