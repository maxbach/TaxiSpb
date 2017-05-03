package ru.testtask.maxbacinskiy.taxispb;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class ImageMemoryUtils {

    public static void saveImageToInternal(String filename, Bitmap bmp, Context context) {

        File mypath = new File(context.getFilesDir(), filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap loadImageFromInternal(String filename, Context context) {
        try {
            File f = new File(context.getFilesDir(), filename);
            return BitmapFactory.decodeStream(new FileInputStream(f));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
