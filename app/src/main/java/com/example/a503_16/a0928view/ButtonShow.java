package com.example.a503_16.a0928view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ButtonShow extends AppCompatActivity {
    //스레드를 annoymous class를 이용해서 만들것인데 annoymous class는 지역변수를 사용할 수 가 없다.
    ImageView imageView;
    Bitmap bitmap;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_show);


        imageView  = (ImageView) findViewById(R.id.imageview);
        //리소스에 포함된 이미지 가져오기
        //imgeView.setImageResource(R.drawable.kim);

        //웹의 이미지 출력하기
        url = "http://img.enews24.cjenm.skcdn.com/News/Contents/20171019/50748751.jpg";

        //이미지를 다운로드 받기 위한 스레드 생성
        Thread th = new Thread(){
            public void run(){
                try {
                    //웹에서 데이터를 가져올 수 있는 스트림을 생성
                    InputStream is = new URL(url).openStream();
                    //스트림의 데이터를 이미지로 변경
                    bitmap = BitmapFactory.decodeStream(is);
                }
                catch (Exception e) {}
            }
        };
        //스레드 시작
        th.start();

        //스레드의 수행이 동료되면 이미지를 설정
        try {
            th.join();
            imageView.setImageBitmap(bitmap);
        }
        catch(Exception e){}
    }
}
