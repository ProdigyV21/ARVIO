package io.sentry.android.replay;

import io.sentry.a7;
import io.sentry.w5;
import io.sentry.y6;
import java.io.File;
import java.io.Serializable;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16975i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f16976l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16977m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Serializable f16978n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(long j10, Object obj, Serializable serializable, int i10) {
        super(1);
        this.f16975i = i10;
        this.f16976l = j10;
        this.f16977m = obj;
        this.f16978n = serializable;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f16975i;
        Serializable serializable = this.f16978n;
        long j10 = this.f16976l;
        Object obj2 = this.f16977m;
        switch (i10) {
            case 0:
                n nVar = (n) obj;
                if (nVar.f16991b >= j10) {
                    k0 k0Var = (k0) serializable;
                    if (k0Var.f19746i == null) {
                        k0Var.f19746i = nVar.f16992c;
                    }
                } else {
                    ((m) obj2).j(nVar.f16990a);
                }
                break;
            default:
                io.sentry.android.replay.capture.f fVar = (io.sentry.android.replay.capture.f) obj2;
                a7 a7Var = ((io.sentry.android.replay.capture.i) obj).f16918a;
                if (a7Var.E.getTime() < j10) {
                    fVar.f(fVar.h() - 1);
                    File file = a7Var.z;
                    y6 y6Var = fVar.f16913r;
                    if (file != null) {
                        try {
                            if (!file.delete()) {
                                y6Var.getLogger().q(w5.ERROR, "Failed to delete replay segment: %s", file.getAbsolutePath());
                            }
                        } catch (Throwable th) {
                            y6Var.getLogger().k(w5.ERROR, th, "Failed to delete replay segment: %s", file.getAbsolutePath());
                        }
                    }
                    ((kotlin.jvm.internal.f0) serializable).f19738i = true;
                    break;
                }
                break;
        }
        return Boolean.FALSE;
    }
}
