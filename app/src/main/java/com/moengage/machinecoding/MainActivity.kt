package com.moengage.machinecoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moengage.machinecoding.network.fragment.OnboardingFragment
import com.moengage.machinecoding.network.fragment.SummaryFragment

class MainActivity : AppCompatActivity(), OnboardingFragment.OnboardingFragmentListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OnboardingFragment.newInstance())
                .commit()
        }
    }

    override fun onNavigateToSummary() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SummaryFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}