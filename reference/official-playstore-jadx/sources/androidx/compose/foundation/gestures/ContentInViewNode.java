package androidx.compose.foundation.gestures;

import androidx.compose.foundation.relocation.BringIntoViewResponder;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import com.google.common.util.concurrent.r0;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import ka.j0;
import ka.k;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001SB'\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0017\u0010\u0010J\"\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ \u0010!\u001a\u00020\b*\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\"\u0010%\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001f\u0010*\u001a\u00020'*\u00020\u00192\u0006\u0010&\u001a\u00020\u0019H\u0082\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020'*\u00020+2\u0006\u0010&\u001a\u00020+H\u0082\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010)J\u0017\u0010.\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000eH\u0016¢\u0006\u0004\b.\u0010/J \u00101\u001a\u00020\u00112\u000e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e00H\u0096@¢\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\u00112\b\u00104\u001a\u0004\u0018\u000103¢\u0006\u0004\b5\u00106J\u0017\u00108\u001a\u00020\u00112\u0006\u00107\u001a\u000203H\u0016¢\u0006\u0004\b8\u00106J\u001a\u0010;\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0019H\u0016ø\u0001\u0000¢\u0006\u0004\b9\u0010:J-\u0010=\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b=\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010>R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010?R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010@R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010AR\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0018\u00107\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u0010ER\u0018\u0010F\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010ER\u0018\u0010G\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010@R*\u0010K\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u00198\u0000@BX\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010@R\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010R\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006T"}, d2 = {"Landroidx/compose/foundation/gestures/ContentInViewNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/foundation/relocation/BringIntoViewResponder;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/foundation/gestures/ScrollableState;", "scrollState", "", "reverseDirection", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "bringIntoViewSpec", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/ScrollableState;ZLandroidx/compose/foundation/gestures/BringIntoViewSpec;)V", "Landroidx/compose/ui/geometry/Rect;", "getFocusedChildBounds", "()Landroidx/compose/ui/geometry/Rect;", "Lx6/t0;", "launchAnimation", "()V", "", "calculateScrollDelta", "()F", "findBringIntoViewRequest", "childBounds", "Landroidx/compose/ui/unit/IntSize;", "containerSize", "computeDestination-O0kMr_c", "(Landroidx/compose/ui/geometry/Rect;J)Landroidx/compose/ui/geometry/Rect;", "computeDestination", ContentDisposition.Parameters.Size, "isMaxVisible-O0kMr_c", "(Landroidx/compose/ui/geometry/Rect;J)Z", "isMaxVisible", "Landroidx/compose/ui/geometry/Offset;", "relocationOffset-BMxPBkI", "(Landroidx/compose/ui/geometry/Rect;J)J", "relocationOffset", "other", "", "compareTo-TemP2vQ", "(JJ)I", "compareTo", "Landroidx/compose/ui/geometry/Size;", "compareTo-iLBOSCw", "localRect", "calculateRectForParent", "(Landroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "Lkotlin/Function0;", "bringChildIntoView", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "newBounds", "onFocusBoundsChanged", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "coordinates", "onPlaced", "onRemeasured-ozmzZPI", "(J)V", "onRemeasured", "state", "update", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/foundation/gestures/ScrollableState;", "Z", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "Landroidx/compose/foundation/gestures/BringIntoViewRequestPriorityQueue;", "bringIntoViewRequests", "Landroidx/compose/foundation/gestures/BringIntoViewRequestPriorityQueue;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "focusedChild", "focusedChildBoundsFromPreviousRemeasure", "Landroidx/compose/ui/geometry/Rect;", "trackingFocusedChild", "<set-?>", "viewportSize", "J", "getViewportSize-YbymL2g$foundation_release", "()J", "isAnimationRunning", "Landroidx/compose/foundation/gestures/UpdatableAnimationState;", "animationState", "Landroidx/compose/foundation/gestures/UpdatableAnimationState;", "Request", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ContentInViewNode extends Modifier.Node implements BringIntoViewResponder, LayoutAwareModifierNode {
    public static final int $stable = 8;
    private final UpdatableAnimationState animationState;
    private BringIntoViewSpec bringIntoViewSpec;
    private LayoutCoordinates coordinates;
    private LayoutCoordinates focusedChild;
    private Rect focusedChildBoundsFromPreviousRemeasure;
    private boolean isAnimationRunning;
    private Orientation orientation;
    private boolean reverseDirection;
    private ScrollableState scrollState;
    private boolean trackingFocusedChild;
    private final BringIntoViewRequestPriorityQueue bringIntoViewRequests = new BringIntoViewRequestPriorityQueue();
    private long viewportSize = IntSize.INSTANCE.m5849getZeroYbymL2g();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u001f\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/foundation/gestures/ContentInViewNode$Request;", "", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Rect;", "currentBounds", "Lka/k;", "Lx6/t0;", "continuation", "<init>", "(Lr7/a;Lka/k;)V", "", "toString", "()Ljava/lang/String;", "Lr7/a;", "getCurrentBounds", "()Lr7/a;", "Lka/k;", "getContinuation", "()Lka/k;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Request {
        public static final int $stable = 8;
        private final k<t0> continuation;
        private final r7.a<Rect> currentBounds;

        /* JADX WARN: Multi-variable type inference failed */
        public Request(r7.a<Rect> aVar, k<? super t0> kVar) {
            this.currentBounds = aVar;
            this.continuation = kVar;
        }

        public final k<t0> getContinuation() {
            return this.continuation;
        }

        public final r7.a<Rect> getCurrentBounds() {
            return this.currentBounds;
        }

        public String toString() {
            String strL;
            j0 j0Var = (j0) this.continuation.getContext().get(j0.f19600l);
            String str = j0Var != null ? j0Var.f19601i : null;
            StringBuilder sb2 = new StringBuilder("Request@");
            int iHashCode = hashCode();
            r0.c(16);
            sb2.append(Integer.toString(iHashCode, 16));
            if (str == null || (strL = a0.c.l("[", str, "](")) == null) {
                strL = "(";
            }
            sb2.append(strL);
            sb2.append("currentBounds()=");
            sb2.append(this.currentBounds.invoke());
            sb2.append(", continuation=");
            sb2.append(this.continuation);
            sb2.append(')');
            return sb2.toString();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2", f = "ContentInViewNode.kt", l = {190}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1", f = "ContentInViewNode.kt", l = {195}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<ScrollScope, d7.d<? super t0>, Object> {
            final /* synthetic */ v1 $animationJob;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ContentInViewNode this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "delta", "Lx6/t0;", "invoke", "(F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00201 extends r implements l<Float, t0> {
                final /* synthetic */ ScrollScope $$this$scroll;
                final /* synthetic */ v1 $animationJob;
                final /* synthetic */ ContentInViewNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00201(ContentInViewNode contentInViewNode, ScrollScope scrollScope, v1 v1Var) {
                    super(1);
                    this.this$0 = contentInViewNode;
                    this.$$this$scroll = scrollScope;
                    this.$animationJob = v1Var;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).floatValue());
                    return t0.f22605a;
                }

                public final void invoke(float f10) {
                    float f11 = this.this$0.reverseDirection ? 1.0f : -1.0f;
                    float fScrollBy = this.$$this$scroll.scrollBy(f11 * f10) * f11;
                    if (Math.abs(fScrollBy) < Math.abs(f10)) {
                        this.$animationJob.cancel(m0.a("Scroll animation cancelled because scroll was not consumed (" + fScrollBy + " < " + f10 + ')', null));
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00212 extends r implements r7.a<t0> {
                final /* synthetic */ ContentInViewNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00212(ContentInViewNode contentInViewNode) {
                    super(0);
                    this.this$0 = contentInViewNode;
                }

                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m299invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m299invoke() {
                    BringIntoViewRequestPriorityQueue bringIntoViewRequestPriorityQueue = this.this$0.bringIntoViewRequests;
                    ContentInViewNode contentInViewNode = this.this$0;
                    while (true) {
                        if (!bringIntoViewRequestPriorityQueue.requests.isNotEmpty()) {
                            break;
                        }
                        Rect rect = (Rect) ((Request) bringIntoViewRequestPriorityQueue.requests.last()).getCurrentBounds().invoke();
                        if (!(rect == null ? true : ContentInViewNode.m296isMaxVisibleO0kMr_c$default(contentInViewNode, rect, 0L, 1, null))) {
                            break;
                        } else {
                            ((Request) bringIntoViewRequestPriorityQueue.requests.removeAt(bringIntoViewRequestPriorityQueue.requests.getSize() - 1)).getContinuation().resumeWith(t0.f22605a);
                        }
                    }
                    if (this.this$0.trackingFocusedChild) {
                        Rect focusedChildBounds = this.this$0.getFocusedChildBounds();
                        if (focusedChildBounds != null && ContentInViewNode.m296isMaxVisibleO0kMr_c$default(this.this$0, focusedChildBounds, 0L, 1, null)) {
                            this.this$0.trackingFocusedChild = false;
                        }
                    }
                    this.this$0.animationState.setValue(this.this$0.calculateScrollDelta());
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ContentInViewNode contentInViewNode, v1 v1Var, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = contentInViewNode;
                this.$animationJob = v1Var;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$animationJob, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    ScrollScope scrollScope = (ScrollScope) this.L$0;
                    this.this$0.animationState.setValue(this.this$0.calculateScrollDelta());
                    UpdatableAnimationState updatableAnimationState = this.this$0.animationState;
                    C00201 c00201 = new C00201(this.this$0, scrollScope, this.$animationJob);
                    C00212 c00212 = new C00212(this.this$0);
                    this.label = 1;
                    Object objAnimateToZero = updatableAnimationState.animateToZero(c00201, c00212, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimateToZero == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }
        }

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = ContentInViewNode.this.new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            AnonymousClass2 anonymousClass2;
            Throwable th;
            CancellationException cancellationException;
            int i10 = this.label;
            CancellationException cancellationException2 = null;
            if (i10 == 0) {
                k2.c.G(obj);
                v1 v1VarK = m0.k(((k0) this.L$0).getCoroutineContext());
                try {
                    ContentInViewNode.this.isAnimationRunning = true;
                    ScrollableState scrollableState = ContentInViewNode.this.scrollState;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(ContentInViewNode.this, v1VarK, null);
                    this.label = 1;
                    anonymousClass2 = this;
                    try {
                        try {
                            Object objE = f.e(scrollableState, null, anonymousClass1, anonymousClass2, 1, null);
                            e7.a aVar = e7.a.f15033i;
                            if (objE == aVar) {
                                return aVar;
                            }
                        } catch (CancellationException e5) {
                            e = e5;
                            cancellationException = e;
                            cancellationException2 = cancellationException;
                            throw cancellationException2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        ContentInViewNode.this.isAnimationRunning = false;
                        ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(cancellationException2);
                        ContentInViewNode.this.trackingFocusedChild = false;
                        throw th;
                    }
                } catch (CancellationException e6) {
                    e = e6;
                    anonymousClass2 = this;
                    cancellationException = e;
                    cancellationException2 = cancellationException;
                    throw cancellationException2;
                } catch (Throwable th3) {
                    th = th3;
                    anonymousClass2 = this;
                    th = th;
                    ContentInViewNode.this.isAnimationRunning = false;
                    ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(cancellationException2);
                    ContentInViewNode.this.trackingFocusedChild = false;
                    throw th;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    k2.c.G(obj);
                    anonymousClass2 = this;
                } catch (CancellationException e10) {
                    cancellationException = e10;
                    anonymousClass2 = this;
                    cancellationException2 = cancellationException;
                    throw cancellationException2;
                } catch (Throwable th4) {
                    th = th4;
                    anonymousClass2 = this;
                    ContentInViewNode.this.isAnimationRunning = false;
                    ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(cancellationException2);
                    ContentInViewNode.this.trackingFocusedChild = false;
                    throw th;
                }
            }
            ContentInViewNode.this.bringIntoViewRequests.resumeAndRemoveAll();
            ContentInViewNode.this.isAnimationRunning = false;
            ContentInViewNode.this.bringIntoViewRequests.cancelAndRemoveAll(null);
            ContentInViewNode.this.trackingFocusedChild = false;
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public ContentInViewNode(Orientation orientation, ScrollableState scrollableState, boolean z, BringIntoViewSpec bringIntoViewSpec) {
        this.orientation = orientation;
        this.scrollState = scrollableState;
        this.reverseDirection = z;
        this.bringIntoViewSpec = bringIntoViewSpec;
        this.animationState = new UpdatableAnimationState(this.bringIntoViewSpec.getScrollAnimationSpec());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float calculateScrollDelta() {
        if (IntSize.m5842equalsimpl0(this.viewportSize, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            return 0.0f;
        }
        Rect rectFindBringIntoViewRequest = findBringIntoViewRequest();
        if (rectFindBringIntoViewRequest == null) {
            rectFindBringIntoViewRequest = this.trackingFocusedChild ? getFocusedChildBounds() : null;
            if (rectFindBringIntoViewRequest == null) {
                return 0.0f;
            }
        }
        long jM5854toSizeozmzZPI = IntSizeKt.m5854toSizeozmzZPI(this.viewportSize);
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i10 == 1) {
            return this.bringIntoViewSpec.calculateScrollDistance(rectFindBringIntoViewRequest.getTop(), rectFindBringIntoViewRequest.getBottom() - rectFindBringIntoViewRequest.getTop(), Size.m3302getHeightimpl(jM5854toSizeozmzZPI));
        }
        if (i10 == 2) {
            return this.bringIntoViewSpec.calculateScrollDistance(rectFindBringIntoViewRequest.getLeft(), rectFindBringIntoViewRequest.getRight() - rectFindBringIntoViewRequest.getLeft(), Size.m3305getWidthimpl(jM5854toSizeozmzZPI));
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: compareTo-TemP2vQ, reason: not valid java name */
    private final int m292compareToTemP2vQ(long j10, long j11) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i10 == 1) {
            return kotlin.jvm.internal.p.c(IntSize.m5843getHeightimpl(j10), IntSize.m5843getHeightimpl(j11));
        }
        if (i10 == 2) {
            return kotlin.jvm.internal.p.c(IntSize.m5844getWidthimpl(j10), IntSize.m5844getWidthimpl(j11));
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: compareTo-iLBOSCw, reason: not valid java name */
    private final int m293compareToiLBOSCw(long j10, long j11) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i10 == 1) {
            return Float.compare(Size.m3302getHeightimpl(j10), Size.m3302getHeightimpl(j11));
        }
        if (i10 == 2) {
            return Float.compare(Size.m3305getWidthimpl(j10), Size.m3305getWidthimpl(j11));
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: computeDestination-O0kMr_c, reason: not valid java name */
    private final Rect m294computeDestinationO0kMr_c(Rect childBounds, long containerSize) {
        return childBounds.m3273translatek4lQ0M(Offset.m3245unaryMinusF1C5BW0(m297relocationOffsetBMxPBkI(childBounds, containerSize)));
    }

    private final Rect findBringIntoViewRequest() {
        MutableVector mutableVector = this.bringIntoViewRequests.requests;
        int size = mutableVector.getSize();
        Rect rect = null;
        if (size > 0) {
            int i10 = size - 1;
            Object[] content = mutableVector.getContent();
            do {
                Rect rect2 = (Rect) ((Request) content[i10]).getCurrentBounds().invoke();
                if (rect2 != null) {
                    if (m293compareToiLBOSCw(rect2.m3269getSizeNHjbRc(), IntSizeKt.m5854toSizeozmzZPI(this.viewportSize)) > 0) {
                        return rect == null ? rect2 : rect;
                    }
                    rect = rect2;
                }
                i10--;
            } while (i10 >= 0);
        }
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getFocusedChildBounds() {
        LayoutCoordinates layoutCoordinates;
        LayoutCoordinates layoutCoordinates2 = this.coordinates;
        if (layoutCoordinates2 != null) {
            if (!layoutCoordinates2.isAttached()) {
                layoutCoordinates2 = null;
            }
            if (layoutCoordinates2 != null && (layoutCoordinates = this.focusedChild) != null) {
                if (!layoutCoordinates.isAttached()) {
                    layoutCoordinates = null;
                }
                if (layoutCoordinates != null) {
                    return layoutCoordinates2.localBoundingBoxOf(layoutCoordinates, false);
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: isMaxVisible-O0kMr_c, reason: not valid java name */
    private final boolean m295isMaxVisibleO0kMr_c(Rect rect, long j10) {
        long jM297relocationOffsetBMxPBkI = m297relocationOffsetBMxPBkI(rect, j10);
        return Math.abs(Offset.m3236getXimpl(jM297relocationOffsetBMxPBkI)) <= 0.5f && Math.abs(Offset.m3237getYimpl(jM297relocationOffsetBMxPBkI)) <= 0.5f;
    }

    /* JADX INFO: renamed from: isMaxVisible-O0kMr_c$default, reason: not valid java name */
    public static /* synthetic */ boolean m296isMaxVisibleO0kMr_c$default(ContentInViewNode contentInViewNode, Rect rect, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = contentInViewNode.viewportSize;
        }
        return contentInViewNode.m295isMaxVisibleO0kMr_c(rect, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchAnimation() {
        if (this.isAnimationRunning) {
            throw new IllegalStateException("launchAnimation called when previous animation was running");
        }
        m0.p(getCoroutineScope(), null, 4, new AnonymousClass2(null), 1);
    }

    /* JADX INFO: renamed from: relocationOffset-BMxPBkI, reason: not valid java name */
    private final long m297relocationOffsetBMxPBkI(Rect childBounds, long containerSize) {
        long jM5854toSizeozmzZPI = IntSizeKt.m5854toSizeozmzZPI(containerSize);
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.orientation.ordinal()];
        if (i10 == 1) {
            return OffsetKt.Offset(0.0f, this.bringIntoViewSpec.calculateScrollDistance(childBounds.getTop(), childBounds.getBottom() - childBounds.getTop(), Size.m3302getHeightimpl(jM5854toSizeozmzZPI)));
        }
        if (i10 == 2) {
            return OffsetKt.Offset(this.bringIntoViewSpec.calculateScrollDistance(childBounds.getLeft(), childBounds.getRight() - childBounds.getLeft(), Size.m3305getWidthimpl(jM5854toSizeozmzZPI)), 0.0f);
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Object bringChildIntoView(r7.a<Rect> aVar, d7.d<? super t0> dVar) {
        Rect rect = (Rect) aVar.invoke();
        if (rect != null && !m296isMaxVisibleO0kMr_c$default(this, rect, 0L, 1, null)) {
            ka.l lVar = new ka.l(1, t7.a.A(dVar));
            lVar.s();
            if (this.bringIntoViewRequests.enqueue(new Request(aVar, lVar)) && !this.isAnimationRunning) {
                launchAnimation();
            }
            Object objR = lVar.r();
            if (objR == e7.a.f15033i) {
                return objR;
            }
        }
        return t0.f22605a;
    }

    @Override // androidx.compose.foundation.relocation.BringIntoViewResponder
    public Rect calculateRectForParent(Rect localRect) {
        if (IntSize.m5842equalsimpl0(this.viewportSize, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            throw new IllegalStateException("Expected BringIntoViewRequester to not be used before parents are placed.");
        }
        return m294computeDestinationO0kMr_c(localRect, this.viewportSize);
    }

    /* JADX INFO: renamed from: getViewportSize-YbymL2g$foundation_release, reason: not valid java name and from getter */
    public final long getViewportSize() {
        return this.viewportSize;
    }

    public final void onFocusBoundsChanged(LayoutCoordinates newBounds) {
        this.focusedChild = newBounds;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onPlaced(LayoutCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    public void mo236onRemeasuredozmzZPI(long size) {
        Rect focusedChildBounds;
        long j10 = this.viewportSize;
        this.viewportSize = size;
        if (m292compareToTemP2vQ(size, j10) < 0 && (focusedChildBounds = getFocusedChildBounds()) != null) {
            Rect rect = this.focusedChildBoundsFromPreviousRemeasure;
            if (rect == null) {
                rect = focusedChildBounds;
            }
            if (!this.isAnimationRunning && !this.trackingFocusedChild && m295isMaxVisibleO0kMr_c(rect, j10) && !m295isMaxVisibleO0kMr_c(focusedChildBounds, size)) {
                this.trackingFocusedChild = true;
                launchAnimation();
            }
            this.focusedChildBoundsFromPreviousRemeasure = focusedChildBounds;
        }
    }

    public final void update(Orientation orientation, ScrollableState state, boolean reverseDirection, BringIntoViewSpec bringIntoViewSpec) {
        this.orientation = orientation;
        this.scrollState = state;
        this.reverseDirection = reverseDirection;
        this.bringIntoViewSpec = bringIntoViewSpec;
    }
}
