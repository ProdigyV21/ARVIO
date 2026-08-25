package io.ktor.network.util;

import d7.d;
import io.ktor.http.ContentDisposition;
import io.ktor.util.date.DateJvmKt;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001aV\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a-\u0010\u0011\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f*\u0004\u0018\u00010\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0080\bø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0013\u001a\u00020\u00038\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"Lka/k0;", "", ContentDisposition.Parameters.Name, "", "timeoutMs", "Lkotlin/Function0;", "clock", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "onTimeout", "Lio/ktor/network/util/Timeout;", "createTimeout", "(Lka/k0;Ljava/lang/String;JLr7/a;Lr7/l;)Lio/ktor/network/util/Timeout;", "T", "block", "withTimeout", "(Lio/ktor/network/util/Timeout;Lr7/a;)Ljava/lang/Object;", "INFINITE_TIMEOUT_MS", "J", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    public static final long INFINITE_TIMEOUT_MS = Long.MAX_VALUE;

    /* JADX INFO: renamed from: io.ktor.network.util.UtilsKt$createTimeout$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Long> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(DateJvmKt.getTimeMillis());
        }
    }

    public static final Timeout createTimeout(k0 k0Var, String str, long j10, a<Long> aVar, l<? super d<? super t0>, ? extends Object> lVar) {
        return new Timeout(str, j10, aVar, k0Var, lVar);
    }

    public static /* synthetic */ Timeout createTimeout$default(k0 k0Var, String str, long j10, a aVar, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = "";
        }
        if ((i10 & 4) != 0) {
            aVar = AnonymousClass1.INSTANCE;
        }
        return createTimeout(k0Var, str, j10, aVar, lVar);
    }

    public static final <T> T withTimeout(Timeout timeout, a<? extends T> aVar) {
        if (timeout == null) {
            return (T) aVar.invoke();
        }
        timeout.start();
        try {
            return (T) aVar.invoke();
        } finally {
            timeout.stop();
        }
    }
}
