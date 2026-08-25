package io.ktor.server.application.internal;

import a8.f0;
import g8.b1;
import g8.h;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.reflect.d;
import kotlin.reflect.q;
import kotlin.reflect.t;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"", "T", "Lkotlin/reflect/d;", "klass", "Lkotlin/reflect/q;", "starProjectedTypeBridge", "(Lkotlin/reflect/d;)Lkotlin/reflect/q;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TypeUtilsJvmKt {
    public static final <T> q starProjectedTypeBridge(d<T> dVar) {
        h descriptor;
        f0 f0Var = dVar instanceof f0 ? (f0) dVar : null;
        if (f0Var == null || (descriptor = f0Var.getDescriptor()) == null) {
            return xc.d.Z(dVar, null, 7);
        }
        List<b1> parameters = descriptor.g().getParameters();
        if (parameters.isEmpty()) {
            return xc.d.Z(dVar, null, 7);
        }
        ArrayList arrayList = new ArrayList(s.U(parameters, 10));
        for (b1 b1Var : parameters) {
            arrayList.add(t.f19910c);
        }
        return xc.d.Z(dVar, arrayList, 6);
    }
}
