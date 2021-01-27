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

@AndroidEntryPoint
class InputStudentFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by viewBinding(FragmentInputStudentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentInputStudentBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputStudentButton.setOnClickListener {
            dismiss()
            viewModel.inputStudents(
                binding.inputNim.text.toString().toLong(),
                binding.inputName.text.toString()
            )
        }

    }
}