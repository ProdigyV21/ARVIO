package io.ktor.server.plugins.callloging;

import d7.d;
import e7.a;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.logging.MDCProvider;
import io.ktor.util.AttributeKey;
import java.util.List;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import ta.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J9\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/server/plugins/callloging/KtorMDCProvider;", "Lio/ktor/server/logging/MDCProvider;", "", "Lio/ktor/server/plugins/callloging/MDCEntry;", "entries", "<init>", "(Ljava/util/List;)V", "Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "withMDCBlock", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ljava/util/List;", "Companion", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KtorMDCProvider implements MDCProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<KtorMDCProvider> key = new AttributeKey<>("KtorMDCProvider");
    private final List<MDCEntry> entries;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/plugins/callloging/KtorMDCProvider$Companion;", "", "()V", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/plugins/callloging/KtorMDCProvider;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final AttributeKey<KtorMDCProvider> getKey() {
            return KtorMDCProvider.key;
        }

        private Companion() {
        }
    }

    public KtorMDCProvider(List<MDCEntry> list) {
        this.entries = list;
    }

    @Override // io.ktor.server.logging.MDCProvider
    public Object withMDCBlock(ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) throws Throwable {
        List<MDCEntry> list = this.entries;
        Object objY = m0.y(new b(MDCEntryUtilsKt.setup(list, applicationCall)), new KtorMDCProvider$withMDCBlock$$inlined$withMDC$1(lVar, list, null), dVar);
        return objY == a.f15033i ? objY : t0.f22605a;
    }
}
