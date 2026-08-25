package androidx.media3.session;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l3 implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SizeLimitedBitmapLoader f4070a;

    public /* synthetic */ l3(SizeLimitedBitmapLoader sizeLimitedBitmapLoader) {
        this.f4070a = sizeLimitedBitmapLoader;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        return this.f4070a.scaleIfNecessary((Bitmap) obj);
    }
}
