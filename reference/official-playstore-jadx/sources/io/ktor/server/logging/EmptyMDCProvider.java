package io.ktor.server.logging;

import d7.d;
import e7.a;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u001c\u0010\n\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/ktor/server/logging/EmptyMDCProvider;", "Lio/ktor/server/logging/MDCProvider;", "<init>", "()V", "Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "withMDCBlock", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;Ld7/d;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class EmptyMDCProvider implements MDCProvider {
    public static final EmptyMDCProvider INSTANCE = new EmptyMDCProvider();

    private EmptyMDCProvider() {
    }

    @Override // io.ktor.server.logging.MDCProvider
    public Object withMDCBlock(ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) {
        Object objInvoke = lVar.invoke(dVar);
        return objInvoke == a.f15033i ? objInvoke : t0.f22605a;
    }
}
