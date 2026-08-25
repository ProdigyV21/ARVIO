package androidx.compose.ui.node;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0015\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u0018\u0010\u0019J-\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ%\u0010#\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002¢\u0006\u0004\b#\u0010\u000bJ\r\u0010$\u001a\u00020\u0002¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0014¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\t¢\u0006\u0004\b(\u0010)R\u0016\u0010+\u001a\u00020*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0011\u0010\"\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b/\u0010%¨\u00060"}, d2 = {"Landroidx/compose/ui/node/IntStack;", "", "", "initialCapacity", "<init>", "(I)V", TtmlNode.START, TtmlNode.END, "elSize", "Lx6/t0;", "quickSort", "(III)V", "partition", "(III)I", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "j", "swapDiagonal", "(II)V", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "compareDiagonal", "(II)Z", "index", "get", "(I)I", "oldStart", "oldEnd", "newStart", "newEnd", "pushRange", "(IIII)V", "x", "y", ContentDisposition.Parameters.Size, "pushDiagonal", "pop", "()I", "isNotEmpty", "()Z", "sortDiagonals", "()V", "", "stack", "[I", "lastIndex", "I", "getSize", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class IntStack {
    private int lastIndex;
    private int[] stack;

    public IntStack(int i10) {
        this.stack = new int[i10];
    }

    private final boolean compareDiagonal(int a10, int b10) {
        int[] iArr = this.stack;
        int i10 = iArr[a10];
        int i11 = iArr[b10];
        return i10 < i11 || (i10 == i11 && iArr[a10 + 1] <= iArr[b10 + 1]);
    }

    private final int partition(int start, int end, int elSize) {
        int i10 = start - elSize;
        while (start < end) {
            if (compareDiagonal(start, end)) {
                i10 += elSize;
                swapDiagonal(i10, start);
            }
            start += elSize;
        }
        int i11 = i10 + elSize;
        swapDiagonal(i11, end);
        return i11;
    }

    private final void quickSort(int start, int end, int elSize) {
        if (start < end) {
            int iPartition = partition(start, end, elSize);
            quickSort(start, iPartition - elSize, elSize);
            quickSort(iPartition + elSize, end, elSize);
        }
    }

    private final void swapDiagonal(int i10, int j10) {
        int[] iArr = this.stack;
        MyersDiffKt.swap(iArr, i10, j10);
        MyersDiffKt.swap(iArr, i10 + 1, j10 + 1);
        MyersDiffKt.swap(iArr, i10 + 2, j10 + 2);
    }

    public final int get(int index) {
        return this.stack[index];
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getLastIndex() {
        return this.lastIndex;
    }

    public final boolean isNotEmpty() {
        return this.lastIndex != 0;
    }

    public final int pop() {
        int[] iArr = this.stack;
        int i10 = this.lastIndex - 1;
        this.lastIndex = i10;
        return iArr[i10];
    }

    public final void pushDiagonal(int x, int y10, int size) {
        int i10 = this.lastIndex;
        int i11 = i10 + 3;
        int[] iArr = this.stack;
        if (i11 >= iArr.length) {
            this.stack = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.stack;
        iArr2[i10] = x + size;
        iArr2[i10 + 1] = y10 + size;
        iArr2[i10 + 2] = size;
        this.lastIndex = i11;
    }

    public final void pushRange(int oldStart, int oldEnd, int newStart, int newEnd) {
        int i10 = this.lastIndex;
        int i11 = i10 + 4;
        int[] iArr = this.stack;
        if (i11 >= iArr.length) {
            this.stack = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.stack;
        iArr2[i10] = oldStart;
        iArr2[i10 + 1] = oldEnd;
        iArr2[i10 + 2] = newStart;
        iArr2[i10 + 3] = newEnd;
        this.lastIndex = i11;
    }

    public final void sortDiagonals() {
        int i10 = this.lastIndex;
        if (i10 % 3 != 0) {
            throw new IllegalStateException("Array size not a multiple of 3");
        }
        if (i10 > 3) {
            quickSort(0, i10 - 3, 3);
        }
    }
}
