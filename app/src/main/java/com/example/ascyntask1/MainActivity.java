package com.example.ascyntask1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.TrustManager;

public class MainActivity extends AppCompatActivity {
    ArrayList<Demo> newsDatas=new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        new News().execute();
    }
    public class News extends AsyncTask<String,String,String>
    {
    String Title,Name,Author,UrlImage;

    HttpURLConnection httpURLConnection;
    String json="";
    JSONObject jsonObject;
    StringBuilder stringBuilder=new StringBuilder("");
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d( "onPostExecute: ",s);
        try
        {
            jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("articles");
            for (int i=0;i<jsonArray.length();i++) {
                Demo newsData = new Demo();
                newsData.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                newsData.setTitle(jsonArray.getJSONObject(i).getString("title"));

                newsData.setUrlToImage(jsonArray.getJSONObject(i).getString("urlToImage"));
                JSONObject jsonObject1=jsonArray.getJSONObject(i).getJSONObject("source");
                newsData.setName(jsonObject1.getString("name"));
                newsDatas.add(newsData);
                Log.d("author",newsData.getAuthor());
                Log.d("name",newsData.getName());
                Log.d("title",newsData.getTitle());
                Log.d("img",newsData.getUrlToImage());

            }
            for (int i=0;i<newsDatas.size();i++){
                Log.d("Values",newsDatas.get(i).getTitle());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        adapter=new Adapter(newsDatas,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected String doInBackground(String... strings) {
        try {

            URL url=new URL("https://newsapi.org/v2/top-headlines?country=in&apiKey=b4f0614a42bc491498fa9fc73943a173");
            httpURLConnection=(HttpURLConnection)url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data;
            while((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json=stringBuilder.toString();
       // Log.d("doInBackground: ",json);


        return json;
    }
}


}
