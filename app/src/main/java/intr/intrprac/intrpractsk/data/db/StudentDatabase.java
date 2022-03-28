package intr.intrprac.intrpractsk.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import intr.intrprac.intrpractsk.data.db.dao.StudentDao;
import intr.intrprac.intrpractsk.data.db.entity.Student;


@Database(entities = {
    Student.class}, version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
}
