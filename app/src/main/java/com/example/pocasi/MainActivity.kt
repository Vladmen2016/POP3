package com.example.pocasi


import SearchFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pocasi.HomeFragment
import com.example.pocasi.R
import com.example.pocasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.visibility = View.GONE
        replaceFragment(SplashFragment())
        Handler(Looper.getMainLooper()).postDelayed({
            // Po 3000 milisekundách zobrazí spodní navigační panel
            binding.bottomNav.visibility = View.VISIBLE
            replaceFragment(SearchFragment())
        }, 3000)


        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.homeicon -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearchFragment())
                else -> {
                    // Co se má stát, když je vybrána jiná položka než home, profile nebo settings?
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }


}
