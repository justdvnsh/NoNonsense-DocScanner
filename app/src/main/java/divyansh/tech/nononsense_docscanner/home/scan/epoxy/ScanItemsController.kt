package divyansh.tech.nononsense_docscanner.home.scan.epoxy

import android.net.Uri
import androidx.core.net.toUri
import com.airbnb.epoxy.TypedEpoxyController
import divyansh.tech.nononsense_docscanner.home.scan.callbacks.ScannedItemClick
import javax.inject.Inject

class ScanItemsController @Inject constructor(
        private val callbacks: ScannedItemClick
): TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>) {
        for (uri in data) {
            ScanItemsModel_()
                .id(uri)
                .imageUri(uri.toUri())
                .clickListener { view ->
                    callbacks.onClick(uri)
                }
                .addTo(this)
        }
    }
}