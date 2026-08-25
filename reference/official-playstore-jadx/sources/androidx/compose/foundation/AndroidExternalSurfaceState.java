package androidx.compose.foundation;

import android.graphics.Rect;
import android.view.SurfaceHolder;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0010\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0012\u0010\u000bR\"\u0010\u0013\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/AndroidExternalSurfaceState;", "Landroidx/compose/foundation/BaseAndroidExternalSurfaceState;", "Landroid/view/SurfaceHolder$Callback;", "Lka/k0;", "scope", "<init>", "(Lka/k0;)V", "Landroid/view/SurfaceHolder;", "holder", "Lx6/t0;", "surfaceCreated", "(Landroid/view/SurfaceHolder;)V", "", "format", "width", "height", "surfaceChanged", "(Landroid/view/SurfaceHolder;III)V", "surfaceDestroyed", "lastWidth", "I", "getLastWidth", "()I", "setLastWidth", "(I)V", "lastHeight", "getLastHeight", "setLastHeight", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AndroidExternalSurfaceState extends BaseAndroidExternalSurfaceState implements SurfaceHolder.Callback {
    private int lastHeight;
    private int lastWidth;

    public AndroidExternalSurfaceState(k0 k0Var) {
        super(k0Var);
        this.lastWidth = -1;
        this.lastHeight = -1;
    }

    public final int getLastHeight() {
        return this.lastHeight;
    }

    public final int getLastWidth() {
        return this.lastWidth;
    }

    public final void setLastHeight(int i10) {
        this.lastHeight = i10;
    }

    public final void setLastWidth(int i10) {
        this.lastWidth = i10;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (this.lastWidth == width && this.lastHeight == height) {
            return;
        }
        this.lastWidth = width;
        this.lastHeight = height;
        dispatchSurfaceChanged(holder.getSurface(), width, height);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
        Rect surfaceFrame = holder.getSurfaceFrame();
        this.lastWidth = surfaceFrame.width();
        this.lastHeight = surfaceFrame.height();
        dispatchSurfaceCreated(holder.getSurface(), this.lastWidth, this.lastHeight);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
        dispatchSurfaceDestroyed(holder.getSurface());
    }
}
