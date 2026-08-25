package io.ktor.serialization.kotlinx.json;

import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.ui.theme.AnimationConstants;
import d7.d;
import db.b;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.ChannelWriterContent;
import io.ktor.http.content.OutgoingContent;
import io.ktor.serialization.kotlinx.KotlinxSerializationExtension;
import io.ktor.serialization.kotlinx.SerializerLookupKt;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import r7.p;
import x6.t0;
import ya.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JE\u0010\u0010\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\n\u0010\f\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J;\u0010\u0010\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0013\u001a\u00020\u00122\n\u0010\f\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0019J1\u0010\u001c\u001a\u0004\u0018\u00010\u00162\n\u0010\f\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR$\u0010!\u001a\u0012\u0012\b\u0012\u00060\nj\u0002`\u000b\u0012\u0004\u0012\u00020 0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/serialization/kotlinx/json/KotlinxSerializationJsonExtensions;", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "Ldb/b;", "format", "<init>", "(Ldb/b;)V", "T", "Lna/j;", "Lya/h;", "serializer", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "Lx6/t0;", "serialize", "(Lna/j;Lya/h;Ljava/nio/charset/Charset;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "", "value", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "content", "deserialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "Ldb/b;", "", "Lio/ktor/serialization/kotlinx/json/JsonArraySymbols;", "jsonArraySymbolsMap", "Ljava/util/Map;", "ktor-serialization-kotlinx-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationJsonExtensions implements KotlinxSerializationExtension {
    private final b format;
    private final Map<Charset, JsonArraySymbols> jsonArraySymbolsMap = new LinkedHashMap();

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$deserialize$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions", f = "KotlinxSerializationJsonExtensions.kt", l = {66}, m = "deserialize")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationJsonExtensions.this.deserialize(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ByteWriteChannel;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$2", f = "KotlinxSerializationJsonExtensions.kt", l = {51}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ByteWriteChannel, d<? super t0>, Object> {
        final /* synthetic */ Charset $charset;
        final /* synthetic */ h<?> $serializer;
        final /* synthetic */ Object $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Object obj, h<?> hVar, Charset charset, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$value = obj;
            this.$serializer = hVar;
            this.$charset = charset;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = KotlinxSerializationJsonExtensions.this.new AnonymousClass2(this.$value, this.$serializer, this.$charset, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
            return ((AnonymousClass2) create(byteWriteChannel, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ByteWriteChannel byteWriteChannel = (ByteWriteChannel) this.L$0;
                KotlinxSerializationJsonExtensions kotlinxSerializationJsonExtensions = KotlinxSerializationJsonExtensions.this;
                na.j jVar = (na.j) this.$value;
                h<?> hVar = this.$serializer;
                Charset charset = this.$charset;
                this.label = 1;
                Object objSerialize = kotlinxSerializationJsonExtensions.serialize(jVar, hVar, charset, byteWriteChannel, this);
                a aVar = a.f15033i;
                if (objSerialize == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions", f = "KotlinxSerializationJsonExtensions.kt", l = {AnimationConstants.STAGGER_SECTION, 121, TsExtractor.TS_STREAM_TYPE_DVBSUBS}, m = "serialize")
    public static final class AnonymousClass3<T> extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationJsonExtensions.this.serialize((na.j) null, (h) null, (Charset) null, (ByteWriteChannel) null, this);
        }
    }

    public KotlinxSerializationJsonExtensions(b bVar) {
        this.format = bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.serialization.kotlinx.KotlinxSerializationExtension
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object deserialize(java.nio.charset.Charset r5, io.ktor.util.reflect.TypeInfo r6, io.ktor.utils.io.ByteReadChannel r7, d7.d<java.lang.Object> r8) throws io.ktor.serialization.JsonConvertException {
        /*
            r4 = this;
            boolean r0 = r8 instanceof io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$deserialize$1 r0 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$deserialize$1 r0 = new io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$deserialize$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L25
            return r8
        L25:
            r5 = move-exception
            goto L5b
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            k2.c.G(r8)
            java.nio.charset.Charset r8 = kotlin.text.a.f19924a
            boolean r5 = kotlin.jvm.internal.p.a(r5, r8)
            if (r5 == 0) goto L73
            kotlin.reflect.d r5 = r6.getType()
            java.lang.Class<ga.m> r8 = ga.m.class
            kotlin.jvm.internal.m0 r1 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r8 = r1.b(r8)
            boolean r5 = kotlin.jvm.internal.p.a(r5, r8)
            if (r5 != 0) goto L4d
            goto L73
        L4d:
            db.b r5 = r4.format     // Catch: java.lang.Throwable -> L25
            r0.label = r2     // Catch: java.lang.Throwable -> L25
            java.lang.Object r5 = io.ktor.serialization.kotlinx.json.JsonExtensionsJvmKt.deserializeSequence(r5, r7, r6, r0)     // Catch: java.lang.Throwable -> L25
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L5a
            return r6
        L5a:
            return r5
        L5b:
            io.ktor.serialization.JsonConvertException r6 = new io.ktor.serialization.JsonConvertException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Illegal input: "
            r7.<init>(r8)
            java.lang.String r8 = r5.getMessage()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7, r5)
            throw r6
        L73:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.deserialize(java.nio.charset.Charset, io.ktor.util.reflect.TypeInfo, io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    @Override // io.ktor.serialization.kotlinx.KotlinxSerializationExtension
    public Object serialize(ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar) {
        if (!kotlin.jvm.internal.p.a(charset, kotlin.text.a.f19924a) || !kotlin.jvm.internal.p.a(typeInfo.getType(), l0.f19747a.b(na.j.class))) {
            return null;
        }
        return new ChannelWriterContent(new AnonymousClass2(obj, SerializerLookupKt.serializerForTypeInfo(this.format.f14879b, KotlinxSerializationJsonExtensionsKt.argumentTypeInfo(typeInfo)), charset, null), ContentTypesKt.withCharsetIfNeeded(contentType, charset), null, null, 12, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d3, code lost:
    
        if (io.ktor.utils.io.ByteWriteChannelKt.writeFully(r2, r1, r4) != r10) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object serialize(na.j<? extends T> r19, ya.h<T> r20, java.nio.charset.Charset r21, io.ktor.utils.io.ByteWriteChannel r22, d7.d<? super x6.t0> r23) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.serialize(na.j, ya.h, java.nio.charset.Charset, io.ktor.utils.io.ByteWriteChannel, d7.d):java.lang.Object");
    }
}
