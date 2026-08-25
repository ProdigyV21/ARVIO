package androidx.core.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f1997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f1998b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f1999c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f2000d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f2001e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f2002f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f2003g = new Handler(Looper.getMainLooper());

    static {
        Class<?> cls;
        Field declaredField;
        Field declaredField2;
        Method declaredMethod;
        Method declaredMethod2;
        Method method = null;
        try {
            cls = Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            cls = null;
        }
        f1997a = cls;
        try {
            declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
        } catch (Throwable unused2) {
            declaredField = null;
        }
        f1998b = declaredField;
        try {
            declaredField2 = Activity.class.getDeclaredField("mToken");
            declaredField2.setAccessible(true);
        } catch (Throwable unused3) {
            declaredField2 = null;
        }
        f1999c = declaredField2;
        Class cls2 = f1997a;
        Class<?> cls3 = Boolean.TYPE;
        if (cls2 == null) {
            declaredMethod = null;
        } else {
            try {
                declaredMethod = cls2.getDeclaredMethod("performStopActivity", IBinder.class, cls3, String.class);
                declaredMethod.setAccessible(true);
            } catch (Throwable unused4) {
                declaredMethod = null;
            }
        }
        f2000d = declaredMethod;
        Class cls4 = f1997a;
        if (cls4 == null) {
            declaredMethod2 = null;
        } else {
            try {
                declaredMethod2 = cls4.getDeclaredMethod("performStopActivity", IBinder.class, cls3);
                declaredMethod2.setAccessible(true);
            } catch (Throwable unused5) {
                declaredMethod2 = null;
            }
        }
        f2001e = declaredMethod2;
        Class cls5 = f1997a;
        int i10 = Build.VERSION.SDK_INT;
        if ((i10 == 26 || i10 == 27) && cls5 != null) {
            try {
                Method declaredMethod3 = cls5.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls3, Configuration.class, Configuration.class, cls3, cls3);
                declaredMethod3.setAccessible(true);
                method = declaredMethod3;
            } catch (Throwable unused6) {
            }
        }
        f2002f = method;
    }
}
