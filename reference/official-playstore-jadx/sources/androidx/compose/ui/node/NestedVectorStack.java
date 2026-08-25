package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\n0\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/node/NestedVectorStack;", "T", "", "<init>", "()V", "", "isNotEmpty", "()Z", "pop", "()Ljava/lang/Object;", "Landroidx/compose/runtime/collection/MutableVector;", "vector", "Lx6/t0;", "push", "(Landroidx/compose/runtime/collection/MutableVector;)V", "", ContentDisposition.Parameters.Size, "I", "", "currentIndexes", "[I", "", "vectors", "[Landroidx/compose/runtime/collection/MutableVector;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NestedVectorStack<T> {
    public static final int $stable = 8;
    private int size;
    private int[] currentIndexes = new int[16];
    private MutableVector<T>[] vectors = new MutableVector[16];

    public final boolean isNotEmpty() {
        int i10 = this.size;
        return i10 > 0 && this.currentIndexes[i10 - 1] >= 0;
    }

    public final T pop() {
        int i10 = this.size;
        if (i10 <= 0) {
            throw new IllegalStateException("Cannot call pop() on an empty stack. Guard with a call to isNotEmpty()");
        }
        int i11 = i10 - 1;
        int[] iArr = this.currentIndexes;
        int i12 = iArr[i11];
        MutableVector<T>[] mutableVectorArr = this.vectors;
        MutableVector<T> mutableVector = mutableVectorArr[i11];
        if (i12 > 0) {
            iArr[i11] = i12 - 1;
        } else if (i12 == 0) {
            mutableVectorArr[i11] = null;
            this.size = i10 - 1;
        }
        return mutableVector.getContent()[i12];
    }

    public final void push(MutableVector<T> vector) {
        if (vector.isEmpty()) {
            return;
        }
        int i10 = this.size;
        int[] iArr = this.currentIndexes;
        if (i10 >= iArr.length) {
            this.currentIndexes = Arrays.copyOf(iArr, iArr.length * 2);
            MutableVector<T>[] mutableVectorArr = this.vectors;
            this.vectors = (MutableVector[]) Arrays.copyOf(mutableVectorArr, mutableVectorArr.length * 2);
        }
        this.currentIndexes[i10] = vector.getSize() - 1;
        this.vectors[i10] = vector;
        this.size++;
    }
}
