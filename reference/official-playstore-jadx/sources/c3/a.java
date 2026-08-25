package c3;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f7386b;

    public a(ArrayList arrayList, byte[] bArr) {
        this.f7385a = arrayList;
        this.f7386b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f7385a.equals(aVar.f7385a) && Arrays.equals(this.f7386b, aVar.f7386b);
    }

    public final int hashCode() {
        return ((this.f7385a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f7386b);
    }

    public final String toString() {
        return "BackendRequest{events=" + this.f7385a + ", extras=" + Arrays.toString(this.f7386b) + "}";
    }
}
