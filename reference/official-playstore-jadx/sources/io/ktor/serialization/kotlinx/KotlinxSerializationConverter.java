package io.ktor.serialization.kotlinx;

import androidx.media3.container.MdtaMetadataEntry;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.serialization.ContentConverter;
import io.ktor.util.reflect.TypeInfo;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import ya.a;
import ya.h;
import ya.o;
import ya.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JA\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\n\u0010\u000e\u001a\u00060\fj\u0002`\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\n\u0010\u000e\u001a\u00060\fj\u0002`\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0097@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J9\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\n\u0010\u000e\u001a\u00060\fj\u0002`\r2\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0016J1\u0010\u001a\u001a\u0004\u0018\u00010\b2\n\u0010\u000e\u001a\u00060\fj\u0002`\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0018H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/serialization/kotlinx/KotlinxSerializationConverter;", "Lio/ktor/serialization/ContentConverter;", "Lya/o;", "format", "<init>", "(Lya/o;)V", "Lya/h;", "serializer", "", "value", "Lio/ktor/http/ContentType;", "contentType", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "serializeContent", "(Lya/h;Lya/o;Ljava/lang/Object;Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;)Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "Lio/ktor/http/content/OutgoingContent;", "serialize", "(Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "serializeNullable", "Lio/ktor/utils/io/ByteReadChannel;", "content", "deserialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "Lya/o;", "", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "extensions", "Ljava/util/List;", "ktor-serialization-kotlinx"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationConverter implements ContentConverter {
    private final List<KotlinxSerializationExtension> extensions;
    private final o format;

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.KotlinxSerializationConverter$deserialize$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.kotlinx.KotlinxSerializationConverter", f = "KotlinxSerializationConverter.kt", l = {74, MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64}, m = "deserialize")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationConverter.this.deserialize(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.kotlinx.KotlinxSerializationConverter", f = "KotlinxSerializationConverter.kt", l = {59}, m = "serializeNullable")
    public static final class C18451 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C18451(d<? super C18451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationConverter.this.serializeNullable(null, null, null, null, this);
        }
    }

    public KotlinxSerializationConverter(o oVar) {
        this.format = oVar;
        this.extensions = ExtensionsKt.extensions(oVar);
        if ((oVar instanceof a) || (oVar instanceof x)) {
            return;
        }
        throw new IllegalArgumentException(("Only binary and string formats are supported, " + oVar + " is not supported.").toString());
    }

    private final OutgoingContent.ByteArrayContent serializeContent(h<?> serializer, o format, Object value, ContentType contentType, Charset charset) {
        if (format instanceof x) {
            return new TextContent(((x) format).b(serializer, value), ContentTypesKt.withCharsetIfNeeded(contentType, charset), null, 4, null);
        }
        if (format instanceof a) {
            return new ByteArrayContent(((a) format).e(), contentType, null, 4, null);
        }
        throw new IllegalStateException(("Unsupported format " + format).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00d0 A[Catch: all -> 0x00db, TryCatch #0 {all -> 0x00db, blocks: (B:32:0x00c9, B:34:0x00d0, B:38:0x00dd, B:40:0x00e1, B:42:0x00eb, B:43:0x0105), top: B:46:0x00c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00dd A[Catch: all -> 0x00db, TryCatch #0 {all -> 0x00db, blocks: (B:32:0x00c9, B:34:0x00d0, B:38:0x00dd, B:40:0x00e1, B:42:0x00eb, B:43:0x0105), top: B:46:0x00c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    @Override // io.ktor.serialization.ContentConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object deserialize(java.nio.charset.Charset r18, io.ktor.util.reflect.TypeInfo r19, final io.ktor.utils.io.ByteReadChannel r20, d7.d<java.lang.Object> r21) throws java.lang.IllegalAccessException, io.ktor.serialization.JsonConvertException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize(java.nio.charset.Charset, io.ktor.util.reflect.TypeInfo, io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    @Override // io.ktor.serialization.ContentConverter
    @x6.e
    public Object serialize(ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar) {
        return serializeNullable(contentType, charset, typeInfo, obj, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.serialization.ContentConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object serializeNullable(final io.ktor.http.ContentType r10, final java.nio.charset.Charset r11, final io.ktor.util.reflect.TypeInfo r12, final java.lang.Object r13, d7.d<? super io.ktor.http.content.OutgoingContent> r14) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r9 = this;
            boolean r0 = r14 instanceof io.ktor.serialization.kotlinx.KotlinxSerializationConverter.C18451
            if (r0 == 0) goto L13
            r0 = r14
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1 r0 = (io.ktor.serialization.kotlinx.KotlinxSerializationConverter.C18451) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1 r0 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L44
            if (r1 != r2) goto L3c
            java.lang.Object r13 = r0.L$4
            java.lang.Object r10 = r0.L$3
            r12 = r10
            io.ktor.util.reflect.TypeInfo r12 = (io.ktor.util.reflect.TypeInfo) r12
            java.lang.Object r10 = r0.L$2
            r11 = r10
            java.nio.charset.Charset r11 = (java.nio.charset.Charset) r11
            java.lang.Object r10 = r0.L$1
            io.ktor.http.ContentType r10 = (io.ktor.http.ContentType) r10
            java.lang.Object r0 = r0.L$0
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter r0 = (io.ktor.serialization.kotlinx.KotlinxSerializationConverter) r0
            k2.c.G(r14)
            r4 = r10
            r5 = r11
            r3 = r13
            goto L78
        L3c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L44:
            k2.c.G(r14)
            java.util.List<io.ktor.serialization.kotlinx.KotlinxSerializationExtension> r14 = r9.extensions
            androidx.work.impl.constraints.i r4 = new androidx.work.impl.constraints.i
            r1 = 1
            r4.<init>(r14, r1)
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$$inlined$map$1 r3 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$$inlined$map$1
            r5 = r10
            r6 = r11
            r7 = r12
            r8 = r13
            r3.<init>()
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$fromExtension$2 r10 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$fromExtension$2
            r11 = 0
            r10.<init>(r11)
            r0.L$0 = r9
            r0.L$1 = r5
            r0.L$2 = r6
            r0.L$3 = r7
            r0.L$4 = r8
            r0.label = r2
            java.lang.Object r14 = na.y0.n(r3, r10, r0)
            e7.a r10 = e7.a.f15033i
            if (r14 != r10) goto L73
            return r10
        L73:
            r0 = r9
            r4 = r5
            r5 = r6
            r12 = r7
            r3 = r8
        L78:
            io.ktor.http.content.OutgoingContent r14 = (io.ktor.http.content.OutgoingContent) r14
            if (r14 == 0) goto L7d
            return r14
        L7d:
            ya.o r10 = r0.format     // Catch: kotlinx.serialization.SerializationException -> L89
            fb.b r10 = r10.a()     // Catch: kotlinx.serialization.SerializationException -> L89
            ya.h r10 = io.ktor.serialization.kotlinx.SerializerLookupKt.serializerForTypeInfo(r10, r12)     // Catch: kotlinx.serialization.SerializationException -> L89
        L87:
            r1 = r10
            goto L94
        L89:
            ya.o r10 = r0.format
            fb.b r10 = r10.a()
            ya.h r10 = io.ktor.serialization.kotlinx.SerializerLookupKt.guessSerializer(r3, r10)
            goto L87
        L94:
            ya.o r2 = r0.format
            io.ktor.http.content.OutgoingContent$ByteArrayContent r10 = r0.serializeContent(r1, r2, r3, r4, r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.KotlinxSerializationConverter.serializeNullable(io.ktor.http.ContentType, java.nio.charset.Charset, io.ktor.util.reflect.TypeInfo, java.lang.Object, d7.d):java.lang.Object");
    }
}
