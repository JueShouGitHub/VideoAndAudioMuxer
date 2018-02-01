package com.jues.mediemuxerlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.jues.mediemuxerlibrary.callback.ZCreateListener;
import com.jues.zlibrary.api.BaseObserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Introduce:
 * <p>
 * Create By JueShou -- 2018/1/28.13:51
 * Project: AdFastFlash
 * Email: 152063943642@163.com
 */

public class CreateGifUtil {
    public static void createGif(String filename, List<String> paths, Activity activity, ZCreateListener listener) throws IOException {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int mWidth = outMetrics.widthPixels;
        int mHeight = outMetrics.heightPixels;
        Observable.create((ObservableOnSubscribe<String>) e -> {
            String path = gif(filename, paths, mWidth, mHeight);
            //TODO 不要忘了发射
            e.onNext(path);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(activity) {
                    @Override
                    protected void onExecute(String s) {
                        //TODO 生成GIF后
                        listener.onComplete(s);
                        onComplete();
                    }
                });
    }

    public static void createGif(String filename, List<String> paths, Context context, ZCreateListener listener) throws IOException {
        createGif(filename, paths, ((Activity) context), listener);
    }

    //生成GIF关键代码
    private static String gif(String filename, List<String> paths, int mWidth, int mHeight) throws IOException {
        //TODO 生成GIF关键代码
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        AnimatedGifEncoder localAnimatedGifEncoder = new AnimatedGifEncoder();
        localAnimatedGifEncoder.start(baos);//start
        localAnimatedGifEncoder.setRepeat(0);//设置生成gif的开始播放时间。0为立即开始播放
        localAnimatedGifEncoder.setDelay(500);
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeFile(paths.get(i));
                Bitmap resizeBm = ImageUtil.resizeImage(bitmap, mWidth, mHeight);
                localAnimatedGifEncoder.addFrame(bitmap);
                bitmap.recycle();
            }
        }
        localAnimatedGifEncoder.finish();//finish

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/AdFastFlash");
        if (!file.exists()) file.mkdir();
        String path = Environment.getExternalStorageDirectory().getPath() + "/AdFastFlash/" + filename + ".gif";
        FileOutputStream fos = new FileOutputStream(path);
        baos.writeTo(fos);
        baos.flush();
        fos.flush();
        baos.close();
        fos.close();
        return path;
    }
}
