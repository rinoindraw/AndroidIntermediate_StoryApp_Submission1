package com.rinoindraw.storybismillah.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.rinoindraw.storybismillah.ui.main.MainActivity
import com.rinoindraw.storybismillah.R
import com.rinoindraw.storybismillah.ui.login.LoginActivity
import com.rinoindraw.storybismillah.utils.SessionManager
import com.rinoindraw.storybismillah.utils.UiConstValue

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var pref: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_splash)

        pref = SessionManager(this)
        val isLogin = pref.isLogin
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                isLogin -> {
                    val imageLogo = findViewById<ImageView>(R.id.imgLogo)
                    imageLogo.alpha = 1f
                    imageLogo.animate().setDuration(1000).alpha(0f).withEndAction {
                        val intentSplash = Intent(this, MainActivity::class.java)
                        startActivity(intentSplash)
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                        finish()
                    }
                }
                else -> {
                    val imageLogo = findViewById<ImageView>(R.id.imgLogo)
                    imageLogo.alpha = 1f
                    imageLogo.animate().setDuration(1000).alpha(0f).withEndAction {
                        val intentSplash = Intent(this, LoginActivity::class.java)
                        startActivity(intentSplash)
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                        finish()
                    }
                }
            }
        }, UiConstValue.LOADING_TIME)
    }
}