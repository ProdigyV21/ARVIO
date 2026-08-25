package com.arflix.tv.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import b6.b;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/arflix/tv/util/QrCodeGenerator;", "", "<init>", "()V", "generate", "Landroid/graphics/Bitmap;", "content", "", ContentDisposition.Parameters.Size, "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QrCodeGenerator {
    public static final int $stable = 0;
    public static final QrCodeGenerator INSTANCE = new QrCodeGenerator();

    private QrCodeGenerator() {
    }

    public static /* synthetic */ Bitmap generate$default(QrCodeGenerator qrCodeGenerator, String str, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 512;
        }
        return qrCodeGenerator.generate(str, i10);
    }

    public final Bitmap generate(String content, int size) {
        b bVar;
        b bVarL = l.l(size, size, content);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        float f10 = size;
        float f11 = 0.06f * f10;
        RectF rectF = new RectF(0.0f, 0.0f, f10, f10);
        canvas.drawRoundRect(rectF, f11, f11, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-16777216);
        paint2.setStyle(style);
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                i11 = 1;
                break;
            }
            if (bVarL.a(i11, 0) != bVarL.a(0, 0)) {
                break;
            }
            i11++;
        }
        int i12 = i11 >= 1 ? i11 : 1;
        float f12 = i12 * 0.3f;
        int i13 = 0;
        while (i13 < size) {
            int i14 = i10;
            while (i14 < size) {
                if (bVarL.a(i13, i14)) {
                    float f13 = i13;
                    float f14 = i14;
                    float f15 = i13 + i12;
                    if (f15 > f10) {
                        f15 = f10;
                    }
                    bVar = bVarL;
                    float f16 = i14 + i12;
                    if (f16 > f10) {
                        f16 = f10;
                    }
                    canvas.drawRoundRect(new RectF(f13, f14, f15, f16), f12, f12, paint2);
                } else {
                    bVar = bVarL;
                }
                i14 += i12;
                bVarL = bVar;
            }
            i13 += i12;
            i10 = 0;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
        Path path = new Path();
        path.addRoundRect(rectF, f11, f11, Path.Direction.CW);
        canvas2.clipPath(path);
        canvas2.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap2;
    }
}
