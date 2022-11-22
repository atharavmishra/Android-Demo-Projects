package com.example.imagetovolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<ModelClass> userlist;
    public Adapter(List <ModelClass>userlist){this.userlist=userlist;}

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    int res1= userlist.get(position).getRes1();
    int res2= userlist.get(position).getRes2();
    holder.setdata(res1,res2);
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView DP;
        private ImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DP = itemView.findViewById(R.id.DP);
            DP = itemView.findViewById(R.id.image);

        }

        public void setdata(int res1, int res2) {
            DP.setImageResource(res1);
            DP.setImageResource(res2);
        }
    }
}
