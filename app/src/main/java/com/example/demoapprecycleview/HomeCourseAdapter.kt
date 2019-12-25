package com.example.demoapprecycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapprecycleview.databinding.RowHomeCoursesBinding
import com.example.demoapprecycleview.model.HomeDataResponse
import com.stlacademy.home.interfaces.OnCourseItemClickListener


class HomeCourseAdapter : RecyclerView.Adapter<HomeCourseAdapter.ViewHolder>() {


    private lateinit var homeSubCourseAdapter: HomeSubCourseAdapter
    private lateinit var coursesList: MutableList<HomeDataResponse.CoursesData>
    private lateinit var onCourseItemClickListener: OnCourseItemClickListener
    private lateinit var context: Context
    private var orientation: Int? = 0

    fun setData(
        context: Context,
        coursesList: MutableList<HomeDataResponse.CoursesData>,
        onCourseItemClickListener: OnCourseItemClickListener
    ) {
        this.context = context
        this.coursesList = coursesList
        this.onCourseItemClickListener = onCourseItemClickListener
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RowHomeCoursesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowHomeCoursesBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        homeSubCourseAdapter = HomeSubCourseAdapter()
        coursesList[position].coursesInfo?.let {
            homeSubCourseAdapter.setData(
                it,
                onCourseItemClickListener
            )
            holder.binding.courseDataName = coursesList[position]

//            holder.binding.rvSubCourseList.addItemDecoration(MarginItemDecoration(20))
            holder.binding.rvSubCourseList.adapter = homeSubCourseAdapter

            holder.binding.txtLblCategoryTitle.setOnClickListener {
                onCourseItemClickListener.onViewAllClick(coursesList[position].courseShowAll)
                if (orientation == 0) {
                    orientation = 1
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    holder.binding.rvSubCourseList.layoutManager = layoutManager
                } else {
                    orientation = 0
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    holder.binding.rvSubCourseList.layoutManager = layoutManager
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}