package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f3 {
    public final boolean a(Object obj, n2 n2Var) throws InvalidProtocolBufferException {
        int iB = n2Var.b();
        int i10 = iB >>> 3;
        int i11 = iB & 7;
        if (i11 == 0) {
            ((g3) obj).c(i10 << 3, Long.valueOf(n2Var.L()));
            return true;
        }
        if (i11 == 1) {
            ((g3) obj).c((i10 << 3) | 1, Long.valueOf(n2Var.c()));
            return true;
        }
        if (i11 == 2) {
            ((g3) obj).c((i10 << 3) | 2, n2Var.p());
            return true;
        }
        if (i11 != 3) {
            if (i11 == 4) {
                return false;
            }
            if (i11 != 5) {
                throw InvalidProtocolBufferException.b();
            }
            ((g3) obj).c((i10 << 3) | 5, Integer.valueOf(n2Var.y()));
            return true;
        }
        g3 g3VarB = g3.b();
        int i12 = i10 << 3;
        int i13 = i12 | 4;
        while (n2Var.E() != Integer.MAX_VALUE && a(g3VarB, n2Var)) {
        }
        if (i13 != n2Var.b()) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
        g3VarB.f2444e = false;
        ((g3) obj).c(i12 | 3, g3VarB);
        return true;
    }
}
