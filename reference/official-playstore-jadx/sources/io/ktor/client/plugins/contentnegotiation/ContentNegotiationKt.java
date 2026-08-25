package io.ktor.client.plugins.contentnegotiation;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.d;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0018\u0010\u0002\u001a\u00060\u0000j\u0002`\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0003\"$\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "", "Lkotlin/reflect/d;", "DefaultCommonIgnoredTypes", "Ljava/util/Set;", "getDefaultCommonIgnoredTypes", "()Ljava/util/Set;", "ktor-client-content-negotiation"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentNegotiationKt {
    private static final Set<d<?>> DefaultCommonIgnoredTypes;
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.contentnegotiation.ContentNegotiation");

    static {
        m0 m0Var = l0.f19747a;
        DefaultCommonIgnoredTypes = r.p0(new d[]{m0Var.b(byte[].class), m0Var.b(String.class), m0Var.b(HttpStatusCode.class), m0Var.b(ByteReadChannel.class), m0Var.b(OutgoingContent.class)});
    }

    public static final Set<d<?>> getDefaultCommonIgnoredTypes() {
        return DefaultCommonIgnoredTypes;
    }
}
