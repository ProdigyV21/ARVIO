package androidx.core.graphics;

import android.graphics.Typeface;
import androidx.media3.common.C;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends j {
    @Override // androidx.core.graphics.j
    public final Typeface r(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.f2114f, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f2119l.invoke(null, objNewInstance, C.SANS_SERIF_NAME, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e5) {
            throw new RuntimeException(e5);
        }
    }

    @Override // androidx.core.graphics.j
    public final Method u(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), String.class, cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
