package divyansh.tech.nononsense_docscanner.home

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.replace
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.home.fragments.HomeFragment
import divyansh.tech.nononsense_docscanner.utils.C.REQUEST_CODE_READ_AND_WRITE_EXTERNAL_STORAGE
import divyansh.tech.nononsense_docscanner.utils.C.SELECT_IMAGE_CODE
import divyansh.tech.nononsense_docscanner.utils.FileUtils.openFileChooser
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment()
    }

    /*
    * Private function to check and handle permissions
    * */
    @AfterPermissionGranted(
        REQUEST_CODE_READ_AND_WRITE_EXTERNAL_STORAGE
    )
    private fun setupFragment() {
        // Check if the permissions have been denied permanently
//        if (EasyPermissions.somePermissionPermanentlyDenied(
//                host = this,
//                deniedPerms = *arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
//            )
//        ) SettingsDialog.Builder(this).build().show()

        if (EasyPermissions.hasPermissions(
                this,
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE
            )
        ) {
            // setup the fragment here
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainNavHost, homeFragment)
                commit()
            }
            setupOpenButton()
        } else {
            EasyPermissions.requestPermissions(
                host = this,
                rationale = getString(R.string.permission_read_and_write_external_storage),
                requestCode = REQUEST_CODE_READ_AND_WRITE_EXTERNAL_STORAGE,
                perms = *arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
            )
        }
    }

    private fun setupOpenButton() {
        openImg.setOnClickListener {
            openFileChooser(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(
            requestCode, permissions, grantResults, this
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE_CODE) {
                val imageURI = data?.data
                imageURI?.let {
                    Log.i("MAIN ACTIVITY", it.toString())
                    val frag = HomeFragment.newInstance(uri = it)
                    supportFragmentManager?.beginTransaction().apply {
                        replace(R.id.mainNavHost, frag)
                        commit()
                    }
                }
            }
        }
    }
}