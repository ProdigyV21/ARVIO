package androidx.compose.foundation.gestures;

import android.view.KeyEvent;
import androidx.compose.animation.SplineBasedDecayKt;
import androidx.compose.foundation.FocusedBoundsObserverNode;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewResponderNode;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005BM\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019JS\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u001a\u0010\u0016J\u000f\u0010\u001b\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001c\u0010\u0019J\u0017\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010%\u001a\u00020\f2\u0006\u0010\"\u001a\u00020!H\u0016ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001a\u0010'\u001a\u00020\f2\u0006\u0010\"\u001a\u00020!H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010$R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010(R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010)R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010*R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010+R\u0016\u0010\u000e\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010+R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010,R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010-R\u0017\u0010/\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0017\u00104\u001a\u0002038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0017\u00109\u001a\u0002088\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010>\u001a\u00020=8\u0006¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0017\u0010C\u001a\u00020B8\u0006¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u0017\u0010H\u001a\u00020G8\u0006¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u0017\u0010M\u001a\u00020L8\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Q"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/foundation/gestures/ScrollableState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/foundation/OverscrollEffect;", "overscrollEffect", "", "enabled", "reverseDirection", "Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "bringIntoViewSpec", "<init>", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/OverscrollEffect;ZZLandroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/BringIntoViewSpec;)V", "Lx6/t0;", "updateDefaultFlingBehavior", "()V", "update", "onAttach", "onObservedReadsChanged", "Landroidx/compose/ui/focus/FocusProperties;", "focusProperties", "applyFocusProperties", "(Landroidx/compose/ui/focus/FocusProperties;)V", "Landroidx/compose/ui/input/key/KeyEvent;", "event", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onPreKeyEvent-ZmokQxo", "onPreKeyEvent", "Landroidx/compose/foundation/gestures/ScrollableState;", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/foundation/OverscrollEffect;", "Z", "Landroidx/compose/foundation/gestures/FlingBehavior;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "getNestedScrollDispatcher", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "Landroidx/compose/foundation/gestures/DefaultFlingBehavior;", "defaultFlingBehavior", "Landroidx/compose/foundation/gestures/DefaultFlingBehavior;", "getDefaultFlingBehavior", "()Landroidx/compose/foundation/gestures/DefaultFlingBehavior;", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "getScrollingLogic", "()Landroidx/compose/foundation/gestures/ScrollingLogic;", "Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "nestedScrollConnection", "Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "getNestedScrollConnection", "()Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "Landroidx/compose/foundation/gestures/ContentInViewNode;", "contentInViewNode", "Landroidx/compose/foundation/gestures/ContentInViewNode;", "getContentInViewNode", "()Landroidx/compose/foundation/gestures/ContentInViewNode;", "Landroidx/compose/foundation/gestures/ModifierLocalScrollableContainerProvider;", "scrollableContainer", "Landroidx/compose/foundation/gestures/ModifierLocalScrollableContainerProvider;", "getScrollableContainer", "()Landroidx/compose/foundation/gestures/ModifierLocalScrollableContainerProvider;", "Landroidx/compose/foundation/gestures/ScrollableGesturesNode;", "scrollableGesturesNode", "Landroidx/compose/foundation/gestures/ScrollableGesturesNode;", "getScrollableGesturesNode", "()Landroidx/compose/foundation/gestures/ScrollableGesturesNode;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableNode extends DelegatingNode implements ObserverModifierNode, CompositionLocalConsumerModifierNode, FocusPropertiesModifierNode, KeyInputModifierNode {
    private final ContentInViewNode contentInViewNode;
    private final DefaultFlingBehavior defaultFlingBehavior;
    private boolean enabled;
    private FlingBehavior flingBehavior;
    private MutableInteractionSource interactionSource;
    private final ScrollableNestedScrollConnection nestedScrollConnection;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private Orientation orientation;
    private OverscrollEffect overscrollEffect;
    private boolean reverseDirection;
    private final ModifierLocalScrollableContainerProvider scrollableContainer;
    private final ScrollableGesturesNode scrollableGesturesNode;
    private final ScrollingLogic scrollingLogic;
    private ScrollableState state;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<LayoutCoordinates, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutCoordinates) obj);
            return t0.f22605a;
        }

        public final void invoke(LayoutCoordinates layoutCoordinates) {
            ScrollableNode.this.getContentInViewNode().onFocusBoundsChanged(layoutCoordinates);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$onAttach$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03221 extends r implements r7.a<t0> {
        public C03221() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m372invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m372invoke() {
            CompositionLocalConsumerModifierNodeKt.currentValueOf(ScrollableNode.this, CompositionLocalsKt.getLocalDensity());
        }
    }

    public ScrollableNode(ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z5, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        this.state = scrollableState;
        this.orientation = orientation;
        this.overscrollEffect = overscrollEffect;
        this.enabled = z;
        this.reverseDirection = z5;
        this.flingBehavior = flingBehavior;
        this.interactionSource = mutableInteractionSource;
        NestedScrollDispatcher nestedScrollDispatcher = new NestedScrollDispatcher();
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        DefaultFlingBehavior defaultFlingBehavior = new DefaultFlingBehavior(SplineBasedDecayKt.splineBasedDecay(ScrollableKt.UnityDensity), null, 2, null);
        this.defaultFlingBehavior = defaultFlingBehavior;
        ScrollableState scrollableState2 = this.state;
        Orientation orientation2 = this.orientation;
        FlingBehavior flingBehavior2 = defaultFlingBehavior;
        OverscrollEffect overscrollEffect2 = this.overscrollEffect;
        boolean z10 = this.reverseDirection;
        FlingBehavior flingBehavior3 = this.flingBehavior;
        ScrollingLogic scrollingLogic = new ScrollingLogic(scrollableState2, orientation2, overscrollEffect2, z10, flingBehavior3 != null ? flingBehavior3 : flingBehavior2, nestedScrollDispatcher);
        this.scrollingLogic = scrollingLogic;
        ScrollableNestedScrollConnection scrollableNestedScrollConnection = new ScrollableNestedScrollConnection(scrollingLogic, this.enabled);
        this.nestedScrollConnection = scrollableNestedScrollConnection;
        ContentInViewNode contentInViewNode = (ContentInViewNode) delegate(new ContentInViewNode(this.orientation, this.state, this.reverseDirection, bringIntoViewSpec));
        this.contentInViewNode = contentInViewNode;
        this.scrollableContainer = (ModifierLocalScrollableContainerProvider) delegate(new ModifierLocalScrollableContainerProvider(this.enabled));
        delegate(NestedScrollNodeKt.nestedScrollModifierNode(scrollableNestedScrollConnection, nestedScrollDispatcher));
        delegate(FocusTargetModifierNodeKt.FocusTargetModifierNode());
        delegate(new BringIntoViewResponderNode(contentInViewNode));
        delegate(new FocusedBoundsObserverNode(new AnonymousClass1()));
        this.scrollableGesturesNode = (ScrollableGesturesNode) delegate(new ScrollableGesturesNode(scrollingLogic, this.orientation, this.enabled, nestedScrollDispatcher, this.interactionSource));
    }

    private final void updateDefaultFlingBehavior() {
        this.defaultFlingBehavior.setFlingDecay(SplineBasedDecayKt.splineBasedDecay((Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalDensity())));
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
    }

    public final ContentInViewNode getContentInViewNode() {
        return this.contentInViewNode;
    }

    public final DefaultFlingBehavior getDefaultFlingBehavior() {
        return this.defaultFlingBehavior;
    }

    public final ScrollableNestedScrollConnection getNestedScrollConnection() {
        return this.nestedScrollConnection;
    }

    public final NestedScrollDispatcher getNestedScrollDispatcher() {
        return this.nestedScrollDispatcher;
    }

    public final ModifierLocalScrollableContainerProvider getScrollableContainer() {
        return this.scrollableContainer;
    }

    public final ScrollableGesturesNode getScrollableGesturesNode() {
        return this.scrollableGesturesNode;
    }

    public final ScrollingLogic getScrollingLogic() {
        return this.scrollingLogic;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDefaultFlingBehavior();
        ObserverModifierNodeKt.observeReads(this, new C03221());
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
    public boolean mo147onKeyEventZmokQxo(KeyEvent event) {
        long jOffset;
        if (this.enabled) {
            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
            Key.Companion companion = Key.INSTANCE;
            if ((Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4281getPageDownEK5gGoQ()) || Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(event), companion.m4282getPageUpEK5gGoQ())) && KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(event), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY()) && !KeyEvent_androidKt.m4405isCtrlPressedZmokQxo(event)) {
                ScrollingLogic scrollingLogic = this.scrollingLogic;
                if (this.orientation == Orientation.Vertical) {
                    int iM5843getHeightimpl = IntSize.m5843getHeightimpl(this.contentInViewNode.getViewportSize());
                    jOffset = OffsetKt.Offset(0.0f, Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(event), companion.m4282getPageUpEK5gGoQ()) ? iM5843getHeightimpl : -iM5843getHeightimpl);
                } else {
                    int iM5844getWidthimpl = IntSize.m5844getWidthimpl(this.contentInViewNode.getViewportSize());
                    jOffset = OffsetKt.Offset(Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(event), companion.m4282getPageUpEK5gGoQ()) ? iM5844getWidthimpl : -iM5844getWidthimpl, 0.0f);
                }
                m0.p(getCoroutineScope(), null, 0, new ScrollableNode$onKeyEvent$1$1(scrollingLogic, jOffset, null), 3);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        updateDefaultFlingBehavior();
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
    public boolean mo149onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    public final void update(ScrollableState state, Orientation orientation, OverscrollEffect overscrollEffect, boolean enabled, boolean reverseDirection, FlingBehavior flingBehavior, MutableInteractionSource interactionSource, BringIntoViewSpec bringIntoViewSpec) {
        if (this.enabled != enabled) {
            this.nestedScrollConnection.setEnabled(enabled);
            this.scrollableContainer.setEnabled(enabled);
        }
        this.scrollingLogic.update(state, orientation, overscrollEffect, reverseDirection, flingBehavior == null ? this.defaultFlingBehavior : flingBehavior, this.nestedScrollDispatcher);
        this.scrollableGesturesNode.update(orientation, enabled, interactionSource);
        this.contentInViewNode.update(orientation, state, reverseDirection, bringIntoViewSpec);
        this.state = state;
        this.orientation = orientation;
        this.overscrollEffect = overscrollEffect;
        this.enabled = enabled;
        this.reverseDirection = reverseDirection;
        this.flingBehavior = flingBehavior;
        this.interactionSource = interactionSource;
    }
}
