package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Size;
import com.google.accompanist.drawablepainter.DrawablePainter;
import com.google.accompanist.drawablepainter.DrawablePainterKt;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements Drawable.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6624i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f6625l;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        switch (this.f6624i) {
            case 0:
                ((h) this.f6625l).invalidateSelf();
                break;
            case 1:
                break;
            default:
                DrawablePainter drawablePainter = (DrawablePainter) this.f6625l;
                MutableState mutableState = drawablePainter.f12558l;
                mutableState.setValue(Integer.valueOf(((Number) mutableState.getValue()).intValue() + 1));
                drawablePainter.f12559m.setValue(Size.m3293boximpl(DrawablePainterKt.a(drawablePainter.f12557i)));
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, x6.s] */
    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j10) {
        switch (this.f6624i) {
            case 0:
                ((h) this.f6625l).scheduleSelf(runnable, j10);
                break;
            case 1:
                Drawable.Callback callback = (Drawable.Callback) this.f6625l;
                if (callback != null) {
                    callback.scheduleDrawable(drawable, runnable, j10);
                }
                break;
            default:
                ((Handler) DrawablePainterKt.f12561a.getValue()).postAtTime(runnable, j10);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, x6.s] */
    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        switch (this.f6624i) {
            case 0:
                ((h) this.f6625l).unscheduleSelf(runnable);
                break;
            case 1:
                Drawable.Callback callback = (Drawable.Callback) this.f6625l;
                if (callback != null) {
                    callback.unscheduleDrawable(drawable, runnable);
                }
                break;
            default:
                ((Handler) DrawablePainterKt.f12561a.getValue()).removeCallbacks(runnable);
                break;
        }
    }

    public /* synthetic */ e(Object obj, int i10) {
        this.f6624i = i10;
        this.f6625l = obj;
    }

    private final void a(Drawable drawable) {
    }
}
