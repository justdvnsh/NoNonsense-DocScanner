package divyansh.tech.nononsense_docscanner.home.scan

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.utils.C.STRING_URI
import kotlinx.android.synthetic.main.activity_scanned_item.*
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.io.File


@AndroidEntryPoint
class ScannedItemActivity: AppCompatActivity() {

    private var uri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned_item)
        uri = intent?.getStringExtra(STRING_URI).toString()
    }

    override fun onResume() {
        super.onResume()
        Log.d("SCANNEDITEM", "Inside on resume")
        if (!OpenCVLoader.initDebug()) {
            Log.d("SCANNEDITEM", "Internal OpenCV library not found. Using OpenCV Manager for initialization")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback)
        } else {
            Log.d("SCANNEDITEM", "OpenCV library found inside package. Using it!")
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }

    private fun setupImageView() {
        if (uri.isNullOrEmpty()) finish()
        else {
            Log.i("SCANNEDITEM", uri!!)
            val file = File(uri!!)

            val img = Imgcodecs.imread(file.absolutePath)
            Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGR)
            val bitmap = Bitmap.createBitmap(img.cols(), img.rows(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(img, bitmap)
            scanned_iv.setImageBitmap(bitmap)
        }
    }

    private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                SUCCESS -> {
                    Log.i("SCANNEDITEM", "OpenCV loaded successfully")
                    setupImageView()
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }
}