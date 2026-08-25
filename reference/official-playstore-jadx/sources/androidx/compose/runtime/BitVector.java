package androidx.compose.runtime;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0011\u0010 \u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Landroidx/compose/runtime/BitVector;", "", "<init>", "()V", "", "index", "", "get", "(I)Z", "value", "Lx6/t0;", "set", "(IZ)V", "nextSet", "(I)I", "nextClear", TtmlNode.START, TtmlNode.END, "setRange", "(II)V", "", "toString", "()Ljava/lang/String;", "", "first", "J", "second", "", "others", "[J", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BitVector {
    public static final int $stable = 8;
    private long first;
    private long[] others;
    private long second;

    public final boolean get(int index) {
        int i10;
        if (index < 0 || index >= getSize()) {
            throw new IllegalStateException(("Index " + index + " out of bound").toString());
        }
        if (index < 64) {
            return ((1 << index) & this.first) != 0;
        }
        if (index < 128) {
            return ((1 << (index - 64)) & this.second) != 0;
        }
        long[] jArr = this.others;
        if (jArr != null && (index / 64) - 2 < jArr.length) {
            return ((1 << (index % 64)) & jArr[i10]) != 0;
        }
        return false;
    }

    public final int getSize() {
        long[] jArr = this.others;
        if (jArr != null) {
            return (jArr.length + 2) * 64;
        }
        return 128;
    }

    public final int nextClear(int index) {
        int size = getSize();
        while (index < size) {
            if (!get(index)) {
                return index;
            }
            index++;
        }
        return Integer.MAX_VALUE;
    }

    public final int nextSet(int index) {
        int size = getSize();
        while (index < size) {
            if (get(index)) {
                return index;
            }
            index++;
        }
        return Integer.MAX_VALUE;
    }

    public final void set(int index, boolean value) {
        if (index < 64) {
            long j10 = 1 << index;
            this.first = value ? this.first | j10 : this.first & (~j10);
            return;
        }
        if (index < 128) {
            long j11 = 1 << (index - 64);
            this.second = value ? this.second | j11 : this.second & (~j11);
            return;
        }
        int i10 = index / 64;
        int i11 = i10 - 2;
        long j12 = 1 << (index % 64);
        long[] jArrCopyOf = this.others;
        if (jArrCopyOf == null) {
            jArrCopyOf = new long[i10 - 1];
            this.others = jArrCopyOf;
        }
        if (i11 >= jArrCopyOf.length) {
            jArrCopyOf = Arrays.copyOf(jArrCopyOf, i10 - 1);
            this.others = jArrCopyOf;
        }
        long j13 = jArrCopyOf[i11];
        jArrCopyOf[i11] = value ? j12 | j13 : (~j12) & j13;
    }

    public final void setRange(int start, int end) {
        while (start < end) {
            set(start, true);
            start++;
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BitVector [");
        int size = getSize();
        boolean z = true;
        for (int i10 = 0; i10 < size; i10++) {
            if (get(i10)) {
                if (!z) {
                    sb2.append(", ");
                }
                sb2.append(i10);
                z = false;
            }
        }
        sb2.append(']');
        return sb2.toString();
    }
}
