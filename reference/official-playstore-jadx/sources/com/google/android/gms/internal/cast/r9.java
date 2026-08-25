package com.google.android.gms.internal.cast;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r9 implements PrivilegedExceptionAction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ r9 f13591a = new r9();

    @Override // java.security.PrivilegedExceptionAction
    public final /* synthetic */ Object run() throws IllegalAccessException {
        Unsafe unsafe = o9.f13523m;
        for (Field field : Unsafe.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (Unsafe.class.isInstance(obj)) {
                return (Unsafe) Unsafe.class.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }
}
