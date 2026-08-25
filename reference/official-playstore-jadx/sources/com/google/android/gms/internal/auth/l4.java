package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class l4 extends com.google.android.gms.common.api.internal.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f13070c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f13071d;

    public /* synthetic */ l4(int i10, com.google.android.gms.tasks.i iVar) {
        this.f13070c = i10;
        this.f13071d = iVar;
    }

    @Override // com.google.android.gms.common.api.internal.j
    public final void onResult(Status status) {
        switch (this.f13070c) {
            case 0:
                c.c(status, null, this.f13071d);
                break;
            case 1:
                qb.d.S(status, null, this.f13071d);
                break;
            case 2:
                qb.d.S(status, Boolean.TRUE, this.f13071d);
                break;
            case 3:
                qb.d.S(status, null, this.f13071d);
                break;
            case 4:
                qb.d.S(status, null, this.f13071d);
                break;
            case 5:
                qb.d.S(status, null, this.f13071d);
                break;
            case 6:
                int i10 = status.f12675i;
                com.google.android.gms.tasks.i iVar = this.f13071d;
                if (i10 != 6) {
                    qb.d.S(status, null, iVar);
                } else {
                    iVar.c(com.google.android.gms.common.internal.t.m(status));
                }
                break;
            default:
                int i11 = status.f12675i;
                com.google.android.gms.tasks.i iVar2 = this.f13071d;
                if (i11 != 6) {
                    qb.d.S(status, null, iVar2);
                } else {
                    iVar2.c(com.google.android.gms.common.internal.t.m(status));
                }
                break;
        }
    }

    public /* synthetic */ l4(com.google.android.gms.internal.cast.a0 a0Var, com.google.android.gms.tasks.i iVar, int i10) {
        this.f13070c = i10;
        this.f13071d = iVar;
    }
}
