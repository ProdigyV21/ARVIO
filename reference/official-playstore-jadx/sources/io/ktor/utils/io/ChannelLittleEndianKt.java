package io.ktor.utils.io;

import androidx.media3.container.NalUnitUtil;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.core.ByteOrder;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001f\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0005\u001a\u001f\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001f\u0010\r\u001a\u00020\f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0005\u001a\u0017\u0010\u000e\u001a\u00020\u0003*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0017\u0010\u0010\u001a\u00020\u0006*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000f\u001a\u0017\u0010\u0011\u001a\u00020\b*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000f\u001a\u0017\u0010\u0012\u001a\u00020\n*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000f\u001a\u0017\u0010\u0013\u001a\u00020\f*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u000f\u001a'\u0010\u0017\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\u0019\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a'\u0010\u001d\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a'\u0010\u001f\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010!\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001f\u0010'\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010)\u001a\u00020\u0016*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a>\u0010/\u001a\u00028\u0000\"\u0004\b\u0000\u0010+*\u00020\u00002\u0006\u0010\u0015\u001a\u00028\u00002\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000,¢\u0006\u0002\b-H\u0081\bø\u0001\u0001¢\u0006\u0004\b/\u00100\u001a;\u0010/\u001a\u00028\u0000\"\u0004\b\u0000\u0010+*\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000,¢\u0006\u0002\b-H\u0082\b¢\u0006\u0004\b/\u00101\u001a>\u00102\u001a\u00028\u0000\"\u0004\b\u0000\u0010+*\u00028\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000,¢\u0006\u0002\b-H\u0081\bø\u0001\u0001¢\u0006\u0004\b2\u00103\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00064"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/core/ByteOrder;", "byteOrder", "", "readShort", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "", "readInt", "", "readLong", "", "readFloat", "", "readDouble", "readShortLittleEndian", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "readIntLittleEndian", "readLongLittleEndian", "readFloatLittleEndian", "readDoubleLittleEndian", "Lio/ktor/utils/io/ByteWriteChannel;", "value", "Lx6/t0;", "writeShort", "(Lio/ktor/utils/io/ByteWriteChannel;SLio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "writeInt", "(Lio/ktor/utils/io/ByteWriteChannel;ILio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "writeLong", "(Lio/ktor/utils/io/ByteWriteChannel;JLio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "writeFloat", "(Lio/ktor/utils/io/ByteWriteChannel;FLio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "writeDouble", "(Lio/ktor/utils/io/ByteWriteChannel;DLio/ktor/utils/io/core/ByteOrder;Ld7/d;)Ljava/lang/Object;", "writeShortLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;SLd7/d;)Ljava/lang/Object;", "writeIntLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;ILd7/d;)Ljava/lang/Object;", "writeLongLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "writeFloatLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;FLd7/d;)Ljava/lang/Object;", "writeDoubleLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;DLd7/d;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lx6/n;", "reverseBlock", "toLittleEndian", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Object;Lr7/l;)Ljava/lang/Object;", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Object;Lr7/l;)Ljava/lang/Object;", "reverseIfNeeded", "(Ljava/lang/Object;Lio/ktor/utils/io/core/ByteOrder;Lr7/l;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChannelLittleEndianKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ByteOrder.values().length];
            try {
                iArr[ByteOrder.BIG_ENDIAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {23}, m = "readDouble")
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
            return ChannelLittleEndianKt.readDouble(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {43}, m = "readDoubleLittleEndian")
    public static final class C20321 extends c {
        int label;
        /* synthetic */ Object result;

        public C20321(d<? super C20321> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readDoubleLittleEndian(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {19}, m = "readFloat")
    public static final class C20331 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20331(d<? super C20331> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readFloat(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI}, m = "readFloatLittleEndian")
    public static final class C20341 extends c {
        int label;
        /* synthetic */ Object result;

        public C20341(d<? super C20341> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readFloatLittleEndian(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readInt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {11}, m = "readInt")
    public static final class C20351 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20351(d<? super C20351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readInt(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {31}, m = "readIntLittleEndian")
    public static final class C20361 extends c {
        int label;
        /* synthetic */ Object result;

        public C20361(d<? super C20361> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readIntLittleEndian(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readLong$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {15}, m = "readLong")
    public static final class C20371 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20371(d<? super C20371> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readLong(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {35}, m = "readLongLittleEndian")
    public static final class C20381 extends c {
        int label;
        /* synthetic */ Object result;

        public C20381(d<? super C20381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readLongLittleEndian(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readShort$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {7}, m = "readShort")
    public static final class C20391 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20391(d<? super C20391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readShort(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ChannelLittleEndianKt", f = "ChannelLittleEndian.kt", l = {27}, m = "readShortLittleEndian")
    public static final class C20401 extends c {
        int label;
        /* synthetic */ Object result;

        public C20401(d<? super C20401> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelLittleEndianKt.readShortLittleEndian(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readDouble(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.core.ByteOrder r5, d7.d<? super java.lang.Double> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ChannelLittleEndianKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.ByteOrder r5 = (io.ktor.utils.io.core.ByteOrder) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.readDouble(r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L42
            return r4
        L42:
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 != r2) goto L4d
            return r6
        L4d:
            java.lang.Number r6 = (java.lang.Number) r6
            double r4 = r6.doubleValue()
            long r4 = java.lang.Double.doubleToRawLongBits(r4)
            long r4 = java.lang.Long.reverseBytes(r4)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            java.lang.Double r6 = new java.lang.Double
            r6.<init>(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readDouble(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, d7.d):java.lang.Object");
    }

    private static final Object readDouble$$forInline(ByteReadChannel byteReadChannel, ByteOrder byteOrder, d<? super Double> dVar) {
        Object obj = byteReadChannel.readDouble(dVar);
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? obj : Double.valueOf(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(((Number) obj).doubleValue()))));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readDoubleLittleEndian(io.ktor.utils.io.ByteReadChannel r4, d7.d<? super java.lang.Double> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20321
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20321) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readDouble(r0)
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L3b
            return r4
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            double r4 = r5.doubleValue()
            long r4 = java.lang.Double.doubleToRawLongBits(r4)
            long r4 = java.lang.Long.reverseBytes(r4)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            java.lang.Double r0 = new java.lang.Double
            r0.<init>(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readDoubleLittleEndian(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFloat(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.core.ByteOrder r5, d7.d<? super java.lang.Float> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20331
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20331) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.ByteOrder r5 = (io.ktor.utils.io.core.ByteOrder) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.readFloat(r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L42
            return r4
        L42:
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 != r2) goto L4d
            return r6
        L4d:
            java.lang.Number r6 = (java.lang.Number) r6
            float r4 = r6.floatValue()
            int r4 = java.lang.Float.floatToRawIntBits(r4)
            int r4 = java.lang.Integer.reverseBytes(r4)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readFloat(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, d7.d):java.lang.Object");
    }

    private static final Object readFloat$$forInline(ByteReadChannel byteReadChannel, ByteOrder byteOrder, d<? super Float> dVar) {
        Object obj = byteReadChannel.readFloat(dVar);
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? obj : Float.valueOf(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(((Number) obj).floatValue()))));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFloatLittleEndian(io.ktor.utils.io.ByteReadChannel r4, d7.d<? super java.lang.Float> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20341
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20341) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readFloat(r0)
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L3b
            return r4
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            float r4 = r5.floatValue()
            int r4 = java.lang.Float.floatToRawIntBits(r4)
            int r4 = java.lang.Integer.reverseBytes(r4)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readFloatLittleEndian(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readInt(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.core.ByteOrder r5, d7.d<? super java.lang.Integer> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20351
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ChannelLittleEndianKt$readInt$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20351) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readInt$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readInt$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.ByteOrder r5 = (io.ktor.utils.io.core.ByteOrder) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.readInt(r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L42
            return r4
        L42:
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 != r2) goto L4d
            return r6
        L4d:
            java.lang.Number r6 = (java.lang.Number) r6
            int r4 = r6.intValue()
            int r4 = java.lang.Integer.reverseBytes(r4)
            java.lang.Integer r5 = new java.lang.Integer
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readInt(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, d7.d):java.lang.Object");
    }

    private static final Object readInt$$forInline(ByteReadChannel byteReadChannel, ByteOrder byteOrder, d<? super Integer> dVar) {
        Object obj = byteReadChannel.readInt(dVar);
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? obj : Integer.valueOf(Integer.reverseBytes(((Number) obj).intValue()));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readIntLittleEndian(io.ktor.utils.io.ByteReadChannel r4, d7.d<? super java.lang.Integer> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20361
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20361) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readInt(r0)
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L3b
            return r4
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            int r4 = r5.intValue()
            int r4 = java.lang.Integer.reverseBytes(r4)
            java.lang.Integer r5 = new java.lang.Integer
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readIntLittleEndian(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readLong(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.core.ByteOrder r5, d7.d<? super java.lang.Long> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20371
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ChannelLittleEndianKt$readLong$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20371) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readLong$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readLong$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.ByteOrder r5 = (io.ktor.utils.io.core.ByteOrder) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.readLong(r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L42
            return r4
        L42:
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 != r2) goto L4d
            return r6
        L4d:
            java.lang.Number r6 = (java.lang.Number) r6
            long r4 = r6.longValue()
            long r4 = java.lang.Long.reverseBytes(r4)
            java.lang.Long r6 = new java.lang.Long
            r6.<init>(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readLong(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, d7.d):java.lang.Object");
    }

    private static final Object readLong$$forInline(ByteReadChannel byteReadChannel, ByteOrder byteOrder, d<? super Long> dVar) {
        Object obj = byteReadChannel.readLong(dVar);
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? obj : Long.valueOf(Long.reverseBytes(((Number) obj).longValue()));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readLongLittleEndian(io.ktor.utils.io.ByteReadChannel r4, d7.d<? super java.lang.Long> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20381
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20381) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readLong(r0)
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L3b
            return r4
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            long r4 = r5.longValue()
            long r4 = java.lang.Long.reverseBytes(r4)
            java.lang.Long r0 = new java.lang.Long
            r0.<init>(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readLongLittleEndian(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readShort(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.core.ByteOrder r5, d7.d<? super java.lang.Short> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20391
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ChannelLittleEndianKt$readShort$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20391) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readShort$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readShort$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.ByteOrder r5 = (io.ktor.utils.io.core.ByteOrder) r5
            k2.c.G(r6)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.readShort(r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L42
            return r4
        L42:
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 != r2) goto L4d
            return r6
        L4d:
            java.lang.Number r6 = (java.lang.Number) r6
            short r4 = r6.shortValue()
            short r4 = (short) r4
            short r4 = java.lang.Short.reverseBytes(r4)
            java.lang.Short r5 = new java.lang.Short
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readShort(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, d7.d):java.lang.Object");
    }

    private static final Object readShort$$forInline(ByteReadChannel byteReadChannel, ByteOrder byteOrder, d<? super Short> dVar) {
        Object obj = byteReadChannel.readShort(dVar);
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? obj : Short.valueOf(Short.reverseBytes(((Number) obj).shortValue()));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readShortLittleEndian(io.ktor.utils.io.ByteReadChannel r4, d7.d<? super java.lang.Short> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ChannelLittleEndianKt.C20401
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt.C20401) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readShort(r0)
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L3b
            return r4
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            short r4 = r5.shortValue()
            short r4 = (short) r4
            short r4 = java.lang.Short.reverseBytes(r4)
            java.lang.Short r5 = new java.lang.Short
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readShortLittleEndian(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    public static final <T> T reverseIfNeeded(T t2, ByteOrder byteOrder, l<? super T, ? extends T> lVar) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? t2 : (T) lVar.invoke(t2);
    }

    public static final <T> T toLittleEndian(ByteReadChannel byteReadChannel, T t2, l<? super T, ? extends T> lVar) {
        return (T) lVar.invoke(t2);
    }

    public static final Object writeDouble(ByteWriteChannel byteWriteChannel, double d4, ByteOrder byteOrder, d<? super t0> dVar) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            d4 = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d4)));
        }
        Object objWriteDouble = byteWriteChannel.writeDouble(d4, dVar);
        return objWriteDouble == a.f15033i ? objWriteDouble : t0.f22605a;
    }

    public static final Object writeDoubleLittleEndian(ByteWriteChannel byteWriteChannel, double d4, d<? super t0> dVar) {
        Object objWriteDouble = byteWriteChannel.writeDouble(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d4))), dVar);
        return objWriteDouble == a.f15033i ? objWriteDouble : t0.f22605a;
    }

    public static final Object writeFloat(ByteWriteChannel byteWriteChannel, float f10, ByteOrder byteOrder, d<? super t0> dVar) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            f10 = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f10)));
        }
        Object objWriteFloat = byteWriteChannel.writeFloat(f10, dVar);
        return objWriteFloat == a.f15033i ? objWriteFloat : t0.f22605a;
    }

    public static final Object writeFloatLittleEndian(ByteWriteChannel byteWriteChannel, float f10, d<? super t0> dVar) {
        Object objWriteFloat = byteWriteChannel.writeFloat(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f10))), dVar);
        return objWriteFloat == a.f15033i ? objWriteFloat : t0.f22605a;
    }

    public static final Object writeInt(ByteWriteChannel byteWriteChannel, int i10, ByteOrder byteOrder, d<? super t0> dVar) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            i10 = Integer.reverseBytes(i10);
        }
        Object objWriteInt = byteWriteChannel.writeInt(i10, dVar);
        return objWriteInt == a.f15033i ? objWriteInt : t0.f22605a;
    }

    public static final Object writeIntLittleEndian(ByteWriteChannel byteWriteChannel, int i10, d<? super t0> dVar) {
        Object objWriteInt = byteWriteChannel.writeInt(Integer.reverseBytes(i10), dVar);
        return objWriteInt == a.f15033i ? objWriteInt : t0.f22605a;
    }

    public static final Object writeLong(ByteWriteChannel byteWriteChannel, long j10, ByteOrder byteOrder, d<? super t0> dVar) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            j10 = Long.reverseBytes(j10);
        }
        Object objWriteLong = byteWriteChannel.writeLong(j10, dVar);
        return objWriteLong == a.f15033i ? objWriteLong : t0.f22605a;
    }

    public static final Object writeLongLittleEndian(ByteWriteChannel byteWriteChannel, long j10, d<? super t0> dVar) {
        Object objWriteLong = byteWriteChannel.writeLong(Long.reverseBytes(j10), dVar);
        return objWriteLong == a.f15033i ? objWriteLong : t0.f22605a;
    }

    public static final Object writeShort(ByteWriteChannel byteWriteChannel, short s10, ByteOrder byteOrder, d<? super t0> dVar) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            s10 = Short.reverseBytes(s10);
        }
        Object objWriteShort = byteWriteChannel.writeShort(s10, dVar);
        return objWriteShort == a.f15033i ? objWriteShort : t0.f22605a;
    }

    public static final Object writeShortLittleEndian(ByteWriteChannel byteWriteChannel, short s10, d<? super t0> dVar) {
        Object objWriteShort = byteWriteChannel.writeShort(Short.reverseBytes(s10), dVar);
        return objWriteShort == a.f15033i ? objWriteShort : t0.f22605a;
    }

    private static final <T> T toLittleEndian(ByteWriteChannel byteWriteChannel, T t2, l<? super T, ? extends T> lVar) {
        return (T) lVar.invoke(t2);
    }
}
