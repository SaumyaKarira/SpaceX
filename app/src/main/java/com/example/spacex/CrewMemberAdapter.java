package com.example.spacex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CrewMemberAdapter extends RecyclerView.Adapter<CrewMemberAdapter.CrewMemberHolder> {

    private List<CrewMember> crews = new ArrayList<>();
    private Context context;


//    public CrewMemberAdapter(Activity activity) {
//        this.activity = activity;
//    }

    @NonNull
    @Override
    public CrewMemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CrewMemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewMemberHolder holder, int position) {
        CrewMember current = crews.get(position);
        holder.name.setText(current.getName());
        holder.id.setText(current.getId());
        holder.agency.setText(current.getAgency());
        holder.status.setText(current.getStatus());

//        Glide.with(context).load(current.getImg())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.img);
//        Picasso.with(context).load(String.valueOf(current.getImg())).into(ImageView);
//        Bitmap bmp = BitmapFactory.decodeByteArray(current.getImg(), 0, current.getImg().length);
//        holder.img.setImageBitmap(Bitmap.createScaledBitmap(bmp, 80, 80, false));

    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    public void setCrews(List<CrewMember> crews) {
        this.crews = crews;
        notifyDataSetChanged();
    }

    class CrewMemberHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView id;
        private TextView agency;
        private TextView status;

        public CrewMemberHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.uid);
            agency = itemView.findViewById(R.id.agency);
            status = itemView.findViewById(R.id.status);
            img = itemView.findViewById(R.id.image);
        }
    }

}
