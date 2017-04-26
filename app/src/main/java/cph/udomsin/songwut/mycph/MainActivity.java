package cph.udomsin.songwut.mycph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Class cannot use _ # @ and not be use 123xxxxx
//extend MainActivity เพื่อให้ใช้หน้าจอได้
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit ประกาศตัวแปร ถ้าประกาศมากเกินไป Ram จะไม่พอ
    //[aceess](private) [type](EditText) [name](userEdittext) name ให้พิมพ์ชื่อแล้วกด ctrl + spaceเพื่อตั้งชื่อให้ถูกตามเงื่อนไข
    private EditText userEditText,passwordEditText; //กดปุ่ม shift + ctrl + enter เพื่อจบประโยค จะเติมตัวปิดให้อัตโนมัติ
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Statement
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // สร้าง Method (Function)
        //Initial View (ผูกตัวแปร)
        //ขึ้นด้นจ้วยตัวเล็ก
        initialView(); // กด alt + enter ในวงเล็บ เพิ่อสร้างอัตโนมัติ

        //Controller ทำให้ปุ่มมันทำงานได้
        controller(); // ตัวแปรต้องตั้งชื่อเป็นตัวเล็ก

    }//Main Method

    //void เป็นคำสั่งที่ไม่ส่งค่ากลับ
    private void controller() {
        textView.setOnClickListener(MainActivity.this); // alt + enter ที่ MainActivity เพื่อสร้าง Implement เพื่อให้ setOnClickListener ใช้งานได้ใน MainActivity
        button.setOnClickListener(MainActivity.this);
    }

    //Initial View (ผูกตัวแปร จากที่สร้างใน activity_main)
    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtuser); //ต้องเลือกชื่อ R ให้ตรงกับชื่อของตัวโปรแกรม , id
        passwordEditText = (EditText) findViewById(R.id.edtPassword); //กด alt + enter หลัง edtPassword เพิ่อสร้าง Cast (EditText)
        textView = (TextView) findViewById(R.id.txtNewRegis);
        button = (Button) findViewById(R.id.btnLogin);
    }


    @Override
    public void onClick(View v) {

        //For TextView
        //shift + ctrl + enter ในวงเล็บ เพื่อให้สร้างตัวจบประโยค,ถ้า v = textView (จิ้มที่ text View)
        if (v == textView) {
            //Intent to SignUp ย้ายไปหน้า SignUp
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class); //ย้ายจาก MainActivity ไป SignUp
            startActivity(intent); //เปิดหน้า SignUp มาแทนที่
        }

        if (v == button) {

        }
   }
}   //Main Class
