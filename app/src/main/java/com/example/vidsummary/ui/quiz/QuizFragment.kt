package com.example.vidsummary.ui.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vidsummary.R
import com.example.vidsummary.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<QuizFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQuizBinding.bind(view)

        binding.apply {
            quizTitle.text = args.title
            question1.text = args.quizResponse.question_1
            question2.text = args.quizResponse.question_2
            question3.text = args.quizResponse.question_3
            question4.text = args.quizResponse.question_4
            question5.text = args.quizResponse.question_5
            question6.text = args.quizResponse.question_6
            question7.text = args.quizResponse.question_7
            question8.text = args.quizResponse.question_8
            question9.text = args.quizResponse.question_9
            question10.text = args.quizResponse.question_10
            answer1.text = args.quizResponse.answer_1
            answer2.text = args.quizResponse.answer_2
            answer3.text = args.quizResponse.answer_3
            answer4.text = args.quizResponse.answer_4
            answer5.text = args.quizResponse.answer_5
            answer6.text = args.quizResponse.answer_6
            answer7.text = args.quizResponse.answer_7
            answer8.text = args.quizResponse.answer_8
            answer9.text = args.quizResponse.answer_9
            answer10.text = args.quizResponse.answer_10

            goBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}