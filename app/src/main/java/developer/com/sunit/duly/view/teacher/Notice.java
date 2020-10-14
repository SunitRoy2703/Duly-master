package developer.com.sunit.duly.view.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import developer.com.sunit.duly.R;
import developer.com.sunit.duly.model.NoticePOJO;

public class Notice extends Fragment {
    DatabaseReference mDatabase;
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<NoticePOJO> options;
    private FirebaseRecyclerAdapter<NoticePOJO, NoticeHolder> fireBaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("notice");
        mDatabase.keepSynced(true);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View rootView=inflater.inflate(R.layout.notice_fragment, parent, false);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView=new RecyclerView(getContext());
        mRecyclerView=rootView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(layoutManager);
        Query query = mDatabase
                .limitToLast(50);
        options = new FirebaseRecyclerOptions.Builder<NoticePOJO>()
                .setQuery(query, NoticePOJO.class)
                .setLifecycleOwner(this)
                .build();
        fireBaseAdapter = new FirebaseRecyclerAdapter<NoticePOJO, NoticeHolder>(options) {

            @Override
            protected void onBindViewHolder
                    (@NonNull NoticeHolder viewHolder, int position,@NonNull NoticePOJO model) {

                viewHolder.dept.setText(model.getDept());
                viewHolder.title.setText(model.getTitle());
                viewHolder.body.setText(model.getBody());
                viewHolder.teacher.setText(model.getTeacher());
                viewHolder.time.setText(model.getTime());


            }

            @Override
            @NonNull
            public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.notice, parent, false);

                return new NoticeHolder(view);
            }




        };
        fireBaseAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(fireBaseAdapter);




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


    public static class NoticeHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView dept,title,body,teacher,time;

        public NoticeHolder(View itemView)
        {
            super(itemView);
            view=itemView;

            this.dept =  itemView.findViewById(R.id.dept);
            this.title =  itemView.findViewById(R.id.title);
            this.body = itemView.findViewById(R.id.body);
            this.teacher=itemView.findViewById(R.id.teacher);
            this.time=itemView.findViewById(R.id.timeStamp);
        }

        public void setDept(TextView dept) {
            this.dept = dept;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        public void setBody(TextView body) {
            this.body = body;
        }

        public void setTeacher(TextView teacher) {
            this.teacher = teacher;
        }

        public TextView getDept() {
            return dept;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getBody() {
            return body;
        }

        public TextView getTeacher() {
            return teacher;
        }

        public TextView getTime() {
            return time;
        }
    }
    @Override
    public void onStart()
    {
        super.onStart();
        fireBaseAdapter.startListening();
    }


}
