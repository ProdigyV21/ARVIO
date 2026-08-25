package androidx.leanback.widget;

import android.graphics.Color;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3082b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3083c;

    public f1(int i10, int i11, int i12) {
        this.f3081a = i10;
        if (i11 == i10) {
            i11 = Color.argb((int) ((Color.alpha(i10) * 0.85f) + 38.25f), (int) ((Color.red(i10) * 0.85f) + 38.25f), (int) ((Color.green(i10) * 0.85f) + 38.25f), (int) ((Color.blue(i10) * 0.85f) + 38.25f));
        }
        this.f3082b = i11;
        this.f3083c = i12;
    }
}
