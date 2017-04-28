package cph.udomsin.songwut.mycph;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //ดึง Class มาใช้
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { // สร้าง Runnable จาก ctrl + space
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class)); // าส่งค่า ไปที่ MainActivity
            }
        }, 4000); //หน่วยเป็น มิลลิวินาที

    } //Method
} //Main Class
