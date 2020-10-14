package developer.com.sunit.duly.view.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import developer.com.sunit.duly.R;


public class DashBoard extends Fragment {
    Button notice,markAttendance,addStudent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.student_dashboard, parent, false);
        notice=rootView.findViewById(R.id.notice);
        markAttendance=rootView.findViewById(R.id.markAttendance);
        addStudent=rootView.findViewById(R.id.addStudent);


        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

