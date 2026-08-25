package io.ktor.http.cio;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.HttpMethod;
import io.ktor.http.cio.internals.AsciiCharTree;
import io.ktor.http.cio.internals.CharArrayBuilder;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.http.cio.internals.MutableRange;
import io.ktor.http.cio.internals.TokenizerKt;
import io.ktor.utils.io.ByteReadChannel;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0011\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0004\u001a\u001b\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0004\u001a/\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\r\u001a\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001f\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0017\u0010\u0016\u001a\u001f\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001f\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001a\u0010\u0019\u001a\u001f\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0017\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002¢\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\"\u0010#\u001a/\u0010)\u001a\u00020(2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b)\u0010*\u001a\u001f\u0010+\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b+\u0010,\u001a\u001f\u0010-\u001a\u00020(2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b-\u0010.\u001a\u001f\u0010/\u001a\u00020(2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b/\u00100\u001a\u0017\u00101\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b1\u00102\u001a\u0017\u00104\u001a\u00020(2\u0006\u00103\u001a\u00020\u000eH\u0002¢\u0006\u0004\b4\u00105\"\u0014\u00106\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b6\u00107\"\u0014\u00108\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b8\u00107\"\u0014\u00109\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b9\u00107\"\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020&0:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<\"\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/http/cio/Request;", "parseRequest", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/cio/Response;", "parseResponse", "Lio/ktor/http/cio/HttpHeadersMap;", "parseHeaders", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "builder", "Lio/ktor/http/cio/internals/MutableRange;", "range", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/http/cio/internals/CharArrayBuilder;Lio/ktor/http/cio/internals/MutableRange;Ld7/d;)Ljava/lang/Object;", "", "host", "Lx6/t0;", "validateHostHeader", "(Ljava/lang/CharSequence;)V", "text", "Lio/ktor/http/HttpMethod;", "parseHttpMethod", "(Ljava/lang/CharSequence;Lio/ktor/http/cio/internals/MutableRange;)Lio/ktor/http/HttpMethod;", "parseHttpMethodFull", "parseUri", "(Ljava/lang/CharSequence;Lio/ktor/http/cio/internals/MutableRange;)Ljava/lang/CharSequence;", "parseVersion", "", "parseStatusCode", "(Ljava/lang/CharSequence;Lio/ktor/http/cio/internals/MutableRange;)I", "code", "", "statusOutOfRange", "(I)Z", "parseHeaderName", "(Lio/ktor/http/cio/internals/CharArrayBuilder;Lio/ktor/http/cio/internals/MutableRange;)I", "index", TtmlNode.START, "", "ch", "", "parseHeaderNameFailed", "(Lio/ktor/http/cio/internals/CharArrayBuilder;IIC)Ljava/lang/Void;", "parseHeaderValue", "(Lio/ktor/http/cio/internals/CharArrayBuilder;Lio/ktor/http/cio/internals/MutableRange;)V", "noColonFound", "(Ljava/lang/CharSequence;Lio/ktor/http/cio/internals/MutableRange;)Ljava/lang/Void;", "characterIsNotAllowed", "(Ljava/lang/CharSequence;C)Ljava/lang/Void;", "isDelimiter", "(C)Z", "result", "unsupportedHttpVersion", "(Ljava/lang/CharSequence;)Ljava/lang/Void;", "HTTP_LINE_LIMIT", "I", "HTTP_STATUS_CODE_MIN_RANGE", "HTTP_STATUS_CODE_MAX_RANGE", "", "hostForbiddenSymbols", "Ljava/util/Set;", "Lio/ktor/http/cio/internals/AsciiCharTree;", "", "versions", "Lio/ktor/http/cio/internals/AsciiCharTree;", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpParserKt {
    private static final int HTTP_LINE_LIMIT = 8192;
    private static final int HTTP_STATUS_CODE_MAX_RANGE = 999;
    private static final int HTTP_STATUS_CODE_MIN_RANGE = 100;
    private static final Set<Character> hostForbiddenSymbols = r.p0(new Character[]{'/', '?', '#', '@'});
    private static final AsciiCharTree<String> versions = AsciiCharTree.INSTANCE.build(a.E("HTTP/1.0", "HTTP/1.1"));

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpParserKt$parseHeaders$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", l = {86}, m = "parseHeaders")
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
            return HttpParserKt.parseHeaders(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpParserKt$parseHeaders$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", l = {101}, m = "parseHeaders")
    public static final class AnonymousClass2 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpParserKt.parseHeaders(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpParserKt$parseRequest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", l = {30, TsExtractor.TS_STREAM_TYPE_MHAS}, m = "parseRequest")
    public static final class C18221 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C18221(d<? super C18221> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpParserKt.parseRequest(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.HttpParserKt$parseResponse$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", l = {ColorSpace.MaxId, 72}, m = "parseResponse")
    public static final class C18231 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C18231(d<? super C18231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpParserKt.parseResponse(null, this);
        }
    }

    private static final Void characterIsNotAllowed(CharSequence charSequence, char c10) {
        throw new ParserException("Character with code " + (c10 & 255) + " is not allowed in header names, \n" + ((Object) charSequence));
    }

    private static final boolean isDelimiter(char c10) {
        return p.c(c10, 32) <= 0 || o.U("\"(),/:;<=>?@[\\]{}", c10);
    }

    private static final Void noColonFound(CharSequence charSequence, MutableRange mutableRange) {
        throw new ParserException("No colon in HTTP header in " + charSequence.subSequence(mutableRange.getStart(), mutableRange.getEnd()).toString() + " in builder: \n" + ((Object) charSequence));
    }

    public static final int parseHeaderName(CharArrayBuilder charArrayBuilder, MutableRange mutableRange) {
        int end = mutableRange.getEnd();
        for (int start = mutableRange.getStart(); start < end; start++) {
            char cCharAt = charArrayBuilder.charAt(start);
            if (cCharAt == ':' && start != mutableRange.getStart()) {
                mutableRange.setStart(start + 1);
                return start;
            }
            if (isDelimiter(cCharAt)) {
                parseHeaderNameFailed(charArrayBuilder, start, mutableRange.getStart(), cCharAt);
                throw new KotlinNothingValueException();
            }
        }
        noColonFound(charArrayBuilder, mutableRange);
        throw new KotlinNothingValueException();
    }

    private static final Void parseHeaderNameFailed(CharArrayBuilder charArrayBuilder, int i10, int i11, char c10) {
        if (c10 == ':') {
            throw new ParserException("Empty header names are not allowed as per RFC7230.");
        }
        if (i10 == i11) {
            throw new ParserException("Multiline headers via line folding is not supported since it is deprecated as per RFC7230.");
        }
        characterIsNotAllowed(charArrayBuilder, c10);
        throw new KotlinNothingValueException();
    }

    public static final void parseHeaderValue(CharArrayBuilder charArrayBuilder, MutableRange mutableRange) {
        int start = mutableRange.getStart();
        int end = mutableRange.getEnd();
        int iSkipSpacesAndHorizontalTabs = TokenizerKt.skipSpacesAndHorizontalTabs(charArrayBuilder, start, end);
        if (iSkipSpacesAndHorizontalTabs >= end) {
            mutableRange.setStart(end);
            return;
        }
        int i10 = iSkipSpacesAndHorizontalTabs;
        int i11 = i10;
        while (i10 < end) {
            char cCharAt = charArrayBuilder.charAt(i10);
            if (cCharAt != '\t' && cCharAt != ' ') {
                if (cCharAt == '\r' || cCharAt == '\n') {
                    characterIsNotAllowed(charArrayBuilder, cCharAt);
                    throw new KotlinNothingValueException();
                }
                i11 = i10;
            }
            i10++;
        }
        mutableRange.setStart(iSkipSpacesAndHorizontalTabs);
        mutableRange.setEnd(i11 + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parseHeaders(io.ktor.utils.io.ByteReadChannel r7, d7.d<? super io.ktor.http.cio.HttpHeadersMap> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.http.cio.HttpParserKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.http.cio.HttpParserKt$parseHeaders$1 r0 = (io.ktor.http.cio.HttpParserKt.AnonymousClass1) r0
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
            io.ktor.http.cio.HttpParserKt$parseHeaders$1 r0 = new io.ktor.http.cio.HttpParserKt$parseHeaders$1
            r0.<init>(r8)
            goto L12
        L1a:
            java.lang.Object r8 = r4.result
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r7 = r4.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r7 = (io.ktor.http.cio.internals.CharArrayBuilder) r7
            k2.c.G(r8)
            goto L4e
        L2b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L33:
            k2.c.G(r8)
            io.ktor.http.cio.internals.CharArrayBuilder r2 = new io.ktor.http.cio.internals.CharArrayBuilder
            r8 = 0
            r2.<init>(r8, r1, r8)
            r4.L$0 = r2
            r4.label = r1
            r3 = 0
            r5 = 4
            r6 = 0
            r1 = r7
            java.lang.Object r8 = parseHeaders$default(r1, r2, r3, r4, r5, r6)
            e7.a r7 = e7.a.f15033i
            if (r8 != r7) goto L4d
            return r7
        L4d:
            r7 = r2
        L4e:
            io.ktor.http.cio.HttpHeadersMap r8 = (io.ktor.http.cio.HttpHeadersMap) r8
            if (r8 != 0) goto L57
            io.ktor.http.cio.HttpHeadersMap r8 = new io.ktor.http.cio.HttpHeadersMap
            r8.<init>(r7)
        L57:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseHeaders(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object parseHeaders$default(ByteReadChannel byteReadChannel, CharArrayBuilder charArrayBuilder, MutableRange mutableRange, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            mutableRange = new MutableRange(0, 0);
        }
        return parseHeaders(byteReadChannel, charArrayBuilder, mutableRange, dVar);
    }

    private static final HttpMethod parseHttpMethod(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        HttpMethod httpMethod = (HttpMethod) x.T0(AsciiCharTree.search$default(CharsKt.getDefaultHttpMethods(), charSequence, mutableRange.getStart(), mutableRange.getEnd(), false, HttpParserKt$parseHttpMethod$exact$1.INSTANCE, 8, null));
        if (httpMethod == null) {
            return parseHttpMethodFull(charSequence, mutableRange);
        }
        mutableRange.setStart(httpMethod.getValue().length() + mutableRange.getStart());
        return httpMethod;
    }

    private static final HttpMethod parseHttpMethodFull(CharSequence charSequence, MutableRange mutableRange) {
        return new HttpMethod(TokenizerKt.nextToken(charSequence, mutableRange).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0091 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0092 A[Catch: all -> 0x0040, TryCatch #0 {all -> 0x0040, blocks: (B:13:0x0037, B:30:0x0089, B:33:0x0092, B:35:0x00a3, B:37:0x00bc, B:39:0x00c2, B:41:0x00c8, B:52:0x00ec, B:53:0x00f3, B:54:0x00f4, B:55:0x00fb, B:56:0x00fc, B:57:0x0122, B:26:0x0075), top: B:61:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e3 A[Catch: all -> 0x00e9, TRY_LEAVE, TryCatch #1 {all -> 0x00e9, blocks: (B:45:0x00dd, B:48:0x00e3), top: B:62:0x00dd }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [io.ktor.http.cio.HttpParserKt$parseRequest$1] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0086 -> B:30:0x0089). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parseRequest(io.ktor.utils.io.ByteReadChannel r13, d7.d<? super io.ktor.http.cio.Request> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseRequest(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d A[Catch: all -> 0x005b, TRY_LEAVE, TryCatch #1 {all -> 0x005b, blocks: (B:20:0x0057, B:29:0x0084, B:32:0x008d), top: B:50:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cc A[Catch: all -> 0x00d3, TryCatch #0 {all -> 0x00d3, blocks: (B:36:0x00c8, B:38:0x00cc, B:42:0x00d7), top: B:48:0x00c8 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parseResponse(io.ktor.utils.io.ByteReadChannel r14, d7.d<? super io.ktor.http.cio.Response> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseResponse(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x006c, code lost:
    
        r7.setStart(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006f, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int parseStatusCode(java.lang.CharSequence r6, io.ktor.http.cio.internals.MutableRange r7) {
        /*
            io.ktor.http.cio.internals.TokenizerKt.skipSpaces(r6, r7)
            int r0 = r7.getEnd()
            int r1 = r7.getStart()
            int r2 = r7.getEnd()
            r3 = 0
        L10:
            if (r1 >= r2) goto L6c
            char r4 = r6.charAt(r1)
            r5 = 32
            if (r4 != r5) goto L30
            boolean r6 = statusOutOfRange(r3)
            if (r6 != 0) goto L22
            r0 = r1
            goto L6c
        L22:
            io.ktor.http.cio.ParserException r6 = new io.ktor.http.cio.ParserException
            java.lang.String r7 = "Status-code must be 3-digit. Status received: "
            r0 = 46
            java.lang.String r7 = a0.c.k(r7, r3, r0)
            r6.<init>(r7)
            throw r6
        L30:
            r5 = 48
            if (r5 > r4) goto L40
            r5 = 58
            if (r4 >= r5) goto L40
            int r3 = r3 * 10
            int r4 = r4 + (-48)
            int r3 = r3 + r4
            int r1 = r1 + 1
            goto L10
        L40:
            int r0 = r7.getStart()
            int r7 = io.ktor.http.cio.internals.TokenizerKt.findSpaceOrEnd(r6, r7)
            java.lang.CharSequence r6 = r6.subSequence(r0, r7)
            java.lang.String r6 = r6.toString()
            java.lang.NumberFormatException r7 = new java.lang.NumberFormatException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Illegal digit "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r1 = " in status code "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r7.<init>(r6)
            throw r7
        L6c:
            r7.setStart(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseStatusCode(java.lang.CharSequence, io.ktor.http.cio.internals.MutableRange):int");
    }

    private static final CharSequence parseUri(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        int start = mutableRange.getStart();
        int iFindSpaceOrEnd = TokenizerKt.findSpaceOrEnd(charSequence, mutableRange);
        int i10 = iFindSpaceOrEnd - start;
        if (i10 <= 0) {
            return "";
        }
        if (i10 == 1 && charSequence.charAt(start) == '/') {
            mutableRange.setStart(iFindSpaceOrEnd);
            return DomExceptionUtils.SEPARATOR;
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(start, iFindSpaceOrEnd);
        mutableRange.setStart(iFindSpaceOrEnd);
        return charSequenceSubSequence;
    }

    private static final CharSequence parseVersion(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        if (mutableRange.getStart() >= mutableRange.getEnd()) {
            throw new IllegalStateException(("Failed to parse version: " + ((Object) charSequence)).toString());
        }
        String str = (String) x.T0(AsciiCharTree.search$default(versions, charSequence, mutableRange.getStart(), mutableRange.getEnd(), false, HttpParserKt$parseVersion$exact$1.INSTANCE, 8, null));
        if (str == null) {
            unsupportedHttpVersion(TokenizerKt.nextToken(charSequence, mutableRange));
            throw new KotlinNothingValueException();
        }
        mutableRange.setStart(str.length() + mutableRange.getStart());
        return str;
    }

    private static final boolean statusOutOfRange(int i10) {
        return i10 < 100 || i10 > 999;
    }

    private static final Void unsupportedHttpVersion(CharSequence charSequence) {
        throw new ParserException("Unsupported HTTP version: " + ((Object) charSequence));
    }

    private static final void validateHostHeader(CharSequence charSequence) {
        if (o.Y(":", charSequence)) {
            throw new ParserException("Host header with ':' should contains port: " + ((Object) charSequence));
        }
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            char cCharAt = charSequence.charAt(i10);
            Set<Character> set = hostForbiddenSymbols;
            if (set.contains(Character.valueOf(cCharAt))) {
                throw new ParserException("Host cannot contain any of the following symbols: " + set);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:24:0x0065, B:26:0x006d, B:30:0x0076, B:33:0x008a, B:34:0x00b1, B:35:0x00b8, B:36:0x00b9, B:38:0x00c5), top: B:44:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0076 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:24:0x0065, B:26:0x006d, B:30:0x0076, B:33:0x008a, B:34:0x00b1, B:35:0x00b8, B:36:0x00b9, B:38:0x00c5), top: B:44:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0063 -> B:44:0x0065). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object parseHeaders(io.ktor.utils.io.ByteReadChannel r12, io.ktor.http.cio.internals.CharArrayBuilder r13, io.ktor.http.cio.internals.MutableRange r14, d7.d<? super io.ktor.http.cio.HttpHeadersMap> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseHeaders(io.ktor.utils.io.ByteReadChannel, io.ktor.http.cio.internals.CharArrayBuilder, io.ktor.http.cio.internals.MutableRange, d7.d):java.lang.Object");
    }
}
