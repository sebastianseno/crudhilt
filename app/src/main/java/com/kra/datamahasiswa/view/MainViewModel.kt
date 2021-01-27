package com.kra.datamahasiswa.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kra.datamahasiswa.database.entity.StudentDb
import com.kra.datamahasiswa.repo.StudentRepo
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
   private val repo: StudentRepo
) : ViewModel() {

   val getStudents: LiveData<List<StudentDb>>
      get() = repo.getAllStudents()

   val studentNameQuery = MutableLiveData<String>()

   val getStudentsByName: LiveData<List<StudentDb>> =
      Transformations.switchMap(studentNameQuery, repo::searchStudentsByName)

   fun deleteStudents(
      nim: Long
   ) {
      viewModelScope.launch {
         repo.deleteStudent(nim)
      }
   }

   fun inputStudents(
      nim: Long,
      name: String
   ) {
      viewModelScope.launch {
         repo.saveStudent(nim, name)
      }
   }
}