package developer.com.sunit.duly.presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import developer.com.sunit.duly.R;
import developer.com.sunit.duly.model.NoticePOJO;

public class AddNotice extends AppCompatActivity {

    @BindView(R.id.body)
    EditText body;
    @BindView(R.id.Ntitle)
    EditText title;
    @BindView(R.id.upload)
    FloatingActionButton upload;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotice_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Add Notice");
        FirebaseApp.initializeApp(getBaseContext());
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals("")){title.setError("OOPS!");}
                else if (body.getText().toString().equals("")){body.setError("OOPS!");}
                else
                {
                    progressDialog = new ProgressDialog(AddNotice.this);
                    progressDialog.setMessage("Please Wait, Uploading Notice.....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    String Ntitle=title.getText().toString();
                    String bodyText=body.getText().toString();
                    String date="2/11/18";
                    FirebaseDatabase.getInstance()
                            .getReference("notice")
                            .push()
                            .setValue(new NoticePOJO("Academic",
                                    title.getText().toString(),
                                    "Sunit(Compt Dept)",
                                    body.getText().toString()
                                    ,date));
                    progressDialog.hide();
                    Snackbar.make(findViewById(android.R.id.content), "Notice uploaded successfully",
                            Snackbar.LENGTH_SHORT).show();
                    title.setText("");
                    body.setText("");



                }
            }
        });
    }

    }

