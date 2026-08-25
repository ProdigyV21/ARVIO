package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzn implements zza {
    final /* synthetic */ zzs zza;

    public zzn(zzs zzsVar) {
        Objects.requireNonNull(zzsVar);
        this.zza = zzsVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        int i10 = zzs.zza;
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            float f10 = width;
            int i11 = (int) (((9.0f * f10) / 16.0f) + 0.5f);
            float f11 = (i11 - r3) / 2.0f;
            RectF rectF = new RectF(0.0f, f11, f10, bitmap.getHeight() + f11);
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, i11, config);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, (Rect) null, rectF, (Paint) null);
            bitmap2 = bitmapCreateBitmap;
        }
        this.zza.zze(bitmap2, 0);
    }
}
