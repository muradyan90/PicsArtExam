package com.aram.picsartexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.NavigationUI
import com.aram.picsartexam.databinding.ActivityMainBinding
import com.aram.picsartexam.databinding.FragmentItemsBinding
import org.koin.android.ext.android.bind

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

//    override fun onSupportNavigateUp(): Boolean {
//     //ToDo
//    }
}
