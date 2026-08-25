package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f3220a = t7.a.E(Application.class, w0.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List f3221b = Collections.singletonList(w0.class);

    public static final Constructor a(List list, Class cls) {
        for (Constructor<?> constructor : cls.getConstructors()) {
            List listO0 = kotlin.collections.r.o0(constructor.getParameterTypes());
            if (list.equals(listO0)) {
                return constructor;
            }
            if (list.size() == listO0.size() && listO0.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final d1 b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (d1) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Failed to access " + cls, e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e6);
        } catch (InvocationTargetException e10) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e10.getCause());
        }
    }
}
