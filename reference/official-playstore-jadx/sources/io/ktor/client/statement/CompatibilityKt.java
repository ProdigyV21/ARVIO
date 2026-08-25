package io.ktor.client.statement;

import d7.d;
import io.ktor.http.ContentDisposition;
import java.nio.charset.Charset;
import kotlin.Metadata;
import r7.p;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001f\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001aZ\u0010\u0002\u001a\u00028\u0001\"\u0006\b\u0000\u0010\u0000\u0018\u0001\"\u0004\b\u0001\u0010\u0004*\u00020\u000123\b\u0004\u0010\u000b\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0005H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\f\u001a)\u0010\u0012\u001a\u00020\u0011*\u00020\r2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"T", "Lio/ktor/client/statement/HttpStatement;", "receive", "(Lio/ktor/client/statement/HttpStatement;Ld7/d;)Ljava/lang/Object;", "R", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "response", "Ld7/d;", "", "block", "(Lio/ktor/client/statement/HttpStatement;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpResponse;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "fallbackCharset", "", "readText", "(Lio/ktor/client/statement/HttpResponse;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompatibilityKt {
    @e
    public static final Object readText(HttpResponse httpResponse, Charset charset, d<? super String> dVar) {
        throw new IllegalStateException("Use `bodyAsText` method instead");
    }

    public static /* synthetic */ Object readText$default(HttpResponse httpResponse, Charset charset, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = null;
        }
        return readText(httpResponse, charset, dVar);
    }

    @e
    public static final /* synthetic */ <T> Object receive(HttpStatement httpStatement, d<? super T> dVar) {
        throw new IllegalStateException("Use `body` method instead");
    }

    @e
    public static final /* synthetic */ <T, R> Object receive(HttpStatement httpStatement, p<? super T, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        throw new IllegalStateException("Use `body` method instead");
    }
}
