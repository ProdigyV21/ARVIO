package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public abstract class w0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q0 f4791b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final q0 f4792c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q0 f4793d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q0 f4794e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final q0 f4795f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final q0 f4796g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final q0 f4797h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q0 f4798i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final q0 f4799j;
    public static final q0 k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f4800a;

    static {
        boolean z = false;
        f4791b = new q0(z, 5);
        boolean z5 = true;
        f4792c = new q0(z5, 4);
        f4793d = new q0(z, 7);
        f4794e = new q0(z5, 6);
        f4795f = new q0(z, 3);
        f4796g = new q0(z5, 2);
        f4797h = new q0(z, 1);
        f4798i = new q0(z5, 0);
        f4799j = new q0(z5, 9);
        k = new q0(z5, 8);
    }

    public w0(boolean z) {
        this.f4800a = z;
    }

    public abstract Object a(Bundle bundle, String str);

    public abstract String b();

    public abstract Object c(String str);

    public Object d(String str, Object obj) {
        return c(str);
    }

    public abstract void e(Bundle bundle, String str, Object obj);

    public final String toString() {
        return b();
    }
}
