package io.sentry.android.replay;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f16864l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f16865m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f16866n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f16867o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a f16868p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final a f16869q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a f16870r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a f16871s;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16872i;

    static {
        int i10 = 0;
        f16864l = new a(i10, 0);
        f16865m = new a(i10, 1);
        f16866n = new a(i10, 2);
        f16867o = new a(i10, 3);
        f16868p = new a(i10, 4);
        f16869q = new a(i10, 5);
        f16870r = new a(i10, 6);
        f16871s = new a(i10, 7);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i10, int i11) {
        super(i10);
        this.f16872i = i11;
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, x6.s] */
    @Override // r7.a
    public final Object invoke() throws NoSuchFieldException {
        Method method;
        switch (this.f16872i) {
            case 0:
                return new kotlin.text.m("_[a-z]");
            case 1:
                return new io.sentry.util.p();
            case 2:
                u uVar = new u();
                new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new androidx.activity.n(uVar, 24));
                return uVar;
            case 3:
                Class cls = (Class) c0.f16877a.getValue();
                if (cls == null) {
                    return null;
                }
                Field declaredField = cls.getDeclaredField("mViews");
                declaredField.setAccessible(true);
                return declaredField;
            case 4:
                try {
                    return Class.forName("android.view.WindowManagerGlobal");
                } catch (Throwable th) {
                    Log.w("WindowManagerSpy", th);
                    return null;
                }
            case 5:
                Class cls2 = (Class) c0.f16877a.getValue();
                if (cls2 == null || (method = cls2.getMethod("getInstance", null)) == null) {
                    return null;
                }
                return method.invoke(null, null);
            case 6:
                try {
                    return Class.forName("com.android.internal.policy.DecorView");
                } catch (Throwable th2) {
                    Log.d("WindowSpy", "Unexpected exception loading DecorView on API " + Build.VERSION.SDK_INT, th2);
                    return null;
                }
            default:
                Class cls3 = (Class) g0.f16962a.getValue();
                if (cls3 == null) {
                    return null;
                }
                try {
                    Field declaredField2 = cls3.getDeclaredField("mWindow");
                    declaredField2.setAccessible(true);
                    return declaredField2;
                } catch (NoSuchFieldException e5) {
                    Log.d("WindowSpy", "Unexpected exception retrieving " + cls3 + "#mWindow on API " + Build.VERSION.SDK_INT, e5);
                    return null;
                }
        }
    }
}
