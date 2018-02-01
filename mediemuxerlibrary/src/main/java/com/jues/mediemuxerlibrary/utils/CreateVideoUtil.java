package com.jues.mediemuxerlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.jues.mediemuxerlibrary.callback.ZCreateListener;
import com.jues.mediemuxerlibrary.global.MediaConstant;
import com.jues.mediemuxerlibrary.mediaapi.AudioAndVideoCompounder;
import com.jues.zlibrary.api.BaseObserver;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;


/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/29
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class CreateVideoUtil {
    public static void createVideo(String saveMp4Name, List<String> imageList, Activity activity
            , String audioPath, ZCreateListener listener) throws FrameRecorder.Exception {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int mWidth = outMetrics.widthPixels;
        int mHeight = outMetrics.heightPixels;
        Observable.create((ObservableOnSubscribe<String>) e -> {
            String path = Video(saveMp4Name, imageList, mWidth, mHeight, audioPath);
            e.onNext(path);
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>(activity) {
                    @Override
                    protected void onExecute(String s) {
                        listener.onComplete(s);
                        onComplete();
                    }
                });
    }

    public static void createVideo(String saveMp4Name, List<String> imageList, Context context
            , String audioPath, ZCreateListener listener) throws FrameRecorder.Exception {
        createVideo(saveMp4Name, imageList, ((Activity) context), audioPath, listener);
    }

    private static String Video(String saveMp4Name, List<String> imageList, int width, int height, String audioPath) throws Exception {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(saveMp4Name, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
        recorder.setFormat("mp4");
        //设置帧速率 一般设置20，越大越快，越小越慢
        //1 表示一秒一帧
        recorder.setFrameRate(2);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.start();

        OpenCVFrameConverter.ToIplImage iplImage = new OpenCVFrameConverter.ToIplImage();
        for (String path : imageList) {
            // 非常吃内存！！
            opencv_core.IplImage image = cvLoadImage(path);
            //cvLoadImage(image); 非常吃内存！！
            recorder.record(iplImage.convert(image));
            // 释放内存
            cvReleaseImage(image);
        }
        recorder.stop();
        recorder.release();
        String resultPath = MediaConstant.getVideoPath();
        AudioAndVideoCompounder compounder = AudioAndVideoCompounder.createCompounder(saveMp4Name, audioPath, resultPath);
        if (compounder != null) compounder.start();
        File file = new File(saveMp4Name);
        if (file.exists()) file.delete();
        return resultPath;
        //return compoundAudioAndVideo2(saveMp4Name, audioPath, resultPath);
    }

    public static String createVideo(String saveMp4Name, List<String> imageList, int width, int height) throws Exception {
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(saveMp4Name, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
        recorder.setFormat("mp4");
        //设置帧速率 一般设置20，越大越快，越小越慢
        //1 表示一秒一帧
        recorder.setFrameRate(2);
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.start();

        OpenCVFrameConverter.ToIplImage iplImage = new OpenCVFrameConverter.ToIplImage();
        for (String path : imageList) {
            // 非常吃内存！！
            opencv_core.IplImage image = cvLoadImage(path);
            //cvLoadImage(image); 非常吃内存！！
            recorder.record(iplImage.convert(image));
            // 释放内存
            cvReleaseImage(image);
        }
        recorder.stop();
        recorder.release();
        return saveMp4Name;
    }
}
