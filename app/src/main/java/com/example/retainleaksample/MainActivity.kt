package com.example.retainleaksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

private const val UGLY_FRAGMENT_TAG = "UGLY_FRAGMENT_TAG"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, UglyFragment(), UGLY_FRAGMENT_TAG).commit()
        }

        attachButton.setOnClickListener {
            val uglyFragment = supportFragmentManager.findFragmentByTag(UGLY_FRAGMENT_TAG) ?: return@setOnClickListener

            supportFragmentManager.beginTransaction().also {
                if (uglyFragment.isDetached) {
                    it.attach(uglyFragment)
                } else {
                    it.detach(uglyFragment)
                }
            }.commit()
        }
    }
}
