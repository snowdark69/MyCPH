package cph.udomsin.songwut.mycph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Class cannot use _ # @ and not be use 123xxxxx
public class MainActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร ถ้าประกาศมากเกินไป Ram จะไม่พอ
    //[aceess](private) [type](EditText) [name](userEdittext) name ให้พิมพ์ชื่อแล้วกด ctrl + spaceเพื่อตั้งชื่อให้ถูกตามเงื่อนไข
    private EditText userEditText,passwordEditText; //กดปุ่ม shift + ctrl + enter เพื่อจบประโยค จะเติมตัวปิดให้อัตโนมัติ
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Statement
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View (ผูกตัวแปร)
        //ขึ้นด้นจ้วยตัวเล็ก
        initialView(); // กด alt + enter ในวงเล็บ เพิ่อสร้างอัตโนมัติ

    }//Main Method

    //Initial View (ผูกตัวแปร จากที่สร้างใน activity_main)
    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtuser); //ต้องเลือกชื่อ R ให้ตรงกับชื่อของตัวโปรแกรม , id
        passwordEditText = (EditText) findViewById(R.id.edtPassword); //กด alt + enter หลัง edtPassword เพิ่อสร้าง Cast (EditText)
        textView = (TextView) findViewById(R.id.txtNewRegis);
        button = (Button) findViewById(R.id.btnLogin);

    }


}   //Main Class
