package com.example.demoapprecycleview.model


import com.google.gson.annotations.SerializedName

data class HomeDataResponse(
    @SerializedName("CategoryData")
    val categoryData: CategoryData,
    @SerializedName("CorosalImages")
    val corosalImages: List<CorosalImage>,
    @SerializedName("CoursesData")
    val coursesData: List<CoursesData>,
    @SerializedName("Marquee")
    val marquee: Marquee,
    @SerializedName("TestimonialData")
    val testimonialData: TestimonialData
) {
    data class CategoryData(
        @SerializedName("CatItemData")
        val catItemData: List<CatItemData>,
        @SerializedName("CatLableTitle")
        val catLableTitle: String // Categories
    ) {
        data class CatItemData(
            @SerializedName("TermId")
            val termId: Int, // 54
            @SerializedName("TermLink")
            val termLink: String, // http://sterlite.demo.brainvire.com/course-category/web-development/
            @SerializedName("TermName")
            val termName: String // Web Development
        )
    }

    data class CorosalImage(
        @SerializedName("SlideImage")
        val slideImage: String, // http://sterlite.demo.brainvire.com/wp-content/uploads/2019/09/our-story-timer.jpg
        @SerializedName("SlideLink")
        val slideLink: String, // http://sterlite.demo.brainvire.com/
        @SerializedName("SlideTitle")
        val slideTitle: String // Creating Smarter Network Professionals 3
    )

    data class CoursesData(
        @SerializedName("CourseName")
        val courseName: String, // Latest Courses
        @SerializedName("CourseShowAll")
        val courseShowAll: String, // latest
        @SerializedName("CoursesInfo")
        val coursesInfo: List<CoursesInfo>? = null
    ) {
        data class CoursesInfo(
            @SerializedName("CourseAuthorName")
            val courseAuthorName: String, // sterlite
            @SerializedName("CourseCategory")
            val courseCategory: String, // Web Development
            @SerializedName("CourseFree")
            val courseFree: String, // Free
            @SerializedName("CourseId")
            val courseId: String, // 123
            @SerializedName("CourseImage")
            val courseImage: String, // http://sterlite.demo.brainvire.com/wp-content/uploads/2017/06/technology-365x405.jpg
            @SerializedName("CourseLink")
            val courseLink: String, // http://sterlite.demo.brainvire.com/courses/complete-java-masterclass/
            @SerializedName("CourseRating")
            val courseRating: String,
            @SerializedName("CourseRatingCount")
            val courseRatingCount: String,
            @SerializedName("CourseRegularPrice")
            val courseRegularPrice: String, // 200
            @SerializedName("CourseReviewCount")
            val courseReviewCount: String, // 0
            @SerializedName("CourseSalePrice")
            val courseSalePrice: String,
            @SerializedName("CourseTitle")
            val courseTitle: String, // Complete Java Masterclass
            @SerializedName("EnrolledStudents")
            val enrolledStudents: String // 200
        )
    }

    data class Marquee(
        @SerializedName("Id")
        val id: String, // 486
        @SerializedName("Text")
        val text: String, // Testing course data
        @SerializedName("Type")
        val type: String // Course
    )

    data class TestimonialData(
        @SerializedName("TestimonialInfo")
        val testimonialInfo: List<TestimonialInfo>,
        @SerializedName("TestimonialTitle")
        val testimonialTitle: String // Testimonials
    ) {
        data class TestimonialInfo(
            @SerializedName("Description")
            val description: String, // You don't need a whole eCommerce system to sell your online courses. Paypal, Stripe payment methods integration can help you sell your use WooCommerce.
            @SerializedName("Image")
            val image: String, // http://sterlite.demo.brainvire.com/wp-content/uploads/2019/09/contact-testimonial.png
            @SerializedName("Name")
            val name: String // JENNY KATE, USA
        )
    }
}