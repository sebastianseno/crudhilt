package com.kra.datamahasiswa.repo

import androidx.lifecycle.LiveData
import com.kra.datamahasiswa.database.KraDb
import com.kra.datamahasiswa.database.entity.StudentDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepo @Inject constructor(
    db: KraDb
) {
    private val studentDao = db.studentDao()

    fun getAllStudents(): LiveData<List<StudentDb>> {
        return studentDao.getAllStudents()
    }

    fun searchStudentsByName(name: String): LiveData<List<StudentDb>> {
        return studentDao.searchStudentsName(name)
    }

    suspend fun deleteStudent(
        nim: Long
    ) = withContext(Dispatchers.IO) {
        studentDao.deleteStudent(nim)
    }

    suspend fun saveStudent(
        nim: Long,
        name: String
    ) = withContext(Dispatchers.IO) {
        studentDao.insertStudentData(
            StudentDb(
                0, nim, name
            )
        )
    }
}