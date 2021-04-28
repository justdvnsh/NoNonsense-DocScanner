package divyansh.tech.nononsense_docscanner.home.scan.epoxy

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import divyansh.tech.nononsense_docscanner.R

@EpoxyModelClass(layout = R.layout.item_your_scans)
abstract class ScanItemsModel: EpoxyModelWithHolder<Holder>() {

    @EpoxyAttribute lateinit var imageUri: Uri

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.imageView.setImageURI(imageUri)
    }
}

class Holder: EpoxyHolder() {
    lateinit var imageView: ImageView
    override fun bindView(itemView: View) {
        imageView = itemView.findViewById(R.id.scanned_image_item_iv) as ImageView
    }


}