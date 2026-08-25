package androidx.mediarouter.media;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class q1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4475b;

    public q1(String str, int i10) {
        this.f4474a = str;
        this.f4475b = i10;
    }

    public q1(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f4474a = str;
            this.f4475b = 1;
            return;
        }
        throw new IllegalArgumentException();
    }
}
