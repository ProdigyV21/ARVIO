package androidx.compose.ui.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0019\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\b\u001a\u0019\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0086\b\u001a\u0011\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0001H\u0086\b¨\u0006\f"}, d2 = {"packFloats", "", "val1", "", "val2", "packInts", "", "unpackFloat1", "value", "unpackFloat2", "unpackInt1", "unpackInt2", "ui-util_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InlineClassHelperKt {
    public static final long packFloats(float f10, float f11) {
        return (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
    }

    public static final long packInts(int i10, int i11) {
        return (((long) i11) & 4294967295L) | (((long) i10) << 32);
    }

    public static final float unpackFloat1(long j10) {
        return Float.intBitsToFloat((int) (j10 >> 32));
    }

    public static final float unpackFloat2(long j10) {
        return Float.intBitsToFloat((int) (j10 & 4294967295L));
    }

    public static final int unpackInt1(long j10) {
        return (int) (j10 >> 32);
    }

    public static final int unpackInt2(long j10) {
        return (int) (j10 & 4294967295L);
    }
}
