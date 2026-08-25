package io.ktor.server.plugins.callloging;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import java.util.List;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V", "io/ktor/server/plugins/callloging/MDCEntryUtilsKt$withMDC$2"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1", f = "CallLogging.kt", l = {}, m = "invokeSuspend")
public final class CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ ApplicationCall $call$inlined;
    final /* synthetic */ l $logSuccess$inlined;
    final /* synthetic */ List $mdcEntries;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(List list, d dVar, l lVar, ApplicationCall applicationCall) {
        super(2, dVar);
        this.$mdcEntries = list;
        this.$logSuccess$inlined = lVar;
        this.$call$inlined = applicationCall;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(this.$mdcEntries, dVar, this.$logSuccess$inlined, this.$call$inlined);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        try {
            this.$logSuccess$inlined.invoke(this.$call$inlined);
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            return t0.f22605a;
        } catch (Throwable th) {
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            throw th;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
