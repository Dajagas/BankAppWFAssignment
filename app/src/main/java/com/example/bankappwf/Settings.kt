package com.example.bankappwf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankappwf.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private lateinit var binding:FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        val accountFragment = Accounts()

        binding.maskSwitch.setOnClickListener{
            val maskState = binding.maskSwitch.isChecked
            println("KESARI BATH $maskState")
            val bundle = Bundle()
            bundle.putBoolean("maskState",maskState)
            println("BUNDLE ANNA ${bundle.toString()}")
            accountFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout,accountFragment).addToBackStack(null).commit()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
        //return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}