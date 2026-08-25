package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.t;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends n4.d implements b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f12974d;

    public d(Object obj) {
        super("com.google.android.gms.dynamic.IObjectWrapper", 1);
        this.f12974d = obj;
    }

    public static b f0(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
        return iInterfaceQueryLocalInterface instanceof b ? (b) iInterfaceQueryLocalInterface : new g(iBinder, "com.google.android.gms.dynamic.IObjectWrapper", 1);
    }

    public static Object g0(b bVar) {
        if (bVar instanceof d) {
            return ((d) bVar).f12974d;
        }
        IBinder iBinderAsBinder = bVar.asBinder();
        Field[] declaredFields = iBinderAsBinder.getClass().getDeclaredFields();
        Field field = null;
        int i10 = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i10++;
                field = field2;
            }
        }
        if (i10 != 1) {
            int length = declaredFields.length;
            throw new IllegalArgumentException(androidx.compose.foundation.c.p(length, "Unexpected number of IObjectWrapper declared fields: ", new StringBuilder(String.valueOf(length).length() + 53)));
        }
        t.i(field);
        if (field.isAccessible()) {
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        field.setAccessible(true);
        try {
            return field.get(iBinderAsBinder);
        } catch (IllegalAccessException e5) {
            throw new IllegalArgumentException("Could not access the field in remoteBinder.", e5);
        } catch (NullPointerException e6) {
            throw new IllegalArgumentException("Binder object is null.", e6);
        }
    }
}
