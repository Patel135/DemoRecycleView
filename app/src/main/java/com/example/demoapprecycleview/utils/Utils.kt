package com.commonlibrary.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatTextView
import com.example.demoapprecycleview.R
import com.example.demoapprecycleview.utils.DebugLog

@SuppressLint("NewApi")
object Utils {
    private var dialog: Dialog? = null

    /**
     * Show ProgressBar
     *
     * @param mContext Context
     * @param message  as String which will display with progressbar
     */
    fun showProgressBar(mContext: Context, message: String) {

        dialog = Dialog(mContext/*, android.R.style.Theme_Translucent*/)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog!!.window!!.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        dialog!!.setCancelable(false)
        dialog!!.setCanceledOnTouchOutside(false)
        val inflater = mContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        @SuppressLint("InflateParams") val viewChild: View
        viewChild = inflater.inflate(R.layout.loader_with_message, null)
        val txtMessage = viewChild.findViewById<AppCompatTextView>(R.id.txtMessage)
        txtMessage.text = message


        dialog!!.setContentView(viewChild)

        //        Runtime.getRuntime().gc();
        //        System.gc();

        try {
            dialog!!.show()
        } catch (e: Exception) {
            DebugLog.print(e)
        }

    }

    /**
     * Hide Progress bar
     */
    fun hideProgressBar() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }
}
