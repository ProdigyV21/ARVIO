package androidx.compose.foundation;

import androidx.compose.foundation.AbstractClickableNode;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.Role;
import d7.d;
import java.util.Map;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aD\u0010\f\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001aV\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001av\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0088\u0001\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a=\u0010#\u001a\u00020\b*\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a \u0001\u0010.\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010%\u001a\u00020$2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0*2\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0000ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001an\u00102\u001a\u00020/2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00063"}, d2 = {"Landroidx/compose/ui/Modifier;", "", "enabled", "", "onClickLabel", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "clickable-XHw0xAI", "(Landroidx/compose/ui/Modifier;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "clickable", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/Indication;", "indication", "clickable-O2vRcR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "onLongClickLabel", "onLongClick", "onDoubleClick", "combinedClickable-cJG_KMw", "(Landroidx/compose/ui/Modifier;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Ljava/lang/String;Lr7/a;Lr7/a;Lr7/a;)Landroidx/compose/ui/Modifier;", "combinedClickable", "combinedClickable-XVZzFYc", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Ljava/lang/String;Lr7/a;Lr7/a;Lr7/a;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "pressPoint", "Landroidx/compose/foundation/AbstractClickableNode$InteractionData;", "interactionData", "delayPressInteraction", "handlePressInteraction-EPk0efs", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/AbstractClickableNode$InteractionData;Lr7/a;Ld7/d;)Ljava/lang/Object;", "handlePressInteraction", "Lka/k0;", "indicationScope", "", "Landroidx/compose/ui/input/key/Key;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "currentKeyPressInteractions", "Landroidx/compose/runtime/State;", "keyClickOffset", "genericClickableWithoutGesture-Kqv-Bsg", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;Lka/k0;Ljava/util/Map;Landroidx/compose/runtime/State;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Ljava/lang/String;Lr7/a;Lr7/a;)Landroidx/compose/ui/Modifier;", "genericClickableWithoutGesture", "Landroidx/compose/foundation/CombinedClickableNode;", "CombinedClickableNode-xpl5gLE", "(Lr7/a;Ljava/lang/String;Lr7/a;Lr7/a;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;)Landroidx/compose/foundation/CombinedClickableNode;", "CombinedClickableNode", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClickableKt {
    /* JADX INFO: renamed from: CombinedClickableNode-xpl5gLE, reason: not valid java name */
    public static final CombinedClickableNode m209CombinedClickableNodexpl5gLE(r7.a<t0> aVar, String str, r7.a<t0> aVar2, r7.a<t0> aVar3, MutableInteractionSource mutableInteractionSource, boolean z, String str2, Role role) {
        return new CombinedClickableNodeImpl(aVar, str, aVar2, aVar3, mutableInteractionSource, z, str2, role, null);
    }

    /* JADX INFO: renamed from: clickable-O2vRcR0, reason: not valid java name */
    public static final Modifier m211clickableO2vRcR0(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, r7.a<t0> aVar) {
        MutableInteractionSource mutableInteractionSource2;
        Indication indication2;
        boolean z5;
        String str2;
        Role role2;
        r7.a<t0> aVar2;
        l noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            z5 = z;
            str2 = str;
            role2 = role;
            aVar2 = aVar;
            noInspectorInfo = new ClickableKt$clickableO2vRcR0$$inlined$debugInspectorInfo$1(mutableInteractionSource2, indication2, z5, str2, role2, aVar2);
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            z5 = z;
            str2 = str;
            role2 = role;
            aVar2 = aVar;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        return InspectableValueKt.inspectableWrapper(modifier, noInspectorInfo, FocusableKt.focusableInNonTouchMode(HoverableKt.hoverable(IndicationKt.indication(Modifier.INSTANCE, mutableInteractionSource2, indication2), mutableInteractionSource2, z5), z5, mutableInteractionSource2).then(new ClickableElement(mutableInteractionSource2, z5, str2, role2, aVar2, null)));
    }

    /* JADX INFO: renamed from: clickable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m212clickableO2vRcR0$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, r7.a aVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        return m211clickableO2vRcR0(modifier, mutableInteractionSource, indication, z, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? null : role, aVar);
    }

    /* JADX INFO: renamed from: clickable-XHw0xAI, reason: not valid java name */
    public static final Modifier m213clickableXHw0xAI(Modifier modifier, boolean z, String str, Role role, r7.a<t0> aVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ClickableKt$clickableXHw0xAI$$inlined$debugInspectorInfo$1(z, str, role, aVar) : InspectableValueKt.getNoInspectorInfo(), new ClickableKt$clickable$2(z, str, role, aVar));
    }

    /* JADX INFO: renamed from: clickable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m214clickableXHw0xAI$default(Modifier modifier, boolean z, String str, Role role, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            role = null;
        }
        return m213clickableXHw0xAI(modifier, z, str, role, aVar);
    }

    /* JADX INFO: renamed from: combinedClickable-XVZzFYc, reason: not valid java name */
    public static final Modifier m215combinedClickableXVZzFYc(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, String str2, r7.a<t0> aVar, r7.a<t0> aVar2, r7.a<t0> aVar3) {
        return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ClickableKt$combinedClickableXVZzFYc$$inlined$debugInspectorInfo$1(indication, mutableInteractionSource, z, str, role, aVar3, aVar2, aVar, str2) : InspectableValueKt.getNoInspectorInfo(), FocusableKt.focusableInNonTouchMode(HoverableKt.hoverable(IndicationKt.indication(Modifier.INSTANCE, mutableInteractionSource, indication), mutableInteractionSource, z), z, mutableInteractionSource).then(new CombinedClickableElement(mutableInteractionSource, z, str, role, aVar3, str2, aVar, aVar2, null)));
    }

    /* JADX INFO: renamed from: combinedClickable-XVZzFYc$default, reason: not valid java name */
    public static /* synthetic */ Modifier m216combinedClickableXVZzFYc$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, String str2, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10, Object obj) {
        r7.a aVar4;
        Modifier modifier2;
        MutableInteractionSource mutableInteractionSource2;
        Indication indication2;
        r7.a aVar5;
        if ((i10 & 4) != 0) {
            z = true;
        }
        boolean z5 = z;
        String str3 = (i10 & 8) != 0 ? null : str;
        Role role2 = (i10 & 16) != 0 ? null : role;
        String str4 = (i10 & 32) != 0 ? null : str2;
        r7.a aVar6 = (i10 & 64) != 0 ? null : aVar;
        if ((i10 & 128) != 0) {
            aVar4 = null;
            modifier2 = modifier;
            indication2 = indication;
            aVar5 = aVar3;
            mutableInteractionSource2 = mutableInteractionSource;
        } else {
            aVar4 = aVar2;
            modifier2 = modifier;
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            aVar5 = aVar3;
        }
        return m215combinedClickableXVZzFYc(modifier2, mutableInteractionSource2, indication2, z5, str3, role2, str4, aVar6, aVar4, aVar5);
    }

    /* JADX INFO: renamed from: combinedClickable-cJG_KMw, reason: not valid java name */
    public static final Modifier m217combinedClickablecJG_KMw(Modifier modifier, boolean z, String str, Role role, String str2, r7.a<t0> aVar, r7.a<t0> aVar2, r7.a<t0> aVar3) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ClickableKt$combinedClickablecJG_KMw$$inlined$debugInspectorInfo$1(z, str, role, aVar3, aVar2, aVar, str2) : InspectableValueKt.getNoInspectorInfo(), new ClickableKt$combinedClickable$2(z, str, role, str2, aVar, aVar2, aVar3));
    }

    /* JADX INFO: renamed from: combinedClickable-cJG_KMw$default, reason: not valid java name */
    public static /* synthetic */ Modifier m218combinedClickablecJG_KMw$default(Modifier modifier, boolean z, String str, Role role, String str2, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10, Object obj) {
        r7.a aVar4;
        r7.a aVar5;
        String str3;
        r7.a aVar6;
        String str4;
        Role role2;
        Modifier modifier2;
        if ((i10 & 1) != 0) {
            z = true;
        }
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            role = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 16) != 0) {
            aVar = null;
        }
        if ((i10 & 32) != 0) {
            aVar4 = null;
            str3 = str2;
            aVar5 = aVar3;
            str4 = str;
            aVar6 = aVar;
            modifier2 = modifier;
            role2 = role;
        } else {
            aVar4 = aVar2;
            aVar5 = aVar3;
            str3 = str2;
            aVar6 = aVar;
            str4 = str;
            role2 = role;
            modifier2 = modifier;
        }
        return m217combinedClickablecJG_KMw(modifier2, z, str4, role2, str3, aVar6, aVar4, aVar5);
    }

    /* JADX INFO: renamed from: genericClickableWithoutGesture-Kqv-Bsg, reason: not valid java name */
    public static final Modifier m219genericClickableWithoutGestureKqvBsg(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, k0 k0Var, Map<Key, PressInteraction.Press> map, State<Offset> state, boolean z, String str, Role role, String str2, r7.a<t0> aVar, r7.a<t0> aVar2) {
        return modifier.then(FocusableKt.focusableInNonTouchMode(HoverableKt.hoverable(IndicationKt.indication(genericClickableWithoutGesture_Kqv_Bsg$detectPressAndClickFromKey(new ClickableSemanticsElement(z, role, str2, aVar, str, aVar2, null), z, map, state, k0Var, aVar2, mutableInteractionSource), mutableInteractionSource, indication), mutableInteractionSource, z), z, mutableInteractionSource));
    }

    /* JADX INFO: renamed from: genericClickableWithoutGesture-Kqv-Bsg$default, reason: not valid java name */
    public static /* synthetic */ Modifier m220genericClickableWithoutGestureKqvBsg$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, k0 k0Var, Map map, State state, boolean z, String str, Role role, String str2, r7.a aVar, r7.a aVar2, int i10, Object obj) {
        r7.a aVar3;
        Modifier modifier2;
        MutableInteractionSource mutableInteractionSource2;
        Indication indication2;
        k0 k0Var2;
        Map map2;
        State state2;
        r7.a aVar4;
        boolean z5 = (i10 & 32) != 0 ? true : z;
        String str3 = (i10 & 64) != 0 ? null : str;
        Role role2 = (i10 & 128) != 0 ? null : role;
        String str4 = (i10 & 256) != 0 ? null : str2;
        if ((i10 & 512) != 0) {
            aVar3 = null;
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            k0Var2 = k0Var;
            map2 = map;
            state2 = state;
            aVar4 = aVar2;
            modifier2 = modifier;
        } else {
            aVar3 = aVar;
            modifier2 = modifier;
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            k0Var2 = k0Var;
            map2 = map;
            state2 = state;
            aVar4 = aVar2;
        }
        return m219genericClickableWithoutGestureKqvBsg(modifier2, mutableInteractionSource2, indication2, k0Var2, map2, state2, z5, str3, role2, str4, aVar3, aVar4);
    }

    private static final Modifier genericClickableWithoutGesture_Kqv_Bsg$detectPressAndClickFromKey(Modifier modifier, boolean z, Map<Key, PressInteraction.Press> map, State<Offset> state, k0 k0Var, r7.a<t0> aVar, MutableInteractionSource mutableInteractionSource) {
        return KeyInputModifierKt.onKeyEvent(modifier, new ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1(z, map, state, k0Var, aVar, mutableInteractionSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePressInteraction-EPk0efs, reason: not valid java name */
    public static final Object m221handlePressInteractionEPk0efs(PressGestureScope pressGestureScope, long j10, MutableInteractionSource mutableInteractionSource, AbstractClickableNode.InteractionData interactionData, r7.a<Boolean> aVar, d<? super t0> dVar) {
        Object objC = l0.c(new ClickableKt$handlePressInteraction$2(pressGestureScope, j10, mutableInteractionSource, interactionData, aVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }
}
