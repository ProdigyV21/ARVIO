package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class m2 extends n2 {
    @Override // com.google.android.gms.internal.auth.n2
    public final void a(long j10, Object obj) {
        ((g2) u3.d(j10, obj)).zzb();
    }

    @Override // com.google.android.gms.internal.auth.n2
    public final void b(Object obj, Object obj2, long j10) {
        g2 g2VarZzd = (g2) u3.d(j10, obj);
        g2 g2Var = (g2) u3.d(j10, obj2);
        int size = g2VarZzd.size();
        int size2 = g2Var.size();
        if (size > 0 && size2 > 0) {
            if (!g2VarZzd.zzc()) {
                g2VarZzd = g2VarZzd.zzd(size2 + size);
            }
            g2VarZzd.addAll(g2Var);
        }
        if (size > 0) {
            g2Var = g2VarZzd;
        }
        u3.j(obj, g2Var, j10);
    }
}
