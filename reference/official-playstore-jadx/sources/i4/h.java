package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.a1;
import com.google.android.gms.internal.fido.i1;
import com.google.android.gms.internal.fido.y0;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends j {
    public static final Parcelable.Creator<h> CREATOR = new k0(25);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f16118i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16119l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f16120m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String[] f16121n;

    public h(byte[] bArr, byte[] bArr2, byte[] bArr3, String[] strArr) {
        com.google.android.gms.common.internal.t.i(bArr);
        i1 i1VarH = i1.h(bArr.length, bArr);
        com.google.android.gms.common.internal.t.i(bArr2);
        i1 i1VarH2 = i1.h(bArr2.length, bArr2);
        com.google.android.gms.common.internal.t.i(bArr3);
        i1 i1VarH3 = i1.h(bArr3.length, bArr3);
        this.f16118i = i1VarH;
        this.f16119l = i1VarH2;
        this.f16120m = i1VarH3;
        com.google.android.gms.common.internal.t.i(strArr);
        this.f16121n = strArr;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return com.google.android.gms.common.internal.t.l(this.f16118i, hVar.f16118i) && com.google.android.gms.common.internal.t.l(this.f16119l, hVar.f16119l) && com.google.android.gms.common.internal.t.l(this.f16120m, hVar.f16120m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(new Object[]{this.f16118i})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16119l})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16120m}))});
    }

    public final String toString() {
        androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(getClass().getSimpleName(), 12);
        y0 y0Var = a1.f13747d;
        byte[] bArrK = this.f16118i.k();
        i1Var.f0(y0Var.c(bArrK.length, bArrK), "keyHandle");
        byte[] bArrK2 = this.f16119l.k();
        i1Var.f0(y0Var.c(bArrK2.length, bArrK2), "clientDataJSON");
        byte[] bArrK3 = this.f16120m.k();
        i1Var.f0(y0Var.c(bArrK3.length, bArrK3), "attestationObject");
        i1Var.f0(Arrays.toString(this.f16121n), "transports");
        return i1Var.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.a0(parcel, 2, this.f16118i.k(), false);
        a.a.a0(parcel, 3, this.f16119l.k(), false);
        a.a.a0(parcel, 4, this.f16120m.k(), false);
        a.a.h0(parcel, 5, this.f16121n, false);
        a.a.n0(iM0, parcel);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0146 A[Catch: JSONException -> 0x0021, zzho -> 0x01b4, TRY_LEAVE, TryCatch #10 {zzho -> 0x01b4, blocks: (B:41:0x0112, B:47:0x0135, B:49:0x0146, B:54:0x0160, B:57:0x017c, B:59:0x0191, B:61:0x0196, B:64:0x01b7, B:65:0x01bc, B:66:0x01bd, B:67:0x01c4, B:72:0x01d1, B:74:0x01de, B:76:0x01eb, B:77:0x0204, B:78:0x0209, B:79:0x020a, B:80:0x020f, B:86:0x0231, B:87:0x0236), top: B:131:0x0112, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject zza() {
        /*
            Method dump skipped, instruction units count: 653
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i4.h.zza():org.json.JSONObject");
    }
}
