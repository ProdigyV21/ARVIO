package androidx.compose.ui.platform;

import android.view.View;
import android.view.ViewParent;
import androidx.core.view.j2;
import androidx.lifecycle.y;
import androidx.lifecycle.z0;
import com.arvio.tv.R;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000 \b2\u00020\u0001:\u0005\b\t\n\u000b\fJ\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy;", "", "Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Lkotlin/Function0;", "Lx6/t0;", "installFor", "(Landroidx/compose/ui/platform/AbstractComposeView;)Lr7/a;", "Companion", "DisposeOnDetachedFromWindow", "DisposeOnDetachedFromWindowOrReleasedFromPool", "DisposeOnLifecycleDestroyed", "DisposeOnViewTreeLifecycleDestroyed", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ViewCompositionStrategy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy$Companion;", "", "()V", "Default", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "getDefault", "()Landroidx/compose/ui/platform/ViewCompositionStrategy;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final ViewCompositionStrategy getDefault() {
            return DisposeOnDetachedFromWindowOrReleasedFromPool.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy$DisposeOnDetachedFromWindow;", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "<init>", "()V", "Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Lkotlin/Function0;", "Lx6/t0;", "installFor", "(Landroidx/compose/ui/platform/AbstractComposeView;)Lr7/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DisposeOnDetachedFromWindow implements ViewCompositionStrategy {
        public static final int $stable = 0;
        public static final DisposeOnDetachedFromWindow INSTANCE = new DisposeOnDetachedFromWindow();

        private DisposeOnDetachedFromWindow() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View$OnAttachStateChangeListener, androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindow$installFor$listener$1] */
        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public r7.a<t0> installFor(final AbstractComposeView view) {
            ?? r02 = new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindow$installFor$listener$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v2) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v2) {
                    view.disposeComposition();
                }
            };
            view.addOnAttachStateChangeListener(r02);
            return new ViewCompositionStrategy$DisposeOnDetachedFromWindow$installFor$1(view, r02);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool;", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "<init>", "()V", "Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Lkotlin/Function0;", "Lx6/t0;", "installFor", "(Landroidx/compose/ui/platform/AbstractComposeView;)Lr7/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DisposeOnDetachedFromWindowOrReleasedFromPool implements ViewCompositionStrategy {
        public static final int $stable = 0;
        public static final DisposeOnDetachedFromWindowOrReleasedFromPool INSTANCE = new DisposeOnDetachedFromWindowOrReleasedFromPool();

        private DisposeOnDetachedFromWindowOrReleasedFromPool() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [android.view.View$OnAttachStateChangeListener, androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$listener$1] */
        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public r7.a<t0> installFor(final AbstractComposeView view) {
            ?? r02 = new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$listener$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v2) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v2) {
                    boolean z;
                    Iterator it = ga.r.M(view.getParent(), j2.f2276i).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object obj = (ViewParent) it.next();
                        if (obj instanceof View) {
                            Object tag = ((View) obj).getTag(R.id.is_pooling_container_tag);
                            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
                            if (bool != null ? bool.booleanValue() : false) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (z) {
                        return;
                    }
                    view.disposeComposition();
                }
            };
            view.addOnAttachStateChangeListener(r02);
            w0.a aVar = new w0.a() { // from class: androidx.compose.ui.platform.o
                @Override // w0.a
                public final void onRelease() {
                    view.disposeComposition();
                }
            };
            androidx.work.impl.t.s(view).f22337a.add(aVar);
            return new ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1(view, r02, aVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed;", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "<init>", "()V", "Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Lkotlin/Function0;", "Lx6/t0;", "installFor", "(Landroidx/compose/ui/platform/AbstractComposeView;)Lr7/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DisposeOnViewTreeLifecycleDestroyed implements ViewCompositionStrategy {
        public static final int $stable = 0;
        public static final DisposeOnViewTreeLifecycleDestroyed INSTANCE = new DisposeOnViewTreeLifecycleDestroyed();

        private DisposeOnViewTreeLifecycleDestroyed() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [android.view.View$OnAttachStateChangeListener, androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$listener$1] */
        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public r7.a<t0> installFor(final AbstractComposeView view) {
            if (!view.isAttachedToWindow()) {
                final k0 k0Var = new k0();
                ?? r1 = new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$listener$1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View v2) {
                        y yVarE = z0.e(view);
                        AbstractComposeView abstractComposeView = view;
                        if (yVarE != null) {
                            k0Var.f19746i = ViewCompositionStrategy_androidKt.installForLifecycle(abstractComposeView, yVarE.getLifecycle());
                            view.removeOnAttachStateChangeListener(this);
                        } else {
                            throw new IllegalStateException(("View tree for " + abstractComposeView + " has no ViewTreeLifecycleOwner").toString());
                        }
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View v2) {
                    }
                };
                view.addOnAttachStateChangeListener(r1);
                k0Var.f19746i = new ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$1(view, r1);
                return new ViewCompositionStrategy$DisposeOnViewTreeLifecycleDestroyed$installFor$2(k0Var);
            }
            y yVarE = z0.e(view);
            if (yVarE != null) {
                return ViewCompositionStrategy_androidKt.installForLifecycle(view, yVarE.getLifecycle());
            }
            throw new IllegalStateException(("View tree for " + view + " has no ViewTreeLifecycleOwner").toString());
        }
    }

    r7.a<t0> installFor(AbstractComposeView view);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/platform/ViewCompositionStrategy$DisposeOnLifecycleDestroyed;", "Landroidx/compose/ui/platform/ViewCompositionStrategy;", "Landroidx/lifecycle/r;", "lifecycle", "<init>", "(Landroidx/lifecycle/r;)V", "Landroidx/lifecycle/y;", "lifecycleOwner", "(Landroidx/lifecycle/y;)V", "Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Lkotlin/Function0;", "Lx6/t0;", "installFor", "(Landroidx/compose/ui/platform/AbstractComposeView;)Lr7/a;", "Landroidx/lifecycle/r;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DisposeOnLifecycleDestroyed implements ViewCompositionStrategy {
        public static final int $stable = 8;
        private final androidx.lifecycle.r lifecycle;

        public DisposeOnLifecycleDestroyed(androidx.lifecycle.r rVar) {
            this.lifecycle = rVar;
        }

        @Override // androidx.compose.ui.platform.ViewCompositionStrategy
        public r7.a<t0> installFor(AbstractComposeView view) {
            return ViewCompositionStrategy_androidKt.installForLifecycle(view, this.lifecycle);
        }

        public DisposeOnLifecycleDestroyed(y yVar) {
            this(yVar.getLifecycle());
        }
    }
}
