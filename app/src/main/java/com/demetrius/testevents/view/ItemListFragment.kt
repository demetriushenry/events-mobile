package com.demetrius.testevents.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.demetrius.testevents.R
import com.demetrius.testevents.databinding.FragmentItemListBinding
import com.demetrius.testevents.databinding.ItemListContentBinding
import com.demetrius.testevents.model.Event
import com.demetrius.testevents.repository.Result
import com.demetrius.testevents.view.viewmodel.EventViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EventViewModel by viewModel()

    private lateinit var placeholderItems: List<Event>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeholderItems = ArrayList()

        val recyclerView: RecyclerView = binding.itemList
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        val onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as Event

            val bundle = Bundle()
            bundle.putString(ItemDetailFragment.ARG_ITEM_TITLE, item.title)
            bundle.putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, item.description)
            bundle.putString(ItemDetailFragment.ARG_ITEM_IMG_SRC, item.image)

            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                itemView.findNavController().navigate(R.id.show_item_detail, bundle)
            }
        }

        setupRecyclerView(recyclerView, onClickListener)
    }

    private fun retrieveEvents(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener
    ) {
        viewModel.getEvents().observe(this) {
            it?.let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { events ->
                            placeholderItems = events
                            recyclerView.adapter = SimpleItemRecyclerViewAdapter(
                                events,
                                onClickListener
                            )
                        }
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this.activity,
                            getString(R.string.error_retrieve_events),
                            Toast.LENGTH_SHORT
                        ).show()
                        result.exception.message?.let { error ->
                            Log.d("ApiResponse Error", error)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener
    ) {

        retrieveEvents(recyclerView, onClickListener)
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<Event>,
        private val onClickListener: View.OnClickListener
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id.toString()
            holder.contentView.text = item.title

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val idView: TextView = binding.idText
            val contentView: TextView = binding.content
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}