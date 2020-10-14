package developer.com.sunit.duly.presenter;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Switch;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import developer.com.sunit.duly.R;
import developer.com.sunit.duly.model.AttendanceListPOJO;

import developer.com.sunit.duly.model.StudentPOJO;


public class MarkAttendance extends AppCompatActivity{
    @BindView(R.id.date)
    EditText selectDate;
    @BindView(R.id.save)
    Button save;

    @BindView(R.id.classN)
            TextView className;
    ProgressDialog mProgressDialog;
    private int mYear, mMonth, mDay;
    DatabaseReference mref;
    RecyclerView mRecyclerView;
    Query query;
    String classNa;
    HashMap<String,Boolean> attendenceList;
    FirebaseRecyclerOptions<StudentPOJO> options;
    private FirebaseRecyclerAdapter<StudentPOJO, StudentHolder> fireBaseAdapter;
    Date date;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_attendance);
        ButterKnife.bind(this);
        final String standard=getIntent().getExtras().getString("standard");
        className.setText(standard);
        attendenceList=new HashMap<>();
        final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy hh-mm");
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MarkAttendance.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();







        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MarkAttendance.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });




        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView=new RecyclerView(this);
        mRecyclerView=findViewById(R.id.list);
        mRecyclerView.setLayoutManager(layoutManager);
        mref=FirebaseDatabase.getInstance().getReference().child("Class").child(standard);
        mref.keepSynced(true);

        query = mref
                .limitToLast(100);

        options = new FirebaseRecyclerOptions.Builder<StudentPOJO>()
                .setQuery(query, StudentPOJO.class)
                .setLifecycleOwner(this)
                .build();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Fetching data");



        fireBaseAdapter = new FirebaseRecyclerAdapter<StudentPOJO, StudentHolder>(options) {

                    @Override
                    protected void onBindViewHolder
                            (@NonNull StudentHolder viewHolder, int position, @NonNull StudentPOJO model) {

                        viewHolder.name.setText(model.getName());
                        viewHolder.roll.setText(model.getRoll());
                       final String roll=model.getRoll();
                        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked)
                                {
                                    attendenceList.put(roll,true);
                                }
                                if(!isChecked)
                                {
                                    if(attendenceList.containsKey(roll))
                                    {
                                        attendenceList.remove(roll);
                                    }
                                }
                            }
                        });

                    }

                    @Override
                    @NonNull
                    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        // Create a new instance of the ViewHolder, in this case we are using a custom
                        // layout called R.layout.message for each item
                        View view = LayoutInflater.from(getBaseContext())
                                .inflate(R.layout.attendance_card, parent, false);

                        return new StudentHolder(view);
                    }
                };
                fireBaseAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(fireBaseAdapter);





               mref
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    private DataSnapshot dataSnapshot;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        this.dataSnapshot = dataSnapshot;

                        if (dataSnapshot.exists()){


                            mProgressDialog.dismiss();
                        } else {

                            mProgressDialog.dismiss();
                            Snackbar.make(findViewById(android.R.id.content), "datasnapshot is null", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        mProgressDialog.dismiss();
                    }
                });
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 date=Calendar.getInstance().getTime();
                 final String timeStamp=dateFormat.format(date);
                 FirebaseDatabase.getInstance()
                         .getReference("attendance").child(standard)
                         .push().setValue(new AttendanceListPOJO(attendenceList,timeStamp));
                 Snackbar.make(findViewById(android.R.id.content), "Attendance Uploaded",
                         Snackbar.LENGTH_SHORT).show();
                 finish();


             }
         });


    }



    public static class StudentHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView name,roll;
        Switch aSwitch;
        public StudentHolder(View itemView)
        {
            super(itemView);
            view=itemView;

           this.name=view.findViewById(R.id.name);
           this.roll=view.findViewById(R.id.roll);
           this.aSwitch=view.findViewById(R.id.attend);

        }


        public void setaSwitch(Switch aSwitch) {
            this.aSwitch = aSwitch;
        }

        public Switch getaSwitch() {
            return aSwitch;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public void setRoll(TextView roll) {
            this.roll = roll;
        }

        public TextView getName() {
            return name;
        }

        public TextView getRoll() {
            return roll;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        fireBaseAdapter.startListening();
    }

}
