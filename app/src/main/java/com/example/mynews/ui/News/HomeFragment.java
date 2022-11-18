package com.example.mynews.ui.News;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynews.Model_News;
import com.example.mynews.databinding.FragmentHomeBinding;
import com.google.android.material.chip.Chip;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {
    public ArrayList<Chip> chips;
    public static ArrayList<Model_News> newsArrayList=new ArrayList<>(100);
    private String category_name="all";
    public static JSONArray dataArrayList;

    private FragmentHomeBinding binding;
    public static HomeViewModel  homeViewModel;
    ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = binding.newsReview;


        Chip all=binding.chip5;
        Chip sports=binding.chip4;
        Chip science=binding.Science;
        Chip world=binding.world;
        Chip entertainment=binding.entertainment;
        Chip technology=binding.technology;
         progressBar=binding.progressbar;


        RevAdapter adapter=new RevAdapter(getContext());


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(!Objects.equals(category_name, "all")){
                     homeViewModel.updateCatagory("all");
                     new GetData().execute("all");
                     category_name="all";
                 }

            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(category_name!="science"){
                     homeViewModel.updateCatagory("science");
                     new GetData().execute("science");
                     category_name="science";
                 }

            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(category_name!="entertainment"){
                     homeViewModel.updateCatagory("entertainment");
                     new GetData().execute("entertainment");
                     category_name="entertainment";
                 }

            }
        });
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(category_name!="world"){
                     homeViewModel.updateCatagory("world");
                     new GetData().execute("world");
                     category_name="world";
                 }

            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(category_name!="technology"){
                     homeViewModel.updateCatagory("technology");
                     new GetData().execute("technology");
                     category_name="technology";
                 }

            }
        });

        category_name=homeViewModel.getCatagory().getValue();

        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        if(homeViewModel.getNewsList().getValue()==null)
        {

            homeViewModel.updateCatagory("all");
            new GetData().execute("all");


        }
        else
        {

            adapter.DatasetChanged(newsArrayList);
            recyclerView.setAdapter(adapter);
        }






        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Objects.equals(category_name, "sports")){
                    homeViewModel.updateCatagory("sports");
                    new GetData().execute("sports");
                    category_name="sports";
                }






            }
        });

        homeViewModel.getNewsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Model_News>>() {
            @Override
            public void onChanged(ArrayList<Model_News> newsArrayList) {
                Log.d("TEST","obsreved size"+newsArrayList.size());

                        adapter.DatasetChanged(newsArrayList);
                        recyclerView.setAdapter(adapter);
            }
        });


        return root;


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class GetData extends AsyncTask<String, ArrayList<Model_News>,ArrayList<Model_News> > {


        protected ArrayList<Model_News> doInBackground(String... urls) {
            ArrayList<Model_News> arrayList=new ArrayList<>();


//            homeViewModel.updateNewsArray(arrayList);

            RequestQueue requestQueue;
            requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    "https://inshorts-news.vercel.app/"+ urls[0],null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {

                        String ctg = response.getString("category");
                        int count=response.getInt("count-articles");

                        dataArrayList = response.getJSONArray("data");

//                    String a1= dataArrayList.getJSONObject(0).getString("title");

                        for(int i=0; i<count; i++){

                            Model_News m=new Model_News(dataArrayList.getJSONObject(i).getString("title"),dataArrayList.getJSONObject(i).getString("author"),dataArrayList.getJSONObject(i).getString("decription"),dataArrayList.getJSONObject(i).getString("time"),dataArrayList.getJSONObject(i).getString("images"),dataArrayList.getJSONObject(i).getString("inshorts-link"),dataArrayList.getJSONObject(i).getString("read-more"));
                            arrayList.add(m);

                        }

                        homeViewModel.updateNewsArray(arrayList);
                        progressBar.setVisibility(View.GONE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Not done", Toast.LENGTH_SHORT).show();
                    Log.d("TEST", "onErrorResponse: "+error);
                }
            });

            requestQueue.add(jsonObjectRequest);


            return arrayList;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }



        protected void onPostExecute(ArrayList<Model_News> result) {
          homeViewModel.updateNewsArray(result);



        };


    }




}