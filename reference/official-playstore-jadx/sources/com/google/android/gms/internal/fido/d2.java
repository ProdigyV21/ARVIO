package com.google.android.gms.internal.fido;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes4.dex */
public final class d2 implements IInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final IBinder f13772c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f13773d;

    public d2(IBinder iBinder, String str) {
        this.f13772c = iBinder;
        this.f13773d = str;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f13772c;
    }
}
