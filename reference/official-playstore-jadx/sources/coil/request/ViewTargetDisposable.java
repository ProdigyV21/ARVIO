package coil.request;

import android.content.Utils;
import android.view.View;
import ka.p0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\fR(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcoil/request/ViewTargetDisposable;", "Lcoil/request/Disposable;", "Landroid/view/View;", "view", "Lka/p0;", "Lcoil/request/ImageResult;", "job", "<init>", "(Landroid/view/View;Lka/p0;)V", "Lx6/t0;", "dispose", "()V", "Landroid/view/View;", "Lka/p0;", "getJob", "()Lka/p0;", "setJob", "(Lka/p0;)V", "", "isDisposed", "()Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ViewTargetDisposable implements Disposable {
    private volatile p0<? extends ImageResult> job;
    private final View view;

    public ViewTargetDisposable(View view, p0<? extends ImageResult> p0Var) {
        this.view = view;
        this.job = p0Var;
    }

    @Override // coil.request.Disposable
    public void dispose() {
        if (isDisposed()) {
            return;
        }
        Utils.getRequestManager(this.view).dispose();
    }

    @Override // coil.request.Disposable
    public p0<ImageResult> getJob() {
        return this.job;
    }

    @Override // coil.request.Disposable
    public boolean isDisposed() {
        return Utils.getRequestManager(this.view).isDisposed(this);
    }

    public void setJob(p0<? extends ImageResult> p0Var) {
        this.job = p0Var;
    }
}
