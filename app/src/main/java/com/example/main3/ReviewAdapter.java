package com.example.main3;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{

    private ArrayList<MemberDTO> memberList;

    LayoutInflater inflater;


    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        protected  TextView tv_reviewtitle;
        protected  TextView tv_reviewuser;
        protected  TextView tv_reviewtime ;
        protected  TextView tv_reviewcontents ;
        protected  RatingBar tv_reviewscore;

        public ReviewViewHolder(View view) {
            super(view);
            tv_reviewtitle =(TextView)view.findViewById(R.id.tv_reviewtitle);
            tv_reviewuser=(TextView)view.findViewById(R.id.tv_reviewuser);
            tv_reviewtime =(TextView)view.findViewById(R.id.tv_reviewtime);
            tv_reviewcontents =(TextView)view.findViewById(R.id.tv_reviewcontents);
            tv_reviewscore =(RatingBar) view.findViewById(R.id.tv_score);
        }
    }

    public ReviewAdapter(ArrayList<MemberDTO> list) {
        this.memberList = list;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.reviews, viewGroup, false);

        ReviewViewHolder viewHolder = new ReviewViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder viewholder, int position) {

        viewholder.tv_reviewtitle.setText("제목  : " +memberList.get(position).getReview_title());
        viewholder.tv_reviewuser.setText("글작성자 : "+ memberList.get(position).getReview_user());
        viewholder.tv_reviewtime.setText("작성시간: " + memberList.get(position).getReview_time());
        viewholder.tv_reviewscore.setRating(memberList.get(position).getReview_score());
        viewholder.tv_reviewcontents.setText("내용 : " + memberList.get(position).getReview_contents());

    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }


}
