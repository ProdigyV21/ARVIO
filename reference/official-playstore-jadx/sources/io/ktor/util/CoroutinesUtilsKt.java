package io.ktor.util;

import d7.h;
import d7.j;
import java.util.Iterator;
import ka.g0;
import ka.p2;
import ka.v1;
import kotlin.Metadata;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lka/v1;", "", "offset", "Lx6/t0;", "printDebugTree", "(Lka/v1;I)V", "parent", "Ld7/j;", "SilentSupervisor", "(Lka/v1;)Ld7/j;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoroutinesUtilsKt {
    public static final j SilentSupervisor(v1 v1Var) {
        return h.a(new p2(v1Var), new CoroutinesUtilsKt$SilentSupervisor$$inlined$CoroutineExceptionHandler$1(g0.f19591i));
    }

    public static /* synthetic */ j SilentSupervisor$default(v1 v1Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            v1Var = null;
        }
        return SilentSupervisor(v1Var);
    }

    public static final void printDebugTree(v1 v1Var, int i10) {
        System.out.println((Object) (u.N(i10, " ") + v1Var));
        Iterator it = v1Var.getChildren().iterator();
        while (it.hasNext()) {
            printDebugTree((v1) it.next(), i10 + 2);
        }
        if (i10 == 0) {
            System.out.println();
        }
    }

    public static /* synthetic */ void printDebugTree$default(v1 v1Var, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 0;
        }
        printDebugTree(v1Var, i10);
    }
}
