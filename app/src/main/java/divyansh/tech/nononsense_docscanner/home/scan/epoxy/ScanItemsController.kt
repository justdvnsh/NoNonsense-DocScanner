package divyansh.tech.nononsense_docscanner.home.scan.epoxy

import android.net.Uri
import com.airbnb.epoxy.TypedEpoxyController

class ScanItemsController: TypedEpoxyController<List<Uri>>() {
    override fun buildModels(data: List<Uri>) {
        for (uri in data) {
            ScanItemsModel_()
                .id(uri.toString())
                .imageUri(uri)
                .addTo(this)
        }
    }
}