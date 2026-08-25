package androidx.compose.material3.internal;

import a0.c;
import android.R;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
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
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.lifecycle.z0;
import f4.f;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.a;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B?\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0005¢\u0006\u0004\b\u001a\u0010\u001bJ(\u0010 \u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001c2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0005H\u0017¢\u0006\u0004\b\"\u0010#J\u0017\u0010&\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J%\u0010(\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\u0005¢\u0006\u0004\b*\u0010\u001bJ\r\u0010+\u001a\u00020\u0005¢\u0006\u0004\b+\u0010\u001bJ\u0019\u0010-\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010,H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020/H\u0016¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0005H\u0016¢\u0006\u0004\b2\u0010\u001bR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u00103R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00104R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u00105R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\"\u0010<\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010\u0016R/\u0010I\u001a\u0004\u0018\u00010A2\b\u0010B\u001a\u0004\u0018\u00010A8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR5\u0010P\u001a\u0004\u0018\u00010J2\b\u0010B\u001a\u0004\u0018\u00010J8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bK\u0010D\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001b\u0010U\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR\u001a\u0010W\u001a\u00020V8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010\\\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010[RA\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e2\u0011\u0010B\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b]\u0010D\u001a\u0004\b^\u0010_\"\u0004\b \u0010`R$\u0010a\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020\u000b8\u0014@RX\u0094\u000e¢\u0006\f\n\u0004\ba\u00106\u001a\u0004\bb\u0010TR\u0014\u0010e\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010d\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006f"}, d2 = {"Landroidx/compose/material3/internal/PopupLayout;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroid/view/View;", "composeView", "Landroidx/compose/ui/window/PopupPositionProvider;", "positionProvider", "", "isTouchExplorationEnabled", "Landroidx/compose/ui/unit/Density;", "density", "Ljava/util/UUID;", "popupId", "<init>", "(Lr7/a;Landroid/view/View;Landroidx/compose/ui/window/PopupPositionProvider;ZLandroidx/compose/ui/unit/Density;Ljava/util/UUID;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "superSetLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroid/view/WindowManager$LayoutParams;", "createLayoutParams", "()Landroid/view/WindowManager$LayoutParams;", "show", "()V", "Landroidx/compose/runtime/CompositionContext;", "parent", "Landroidx/compose/runtime/Composable;", "content", "setContent", "(Landroidx/compose/runtime/CompositionContext;Lr7/p;)V", "Content", "(Landroidx/compose/runtime/Composer;I)V", "Landroid/view/KeyEvent;", "event", "dispatchKeyEvent", "(Landroid/view/KeyEvent;)Z", "updateParameters", "(Lr7/a;Landroidx/compose/ui/unit/LayoutDirection;)V", "updatePosition", "dismiss", "Landroid/view/MotionEvent;", "onTouchEvent", "(Landroid/view/MotionEvent;)Z", "", "setLayoutDirection", "(I)V", "onGlobalLayout", "Lr7/a;", "Landroid/view/View;", "Landroidx/compose/ui/window/PopupPositionProvider;", "Z", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "params", "Landroid/view/WindowManager$LayoutParams;", "parentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setParentLayoutDirection", "Landroidx/compose/ui/unit/IntRect;", "<set-?>", "parentBounds$delegate", "Landroidx/compose/runtime/MutableState;", "getParentBounds", "()Landroidx/compose/ui/unit/IntRect;", "setParentBounds", "(Landroidx/compose/ui/unit/IntRect;)V", "parentBounds", "Landroidx/compose/ui/unit/IntSize;", "popupContentSize$delegate", "getPopupContentSize-bOM6tXw", "()Landroidx/compose/ui/unit/IntSize;", "setPopupContentSize-fhxjrPA", "(Landroidx/compose/ui/unit/IntSize;)V", "popupContentSize", "canCalculatePosition$delegate", "Landroidx/compose/runtime/State;", "getCanCalculatePosition", "()Z", "canCalculatePosition", "Landroidx/compose/ui/unit/Dp;", "maxSupportedElevation", "F", "Landroid/graphics/Rect;", "previousWindowVisibleFrame", "Landroid/graphics/Rect;", "tmpWindowVisibleFrame", "content$delegate", "getContent", "()Lr7/p;", "(Lr7/p;)V", "shouldCreateCompositionOnAttachedToWindow", "getShouldCreateCompositionOnAttachedToWindow", "getSubCompositionView", "()Landroidx/compose/ui/platform/AbstractComposeView;", "subCompositionView", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PopupLayout extends AbstractComposeView implements ViewRootForInspector, ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: canCalculatePosition$delegate, reason: from kotlin metadata */
    private final State canCalculatePosition;
    private final View composeView;

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private final boolean isTouchExplorationEnabled;
    private final float maxSupportedElevation;
    private a<t0> onDismissRequest;
    private final WindowManager.LayoutParams params;

    /* JADX INFO: renamed from: parentBounds$delegate, reason: from kotlin metadata */
    private final MutableState parentBounds;
    private LayoutDirection parentLayoutDirection;

    /* JADX INFO: renamed from: popupContentSize$delegate, reason: from kotlin metadata */
    private final MutableState popupContentSize;
    private final PopupPositionProvider positionProvider;
    private final Rect previousWindowVisibleFrame;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final Rect tmpWindowVisibleFrame;
    private final WindowManager windowManager;

    /* JADX INFO: renamed from: androidx.compose.material3.internal.PopupLayout$Content$4, reason: invalid class name */
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

    public PopupLayout(a<t0> aVar, View view, PopupPositionProvider popupPositionProvider, boolean z, Density density, UUID uuid) {
        super(view.getContext(), null, 0, 6, null);
        this.onDismissRequest = aVar;
        this.composeView = view;
        this.positionProvider = popupPositionProvider;
        this.isTouchExplorationEnabled = z;
        this.windowManager = (WindowManager) view.getContext().getSystemService("window");
        this.params = createLayoutParams();
        this.parentLayoutDirection = LayoutDirection.Ltr;
        this.parentBounds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.popupContentSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.canCalculatePosition = SnapshotStateKt.derivedStateOf(new PopupLayout$canCalculatePosition$2(this));
        float fM5678constructorimpl = Dp.m5678constructorimpl(8);
        this.maxSupportedElevation = fM5678constructorimpl;
        this.previousWindowVisibleFrame = new Rect();
        this.tmpWindowVisibleFrame = new Rect();
        setId(R.id.content);
        z0.i(this, z0.e(view));
        z0.j(this, z0.f(view));
        f.w(this, f.k(view));
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        setTag(androidx.compose.ui.R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        setElevation(density.mo285toPx0680j_4(fM5678constructorimpl));
        setOutlineProvider(new ViewOutlineProvider() { // from class: androidx.compose.material3.internal.PopupLayout.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline result) {
                result.setRect(0, 0, view2.getWidth(), view2.getHeight());
                result.setAlpha(0.0f);
            }
        });
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$ExposedDropdownMenuPopup_androidKt.INSTANCE.m2318getLambda1$material3_release(), null, 2, null);
    }

    private final WindowManager.LayoutParams createLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.flags = 393248;
        layoutParams.flags = this.isTouchExplorationEnabled ? 393248 & (-33) : 393248;
        layoutParams.softInputMode = 1;
        layoutParams.type = 1000;
        layoutParams.token = this.composeView.getApplicationWindowToken();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.setTitle(this.composeView.getContext().getResources().getString(androidx.compose.ui.R.string.default_popup_window_title));
        return layoutParams;
    }

    private final p<Composer, Integer, t0> getContent() {
        return (p) this.content.getValue();
    }

    private final void setContent(p<? super Composer, ? super Integer, t0> pVar) {
        this.content.setValue(pVar);
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
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1284481754);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1284481754, i11, -1, "androidx.compose.material3.internal.PopupLayout.Content (ExposedDropdownMenuPopup.android.kt:279)");
            }
            if (c.A(composerStartRestartGroup, 0, getContent())) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(i10));
        }
    }

    public final void dismiss() {
        z0.i(this, null);
        this.composeView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (event.getKeyCode() == 4) {
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
                a<t0> aVar = this.onDismissRequest;
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

    /* JADX WARN: Multi-variable type inference failed */
    public final IntRect getParentBounds() {
        return (IntRect) this.parentBounds.getValue();
    }

    public final LayoutDirection getParentLayoutDirection() {
        return this.parentLayoutDirection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final IntSize m2321getPopupContentSizebOM6tXw() {
        return (IntSize) this.popupContentSize.getValue();
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public AbstractComposeView getSubCompositionView() {
        return this;
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public final /* synthetic */ View getViewRoot() {
        return androidx.compose.ui.platform.r.b(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.composeView.getWindowVisibleDisplayFrame(this.tmpWindowVisibleFrame);
        if (kotlin.jvm.internal.p.a(this.tmpWindowVisibleFrame, this.previousWindowVisibleFrame)) {
            return;
        }
        updatePosition();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event == null) {
            return super.onTouchEvent(event);
        }
        if (event.getAction() == 4 || (event.getAction() == 0 && (event.getX() < 0.0f || event.getX() >= getWidth() || event.getY() < 0.0f || event.getY() >= getHeight()))) {
            boolean z = event.getRawX() == 0.0f && event.getRawY() == 0.0f;
            if (getParentBounds() == null || !z) {
                a<t0> aVar = this.onDismissRequest;
                if (aVar != null) {
                    aVar.invoke();
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override // android.view.View
    public void setLayoutDirection(int layoutDirection) {
    }

    public final void setParentBounds(IntRect intRect) {
        this.parentBounds.setValue(intRect);
    }

    public final void setParentLayoutDirection(LayoutDirection layoutDirection) {
        this.parentLayoutDirection = layoutDirection;
    }

    /* JADX INFO: renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m2322setPopupContentSizefhxjrPA(IntSize intSize) {
        this.popupContentSize.setValue(intSize);
    }

    public final void show() {
        this.windowManager.addView(this, this.params);
    }

    public final void updateParameters(a<t0> onDismissRequest, LayoutDirection layoutDirection) {
        this.onDismissRequest = onDismissRequest;
        superSetLayoutDirection(layoutDirection);
    }

    public final void updatePosition() {
        IntSize intSizeM2321getPopupContentSizebOM6tXw;
        IntRect parentBounds = getParentBounds();
        if (parentBounds == null || (intSizeM2321getPopupContentSizebOM6tXw = m2321getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        long packedValue = intSizeM2321getPopupContentSizebOM6tXw.getPackedValue();
        Rect rect = this.previousWindowVisibleFrame;
        this.composeView.getWindowVisibleDisplayFrame(rect);
        long jMo959calculatePositionllwVHH4 = this.positionProvider.mo959calculatePositionllwVHH4(parentBounds, RectHelper_androidKt.toComposeIntRect(rect).m5828getSizeYbymL2g(), this.parentLayoutDirection, packedValue);
        this.params.x = IntOffset.m5802getXimpl(jMo959calculatePositionllwVHH4);
        this.params.y = IntOffset.m5803getYimpl(jMo959calculatePositionllwVHH4);
        this.windowManager.updateViewLayout(this, this.params);
    }

    public final void setContent(CompositionContext parent, p<? super Composer, ? super Integer, t0> content) {
        setParentCompositionContext(parent);
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
    }
}
