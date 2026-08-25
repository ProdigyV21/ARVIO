package androidx.appcompat.widget;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f1508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Method f1509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Method f1510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1511d;

    static {
        try {
            Class cls = Integer.TYPE;
            Class cls2 = Float.TYPE;
            Method declaredMethod = AbsListView.class.getDeclaredMethod("positionSelector", cls, View.class, Boolean.TYPE, cls2, cls2);
            f1508a = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", cls);
            f1509b = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", cls);
            f1510c = declaredMethod3;
            declaredMethod3.setAccessible(true);
            f1511d = true;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        }
    }
}
