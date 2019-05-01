package com.example.ascyntask1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class Adapter extends RecyclerView.Adapter<Adapter.ProgramViewHolder> {
    List<Demo> newsData;
    Context context;

    public Adapter(List<Demo> newsData, Context context) {
        this.newsData = newsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter, viewGroup, false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder programViewHolder, int i) {
    Demo d=newsData.get(i);
        programViewHolder.txt2.setText(newsData.get(i).getAuthor());
        programViewHolder.txt3.setText(newsData.get(i).getName());
        programViewHolder.txt1.setText(newsData.get(i).getTitle());
        Picasso.get().load(newsData.get(i).getUrlToImage()).into(programViewHolder.img);
    }



    @Override
    public int getItemCount() {
        return newsData.size();
    }
    public class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2,txt3;
        ImageView img;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.newstitle);
            txt2=itemView.findViewById(R.id.newsname);
            txt3=itemView.findViewById(R.id.newsauthor);
            img=itemView.findViewById(R.id.imgurl);
        }
    }
}
