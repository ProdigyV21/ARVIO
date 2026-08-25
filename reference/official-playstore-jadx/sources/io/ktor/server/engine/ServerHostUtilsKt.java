package io.ktor.server.engine;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.reflect.g;
import x6.o;
import z7.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lx6/o;", "", "methodName", "(Lx6/o;)Ljava/lang/String;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ServerHostUtilsKt {
    public static final String methodName(o<?> oVar) {
        Method methodC;
        g gVar = oVar instanceof g ? (g) oVar : null;
        if (gVar == null || (methodC = d.c(gVar)) == null) {
            return oVar.getClass().getName().concat(".invoke");
        }
        Class<?> declaringClass = methodC.getDeclaringClass();
        return declaringClass.getName() + '.' + methodC.getName();
    }
}
