package com.jues.mediemuxerlibrary.view;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jues.mediemuxerlibrary.R;
import com.jues.mediemuxerlibrary.utils.AnimatedGifEncoder;
import com.jues.zlibrary.base.ZBaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/27
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class ActivityPreview extends ZBaseActivity {
//
//    private static int mWidth;
//    private static int mHeight;
//    private SurfaceView mSurfaceView;
//    private ImageView mImageView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_surface_view);
//        initView();
//        initData();
//    }
//
//    private void initView() {
//        mSurfaceView = findViewById(R.id.surfaceView);
//        mImageView = findViewById(R.id.imageView2);
//        RxView.clicks(findViewById(R.id.iv_back)).subscribe(v -> finish());
//        TextView right = findViewById(R.id.tv_right);
//        right.setText("分享");
//        RxView.clicks(right).subscribe(v -> {
//            toast("分享，还未实现");
//        });
//    }
//
//    private void initData() {
//        String gifPath = getIntent().getStringExtra("path");
////        List<String> stringList = getIntent().getStringArrayListExtra("imageList");
////        SilkyAnimation mAnimation = new SilkyAnimation.Builder(mSurfaceView)
////                .setScaleType(SilkyAnimation.SCALE_TYPE_CENTER_CROP).build();
////        //mAnimation.start(stringList);
////        String path = createGif("AdFastFlash", stringList);
//
//        //Glide.with(mImageView).load(gifPath).into(mImageView);
//
//        //这是加载视频的方法
//        //String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
//        Glide.with(mContext).load(Uri.fromFile(new File(gifPath))).into(mImageView);
//    }
//
//    @Deprecated
//    private File createGif() throws IOException {
//        List<String> stringList = getIntent().getStringArrayListExtra("imageList");
//        //生成Gif图片
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
//                + "/data/" + this.getPackageName() + "/cache";
//        File pathFile = new File(path);
//        File gifFile = new File(pathFile, "AdFastFlash.gif");
//        if (!pathFile.exists())
//            pathFile.mkdirs();
//        if (!gifFile.exists()) gifFile.createNewFile();
//
//        OutputStream os;
//        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
//
//        os = new FileOutputStream(gifFile);
//        gifEncoder.start(os);  //注意顺序
//        for (Bitmap bitmap : getBMList(stringList)) {
//            gifEncoder.addFrame(bitmap);
//        }
//        gifEncoder.setDelay(500);
//        gifEncoder.setRepeat(0);
//        gifEncoder.finish();
//        return gifFile;
//    }
//
//    @Deprecated
//    private List<Bitmap> getBMList(List<String> pathList) {
//        AssetManager assetManager = mContext.getAssets();
//        BitmapFactory.Options mOptions = new BitmapFactory.Options();
//        List<Bitmap> bitmapList = new ArrayList<>();
//        for (String path : pathList) {
//            try {
//                Bitmap bitmap = BitmapFactory.decodeStream(assetManager.open(path), null, mOptions);
//                bitmapList.add(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return bitmapList;
//    }
}
