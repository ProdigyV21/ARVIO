package io.sentry.android.replay.screenshot;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16997i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d f16998l;

    public /* synthetic */ b(d dVar, int i10) {
        this.f16997i = i10;
        this.f16998l = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16997i) {
            case 0:
                d.d(this.f16998l);
                return;
            default:
                d dVar = this.f16998l;
                Bitmap bitmap = dVar.f17004e;
                if (bitmap != null) {
                    synchronized (bitmap) {
                        if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                        }
                        break;
                    }
                }
                dVar.f17011m.release();
                dVar.f17010l.release();
                return;
        }
    }
}
