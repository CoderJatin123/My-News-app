package com.example.mynews.ui.News;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.mynews.R;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class NewsDetailActivity extends AppCompatActivity {
    private ImageView imageView;

    private TextView title_view,time_view,desc_view,auth_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imageView=findViewById(R.id.img);
        title_view=findViewById(R.id.d_title);
        time_view=findViewById(R.id.d_time);
        auth_view=findViewById(R.id.d_auth);
        desc_view=findViewById(R.id.d_desc);
        Date ts = null;        Intent intent=getIntent();

        String title=intent.getStringExtra("title");
        String desc=intent.getStringExtra("desc");
        String url=intent.getStringExtra("img");
        String auth=intent.getStringExtra("auth");
        auth="Author : "+auth;
        String time=intent.getStringExtra("time");

        Glide.with(this).load(url).into(imageView);
        title_view.setText(title);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ts = Timestamp.from( Instant.parse(time) );
        }
        time_view.setText(String.valueOf(ts));
        desc=desc.replaceAll("\\s+$","");
        desc_view.setText(desc);


        auth_view.setText(auth);


    }
}