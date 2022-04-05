package com.demetrius.testevents.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.demetrius.testevents.databinding.FragmentItemDetailBinding
import com.demetrius.testevents.utilities.image.ImageUtils
import com.google.android.material.appbar.CollapsingToolbarLayout

class ItemDetailFragment : Fragment() {

    private var itemTitle: String? = null
    private var itemDescription: String? = null
    private var itemImageSource: String? = null

    private lateinit var itemDetailTextView: TextView
    private lateinit var eventImageView: ImageView

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_TITLE)) {
                itemTitle = it.getString(ARG_ITEM_TITLE)
            }
            if (it.containsKey(ARG_ITEM_DESCRIPTION)) {
                itemDescription = it.getString(ARG_ITEM_DESCRIPTION)
            }
            if (it.containsKey(ARG_ITEM_IMG_SRC)) {
                itemImageSource = it.getString(ARG_ITEM_IMG_SRC)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        eventImageView = binding.eventImage!!
        itemDetailTextView = binding.itemDetail

        updateContent()

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = itemTitle

        itemDescription?.let {
            itemDetailTextView.text = it
        }

        itemImageSource?.let {
            ImageUtils.loadImage(eventImageView, it)
        }
    }

    companion object {
        const val ARG_ITEM_TITLE = "item_title"
        const val ARG_ITEM_DESCRIPTION = "item_description"
        const val ARG_ITEM_IMG_SRC = "item_img_src"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}