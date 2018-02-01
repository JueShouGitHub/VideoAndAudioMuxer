package com.jues.mediemuxerlibrary.utils;

import android.app.Activity;
import android.content.Intent;

import cn.hzw.graffiti.GraffitiActivity;
import cn.hzw.graffiti.GraffitiParams;

/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/27
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class Graffiti {
    public static void startActivityForResult(Activity activity, String path, int requestCode) {
        // 涂鸦参数
        GraffitiParams params = new GraffitiParams();
        // 图片路径
        params.mImagePath = path;
        // 初始画笔大小
        params.mPaintSize = 20;
        // 启动涂鸦页面
        Intent intent = new Intent(activity, GraffitiActivity.class);
        intent.putExtra(GraffitiActivity.KEY_PARAMS, params);
        activity.startActivityForResult(intent, requestCode);
    }
}
