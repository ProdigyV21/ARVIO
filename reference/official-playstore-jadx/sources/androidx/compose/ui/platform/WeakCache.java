package androidx.compose.ui.platform;

import androidx.compose.runtime.collection.MutableVector;
import io.ktor.http.ContentDisposition;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0004J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\n\u0010\u000bR \u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/platform/WeakCache;", "T", "", "<init>", "()V", "Lx6/t0;", "clearWeakReferences", "element", "push", "(Ljava/lang/Object;)V", "pop", "()Ljava/lang/Object;", "Landroidx/compose/runtime/collection/MutableVector;", "Ljava/lang/ref/Reference;", "values", "Landroidx/compose/runtime/collection/MutableVector;", "Ljava/lang/ref/ReferenceQueue;", "referenceQueue", "Ljava/lang/ref/ReferenceQueue;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WeakCache<T> {
    public static final int $stable = 8;
    private final MutableVector<Reference<T>> values = new MutableVector<>(new Reference[16], 0);
    private final ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();

    private final void clearWeakReferences() {
        Reference<? extends T> referencePoll;
        do {
            referencePoll = this.referenceQueue.poll();
            if (referencePoll != null) {
                this.values.remove(referencePoll);
            }
        } while (referencePoll != null);
    }

    public final int getSize() {
        clearWeakReferences();
        return this.values.getSize();
    }

    public final T pop() {
        clearWeakReferences();
        while (this.values.isNotEmpty()) {
            T t2 = (T) ((Reference) androidx.compose.material3.d.h(this.values, 1)).get();
            if (t2 != null) {
                return t2;
            }
        }
        return null;
    }

    public final void push(T element) {
        clearWeakReferences();
        this.values.add(new WeakReference(element, this.referenceQueue));
    }
}
