package divyansh.tech.nononsense_docscanner.home.scan.epoxy

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.home.scan.callbacks.ScannedItemClick

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_your_scans)
abstract class ScanItemsModel: EpoxyModelWithHolder<Holder>() {

    @EpoxyAttribute lateinit var imageUri: Uri
    @EpoxyAttribute lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.imageView.setImageURI(imageUri)
        holder.cardView.setOnClickListener(clickListener)
    }
}

class Holder: EpoxyHolder() {
    lateinit var imageView: ImageView
    lateinit var cardView: CardView

    override fun bindView(itemView: View) {
        imageView = itemView.findViewById(R.id.scanned_image_item_iv) as ImageView
        cardView = itemView.findViewById(R.id.cardView) as CardView
    }


}