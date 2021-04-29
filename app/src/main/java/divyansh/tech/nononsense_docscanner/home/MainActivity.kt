package divyansh.tech.nononsense_docscanner.home

import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.home.pdf.PdfFragment
import divyansh.tech.nononsense_docscanner.home.scan.ScanFragment
import divyansh.tech.nononsense_docscanner.home.settings.SettingsFragment
import divyansh.tech.nononsense_docscanner.utils.C.IMAGE_CAPTURE_CODE
import divyansh.tech.nononsense_docscanner.utils.C.REQUEST_CODE_CAMERA_PERM
import divyansh.tech.nononsense_docscanner.utils.C.REQUEST_CODE_READ_AND_WRITE_EXTERNAL_STORAGE
import divyansh.tech.nononsense_docscanner.utils.C.SELECT_IMAGE_CODE
import divyansh.tech.nononsense_docscanner.utils.FileUtils.openFileChooser
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var scanFragment: ScanFragment
    @Inject lateinit var pdfFragment: PdfFragment
    @Inject lateinit var settingsFragment: SettingsFragment

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

        if (EasyPermissions.hasPermissions(
                this,
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE
            )
        ) {
            replaceFragment(scanFragment)
            setupNavigation()
        }

        else EasyPermissions.requestPermissions(
            host = this,
            rationale = getString(R.string.permission_read_and_write_external_storage),
            requestCode = REQUEST_CODE_READ_AND_WRITE_EXTERNAL_STORAGE,
            perms = *arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
        )

    }

    private fun setupNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.scanFragment -> replaceFragment(scanFragment)
                R.id.pdfFragment -> replaceFragment(pdfFragment)
                R.id.settingsFragment -> replaceFragment(settingsFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainNavHost, fragment)
            commit()
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
}