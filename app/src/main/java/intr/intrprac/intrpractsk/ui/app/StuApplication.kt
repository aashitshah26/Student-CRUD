package intr.intrprac.intrpractsk.ui.app

import android.app.Application
import intr.intrprac.intrpractsk.data.db.DBInitializer

class StuApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initialization()

    }

    private fun initialization() {
        initDatabase()
    }

    private fun initDatabase() {

        DBInitializer.initDb(this)

    }

}