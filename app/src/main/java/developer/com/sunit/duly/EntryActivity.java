package developer.com.sunit.duly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import butterknife.ButterKnife;

public class EntryActivity extends AppCompatActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.entry_activity);
        ButterKnife.bind(this);
        setContentView(R.layout.date_attendance);
        String[] class_list={"Student","Teacher"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,class_list);
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

                                startActivity(new Intent(EntryActivity.this,Student_login.class));
                                break;
                            case 1:
                                startActivity(new Intent(EntryActivity.this,Teacher_login.class));

                                break;

                        }
                    }
                })
                .setExpanded(false)// This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();


    }

    }

