package com.moengage.machinecoding.network.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.moengage.machinecoding.network.Repository.OnboardingRepository
import com.moengage.machinecoding.network.model.Answer
import com.moengage.machinecoding.network.model.Question
import com.moengage.machinecoding.network.model.QuestionAnswer

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = OnboardingRepository()
    public val _questions = repository.fetchQuestions()

    private val _currentQuestionIndex = MutableLiveData(0)
    private val _questionsWithAnswers = MutableLiveData<MutableList<QuestionAnswer>>(mutableListOf())

    val currentQuestion: LiveData<Question> = MutableLiveData(_questions[0])
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex
    val questionsWithAnswers: MutableLiveData<MutableList<QuestionAnswer>> = _questionsWithAnswers

    fun nextQuestion(answer: String) {
        _questionsWithAnswers.value?.add(
            QuestionAnswer(
                _questions[_currentQuestionIndex.value!!].question,
                answer
            )
        )
        if (_currentQuestionIndex.value!! < _questions.size - 1) {
            _currentQuestionIndex.value = _currentQuestionIndex.value?.plus(1)
            (currentQuestion as MutableLiveData).value = _questions[_currentQuestionIndex.value!!]
        } else {
            _currentQuestionIndex.value = -1
        }
    }
}