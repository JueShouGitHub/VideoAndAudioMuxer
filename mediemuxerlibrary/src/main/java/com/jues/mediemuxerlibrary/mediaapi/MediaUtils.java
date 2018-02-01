package com.jues.mediemuxerlibrary.mediaapi;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/31
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

class MediaUtils {
    static String VIDEO_TYPE = "video";
    static String AUDIO_TYPE = "audio";
    private static String TAG = "MediaUtils";

    /**
     * 获取媒体编码格式
     *
     * @param extractor
     * @param type
     * @param mediaPath
     * @return
     */
    public static MediaFormat getMediaFormat(MediaExtractor extractor, String type, String mediaPath) {
        if (extractor == null || mediaPath == null || mediaPath.equals("")) {
            return null;
        }
        try {
            MediaFormat format;
            extractor.setDataSource(mediaPath);
            for (int i = 0; i < extractor.getTrackCount(); i++) {
                format = extractor.getTrackFormat(i);
                if (format.getString(MediaFormat.KEY_MIME).startsWith(type)) {
                    extractor.selectTrack(i);
                    return format;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}
