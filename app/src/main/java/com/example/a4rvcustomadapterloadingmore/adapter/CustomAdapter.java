package com.example.a4rvcustomadapterloadingmore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a4rvcustomadapterloadingmore.R;
import com.example.a4rvcustomadapterloadingmore.listener.onButtomReachedListener;
import com.example.a4rvcustomadapterloadingmore.model.User;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM_HEADER = 0;
    private static final int TYPE_ITEM_YES_VIEW = 1;
    private static final int TYPE_ITEM_NOT_VIEW = 2;
    private static final int TYPE_ITEM_FOOTER = 3;

    private Context context;
    private List<User> users;
    private onButtomReachedListener listener;

    public CustomAdapter(Context context,List<User>users, onButtomReachedListener listener ){
        this.context = context;
        this.users = users;
        this.listener = listener;
    }

    public void setUsers(List<User> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) return TYPE_ITEM_HEADER;
        if (isFooter(position)) return TYPE_ITEM_FOOTER;
        User user = users.get(position);
        if (user.isAvailable()) return TYPE_ITEM_YES_VIEW;
        return TYPE_ITEM_NOT_VIEW;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType ==TYPE_ITEM_HEADER){
            View header = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_header,parent,false);
            return new CustomViewHeaderHolder(header);
        }

        else if (viewType ==TYPE_ITEM_YES_VIEW){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_yes_view,parent,false);
            return new CustomViewYesHolder(view);
        }
        else if (viewType == TYPE_ITEM_NOT_VIEW){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_not_view,parent,false);
            return new CustomViewNotHolder(view);
        }
        View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layaut_footer,parent,false);
        return new CustomViewNotHolder(footer);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position==users.size()-1){
            listener.onButtomReached(position);
        }
        if (isHeader(position)||isFooter(position))return;
        User user = users.get(position);
        if (holder instanceof CustomViewYesHolder){
            TextView firstName = ((CustomViewYesHolder) holder).firstName;
            TextView lastName =((CustomViewYesHolder)holder).lastName;

            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
        }
        if (holder instanceof CustomViewNotHolder){
            TextView firstName = ((CustomViewNotHolder)holder).firstName;
            TextView lastName = ((CustomViewNotHolder)holder).lastName;

            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
        }
    }




    @Override
    public int getItemCount() {
        return users.size();
    }



    public boolean isHeader(int position){return position ==0;}
    public boolean isFooter(int position){return  position==(users.size()-1);}

    public class CustomViewHeaderHolder extends RecyclerView.ViewHolder{
        View view;

        public CustomViewHeaderHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
    public class CustomViewYesHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView firstName,lastName;

        public CustomViewYesHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            firstName = view.findViewById(R.id.first_name);
            lastName = view.findViewById(R.id.last_name);
        }
    }
    public class CustomViewNotHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView firstName, lastName;

        public CustomViewNotHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            firstName = view.findViewById(R.id.first_name);
            lastName = view.findViewById(R.id.last_name);
        }
    }
    public class CustomViewFooterHolder extends RecyclerView.ViewHolder{
        public View view;

        public CustomViewFooterHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }


}
