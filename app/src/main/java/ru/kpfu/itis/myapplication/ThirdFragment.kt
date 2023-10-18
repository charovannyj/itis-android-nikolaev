package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding
import ru.kpfu.itis.myapplication.databinding.FragmentSecondBinding
import ru.kpfu.itis.myapplication.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {
    private var _binding: FragmentThirdBinding? = null
    private var texti: String? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding!!) {
            textView.text = arguments?.getString(ParamsKey.MESSAGE_TEXT_KEY)?.takeIf { it.isNotEmpty() } ?: getString(R.string.screen3)
        }
    }
    companion object {
        const val THIRD_FRAGMENT_TAG = "THIRD_FRAGMENT_TAG"
        fun getInstance() = ThirdFragment()
        fun getInstance(text: String?): ThirdFragment = ThirdFragment().apply {
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
        //_binding!!.textView.text = texti
    }
}