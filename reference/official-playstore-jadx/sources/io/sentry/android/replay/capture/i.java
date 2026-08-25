package io.sentry.android.replay.capture;

import io.sentry.a7;
import io.sentry.c1;
import io.sentry.j0;
import io.sentry.y3;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a7 f16918a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y3 f16919b;

    public i(a7 a7Var, y3 y3Var) {
        this.f16918a = a7Var;
        this.f16919b = y3Var;
    }

    public static void a(i iVar, c1 c1Var) {
        j0 j0Var = new j0();
        if (c1Var == null) {
            iVar.getClass();
            return;
        }
        a7 a7Var = iVar.f16918a;
        j0Var.f17396g = iVar.f16919b;
        c1Var.r(a7Var, j0Var);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return p.a(this.f16918a, iVar.f16918a) && p.a(this.f16919b, iVar.f16919b);
    }

    public final int hashCode() {
        return this.f16919b.hashCode() + (this.f16918a.hashCode() * 31);
    }

    public final String toString() {
        return "Created(replay=" + this.f16918a + ", recording=" + this.f16919b + ')';
    }
}
