package com.bawei.yuekao1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bawei.yuekao1.base.BaseActivity;
import com.bawei.yuekao1.fragment.MyFragment;
import com.bawei.yuekao1.fragment.ShouyeFragment;
import com.bawei.yuekao1.fragment.WangluoFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ImageView image_title,image_picture;
    private RadioGroup radio_group;
    private ViewPager viewPager;
    private List<Fragment> list;
    private String file= Environment.getExternalStorageDirectory()+"/bb.png";


    @Override
    protected void initListener() {
        image_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        image_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertPath();
            }
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new ShouyeFragment());
        list.add(new WangluoFragment());
        list.add(new MyFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radio_group.check(radio_group.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        drawerLayout = findViewById(R.id.drawerlayout);
        image_title = findViewById(R.id.image_title);
        image_picture = findViewById(R.id.image_picture);
        radio_group = findViewById(R.id.radio_group);
        viewPager = findViewById(R.id.viewpager);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
    public void alertPath(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("上传工具");
        String itmes[] = {"相机","相册"};
        builder.setItems(itmes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        //打开相机
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(file)));
                        //响应码
                        startActivityForResult(intent,100);

                        break;
                    case  1:
                        //打开相册
                        Intent intent1 = new Intent(Intent.ACTION_PICK);
                        intent1.setType("image/*");
                        //响应码
                        startActivityForResult(intent1,200);
                        break;
                }
            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            //给剪切传值
            Uri uri = Uri.fromFile(new File(file));
            Crop(uri);
        }else  if(requestCode == 200){
            //给剪切传值
            Uri uri = data.getData();
            Crop(uri);
        }else if(requestCode == 300){
            Bitmap bitmap = data.getParcelableExtra("data");
            Glide.with(this).load(bitmap).apply(RequestOptions.centerInsideTransform()).apply(RequestOptions.circleCropTransform()).into(image_picture);
            Glide.with(this).load(bitmap).apply(RequestOptions.centerInsideTransform()).apply(RequestOptions.circleCropTransform()).into(image_title);
        }
    }

    public void  Crop(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop",true);
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",250);
        intent.putExtra("outputY",250);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,300);

    }
}
