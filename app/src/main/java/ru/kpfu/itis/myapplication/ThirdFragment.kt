package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding
import ru.kpfu.itis.myapplication.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false).also {
            FragmentThirdBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(_binding!!) {
            textView.text =
                (arguments?.getString(ParamsKey.MESSAGE_TEXT_KEY) ?: R.string.screen3).toString();
        }
    }
    companion object {
        const val THIRD_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        fun getInstance() = ThirdFragment()
        fun getInstance(text: String?): ThirdFragment = ThirdFragment().apply {
            arguments = bundleOf(ParamsKey.MESSAGE_TEXT_KEY to text)
        }
    }
}