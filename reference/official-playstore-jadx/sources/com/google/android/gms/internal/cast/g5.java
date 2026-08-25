package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class g5 extends SessionTransferCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13261b;

    public /* synthetic */ g5(Object obj, int i10) {
        this.f13260a = i10;
        this.f13261b = obj;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferFailed(int i10, int i11) {
        int i12 = this.f13260a;
        Object obj = this.f13261b;
        int i13 = 2;
        switch (i12) {
            case 0:
                Logger logger = i5.f13315j;
                i5.f13315j.d("onTransferFailed with type = %d and reason = %d", Integer.valueOf(i10), Integer.valueOf(i11));
                i5 i5Var = (i5) obj;
                i5Var.e();
                l5 l5Var = i5Var.f13318c;
                j5 j5Var = i5Var.f13322g;
                z4 z4Var = i5Var.f13316a;
                b6 b6VarB = l5Var.b(j5Var);
                v5 v5VarO = w5.o(b6VarB.f());
                v5VarO.c();
                ((w5) v5VarO.f13530l).x(i10);
                v5VarO.c();
                ((w5) v5VarO.f13530l).y(i11);
                b6VarB.g((w5) v5VarO.e());
                z4Var.a((c6) b6VarB.e(), 232);
                i5Var.f13324i = false;
                break;
            default:
                androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(11, i13);
                f0Var.f1567m = Integer.valueOf(i11);
                androidx.core.provider.e eVar = (androidx.core.provider.e) obj;
                f0Var.f1568n = Boolean.valueOf(((c0) eVar.f2142n).a());
                eVar.a(new k1(f0Var));
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferred(int i10, SessionState sessionState) {
        switch (this.f13260a) {
            case 0:
                Logger logger = i5.f13315j;
                i5.f13315j.d("onTransferred with type = %d", Integer.valueOf(i10));
                i5 i5Var = (i5) this.f13261b;
                i5Var.e();
                l5 l5Var = i5Var.f13318c;
                j5 j5Var = i5Var.f13322g;
                z4 z4Var = i5Var.f13316a;
                b6 b6VarB = l5Var.b(j5Var);
                v5 v5VarO = w5.o(b6VarB.f());
                v5VarO.c();
                ((w5) v5VarO.f13530l).x(i10);
                b6VarB.g((w5) v5VarO.e());
                z4Var.a((c6) b6VarB.e(), 231);
                i5Var.f13324i = false;
                i5Var.f13322g = null;
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferring(int i10) {
        int i11 = this.f13260a;
        Object obj = this.f13261b;
        switch (i11) {
            case 0:
                Logger logger = i5.f13315j;
                i5.f13315j.d("onTransferring with type = %d", Integer.valueOf(i10));
                i5 i5Var = (i5) obj;
                i5Var.f13324i = true;
                i5Var.e();
                l5 l5Var = i5Var.f13318c;
                j5 j5Var = i5Var.f13322g;
                z4 z4Var = i5Var.f13316a;
                b6 b6VarB = l5Var.b(j5Var);
                v5 v5VarO = w5.o(b6VarB.f());
                v5VarO.c();
                ((w5) v5VarO.f13530l).x(i10);
                b6VarB.g((w5) v5VarO.e());
                z4Var.a((c6) b6VarB.e(), 230);
                break;
            default:
                androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(10, 2);
                androidx.core.provider.e eVar = (androidx.core.provider.e) obj;
                f0Var.f1568n = Boolean.valueOf(((c0) eVar.f2142n).a());
                eVar.a(new k1(f0Var));
                b bVarB = eVar.b();
                r rVar = new r(new androidx.leanback.widget.r(i10));
                rVar.f13572c = bVarB.f13179h;
                bVarB.f13174c.add(rVar);
                break;
        }
    }

    private final void a(int i10, SessionState sessionState) {
    }
}
