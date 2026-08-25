package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcel;
import androidx.compose.material3.d;

/* JADX INFO: loaded from: classes4.dex */
public class SafeParcelReader$ParseException extends RuntimeException {
    public SafeParcelReader$ParseException(String str, Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int iDataSize = parcel.dataSize();
        int length = String.valueOf(str).length();
        StringBuilder sb2 = new StringBuilder(length + 13 + String.valueOf(iDataPosition).length() + 6 + String.valueOf(iDataSize).length());
        d.A(sb2, str, " Parcel: pos=", iDataPosition, " size=");
        sb2.append(iDataSize);
        super(sb2.toString());
    }
}
