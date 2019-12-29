package com.example.myphoto;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private ImageView mImg5;
    int type;
    int num = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
    }

    private void initView() {
        mImg1 = (ImageView) findViewById(R.id.img_1);
        mImg2 = (ImageView) findViewById(R.id.img_2);
        mImg3 = (ImageView) findViewById(R.id.img_3);
        mImg4 = (ImageView) findViewById(R.id.img_4);
        mImg5 = (ImageView) findViewById(R.id.img_5);
/**
 * 点击监听
 * */
        mImg1.setOnClickListener(this);
        mImg2.setOnClickListener(this);
        mImg3.setOnClickListener(this);
        mImg4.setOnClickListener(this);
        mImg5.setOnClickListener(this);
/**
 * 长按监听
 * */
        mImg1.setOnLongClickListener(this);
        mImg2.setOnLongClickListener(this);
        mImg3.setOnLongClickListener(this);
        mImg4.setOnLongClickListener(this);
        mImg5.setOnLongClickListener(this);
    }

    private void initPermission() {
        String[] mPermission = {Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET};
        //先判断版本号是否在23（6.0）以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的权限是否设置
            for (int i = 0; i < mPermission.length; i++) {
                int permission = ContextCompat.checkSelfPermission(this, mPermission[i]);
                if (permission != PackageManager.PERMISSION_GRANTED) {//没有授权
                    //请求授权
                    ActivityCompat.requestPermissions(this, mPermission, 1);
                } else {
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//授权
                Toast.makeText(this, "同意授权", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "不同意授权", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            //Bitmap data1 = data.getParcelableExtra("data");
            Uri uri = data.getData();
            Log.e("TYPE", "data:" + data + "--URI:" + uri);
            /*try {*/
                /*assert uri != null;
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                File file = compressImage(bitmap);*/
            Log.e("TYPE", "type:" + type);
            if (type == 1) {
                mImg1.setImageURI(uri);
                mImg2.setVisibility(View.VISIBLE);
            }
            if (type == 2) {
                mImg2.setImageURI(uri);
                mImg3.setVisibility(View.VISIBLE);
            }
            if (type == 3) {
                mImg3.setImageURI(uri);
                mImg4.setVisibility(View.VISIBLE);
            }
            if (type == 4) {
                mImg4.setImageURI(uri);
                mImg5.setVisibility(View.VISIBLE);
            }
            if (type == 5) {
                mImg5.setImageURI(uri);
            }

           /* } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
        }
    }

    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        Log.i("file", file + "");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                Log.e("TAG", e.getMessage());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", e.getMessage());
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }

    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent1;
        switch (v.getId()) {
            case R.id.img_1:
                initPermission();
                intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 1);
                type = 1;
                break;
            case R.id.img_2:
                initPermission();
                intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 1);
                type = 2;
                break;
            case R.id.img_3:
                initPermission();
                intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 1);
                type = 3;
                break;
            case R.id.img_4:
                initPermission();
                intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 1);
                type = 4;
                break;
            case R.id.img_5:
                initPermission();
                intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 1);
                type = 5;
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.img_1:
                type = 1;
                initDilog();

                break;
            case R.id.img_2:
                type = 2;
                initDilog();
                break;
            case R.id.img_3:
                type = 3;
                initDilog();
                break;
            case R.id.img_4:
                type = 4;
                initDilog();
                break;
            case R.id.img_5:
                type = 5;
                initDilog();
                break;
        }
        return true;
    }

    private void initDilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("是否删除")
                .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("TYPE","num:"+num);
                        if (type == 1) {
                            num = num - 1;
                            mImg1.setImageURI(null);
                            mImg1.setVisibility(View.GONE);
                            if (num == 1) {
                                mImg1.setVisibility(View.VISIBLE);
                                mImg1.setImageResource(R.drawable.ic_launcher_background);
                            }
                        }
                        if (type == 2) {
                            num = num - 1;
                            mImg2.setImageURI(null);
                            mImg2.setVisibility(View.GONE);
                            if (num == 1) {
                                mImg1.setVisibility(View.VISIBLE);
                                mImg1.setImageResource(R.drawable.ic_launcher_background);

                            }
                        }
                        if (type == 3) {
                            num = num - 1;
                            mImg3.setImageURI(null);
                            mImg3.setVisibility(View.GONE);
                            if (num == 1) {
                                mImg1.setVisibility(View.VISIBLE);
                                mImg1.setImageResource(R.drawable.ic_launcher_background);

                            }
                        }
                        if (type == 4) {
                            num = num - 1;
                            mImg4.setImageURI(null);
                            mImg4.setVisibility(View.GONE);
                            if (num == 1) {
                                mImg1.setVisibility(View.VISIBLE);
                                mImg1.setImageResource(R.drawable.ic_launcher_background);

                            }
                        }
                        if (type == 5) {
                            num = num - 1;
                            mImg5.setImageURI(null);
                            mImg5.setVisibility(View.GONE);
                            if (num == 1) {
                                mImg1.setVisibility(View.VISIBLE);
                                mImg1.setImageResource(R.drawable.ic_launcher_background);

                            }
                        }
                    }
                })
                .setPositiveButton("取消", null);
        builder.show();
    }
}
