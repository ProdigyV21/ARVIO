package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new android.support.v4.media.d(17);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final IBinder f12828i;

    public BinderWrapper(IBinder iBinder) {
        this.f12828i = iBinder;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeStrongBinder(this.f12828i);
    }

    public /* synthetic */ BinderWrapper(Parcel parcel) {
        this.f12828i = parcel.readStrongBinder();
    }
}
