package androidx.compose.ui.node;

import androidx.media3.session.MediaUtils;
import kotlin.Metadata;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\bR'\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\bR'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\bR'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0017\u0010\u0002\u001a\u0004\b\u0018\u0010\bR'\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001b\u0010\u0002\u001a\u0004\b\u001c\u0010\bR'\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\bR'\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b#\u0010\u0002\u001a\u0004\b$\u0010\bR'\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b'\u0010\u0002\u001a\u0004\b(\u0010\bR'\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b+\u0010\u0002\u001a\u0004\b,\u0010\bR'\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b/\u0010\u0002\u001a\u0004\b0\u0010\bR'\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b3\u0010\u0002\u001a\u0004\b4\u0010\bR'\u00105\u001a\b\u0012\u0004\u0012\u0002060\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b7\u0010\u0002\u001a\u0004\b8\u0010\bR'\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b;\u0010\u0002\u001a\u0004\b<\u0010\bR'\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b?\u0010\u0002\u001a\u0004\b@\u0010\bR'\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bC\u0010\u0002\u001a\u0004\bD\u0010\bR'\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bG\u0010\u0002\u001a\u0004\bH\u0010\bR'\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\u00048Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\bK\u0010\u0002\u001a\u0004\bL\u0010\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006M"}, d2 = {"Landroidx/compose/ui/node/Nodes;", "", "()V", "Any", "Landroidx/compose/ui/node/NodeKind;", "Landroidx/compose/ui/Modifier$Node;", "getAny-OLwlOKw$annotations", "getAny-OLwlOKw", "()I", "CompositionLocalConsumer", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "getCompositionLocalConsumer-OLwlOKw$annotations", "getCompositionLocalConsumer-OLwlOKw", "Draw", "Landroidx/compose/ui/node/DrawModifierNode;", "getDraw-OLwlOKw$annotations", "getDraw-OLwlOKw", "FocusEvent", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "getFocusEvent-OLwlOKw$annotations", "getFocusEvent-OLwlOKw", "FocusProperties", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "getFocusProperties-OLwlOKw$annotations", "getFocusProperties-OLwlOKw", "FocusTarget", "Landroidx/compose/ui/focus/FocusTargetNode;", "getFocusTarget-OLwlOKw$annotations", "getFocusTarget-OLwlOKw", "GlobalPositionAware", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "getGlobalPositionAware-OLwlOKw$annotations", "getGlobalPositionAware-OLwlOKw", "IntermediateMeasure", "Landroidx/compose/ui/layout/IntermediateLayoutModifierNode;", "getIntermediateMeasure-OLwlOKw$annotations", "getIntermediateMeasure-OLwlOKw", "KeyInput", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "getKeyInput-OLwlOKw$annotations", "getKeyInput-OLwlOKw", "Layout", "Landroidx/compose/ui/node/LayoutModifierNode;", "getLayout-OLwlOKw$annotations", "getLayout-OLwlOKw", "LayoutAware", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "getLayoutAware-OLwlOKw$annotations", "getLayoutAware-OLwlOKw", "Locals", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "getLocals-OLwlOKw$annotations", "getLocals-OLwlOKw", "ParentData", "Landroidx/compose/ui/node/ParentDataModifierNode;", "getParentData-OLwlOKw$annotations", "getParentData-OLwlOKw", "PointerInput", "Landroidx/compose/ui/node/PointerInputModifierNode;", "getPointerInput-OLwlOKw$annotations", "getPointerInput-OLwlOKw", "RotaryInput", "Landroidx/compose/ui/input/rotary/RotaryInputModifierNode;", "getRotaryInput-OLwlOKw$annotations", "getRotaryInput-OLwlOKw", "Semantics", "Landroidx/compose/ui/node/SemanticsModifierNode;", "getSemantics-OLwlOKw$annotations", "getSemantics-OLwlOKw", "SoftKeyboardKeyInput", "Landroidx/compose/ui/input/key/SoftKeyboardInterceptionModifierNode;", "getSoftKeyboardKeyInput-OLwlOKw$annotations", "getSoftKeyboardKeyInput-OLwlOKw", "Traversable", "Landroidx/compose/ui/node/TraversableNode;", "getTraversable-OLwlOKw$annotations", "getTraversable-OLwlOKw", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Nodes {
    public static final int $stable = 0;
    public static final Nodes INSTANCE = new Nodes();

    private Nodes() {
    }

    /* JADX INFO: renamed from: getAny-OLwlOKw, reason: not valid java name */
    public static final int m4854getAnyOLwlOKw() {
        return NodeKind.m4843constructorimpl(1);
    }

    @n
    /* JADX INFO: renamed from: getAny-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4855getAnyOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getCompositionLocalConsumer-OLwlOKw, reason: not valid java name */
    public static final int m4856getCompositionLocalConsumerOLwlOKw() {
        return NodeKind.m4843constructorimpl(32768);
    }

    @n
    /* JADX INFO: renamed from: getCompositionLocalConsumer-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4857getCompositionLocalConsumerOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getDraw-OLwlOKw, reason: not valid java name */
    public static final int m4858getDrawOLwlOKw() {
        return NodeKind.m4843constructorimpl(4);
    }

    @n
    /* JADX INFO: renamed from: getDraw-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4859getDrawOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getFocusEvent-OLwlOKw, reason: not valid java name */
    public static final int m4860getFocusEventOLwlOKw() {
        return NodeKind.m4843constructorimpl(4096);
    }

    @n
    /* JADX INFO: renamed from: getFocusEvent-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4861getFocusEventOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getFocusProperties-OLwlOKw, reason: not valid java name */
    public static final int m4862getFocusPropertiesOLwlOKw() {
        return NodeKind.m4843constructorimpl(2048);
    }

    @n
    /* JADX INFO: renamed from: getFocusProperties-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4863getFocusPropertiesOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getFocusTarget-OLwlOKw, reason: not valid java name */
    public static final int m4864getFocusTargetOLwlOKw() {
        return NodeKind.m4843constructorimpl(1024);
    }

    @n
    /* JADX INFO: renamed from: getFocusTarget-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4865getFocusTargetOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getGlobalPositionAware-OLwlOKw, reason: not valid java name */
    public static final int m4866getGlobalPositionAwareOLwlOKw() {
        return NodeKind.m4843constructorimpl(256);
    }

    @n
    /* JADX INFO: renamed from: getGlobalPositionAware-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4867getGlobalPositionAwareOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getIntermediateMeasure-OLwlOKw, reason: not valid java name */
    public static final int m4868getIntermediateMeasureOLwlOKw() {
        return NodeKind.m4843constructorimpl(512);
    }

    @n
    /* JADX INFO: renamed from: getIntermediateMeasure-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4869getIntermediateMeasureOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getKeyInput-OLwlOKw, reason: not valid java name */
    public static final int m4870getKeyInputOLwlOKw() {
        return NodeKind.m4843constructorimpl(8192);
    }

    @n
    /* JADX INFO: renamed from: getKeyInput-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4871getKeyInputOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getLayout-OLwlOKw, reason: not valid java name */
    public static final int m4872getLayoutOLwlOKw() {
        return NodeKind.m4843constructorimpl(2);
    }

    @n
    /* JADX INFO: renamed from: getLayout-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4873getLayoutOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getLayoutAware-OLwlOKw, reason: not valid java name */
    public static final int m4874getLayoutAwareOLwlOKw() {
        return NodeKind.m4843constructorimpl(128);
    }

    @n
    /* JADX INFO: renamed from: getLayoutAware-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4875getLayoutAwareOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getLocals-OLwlOKw, reason: not valid java name */
    public static final int m4876getLocalsOLwlOKw() {
        return NodeKind.m4843constructorimpl(32);
    }

    @n
    /* JADX INFO: renamed from: getLocals-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4877getLocalsOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getParentData-OLwlOKw, reason: not valid java name */
    public static final int m4878getParentDataOLwlOKw() {
        return NodeKind.m4843constructorimpl(64);
    }

    @n
    /* JADX INFO: renamed from: getParentData-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4879getParentDataOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getPointerInput-OLwlOKw, reason: not valid java name */
    public static final int m4880getPointerInputOLwlOKw() {
        return NodeKind.m4843constructorimpl(16);
    }

    @n
    /* JADX INFO: renamed from: getPointerInput-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4881getPointerInputOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getRotaryInput-OLwlOKw, reason: not valid java name */
    public static final int m4882getRotaryInputOLwlOKw() {
        return NodeKind.m4843constructorimpl(16384);
    }

    @n
    /* JADX INFO: renamed from: getRotaryInput-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4883getRotaryInputOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getSemantics-OLwlOKw, reason: not valid java name */
    public static final int m4884getSemanticsOLwlOKw() {
        return NodeKind.m4843constructorimpl(8);
    }

    @n
    /* JADX INFO: renamed from: getSemantics-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4885getSemanticsOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getSoftKeyboardKeyInput-OLwlOKw, reason: not valid java name */
    public static final int m4886getSoftKeyboardKeyInputOLwlOKw() {
        return NodeKind.m4843constructorimpl(131072);
    }

    @n
    /* JADX INFO: renamed from: getSoftKeyboardKeyInput-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4887getSoftKeyboardKeyInputOLwlOKw$annotations() {
    }

    /* JADX INFO: renamed from: getTraversable-OLwlOKw, reason: not valid java name */
    public static final int m4888getTraversableOLwlOKw() {
        return NodeKind.m4843constructorimpl(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
    }

    @n
    /* JADX INFO: renamed from: getTraversable-OLwlOKw$annotations, reason: not valid java name */
    public static /* synthetic */ void m4889getTraversableOLwlOKw$annotations() {
    }
}
