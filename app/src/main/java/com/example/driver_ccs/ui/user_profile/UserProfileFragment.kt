package com.example.driver_ccs.ui.user_profile

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.driver_ccs.databinding.FragmentUserProfileBinding
import com.example.driver_ccs.extensions.viewBinding
import org.json.JSONObject

class UserProfileFragment : Fragment() {

    private val binding: FragmentUserProfileBinding by viewBinding()
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDefaultPicture("Gabriel Romao")
        observe()
    }

    private fun observe() {
        viewModel.defaultPicture.observe(viewLifecycleOwner) {
            loadSvgToImageView(it, binding.profilePic)
        }
    }

    private fun loadSvgToImageView(svgString: String, imageView: ImageView) {
        try {
            val jsonObject = JSONObject(svgString)
            val svgContent = jsonObject.getString("svg")

            val svg = SVG.getFromString(svgContent)

            imageView.setImageDrawable(PictureDrawable(svg.renderToPicture()))
        } catch (e: SVGParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}