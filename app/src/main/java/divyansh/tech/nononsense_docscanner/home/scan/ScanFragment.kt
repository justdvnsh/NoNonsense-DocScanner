package divyansh.tech.nononsense_docscanner.home.scan

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.R
import divyansh.tech.nononsense_docscanner.databinding.FragmentScanBinding
import divyansh.tech.nononsense_docscanner.home.camera.CameraActivity
import divyansh.tech.nononsense_docscanner.home.scan.callbacks.ScannedItemClick
import divyansh.tech.nononsense_docscanner.home.scan.epoxy.ScanItemsController
import divyansh.tech.nononsense_docscanner.utils.C
import divyansh.tech.nononsense_docscanner.utils.C.STRING_URI
import javax.inject.Inject

@AndroidEntryPoint
class ScanFragment: Fragment(), ScannedItemClick {

    private lateinit var binding: FragmentScanBinding
    @Inject lateinit var controller: ScanItemsController
    private val viewModel: ScanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFab()
        setupRecyclerView()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllScannedDocuments(
                externalMediaDir = requireActivity().externalMediaDirs,
                filename = getString(R.string.app_name)
        )
    }

    /*
    * Helper method to setup the fab
    * */
    @AfterPermissionGranted(C.REQUEST_CODE_CAMERA_PERM)
    private fun setupFab() {
        binding.fabCamera.setOnClickListener {
            if (
                EasyPermissions.hasPermissions(
                    requireContext(),
                    perms = *arrayOf(Manifest.permission.CAMERA)
                )
            ) startActivity(
                Intent(requireActivity(), CameraActivity::class.java)
            )

            else EasyPermissions.requestPermissions(
                host = requireActivity(),
                rationale = getString(R.string.permission_camera),
                requestCode = C.REQUEST_CODE_CAMERA_PERM,
                perms = *arrayOf(Manifest.permission.CAMERA)
            )
        }
    }

    /*
    * Helper method to setup the Recycler View
    * */
    private fun setupRecyclerView() {
        binding.imageViewRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = controller.adapter
        }
    }

    /*Helper method to setup the observer*/
    private fun setupObservers() {
        viewModel.uris.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) controller.setData(it)
        })
    }

    // When a scanned Item is clicked
    override fun onClick(filePath: String) {
        val intent = Intent(requireContext(), ScannedItemActivity::class.java).apply {
            putExtra(STRING_URI, filePath)
        }
        startActivity(intent)
    }
}