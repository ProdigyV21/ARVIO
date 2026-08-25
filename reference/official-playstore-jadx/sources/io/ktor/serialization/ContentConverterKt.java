package io.ktor.serialization;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a#\u0010\u0004\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\u0006\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002*\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002¢\u0006\u0004\b\u0006\u0010\u0005\u001a9\u0010\u000f\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u0001j\u0002`\u0002H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/http/Headers;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "defaultCharset", "suitableCharset", "(Lio/ktor/http/Headers;Ljava/nio/charset/Charset;)Ljava/nio/charset/Charset;", "suitableCharsetOrNull", "", "Lio/ktor/serialization/ContentConverter;", "Lio/ktor/utils/io/ByteReadChannel;", TtmlNode.TAG_BODY, "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", HttpAuthHeader.Parameters.Charset, "", "deserialize", "(Ljava/util/List;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/util/reflect/TypeInfo;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "ktor-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentConverterKt {

    /* JADX INFO: renamed from: io.ktor.serialization.ContentConverterKt$deserialize$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.ContentConverterKt", f = "ContentConverter.kt", l = {123}, m = "deserialize")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContentConverterKt.deserialize(null, null, null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object deserialize(java.util.List<? extends io.ktor.serialization.ContentConverter> r4, final io.ktor.utils.io.ByteReadChannel r5, final io.ktor.util.reflect.TypeInfo r6, final java.nio.charset.Charset r7, d7.d<java.lang.Object> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.serialization.ContentConverterKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = (io.ktor.serialization.ContentConverterKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = new io.ktor.serialization.ContentConverterKt$deserialize$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L38
            if (r1 != r3) goto L30
            java.lang.Object r4 = r0.L$1
            r6 = r4
            io.ktor.util.reflect.TypeInfo r6 = (io.ktor.util.reflect.TypeInfo) r6
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.ByteReadChannel r5 = (io.ktor.utils.io.ByteReadChannel) r5
            k2.c.G(r8)
            goto L5a
        L30:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L38:
            k2.c.G(r8)
            androidx.work.impl.constraints.i r8 = new androidx.work.impl.constraints.i
            r1 = 1
            r8.<init>(r4, r1)
            io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1 r4 = new io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1
            r4.<init>()
            io.ktor.serialization.ContentConverterKt$deserialize$result$2 r7 = new io.ktor.serialization.ContentConverterKt$deserialize$result$2
            r7.<init>(r5, r2)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r8 = na.y0.n(r4, r7, r0)
            e7.a r4 = e7.a.f15033i
            if (r8 != r4) goto L5a
            return r4
        L5a:
            if (r8 != 0) goto L87
            boolean r4 = r5.isClosedForRead()
            if (r4 != 0) goto L63
            return r5
        L63:
            kotlin.reflect.q r4 = r6.getKotlinType()
            if (r4 == 0) goto L72
            boolean r4 = r4.j()
            if (r4 != r3) goto L72
            io.ktor.http.content.NullBody r4 = io.ktor.http.content.NullBody.INSTANCE
            return r4
        L72:
            io.ktor.serialization.ContentConvertException r4 = new io.ktor.serialization.ContentConvertException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "No suitable converter found for "
            r5.<init>(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r6 = 2
            r4.<init>(r5, r2, r6, r2)
            throw r4
        L87:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.ContentConverterKt.deserialize(java.util.List, io.ktor.utils.io.ByteReadChannel, io.ktor.util.reflect.TypeInfo, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    public static final Charset suitableCharset(Headers headers, Charset charset) {
        Charset charsetSuitableCharsetOrNull = suitableCharsetOrNull(headers, charset);
        return charsetSuitableCharsetOrNull == null ? charset : charsetSuitableCharsetOrNull;
    }

    public static /* synthetic */ Charset suitableCharset$default(Headers headers, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return suitableCharset(headers, charset);
    }

    public static final Charset suitableCharsetOrNull(Headers headers, Charset charset) {
        Iterator<HeaderValue> it = HttpHeaderValueParserKt.parseAndSortHeader(headers.get(HttpHeaders.INSTANCE.getAcceptCharset())).iterator();
        while (it.hasNext()) {
            String value = it.next().getValue();
            if (p.a(value, "*")) {
                return charset;
            }
            if (Charset.isSupported(value)) {
                return Charset.forName(value);
            }
        }
        return null;
    }

    public static /* synthetic */ Charset suitableCharsetOrNull$default(Headers headers, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return suitableCharsetOrNull(headers, charset);
    }
}
