package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class w extends t3.a {
    public static final Parcelable.Creator<w> CREATOR = new k0(7);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16204i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16205l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16206m;

    public w(String str, String str2, String str3) {
        com.google.android.gms.common.internal.t.i(str);
        this.f16204i = str;
        com.google.android.gms.common.internal.t.i(str2);
        this.f16205l = str2;
        this.f16206m = str3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return com.google.android.gms.common.internal.t.l(this.f16204i, wVar.f16204i) && com.google.android.gms.common.internal.t.l(this.f16205l, wVar.f16205l) && com.google.android.gms.common.internal.t.l(this.f16206m, wVar.f16206m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16204i, this.f16205l, this.f16206m});
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("PublicKeyCredentialRpEntity{\n id='");
        sb2.append(this.f16204i);
        sb2.append("', \n name='");
        sb2.append(this.f16205l);
        sb2.append("', \n icon='");
        return a0.c.p(sb2, this.f16206m, "'}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, this.f16204i, false);
        a.a.g0(parcel, 3, this.f16205l, false);
        a.a.g0(parcel, 4, this.f16206m, false);
        a.a.n0(iM0, parcel);
    }
}
