package com.jues.mediemuxerlibrary.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jues.mediemuxerlibrary.R;
import com.jues.mediemuxerlibrary.global.MediaConstant;
import com.jues.mediemuxerlibrary.global.GridImageConfig;
import com.jues.mediemuxerlibrary.gridImage.GridImageAdapter;
import com.jues.mediemuxerlibrary.utils.CreateVideoUtil;
import com.jues.mediemuxerlibrary.utils.Graffiti;
import com.jues.zlibrary.base.ZBaseActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import cn.hzw.graffiti.GraffitiActivity;

/**
 * Created by Intellij IDEA .
 * 作者：zhong
 * 日期：2018/1/27
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class ActivityImageList extends ZBaseActivity {
//    private RecyclerView mRecyclerView;
//    private EditText mEditText;
//
//    private List<LocalMedia> selectList = new ArrayList<>();
//    private GridImageAdapter mAdapter;
//    private List<String> mImageList = new ArrayList<>();
//
//    private String musicNum = "0";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_list);
//        initView();
//        initData();
//    }
//
//    private void initView() {
//        mRecyclerView = findViewById(R.id.rv_list);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
//        mEditText = findViewById(R.id.editText);
//        RxTextView.textChanges(mEditText).subscribe(key -> musicNum = key.toString());
//        TextView title = findViewById(R.id.tv_title);
//        title.setText("制作");
//        RxView.clicks(findViewById(R.id.iv_back)).subscribe(v -> finish());
//        TextView right = findViewById(R.id.tv_right);
//        right.setText("预览");
//        RxView.clicks(right).subscribe(v -> {
//            //TODO 播放
//            if (mImageList.size() > 0) {
////                CreateGifUtil.createGif("AdFastFlash", mImageList, mContext, path -> {
////                    Intent intent = new Intent(mContext, ActivityPreview.class);
////                    intent.putExtra("path", path);
////                    //intent.putStringArrayListExtra("imageList", (ArrayList<String>) mImageList);
////                    startActivity(intent);
////                });
//                String audioPath = MediaConstant.getAssetsPath(Integer.parseInt(musicNum));
//                CreateVideoUtil.createVideo(MediaConstant.getVideoPath(), mImageList, mContext, audioPath, path1 -> {
//                    Intent intent = new Intent(mContext, ActivityPreview.class);
//                    intent.putExtra("path", path1);
//                    //intent.putStringArrayListExtra("imageList", (ArrayList<String>) mImageList);
//                    startActivity(intent);
//                });
//            } else toast("请选择要制作的图片");
//        });
//    }
//
//    private void initData() {
//        mAdapter = new GridImageAdapter(this, () -> {
//            GridImageConfig.intoAlbum(mContext, 30, selectList);
//        });
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener((position, v) -> {
//            if (selectList.size() > 0) {
//                LocalMedia media = selectList.get(position);
//                //TODO 跳转涂鸦页面
//                Graffiti.startActivityForResult(ActivityImageList.this
//                        , media.getCompressPath()
//                        , position);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //涂鸦回调
//        for (int a = 0; a < mAdapter.getItemCount(); a++) {
//            if (requestCode == a)
//                if (resultCode == GraffitiActivity.RESULT_OK) {
//                    // 获取涂鸦后的图片
//                    String path = data.getStringExtra(GraffitiActivity.KEY_IMAGE_PATH);
//                    mImageList.remove(a);
//                    mImageList.add(a, path);
//                    LocalMedia media = selectList.get(a);
//                    media.setCompressPath(path);
//                    mAdapter.notifyDataSetChanged();
//                } else if (resultCode == GraffitiActivity.RESULT_ERROR) {
//                    toast("制作失败");
//                }
//        }
//        //++++++++++++++++++++++++
//        //相册选取回调
//        if (requestCode == PictureConfig.CHOOSE_REQUEST)
//            if (resultCode == RESULT_OK) {
//                // 图片选择结果回调
//                selectList = PictureSelector.obtainMultipleResult(data);
//                GridImageAdapter adapter = (GridImageAdapter) mRecyclerView.getAdapter();
//                adapter.setList(selectList);
//                adapter.notifyDataSetChanged();
//                for (int a = 0; a < selectList.size(); a++) {
//                    mImageList.add(a, selectList.get(a).getCompressPath());
//                }
//            }
//
//        Log.d("mImageList", mImageList.size() + "");
//        for (String path : mImageList) {
//            Log.d("mImageList", path);
//        }
//    }
}
