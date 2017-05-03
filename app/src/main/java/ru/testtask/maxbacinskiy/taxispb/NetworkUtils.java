package ru.testtask.maxbacinskiy.taxispb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by maxbacinskiy on 02.05.17.
 */

public class NetworkUtils {

    final static String INFO_URL = "http://careers.ekassir.com/test/orders.json";
    final static String IMAGE_URL = "http://careers.ekassir.com/test/images";

    public static URL buildInfoUrl() {
        Uri buildUri = Uri.parse(INFO_URL).buildUpon().build();
        return fromUriToUrl(buildUri);
    }

    public static URL buildImageUrl(String imageFileWay) {
        Uri buildUri = Uri.parse(IMAGE_URL).buildUpon().appendPath(imageFileWay).build();
        return fromUriToUrl(buildUri);
    }

    private static URL fromUriToUrl(Uri uri) {
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponceFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = connection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }

    public static Bitmap getImageFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = connection.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(in);

            if (bmp != null) {
                return bmp;
            } else {
                return null;
            }

        } finally {
            connection.disconnect();
        }
    }

}
