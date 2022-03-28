package intr.intrprac.intrpractsk.ui.studentaddscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import intr.intrprac.intrpractsk.databinding.ActivityStudentAddBinding
import android.widget.Toast

import android.R

import android.widget.RadioGroup
import androidx.activity.viewModels
import intr.intrprac.intrpractsk.utils.validateEmpty


class ActivityStudentAdd : AppCompatActivity() {

    private lateinit var binding : ActivityStudentAddBinding
    private val viewModel by viewModels<ViewModelStudentAdd>()
    private var gender : Int = -1
    private var validGender : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialization()


    }

    private fun initialization() {
        initToolbar()
        listeners()
        observeState()
    }

    private fun observeState(){
        viewModel.getStateFlow().observe(this){
            when(it){
                is AddStudentState.ShowToast -> {
                    Toast.makeText(this, it.data, Toast.LENGTH_SHORT).show()
                }
                is AddStudentState.StudentAddedSuccessfully -> {
                    Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is AddStudentState.StudentAddingFailed -> {
                    Toast.makeText(this, "Student adding failed: -> ${it.reason}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.title = "Add Student"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun listeners() {
        binding.submit.setOnClickListener {
            validateEntries()
        }

        val radioGroup = binding.radioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->

            gender = when(checkedId){
                binding.radioMale.id -> {
                    0
                }
                binding.radioFemale.id -> {
                    1
                }
                else -> {
                    -1
                }
            }

        }
    }

    private fun validateEntries() {
        if(binding.edName.validateEmpty().not()){
            Toast.makeText(this, "Name can not be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if(gender == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.addData("", binding.edName.text.toString(), gender)

    }
}