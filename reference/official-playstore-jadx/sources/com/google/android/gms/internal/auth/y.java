package com.google.android.gms.internal.auth;

import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y extends w implements a0 {
    public y() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    public void D(String str) {
        throw new UnsupportedOperationException();
    }

    public void d(ProxyResponse proxyResponse) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.auth.w
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 == 1) {
            ProxyResponse proxyResponse = (ProxyResponse) o0.a(parcel, ProxyResponse.CREATOR);
            o0.b(parcel);
            d(proxyResponse);
        } else {
            if (i10 != 2) {
                return false;
            }
            String string = parcel.readString();
            o0.b(parcel);
            D(string);
        }
        parcel2.writeNoException();
        return true;
    }
}
