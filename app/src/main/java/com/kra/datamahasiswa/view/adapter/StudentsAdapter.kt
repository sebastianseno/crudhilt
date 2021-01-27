package com.kra.datamahasiswa.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kra.datamahasiswa.database.entity.StudentDb
import com.kra.datamahasiswa.databinding.ItemMahasiswaBinding

class StudentsAdapter(
    private val onSwipe: (Long) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    var studentDb = mutableListOf<StudentDb>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun removeAt(position: Int) {
        onSwipe(studentDb[position].nim)
        studentDb.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return studentDb.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentDb[position])
    }

    inner class ViewHolder(private val containerView: ItemMahasiswaBinding) :
        RecyclerView.ViewHolder(containerView.root) {

        fun bind(data: StudentDb) {
            containerView.apply {
                nim.text = data.nim.toString()
                name.text = data.name
            }
        }
    }
}