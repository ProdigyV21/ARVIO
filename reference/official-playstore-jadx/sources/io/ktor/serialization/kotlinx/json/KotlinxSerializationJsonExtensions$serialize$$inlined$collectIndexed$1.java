package io.ktor.serialization.kotlinx.json;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import na.k;
import ya.h;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"io/ktor/serialization/kotlinx/json/KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1", "Lna/k;", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "", "index", "I", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1<T> implements k<T> {
    final /* synthetic */ ByteWriteChannel $channel$inlined;
    final /* synthetic */ Charset $charset$inlined;
    final /* synthetic */ JsonArraySymbols $jsonArraySymbols$inlined;
    final /* synthetic */ h $serializer$inlined;
    private int index;
    final /* synthetic */ KotlinxSerializationJsonExtensions this$0;

    /* JADX INFO: renamed from: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1", f = "KotlinxSerializationJsonExtensions.kt", l = {124, 127}, m = "emit")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.this.emit(null, this);
        }
    }

    public KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1(ByteWriteChannel byteWriteChannel, JsonArraySymbols jsonArraySymbols, KotlinxSerializationJsonExtensions kotlinxSerializationJsonExtensions, h hVar, Charset charset) {
        this.$channel$inlined = byteWriteChannel;
        this.$jsonArraySymbols$inlined = jsonArraySymbols;
        this.this$0 = kotlinxSerializationJsonExtensions;
        this.$serializer$inlined = hVar;
        this.$charset$inlined = charset;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:
    
        if (io.ktor.utils.io.ByteWriteChannelKt.writeFully(r1, r9, r0) != r4) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // na.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object emit(T r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1 r0 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1 r0 = new io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L41
            if (r1 == r3) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r8 = r0.L$0
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1 r8 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1) r8
            k2.c.G(r9)
            goto La3
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            java.lang.Object r8 = r0.L$1
            java.lang.Object r1 = r0.L$0
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1 r1 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1) r1
            k2.c.G(r9)
            goto L64
        L41:
            k2.c.G(r9)
            int r9 = r7.index
            int r1 = r9 + 1
            r7.index = r1
            if (r9 < 0) goto Lab
            if (r9 <= 0) goto L67
            io.ktor.utils.io.ByteWriteChannel r9 = r7.$channel$inlined
            io.ktor.serialization.kotlinx.json.JsonArraySymbols r1 = r7.$jsonArraySymbols$inlined
            byte[] r1 = r1.getObjectSeparator()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r9, r1, r0)
            if (r9 != r4) goto L63
            goto La2
        L63:
            r1 = r7
        L64:
            r9 = r8
            r8 = r1
            goto L69
        L67:
            r9 = r8
            r8 = r7
        L69:
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions r1 = r8.this$0
            db.b r1 = io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.access$getFormat$p(r1)
            ya.h r3 = r8.$serializer$inlined
            java.lang.String r9 = r1.b(r3, r9)
            io.ktor.utils.io.ByteWriteChannel r1 = r8.$channel$inlined
            java.nio.charset.Charset r3 = r8.$charset$inlined
            java.nio.charset.Charset r5 = kotlin.text.a.f19924a
            boolean r3 = kotlin.jvm.internal.p.a(r3, r5)
            if (r3 == 0) goto L86
            byte[] r9 = r9.getBytes(r5)
            goto L95
        L86:
            java.nio.charset.Charset r3 = r8.$charset$inlined
            java.nio.charset.CharsetEncoder r3 = r3.newEncoder()
            r5 = 0
            int r6 = r9.length()
            byte[] r9 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r3, r9, r5, r6)
        L95:
            r0.L$0 = r8
            r3 = 0
            r0.L$1 = r3
            r0.label = r2
            java.lang.Object r9 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r1, r9, r0)
            if (r9 != r4) goto La3
        La2:
            return r4
        La3:
            io.ktor.utils.io.ByteWriteChannel r8 = r8.$channel$inlined
            r8.flush()
            x6.t0 r8 = x6.t0.f22605a
            return r8
        Lab:
            java.lang.ArithmeticException r8 = new java.lang.ArithmeticException
            java.lang.String r9 = "Index overflow has happened"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.emit(java.lang.Object, d7.d):java.lang.Object");
    }
}
