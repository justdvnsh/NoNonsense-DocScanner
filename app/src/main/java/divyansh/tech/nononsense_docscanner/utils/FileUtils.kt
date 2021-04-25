package divyansh.tech.nononsense_docscanner.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

object FileUtils {
    fun openFileChooser(activity: Activity) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityIfNeeded(
            Intent.createChooser(intent, "Select A Picture"),
            C.SELECT_IMAGE_CODE
        )
    }
}