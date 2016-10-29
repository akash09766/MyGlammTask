package com.myglamm.myglammtask.application;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Akash Wangalwar on 28-10-2016.
 */
public class MyGlammApplication extends Application{

    private DisplayImageOptions options;
    private String root = Environment.getExternalStorageDirectory().toString() + "/lifepod/";

    @Override
    public void onCreate() {
        super.onCreate();


        File cacheDir = StorageUtils.getOwnCacheDirectory(this, root);
        // Create global configuration and initialize ImageLoader with this config

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .defaultDisplayImageOptions(options)
//                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);

    }
}
