package j$.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class DesugarCollections {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f18427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f18428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f18429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Constructor f18430d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Constructor f18431e;

    static {
        Field declaredField;
        Field declaredField2;
        Constructor<?> declaredConstructor;
        Class<?> cls = Collections.synchronizedCollection(new ArrayList()).getClass();
        f18427a = cls;
        Collections.synchronizedList(new LinkedList()).getClass();
        Constructor declaredConstructor2 = null;
        try {
            declaredField = cls.getDeclaredField("mutex");
        } catch (NoSuchFieldException unused) {
            declaredField = null;
        }
        f18428b = declaredField;
        if (declaredField != null) {
            declaredField.setAccessible(true);
        }
        try {
            declaredField2 = f18427a.getDeclaredField("c");
        } catch (NoSuchFieldException unused2) {
            declaredField2 = null;
        }
        f18429c = declaredField2;
        if (declaredField2 != null) {
            declaredField2.setAccessible(true);
        }
        try {
            declaredConstructor = Collections.synchronizedSet(new HashSet()).getClass().getDeclaredConstructor(java.util.Set.class, Object.class);
        } catch (NoSuchMethodException unused3) {
            declaredConstructor = null;
        }
        f18431e = declaredConstructor;
        if (declaredConstructor != null) {
            declaredConstructor.setAccessible(true);
        }
        try {
            declaredConstructor2 = f18427a.getDeclaredConstructor(java.util.Collection.class, Object.class);
        } catch (NoSuchMethodException unused4) {
        }
        f18430d = declaredConstructor2;
        if (declaredConstructor2 != null) {
            declaredConstructor2.setAccessible(true);
        }
    }

    public static <K, V> java.util.Map<K, V> synchronizedMap(java.util.Map<K, V> map) {
        return new g(map);
    }
}
