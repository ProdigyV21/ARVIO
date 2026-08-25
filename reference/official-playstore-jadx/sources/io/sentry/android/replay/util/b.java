package io.sentry.android.replay.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends Drawable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f17037d = Color.argb(32, 255, 20, 20);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f17038e = Color.argb(128, 255, 20, 20);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f17039a = new Paint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f17040b = new Rect();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f17041c = z.f19728i;

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint = this.f17039a;
        paint.setTextSize(32.0f);
        paint.setColor(-16777216);
        paint.setStrokeWidth(6.0f);
        for (Rect rect : this.f17041c) {
            paint.setColor(f17037d);
            Paint.Style style = Paint.Style.FILL;
            paint.setStyle(style);
            canvas.drawRect(rect, paint);
            paint.setColor(f17038e);
            Paint.Style style2 = Paint.Style.STROKE;
            paint.setStyle(style2);
            canvas.drawRect(rect, paint);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(rect.left);
            sb2.append('/');
            sb2.append(rect.top);
            String string = sb2.toString();
            int length = string.length();
            Rect rect2 = this.f17040b;
            paint.getTextBounds(string, 0, length, rect2);
            float f10 = rect.left;
            float f11 = rect.top;
            paint.setColor(-1);
            paint.setStyle(style2);
            canvas.drawText(string, f10, f11, paint);
            paint.setColor(-16777216);
            paint.setStyle(style);
            canvas.drawText(string, f10, f11, paint);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(rect.right);
            sb3.append('/');
            sb3.append(rect.bottom);
            String string2 = sb3.toString();
            paint.getTextBounds(string2, 0, string2.length(), rect2);
            float fWidth = rect.right - rect2.width();
            float fHeight = rect.bottom + rect2.height();
            paint.setColor(-1);
            paint.setStyle(style2);
            canvas.drawText(string2, fWidth, fHeight, paint);
            paint.setColor(-16777216);
            paint.setStyle(style);
            canvas.drawText(string2, fWidth, fHeight, paint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i10) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
