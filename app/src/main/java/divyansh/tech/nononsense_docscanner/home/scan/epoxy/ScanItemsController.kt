package divyansh.tech.nononsense_docscanner.home.scan.epoxy

import android.net.Uri
import com.airbnb.epoxy.TypedEpoxyController
import divyansh.tech.nononsense_docscanner.home.scan.callbacks.ScannedItemClick
import javax.inject.Inject

class ScanItemsController @Inject constructor(
        private val callbacks: ScannedItemClick
): TypedEpoxyController<List<Uri>>() {
    override fun buildModels(data: List<Uri>) {
        for (uri in data) {
            ScanItemsModel_()
                .id(uri.toString())
                .imageUri(uri)
                .clickListener { view ->
                    callbacks.onClick(uri)
                }
                .addTo(this)
        }
    }
}