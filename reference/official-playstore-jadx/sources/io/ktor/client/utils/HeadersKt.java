package io.ktor.client.utils;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0006\u001a\u00020\u00052\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/Function1;", "Lio/ktor/http/HeadersBuilder;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/http/Headers;", "buildHeaders", "(Lr7/l;)Lio/ktor/http/Headers;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeadersKt {

    /* JADX INFO: renamed from: io.ktor.client.utils.HeadersKt$buildHeaders$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/HeadersBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/HeadersBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<HeadersBuilder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(HeadersBuilder headersBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HeadersBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final Headers buildHeaders(l<? super HeadersBuilder, t0> lVar) {
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        lVar.invoke(headersBuilder);
        return headersBuilder.build();
    }

    public static /* synthetic */ Headers buildHeaders$default(l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return buildHeaders(lVar);
    }
}
