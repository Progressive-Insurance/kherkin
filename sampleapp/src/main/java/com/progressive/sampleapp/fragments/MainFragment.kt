package com.progressive.sampleapp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.FragmentMainBinding
import com.progressive.sampleapp.activities.compose.BasicComposeActivity
import com.progressive.sampleapp.activities.xml.ListActivity
import com.progressive.sampleapp.activities.xml.ViewPagerActivity
import com.progressive.sampleapp.viewmodels.Destinations
import com.progressive.sampleapp.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var destination: Class<out Activity>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()
        setupClickListeners()
        handleNavigation()
        fragmentTextUpdateObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main_view, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.toolbarItem1 -> {
                        Toast.makeText(activity, getString(R.string.button_1), Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.toolbarItem2 -> {
                        Toast.makeText(activity, getString(R.string.button_2), Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupClickListeners() {
        binding.button.setOnClickListener {
            viewModel.setUpdatedClicks()
        }
        binding.buttonClearEditText.setOnClickListener {
            viewModel.clearText()
        }
        binding.buttonNav.setOnClickListener {
            val destination = activity?.intent?.getSerializableExtra(MainViewModel.DESTINATION)
            viewModel.navigate(destination as Destinations?)
        }
        binding.buttonNavCompose.setOnClickListener {
            activity?.intent = Intent(activity, BasicComposeActivity::class.java)
            activity?.intent?.let { startActivity(it) }
        }
    }

    private fun handleNavigation() {
        viewModel.destinationLiveData.observe(viewLifecycleOwner) { dest ->

            if (dest == Destinations.List) {
                destination = ListActivity::class.java
            } else if (dest == Destinations.ViewPager) {
                destination = ViewPagerActivity::class.java
            }

            if (this@MainFragment::destination.isInitialized) {
                activity?.intent = Intent(activity, destination)
                activity?.intent?.let { startActivity(it) }
            }
        }
    }

    private fun fragmentTextUpdateObserver() {
        viewModel.uiBannerLiveData.observe(viewLifecycleOwner) { text ->
            binding.textView.text = text
        }
        viewModel.uiTextLiveData.observe(viewLifecycleOwner) {
            binding.editText.setText("")
        }
        viewModel.uiIntLiveData.observe(viewLifecycleOwner) { clicks ->
            binding.button.text = getString(R.string.button_main_click_counter, clicks + 1)
        }
        viewModel.uiAlertButtonTextLiveData.observe(viewLifecycleOwner) { text ->
            binding.buttonAlert.text = text
        }
        viewModel.datePickerTextLiveData.observe(viewLifecycleOwner) { date ->
            binding.textDatePicker.setText(date)
        }
        viewModel.dateSpinnerTextLiveData.observe(viewLifecycleOwner) { date ->
            binding.textDateSpinner.setText(date)
        }
        viewModel.timePickerTextLiveData.observe(viewLifecycleOwner) { time ->
            binding.textTimePicker.setText(time)
        }
    }
}