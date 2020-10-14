package developer.com.sunit.duly.presenter;

;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import developer.com.sunit.duly.R;

import developer.com.sunit.duly.model.StudentPOJO;

public class AddStudent extends AppCompatActivity {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.roll)
    EditText roll;
    @BindView(R.id.classB)
    EditText classB;
    @BindView(R.id.add)
    FloatingActionButton addStudent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Add Student");







        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")){name.setError("OOPS!");}
                else if (roll.getText().toString().equals("")){roll.setError("OOPS!");}
                else if (classB.getText().toString().equals("")){classB.setError("OOPS!");}
                else
                {

                    String nameText=name.getText().toString();
                    String rollText=roll.getText().toString();
                    String classText=classB.getText().toString();
                    FirebaseDatabase.getInstance()
                            .getReference("Class").child(classText)
                            .push()
                            .setValue(new StudentPOJO(nameText,
                                    rollText));
                    name.setText("");
                    roll.setText("");
                    classB.setText("");
                    Snackbar.make(findViewById(android.R.id.content), "Student added successfully",
                            Snackbar.LENGTH_SHORT).show();



                }
            }
        });

    }
}
