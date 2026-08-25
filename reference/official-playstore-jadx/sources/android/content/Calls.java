package android.content;

import d7.d;
import gb.i;
import gb.p0;
import ka.l;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: renamed from: coil.util.-Calls, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"Lgb/i;", "Lgb/p0;", "await", "(Lgb/i;Ld7/d;)Ljava/lang/Object;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Calls {
    public static final Object await(i iVar, d<? super p0> dVar) {
        l lVar = new l(1, a.A(dVar));
        lVar.s();
        ContinuationCallback continuationCallback = new ContinuationCallback(iVar, lVar);
        iVar.K0(continuationCallback);
        lVar.c(continuationCallback);
        return lVar.r();
    }
}
