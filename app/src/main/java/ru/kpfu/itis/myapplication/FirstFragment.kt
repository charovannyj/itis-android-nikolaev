package ru.kpfu.itis.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private lateinit var til_phone_number: TextInputLayout
    private lateinit var et_phone_number: TextInputEditText
    private lateinit var til_count_questions: TextInputLayout
    private lateinit var et_count_questions: TextInputEditText
    private lateinit var button: Button
    private var ready1: Boolean? = false
    private var ready2: Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneRegex ="^(\\+7|8)(\\(| |-|)9\\d{2}(\\)|)(-| |)\\d{3}(-| |)\\d{2}(-| |)\\d{2}\$".toRegex()
        til_phone_number = view.findViewById<TextInputLayout>(R.id.til_phone_number)
        et_phone_number = view.findViewById<TextInputEditText>(R.id.et_phone_number)
        button = view.findViewById<Button>(R.id.button)
        val validDrawable = ContextCompat.getDrawable(requireContext(),R.drawable.baseline_check_24)
        val invalidDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_mood_bad_24)
        checkReady()
        et_phone_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    til_phone_number.error = "Поле не может быть пустым"
                } else {
                    if (phoneRegex.matches(s.toString())) {
                        ready1 = true
                        til_phone_number.error=null
                    } else {
                        ready1 = false
                        til_phone_number.error = "Некорректный номер"
                    }
                }
                checkReady()
            }
        })
        til_count_questions = view.findViewById<TextInputLayout>(R.id.til_count_questions)
        et_count_questions = view.findViewById<TextInputEditText>(R.id.et_count_questions)

        et_count_questions.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                checkReady()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkReady()
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    ready2 = false
                    til_count_questions.error = "Поле не может быть пустым"
                } else {
                    if(s.toString().toInt() <1) {
                        ready2 = false
                        til_count_questions.error = "Значение должно быть больше 0"
                    }
                    else{
                        ready2 = true
                        til_count_questions.error=null
                    }
                }
                checkReady()
            }
        })
    }

    fun checkReady() {
        button.isEnabled = ready1 == true && ready2 == true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}