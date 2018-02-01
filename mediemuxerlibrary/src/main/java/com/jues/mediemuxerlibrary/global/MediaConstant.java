package com.jues.mediemuxerlibrary.global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/29
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class MediaConstant {
    private static final String MUSIC_1 = "audio/music_1.mp3";
    private static final String MUSIC_2 = "audio/music_2.mp3";
    private static final String MUSIC_3 = "audio/music_3.mp3";
    private static final String MUSIC_4 = "audio/music_4.mp3";
    private static final String MUSIC_5 = "audio/music_5.mp3";
    private static final String MUSIC_6 = "audio/music_6.mp3";
    private static final String MUSIC_7 = "audio/music_7.mp3";
    private static final String MUSIC_8 = "audio/music_8.mp3";
    private static final String MUSIC_9 = "audio/music_9.mp3";
    private static final String MUSIC_10 = "audio/music_10.mp3";
    private static final String MUSIC_11 = "audio/music_11.mp3";
    private static final String MUSIC_12 = "audio/music_12.mp3";
    private static final String MUSIC_13 = "audio/music_13.mp3";
    public static final String[] MusicList = {MUSIC_1, MUSIC_2, MUSIC_3, MUSIC_4, MUSIC_5, MUSIC_6, MUSIC_7, MUSIC_8, MUSIC_9, MUSIC_10, MUSIC_11, MUSIC_12, MUSIC_13,};

    public static String getVideoPath() {
        //使用Date
        Date d = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("当前时间：" + sdf.format(d));
        //命名格式，AdFastFlash + “_当前时间”
        return Environment.getExternalStorageDirectory().getPath()
                + "/AdFastFlash/" + "AdFastFlash_" + sdf.format(d) + ".mp4";
    }

    public static String getAssetsPath(int pos) {
        String music = "audio/music_1.mp3";
        if (pos <= 0) music = MusicList[0];
        else if (pos > 0 && pos < MusicList.length) music = MusicList[pos];
        else if (pos >= MusicList.length) music = MusicList[MusicList.length - 1];
        return Environment.getExternalStorageDirectory().getPath()
                + "/AdFastFlash/assets/" + music;
    }

    public static String createAssetsPath() {
        return Environment.getExternalStorageDirectory().getPath()
                + "/AdFastFlash/assets/";
    }

    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            Log.e("audioPath", Arrays.toString(fileNames));
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程
            //MainActivity.handler.sendEmptyMessage(COPY_FALSE);
        }
    }
}
