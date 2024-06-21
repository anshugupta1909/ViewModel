package com.moengage.machinecoding.network.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moengage.machinecoding.R
import com.moengage.machinecoding.network.adapter.SummaryAdapter
import com.moengage.machinecoding.network.ViewModel.OnboardingViewModel


class SummaryFragment: Fragment() {

    private lateinit var viewModel: OnboardingViewModel
    private lateinit var summaryRecyclerView: RecyclerView
    private lateinit var summaryAdapter: SummaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        summaryRecyclerView = view.findViewById(R.id.summaryRecyclerView)

        // Initialize RecyclerView and its adapter
        summaryRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.questionsWithAnswers.observe(viewLifecycleOwner, Observer { questionsWithAnswers ->
            summaryAdapter = SummaryAdapter(questionsWithAnswers)
            summaryRecyclerView.adapter = summaryAdapter
        })

        return view
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }
}
