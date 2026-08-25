package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x f12806a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(x xVar, o3.d[] dVarArr, boolean z, int i10) {
        super(dVarArr, z, i10);
        this.f12806a = xVar;
    }

    @Override // com.google.android.gms.common.api.internal.y
    public final void doExecute(com.google.android.gms.common.api.b bVar, com.google.android.gms.tasks.i iVar) {
        this.f12806a.f12807a.accept(bVar, iVar);
    }
}
