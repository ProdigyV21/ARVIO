package androidx.compose.ui.platform;

import android.os.Handler;
import android.view.View;
import androidx.compose.runtime.Recomposer;
import com.google.common.util.concurrent.r0;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import ka.m0;
import ka.n1;
import ka.o2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ,\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/platform/WindowRecomposerPolicy;", "", "<init>", "()V", "Landroidx/compose/ui/platform/WindowRecomposerFactory;", "factory", "getAndSetFactory", "(Landroidx/compose/ui/platform/WindowRecomposerFactory;)Landroidx/compose/ui/platform/WindowRecomposerFactory;", "expected", "", "compareAndSetFactory", "(Landroidx/compose/ui/platform/WindowRecomposerFactory;Landroidx/compose/ui/platform/WindowRecomposerFactory;)Z", "Lx6/t0;", "setFactory", "(Landroidx/compose/ui/platform/WindowRecomposerFactory;)V", "R", "Lkotlin/Function0;", "block", "withFactory", "(Landroidx/compose/ui/platform/WindowRecomposerFactory;Lr7/a;)Ljava/lang/Object;", "Landroid/view/View;", "rootView", "Landroidx/compose/runtime/Recomposer;", "createAndInstallWindowRecomposer$ui_release", "(Landroid/view/View;)Landroidx/compose/runtime/Recomposer;", "createAndInstallWindowRecomposer", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WindowRecomposerPolicy {
    public static final WindowRecomposerPolicy INSTANCE = new WindowRecomposerPolicy();
    private static final AtomicReference<WindowRecomposerFactory> factory = new AtomicReference<>(WindowRecomposerFactory.INSTANCE.getLifecycleAware());
    public static final int $stable = 8;

    private WindowRecomposerPolicy() {
    }

    public final boolean compareAndSetFactory(WindowRecomposerFactory expected, WindowRecomposerFactory factory2) {
        AtomicReference<WindowRecomposerFactory> atomicReference = factory;
        while (!atomicReference.compareAndSet(expected, factory2)) {
            if (atomicReference.get() != expected) {
                return false;
            }
        }
        return true;
    }

    public final Recomposer createAndInstallWindowRecomposer$ui_release(View rootView) {
        Recomposer recomposerCreateRecomposer = factory.get().createRecomposer(rootView);
        WindowRecomposer_androidKt.setCompositionContext(rootView, recomposerCreateRecomposer);
        Handler handler = rootView.getHandler();
        int i10 = la.f.f20078a;
        la.d dVar = new la.d(handler, "windowRecomposer cleanup", false);
        final o2 o2VarP = m0.p(n1.f19624i, dVar.f20077n, 0, new WindowRecomposerPolicy$createAndInstallWindowRecomposer$unsetJob$1(recomposerCreateRecomposer, rootView, null), 2);
        rootView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.WindowRecomposerPolicy$createAndInstallWindowRecomposer$1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                v2.removeOnAttachStateChangeListener(this);
                o2VarP.cancel((CancellationException) null);
            }
        });
        return recomposerCreateRecomposer;
    }

    public final WindowRecomposerFactory getAndSetFactory(WindowRecomposerFactory factory2) {
        return factory.getAndSet(factory2);
    }

    public final void setFactory(WindowRecomposerFactory factory2) {
        factory.set(factory2);
    }

    public final <R> R withFactory(WindowRecomposerFactory factory2, r7.a<? extends R> block) throws IllegalAccessException, InvocationTargetException {
        WindowRecomposerFactory andSetFactory = getAndSetFactory(factory2);
        try {
            R r4 = (R) block.invoke();
            if (compareAndSetFactory(factory2, andSetFactory)) {
                return r4;
            }
            throw new IllegalStateException("WindowRecomposerFactory was set to unexpected value; cannot safely restore old state");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (compareAndSetFactory(factory2, andSetFactory)) {
                    throw th2;
                }
                r0.a(th, new IllegalStateException("WindowRecomposerFactory was set to unexpected value; cannot safely restore old state"));
                throw th;
            }
        }
    }
}
