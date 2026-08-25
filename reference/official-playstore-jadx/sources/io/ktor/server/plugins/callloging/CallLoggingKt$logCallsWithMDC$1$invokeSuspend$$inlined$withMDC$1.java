package io.ktor.server.plugins.callloging;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.util.List;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V", "io/ktor/server/plugins/callloging/MDCEntryUtilsKt$withMDC$2"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1", f = "CallLogging.kt", l = {21}, m = "invokeSuspend")
public final class CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ l $block;
    final /* synthetic */ List $mdcEntries;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1(l lVar, List list, d dVar) {
        super(2, dVar);
        this.$block = lVar;
        this.$mdcEntries = list;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1(this.$block, this.$mdcEntries, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                c.G(obj);
                l lVar = this.$block;
                this.label = 1;
                Object objInvoke = lVar.invoke(this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            return t0.f22605a;
        } catch (Throwable th) {
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            throw th;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
