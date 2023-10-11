package ru.kpfu.itis.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import ru.kpfu.itis.myapplication.databinding.FragmentFirstBinding
import ru.kpfu.itis.myapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false).also {
            FragmentSecondBinding.bind(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.buttonTo3?.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container,
                    ThirdFragment.getInstance(_binding!!.textView.text.toString()),
                    ThirdFragment.THIRD_FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        }
        _binding?.buttonTo1?.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container,
                    FirstFragment.getInstance(_binding!!.textView.text.toString()),
                    FirstFragment.FIRST_FRAGMENT_TAG
                )
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        const val SECOND_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        fun getInstance() = SecondFragment()
        fun getInstance(text: String?): SecondFragment = SecondFragment().apply {
            arguments = bundleOf(ParamsKey.MESSAGE_TEXT_KEY to text)
        }
    }
}