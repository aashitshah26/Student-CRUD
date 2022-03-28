package intr.intrprac.intrpractsk.ui.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import intr.intrprac.intrpractsk.data.db.entity.Student
import intr.intrprac.intrpractsk.databinding.ActivityMainBinding
import intr.intrprac.intrpractsk.ui.studentaddscreen.ActivityStudentAdd

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<ViewModelMainActivity>()
    private lateinit var adapter : AdapterStudent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)


        initialization()


    }

    private fun initialization() {
        initToolbar()
        initAdapter()
        listeners()
    }

    private fun initToolbar() {
        binding.toolbar.title = "Students"
        setSupportActionBar(binding.toolbar)

    }

    private fun listeners() {
     binding.addStu.setOnClickListener{
         val intent = Intent(this,ActivityStudentAdd::class.java)
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         startActivity(intent)
     }

      viewModel.getStateFlow().observe(this){
          when(it){
              is StudentListState.ListLiveData -> {
                  it.students.observe(this){ students ->
                      adapter.submitList(students)
                      viewModel.updateEmptyState(students.isNullOrEmpty())
                  }
              }
              is StudentListState.ShowError -> {
                  Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
              }
              is StudentListState.ShowToast -> {
                  Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
              }
          }

      }
    }

    private fun initAdapter() {
        adapter = AdapterStudent(this)
        binding.rvMoviesList.layoutManager = LinearLayoutManager(this)
        binding.rvMoviesList.adapter = adapter
        viewModel.fetchData()
    }
}