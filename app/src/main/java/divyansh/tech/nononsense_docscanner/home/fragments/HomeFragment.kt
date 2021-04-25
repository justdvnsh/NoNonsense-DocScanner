package divyansh.tech.nononsense_docscanner.home.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import divyansh.tech.nononsense_docscanner.databinding.FragmentHomeBinding
import divyansh.tech.nononsense_docscanner.utils.C.STRING_URI
import divyansh.tech.nononsense_docscanner.utils.FileUtils.openFileChooser
import kotlinx.android.synthetic.main.fragment_home.*

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
        if (!imageUri.isNullOrEmpty()) {
            binding.openImgTv.visibility = View.GONE
            binding.imageView.setImageURI(imageUri!!.toUri())
        } else {
            binding.openImgTv.setOnClickListener {
                openFileChooser(requireActivity())
            }
        }
        setupFab()
    }

    private fun setupFab() {
        binding.fabCamera.setOnClickListener {
//            openCamera
        }
    }
}