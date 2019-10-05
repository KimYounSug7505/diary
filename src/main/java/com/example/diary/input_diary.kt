package com.example.diary

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.input_diary.*
import java.util.ArrayList
import android.widget.EditText
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import android.widget.Toast
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.content.ContentResolver
import android.content.Intent

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.FragmentActivity
import org.jetbrains.annotations.NotNull
import com.pedro.library.AutoPermissions
import kotlinx.android.synthetic.main.input_diary.*


class input_diary : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_diary)


        val image = findViewById<Button>(R.id.image)
        val search= findViewById<Button>(R.id.search)
        val inputText  = findViewById<EditText>(R.id.inputText)
        val imageView = findViewById<ImageView>(R.id.imageView)

        image.setOnClickListener {
            openGallery()
            AutoPermissions.Companion.loadAllPermissions(this, 101);
        }

}

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT // ACTION_GET_CONTENT - 데이터에서 하나의 콘텐트를 선택하여 반환

        startActivityForResult(intent, 101) // 등번호 101번
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data!!.data


                val resolver = contentResolver // 리졸버 획득

                try {
                    val instream = resolver.openInputStream(fileUri!!) // 저장소의 URL에 연결하여 데이터를 가져옴
                    val imgBitmap = BitmapFactory.decodeStream(instream)
                    imageView.setImageBitmap(imgBitmap)

                    instream!!.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    fun onDenied(requestCode: Int, permissions: Array<String>) {

    }

    fun onGranted(requestCode: Int, permissions: Array<String>) {
    }


}


