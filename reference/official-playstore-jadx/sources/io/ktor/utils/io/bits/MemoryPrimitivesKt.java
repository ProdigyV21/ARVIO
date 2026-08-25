package io.ktor.utils.io.bits;

import g8.b;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u001a\"\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\"\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\b\u001a*\u0010\r\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0003H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a*\u0010\r\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\u000e\u001a\"\u0010\u0012\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\"\u0010\u0012\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0013\u001a*\u0010\u0016\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u000fH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a*\u0010\u0016\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u000fH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0017\u001a\"\u0010\u001b\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a\"\u0010\u001b\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001c\u001a*\u0010\u001f\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0018H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a*\u0010\u001f\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0018H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010 \u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "offset", "Lx6/r0;", "loadUShortAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)S", "loadUShortAt", "", "(Ljava/nio/ByteBuffer;J)S", "value", "Lx6/t0;", "storeUShortAt-4ET0KQI", "(Ljava/nio/ByteBuffer;IS)V", "storeUShortAt", "(Ljava/nio/ByteBuffer;JS)V", "Lx6/m0;", "loadUIntAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)I", "loadUIntAt", "(Ljava/nio/ByteBuffer;J)I", "storeUIntAt-c9EmHqw", "(Ljava/nio/ByteBuffer;II)V", "storeUIntAt", "(Ljava/nio/ByteBuffer;JI)V", "Lx6/o0;", "loadULongAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)J", "loadULongAt", "(Ljava/nio/ByteBuffer;J)J", "storeULongAt-zwzI6Wg", "(Ljava/nio/ByteBuffer;IJ)V", "storeULongAt", "(Ljava/nio/ByteBuffer;JJ)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MemoryPrimitivesKt {
    /* JADX INFO: renamed from: loadUIntAt-eY85DW0, reason: not valid java name */
    public static final int m6798loadUIntAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getInt(i10);
    }

    /* JADX INFO: renamed from: loadULongAt-eY85DW0, reason: not valid java name */
    public static final long m6800loadULongAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getLong(i10);
    }

    /* JADX INFO: renamed from: loadUShortAt-eY85DW0, reason: not valid java name */
    public static final short m6802loadUShortAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getShort(i10);
    }

    /* JADX INFO: renamed from: storeUIntAt-c9EmHqw, reason: not valid java name */
    public static final void m6804storeUIntAtc9EmHqw(ByteBuffer byteBuffer, int i10, int i11) {
        byteBuffer.putInt(i10, i11);
    }

    /* JADX INFO: renamed from: storeULongAt-zwzI6Wg, reason: not valid java name */
    public static final void m6806storeULongAtzwzI6Wg(ByteBuffer byteBuffer, int i10, long j10) {
        byteBuffer.putLong(i10, j10);
    }

    /* JADX INFO: renamed from: storeUShortAt-4ET0KQI, reason: not valid java name */
    public static final void m6808storeUShortAt4ET0KQI(ByteBuffer byteBuffer, int i10, short s10) {
        byteBuffer.putShort(i10, s10);
    }

    /* JADX INFO: renamed from: loadUIntAt-eY85DW0, reason: not valid java name */
    public static final int m6799loadUIntAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getInt((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadULongAt-eY85DW0, reason: not valid java name */
    public static final long m6801loadULongAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getLong((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadUShortAt-eY85DW0, reason: not valid java name */
    public static final short m6803loadUShortAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getShort((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: storeUIntAt-c9EmHqw, reason: not valid java name */
    public static final void m6805storeUIntAtc9EmHqw(ByteBuffer byteBuffer, long j10, int i10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putInt((int) j10, i10);
    }

    /* JADX INFO: renamed from: storeULongAt-zwzI6Wg, reason: not valid java name */
    public static final void m6807storeULongAtzwzI6Wg(ByteBuffer byteBuffer, long j10, long j11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putLong((int) j10, j11);
    }

    /* JADX INFO: renamed from: storeUShortAt-4ET0KQI, reason: not valid java name */
    public static final void m6809storeUShortAt4ET0KQI(ByteBuffer byteBuffer, long j10, short s10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putShort((int) j10, s10);
    }
}
