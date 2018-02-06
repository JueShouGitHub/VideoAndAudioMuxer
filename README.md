# VideoAndAudioMuxer

一个简单的音视频你合成的框架，支持图片合成音视频（有内置的几款音频），支持图片编辑后合成音视频
=

### 使用方法
[^_^]:
#### gradle 
1、第一步
```aidl 
    allprojects {
    		repositories {
    			...
    			maven { url 'https://jitpack.io' }
    		}
    	}
```
2、第二步
```aidl 
    dependencies {
    	        compile 'com.github.JueShouGitHub:VideoAndAudioMuxer:1.0.2'
    	}
```

#### maven
1、第一步
```aidl 
    <repositories>
    		<repository>
    		    <id>jitpack.io</id>
    		    <url>https://jitpack.io</url>
    		</repository>
    	</repositories>
```
2、第二步
```aidl 
    <dependency>
    	    <groupId>com.github.JueShouGitHub</groupId>
    	    <artifactId>VideoAndAudioMuxer</artifactId>
    	    <version>1.0.2</version>
    	</dependency>
```

###### 本项目中图片生成视频用的时```JavaCV```来实现的，JavaCV的使用方法 [请参照](https://github.com/bytedeco/javacv)
###### 还有一点，该项目还集成了RxJava来处理异步操作，所以就不必要在重复添加依赖了，还有retrofit也集成了，不需要的小伙伴可以直接去掉
```aidl 
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    compile 'io.reactivex.rxjava2:rxjava:2.1.7'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
```

### 1、首先在程序启动时初始化资源（即复制音频资源到SD卡上），音频文件放在assets文件夹下（与res文件夹
### 同级）

```aidl  
    MediaConstant.copyFilesFassets(this, "audio", MediaConstant.createAssetsPath()); 
```
- 其中 ``` audio ``` 即本地存放音频文件的文件夹的原文件路径 可以随意修改
- ``` Constant.createAssetsPath() ``` 即复制后在SD卡上的路径 可以随意修改

### 2、视频音频合成新的视频

```aidl 
    AudioAndVideoCompounder compounder = 
    AudioAndVideoCompounder.createCompounder(String videoPath, String audioPath, String dstPath);
 ```

```createCompounder``` 方法参数说明：
- videoPath: 原视频路径
- audioPath：原音频路径
- dstPath ：合成后音视频路径

然后调用 ```AudioAndVideoCompounder```的```start()```方法即可 最好通过异步方式来调用
如果需要，记得合成玩将原视频删除
```aidl 
    File file = new File(saveMp4Name);
    if (file.exists()) file.delete();
```

###3、通过选择本地图片（编辑或者其他对图片进行涂鸦后），合成GIF或者合成无声的视频
##### 先说合成无声视频吧（关于声音，有了视频再合成声音嘛，上面说的方法就好了！）

```aidl 
    String videoPath = 
    CreateVideoUtil.createVideo(String saveMp4Name, List<String> imageList, int width, int height);
```

```createVideo```   该方法有多个重载方法，注意参数就好了
- saveMp4Name 生成视频的路径
- imageList 用于生成视频的图片合集（可以是涂鸦过的，也可以时原图）
- width 生成视频的宽
- height 生成视频的高
宽高可以这样获取：
```aidl 
    WindowManager manager = activity.getWindowManager();
    DisplayMetrics outMetrics = new DisplayMetrics();
    manager.getDefaultDisplay().getMetrics(outMetrics);
    int mWidth = outMetrics.widthPixels;
    int mHeight = outMetrics.heightPixels;
```

###### 如果想要音频效果，按照第一步的合成音视频即可

##### 接着说说用图片生成GIf图的方法吧

```aidl 
    CreateGifUtil.createGif(String filename, List<String> paths, Activity activity, ZCreateListener listener);
```
其中涉及一个回调函数 ```ZCreateListener```,很简单就一个方法
```aidl 
    void onComplete(String path);
```
参数 ```String path``` 就是生成的GIf的路径

###### 补充一下，其中涉及的图片选择和图片涂鸦的功能
图片选择选用的是：
```aidl 
    implementation('com.github.LuckSiege.PictureSelector:picture_library:v2.1.7')
```
图片涂鸦选择的是：
```aidl 
    implementation ('com.hzw.graffiti:graffiti:4.3.1')
```

> 用法：必须用RecyclerView来展示图片，
>```aidl 
>   mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
>```
> 其中 GridLayoutManager 已经实现，可以直接拿来使用
>
> 至于Adapter 
>```aidl 
>    GridImageAdapter(Context context, onAddPicClickListener mOnAddPicClickListener);
>```
>也是内部实现好的，可以直接拿来用
> 

```aidl 
     mAdapter = new GridImageAdapter(this, () -> {
        //这里就是起吊相册，选择图片
        GridImageConfig.intoAlbum(mContext, 30, selectList);
     });
     
     mAdapter.setOnItemClickListener((position, v) -> {
         if (selectList.size() > 0) {
             LocalMedia media = selectList.get(position);
             //TODO 这里就是跳转涂鸦页面
             Graffiti.startActivityForResult(ActivityImageList.this
                     , media.getCompressPath()
                     , position);
         }
     });
```

### 下面就是拿到选择后或者涂鸦后的图片方法：
###### 通过```onActivityResult```拿到数据
```aidl 
    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //涂鸦回调
            for (int a = 0; a < mAdapter.getItemCount(); a++) {
                if (requestCode == a)
                    if (resultCode == GraffitiActivity.RESULT_OK) {
                        // 获取涂鸦后的图片
                        String path = data.getStringExtra(GraffitiActivity.KEY_IMAGE_PATH);
                        mImageList.remove(a);
                        mImageList.add(a, path);
                        LocalMedia media = selectList.get(a);
                        media.setCompressPath(path);
                        mAdapter.notifyDataSetChanged();
                    } else if (resultCode == GraffitiActivity.RESULT_ERROR) {
                        toast("制作失败");
                    }
            }
            //++++++++++++++++++++++++
            //相册选取回调
            if (requestCode == PictureConfig.CHOOSE_REQUEST)
                if (resultCode == RESULT_OK) {
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    GridImageAdapter adapter = (GridImageAdapter) mRecyclerView.getAdapter();
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    for (int a = 0; a < selectList.size(); a++) {
                        mImageList.add(a, selectList.get(a).getCompressPath());
                    }
                }
    
            Log.d("mImageList", mImageList.size() + "");
            for (String path : mImageList) {
                Log.d("mImageList", path);
            }
        }
```







