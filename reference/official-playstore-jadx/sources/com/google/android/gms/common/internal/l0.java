package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public final class l0 implements q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final IBinder f12891c;

    public l0(IBinder iBinder) {
        this.f12891c = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f12891c;
    }

    @Override // com.google.android.gms.common.internal.q
    public final void h(p0 p0Var, j jVar) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            parcelObtain.writeStrongBinder(p0Var);
            parcelObtain.writeInt(1);
            android.support.v4.media.d.a(jVar, parcelObtain, 0);
            this.f12891c.transact(46, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
