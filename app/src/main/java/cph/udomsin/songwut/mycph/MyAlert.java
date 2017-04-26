package cph.udomsin.songwut.mycph;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by snowdark69 on 26/4/2560.
 */

public class MyAlert {

    //[aceess](private) [type](Context) [name](context) name ให้พิมพ์ชื่อแล้วกด ctrl + spaceเพื่อตั้งชื่อให้ถูกตามเงื่อนไข
    //context คือตัวแปรตัวเชื่อมรับค่าต่างๆ
    private Context context; //alt + enter ที่ หน้า context เพื่อสร้าง constructor


    public MyAlert(Context context) {
        this.context = context;
    }

    //สร้าง arugument เพื่อรับค่า string 2 ตัว
    public void myDialog(String strTitle,String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); //ตั้งให้กดที่ ok เท่านั้น ไม่สามารถกดปุ่ม cancel (ย้อนกลับ) ที่หน้าจอได้
        builder.setIcon(R.mipmap.ic_name);
        builder.setTitle(strTitle); //กำหนด title = title
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { //หลัง new กด ctrl + space
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

} //main Class
