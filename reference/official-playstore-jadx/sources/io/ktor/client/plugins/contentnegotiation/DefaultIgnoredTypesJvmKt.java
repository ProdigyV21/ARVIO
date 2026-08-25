package io.ktor.client.plugins.contentnegotiation;

import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.r;
import kotlin.jvm.internal.l0;
import kotlin.reflect.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\"$\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "Lkotlin/reflect/d;", "DefaultIgnoredTypes", "Ljava/util/Set;", "getDefaultIgnoredTypes", "()Ljava/util/Set;", "ktor-client-content-negotiation"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultIgnoredTypesJvmKt {
    private static final Set<d<?>> DefaultIgnoredTypes;

    static {
        d[] dVarArr = {l0.f19747a.b(InputStream.class)};
        LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(1));
        r.m0(dVarArr, linkedHashSet);
        DefaultIgnoredTypes = linkedHashSet;
    }

    public static final Set<d<?>> getDefaultIgnoredTypes() {
        return DefaultIgnoredTypes;
    }
}
