package androidx.compose.foundation;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import androidx.compose.ui.unit.IntSize;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R(\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006%"}, d2 = {"Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "Landroidx/compose/foundation/BaseAndroidExternalSurfaceState;", "Landroid/view/TextureView$SurfaceTextureListener;", "Lka/k0;", "scope", "<init>", "(Lka/k0;)V", "Landroid/graphics/SurfaceTexture;", "surfaceTexture", "", "width", "height", "Lx6/t0;", "onSurfaceTextureAvailable", "(Landroid/graphics/SurfaceTexture;II)V", "onSurfaceTextureSizeChanged", "", "onSurfaceTextureDestroyed", "(Landroid/graphics/SurfaceTexture;)Z", "surface", "onSurfaceTextureUpdated", "(Landroid/graphics/SurfaceTexture;)V", "Landroidx/compose/ui/unit/IntSize;", "surfaceSize", "J", "getSurfaceSize-YbymL2g", "()J", "setSurfaceSize-ozmzZPI", "(J)V", "Landroid/graphics/Matrix;", "matrix", "Landroid/graphics/Matrix;", "getMatrix", "()Landroid/graphics/Matrix;", "Landroid/view/Surface;", "surfaceTextureSurface", "Landroid/view/Surface;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AndroidEmbeddedExternalSurfaceState extends BaseAndroidExternalSurfaceState implements TextureView.SurfaceTextureListener {
    private final Matrix matrix;
    private long surfaceSize;
    private Surface surfaceTextureSurface;

    public AndroidEmbeddedExternalSurfaceState(k0 k0Var) {
        super(k0Var);
        this.surfaceSize = IntSize.INSTANCE.m5849getZeroYbymL2g();
        this.matrix = new Matrix();
    }

    public final Matrix getMatrix() {
        return this.matrix;
    }

    /* JADX INFO: renamed from: getSurfaceSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSurfaceSize() {
        return this.surfaceSize;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        if (!IntSize.m5842equalsimpl0(this.surfaceSize, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            width = IntSize.m5844getWidthimpl(this.surfaceSize);
            height = IntSize.m5843getHeightimpl(this.surfaceSize);
            surfaceTexture.setDefaultBufferSize(width, height);
        }
        Surface surface = new Surface(surfaceTexture);
        this.surfaceTextureSurface = surface;
        dispatchSurfaceCreated(surface, width, height);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        dispatchSurfaceDestroyed(this.surfaceTextureSurface);
        this.surfaceTextureSurface = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        if (!IntSize.m5842equalsimpl0(this.surfaceSize, IntSize.INSTANCE.m5849getZeroYbymL2g())) {
            width = IntSize.m5844getWidthimpl(this.surfaceSize);
            height = IntSize.m5843getHeightimpl(this.surfaceSize);
            surfaceTexture.setDefaultBufferSize(width, height);
        }
        dispatchSurfaceChanged(this.surfaceTextureSurface, width, height);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    /* JADX INFO: renamed from: setSurfaceSize-ozmzZPI, reason: not valid java name */
    public final void m163setSurfaceSizeozmzZPI(long j10) {
        this.surfaceSize = j10;
    }
}
