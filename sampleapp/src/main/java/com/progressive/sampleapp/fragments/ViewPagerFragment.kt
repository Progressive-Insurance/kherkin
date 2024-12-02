package com.progressive.sampleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.FragmentViewPagerBinding
import com.progressive.kherkin.sampleapp.databinding.FragmentViewPagerItemBinding

private const val ARG_OBJECT = "object"
private const val TOTAL = 5

class ViewPagerFragment : Fragment() {

    companion object {
        const val IS_VERTICAL = "IS_VERTICAL"

        @JvmStatic
        fun newInstance(isVerticalOrientation: Boolean) = ViewPagerFragment().apply {
            arguments = Bundle().apply {
                putBoolean(IS_VERTICAL, isVerticalOrientation)
            }
        }
    }

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = viewPagerFragmentAdapter
        viewPager.setCurrentItem(2, false)
        val args = arguments
        if (args != null && args.getBoolean(IS_VERTICAL)) {
            viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        }

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = getString(R.string.tab_text, position + 1)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ViewPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = TOTAL

    override fun createFragment(position: Int): Fragment {
        val fragment = ItemFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}


class ItemFragment : Fragment() {

    private var _binding: FragmentViewPagerItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.textViewPagerItem.text = "${getInt(ARG_OBJECT)}"
            when (getInt(ARG_OBJECT)) {
                1 -> binding.textViewPagerItem.id = R.id.view_pager_item_1
                2 -> binding.textViewPagerItem.id = R.id.view_pager_item_2
                3 -> binding.textViewPagerItem.id = R.id.view_pager_item_3
                4 -> binding.textViewPagerItem.id = R.id.view_pager_item_4
                5 -> binding.textViewPagerItem.id = R.id.view_pager_item_5
            }
        }
    }
}