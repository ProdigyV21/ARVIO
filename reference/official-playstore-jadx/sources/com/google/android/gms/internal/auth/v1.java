package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes4.dex */
public abstract class v1 extends w implements h2 {
    public static h2 a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        return iInterfaceQueryLocalInterface instanceof h2 ? (h2) iInterfaceQueryLocalInterface : new e1(iBinder, "com.google.android.auth.IAuthManagerService");
    }
}
