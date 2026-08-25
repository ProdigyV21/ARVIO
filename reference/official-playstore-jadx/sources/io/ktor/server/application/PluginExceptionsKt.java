package io.ktor.server.application;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"noBinaryDataException", "Lio/ktor/server/application/InvalidBodyException;", "expectedTypeName", "", "subject", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PluginExceptionsKt {
    /* JADX WARN: Removed duplicated region for block: B:6:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.server.application.InvalidBodyException noBinaryDataException(java.lang.String r2, java.lang.Object r3) {
        /*
            java.lang.String r0 = "Expected "
            java.lang.String r1 = " type but "
            java.lang.StringBuilder r2 = a0.c.t(r0, r2, r1)
            if (r3 == 0) goto L1a
            java.lang.Class r3 = r3.getClass()
            kotlin.jvm.internal.m0 r0 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r3 = r0.b(r3)
            java.lang.String r3 = r3.r()
            if (r3 != 0) goto L1c
        L1a:
            java.lang.String r3 = "null"
        L1c:
            java.lang.String r0 = " found"
            java.lang.String r2 = a0.c.p(r2, r3, r0)
            io.ktor.server.application.InvalidBodyException r3 = new io.ktor.server.application.InvalidBodyException
            r3.<init>(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.PluginExceptionsKt.noBinaryDataException(java.lang.String, java.lang.Object):io.ktor.server.application.InvalidBodyException");
    }
}
