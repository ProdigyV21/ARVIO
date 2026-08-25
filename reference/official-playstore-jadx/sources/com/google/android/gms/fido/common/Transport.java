package com.google.android.gms.fido.common;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.fido.t1;
import com.google.android.gms.internal.fido.u1;

/* JADX INFO: loaded from: classes4.dex */
public enum Transport implements ReflectedParcelable {
    /* JADX INFO: Fake field, exist only in values array */
    BLUETOOTH_CLASSIC("bt"),
    /* JADX INFO: Fake field, exist only in values array */
    BLUETOOTH_LOW_ENERGY("ble"),
    /* JADX INFO: Fake field, exist only in values array */
    NFC("nfc"),
    /* JADX INFO: Fake field, exist only in values array */
    USB("usb"),
    /* JADX INFO: Fake field, exist only in values array */
    INTERNAL("internal"),
    /* JADX INFO: Fake field, exist only in values array */
    HYBRID("cable"),
    /* JADX INFO: Fake field, exist only in values array */
    HYBRID_V2("hybrid");

    public static final Parcelable.Creator<Transport> CREATOR = new a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f12976i;

    public static class UnsupportedTransportException extends Exception {
    }

    Transport(String str) {
        this.f12976i = str;
    }

    public static Transport a(String str) throws UnsupportedTransportException {
        if (str.equals("hybrid")) {
            ((u1) t1.f13822l.f13823i.f13787i).zza();
            throw null;
        }
        for (Transport transport : values()) {
            if (str.equals(transport.f12976i)) {
                return transport;
            }
        }
        throw new UnsupportedTransportException(c.l("Transport ", str, " not supported"));
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f12976i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f12976i);
    }
}
