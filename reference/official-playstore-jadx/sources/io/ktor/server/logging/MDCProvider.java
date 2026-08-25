package io.ktor.server.logging;

import d7.d;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J9\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/server/logging/MDCProvider;", "", "Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "block", "withMDCBlock", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;Ld7/d;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MDCProvider {
    Object withMDCBlock(ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar);
}
