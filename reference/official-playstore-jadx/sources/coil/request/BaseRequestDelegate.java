package coil.request;

import androidx.lifecycle.e;
import androidx.lifecycle.r;
import androidx.lifecycle.y;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.concurrent.CancellationException;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\nJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcoil/request/BaseRequestDelegate;", "Lcoil/request/RequestDelegate;", "Landroidx/lifecycle/r;", "lifecycle", "Lka/v1;", "job", "<init>", "(Landroidx/lifecycle/r;Lka/v1;)V", "Lx6/t0;", TtmlNode.START, "()V", "complete", "dispose", "Landroidx/lifecycle/y;", "owner", "onDestroy", "(Landroidx/lifecycle/y;)V", "Landroidx/lifecycle/r;", "Lka/v1;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BaseRequestDelegate implements RequestDelegate {
    private final v1 job;
    private final r lifecycle;

    public BaseRequestDelegate(r rVar, v1 v1Var) {
        this.lifecycle = rVar;
        this.job = v1Var;
    }

    @Override // coil.request.RequestDelegate
    public final /* synthetic */ void assertActive() {
        b.a(this);
    }

    @Override // coil.request.RequestDelegate
    public void complete() {
        this.lifecycle.removeObserver(this);
    }

    @Override // coil.request.RequestDelegate
    public void dispose() {
        this.job.cancel((CancellationException) null);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(y yVar) {
        e.a(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(y owner) {
        dispose();
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

    @Override // coil.request.RequestDelegate
    public void start() {
        this.lifecycle.addObserver(this);
    }
}
