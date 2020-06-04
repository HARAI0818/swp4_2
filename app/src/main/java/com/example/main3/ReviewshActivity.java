package com.example.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewshActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewsh);

        listView=(ListView)findViewById(R.id.listView);

        Intent intent = getIntent();
        if(intent!=null) {

            Bundle membersBundle = intent.getBundleExtra("members");
            ArrayList<MemberDTO> membsers = (ArrayList<MemberDTO>) membersBundle.get("members");
            Context  mContext = ReviewshActivity.this;
            ReviewListAdapter adapter =new ReviewListAdapter(ReviewshActivity.this , membsers);
            listView.setAdapter(adapter);

        }


        Button write = (Button) findViewById(R.id.btn_write);
        write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                String Review_hos = ((search_hAct)search_hAct.context_main).Review_hos;
                intent.putExtra("Review_hos",Review_hos);
                startActivity(intent);
            }
        });
    }

}
