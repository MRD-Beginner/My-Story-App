package com.bangkit.storyapp.ui.dashboard.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.storyapp.adapter.LoadingStateAdapter
import com.bangkit.storyapp.adapter.StoryListAdapter
import com.bangkit.storyapp.databinding.FragmentHomeBinding
import com.bangkit.storyapp.ui.StoryViewModelFactory
import com.bangkit.storyapp.ui.dashboard.HomeViewModel
import com.bangkit.storyapp.ui.dashboard.MapsActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        StoryViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvStory.layoutManager = layoutManager

        binding.mapsButton.setOnClickListener{
            startActivity(Intent(requireContext(), MapsActivity::class.java))
        }
        getData()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        val adapter = StoryListAdapter()
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.stories.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
            Log.d("MainActivity", "Data loaded: $it ")
        }
    }
}
