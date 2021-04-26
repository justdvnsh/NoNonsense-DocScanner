package divyansh.tech.nononsense_docscanner.home.fragments

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.databinding.FragmentHomeBinding
import divyansh.tech.nononsense_docscanner.home.camera.CameraActivity
import divyansh.tech.nononsense_docscanner.utils.C
import divyansh.tech.nononsense_docscanner.utils.C.REQUEST_CODE_CAMERA_PERM
import divyansh.tech.nononsense_docscanner.utils.C.STRING_URI
import divyansh.tech.nononsense_docscanner.utils.FileUtils.openFileChooser
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.Executors

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var imageUri: String? = null

    companion object {
        fun newInstance(uri: Uri): HomeFragment {
            val frag = HomeFragment()
            frag.arguments = Bundle().apply {
                putString(STRING_URI, uri.toString())
            }
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        imageUri = arguments?.getString(STRING_URI)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFab()
    }

    @AfterPermissionGranted(REQUEST_CODE_CAMERA_PERM)
    private fun setupFab() {
        binding.fabCamera.setOnClickListener {
            if (
                EasyPermissions.hasPermissions(
                    requireContext(),
                    perms = arrayOf(Manifest.permission.CAMERA)
                )
            ) startActivity(Intent(requireContext(), CameraActivity::class.java))

            else EasyPermissions.requestPermissions(
                host = this,
                rationale = getString(R.string.permission_camera),
                requestCode = C.REQUEST_CODE_CAMERA_PERM,
                perms = arrayOf(Manifest.permission.CAMERA)
            )
        }
    }

}