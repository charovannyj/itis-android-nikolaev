package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding
import ru.kpfu.itis.myapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private var texti: String? = null;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding!!) {
            textView.text = arguments?.getString(ParamsKey.MESSAGE_TEXT_KEY)?.takeIf { it.isNotEmpty() } ?: getString(R.string.screen2)
            buttonTo3.setOnClickListener {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.fragment_container,
                        ThirdFragment.getInstance(textView.text.toString()),
                        ThirdFragment.THIRD_FRAGMENT_TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }
            buttonTo1.setOnClickListener {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.fragment_container,
                        FirstFragment.getInstance(textView.text.toString()),
                        FirstFragment.FIRST_FRAGMENT_TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    companion object {
        const val SECOND_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        fun getInstance() = SecondFragment()
        fun getInstance(text: String?): SecondFragment = SecondFragment().apply {
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