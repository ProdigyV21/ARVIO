package androidx.compose.ui.window;

import a0.c;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.R;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.z0;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import f4.f;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\f\b\u0001\u0018\u0000 ¢\u00012\u00020\u00012\u00020\u0002:\u0002¢\u0001BQ\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J(\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0004H\u0014¢\u0006\u0004\b \u0010\u0017J\u000f\u0010!\u001a\u00020\u0004H\u0014¢\u0006\u0004\b!\u0010\u0017J\u001f\u0010'\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"H\u0010¢\u0006\u0004\b%\u0010&J7\u00100\u001a\u00020\u00042\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\"H\u0010¢\u0006\u0004\b.\u0010/J\u0017\u00103\u001a\u00020(2\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J5\u00107\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u00106\u001a\u000205¢\u0006\u0004\b7\u00108J\u0015\u0010;\u001a\u00020\u00042\u0006\u0010:\u001a\u000209¢\u0006\u0004\b;\u0010<J\r\u0010=\u001a\u00020\u0004¢\u0006\u0004\b=\u0010\u0017J\u000f\u0010?\u001a\u00020\u0004H\u0001¢\u0006\u0004\b>\u0010\u0017J\r\u0010@\u001a\u00020\u0004¢\u0006\u0004\b@\u0010\u0017J\r\u0010A\u001a\u00020\u0004¢\u0006\u0004\bA\u0010\u0017J\u0019\u0010C\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010BH\u0016¢\u0006\u0004\bC\u0010DJ\u0017\u0010E\u001a\u00020\u00042\u0006\u00106\u001a\u00020\"H\u0016¢\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00042\u0006\u0010G\u001a\u00020(H\u0002¢\u0006\u0004\bH\u0010IJ\u0017\u0010L\u001a\u00020\u00042\u0006\u0010K\u001a\u00020JH\u0002¢\u0006\u0004\bL\u0010MJ\u0017\u0010O\u001a\u00020\u00042\u0006\u0010N\u001a\u00020(H\u0002¢\u0006\u0004\bO\u0010IJ\u0017\u0010Q\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\"H\u0002¢\u0006\u0004\bQ\u0010FJ\u0017\u0010R\u001a\u00020\u00042\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\bR\u0010SJ\u000f\u0010U\u001a\u00020TH\u0002¢\u0006\u0004\bU\u0010VR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010WR\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010XR\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010^R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR \u0010c\u001a\u00020T8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\bc\u0010d\u0012\u0004\bf\u0010\u0017\u001a\u0004\be\u0010VR\"\u0010g\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\"\u0010m\u001a\u0002058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010SR5\u0010z\u001a\u0004\u0018\u00010r2\b\u0010s\u001a\u0004\u0018\u00010r8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bt\u0010u\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR/\u0010:\u001a\u0004\u0018\u0001092\b\u0010s\u001a\u0004\u0018\u0001098B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b{\u0010u\u001a\u0004\b|\u0010}\"\u0004\b~\u0010<R\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R \u0010\u0086\u0001\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001e\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0018\u0010\u008b\u0001\u001a\u00030\u008a\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0018\u0010\u008e\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001RE\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u001a2\u0011\u0010s\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u001a8B@BX\u0082\u008e\u0002¢\u0006\u0016\n\u0005\b\u0090\u0001\u0010u\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0005\b\u001c\u0010\u0093\u0001R)\u0010\u0094\u0001\u001a\u00020(2\u0006\u0010s\u001a\u00020(8\u0014@RX\u0094\u000e¢\u0006\u0010\n\u0006\b\u0094\u0001\u0010\u0095\u0001\u001a\u0006\b\u0096\u0001\u0010\u0085\u0001R\u0018\u0010\u0098\u0001\u001a\u00030\u0097\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0017\u0010\u009c\u0001\u001a\u00020\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\"8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0017\u0010¡\u0001\u001a\u00020\"8BX\u0082\u0004¢\u0006\b\u001a\u0006\b \u0001\u0010\u009e\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006£\u0001"}, d2 = {"Landroidx/compose/ui/window/PopupLayout;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/ui/window/PopupProperties;", "properties", "", "testTag", "Landroid/view/View;", "composeView", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/window/PopupPositionProvider;", "initialPositionProvider", "Ljava/util/UUID;", "popupId", "Landroidx/compose/ui/window/PopupLayoutHelper;", "popupLayoutHelper", "<init>", "(Lr7/a;Landroidx/compose/ui/window/PopupProperties;Ljava/lang/String;Landroid/view/View;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/window/PopupPositionProvider;Ljava/util/UUID;Landroidx/compose/ui/window/PopupLayoutHelper;)V", "show", "()V", "Landroidx/compose/runtime/CompositionContext;", "parent", "Landroidx/compose/runtime/Composable;", "content", "setContent", "(Landroidx/compose/runtime/CompositionContext;Lr7/p;)V", "Content", "(Landroidx/compose/runtime/Composer;I)V", "onAttachedToWindow", "onDetachedFromWindow", "", "widthMeasureSpec", "heightMeasureSpec", "internalOnMeasure$ui_release", "(II)V", "internalOnMeasure", "", "changed", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "internalOnLayout$ui_release", "(ZIIII)V", "internalOnLayout", "Landroid/view/KeyEvent;", "event", "dispatchKeyEvent", "(Landroid/view/KeyEvent;)Z", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "updateParameters", "(Lr7/a;Landroidx/compose/ui/window/PopupProperties;Ljava/lang/String;Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "updateParentLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "pollForLocationOnScreenChange", "updateParentBounds$ui_release", "updateParentBounds", "updatePosition", "dismiss", "Landroid/view/MotionEvent;", "onTouchEvent", "(Landroid/view/MotionEvent;)Z", "setLayoutDirection", "(I)V", "isFocusable", "setIsFocusable", "(Z)V", "Landroidx/compose/ui/window/SecureFlagPolicy;", "securePolicy", "setSecurePolicy", "(Landroidx/compose/ui/window/SecureFlagPolicy;)V", "clippingEnabled", "setClippingEnabled", "flags", "applyNewFlags", "superSetLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroid/view/WindowManager$LayoutParams;", "createLayoutParams", "()Landroid/view/WindowManager$LayoutParams;", "Lr7/a;", "Landroidx/compose/ui/window/PopupProperties;", "Ljava/lang/String;", "getTestTag", "()Ljava/lang/String;", "setTestTag", "(Ljava/lang/String;)V", "Landroid/view/View;", "Landroidx/compose/ui/window/PopupLayoutHelper;", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "params", "Landroid/view/WindowManager$LayoutParams;", "getParams$ui_release", "getParams$ui_release$annotations", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "getPositionProvider", "()Landroidx/compose/ui/window/PopupPositionProvider;", "setPositionProvider", "(Landroidx/compose/ui/window/PopupPositionProvider;)V", "parentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setParentLayoutDirection", "Landroidx/compose/ui/unit/IntSize;", "<set-?>", "popupContentSize$delegate", "Landroidx/compose/runtime/MutableState;", "getPopupContentSize-bOM6tXw", "()Landroidx/compose/ui/unit/IntSize;", "setPopupContentSize-fhxjrPA", "(Landroidx/compose/ui/unit/IntSize;)V", "popupContentSize", "parentLayoutCoordinates$delegate", "getParentLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setParentLayoutCoordinates", "Landroidx/compose/ui/unit/IntRect;", "parentBounds", "Landroidx/compose/ui/unit/IntRect;", "canCalculatePosition$delegate", "Landroidx/compose/runtime/State;", "getCanCalculatePosition", "()Z", "canCalculatePosition", "Landroidx/compose/ui/unit/Dp;", "maxSupportedElevation", "F", "Landroid/graphics/Rect;", "previousWindowVisibleFrame", "Landroid/graphics/Rect;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "snapshotStateObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "content$delegate", "getContent", "()Lr7/p;", "(Lr7/p;)V", "shouldCreateCompositionOnAttachedToWindow", "Z", "getShouldCreateCompositionOnAttachedToWindow", "", "locationOnScreen", "[I", "getSubCompositionView", "()Landroidx/compose/ui/platform/AbstractComposeView;", "subCompositionView", "getDisplayWidth", "()I", "displayWidth", "getDisplayHeight", "displayHeight", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PopupLayout extends AbstractComposeView implements ViewRootForInspector {

    /* JADX INFO: renamed from: canCalculatePosition$delegate, reason: from kotlin metadata */
    private final State canCalculatePosition;
    private final View composeView;

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private final int[] locationOnScreen;
    private final float maxSupportedElevation;
    private r7.a<t0> onDismissRequest;
    private final WindowManager.LayoutParams params;
    private IntRect parentBounds;

    /* JADX INFO: renamed from: parentLayoutCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState parentLayoutCoordinates;
    private LayoutDirection parentLayoutDirection;

    /* JADX INFO: renamed from: popupContentSize$delegate, reason: from kotlin metadata */
    private final MutableState popupContentSize;
    private final PopupLayoutHelper popupLayoutHelper;
    private PopupPositionProvider positionProvider;
    private final Rect previousWindowVisibleFrame;
    private PopupProperties properties;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final SnapshotStateObserver snapshotStateObserver;
    private String testTag;
    private final WindowManager windowManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final l<PopupLayout, t0> onCommitAffectingPopupPosition = PopupLayout$Companion$onCommitAffectingPopupPosition$1.INSTANCE;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/window/PopupLayout$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Landroidx/compose/ui/window/PopupLayout;", "Lx6/t0;", "onCommitAffectingPopupPosition", "Lr7/l;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.PopupLayout$Content$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(int i10) {
            super(2);
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            PopupLayout.this.Content(composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.PopupLayout$updatePosition$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ IntRect $parentBounds;
        final /* synthetic */ long $popupContentSize;
        final /* synthetic */ j0 $popupPosition;
        final /* synthetic */ long $windowSize;
        final /* synthetic */ PopupLayout this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(j0 j0Var, PopupLayout popupLayout, IntRect intRect, long j10, long j11) {
            super(0);
            this.$popupPosition = j0Var;
            this.this$0 = popupLayout;
            this.$parentBounds = intRect;
            this.$windowSize = j10;
            this.$popupContentSize = j11;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5939invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5939invoke() {
            this.$popupPosition.f19745i = this.this$0.getPositionProvider().mo959calculatePositionllwVHH4(this.$parentBounds, this.$windowSize, this.this$0.getParentLayoutDirection(), this.$popupContentSize);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PopupLayout(r7.a aVar, PopupProperties popupProperties, String str, View view, Density density, PopupPositionProvider popupPositionProvider, UUID uuid, PopupLayoutHelper popupLayoutHelper, int i10, h hVar) {
        PopupLayoutHelper popupLayoutHelperImpl29;
        if ((i10 & 128) != 0) {
            popupLayoutHelperImpl29 = Build.VERSION.SDK_INT >= 29 ? new PopupLayoutHelperImpl29() : new PopupLayoutHelperImpl();
        } else {
            popupLayoutHelperImpl29 = popupLayoutHelper;
        }
        this(aVar, popupProperties, str, view, density, popupPositionProvider, uuid, popupLayoutHelperImpl29);
    }

    private final void applyNewFlags(int flags) {
        WindowManager.LayoutParams layoutParams = this.params;
        layoutParams.flags = flags;
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, layoutParams);
    }

    private final WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.flags = (layoutParams.flags & (-8552473)) | MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
        layoutParams.type = 1002;
        layoutParams.token = this.composeView.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(this.composeView.getContext().getResources().getString(R.string.default_popup_window_title));
        return layoutParams;
    }

    private final p<Composer, Integer, t0> getContent() {
        return (p) this.content.getValue();
    }

    private final int getDisplayHeight() {
        return t7.a.M(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density);
    }

    private final int getDisplayWidth() {
        return t7.a.M(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density);
    }

    public static /* synthetic */ void getParams$ui_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getParentLayoutCoordinates() {
        return (LayoutCoordinates) this.parentLayoutCoordinates.getValue();
    }

    private final void setClippingEnabled(boolean clippingEnabled) {
        applyNewFlags(clippingEnabled ? this.params.flags & (-513) : this.params.flags | 512);
    }

    private final void setContent(p<? super Composer, ? super Integer, t0> pVar) {
        this.content.setValue(pVar);
    }

    private final void setIsFocusable(boolean isFocusable) {
        applyNewFlags(!isFocusable ? this.params.flags | 8 : this.params.flags & (-9));
    }

    private final void setParentLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.parentLayoutCoordinates.setValue(layoutCoordinates);
    }

    private final void setSecurePolicy(SecureFlagPolicy securePolicy) {
        applyNewFlags(SecureFlagPolicy_androidKt.shouldApplySecureFlag(securePolicy, AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView)) ? this.params.flags | 8192 : this.params.flags & (-8193));
    }

    private final void superSetLayoutDirection(LayoutDirection layoutDirection) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        int i11 = 1;
        if (i10 == 1) {
            i11 = 0;
        } else if (i10 != 2) {
            throw new NoWhenBranchMatchedException();
        }
        super.setLayoutDirection(i11);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-857613600);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-857613600, i10, -1, "androidx.compose.ui.window.PopupLayout.Content (AndroidPopup.android.kt:475)");
        }
        if (c.A(composerStartRestartGroup, 0, getContent())) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(i10));
        }
    }

    public final void dismiss() {
        z0.i(this, null);
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (event.getKeyCode() == 4 && this.properties.getDismissOnBackPress()) {
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(event);
            }
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(event, this);
                }
                return true;
            }
            if (event.getAction() == 1 && (keyDispatcherState = getKeyDispatcherState()) != null && keyDispatcherState.isTracking(event) && !event.isCanceled()) {
                r7.a<t0> aVar = this.onDismissRequest;
                if (aVar != null) {
                    aVar.invoke();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public final boolean getCanCalculatePosition() {
        return ((Boolean) this.canCalculatePosition.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getParams$ui_release, reason: from getter */
    public final WindowManager.LayoutParams getParams() {
        return this.params;
    }

    public final LayoutDirection getParentLayoutDirection() {
        return this.parentLayoutDirection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final IntSize m5937getPopupContentSizebOM6tXw() {
        return (IntSize) this.popupContentSize.getValue();
    }

    public final PopupPositionProvider getPositionProvider() {
        return this.positionProvider;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public AbstractComposeView getSubCompositionView() {
        return this;
    }

    public final String getTestTag() {
        return this.testTag;
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public final /* synthetic */ View getViewRoot() {
        return androidx.compose.ui.platform.r.b(this);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnLayout$ui_release(boolean changed, int left, int top, int right, int bottom) {
        View childAt;
        super.internalOnLayout$ui_release(changed, left, top, right, bottom);
        if (this.properties.getUsePlatformDefaultWidth() || (childAt = getChildAt(0)) == null) {
            return;
        }
        this.params.width = childAt.getMeasuredWidth();
        this.params.height = childAt.getMeasuredHeight();
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnMeasure$ui_release(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.properties.getUsePlatformDefaultWidth()) {
            super.internalOnMeasure$ui_release(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.internalOnMeasure$ui_release(View.MeasureSpec.makeMeasureSpec(getDisplayWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), Integer.MIN_VALUE));
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.snapshotStateObserver.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.snapshotStateObserver.stop();
        this.snapshotStateObserver.clear();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.properties.getDismissOnClickOutside()) {
            return super.onTouchEvent(event);
        }
        if (event != null && event.getAction() == 0 && (event.getX() < 0.0f || event.getX() >= getWidth() || event.getY() < 0.0f || event.getY() >= getHeight())) {
            r7.a<t0> aVar = this.onDismissRequest;
            if (aVar != null) {
                aVar.invoke();
            }
            return true;
        }
        if (event == null || event.getAction() != 4) {
            return super.onTouchEvent(event);
        }
        r7.a<t0> aVar2 = this.onDismissRequest;
        if (aVar2 != null) {
            aVar2.invoke();
        }
        return true;
    }

    public final void pollForLocationOnScreenChange() {
        int[] iArr = this.locationOnScreen;
        int i10 = iArr[0];
        int i11 = iArr[1];
        this.composeView.getLocationOnScreen(iArr);
        int[] iArr2 = this.locationOnScreen;
        if (i10 == iArr2[0] && i11 == iArr2[1]) {
            return;
        }
        updateParentBounds$ui_release();
    }

    @Override // android.view.View
    public void setLayoutDirection(int layoutDirection) {
    }

    public final void setParentLayoutDirection(LayoutDirection layoutDirection) {
        this.parentLayoutDirection = layoutDirection;
    }

    /* JADX INFO: renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m5938setPopupContentSizefhxjrPA(IntSize intSize) {
        this.popupContentSize.setValue(intSize);
    }

    public final void setPositionProvider(PopupPositionProvider popupPositionProvider) {
        this.positionProvider = popupPositionProvider;
    }

    public final void setTestTag(String str) {
        this.testTag = str;
    }

    public final void show() {
        this.windowManager.addView(this, this.params);
    }

    public final void updateParameters(r7.a<t0> onDismissRequest, PopupProperties properties, String testTag, LayoutDirection layoutDirection) {
        this.onDismissRequest = onDismissRequest;
        if (properties.getUsePlatformDefaultWidth() && !this.properties.getUsePlatformDefaultWidth()) {
            WindowManager.LayoutParams layoutParams = this.params;
            layoutParams.width = -2;
            layoutParams.height = -2;
            this.popupLayoutHelper.updateViewLayout(this.windowManager, this, layoutParams);
        }
        this.properties = properties;
        this.testTag = testTag;
        setIsFocusable(properties.getFocusable());
        setSecurePolicy(properties.getSecurePolicy());
        setClippingEnabled(properties.getClippingEnabled());
        superSetLayoutDirection(layoutDirection);
    }

    public final void updateParentBounds$ui_release() {
        LayoutCoordinates parentLayoutCoordinates = getParentLayoutCoordinates();
        if (parentLayoutCoordinates == null) {
            return;
        }
        long jMo4638getSizeYbymL2g = parentLayoutCoordinates.mo4638getSizeYbymL2g();
        long jPositionInWindow = LayoutCoordinatesKt.positionInWindow(parentLayoutCoordinates);
        IntRect intRectM5834IntRectVbeCjmY = IntRectKt.m5834IntRectVbeCjmY(IntOffsetKt.IntOffset(t7.a.M(Offset.m3236getXimpl(jPositionInWindow)), t7.a.M(Offset.m3237getYimpl(jPositionInWindow))), jMo4638getSizeYbymL2g);
        if (kotlin.jvm.internal.p.a(intRectM5834IntRectVbeCjmY, this.parentBounds)) {
            return;
        }
        this.parentBounds = intRectM5834IntRectVbeCjmY;
        updatePosition();
    }

    public final void updateParentLayoutCoordinates(LayoutCoordinates parentLayoutCoordinates) {
        setParentLayoutCoordinates(parentLayoutCoordinates);
        updateParentBounds$ui_release();
    }

    public final void updatePosition() {
        IntSize intSizeM5937getPopupContentSizebOM6tXw;
        IntRect intRect = this.parentBounds;
        if (intRect == null || (intSizeM5937getPopupContentSizebOM6tXw = m5937getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        long packedValue = intSizeM5937getPopupContentSizebOM6tXw.getPackedValue();
        Rect rect = this.previousWindowVisibleFrame;
        this.popupLayoutHelper.getWindowVisibleDisplayFrame(this.composeView, rect);
        IntRect intBounds = AndroidPopup_androidKt.toIntBounds(rect);
        long jIntSize = IntSizeKt.IntSize(intBounds.getWidth(), intBounds.getHeight());
        j0 j0Var = new j0();
        j0Var.f19745i = IntOffset.INSTANCE.m5812getZeronOccac();
        this.snapshotStateObserver.observeReads(this, onCommitAffectingPopupPosition, new AnonymousClass1(j0Var, this, intRect, jIntSize, packedValue));
        this.params.x = IntOffset.m5802getXimpl(j0Var.f19745i);
        this.params.y = IntOffset.m5803getYimpl(j0Var.f19745i);
        if (this.properties.getExcludeFromSystemGesture()) {
            this.popupLayoutHelper.setGestureExclusionRects(this, IntSize.m5844getWidthimpl(jIntSize), IntSize.m5843getHeightimpl(jIntSize));
        }
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    public final void setContent(CompositionContext parent, p<? super Composer, ? super Integer, t0> content) {
        setParentCompositionContext(parent);
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
    }

    public PopupLayout(r7.a<t0> aVar, PopupProperties popupProperties, String str, View view, Density density, PopupPositionProvider popupPositionProvider, UUID uuid, PopupLayoutHelper popupLayoutHelper) {
        super(view.getContext(), null, 0, 6, null);
        this.onDismissRequest = aVar;
        this.properties = popupProperties;
        this.testTag = str;
        this.composeView = view;
        this.popupLayoutHelper = popupLayoutHelper;
        this.windowManager = (WindowManager) view.getContext().getSystemService("window");
        this.params = createLayoutParams();
        this.positionProvider = popupPositionProvider;
        this.parentLayoutDirection = LayoutDirection.Ltr;
        this.popupContentSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.parentLayoutCoordinates = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.canCalculatePosition = SnapshotStateKt.derivedStateOf(new PopupLayout$canCalculatePosition$2(this));
        float fM5678constructorimpl = Dp.m5678constructorimpl(8);
        this.maxSupportedElevation = fM5678constructorimpl;
        this.previousWindowVisibleFrame = new Rect();
        this.snapshotStateObserver = new SnapshotStateObserver(new PopupLayout$snapshotStateObserver$1(this));
        setId(android.R.id.content);
        z0.i(this, z0.e(view));
        z0.j(this, z0.f(view));
        f.w(this, f.k(view));
        setTag(R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(density.mo285toPx0680j_4(fM5678constructorimpl));
        setOutlineProvider(new ViewOutlineProvider() { // from class: androidx.compose.ui.window.PopupLayout.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline result) {
                result.setRect(0, 0, view2.getWidth(), view2.getHeight());
                result.setAlpha(0.0f);
            }
        });
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$AndroidPopup_androidKt.INSTANCE.m5936getLambda1$ui_release(), null, 2, null);
        this.locationOnScreen = new int[2];
    }
}
