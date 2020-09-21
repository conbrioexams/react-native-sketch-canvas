package com.terrylinla.rnsketchcanvas;

import android.util.Log;
import android.graphics.RectF;

public final class Utility {
    public static RectF fillImage(float imgWidth, float imgHeight, float targetWidth, float targetHeight, String mode) {
        float imageAspectRatio = imgWidth / imgHeight;
        float targetAspectRatio = targetWidth / targetHeight;
        switch (mode) {
            case "AspectFill": {
                float scaleFactor = targetAspectRatio < imageAspectRatio ? targetHeight / imgHeight : targetWidth / imgWidth;
                float w = imgWidth * scaleFactor, h = imgHeight * scaleFactor;
                return new RectF((targetWidth - w) / 2, (targetHeight - h) / 2,
                    w + (targetWidth - w) / 2, h + (targetHeight - h) / 2);
            }
            case "AspectFit":
            default: {
                float scaleFactor = targetAspectRatio > imageAspectRatio ? targetHeight / imgHeight : targetWidth / imgWidth;
                float w = imgWidth * scaleFactor, h = imgHeight * scaleFactor;
                return new RectF((targetWidth - w) / 2, (targetHeight - h) / 2,
                    w + (targetWidth - w) / 2, h + (targetHeight - h) / 2);
            }
            case "ScaleToFill": {
                return  new RectF(0, 0, targetWidth, targetHeight);
            }
        }
    }

    public static int calculateInSampleSize(
        int bitmapWidth,
        int bitmapHeight,
        int canvasWidth,
        int canvasHeight) {
        // Raw height and width of image
        final int height = bitmapHeight;
        final int width = bitmapWidth;
        int inSampleSize = 1;

        if (height > canvasHeight || width > canvasWidth) {
            while ((bitmapHeight / inSampleSize) >= (canvasHeight * 1.5)
                    && (bitmapWidth / inSampleSize) >= canvasWidth * 1.5) {
                inSampleSize += 1;
            }
        }

        return inSampleSize;
    }
}
