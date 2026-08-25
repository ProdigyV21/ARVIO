package androidx.compose.material3;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.z0;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import q7.n;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001EB-\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0007H\u0017¢\u0006\u0004\b\u0012\u0010\u0013J,\u0010\u0018\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u0010J\r\u0010\u001b\u001a\u00020\u0007¢\u0006\u0004\b\u001b\u0010\u0010J\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0007H\u0014¢\u0006\u0004\b!\u0010\u0010J\u000f\u0010\"\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\"\u0010\u0010J\u000f\u0010#\u001a\u00020\u0007H\u0016¢\u0006\u0004\b#\u0010\u0010J\u0017\u0010&\u001a\u00020\u00072\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J\u0015\u0010)\u001a\u00020\u00072\u0006\u0010%\u001a\u00020(¢\u0006\u0004\b)\u0010*R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010+R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010,R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106RA\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\u00162\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R$\u0010>\u001a\u00020\u001e2\u0006\u00107\u001a\u00020\u001e8\u0014@RX\u0094\u000e¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0014\u0010D\u001a\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006F"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetWindow;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "Landroidx/compose/material3/ModalBottomSheetProperties;", "properties", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroid/view/View;", "composeView", "Ljava/util/UUID;", "saveId", "<init>", "(Landroidx/compose/material3/ModalBottomSheetProperties;Lr7/a;Landroid/view/View;Ljava/util/UUID;)V", "maybeRegisterBackCallback", "()V", "maybeUnregisterBackCallback", "Content", "(Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/CompositionContext;", "parent", "Landroidx/compose/runtime/Composable;", "content", "setCustomContent", "(Landroidx/compose/runtime/CompositionContext;Lr7/p;)V", "show", "dismiss", "Landroid/view/KeyEvent;", "event", "", "dispatchKeyEvent", "(Landroid/view/KeyEvent;)Z", "onAttachedToWindow", "onDetachedFromWindow", "onGlobalLayout", "", "layoutDirection", "setLayoutDirection", "(I)V", "Landroidx/compose/ui/unit/LayoutDirection;", "superSetLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/material3/ModalBottomSheetProperties;", "Lr7/a;", "Landroid/view/View;", "", "backCallback", "Ljava/lang/Object;", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "Landroid/view/WindowManager$LayoutParams;", "params", "Landroid/view/WindowManager$LayoutParams;", "<set-?>", "content$delegate", "Landroidx/compose/runtime/MutableState;", "getContent", "()Lr7/p;", "setContent", "(Lr7/p;)V", "shouldCreateCompositionOnAttachedToWindow", "Z", "getShouldCreateCompositionOnAttachedToWindow", "()Z", "getDisplayWidth", "()I", "displayWidth", "Api33Impl", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ModalBottomSheetWindow extends AbstractComposeView implements ViewTreeObserver.OnGlobalLayoutListener, ViewRootForInspector {
    private Object backCallback;
    private final View composeView;

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private r7.a<t0> onDismissRequest;
    private final WindowManager.LayoutParams params;
    private final ModalBottomSheetProperties properties;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final WindowManager windowManager;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetWindow$Api33Impl;", "", "<init>", "()V", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroid/window/OnBackInvokedCallback;", "createBackCallback", "(Lr7/a;)Landroid/window/OnBackInvokedCallback;", "Landroid/view/View;", "view", "backCallback", "maybeRegisterBackCallback", "(Landroid/view/View;Ljava/lang/Object;)V", "maybeUnregisterBackCallback", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        @n
        public static final OnBackInvokedCallback createBackCallback(final r7.a<t0> onDismissRequest) {
            return new OnBackInvokedCallback() { // from class: androidx.compose.material3.e
                public final void onBackInvoked() {
                    onDismissRequest.invoke();
                }
            };
        }

        @n
        public static final void maybeRegisterBackCallback(View view, Object backCallback) {
            OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
            if (!(backCallback instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
                return;
            }
            onBackInvokedDispatcherFindOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) backCallback);
        }

        @n
        public static final void maybeUnregisterBackCallback(View view, Object backCallback) {
            OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
            if (!(backCallback instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
                return;
            }
            onBackInvokedDispatcherFindOnBackInvokedDispatcher.unregisterOnBackInvokedCallback((OnBackInvokedCallback) backCallback);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ModalBottomSheetWindow$Content$4, reason: invalid class name */
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
            ModalBottomSheetWindow.this.Content(composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
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

    public ModalBottomSheetWindow(ModalBottomSheetProperties modalBottomSheetProperties, r7.a<t0> aVar, View view, UUID uuid) {
        super(view.getContext(), null, 0, 6, null);
        this.properties = modalBottomSheetProperties;
        this.onDismissRequest = aVar;
        this.composeView = view;
        setId(android.R.id.content);
        z0.i(this, z0.e(view));
        z0.j(this, z0.f(view));
        f4.f.w(this, f4.f.k(view));
        setTag(androidx.compose.ui.R.id.compose_view_saveable_id_tag, "Popup:" + uuid);
        setClipChildren(false);
        this.windowManager = (WindowManager) view.getContext().getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388691;
        layoutParams.type = 1000;
        layoutParams.width = getDisplayWidth();
        layoutParams.height = -1;
        layoutParams.format = -3;
        layoutParams.setTitle(view.getContext().getResources().getString(androidx.compose.ui.R.string.default_popup_window_title));
        layoutParams.token = view.getApplicationWindowToken();
        layoutParams.flags = (layoutParams.flags & (-163841)) | 512;
        if (ModalBottomSheet_androidKt.shouldApplySecureFlag(modalBottomSheetProperties.getSecurePolicy(), ModalBottomSheet_androidKt.isFlagSecureEnabled(view))) {
            layoutParams.flags |= 8192;
        } else {
            layoutParams.flags &= -8193;
        }
        if (modalBottomSheetProperties.getIsFocusable()) {
            layoutParams.flags &= -9;
        } else {
            layoutParams.flags |= 8;
        }
        this.params = layoutParams;
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m1428getLambda2$material3_release(), null, 2, null);
    }

    private final p<Composer, Integer, t0> getContent() {
        return (p) this.content.getValue();
    }

    private final int getDisplayWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    private final void maybeRegisterBackCallback() {
        if (!this.properties.getShouldDismissOnBackPress() || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (this.backCallback == null) {
            this.backCallback = Api33Impl.createBackCallback(this.onDismissRequest);
        }
        Api33Impl.maybeRegisterBackCallback(this, this.backCallback);
    }

    private final void maybeUnregisterBackCallback() {
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.maybeUnregisterBackCallback(this, this.backCallback);
        }
        this.backCallback = null;
    }

    private final void setContent(p<? super Composer, ? super Integer, t0> pVar) {
        this.content.setValue(pVar);
    }

    public static /* synthetic */ void setCustomContent$default(ModalBottomSheetWindow modalBottomSheetWindow, CompositionContext compositionContext, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            compositionContext = null;
        }
        modalBottomSheetWindow.setCustomContent(compositionContext, pVar);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-463309699);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-463309699, i11, -1, "androidx.compose.material3.ModalBottomSheetWindow.Content (ModalBottomSheet.android.kt:562)");
            }
            if (a0.c.A(composerStartRestartGroup, 0, getContent())) {
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
        f4.f.w(this, null);
        this.composeView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        KeyEvent.DispatcherState keyDispatcherState;
        if (event.getKeyCode() == 4 && this.properties.getShouldDismissOnBackPress()) {
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
                this.onDismissRequest.invoke();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public final /* synthetic */ AbstractComposeView getSubCompositionView() {
        return androidx.compose.ui.platform.r.a(this);
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public final /* synthetic */ View getViewRoot() {
        return androidx.compose.ui.platform.r.b(this);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeRegisterBackCallback();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        maybeUnregisterBackCallback();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
    }

    public final void setCustomContent(CompositionContext parent, p<? super Composer, ? super Integer, t0> content) {
        if (parent != null) {
            setParentCompositionContext(parent);
        }
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
    }

    @Override // android.view.View
    public void setLayoutDirection(int layoutDirection) {
    }

    public final void show() {
        this.windowManager.addView(this, this.params);
    }

    public final void superSetLayoutDirection(LayoutDirection layoutDirection) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        int i11 = 1;
        if (i10 == 1) {
            i11 = 0;
        } else if (i10 != 2) {
            throw new NoWhenBranchMatchedException();
        }
        super.setLayoutDirection(i11);
    }
}
