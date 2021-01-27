package com.kra.datamahasiswa.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kra.datamahasiswa.databinding.ActivityMainBinding
import com.kra.datamahasiswa.modules.BaseActivity
import com.kra.datamahasiswa.utils.SwipeToDeleteCallback
import com.kra.datamahasiswa.view.adapter.StudentsAdapter

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val studentsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        StudentsAdapter(::onSwipe)
    }

    private fun onSwipe(nim: Long) {
        viewModel.deleteStudents(nim)
    }

    private val searchTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.length < 2) {
                viewModel.studentNameQuery.value = "%%"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.studentNameQuery.value = "%%"

        val swipeHandler = object : SwipeToDeleteCallback(applicationContext) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                studentsAdapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recycler)
        binding.recycler.adapter = studentsAdapter

        binding.fabAddMahasiswa.setOnClickListener {
            InputStudentFragment().show(supportFragmentManager, "dialog")
        }

        binding.inputSearch.let {
            it.addTextChangedListener(searchTextWatcher)
            it.setOnEditorActionListener(OnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.studentNameQuery.value = "%${v.text}%"
                    return@OnEditorActionListener true
                }
                false
            })
        }

        observe(viewModel.getStudentsByName) {
            studentsAdapter.studentDb = it!!.toMutableList()
        }
    }
}