package io.ktor.util;

import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001c\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0082\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u001a%\u0010\t\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\n\u001a/\u0010\u0012\u001a\u00020\u0000*\u00020\u000b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015\"\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0017\u0010\u001b\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"", "flag", "", "has", "(II)Z", "Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "source", "gzip", "inflate", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;Z)Lio/ktor/utils/io/ByteReadChannel;", "Ljava/util/zip/Inflater;", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "Ljava/nio/ByteBuffer;", "buffer", "Ljava/util/zip/Checksum;", "checksum", "inflateTo", "(Ljava/util/zip/Inflater;Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;Ljava/util/zip/Checksum;Ld7/d;)Ljava/lang/Object;", "GZIP_HEADER_SIZE", "I", "Lio/ktor/util/Encoder;", "Deflate", "Lio/ktor/util/Encoder;", "getDeflate", "()Lio/ktor/util/Encoder;", "GZip", "getGZip", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EncodersJvmKt {
    private static final int GZIP_HEADER_SIZE = 10;
    private static final Encoder Deflate = new Encoder() { // from class: io.ktor.util.EncodersJvmKt$Deflate$1
        @Override // io.ktor.util.Encoder
        public ByteReadChannel decode(k0 k0Var, ByteReadChannel byteReadChannel) {
            return EncodersJvmKt.inflate(k0Var, byteReadChannel, false);
        }

        @Override // io.ktor.util.Encoder
        public ByteReadChannel encode(k0 k0Var, ByteReadChannel byteReadChannel) {
            return DeflaterKt.deflated$default(byteReadChannel, true, (ObjectPool) null, k0Var.getCoroutineContext(), 2, (Object) null);
        }
    };
    private static final Encoder GZip = new Encoder() { // from class: io.ktor.util.EncodersJvmKt$GZip$1
        @Override // io.ktor.util.Encoder
        public ByteReadChannel decode(k0 k0Var, ByteReadChannel byteReadChannel) {
            return EncodersJvmKt.inflate$default(k0Var, byteReadChannel, false, 2, null);
        }

        @Override // io.ktor.util.Encoder
        public ByteReadChannel encode(k0 k0Var, ByteReadChannel byteReadChannel) {
            return DeflaterKt.deflated$default(byteReadChannel, true, (ObjectPool) null, k0Var.getCoroutineContext(), 2, (Object) null);
        }
    };

    /* JADX INFO: renamed from: io.ktor.util.EncodersJvmKt$inflate$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.EncodersJvmKt$inflate$1", f = "EncodersJvm.kt", l = {68, 85, 161, 164, MediaError.DetailedErrorCode.MEDIA_NETWORK, 109, 121}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ boolean $gzip;
        final /* synthetic */ ByteReadChannel $source;
        byte B$0;
        byte B$1;
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        short S$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, ByteReadChannel byteReadChannel, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$gzip = z;
            this.$source = byteReadChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$gzip, this.$source, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:101:0x0349 A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0169  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x01b3  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x01c2  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x01ca  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x01dd  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x021c  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0220  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0250  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0276 A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:80:0x029a A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x02b2 A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:91:0x02fb A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x030f A[Catch: all -> 0x003d, TryCatch #1 {all -> 0x003d, blocks: (B:7:0x0034, B:100:0x0331, B:94:0x0309, B:96:0x030f, B:101:0x0349, B:103:0x034d, B:105:0x0355, B:107:0x0375, B:110:0x037a, B:111:0x039e, B:112:0x039f, B:113:0x03a6, B:114:0x03a7, B:115:0x03ca, B:116:0x03cb, B:120:0x03e5, B:121:0x03ec, B:12:0x005e, B:89:0x02dd, B:81:0x02ac, B:83:0x02b2, B:85:0x02b8, B:90:0x02f6, B:73:0x026e, B:75:0x0276, B:78:0x0292, B:80:0x029a, B:91:0x02fb, B:93:0x0303, B:122:0x03ed, B:15:0x0080), top: B:131:0x000b }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x0298 -> B:73:0x026e). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x029a -> B:81:0x02ac). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x02d8 -> B:89:0x02dd). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x032f -> B:100:0x0331). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.io.EOFException {
            /*
                Method dump skipped, instruction units count: 1048
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.EncodersJvmKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.EncodersJvmKt$inflateTo$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.util.EncodersJvmKt", f = "EncodersJvm.kt", l = {157}, m = "inflateTo")
    public static final class C19471 extends c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C19471(d<? super C19471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EncodersJvmKt.inflateTo(null, null, null, null, this);
        }
    }

    public static final Encoder getDeflate() {
        return Deflate;
    }

    public static final Encoder getGZip() {
        return GZip;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean has(int i10, int i11) {
        return (i10 & i11) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteReadChannel inflate(k0 k0Var, ByteReadChannel byteReadChannel, boolean z) {
        return CoroutinesKt.writer$default(k0Var, (d7.j) null, false, (p) new AnonymousClass1(z, byteReadChannel, null), 3, (Object) null).getChannel();
    }

    public static /* synthetic */ ByteReadChannel inflate$default(k0 k0Var, ByteReadChannel byteReadChannel, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return inflate(k0Var, byteReadChannel, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object inflateTo(java.util.zip.Inflater r4, io.ktor.utils.io.ByteWriteChannel r5, java.nio.ByteBuffer r6, java.util.zip.Checksum r7, d7.d<? super java.lang.Integer> r8) throws java.util.zip.DataFormatException {
        /*
            boolean r0 = r8 instanceof io.ktor.util.EncodersJvmKt.C19471
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.util.EncodersJvmKt$inflateTo$1 r0 = (io.ktor.util.EncodersJvmKt.C19471) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.EncodersJvmKt$inflateTo$1 r0 = new io.ktor.util.EncodersJvmKt$inflateTo$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            int r4 = r0.I$0
            k2.c.G(r8)
            goto L60
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            k2.c.G(r8)
            r6.clear()
            byte[] r8 = r6.array()
            int r1 = r6.position()
            int r3 = r6.remaining()
            int r4 = r4.inflate(r8, r1, r3)
            int r8 = r6.position()
            int r8 = r8 + r4
            r6.position(r8)
            r6.flip()
            io.ktor.util.DeflaterKt.updateKeepPosition(r7, r6)
            r0.I$0 = r4
            r0.label = r2
            java.lang.Object r5 = r5.writeFully(r6, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L60
            return r6
        L60:
            java.lang.Integer r5 = new java.lang.Integer
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.EncodersJvmKt.inflateTo(java.util.zip.Inflater, io.ktor.utils.io.ByteWriteChannel, java.nio.ByteBuffer, java.util.zip.Checksum, d7.d):java.lang.Object");
    }
}
