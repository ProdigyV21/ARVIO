package coil.request;

import android.content.Utils;
import android.view.View;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import ka.m0;
import ka.n1;
import ka.p0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import pa.q;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0017\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u001a\u0010\u0005J\u0017\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u001b\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcoil/request/ViewTargetRequestManager;", "Landroid/view/View$OnAttachStateChangeListener;", "Landroid/view/View;", "view", "<init>", "(Landroid/view/View;)V", "Lcoil/request/ViewTargetDisposable;", "disposable", "", "isDisposed", "(Lcoil/request/ViewTargetDisposable;)Z", "Lka/p0;", "Lcoil/request/ImageResult;", "job", "getDisposable", "(Lka/p0;)Lcoil/request/ViewTargetDisposable;", "Lx6/t0;", "dispose", "()V", "getResult", "()Lcoil/request/ImageResult;", "Lcoil/request/ViewTargetRequestDelegate;", "request", "setRequest", "(Lcoil/request/ViewTargetRequestDelegate;)V", "v", "onViewAttachedToWindow", "onViewDetachedFromWindow", "Landroid/view/View;", "currentDisposable", "Lcoil/request/ViewTargetDisposable;", "Lka/v1;", "pendingClear", "Lka/v1;", "currentRequest", "Lcoil/request/ViewTargetRequestDelegate;", "isRestart", "Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ViewTargetRequestManager implements View.OnAttachStateChangeListener {
    private ViewTargetDisposable currentDisposable;
    private ViewTargetRequestDelegate currentRequest;
    private boolean isRestart;
    private v1 pendingClear;
    private final View view;

    /* JADX INFO: renamed from: coil.request.ViewTargetRequestManager$dispose$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.request.ViewTargetRequestManager$dispose$1", f = "ViewTargetRequestManager.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return ViewTargetRequestManager.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            ViewTargetRequestManager.this.setRequest(null);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public ViewTargetRequestManager(View view) {
        this.view = view;
    }

    public final synchronized void dispose() {
        try {
            v1 v1Var = this.pendingClear;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            n1 n1Var = n1.f19624i;
            x0 x0Var = x0.f19652a;
            this.pendingClear = m0.p(n1Var, ((la.d) q.f21294a).f20077n, 0, new AnonymousClass1(null), 2);
            this.currentDisposable = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized ViewTargetDisposable getDisposable(p0<? extends ImageResult> job) {
        ViewTargetDisposable viewTargetDisposable = this.currentDisposable;
        if (viewTargetDisposable != null && Utils.isMainThread() && this.isRestart) {
            this.isRestart = false;
            viewTargetDisposable.setJob(job);
            return viewTargetDisposable;
        }
        v1 v1Var = this.pendingClear;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.pendingClear = null;
        ViewTargetDisposable viewTargetDisposable2 = new ViewTargetDisposable(this.view, job);
        this.currentDisposable = viewTargetDisposable2;
        return viewTargetDisposable2;
    }

    public final synchronized ImageResult getResult() {
        ViewTargetDisposable viewTargetDisposable;
        p0<ImageResult> job;
        viewTargetDisposable = this.currentDisposable;
        return (viewTargetDisposable == null || (job = viewTargetDisposable.getJob()) == null) ? null : (ImageResult) Utils.getCompletedOrNull(job);
    }

    public final synchronized boolean isDisposed(ViewTargetDisposable disposable) {
        return disposable != this.currentDisposable;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v2) {
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.currentRequest;
        if (viewTargetRequestDelegate == null) {
            return;
        }
        this.isRestart = true;
        viewTargetRequestDelegate.restart();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v2) {
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.currentRequest;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.dispose();
        }
    }

    public final void setRequest(ViewTargetRequestDelegate request) {
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.currentRequest;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.dispose();
        }
        this.currentRequest = request;
    }
}
