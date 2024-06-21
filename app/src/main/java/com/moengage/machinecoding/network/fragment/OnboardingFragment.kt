package com.moengage.machinecoding.network.fragment

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.moengage.machinecoding.R
import com.moengage.machinecoding.network.ViewModel.OnboardingViewModel


class OnboardingFragment : Fragment() {

    private lateinit var viewModel: OnboardingViewModel
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var nextButton: Button

    companion object {
        fun newInstance() = OnboardingFragment()
    }
    private var listener: OnboardingFragmentListener? = null

    interface OnboardingFragmentListener {
        fun onNavigateToSummary()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnboardingFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnboardingFragmentListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        questionTextView = view.findViewById(R.id.questionTextView)
        answerEditText = view.findViewById(R.id.answerEditText)
        nextButton = view.findViewById(R.id.nextButton)

        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            question?.let {
                questionTextView.text = it.question
                when (it.inputType) {
                    "text" -> answerEditText.inputType = InputType.TYPE_CLASS_TEXT
                    "number" -> answerEditText.inputType = InputType.TYPE_CLASS_NUMBER
                    "date" -> answerEditText.inputType = InputType.TYPE_CLASS_DATETIME
                }
                    nextButton.text = if (viewModel.currentQuestionIndex.value == viewModel._questions.size - 1) "FINISH" else "NEXT"
            }
        }

        nextButton.setOnClickListener {
            val answer = answerEditText.text.toString()
            if (answer.isNotBlank()) {
                viewModel.nextQuestion(answer)
                answerEditText.text.clear()
                if (viewModel.currentQuestionIndex.value == -1) {
                    listener?.onNavigateToSummary()
                }
//                if (viewModel.currentQuestionIndex.value == -1) {
//                    // Navigate to summary screen
//                    findNavController().navigate(R.id.action_onboardingFragment_to_summaryFragment)
//                }
            } else {
                Toast.makeText(context, "Please enter an answer", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}