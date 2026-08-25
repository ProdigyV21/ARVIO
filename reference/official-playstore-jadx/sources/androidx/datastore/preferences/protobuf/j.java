package androidx.datastore.preferences.protobuf;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2450a;

    @Override // androidx.datastore.preferences.protobuf.k
    public final byte[] a(int i10, int i11, byte[] bArr) {
        switch (this.f2450a) {
            case 0:
                return Arrays.copyOfRange(bArr, i10, i11 + i10);
            default:
                byte[] bArr2 = new byte[i11];
                System.arraycopy(bArr, i10, bArr2, 0, i11);
                return bArr2;
        }
    }
}
