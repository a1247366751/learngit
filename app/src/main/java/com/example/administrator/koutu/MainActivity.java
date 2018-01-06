package com.example.administrator.koutu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请选择的你的开锁方式");
        String []string={"拍照","从相册中选择"};
        builder.setItems(string, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent openCamera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开系统照相机
                        startActivityForResult(openCamera,0);
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                Bundle bundle=data.getExtras();
                bitmap=(Bitmap)bundle.get("data");
                saveImageToGallery(this,bitmap);

        }
    }

    private void saveImageToGallery(MainActivity mainActivity, Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "image");
        if(!appDir.exists()){
            appDir.mkdirs();
        }
        String FileName=System.currentTimeMillis()+".jpg";
        File file=new File(appDir,FileName);

    }
}
