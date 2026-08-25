package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.IntSizeKt;
import d7.d;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bJ\u0015\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u0005J\u000f\u0010\u000b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\bJ\u001a\u0010\u000e\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewRequesterNode;", "Landroidx/compose/foundation/relocation/BringIntoViewChildNode;", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "requester", "<init>", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;)V", "Lx6/t0;", "disposeRequester", "()V", "onAttach", "updateRequester", "onDetach", "Landroidx/compose/ui/geometry/Rect;", "rect", "bringIntoView", "(Landroidx/compose/ui/geometry/Rect;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BringIntoViewRequesterNode extends BringIntoViewChildNode {
    public static final int $stable = 8;
    private BringIntoViewRequester requester;

    /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewRequesterNode$bringIntoView$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Rect;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<Rect> {
        final /* synthetic */ Rect $rect;
        final /* synthetic */ BringIntoViewRequesterNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Rect rect, BringIntoViewRequesterNode bringIntoViewRequesterNode) {
            super(0);
            this.$rect = rect;
            this.this$0 = bringIntoViewRequesterNode;
        }

        @Override // r7.a
        public final Rect invoke() {
            Rect rect = this.$rect;
            if (rect != null) {
                return rect;
            }
            LayoutCoordinates layoutCoordinates = this.this$0.getLayoutCoordinates();
            if (layoutCoordinates != null) {
                return SizeKt.m3326toRectuvyYCjk(IntSizeKt.m5854toSizeozmzZPI(layoutCoordinates.mo4638getSizeYbymL2g()));
            }
            return null;
        }
    }

    public BringIntoViewRequesterNode(BringIntoViewRequester bringIntoViewRequester) {
        this.requester = bringIntoViewRequester;
    }

    private final void disposeRequester() {
        BringIntoViewRequester bringIntoViewRequester = this.requester;
        if (bringIntoViewRequester instanceof BringIntoViewRequesterImpl) {
            ((BringIntoViewRequesterImpl) bringIntoViewRequester).getModifiers().remove(this);
        }
    }

    public final Object bringIntoView(Rect rect, d<? super t0> dVar) {
        Object objBringChildIntoView;
        BringIntoViewParent parent = getParent();
        LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
        t0 t0Var = t0.f22605a;
        return (layoutCoordinates != null && (objBringChildIntoView = parent.bringChildIntoView(layoutCoordinates, new AnonymousClass2(rect, this), dVar)) == e7.a.f15033i) ? objBringChildIntoView : t0Var;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateRequester(this.requester);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        disposeRequester();
    }

    public final void updateRequester(BringIntoViewRequester requester) {
        disposeRequester();
        if (requester instanceof BringIntoViewRequesterImpl) {
            ((BringIntoViewRequesterImpl) requester).getModifiers().add(this);
        }
        this.requester = requester;
    }
}
