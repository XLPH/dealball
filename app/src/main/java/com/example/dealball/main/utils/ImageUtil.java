package com.example.dealball.main.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     */

    public static Bitmap getPictureFromBytes(byte[] bytes, BitmapFactory.Options options){
        if(bytes != null){
            if(options != null){
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            }else {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }
        return null;
    }

    /**
     * 将Bitmap转byte数组传递数据给后台
     */

    public static byte[] bitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap getBitmapFromUri(Uri uri, Activity activity) throws IOException {
        InputStream inputStream = activity.getContentResolver().openInputStream(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeStream(inputStream, null, options);
        inputStream.close();
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        if (originalWidth == -1 || originalHeight == -1) {
            return null;
        }

        float height = 150f;
        float width = 150f;
        int be = 1; //be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > width) {
            be = (int) (originalWidth / width);
        } else if (originalWidth < originalHeight && originalHeight > height) {
            be = (int) (originalHeight / height);
        }

        if (be <= 0) {
            be = 1;
        }
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inSampleSize = be;
        options1.inDither = true;
        options.inJustDecodeBounds = false;
        options1.inPreferredConfig = Bitmap.Config.ARGB_8888;
        inputStream = activity.getContentResolver().openInputStream(uri);

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options1);
        inputStream.close();

        return compressImage(bitmap);
    }

    public static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    /**
     * 质量压缩方法
     *
     * @param bitmap
     * @return
     */
    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int options = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差 ，第三个参数：保存压缩后的数据的流
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, byteArrayOutputStream);
            options -= 10;
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println(bytes);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Bitmap bitmapImage = BitmapFactory.decodeStream(byteArrayInputStream, null, null);
        return bitmapImage;
//        return byteArrayOutputStream.toByteArray();

    }

    /**
     * base64字符串转byte[]
     */
    public static byte[] base64StringToByte(String base64Str){
        return Base64.decode(base64Str, Base64.DEFAULT);
    }
    public static String byteToBase64String(byte[] bytes){
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
