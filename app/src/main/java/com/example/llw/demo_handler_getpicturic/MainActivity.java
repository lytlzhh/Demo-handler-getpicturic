package com.example.llw.demo_handler_getpicturic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fun();
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap bbt = (Bitmap) msg.obj;
                imageView.setImageBitmap(bbt);

            }
        };
    }

    public void fun() {
        //   final URL url = null;
       // final Bitmap bitmap = null;

        new Thread() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://file01.16sucai.com/d/file/2011/1028/20111028050309490.jpg");
                    URLConnection urll = url.openConnection();
                    InputStream inputstream = urll.getInputStream();
                   Bitmap bitmap = BitmapFactory.decodeStream(inputstream);

                    Message message = new Message();
                    message.obj = bitmap;
                    handler.sendMessage(message);

                    inputstream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


}
