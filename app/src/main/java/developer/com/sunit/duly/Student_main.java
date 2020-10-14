package developer.com.sunit.duly;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import developer.com.sunit.duly.view.teacher.AddEvent;
import developer.com.sunit.duly.view.student.DashBoard;
import developer.com.sunit.duly.view.teacher.Me;
import developer.com.sunit.duly.view.teacher.Notice;

public class Student_main extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @BindView(R.id.main_container)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.dashboard:
                    fragment = new DashBoard();
                    switchFragment(fragment);
                    return true;
                case R.id.notice:
                    fragment = new Notice();
                    switchFragment(fragment);
                    return true;
                case R.id.event:
                    fragment = new AddEvent();
                    switchFragment(fragment);
                    return true;
                case R.id.me:
                    fragment = new Me();
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Log.e("error", "onCreate: ");
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);



    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Press once again to exit!",
                    Snackbar.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();

    }
}
