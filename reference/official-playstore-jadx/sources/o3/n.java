package o3;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.b1;
import com.google.android.gms.common.internal.t;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class n extends n4.d implements b1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f20759d;

    public n(byte[] bArr) {
        super("com.google.android.gms.common.internal.ICertData", 1);
        t.b(bArr.length == 25);
        this.f20759d = Arrays.hashCode(bArr);
    }

    public static byte[] g0(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e5) {
            throw new AssertionError(e5);
        }
    }

    @Override // n4.d
    public final boolean c(int i10, Parcel parcel, Parcel parcel2) {
        if (i10 == 1) {
            com.google.android.gms.dynamic.d dVarZzd = zzd();
            parcel2.writeNoException();
            p4.g.b(parcel2, dVarZzd);
            return true;
        }
        if (i10 != 2) {
            return false;
        }
        parcel2.writeNoException();
        parcel2.writeInt(this.f20759d);
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b1) {
            try {
                b1 b1Var = (b1) obj;
                if (b1Var.zze() == this.f20759d) {
                    return Arrays.equals(f0(), (byte[]) com.google.android.gms.dynamic.d.g0(b1Var.zzd()));
                }
            } catch (RemoteException e5) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e5);
                return false;
            }
        }
        return false;
    }

    public abstract byte[] f0();

    public final int hashCode() {
        return this.f20759d;
    }

    @Override // com.google.android.gms.common.internal.b1
    public final com.google.android.gms.dynamic.d zzd() {
        return new com.google.android.gms.dynamic.d(f0());
    }

    @Override // com.google.android.gms.common.internal.b1
    public final int zze() {
        return this.f20759d;
    }
}
