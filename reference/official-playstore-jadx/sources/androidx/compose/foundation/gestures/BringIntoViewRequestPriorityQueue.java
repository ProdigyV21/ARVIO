package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.ContentInViewNode;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import ka.k;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ@\u0010\u0012\u001a\u00020\u00102#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u000bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0003J@\u0010\u0015\u001a\u00020\u00102#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\u000bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0015\u0010\u0013J\u0017\u0010\u0018\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0011\u0010 \u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Landroidx/compose/foundation/gestures/BringIntoViewRequestPriorityQueue;", "", "<init>", "()V", "", "isEmpty", "()Z", "Landroidx/compose/foundation/gestures/ContentInViewNode$Request;", "request", "enqueue", "(Landroidx/compose/foundation/gestures/ContentInViewNode$Request;)Z", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Rect;", "Lx6/y;", ContentDisposition.Parameters.Name, "bounds", "Lx6/t0;", "block", "forEachFromSmallest", "(Lr7/l;)V", "resumeAndRemoveAll", "resumeAndRemoveWhile", "", "cause", "cancelAndRemoveAll", "(Ljava/lang/Throwable;)V", "Landroidx/compose/runtime/collection/MutableVector;", "requests", "Landroidx/compose/runtime/collection/MutableVector;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BringIntoViewRequestPriorityQueue {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector<ContentInViewNode.Request> requests = new MutableVector<>(new ContentInViewNode.Request[16], 0);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.BringIntoViewRequestPriorityQueue$enqueue$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        final /* synthetic */ ContentInViewNode.Request $request;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ContentInViewNode.Request request) {
            super(1);
            this.$request = request;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            BringIntoViewRequestPriorityQueue.this.requests.remove(this.$request);
        }
    }

    public final void cancelAndRemoveAll(Throwable cause) {
        MutableVector<ContentInViewNode.Request> mutableVector = this.requests;
        int size = mutableVector.getSize();
        k[] kVarArr = new k[size];
        for (int i10 = 0; i10 < size; i10++) {
            kVarArr[i10] = mutableVector.getContent()[i10].getContinuation();
        }
        for (int i11 = 0; i11 < size; i11++) {
            kVarArr[i11].cancel(cause);
        }
        if (!this.requests.isEmpty()) {
            throw new IllegalStateException("uncancelled requests present");
        }
    }

    public final boolean enqueue(ContentInViewNode.Request request) {
        Rect rect = (Rect) request.getCurrentBounds().invoke();
        if (rect == null) {
            request.getContinuation().resumeWith(t0.f22605a);
            return false;
        }
        request.getContinuation().c(new AnonymousClass1(request));
        int i10 = new i(0, this.requests.getSize() - 1, 1).f22620l;
        if (i10 >= 0) {
            while (true) {
                Rect rect2 = (Rect) this.requests.getContent()[i10].getCurrentBounds().invoke();
                if (rect2 != null) {
                    Rect rectIntersect = rect.intersect(rect2);
                    if (p.a(rectIntersect, rect)) {
                        this.requests.add(i10 + 1, request);
                        return true;
                    }
                    if (!p.a(rectIntersect, rect2)) {
                        CancellationException cancellationException = new CancellationException("bringIntoView call interrupted by a newer, non-overlapping call");
                        int size = this.requests.getSize() - 1;
                        if (size <= i10) {
                            while (true) {
                                this.requests.getContent()[i10].getContinuation().cancel(cancellationException);
                                if (size == i10) {
                                    break;
                                }
                                size++;
                            }
                        }
                    }
                }
                if (i10 == 0) {
                    break;
                }
                i10--;
            }
        }
        this.requests.add(0, request);
        return true;
    }

    public final void forEachFromSmallest(l<? super Rect, t0> block) {
        MutableVector mutableVector = this.requests;
        int size = mutableVector.getSize();
        if (size > 0) {
            int i10 = size - 1;
            Object[] content = mutableVector.getContent();
            do {
                block.invoke(((ContentInViewNode.Request) content[i10]).getCurrentBounds().invoke());
                i10--;
            } while (i10 >= 0);
        }
    }

    public final int getSize() {
        return this.requests.getSize();
    }

    public final boolean isEmpty() {
        return this.requests.isEmpty();
    }

    public final void resumeAndRemoveAll() {
        int i10 = 0;
        int i11 = new i(0, this.requests.getSize() - 1, 1).f22620l;
        if (i11 >= 0) {
            while (true) {
                this.requests.getContent()[i10].getContinuation().resumeWith(t0.f22605a);
                if (i10 == i11) {
                    break;
                } else {
                    i10++;
                }
            }
        }
        this.requests.clear();
    }

    public final void resumeAndRemoveWhile(l<? super Rect, Boolean> block) {
        while (this.requests.isNotEmpty() && ((Boolean) block.invoke(((ContentInViewNode.Request) this.requests.last()).getCurrentBounds().invoke())).booleanValue()) {
            ((ContentInViewNode.Request) this.requests.removeAt(this.requests.getSize() - 1)).getContinuation().resumeWith(t0.f22605a);
        }
    }
}
