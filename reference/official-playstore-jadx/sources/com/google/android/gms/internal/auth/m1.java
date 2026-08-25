package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m1 implements k1, com.google.android.gms.common.api.internal.t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ m1 f13074i = new m1();

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(int i10, int i11, byte[] bArr) {
        int iA;
        while (i10 < i11 && bArr[i10] >= 0) {
            i10++;
        }
        if (i10 >= i11) {
            iA = 0;
        } else {
            while (i10 < i11) {
                int i12 = i10 + 1;
                iA = bArr[i10];
                if (iA < 0) {
                    if (iA >= -32) {
                        if (iA >= -16) {
                            if (i12 < i11 - 2) {
                                int i13 = i10 + 2;
                                int i14 = bArr[i12];
                                if (i14 <= -65) {
                                    if ((((i14 + 112) + (iA << 28)) >> 30) == 0) {
                                        int i15 = i10 + 3;
                                        if (bArr[i13] <= -65) {
                                            i10 += 4;
                                            if (bArr[i15] > -65) {
                                            }
                                        }
                                    }
                                }
                                iA = -1;
                                break;
                            }
                            iA = v3.a(i12, i11, bArr);
                            break;
                        }
                        if (i12 < i11 - 1) {
                            int i16 = i10 + 2;
                            char c10 = bArr[i12];
                            if (c10 <= -65 && ((iA != -32 || c10 >= -96) && (iA != -19 || c10 < -96))) {
                                i10 += 3;
                                if (bArr[i16] > -65) {
                                }
                            }
                            iA = -1;
                            break;
                        }
                        iA = v3.a(i12, i11, bArr);
                        break;
                    }
                    if (i12 >= i11) {
                        break;
                    }
                    if (iA >= -62) {
                        i10 += 2;
                        if (bArr[i12] > -65) {
                        }
                    }
                    iA = -1;
                    break;
                }
                i10 = i12;
            }
            iA = 0;
        }
        return iA == 0;
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        e0 e0Var = new e0((com.google.android.gms.tasks.i) obj2, 1);
        b0 b0Var = (b0) ((z) obj).getService();
        Parcel parcelZza = b0Var.zza();
        o0.d(parcelZza, e0Var);
        b0Var.zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.auth.k1
    public Object zza() {
        throw new IllegalStateException();
    }
}
