package io.github.jan.supabase.gotrue;

import kotlin.Metadata;
import kotlin.text.a;
import kotlin.text.u;
import m7.b;
import v6.d;
import v6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001a\b\u0010\u0005\u001a\u00020\u0003H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"VERIFIER_LENGTH", "", "generateCodeChallenge", "", "codeVerifier", "generateCodeVerifier", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class PKCEKt {
    private static final int VERIFIER_LENGTH = 64;

    public static final String generateCodeChallenge(String str) {
        byte[] bArr;
        int i10;
        byte[] bytes = str.getBytes(a.f19924a);
        d dVar = new d();
        int length = bytes.length;
        int i11 = length;
        int i12 = 0;
        while (true) {
            bArr = dVar.f22167d;
            i10 = dVar.f22164a;
            if (i11 <= 0) {
                break;
            }
            int iMin = Math.min(i10 - dVar.f22168e, i11);
            int i13 = i12 + iMin;
            System.arraycopy(bytes, i12, bArr, dVar.f22168e, i13 - i12);
            i11 -= iMin;
            int i14 = dVar.f22168e + iMin;
            dVar.f22168e = i14;
            if (i14 >= i10) {
                dVar.f22168e = i14 - i10;
                dVar.a(bArr);
            }
            i12 = i13;
        }
        long j10 = dVar.f22169f + ((long) length);
        dVar.f22169f = j10;
        int i15 = dVar.f22165b;
        byte[] bArr2 = new byte[i15];
        long j11 = 64;
        long j12 = j10 % j11;
        long j13 = j11 - j12;
        if (j13 < 9) {
            j13 = ((long) 128) - j12;
        }
        int i16 = (int) j13;
        byte[] bArr3 = new byte[i16];
        bArr3[0] = -128;
        long j14 = j10 * ((long) 8);
        for (int i17 = 0; i17 < 8; i17++) {
            bArr3[(i16 - 1) - i17] = (byte) ((j14 >>> (i17 * 8)) & 255);
        }
        int i18 = 0;
        while (i18 < i16) {
            int i19 = dVar.f22168e;
            int i20 = (i10 - i19) + i18;
            System.arraycopy(bArr3, i18, bArr, i19, i20 - i18);
            dVar.a(bArr);
            dVar.f22168e = 0;
            i18 = i20;
        }
        int i21 = 0;
        while (true) {
            int[] iArr = dVar.f22170g;
            if (i21 >= i15) {
                System.arraycopy(d.f22163j, 0, iArr, 0, 8);
                b.f20248e.getClass();
                return u.O(b.a(b.f20250g, bArr2), "=", "", false);
            }
            bArr2[i21] = (byte) (iArr[i21 / 4] >>> (24 - ((i21 % 4) * 8)));
            i21++;
        }
    }

    public static final String generateCodeVerifier() {
        byte[] bArr = new byte[64];
        e.f22173m.c(64, bArr);
        b.f20248e.getClass();
        return b.a(b.f20250g, bArr);
    }
}
