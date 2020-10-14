package developer.com.sunit.duly;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class Duly extends Application {

        @Override
        public void onCreate()
        {
            super.onCreate();
            FirebaseApp.initializeApp(this);


        }
    }

