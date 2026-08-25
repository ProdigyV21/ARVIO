package io.ktor.http.cio;

import androidx.compose.runtime.ComposerKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import d7.d;
import d7.k;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.LookAheadSession;
import io.ktor.utils.io.LookAheadSuspendSession;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import ka.c0;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import kotlin.text.a;
import kotlin.text.o;
import ma.t;
import ma.u;
import ma.w;
import org.jsoup.helper.HttpConnection;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\u001a5\u0010\b\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a5\u0010\n\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\t\u001aA\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000b2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001b\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0011\u001a=\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a=\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0015\u001a#\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0019\u001a\u0017\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\rH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a'\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e*\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\r¢\u0006\u0004\b \u0010!\u001a1\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e*\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b \u0010%\u001a3\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e*\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b \u0010'\u001aY\u0010/\u001a\u00020\u00062\u0006\u0010)\u001a\u00020(2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\"\u0010.\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0+\u0012\u0006\u0012\u0004\u0018\u00010-0*2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0017\u00102\u001a\u0002012\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b2\u00103\u001a\u0017\u00104\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b4\u00105\u001a\u0017\u00106\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"H\u0000¢\u0006\u0004\b6\u00105\u001a\u001f\u00108\u001a\u00020\u0017*\u00020\u00022\u0006\u00107\u001a\u00020\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a\u001f\u0010:\u001a\u00020\u0017*\u00020\u00022\u0006\u00107\u001a\u00020\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b:\u00109\u001a\u001b\u0010<\u001a\u000201*\u00020;2\u0006\u00107\u001a\u00020\u0000H\u0002¢\u0006\u0004\b<\u0010=\u001a%\u0010@\u001a\u00020\u0017*\u00020\u00002\u0006\u0010>\u001a\u00020\u00002\b\b\u0002\u0010?\u001a\u000201H\u0002¢\u0006\u0004\b@\u0010A\u001a\u001b\u0010B\u001a\u000201*\u00020;2\u0006\u00107\u001a\u00020\u0000H\u0002¢\u0006\u0004\bB\u0010=\u001a\u001b\u0010D\u001a\u000201*\u00020\u00002\u0006\u0010C\u001a\u00020\u0000H\u0002¢\u0006\u0004\bD\u0010E\"\u0014\u0010F\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010G\"\u0014\u0010H\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010G\"\u0014\u0010J\u001a\u00020I8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010K\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006L"}, d2 = {"Ljava/nio/ByteBuffer;", "boundaryPrefixed", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/core/BytePacketBuilder;", "output", "", "limit", "parsePreamble", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/BytePacketBuilder;JLd7/d;)Ljava/lang/Object;", "parsePreambleImpl", "Lio/ktor/utils/io/ByteWriteChannel;", "Lx6/x;", "Lio/ktor/http/cio/HttpHeadersMap;", "parsePart", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "parsePartHeaders", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "parsePartHeadersImpl", "headers", "parsePartBody", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/http/cio/HttpHeadersMap;JLd7/d;)Ljava/lang/Object;", "parsePartBodyImpl", "", "boundary", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "skipBoundary", "expectMultipart", "(Lio/ktor/http/cio/HttpHeadersMap;)Z", "Lka/k0;", "Lma/w;", "Lio/ktor/http/cio/MultipartEvent;", "parseMultipart", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/http/cio/HttpHeadersMap;)Lma/w;", "", "contentType", "contentLength", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/CharSequence;Ljava/lang/Long;)Lma/w;", "totalLength", "(Lka/k0;Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Long;)Lma/w;", "", ContentDisposition.Parameters.Name, "Lkotlin/Function2;", "Ld7/d;", "Lx6/t0;", "", "writeFully", "copyUntilBoundary", "(Ljava/lang/String;Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lr7/p;JLd7/d;)Ljava/lang/Object;", "", "findBoundary", "(Ljava/lang/CharSequence;)I", "parseBoundary", "(Ljava/lang/CharSequence;)Ljava/nio/ByteBuffer;", "parseBoundaryInternal", TtmlNode.RUBY_DELIMITER, "skipDelimiterOrEof", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "trySkipDelimiterSuspend", "Lio/ktor/utils/io/LookAheadSession;", "tryEnsureDelimiter", "(Lio/ktor/utils/io/LookAheadSession;Ljava/nio/ByteBuffer;)I", "prefix", "prefixSkip", "startsWith", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;I)Z", "startsWithDelimiter", "sub", "indexOfPartial", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)I", "CrLf", "Ljava/nio/ByteBuffer;", "BoundaryTrailingBuffer", "", "PrefixChar", "B", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultipartKt {
    private static final ByteBuffer BoundaryTrailingBuffer;
    private static final ByteBuffer CrLf;
    private static final byte PrefixChar = 45;

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$copyUntilBoundary$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {368, 371}, m = "copyUntilBoundary")
    public static final class AnonymousClass1 extends c {
        int I$0;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.copyUntilBoundary(null, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$parseMultipart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lma/u;", "Lio/ktor/http/cio/MultipartEvent;", "Lx6/t0;", "<anonymous>", "(Lma/u;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.MultipartKt$parseMultipart$1", f = "Multipart.kt", l = {289, 292, 295, 302, 303, 306, MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST, MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 320, 330, 333, 342, 342, 345, 347}, m = "invokeSuspend")
    public static final class C18241 extends j implements p<u<? super MultipartEvent>, d<? super t0>, Object> {
        final /* synthetic */ ByteBuffer $boundaryPrefixed;
        final /* synthetic */ ByteReadChannel $input;
        final /* synthetic */ Long $totalLength;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18241(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Long l10, d<? super C18241> dVar) {
            super(2, dVar);
            this.$input = byteReadChannel;
            this.$boundaryPrefixed = byteBuffer;
            this.$totalLength = l10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18241 c18241 = new C18241(this.$input, this.$boundaryPrefixed, this.$totalLength, dVar);
            c18241.L$0 = obj;
            return c18241;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0184, code lost:
        
            if (r4 == r12) goto L113;
         */
        /* JADX WARN: Removed duplicated region for block: B:106:0x02fc  */
        /* JADX WARN: Removed duplicated region for block: B:111:0x0319  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x032c  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x033b  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x035e  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x008c A[PHI: r0 r2 r3 r4 r5 r6 r7
          0x008c: PHI (r0v23 long) = (r0v17 long), (r0v35 long) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r2v34 java.nio.ByteBuffer) = (r2v29 java.nio.ByteBuffer), (r2v37 java.nio.ByteBuffer) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r3v8 io.ktor.http.cio.MultipartKt$parseMultipart$1) = (r3v0 io.ktor.http.cio.MultipartKt$parseMultipart$1), (r3v12 io.ktor.http.cio.MultipartKt$parseMultipart$1) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r4v35 ka.r) = (r4v28 ka.r), (r4v40 ka.r) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r5v18 io.ktor.utils.io.ByteChannel) = (r5v11 io.ktor.utils.io.ByteChannel), (r5v24 io.ktor.utils.io.ByteChannel) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r6v9 ma.u) = (r6v5 ma.u), (r6v13 ma.u) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]
          0x008c: PHI (r7v8 java.lang.Object) = (r7v4 java.lang.Object), (r7v15 java.lang.Object) binds: [B:21:0x008a, B:68:0x0228] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x014f  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0172  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x01b6 A[PHI: r0 r2 r3 r4
          0x01b6: PHI (r0v39 long) = (r0v13 long), (r0v40 long) binds: [B:29:0x00d4, B:53:0x01b2] A[DONT_GENERATE, DONT_INLINE]
          0x01b6: PHI (r2v40 java.nio.ByteBuffer) = (r2v18 java.nio.ByteBuffer), (r2v41 java.nio.ByteBuffer) binds: [B:29:0x00d4, B:53:0x01b2] A[DONT_GENERATE, DONT_INLINE]
          0x01b6: PHI (r3v15 io.ktor.http.cio.MultipartKt$parseMultipart$1) = (r3v0 io.ktor.http.cio.MultipartKt$parseMultipart$1), (r3v16 io.ktor.http.cio.MultipartKt$parseMultipart$1) binds: [B:29:0x00d4, B:53:0x01b2] A[DONT_GENERATE, DONT_INLINE]
          0x01b6: PHI (r4v46 ma.u) = (r4v19 ma.u), (r4v47 ma.u) binds: [B:29:0x00d4, B:53:0x01b2] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x01cd A[PHI: r0 r2 r3 r4 r5
          0x01cd: PHI (r0v37 long) = (r0v14 long), (r0v39 long) binds: [B:28:0x00c3, B:56:0x01c9] A[DONT_GENERATE, DONT_INLINE]
          0x01cd: PHI (r2v39 java.nio.ByteBuffer) = (r2v20 java.nio.ByteBuffer), (r2v40 java.nio.ByteBuffer) binds: [B:28:0x00c3, B:56:0x01c9] A[DONT_GENERATE, DONT_INLINE]
          0x01cd: PHI (r3v14 io.ktor.http.cio.MultipartKt$parseMultipart$1) = (r3v0 io.ktor.http.cio.MultipartKt$parseMultipart$1), (r3v15 io.ktor.http.cio.MultipartKt$parseMultipart$1) binds: [B:28:0x00c3, B:56:0x01c9] A[DONT_GENERATE, DONT_INLINE]
          0x01cd: PHI (r4v45 ma.u) = (r4v21 ma.u), (r4v46 ma.u) binds: [B:28:0x00c3, B:56:0x01c9] A[DONT_GENERATE, DONT_INLINE]
          0x01cd: PHI (r5v27 java.lang.Object) = (r5v6 java.lang.Object), (r5v33 java.lang.Object) binds: [B:28:0x00c3, B:56:0x01c9] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01d5  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x020f  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0234 A[Catch: all -> 0x0337, TRY_LEAVE, TryCatch #1 {all -> 0x0337, blocks: (B:71:0x022e, B:73:0x0234), top: B:139:0x022e }] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0285 A[PHI: r0 r2 r3 r4 r7
          0x0285: PHI (r0v41 long) = (r0v22 long), (r0v42 long) binds: [B:81:0x0281, B:13:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x0285: PHI (r2v42 java.nio.ByteBuffer) = (r2v33 java.nio.ByteBuffer), (r2v49 java.nio.ByteBuffer) binds: [B:81:0x0281, B:13:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x0285: PHI (r3v17 io.ktor.http.cio.MultipartKt$parseMultipart$1) = (r3v7 io.ktor.http.cio.MultipartKt$parseMultipart$1), (r3v0 io.ktor.http.cio.MultipartKt$parseMultipart$1) binds: [B:81:0x0281, B:13:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x0285: PHI (r4v48 java.lang.Object) = (r4v34 java.lang.Object), (r4v55 java.lang.Object) binds: [B:81:0x0281, B:13:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x0285: PHI (r7v21 ma.u) = (r7v7 ma.u), (r7v22 ma.u) binds: [B:81:0x0281, B:13:0x0041] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:85:0x028d  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x02b3  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0281 -> B:83:0x0285). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 906
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.C18241.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(u<? super MultipartEvent> uVar, d<? super t0> dVar) {
            return ((C18241) create(uVar, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$parsePart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {115, 117}, m = "parsePart")
    public static final class C18251 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C18251(d<? super C18251> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.parsePart(null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {175, 177}, m = "parsePartBodyImpl")
    public static final class C18261 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18261(d<? super C18261> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.parsePartBodyImpl(null, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {140}, m = "parsePartHeadersImpl")
    public static final class C18271 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18271(d<? super C18271> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.parsePartHeadersImpl(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$parsePreambleImpl$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "<anonymous>", "(Ljava/nio/ByteBuffer;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.MultipartKt$parsePreambleImpl$2", f = "Multipart.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ByteBuffer, d<? super t0>, Object> {
        final /* synthetic */ BytePacketBuilder $output;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(BytePacketBuilder bytePacketBuilder, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$output = bytePacketBuilder;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$output, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            OutputArraysJVMKt.writeFully(this.$output, (ByteBuffer) this.L$0);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ByteBuffer byteBuffer, d<? super t0> dVar) {
            return ((AnonymousClass2) create(byteBuffer, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$skipBoundary$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {197, 203}, m = "skipBoundary")
    public static final class C18281 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18281(d<? super C18281> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.skipBoundary(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$skipBoundary$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/LookAheadSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.MultipartKt$skipBoundary$2", f = "Multipart.kt", l = {ComposerKt.providerMapsKey, 215}, m = "invokeSuspend")
    public static final class C18292 extends j implements p<LookAheadSuspendSession, d<? super t0>, Object> {
        final /* synthetic */ f0 $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18292(f0 f0Var, d<? super C18292> dVar) {
            super(2, dVar);
            this.$result = f0Var;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18292 c18292 = new C18292(this.$result, dVar);
            c18292.L$0 = obj;
            return c18292;
        }

        @Override // r7.p
        public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super t0> dVar) {
            return ((C18292) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
        
            if (r0.awaitAtLeast(2, r8) == r6) goto L27;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.io.IOException {
            /*
                r8 = this;
                int r0 = r8.label
                java.lang.String r1 = "Failed to pass multipart boundary: unexpected end of stream"
                r2 = 45
                x6.t0 r3 = x6.t0.f22605a
                r4 = 2
                r5 = 1
                e7.a r6 = e7.a.f15033i
                if (r0 == 0) goto L2a
                if (r0 == r5) goto L22
                if (r0 != r4) goto L1a
                java.lang.Object r0 = r8.L$0
                io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
                k2.c.G(r9)
                goto L73
            L1a:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L22:
                java.lang.Object r0 = r8.L$0
                io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
                k2.c.G(r9)
                goto L3d
            L2a:
                k2.c.G(r9)
                java.lang.Object r9 = r8.L$0
                io.ktor.utils.io.LookAheadSuspendSession r9 = (io.ktor.utils.io.LookAheadSuspendSession) r9
                r8.L$0 = r9
                r8.label = r5
                java.lang.Object r0 = r9.awaitAtLeast(r5, r8)
                if (r0 != r6) goto L3c
                goto L72
            L3c:
                r0 = r9
            L3d:
                r9 = 0
                java.nio.ByteBuffer r9 = r0.request(r9, r5)
                if (r9 == 0) goto L91
                int r7 = r9.position()
                byte r7 = r9.get(r7)
                if (r7 == r2) goto L4f
                return r3
            L4f:
                int r7 = r9.remaining()
                if (r7 <= r5) goto L68
                int r7 = r9.position()
                int r7 = r7 + r5
                byte r9 = r9.get(r7)
                if (r9 != r2) goto L68
                kotlin.jvm.internal.f0 r9 = r8.$result
                r9.f19738i = r5
                r0.mo7011consumed(r4)
                return r3
            L68:
                r8.L$0 = r0
                r8.label = r4
                java.lang.Object r9 = r0.awaitAtLeast(r4, r8)
                if (r9 != r6) goto L73
            L72:
                return r6
            L73:
                java.nio.ByteBuffer r9 = r0.request(r5, r5)
                if (r9 == 0) goto L8b
                int r1 = r9.position()
                byte r9 = r9.get(r1)
                if (r9 != r2) goto L8a
                kotlin.jvm.internal.f0 r9 = r8.$result
                r9.f19738i = r5
                r0.mo7011consumed(r4)
            L8a:
                return r3
            L8b:
                java.io.IOException r9 = new java.io.IOException
                r9.<init>(r1)
                throw r9
            L91:
                java.io.IOException r9 = new java.io.IOException
                r9.<init>(r1)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.C18292.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$skipDelimiterOrEof$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "Lx6/t0;", "invoke", "(Lio/ktor/utils/io/LookAheadSession;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<LookAheadSession, t0> {
        final /* synthetic */ ByteBuffer $delimiter;
        final /* synthetic */ f0 $found;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(f0 f0Var, ByteBuffer byteBuffer) {
            super(1);
            this.$found = f0Var;
            this.$delimiter = byteBuffer;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LookAheadSession) obj);
            return t0.f22605a;
        }

        public final void invoke(LookAheadSession lookAheadSession) {
            this.$found.f19738i = MultipartKt.tryEnsureDelimiter(lookAheadSession, this.$delimiter) == this.$delimiter.remaining();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", l = {575}, m = "trySkipDelimiterSuspend")
    public static final class C18301 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18301(d<? super C18301> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultipartKt.trySkipDelimiterSuspend(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/LookAheadSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2", f = "Multipart.kt", l = {576, 576}, m = "invokeSuspend")
    public static final class C18312 extends j implements p<LookAheadSuspendSession, d<? super t0>, Object> {
        final /* synthetic */ ByteBuffer $delimiter;
        final /* synthetic */ f0 $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18312(ByteBuffer byteBuffer, f0 f0Var, d<? super C18312> dVar) {
            super(2, dVar);
            this.$delimiter = byteBuffer;
            this.$result = f0Var;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18312 c18312 = new C18312(this.$delimiter, this.$result, dVar);
            c18312.L$0 = obj;
            return c18312;
        }

        @Override // r7.p
        public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super t0> dVar) {
            return ((C18312) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        
            if (r7 == r4) goto L18;
         */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0070 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r6.label
                x6.t0 r1 = x6.t0.f22605a
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L26
                if (r0 == r3) goto L1e
                if (r0 != r2) goto L16
                java.lang.Object r0 = r6.L$0
                io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
                k2.c.G(r7)
                goto L54
            L16:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1e:
                java.lang.Object r0 = r6.L$0
                io.ktor.utils.io.LookAheadSuspendSession r0 = (io.ktor.utils.io.LookAheadSuspendSession) r0
                k2.c.G(r7)
                goto L41
            L26:
                k2.c.G(r7)
                java.lang.Object r7 = r6.L$0
                io.ktor.utils.io.LookAheadSuspendSession r7 = (io.ktor.utils.io.LookAheadSuspendSession) r7
                java.nio.ByteBuffer r0 = r6.$delimiter
                int r0 = r0.remaining()
                r6.L$0 = r7
                r6.label = r3
                java.lang.Object r0 = r7.awaitAtLeast(r0, r6)
                if (r0 != r4) goto L3e
                goto L53
            L3e:
                r5 = r0
                r0 = r7
                r7 = r5
            L41:
                java.lang.Boolean r7 = (java.lang.Boolean) r7
                boolean r7 = r7.booleanValue()
                if (r7 != 0) goto L62
                r6.L$0 = r0
                r6.label = r2
                java.lang.Object r7 = r0.awaitAtLeast(r3, r6)
                if (r7 != r4) goto L54
            L53:
                return r4
            L54:
                java.lang.Boolean r7 = (java.lang.Boolean) r7
                boolean r7 = r7.booleanValue()
                if (r7 != 0) goto L62
                kotlin.jvm.internal.f0 r7 = r6.$result
                r0 = 0
                r7.f19738i = r0
                return r1
            L62:
                java.nio.ByteBuffer r7 = r6.$delimiter
                int r7 = io.ktor.http.cio.MultipartKt.access$tryEnsureDelimiter(r0, r7)
                java.nio.ByteBuffer r0 = r6.$delimiter
                int r0 = r0.remaining()
                if (r7 != r0) goto L71
                return r1
            L71:
                java.io.IOException r7 = new java.io.IOException
                java.lang.String r0 = "Broken delimiter occurred"
                r7.<init>(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.C18312.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    static {
        Charset charset = a.f19924a;
        CrLf = ByteBuffer.wrap(kotlin.jvm.internal.p.a(charset, charset) ? "\r\n".getBytes(charset) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "\r\n", 0, 2));
        BoundaryTrailingBuffer = ByteBuffer.allocate(8192);
    }

    @x6.e
    public static final Object boundary(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, d<? super Boolean> dVar) {
        return skipBoundary(byteBuffer, byteReadChannel, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c7 A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #2 {all -> 0x004f, blocks: (B:13:0x0042, B:27:0x00bf, B:29:0x00c7, B:40:0x0127, B:20:0x0073), top: B:51:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0127 A[Catch: all -> 0x004f, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x004f, blocks: (B:13:0x0042, B:27:0x00bf, B:29:0x00c7, B:40:0x0127, B:20:0x0073), top: B:51:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0100 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00e3 -> B:33:0x00f0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyUntilBoundary(java.lang.String r19, java.nio.ByteBuffer r20, io.ktor.utils.io.ByteReadChannel r21, r7.p<? super java.nio.ByteBuffer, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r22, long r23, d7.d<? super java.lang.Long> r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.copyUntilBoundary(java.lang.String, java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, r7.p, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object copyUntilBoundary$default(String str, ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, p pVar, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyUntilBoundary(str, byteBuffer, byteReadChannel, pVar, j10, dVar);
    }

    @x6.e
    public static final boolean expectMultipart(HttpHeadersMap httpHeadersMap) {
        CharSequence charSequence = httpHeadersMap.get(HttpConnection.CONTENT_TYPE);
        if (charSequence != null) {
            return o.z0(charSequence, "multipart/");
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int findBoundary(java.lang.CharSequence r13) {
        /*
            int r0 = r13.length()
            r1 = 0
            r3 = r1
            r8 = r3
            r9 = r8
        L8:
            if (r3 >= r0) goto L77
            char r2 = r13.charAt(r3)
            r4 = 59
            r5 = 1
            if (r8 == 0) goto L6b
            r6 = 44
            r7 = 2
            if (r8 == r5) goto L40
            r10 = 34
            r11 = 3
            if (r8 == r7) goto L35
            r4 = 4
            if (r8 == r11) goto L28
            if (r8 == r4) goto L23
            goto L3b
        L23:
            r6 = r3
            r8 = r11
        L25:
            r3 = r13
            goto L71
        L28:
            if (r2 != r10) goto L2e
        L2a:
            r9 = r1
            r6 = r3
            r8 = r5
            goto L25
        L2e:
            r5 = 92
            if (r2 != r5) goto L3b
            r6 = r3
            r8 = r4
            goto L25
        L35:
            if (r2 != r10) goto L38
            goto L23
        L38:
            if (r2 != r6) goto L3d
        L3a:
            r8 = r1
        L3b:
            r6 = r3
            goto L25
        L3d:
            if (r2 != r4) goto L3b
            goto L2a
        L40:
            r5 = 61
            if (r2 != r5) goto L47
            r6 = r3
            r8 = r7
            goto L25
        L47:
            if (r2 != r4) goto L4b
            r9 = r1
            goto L3b
        L4b:
            if (r2 != r6) goto L4e
            goto L3a
        L4e:
            r4 = 32
            if (r2 == r4) goto L3b
            if (r9 != 0) goto L66
            r5 = 0
            java.lang.String r4 = "boundary="
            int r6 = r4.length()
            r7 = 1
            r2 = r13
            boolean r13 = kotlin.text.o.q0(r2, r3, r4, r5, r6, r7)
            r6 = r3
            r3 = r2
            if (r13 == 0) goto L68
            return r6
        L66:
            r6 = r3
            r3 = r13
        L68:
            int r9 = r9 + 1
            goto L71
        L6b:
            r6 = r3
            r3 = r13
            if (r2 != r4) goto L71
            r9 = r1
            r8 = r5
        L71:
            int r13 = r6 + 1
            r12 = r3
            r3 = r13
            r13 = r12
            goto L8
        L77:
            r13 = -1
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.findBoundary(java.lang.CharSequence):int");
    }

    private static final int indexOfPartial(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int i10;
        int iPosition = byteBuffer2.position();
        int iRemaining = byteBuffer2.remaining();
        byte b10 = byteBuffer2.get(iPosition);
        int iLimit = byteBuffer.limit();
        loop0: for (int iPosition2 = byteBuffer.position(); iPosition2 < iLimit; iPosition2++) {
            if (byteBuffer.get(iPosition2) == b10) {
                while (i10 < iRemaining) {
                    int i11 = iPosition2 + i10;
                    if (i11 == iLimit) {
                        break loop0;
                    }
                    i10 = byteBuffer.get(i11) == byteBuffer2.get(iPosition + i10) ? i10 + 1 : 1;
                }
                return iPosition2 - byteBuffer.position();
            }
        }
        return -1;
    }

    @x6.e
    public static final ByteBuffer parseBoundary(CharSequence charSequence) {
        return parseBoundaryInternal(charSequence);
    }

    public static final ByteBuffer parseBoundaryInternal(CharSequence charSequence) throws IOException {
        int iFindBoundary = findBoundary(charSequence);
        if (iFindBoundary == -1) {
            throw new IOException("Failed to parse multipart: Content-Type's boundary parameter is missing");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(74);
        byteBufferAllocate.put((byte) 13);
        byteBufferAllocate.put((byte) 10);
        byteBufferAllocate.put(PrefixChar);
        byteBufferAllocate.put(PrefixChar);
        int length = charSequence.length();
        char c10 = 0;
        for (int i10 = iFindBoundary + 9; i10 < length; i10++) {
            char cCharAt = charSequence.charAt(i10);
            int i11 = 65535 & cCharAt;
            if (i11 > 127) {
                StringBuilder sb2 = new StringBuilder("Failed to parse multipart: wrong boundary byte 0x");
                r0.c(16);
                sb2.append(Integer.toString(i11, 16));
                sb2.append(" - should be 7bit character");
                throw new IOException(sb2.toString());
            }
            if (c10 == 0) {
                if (cCharAt == ' ') {
                    continue;
                } else if (cCharAt != '\"') {
                    if (cCharAt == ';' || cCharAt == ',') {
                        break;
                    }
                    byteBufferAllocate.put((byte) i11);
                    c10 = 1;
                } else {
                    c10 = 2;
                }
            } else if (c10 == 1) {
                if (cCharAt == ' ' || cCharAt == ',' || cCharAt == ';') {
                    break;
                }
                if (!byteBufferAllocate.hasRemaining()) {
                    throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                }
                byteBufferAllocate.put((byte) i11);
            } else if (c10 != 2) {
                if (c10 != 3) {
                    continue;
                } else {
                    if (!byteBufferAllocate.hasRemaining()) {
                        throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                    }
                    byteBufferAllocate.put((byte) i11);
                    c10 = 2;
                }
            } else if (cCharAt == '\\') {
                c10 = 3;
            } else {
                if (cCharAt == '\"') {
                    break;
                }
                if (!byteBufferAllocate.hasRemaining()) {
                    throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                }
                byteBufferAllocate.put((byte) i11);
            }
        }
        byteBufferAllocate.flip();
        if (byteBufferAllocate.remaining() != 4) {
            return byteBufferAllocate;
        }
        throw new IOException("Empty multipart boundary is not allowed");
    }

    public static final w<MultipartEvent> parseMultipart(k0 k0Var, ByteReadChannel byteReadChannel, HttpHeadersMap httpHeadersMap) throws IOException {
        CharSequence charSequence = httpHeadersMap.get(HttpConnection.CONTENT_TYPE);
        if (charSequence == null) {
            throw new IOException("Failed to parse multipart: no Content-Type header");
        }
        CharSequence charSequence2 = httpHeadersMap.get("Content-Length");
        return parseMultipart(k0Var, byteReadChannel, charSequence, charSequence2 != null ? Long.valueOf(CharsKt.parseDecLong(charSequence2)) : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parsePart(java.nio.ByteBuffer r9, io.ktor.utils.io.ByteReadChannel r10, io.ktor.utils.io.ByteWriteChannel r11, long r12, d7.d<? super x6.x> r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof io.ktor.http.cio.MultipartKt.C18251
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.http.cio.MultipartKt$parsePart$1 r0 = (io.ktor.http.cio.MultipartKt.C18251) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r7 = r0
            goto L1a
        L14:
            io.ktor.http.cio.MultipartKt$parsePart$1 r0 = new io.ktor.http.cio.MultipartKt$parsePart$1
            r0.<init>(r14)
            goto L12
        L1a:
            java.lang.Object r14 = r7.result
            int r0 = r7.label
            r1 = 2
            r2 = 1
            e7.a r8 = e7.a.f15033i
            if (r0 == 0) goto L52
            if (r0 == r2) goto L3b
            if (r0 != r1) goto L33
            java.lang.Object r9 = r7.L$0
            io.ktor.http.cio.HttpHeadersMap r9 = (io.ktor.http.cio.HttpHeadersMap) r9
            k2.c.G(r14)     // Catch: java.lang.Throwable -> L30
            goto L7b
        L30:
            r0 = move-exception
            r10 = r0
            goto L8f
        L33:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3b:
            long r12 = r7.J$0
            java.lang.Object r9 = r7.L$2
            r11 = r9
            io.ktor.utils.io.ByteWriteChannel r11 = (io.ktor.utils.io.ByteWriteChannel) r11
            java.lang.Object r9 = r7.L$1
            r10 = r9
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            java.lang.Object r9 = r7.L$0
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            k2.c.G(r14)
        L4e:
            r2 = r10
            r3 = r11
            r5 = r12
            goto L66
        L52:
            k2.c.G(r14)
            r7.L$0 = r9
            r7.L$1 = r10
            r7.L$2 = r11
            r7.J$0 = r12
            r7.label = r2
            java.lang.Object r14 = parsePartHeadersImpl(r10, r7)
            if (r14 != r8) goto L4e
            goto L79
        L66:
            r4 = r14
            io.ktor.http.cio.HttpHeadersMap r4 = (io.ktor.http.cio.HttpHeadersMap) r4
            r7.L$0 = r4     // Catch: java.lang.Throwable -> L8c
            r10 = 0
            r7.L$1 = r10     // Catch: java.lang.Throwable -> L8c
            r7.L$2 = r10     // Catch: java.lang.Throwable -> L8c
            r7.label = r1     // Catch: java.lang.Throwable -> L8c
            r1 = r9
            java.lang.Object r14 = parsePartBodyImpl(r1, r2, r3, r4, r5, r7)     // Catch: java.lang.Throwable -> L8c
            if (r14 != r8) goto L7a
        L79:
            return r8
        L7a:
            r9 = r4
        L7b:
            java.lang.Number r14 = (java.lang.Number) r14     // Catch: java.lang.Throwable -> L30
            long r10 = r14.longValue()     // Catch: java.lang.Throwable -> L30
            x6.x r12 = new x6.x     // Catch: java.lang.Throwable -> L30
            java.lang.Long r13 = new java.lang.Long     // Catch: java.lang.Throwable -> L30
            r13.<init>(r10)     // Catch: java.lang.Throwable -> L30
            r12.<init>(r9, r13)     // Catch: java.lang.Throwable -> L30
            return r12
        L8c:
            r0 = move-exception
            r10 = r0
            r9 = r4
        L8f:
            r9.release()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePart(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object parsePart$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return parsePart(byteBuffer, byteReadChannel, byteWriteChannel, j10, dVar);
    }

    @x6.e
    public static final Object parsePartBody(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j10, d<? super Long> dVar) {
        return parsePartBodyImpl(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j10, dVar);
    }

    public static /* synthetic */ Object parsePartBody$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return parsePartBody(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0070, code lost:
    
        if (r14 == r0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ad, code lost:
    
        if (r14 == r0) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parsePartBodyImpl(java.nio.ByteBuffer r8, io.ktor.utils.io.ByteReadChannel r9, io.ktor.utils.io.ByteWriteChannel r10, io.ktor.http.cio.HttpHeadersMap r11, long r12, d7.d<? super java.lang.Long> r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof io.ktor.http.cio.MultipartKt.C18261
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1 r0 = (io.ktor.http.cio.MultipartKt.C18261) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r7 = r0
            goto L1a
        L14:
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1 r0 = new io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1
            r0.<init>(r14)
            goto L12
        L1a:
            java.lang.Object r14 = r7.result
            int r0 = r7.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L41
            if (r0 == r2) goto L38
            if (r0 != r1) goto L30
            java.lang.Object r8 = r7.L$0
            r10 = r8
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            k2.c.G(r14)
            goto Lb0
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L38:
            java.lang.Object r8 = r7.L$0
            r10 = r8
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            k2.c.G(r14)
            goto L73
        L41:
            k2.c.G(r14)
            java.lang.String r14 = "Content-Length"
            java.lang.CharSequence r11 = r11.get(r14)
            r14 = 0
            if (r11 == 0) goto L57
            long r3 = io.ktor.http.cio.internals.CharsKt.parseDecLong(r11)
            java.lang.Long r11 = new java.lang.Long
            r11.<init>(r3)
            goto L58
        L57:
            r11 = r14
        L58:
            e7.a r0 = e7.a.f15033i
            if (r11 == 0) goto L9b
            long r3 = r11.longValue()
            int r8 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r8 > 0) goto L7a
            long r11 = r11.longValue()
            r7.L$0 = r10
            r7.label = r2
            java.lang.Object r14 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r9, r10, r11, r7)
            if (r14 != r0) goto L73
            goto Laf
        L73:
            java.lang.Number r14 = (java.lang.Number) r14
            long r8 = r14.longValue()
            goto Lb6
        L7a:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Multipart part content length limit of "
            r9.<init>(r10)
            r9.append(r12)
            java.lang.String r10 = " exceeded (actual size is "
            r9.append(r10)
            r9.append(r11)
            r10 = 41
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L9b:
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1 r4 = new io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1
            r4.<init>(r10, r14)
            r7.L$0 = r10
            r7.label = r1
            java.lang.String r1 = "part"
            r2 = r8
            r3 = r9
            r5 = r12
            java.lang.Object r14 = copyUntilBoundary(r1, r2, r3, r4, r5, r7)
            if (r14 != r0) goto Lb0
        Laf:
            return r0
        Lb0:
            java.lang.Number r14 = (java.lang.Number) r14
            long r8 = r14.longValue()
        Lb6:
            r10.flush()
            java.lang.Long r10 = new java.lang.Long
            r10.<init>(r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePartBodyImpl(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, io.ktor.http.cio.HttpHeadersMap, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object parsePartBodyImpl$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return parsePartBodyImpl(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j10, dVar);
    }

    @x6.e
    public static final Object parsePartHeaders(ByteReadChannel byteReadChannel, d<? super HttpHeadersMap> dVar) {
        return parsePartHeadersImpl(byteReadChannel, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parsePartHeadersImpl(io.ktor.utils.io.ByteReadChannel r7, d7.d<? super io.ktor.http.cio.HttpHeadersMap> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof io.ktor.http.cio.MultipartKt.C18271
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1 r0 = (io.ktor.http.cio.MultipartKt.C18271) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1 r0 = new io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1
            r0.<init>(r8)
            goto L12
        L1a:
            java.lang.Object r8 = r4.result
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L36
            if (r0 != r1) goto L2e
            java.lang.Object r7 = r4.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r7 = (io.ktor.http.cio.internals.CharArrayBuilder) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L2b
            goto L51
        L2b:
            r0 = move-exception
            r8 = r0
            goto L61
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            k2.c.G(r8)
            io.ktor.http.cio.internals.CharArrayBuilder r2 = new io.ktor.http.cio.internals.CharArrayBuilder
            r8 = 0
            r2.<init>(r8, r1, r8)
            r4.L$0 = r2     // Catch: java.lang.Throwable -> L5e
            r4.label = r1     // Catch: java.lang.Throwable -> L5e
            r3 = 0
            r5 = 4
            r6 = 0
            r1 = r7
            java.lang.Object r8 = io.ktor.http.cio.HttpParserKt.parseHeaders$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L5e
            e7.a r7 = e7.a.f15033i
            if (r8 != r7) goto L50
            return r7
        L50:
            r7 = r2
        L51:
            io.ktor.http.cio.HttpHeadersMap r8 = (io.ktor.http.cio.HttpHeadersMap) r8     // Catch: java.lang.Throwable -> L2b
            if (r8 == 0) goto L56
            return r8
        L56:
            java.io.EOFException r8 = new java.io.EOFException     // Catch: java.lang.Throwable -> L2b
            java.lang.String r0 = "Failed to parse multipart headers: unexpected end of stream"
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L2b
            throw r8     // Catch: java.lang.Throwable -> L2b
        L5e:
            r0 = move-exception
            r8 = r0
            r7 = r2
        L61:
            r7.release()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePartHeadersImpl(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    @x6.e
    public static final Object parsePreamble(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j10, d<? super Long> dVar) {
        return parsePreambleImpl(byteBuffer, byteReadChannel, bytePacketBuilder, j10, dVar);
    }

    public static /* synthetic */ Object parsePreamble$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return parsePreamble(byteBuffer, byteReadChannel, bytePacketBuilder, j10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object parsePreambleImpl(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j10, d<? super Long> dVar) {
        return copyUntilBoundary("preamble/prologue", byteBuffer, byteReadChannel, new AnonymousClass2(bytePacketBuilder, null), j10, dVar);
    }

    public static /* synthetic */ Object parsePreambleImpl$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return parsePreambleImpl(byteBuffer, byteReadChannel, bytePacketBuilder, j10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
    
        if (r6.lookAheadSuspend(r7, r0) == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object skipBoundary(java.nio.ByteBuffer r5, io.ktor.utils.io.ByteReadChannel r6, d7.d<? super java.lang.Boolean> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.http.cio.MultipartKt.C18281
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.http.cio.MultipartKt$skipBoundary$1 r0 = (io.ktor.http.cio.MultipartKt.C18281) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.MultipartKt$skipBoundary$1 r0 = new io.ktor.http.cio.MultipartKt$skipBoundary$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3f
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.f0 r5 = (kotlin.jvm.internal.f0) r5
            k2.c.G(r7)
            goto L6e
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            k2.c.G(r7)
            goto L4d
        L3f:
            k2.c.G(r7)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = skipDelimiterOrEof(r6, r5, r0)
            if (r7 != r4) goto L4d
            goto L6d
        L4d:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            if (r5 != 0) goto L58
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        L58:
            kotlin.jvm.internal.f0 r5 = new kotlin.jvm.internal.f0
            r5.<init>()
            io.ktor.http.cio.MultipartKt$skipBoundary$2 r7 = new io.ktor.http.cio.MultipartKt$skipBoundary$2
            r1 = 0
            r7.<init>(r5, r1)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r6.lookAheadSuspend(r7, r0)
            if (r6 != r4) goto L6e
        L6d:
            return r4
        L6e:
            boolean r5 = r5.f19738i
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.skipBoundary(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    public static final Object skipDelimiterOrEof(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, d<? super Boolean> dVar) {
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (byteBuffer.remaining() <= 8192) {
            f0 f0Var = new f0();
            byteReadChannel.lookAhead(new AnonymousClass3(f0Var, byteBuffer));
            return f0Var.f19738i ? Boolean.TRUE : trySkipDelimiterSuspend(byteReadChannel, byteBuffer, dVar);
        }
        throw new IllegalArgumentException(("Delimiter of " + byteBuffer.remaining() + " bytes is too long: at most 8192 bytes could be checked").toString());
    }

    private static final boolean startsWith(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        int iMin = Math.min(byteBuffer.remaining(), byteBuffer2.remaining() - i10);
        if (iMin <= 0) {
            return false;
        }
        int iPosition = byteBuffer.position();
        int iPosition2 = byteBuffer2.position() + i10;
        for (int i11 = 0; i11 < iMin; i11++) {
            if (byteBuffer.get(iPosition + i11) != byteBuffer2.get(iPosition2 + i11)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean startsWith$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return startsWith(byteBuffer, byteBuffer2, i10);
    }

    private static final int startsWithDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        ByteBuffer byteBufferRequest = lookAheadSession.request(0, 1);
        if (byteBufferRequest == null) {
            return 0;
        }
        int iIndexOfPartial = indexOfPartial(byteBufferRequest, byteBuffer);
        if (iIndexOfPartial != 0) {
            return -1;
        }
        int iMin = Math.min(byteBufferRequest.remaining() - iIndexOfPartial, byteBuffer.remaining());
        int iRemaining = byteBuffer.remaining() - iMin;
        if (iRemaining > 0) {
            ByteBuffer byteBufferRequest2 = lookAheadSession.request(iIndexOfPartial + iMin, iRemaining);
            if (byteBufferRequest2 == null) {
                return iMin;
            }
            if (!startsWith(byteBufferRequest2, byteBuffer, iMin)) {
                return -1;
            }
        }
        return byteBuffer.remaining();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryEnsureDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) throws IOException {
        int iStartsWithDelimiter = startsWithDelimiter(lookAheadSession, byteBuffer);
        if (iStartsWithDelimiter == -1) {
            throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
        }
        if (iStartsWithDelimiter < byteBuffer.remaining()) {
            return iStartsWithDelimiter;
        }
        lookAheadSession.mo7011consumed(byteBuffer.remaining());
        return byteBuffer.remaining();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object trySkipDelimiterSuspend(io.ktor.utils.io.ByteReadChannel r4, java.nio.ByteBuffer r5, d7.d<? super java.lang.Boolean> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.http.cio.MultipartKt.C18301
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1 r0 = (io.ktor.http.cio.MultipartKt.C18301) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1 r0 = new io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            kotlin.jvm.internal.f0 r4 = (kotlin.jvm.internal.f0) r4
            k2.c.G(r6)
            goto L4f
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r6)
            kotlin.jvm.internal.f0 r6 = new kotlin.jvm.internal.f0
            r6.<init>()
            r6.f19738i = r2
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2 r1 = new io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2
            r3 = 0
            r1.<init>(r5, r6, r3)
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r4 = r4.lookAheadSuspend(r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L4e
            return r5
        L4e:
            r4 = r6
        L4f:
            boolean r4 = r4.f19738i
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.trySkipDelimiterSuspend(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    public static final w<MultipartEvent> parseMultipart(k0 k0Var, ByteReadChannel byteReadChannel, CharSequence charSequence, Long l10) throws IOException {
        if (o.z0(charSequence, "multipart/")) {
            return parseMultipart(k0Var, parseBoundaryInternal(charSequence), byteReadChannel, l10);
        }
        throw new IOException("Failed to parse multipart: Content-Type should be multipart/* but it is " + ((Object) charSequence));
    }

    @x6.e
    public static final w<MultipartEvent> parseMultipart(k0 k0Var, ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, Long l10) {
        p c18241 = new C18241(byteReadChannel, byteBuffer, l10, null);
        t tVar = new t(c0.b(k0Var, k.f14688i), m2.f0.a(0, 1, 4));
        tVar.S(1, tVar, c18241);
        return tVar;
    }
}
