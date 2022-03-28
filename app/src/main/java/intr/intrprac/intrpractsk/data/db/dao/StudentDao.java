package intr.intrprac.intrpractsk.data.db.dao;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import intr.intrprac.intrpractsk.data.db.entity.Student;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM table_student order by sr_no desc")
    LiveData<List<Student>> fetchAllStudents();

    @Insert
    void insertStudentData(Student studentData);


}
