package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.cast.framework.ModuleUnavailableException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f13226a = new Logger("CastDynamiteModule");

    public static h0 a(Context context) {
        try {
            try {
                IBinder iBinder = (IBinder) f4.d.b(context, f4.d.f15254b).f15264a.getClassLoader().loadClass("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl").newInstance();
                if (iBinder == null) {
                    return null;
                }
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
                return iInterfaceQueryLocalInterface instanceof h0 ? (h0) iInterfaceQueryLocalInterface : new g0(iBinder, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
            } catch (ClassNotFoundException e5) {
                e = e5;
                throw new DynamiteModule$LoadingException("Failed to instantiate module class: ".concat("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl"), e);
            } catch (IllegalAccessException e6) {
                e = e6;
                throw new DynamiteModule$LoadingException("Failed to instantiate module class: ".concat("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl"), e);
            } catch (InstantiationException e10) {
                e = e10;
                throw new DynamiteModule$LoadingException("Failed to instantiate module class: ".concat("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl"), e);
            }
        } catch (DynamiteModule$LoadingException e11) {
            throw new ModuleUnavailableException(e11);
        }
    }
}
