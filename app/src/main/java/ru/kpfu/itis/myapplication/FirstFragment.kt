package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false).also {
            FragmentFirstBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.button?.setOnClickListener {
            val text = _binding?.editText?.text.toString()
            _binding!!.button.setOnClickListener{
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ThirdFragment.getInstance(text), ThirdFragment.THIRD_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, SecondFragment.getInstance(text), SecondFragment.SECOND_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()
        fun getInstance(text: String?): FirstFragment = FirstFragment().apply {
            arguments = bundleOf(ParamsKey.MESSAGE_TEXT_KEY to text)
        }
    }
}