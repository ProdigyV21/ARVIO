package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y {
    private final o3.d[] zaa;
    private final boolean zab;
    private final int zac;

    public y(o3.d[] dVarArr, boolean z, int i10) {
        this.zaa = dVarArr;
        boolean z5 = false;
        if (dVarArr != null && z) {
            z5 = true;
        }
        this.zab = z5;
        this.zac = i10;
    }

    public static <A extends com.google.android.gms.common.api.b, ResultT> x builder() {
        x xVar = new x();
        xVar.f12808b = true;
        xVar.f12810d = 0;
        return xVar;
    }

    public abstract void doExecute(com.google.android.gms.common.api.b bVar, com.google.android.gms.tasks.i iVar);

    public boolean shouldAutoResolveMissingFeatures() {
        return this.zab;
    }

    public final o3.d[] zaa() {
        return this.zaa;
    }

    public final int zab() {
        return this.zac;
    }
}
