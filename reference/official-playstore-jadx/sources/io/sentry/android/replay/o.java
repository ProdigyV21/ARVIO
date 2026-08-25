package io.sentry.android.replay;

import io.sentry.a1;
import io.sentry.e4;
import java.util.ArrayList;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class o implements e4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16993i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k0 f16994l;

    public /* synthetic */ o(k0 k0Var, int i10) {
        this.f16993i = i10;
        this.f16994l = k0Var;
    }

    @Override // io.sentry.e4
    public final void e(a1 a1Var) {
        int i10 = this.f16993i;
        k0 k0Var = this.f16994l;
        switch (i10) {
            case 0:
                int i11 = ReplayIntegration.B;
                String strX = a1Var.x();
                k0Var.f19746i = strX != null ? kotlin.text.o.E0('.', strX, strX) : null;
                break;
            default:
                k0Var.f19746i = new ArrayList(a1Var.s());
                break;
        }
    }
}
