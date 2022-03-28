package intr.intrprac.intrpractsk.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_student")
data class Student(
                   @PrimaryKey(autoGenerate = true)
                   var sr_no: Int,
                   var stuName: String,
                   var stuImage: String,
                   var stuGender: Int)