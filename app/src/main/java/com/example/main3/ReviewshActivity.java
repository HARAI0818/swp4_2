package com.example.main3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ReviewshActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    ArrayList<MemberDTO> members = null;
    public String Review_hos = "";
    private LinearLayoutManager mLinearLayoutManager = null;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewsh);

        Intent intent = getIntent(); /*데이터 수신*/
        Review_hos = intent.getExtras().getString("Review_hos");
        new BackgroundTask().execute();

        FloatingActionButton fab = findViewById(R.id.btn_write);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                String Review_hos = ((search_hAct)search_hAct.context_main).Review_hos;
                intent.putExtra("Review_hos",Review_hos);
                startActivity(intent);
            }
        });

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;
        String sendMsg;


        @Override
        protected void onPreExecute() {
            target = "http://211.110.104.63/Reviewsh.php";
        }


        @Override
        protected String doInBackground(Void... params) {

            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader reader = null;
            StringBuffer stringBuffer = new StringBuffer();

            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.
                OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream());
                sendMsg = "Review_hos=" + Review_hos;
                osw.write(sendMsg);
                osw.flush();


                httpURLConnection.setConnectTimeout(10000);

                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    is = httpURLConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is));
                    while (true) {
                        String stringLine = reader.readLine();
                        if (stringLine == null) break;
                        stringBuffer.append(stringLine + "\n");
                    }

                }
                parsing(stringBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (isr != null) isr.close();
                    if (is != null) is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(String s) {
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
            mLinearLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            ArrayList<MemberDTO> membsers = members;
            ReviewAdapter adapter = new ReviewAdapter(members);
            mRecyclerView.setAdapter(adapter);
        }

    }


    public void parsing(String data) {

        members = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("response"));
            //arrayList 클리어
            members.clear();

            for (int i = 0; i < jsonArray.length(); i++) {

                MemberDTO member = new MemberDTO();

                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                member.setReview_num(jsonObject1.getString("Review_num"));
                member.setReview_score(jsonObject1.getString("Review_score"));
                member.setReview_time(jsonObject1.getString("Review_time"));
                member.setReview_title(jsonObject1.getString("Review_title"));
                member.setReview_contents(jsonObject1.getString("Review_contents"));
                member.setReview_user(jsonObject1.getString("Review_user"));
                member.setReview_hos(jsonObject1.getString("Review_hos"));
                members.add(member);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}