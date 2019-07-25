package com.example.retainleaksample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class UglyFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ugly, container, false).also { view ->
            val viewModel = ViewModelProviders.of(this)[UglyViewModel::class.java]

            val textView = view as TextView

            // Тут утечка будет, если фрагмент задетачить. Чтобы после детача была отписка, надо использовать viewLifecycleOwner
            viewModel.uglyLiveData.observe(this, Observer {
                textView.text = it
            })
        }
    }
}