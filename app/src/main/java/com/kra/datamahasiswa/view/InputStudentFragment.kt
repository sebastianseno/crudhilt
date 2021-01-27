package com.kra.datamahasiswa.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kra.datamahasiswa.databinding.FragmentInputStudentBinding
import com.kra.datamahasiswa.modules.viewBinding
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class InputStudentFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by viewBinding(FragmentInputStudentBinding::bind)

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentInputStudentBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputStudentButton.setOnClickListener {
            viewModel.inputStudents(
                binding.inputNim.text.toString().toLong(),
                binding.inputName.text.toString()
            )
        }

    }
    companion object {
        fun newInstance(param1: String, param2: String) =
            InputStudentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}