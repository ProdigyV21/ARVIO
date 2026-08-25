package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.f;
import androidx.compose.ui.semantics.Role;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B;\b\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000f\u0010\u0010JF\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0004ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\rH\u0004¢\u0006\u0004\b\u0015\u0010\u0014J*\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001f\u0010\u0014J\u001a\u0010$\u001a\u00020\u00062\u0006\u0010!\u001a\u00020 H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001a\u0010&\u001a\u00020\u00062\u0006\u0010!\u001a\u00020 H\u0016ø\u0001\u0000¢\u0006\u0004\b%\u0010#R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010'R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010(R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010)R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010*R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010+R\u001a\u0010-\u001a\u00020,8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u0014\u00104\u001a\u0002018&X¦\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00108\u001a\u0002058&X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u00107\u0082\u0001\u0002:;\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "", "onClickLabel", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;)V", "updateCommon-XHw0xAI", "updateCommon", "onDetach", "()V", "disposeInteractionSource", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "Landroidx/compose/ui/input/key/KeyEvent;", "event", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onPreKeyEvent-ZmokQxo", "onPreKeyEvent", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Z", "Ljava/lang/String;", "Landroidx/compose/ui/semantics/Role;", "Lr7/a;", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "interactionData", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "getInteractionData", "()Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "Landroidx/compose/foundation/AbstractClickablePointerInputNode;", "getClickablePointerInputNode", "()Landroidx/compose/foundation/AbstractClickablePointerInputNode;", "clickablePointerInputNode", "Landroidx/compose/foundation/ClickableSemanticsNode;", "getClickableSemanticsNode", "()Landroidx/compose/foundation/ClickableSemanticsNode;", "clickableSemanticsNode", "InteractionData", "Landroidx/compose/foundation/ClickableNode;", "Landroidx/compose/foundation/CombinedClickableNodeImpl;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class AbstractClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode {
    private boolean enabled;
    private final InteractionData interactionData;
    private MutableInteractionSource interactionSource;
    private r7.a<t0> onClick;
    private String onClickLabel;
    private Role role;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "", "()V", "centreOffset", "Landroidx/compose/ui/geometry/Offset;", "getCentreOffset-F1C5BW0", "()J", "setCentreOffset-k-4lQ0M", "(J)V", "J", "currentKeyPressInteractions", "", "Landroidx/compose/ui/input/key/Key;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "getCurrentKeyPressInteractions", "()Ljava/util/Map;", "pressInteraction", "getPressInteraction", "()Landroidx/compose/foundation/interaction/PressInteraction$Press;", "setPressInteraction", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InteractionData {
        public static final int $stable = 8;
        private PressInteraction.Press pressInteraction;
        private final Map<Key, PressInteraction.Press> currentKeyPressInteractions = new LinkedHashMap();
        private long centreOffset = Offset.INSTANCE.m3252getZeroF1C5BW0();

        /* JADX INFO: renamed from: getCentreOffset-F1C5BW0, reason: not valid java name and from getter */
        public final long getCentreOffset() {
            return this.centreOffset;
        }

        public final Map<Key, PressInteraction.Press> getCurrentKeyPressInteractions() {
            return this.currentKeyPressInteractions;
        }

        public final PressInteraction.Press getPressInteraction() {
            return this.pressInteraction;
        }

        /* JADX INFO: renamed from: setCentreOffset-k-4lQ0M, reason: not valid java name */
        public final void m152setCentreOffsetk4lQ0M(long j10) {
            this.centreOffset = j10;
        }

        public final void setPressInteraction(PressInteraction.Press press) {
            this.pressInteraction = press;
        }
    }

    public /* synthetic */ AbstractClickableNode(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a aVar, h hVar) {
        this(mutableInteractionSource, z, str, role, aVar);
    }

    /* JADX INFO: renamed from: updateCommon-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ void m146updateCommonXHw0xAI$default(AbstractClickableNode abstractClickableNode, MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a aVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateCommon-XHw0xAI");
        }
        if ((i10 & 8) != 0) {
            role = null;
        }
        abstractClickableNode.m150updateCommonXHw0xAI(mutableInteractionSource, z, str, role, aVar);
    }

    public final void disposeInteractionSource() {
        PressInteraction.Press pressInteraction = this.interactionData.getPressInteraction();
        if (pressInteraction != null) {
            this.interactionSource.tryEmit(new PressInteraction.Cancel(pressInteraction));
        }
        Iterator<T> it = this.interactionData.getCurrentKeyPressInteractions().values().iterator();
        while (it.hasNext()) {
            this.interactionSource.tryEmit(new PressInteraction.Cancel((PressInteraction.Press) it.next()));
        }
        this.interactionData.setPressInteraction(null);
        this.interactionData.getCurrentKeyPressInteractions().clear();
    }

    public abstract AbstractClickablePointerInputNode getClickablePointerInputNode();

    public abstract ClickableSemanticsNode getClickableSemanticsNode();

    public final InteractionData getInteractionData() {
        return this.interactionData;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return f.a(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        getClickablePointerInputNode().onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onDensityChange() {
        f.b(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        disposeInteractionSource();
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean mo147onKeyEventZmokQxo(KeyEvent event) {
        if (this.enabled && Clickable_androidKt.m229isPressZmokQxo(event)) {
            if (!this.interactionData.getCurrentKeyPressInteractions().containsKey(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(event)))) {
                PressInteraction.Press press = new PressInteraction.Press(this.interactionData.getCentreOffset(), null);
                this.interactionData.getCurrentKeyPressInteractions().put(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(event)), press);
                m0.p(getCoroutineScope(), null, 0, new AbstractClickableNode$onKeyEvent$1(this, press, null), 3);
                return true;
            }
        } else if (this.enabled && Clickable_androidKt.m227isClickZmokQxo(event)) {
            PressInteraction.Press pressRemove = this.interactionData.getCurrentKeyPressInteractions().remove(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(event)));
            if (pressRemove != null) {
                m0.p(getCoroutineScope(), null, 0, new AbstractClickableNode$onKeyEvent$2$1(this, pressRemove, null), 3);
            }
            this.onClick.invoke();
            return true;
        }
        return false;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        getClickablePointerInputNode().mo148onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean mo149onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onViewConfigurationChange() {
        f.c(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return f.d(this);
    }

    /* JADX INFO: renamed from: updateCommon-XHw0xAI, reason: not valid java name */
    public final void m150updateCommonXHw0xAI(MutableInteractionSource interactionSource, boolean enabled, String onClickLabel, Role role, r7.a<t0> onClick) {
        if (!p.a(this.interactionSource, interactionSource)) {
            disposeInteractionSource();
            this.interactionSource = interactionSource;
        }
        if (this.enabled != enabled) {
            if (!enabled) {
                disposeInteractionSource();
            }
            this.enabled = enabled;
        }
        this.onClickLabel = onClickLabel;
        this.role = role;
        this.onClick = onClick;
    }

    private AbstractClickableNode(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a<t0> aVar) {
        this.interactionSource = mutableInteractionSource;
        this.enabled = z;
        this.onClickLabel = str;
        this.role = role;
        this.onClick = aVar;
        this.interactionData = new InteractionData();
    }
}
