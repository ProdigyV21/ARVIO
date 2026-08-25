package androidx.core.view;

import android.os.Build;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public final class b3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a3 f2205a;

    public b3(int i10, Interpolator interpolator, long j10) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.f2205a = new z2(x2.b(i10, interpolator, j10));
        } else {
            this.f2205a = new w2(interpolator, j10);
        }
    }
}
