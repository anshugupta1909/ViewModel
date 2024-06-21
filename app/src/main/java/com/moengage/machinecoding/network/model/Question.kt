package com.moengage.machinecoding.network.model

// Question.kt


// Answer.kt
data class Answer(
    val questionId: Int,
    val answer: String
)
data class Question(val question: String, val inputType: String)
data class QuestionAnswer(val question: String, val answer: String)