package androidx.compose.ui.platform;

import android.content.Context;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.node.Owner;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\b'\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH'¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\fH\u0014¢\u0006\u0004\b\u0018\u0010\u0016J\u001f\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0010¢\u0006\u0004\b\u001d\u0010\u001cJ7\u0010%\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0004¢\u0006\u0004\b%\u0010&J7\u0010(\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0010¢\u0006\u0004\b'\u0010&J\u0017\u0010*\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u0006H\u0016¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u001fH\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\f2\u0006\u0010,\u001a\u00020\u001fH\u0016¢\u0006\u0004\b.\u0010/J\u0019\u00102\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u000100H\u0016¢\u0006\u0004\b2\u00103J!\u00102\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00104\u001a\u00020\u0006H\u0016¢\u0006\u0004\b2\u00105J)\u00102\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u0006H\u0016¢\u0006\u0004\b2\u00108J#\u00102\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u0001002\b\u0010:\u001a\u0004\u0018\u000109H\u0016¢\u0006\u0004\b2\u0010;J+\u00102\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00104\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u000109H\u0016¢\u0006\u0004\b2\u0010<J+\u0010=\u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00104\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u000109H\u0014¢\u0006\u0004\b=\u0010>J3\u0010=\u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00104\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u0001092\u0006\u0010?\u001a\u00020\u001fH\u0014¢\u0006\u0004\b=\u0010@J\u000f\u0010A\u001a\u00020\u001fH\u0016¢\u0006\u0004\bA\u0010-J\u000f\u0010B\u001a\u00020\fH\u0002¢\u0006\u0004\bB\u0010\u0016J\u0013\u0010C\u001a\u00020\n*\u00020\nH\u0002¢\u0006\u0004\bC\u0010DJ\u000f\u0010E\u001a\u00020\nH\u0002¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\fH\u0002¢\u0006\u0004\bG\u0010\u0016R\u001e\u0010I\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010H8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR(\u0010M\u001a\u0004\u0018\u00010K2\b\u0010L\u001a\u0004\u0018\u00010K8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010SR(\u0010T\u001a\u0004\u0018\u00010\n2\b\u0010L\u001a\u0004\u0018\u00010\n8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bT\u0010U\"\u0004\bV\u0010\u000eR$\u0010X\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010W8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\bX\u0010Y\u0012\u0004\bZ\u0010\u0016R0\u0010[\u001a\u00020\u001f2\u0006\u0010L\u001a\u00020\u001f8F@FX\u0087\u000e¢\u0006\u0018\n\u0004\b[\u0010\\\u0012\u0004\b_\u0010\u0016\u001a\u0004\b]\u0010-\"\u0004\b^\u0010/R\u0016\u0010`\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010\\R\u0016\u0010a\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010\\R\u0014\u0010c\u001a\u00020\u001f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\bb\u0010-R\u0011\u0010e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\bd\u0010-R\u0018\u0010f\u001a\u00020\u001f*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bf\u0010g¨\u0006h"}, d2 = {"Landroidx/compose/ui/platform/AbstractComposeView;", "Landroid/view/ViewGroup;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Landroidx/compose/runtime/CompositionContext;", "parent", "Lx6/t0;", "setParentCompositionContext", "(Landroidx/compose/runtime/CompositionContext;)V", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "strategy", "setViewCompositionStrategy", "(Landroidx/compose/ui/platform/ViewCompositionStrategy;)V", "Content", "(Landroidx/compose/runtime/Composer;I)V", "createComposition", "()V", "disposeComposition", "onAttachedToWindow", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "internalOnMeasure$ui_release", "internalOnMeasure", "", "changed", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "onLayout", "(ZIIII)V", "internalOnLayout$ui_release", "internalOnLayout", "layoutDirection", "onRtlPropertiesChanged", "(I)V", "isTransitionGroup", "()Z", "setTransitionGroup", "(Z)V", "Landroid/view/View;", "child", "addView", "(Landroid/view/View;)V", "index", "(Landroid/view/View;I)V", "width", "height", "(Landroid/view/View;II)V", "Landroid/view/ViewGroup$LayoutParams;", "params", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V", "(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V", "addViewInLayout", "(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)Z", "preventRequestLayout", "(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;Z)Z", "shouldDelayChildPressedState", "checkAddView", "cacheIfAlive", "(Landroidx/compose/runtime/CompositionContext;)Landroidx/compose/runtime/CompositionContext;", "resolveParentCompositionContext", "()Landroidx/compose/runtime/CompositionContext;", "ensureCompositionCreated", "Ljava/lang/ref/WeakReference;", "cachedViewTreeCompositionContext", "Ljava/lang/ref/WeakReference;", "Landroid/os/IBinder;", "value", "previousAttachedWindowToken", "Landroid/os/IBinder;", "setPreviousAttachedWindowToken", "(Landroid/os/IBinder;)V", "Landroidx/compose/runtime/Composition;", "composition", "Landroidx/compose/runtime/Composition;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "setParentContext", "Lkotlin/Function0;", "disposeViewCompositionStrategy", "Lr7/a;", "getDisposeViewCompositionStrategy$annotations", "showLayoutBounds", "Z", "getShowLayoutBounds", "setShowLayoutBounds", "getShowLayoutBounds$annotations", "creatingComposition", "isTransitionGroupSet", "getShouldCreateCompositionOnAttachedToWindow", "shouldCreateCompositionOnAttachedToWindow", "getHasComposition", "hasComposition", "isAlive", "(Landroidx/compose/runtime/CompositionContext;)Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AbstractComposeView extends ViewGroup {
    public static final int $stable = 8;
    private WeakReference<CompositionContext> cachedViewTreeCompositionContext;
    private Composition composition;
    private boolean creatingComposition;
    private r7.a<t0> disposeViewCompositionStrategy;
    private boolean isTransitionGroupSet;
    private CompositionContext parentContext;
    private IBinder previousAttachedWindowToken;
    private boolean showLayoutBounds;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {
        public AnonymousClass1() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-656146368, i10, -1, "androidx.compose.ui.platform.AbstractComposeView.ensureCompositionCreated.<anonymous> (ComposeView.android.kt:251)");
            }
            AbstractComposeView.this.Content(composer, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public AbstractComposeView(Context context) {
        this(context, null, 0, 6, null);
    }

    private final CompositionContext cacheIfAlive(CompositionContext compositionContext) {
        CompositionContext compositionContext2 = isAlive(compositionContext) ? compositionContext : null;
        if (compositionContext2 != null) {
            this.cachedViewTreeCompositionContext = new WeakReference<>(compositionContext2);
        }
        return compositionContext;
    }

    private final void checkAddView() {
        if (this.creatingComposition) {
            return;
        }
        throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
    }

    private final void ensureCompositionCreated() {
        if (this.composition == null) {
            try {
                this.creatingComposition = true;
                this.composition = Wrapper_androidKt.setContent(this, resolveParentCompositionContext(), ComposableLambdaKt.composableLambdaInstance(-656146368, true, new AnonymousClass1()));
            } finally {
                this.creatingComposition = false;
            }
        }
    }

    private static /* synthetic */ void getDisposeViewCompositionStrategy$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    private final boolean isAlive(CompositionContext compositionContext) {
        return !(compositionContext instanceof Recomposer) || ((Recomposer.State) ((Recomposer) compositionContext).getCurrentState().getValue()).compareTo(Recomposer.State.ShuttingDown) > 0;
    }

    private final CompositionContext resolveParentCompositionContext() {
        CompositionContext compositionContext;
        CompositionContext compositionContextCacheIfAlive = this.parentContext;
        if (compositionContextCacheIfAlive == null) {
            CompositionContext compositionContextFindViewTreeCompositionContext = WindowRecomposer_androidKt.findViewTreeCompositionContext(this);
            CompositionContext compositionContext2 = null;
            compositionContextCacheIfAlive = compositionContextFindViewTreeCompositionContext != null ? cacheIfAlive(compositionContextFindViewTreeCompositionContext) : null;
            if (compositionContextCacheIfAlive == null) {
                WeakReference<CompositionContext> weakReference = this.cachedViewTreeCompositionContext;
                if (weakReference != null && (compositionContext = weakReference.get()) != null && isAlive(compositionContext)) {
                    compositionContext2 = compositionContext;
                }
                return compositionContext2 == null ? cacheIfAlive(WindowRecomposer_androidKt.getWindowRecomposer(this)) : compositionContext2;
            }
        }
        return compositionContextCacheIfAlive;
    }

    private final void setParentContext(CompositionContext compositionContext) {
        if (this.parentContext != compositionContext) {
            this.parentContext = compositionContext;
            if (compositionContext != null) {
                this.cachedViewTreeCompositionContext = null;
            }
            Composition composition = this.composition;
            if (composition != null) {
                composition.dispose();
                this.composition = null;
                if (isAttachedToWindow()) {
                    ensureCompositionCreated();
                }
            }
        }
    }

    private final void setPreviousAttachedWindowToken(IBinder iBinder) {
        if (this.previousAttachedWindowToken != iBinder) {
            this.previousAttachedWindowToken = iBinder;
            this.cachedViewTreeCompositionContext = null;
        }
    }

    public abstract void Content(Composer composer, int i10);

    @Override // android.view.ViewGroup
    public void addView(View child) {
        checkAddView();
        super.addView(child);
    }

    @Override // android.view.ViewGroup
    public boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkAddView();
        return super.addViewInLayout(child, index, params);
    }

    public final void createComposition() {
        if (this.parentContext == null && !isAttachedToWindow()) {
            throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.");
        }
        ensureCompositionCreated();
    }

    public final void disposeComposition() {
        Composition composition = this.composition;
        if (composition != null) {
            composition.dispose();
        }
        this.composition = null;
        requestLayout();
    }

    public final boolean getHasComposition() {
        return this.composition != null;
    }

    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return true;
    }

    public final boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    public void internalOnLayout$ui_release(boolean changed, int left, int top, int right, int bottom) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (right - left) - getPaddingRight(), (bottom - top) - getPaddingBottom());
        }
    }

    public void internalOnMeasure$ui_release(int widthMeasureSpec, int heightMeasureSpec) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        childAt.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight()), View.MeasureSpec.getMode(widthMeasureSpec)), View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()) - getPaddingBottom()), View.MeasureSpec.getMode(heightMeasureSpec)));
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + childAt.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight());
    }

    @Override // android.view.ViewGroup
    public boolean isTransitionGroup() {
        return !this.isTransitionGroupSet || super.isTransitionGroup();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPreviousAttachedWindowToken(getWindowToken());
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            ensureCompositionCreated();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        internalOnLayout$ui_release(changed, left, top, right, bottom);
    }

    @Override // android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ensureCompositionCreated();
        internalOnMeasure$ui_release(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        childAt.setLayoutDirection(layoutDirection);
    }

    public final void setParentCompositionContext(CompositionContext parent) {
        setParentContext(parent);
    }

    public final void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt != null) {
            ((Owner) childAt).setShowLayoutBounds(z);
        }
    }

    @Override // android.view.ViewGroup
    public void setTransitionGroup(boolean isTransitionGroup) {
        super.setTransitionGroup(isTransitionGroup);
        this.isTransitionGroupSet = true;
    }

    public final void setViewCompositionStrategy(ViewCompositionStrategy strategy) {
        r7.a<t0> aVar = this.disposeViewCompositionStrategy;
        if (aVar != null) {
            aVar.invoke();
        }
        this.disposeViewCompositionStrategy = strategy.installFor(this);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public AbstractComposeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ AbstractComposeView(Context context, AttributeSet attributeSet, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(context, (i11 & 2) != 0 ? null : attributeSet, (i11 & 4) != 0 ? 0 : i10);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        checkAddView();
        super.addView(child, index);
    }

    @Override // android.view.ViewGroup
    public boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkAddView();
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    public AbstractComposeView(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        setClipChildren(false);
        setClipToPadding(false);
        this.disposeViewCompositionStrategy = ViewCompositionStrategy.INSTANCE.getDefault().installFor(this);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int width, int height) {
        checkAddView();
        super.addView(child, width, height);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkAddView();
        super.addView(child, params);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkAddView();
        super.addView(child, index, params);
    }
}
