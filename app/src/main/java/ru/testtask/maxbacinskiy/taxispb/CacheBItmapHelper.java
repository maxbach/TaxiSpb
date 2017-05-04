package ru.testtask.maxbacinskiy.taxispb;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by maxbacinskiy on 03.05.17.
 */

public class CacheBItmapHelper {
    private LruCache<String, Pair> cacheMap;
    private Set<String> filenames;

    public CacheBItmapHelper() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        final int cacheSize = maxMemory / 8;
        cacheMap = new LruCache<String, Pair>(cacheSize) {
            @Override
            protected int sizeOf(String key, Pair value) {
                return value.bmp.getByteCount() / 1024;
            }
        };

        filenames = new HashSet<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        checkAllBitmapForTime();
                        Thread.sleep(10*60*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void addBitmap(String filename, Bitmap bmp) {
        filenames.add(filename);
        cacheMap.put(filename, new Pair(bmp));
    }

    public Bitmap getBitmap(String filename) {
        Pair pair = cacheMap.get(filename);
        return pair == null ? null : pair.getBmp();
    }

    private void checkAllBitmapForTime() {
        Iterator<String> iterator = filenames.iterator();
        Calendar nowTime = Calendar.getInstance();

        while (iterator.hasNext()) {
            String filename = iterator.next();
            Pair pair = cacheMap.get(filename);
            Calendar endTime = pair.endTime;

            if (endTime.after(nowTime)) {
                iterator.remove();
                cacheMap.remove(filename);
            }

        }
    }

    private class Pair {
        Bitmap bmp;
        Calendar endTime;

        public Pair(Bitmap bmp) {
            this.bmp = bmp;

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);
            this.endTime = calendar;
        }

        public Bitmap getBmp() {
            return bmp;
        }
    }
}
