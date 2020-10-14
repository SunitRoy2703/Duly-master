package developer.com.sunit.duly.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import developer.com.sunit.duly.R;

public class DateClass extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_attendance);
        String[] class_list={"7","8","9","10","11","12"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,class_list);
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setAdapter(adapter)
                .setCancelable(false)
                .setGravity(Gravity.CENTER_VERTICAL)
                .setOutAnimation(R.anim.slide_in_bottom)
                .setInAnimation(R.anim.fade_out_center)
                .setContentHolder(new ListHolder())
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position)
                    {
                        Intent intent;
                        switch (position) {
                            case 0:
                                 intent =new Intent(DateClass.this,MarkAttendance.class);
                                  intent.putExtra("standard","7");
                                  startActivity(intent);
                                  break;
                            case 1:
                                intent=new Intent(DateClass.this,MarkAttendance.class);
                                intent.putExtra("standard","8");
                                startActivity(intent);

                                break;
                            case 2:
                                intent =new Intent(DateClass.this,MarkAttendance.class);
                                intent.putExtra("standard","9");
                                startActivity(intent);
                                break;


                            case 3:
                                intent =new Intent(DateClass.this,MarkAttendance.class);
                                intent.putExtra("standard","10");
                                startActivity(intent);

                                break;
                            case 4:
                                intent =new Intent(DateClass.this,MarkAttendance.class);
                                intent.putExtra("standard","11");
                                startActivity(intent);


                                break;
                            case 5:

                                intent =new Intent(DateClass.this,MarkAttendance.class);
                                intent.putExtra("standard","12");
                                startActivity(intent);

                                break;

                        }
                    }
                })
                .setExpanded(false)// This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();


    }
}
