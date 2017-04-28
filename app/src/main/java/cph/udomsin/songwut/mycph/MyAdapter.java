package cph.udomsin.songwut.mycph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by snowdark69 on 27/4/2560.
 */

//สร้าง ListView ด้วย adaptor (engine)

public class MyAdapter extends BaseAdapter{ //BaseAdapter คือ ตัวสร้าง apapter >>> สร้าง Implement โดย alt+ enter หน้า BaseAdapter

    private Context context;
    private String[] nameStrings, dateStrings, detailStrings; //สร้างตัวแปรเพื่อรอรับค่าจาก my_listview

    public MyAdapter(Context context,
                     String[] nameStrings,
                     String[] dataStrings,
                     String[] detailStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.dateStrings = dataStrings;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length; //ถ้าสร้างชื่อเข้าไปเท่าไหร่ ให้ส่งค่ากลับมาเท่านั้น
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //สร้าง layout เสมือน แล้วถึงเอามาประกอบกัน
        //class object
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //สร้าง Castto(LayoutInflater) ด้วย alt + enter
        View view = layoutInflater.inflate(R.layout.my_listview, parent, false); //parent มากจาก ตัวแปรที่ประกาศใน public

        TextView nameTextView = (TextView) view.findViewById(R.id.txtName); //สร้าง Castto(TextView) ด้วย alt + enter
        TextView dateTextView = (TextView) view.findViewById(R.id.txtDate);
        TextView detailTextView = (TextView) view.findViewById(R.id.txtDetail);

        nameTextView.setText(nameStrings[position]); //show nameString ที่รับค่ามา ค่าตำแหน่งที่ position ตามที่ประกาศตัวแปรไว้ใน public
        dateTextView.setText(dateStrings[position]);
        //detailTextView.setText(detailStrings[position]);
        detailTextView.setText(createDetailshow(detailStrings[position])); //เอาข้อมูลใส่เข้าไปแล้วถึงตัดเอาออกมา โดยสร้าง method cretaeDetailshow

        return view; //ส่งค่า view กลับมา
    }

    private String createDetailshow(String detailString) {

        String result = null;

        if (detailString.length() >= 30) { // ถ้า detailString มีคา่ มากกว่า = 30

            result = detailString.substring(0,30) + "..."; //ตัดคำเยอะๆให้เป็นจุด 3 จุด ถ้าเกิน 30 คำ

        } else {
            result = detailString;
        }

        return result;

    }
}//Main Class
