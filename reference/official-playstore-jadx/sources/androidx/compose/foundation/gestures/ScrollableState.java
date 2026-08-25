package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001JC\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0003\u001a\u00020\u00022'\u0010\t\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\bH¦@¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableState;", "", "Landroidx/compose/foundation/MutatePriority;", "scrollPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "block", "scroll", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "", "delta", "dispatchRawDelta", "(F)F", "", "isScrollInProgress", "()Z", "getCanScrollForward", "canScrollForward", "getCanScrollBackward", "canScrollBackward", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ScrollableState {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean getCanScrollBackward(ScrollableState scrollableState) {
            return f.a(scrollableState);
        }

        @Deprecated
        public static boolean getCanScrollForward(ScrollableState scrollableState) {
            return f.b(scrollableState);
        }
    }

    float dispatchRawDelta(float delta);

    boolean getCanScrollBackward();

    boolean getCanScrollForward();

    boolean isScrollInProgress();

    Object scroll(MutatePriority mutatePriority, p<? super ScrollScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar);
}
