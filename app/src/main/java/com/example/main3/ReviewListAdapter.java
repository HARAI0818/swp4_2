package com.example.main3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

//리뷰를 리스트 뷰로 뿌리기위한 클래스
public class ReviewListAdapter extends BaseAdapter {

    Context mContext;
    List<MemberDTO> memberList;
    LayoutInflater inflater;

    public ReviewListAdapter(Context mContext, List<MemberDTO> memberList) {
        this.mContext = mContext;
        this.memberList = memberList;
        inflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=inflater.inflate(R.layout.reviews, parent, false);
        }

        TextView tv_reviewhos =(TextView)convertView.findViewById(R.id.tv_reviewhos);
        TextView tv_reviewtitle =(TextView)convertView.findViewById(R.id.tv_reviewtitle);
        TextView tv_reviewuser=(TextView)convertView.findViewById(R.id.tv_reviewuser);
        TextView tv_reviewtime =(TextView)convertView.findViewById(R.id.tv_reviewtime);
        TextView tv_reviewcontents =(TextView)convertView.findViewById(R.id.tv_reviewcontents);
        RatingBar tv_reviewscore =(RatingBar) convertView.findViewById(R.id.tv_score);



        tv_reviewhos.setText("병원 : " +memberList.get(position).getReview_hos());
        tv_reviewtitle.setText("제목  : " +memberList.get(position).getReview_title());
        tv_reviewuser.setText("글작성자 : "+ memberList.get(position).getReview_user());
        tv_reviewtime.setText("작성시간: " + memberList.get(position).getReview_time());
        tv_reviewscore.setRating(memberList.get(position).getReview_score());
        tv_reviewcontents.setText("내용 : " + memberList.get(position).getReview_contents());


        return convertView;
    }




}

