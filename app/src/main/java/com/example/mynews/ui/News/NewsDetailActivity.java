package com.example.mynews.ui.News;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.mynews.Model_News;
import com.example.mynews.R;
import org.w3c.dom.Text;

public class NewsDetailActivity extends AppCompatActivity {
    private ImageView imageView;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        imageView=findViewById(R.id.img);
        title=findViewById(R.id.d_title);


        Intent intent=getIntent();

        String title=intent.getStringExtra("title");
        String desc=intent.getStringExtra("desc");
        String url=intent.getStringExtra("img");
        String auth=intent.getStringExtra("auth");
        String time=intent.getStringExtra("time");

        Glide.with(this).load(url).into(imageView);
        this.title.setText(title);


    }
}