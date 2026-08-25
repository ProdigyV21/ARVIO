package androidx.appcompat.widget;

import android.os.Build;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class v3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Method f1798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Method f1799b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Method f1800c;

    public /* synthetic */ v3(Method method, Method method2, Method method3) {
        this.f1798a = method;
        this.f1799b = method2;
        this.f1800c = method3;
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 29) {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
        }
    }
}
