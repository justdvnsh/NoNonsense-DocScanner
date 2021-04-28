package divyansh.tech.nononsense_docscanner.home.scan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.utils.C.STRING_URI
import kotlinx.android.synthetic.main.activity_scanned_item.*

@AndroidEntryPoint
class ScannedItemActivity: AppCompatActivity() {

    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned_item)
        uri = intent?.getStringExtra(STRING_URI).toString()
        setupImageView()
    }

    private fun setupImageView() {
        if (uri.isNullOrEmpty()) finish()
        else scanned_iv.setImageURI(uri!!.toUri())
    }
}