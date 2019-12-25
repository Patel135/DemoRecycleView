package com.example.demoapprecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chartshealthcare.network.model.ApiResponse
import com.commonlibrary.util.Utils
import com.example.demoapprecycleview.databinding.ActivityMainBinding
import com.example.demoapprecycleview.model.HomeDataRequest
import com.example.demoapprecycleview.model.HomeDataResponse
import com.example.demoapprecycleview.repository.HomeRepository
import com.example.demoapprecycleview.viewmodel.HomeViewModel
import com.example.demoapprecycleview.network.model.ResponseWrapper
import com.stlacademy.home.interfaces.OnCourseItemClickListener

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    private lateinit var viewModel: HomeViewModel
    private var coursesList: MutableList<HomeDataResponse.CoursesData> = arrayListOf()
    private var homeCourseAdapter = HomeCourseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        var homeRequest = HomeDataRequest("Android", "199", "1.0")
        HomeRepository.getFavoriteList(this, homeRequest, viewModel.responseLiveDataFavoriteList)


        viewModel.responseLiveDataFavoriteList.observe(
            this,
            Observer<ApiResponse>(function = fun(it: ApiResponse) {
                when {
                    !TextUtils.isEmpty(it.errorMessage) -> Toast.makeText(
                        this,
                        "ERROR",
                        Toast.LENGTH_LONG
                    ).show()
                    else -> {
                        val bean: ResponseWrapper<HomeDataResponse> =
                            it.data as ResponseWrapper<HomeDataResponse>
                        Utils.hideProgressBar()
                        val userData = bean.data

//Add Courses list
                        if (bean.data!!.coursesData.isNotEmpty()) {
                            coursesList.clear()
                            coursesList.addAll(userData!!.coursesData)
                            setCoursesData(coursesList)
                        }
                    }
                }
            })
        )
    }

    private fun setCoursesData(coursesList: MutableList<HomeDataResponse.CoursesData>) {
        homeCourseAdapter.setData(this, coursesList, object : OnCourseItemClickListener {
            override fun onViewAllClick(viewAll: String) {
                /*val bundle =
                    HomeFragmentDirections.actionHomeFragment2ToCourseListFragment(
                        "",
                        "",
                        IS_COME_FROM_HOME,
                        viewAll
                    )
                findNavController().navigate(bundle)*/
            }

            override fun onCourseItemClick(pos: Int, courseId: String) {
                /*val bundle =
                    HomeFragmentDirections.actionHomeFragment2ToCourseDetailFragment(courseId)
                findNavController().navigate(bundle)*/
            }

        })
        binding!!.rvHomeCoursesList.adapter = homeCourseAdapter
    }
}
