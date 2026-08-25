package coil.request;

import android.content.Lifecycles;
import android.content.Utils;
import androidx.lifecycle.e;
import androidx.lifecycle.r;
import androidx.lifecycle.x;
import androidx.lifecycle.y;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.ImageLoader;
import coil.target.ViewTarget;
import java.util.concurrent.CancellationException;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0010J\u0017\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001aR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001bR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcoil/request/ViewTargetRequestDelegate;", "Lcoil/request/RequestDelegate;", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/request/ImageRequest;", "initialRequest", "Lcoil/target/ViewTarget;", "target", "Landroidx/lifecycle/r;", "lifecycle", "Lka/v1;", "job", "<init>", "(Lcoil/ImageLoader;Lcoil/request/ImageRequest;Lcoil/target/ViewTarget;Landroidx/lifecycle/r;Lka/v1;)V", "Lx6/t0;", "restart", "()V", "assertActive", TtmlNode.START, "dispose", "Landroidx/lifecycle/y;", "owner", "onDestroy", "(Landroidx/lifecycle/y;)V", "Lcoil/ImageLoader;", "Lcoil/request/ImageRequest;", "Lcoil/target/ViewTarget;", "Landroidx/lifecycle/r;", "Lka/v1;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ViewTargetRequestDelegate implements RequestDelegate {
    private final ImageLoader imageLoader;
    private final ImageRequest initialRequest;
    private final v1 job;
    private final r lifecycle;
    private final ViewTarget<?> target;

    public ViewTargetRequestDelegate(ImageLoader imageLoader, ImageRequest imageRequest, ViewTarget<?> viewTarget, r rVar, v1 v1Var) {
        this.imageLoader = imageLoader;
        this.initialRequest = imageRequest;
        this.target = viewTarget;
        this.lifecycle = rVar;
        this.job = v1Var;
    }

    @Override // coil.request.RequestDelegate
    public void assertActive() {
        if (this.target.getView().isAttachedToWindow()) {
            return;
        }
        Utils.getRequestManager(this.target.getView()).setRequest(this);
        throw new CancellationException("'ViewTarget.view' must be attached to a window.");
    }

    @Override // coil.request.RequestDelegate
    public final /* synthetic */ void complete() {
        b.b(this);
    }

    @Override // coil.request.RequestDelegate
    public void dispose() {
        this.job.cancel((CancellationException) null);
        ViewTarget<?> viewTarget = this.target;
        if (viewTarget instanceof x) {
            this.lifecycle.removeObserver((x) viewTarget);
        }
        this.lifecycle.removeObserver(this);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(y yVar) {
        e.a(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(y owner) {
        Utils.getRequestManager(this.target.getView()).dispose();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(y yVar) {
        e.c(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onResume(y yVar) {
        e.d(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStart(y yVar) {
        e.e(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStop(y yVar) {
        e.f(this, yVar);
    }

    public final void restart() {
        this.imageLoader.enqueue(this.initialRequest);
    }

    @Override // coil.request.RequestDelegate
    public void start() {
        this.lifecycle.addObserver(this);
        ViewTarget<?> viewTarget = this.target;
        if (viewTarget instanceof x) {
            Lifecycles.removeAndAddObserver(this.lifecycle, (x) viewTarget);
        }
        Utils.getRequestManager(this.target.getView()).setRequest(this);
    }
}
