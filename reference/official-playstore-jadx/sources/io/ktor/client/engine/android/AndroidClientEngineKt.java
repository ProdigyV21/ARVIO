package io.ktor.client.engine.android;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.HttpMethod;
import java.util.List;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\"\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Ljava/io/OutputStream;", "stream", "Ld7/j;", "callContext", "Lx6/t0;", "writeTo", "(Lio/ktor/http/content/OutgoingContent;Ljava/io/OutputStream;Ld7/j;Ld7/d;)Ljava/lang/Object;", "", "Lio/ktor/http/HttpMethod;", "METHODS_WITHOUT_BODY", "Ljava/util/List;", "ktor-client-android"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidClientEngineKt {
    private static final List<HttpMethod> METHODS_WITHOUT_BODY;

    /* JADX INFO: renamed from: io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.engine.android.AndroidClientEngineKt", f = "AndroidClientEngine.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 123}, m = "writeTo")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidClientEngineKt.writeTo(null, null, null, this);
        }
    }

    static {
        HttpMethod.Companion companion = HttpMethod.INSTANCE;
        METHODS_WITHOUT_BODY = a.E(companion.getGet(), companion.getHead());
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a2, code lost:
    
        if (io.ktor.utils.io.jvm.javaio.WritingKt.copyTo$default(r0, r1, 0, r4, 2, null) == r8) goto L44;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARN: Type inference failed for: r15v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.OutputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeTo(io.ktor.http.content.OutgoingContent r15, java.io.OutputStream r16, d7.j r17, d7.d<? super x6.t0> r18) {
        /*
            r1 = r16
            r0 = r18
            boolean r2 = r0 instanceof io.ktor.client.engine.android.AndroidClientEngineKt.AnonymousClass1
            if (r2 == 0) goto L18
            r2 = r0
            io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$1 r2 = (io.ktor.client.engine.android.AndroidClientEngineKt.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L18
            int r3 = r3 - r4
            r2.label = r3
        L16:
            r4 = r2
            goto L1e
        L18:
            io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$1 r2 = new io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$1
            r2.<init>(r0)
            goto L16
        L1e:
            java.lang.Object r0 = r4.result
            int r2 = r4.label
            r3 = 2
            r5 = 1
            r7 = 0
            if (r2 == 0) goto L49
            if (r2 == r5) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r15 = r4.L$0
            java.io.Closeable r15 = (java.io.Closeable) r15
            k2.c.G(r0)     // Catch: java.lang.Throwable -> L34
            goto Laf
        L34:
            r0 = move-exception
            r1 = r15
        L36:
            r15 = r0
            goto Lbb
        L39:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L41:
            java.lang.Object r15 = r4.L$0
            java.io.Closeable r15 = (java.io.Closeable) r15
            k2.c.G(r0)     // Catch: java.lang.Throwable -> L34
            goto L78
        L49:
            k2.c.G(r0)
            boolean r0 = r15 instanceof io.ktor.http.content.OutgoingContent.ByteArrayContent     // Catch: java.lang.Throwable -> La5
            if (r0 == 0) goto L5c
            io.ktor.http.content.OutgoingContent$ByteArrayContent r15 = (io.ktor.http.content.OutgoingContent.ByteArrayContent) r15     // Catch: java.lang.Throwable -> L5a
            byte[] r15 = r15.getContent()     // Catch: java.lang.Throwable -> L5a
            r1.write(r15)     // Catch: java.lang.Throwable -> L5a
            goto Lad
        L5a:
            r0 = move-exception
            goto L36
        L5c:
            boolean r0 = r15 instanceof io.ktor.http.content.OutgoingContent.ReadChannelContent     // Catch: java.lang.Throwable -> La5
            e7.a r8 = e7.a.f15033i
            if (r0 == 0) goto L7e
            io.ktor.http.content.OutgoingContent$ReadChannelContent r15 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r15     // Catch: java.lang.Throwable -> L5a
            io.ktor.utils.io.ByteReadChannel r0 = r15.readFrom()     // Catch: java.lang.Throwable -> L5a
            r4.L$0 = r1     // Catch: java.lang.Throwable -> L5a
            r4.label = r5     // Catch: java.lang.Throwable -> L5a
            r2 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r0 = io.ktor.utils.io.jvm.javaio.WritingKt.copyTo$default(r0, r1, r2, r4, r5, r6)     // Catch: java.lang.Throwable -> L5a
            if (r0 != r8) goto L77
            goto La4
        L77:
            r15 = r1
        L78:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch: java.lang.Throwable -> L34
            r0.longValue()     // Catch: java.lang.Throwable -> L34
            goto Laf
        L7e:
            boolean r0 = r15 instanceof io.ktor.http.content.OutgoingContent.WriteChannelContent     // Catch: java.lang.Throwable -> La5
            if (r0 == 0) goto La9
            ka.n1 r9 = ka.n1.f19624i     // Catch: java.lang.Throwable -> La5
            io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$2$channel$1 r12 = new io.ktor.client.engine.android.AndroidClientEngineKt$writeTo$2$channel$1     // Catch: java.lang.Throwable -> La5
            r12.<init>(r15, r7)     // Catch: java.lang.Throwable -> La5
            r13 = 2
            r14 = 0
            r11 = 0
            r10 = r17
            io.ktor.utils.io.WriterJob r15 = io.ktor.utils.io.CoroutinesKt.writer$default(r9, r10, r11, r12, r13, r14)     // Catch: java.lang.Throwable -> La5
            io.ktor.utils.io.ByteReadChannel r0 = r15.getChannel()     // Catch: java.lang.Throwable -> La5
            r4.L$0 = r1     // Catch: java.lang.Throwable -> La5
            r4.label = r3     // Catch: java.lang.Throwable -> La5
            r2 = 0
            r5 = 2
            r6 = 0
            java.lang.Object r15 = io.ktor.utils.io.jvm.javaio.WritingKt.copyTo$default(r0, r1, r2, r4, r5, r6)     // Catch: java.lang.Throwable -> La5
            if (r15 != r8) goto Lad
        La4:
            return r8
        La5:
            r0 = move-exception
            r1 = r16
            goto L36
        La9:
            boolean r0 = r15 instanceof io.ktor.http.content.OutgoingContent.NoContent     // Catch: java.lang.Throwable -> La5
            if (r0 == 0) goto Lb5
        Lad:
            r15 = r16
        Laf:
            xc.d.L(r15, r7)
            x6.t0 r15 = x6.t0.f22605a
            return r15
        Lb5:
            io.ktor.client.call.UnsupportedContentTypeException r0 = new io.ktor.client.call.UnsupportedContentTypeException     // Catch: java.lang.Throwable -> La5
            r0.<init>(r15)     // Catch: java.lang.Throwable -> La5
            throw r0     // Catch: java.lang.Throwable -> La5
        Lbb:
            throw r15     // Catch: java.lang.Throwable -> Lbc
        Lbc:
            r0 = move-exception
            xc.d.L(r1, r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.android.AndroidClientEngineKt.writeTo(io.ktor.http.content.OutgoingContent, java.io.OutputStream, d7.j, d7.d):java.lang.Object");
    }
}
