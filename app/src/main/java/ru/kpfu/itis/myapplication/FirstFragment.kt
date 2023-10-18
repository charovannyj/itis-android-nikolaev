package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private var texti: String? = null;
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
        with(_binding!!){
            button.setOnClickListener{
                texti = editText.text.toString()
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, SecondFragment.getInstance(texti), SecondFragment.SECOND_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, ThirdFragment.getInstance(texti), ThirdFragment.THIRD_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()
        fun getInstance(text: String?): FirstFragment = FirstFragment().apply {
            arguments = bundleOf(ParamsKey.MESSAGE_TEXT_KEY to text)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentTexti = texti
        outState.putString("savedStr", currentTexti)    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val currentString = savedInstanceState?.getString("savedStr") ?: texti
        texti = currentString
    }
}