package coil.drawable;

import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DecodeUtils;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Scale;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t7.a;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001b\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u0014\u0010\u0010J\u0011\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u000eH\u0014¢\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020 2\u0006\u0010$\u001a\u00020#H\u0014¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u000eH\u0016¢\u0006\u0004\b'\u0010\u0010J\u000f\u0010(\u001a\u00020\u000eH\u0016¢\u0006\u0004\b(\u0010\u0010J\u001f\u0010,\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0001H\u0016¢\u0006\u0004\b.\u0010/J'\u00102\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010+\u001a\u00020*2\u0006\u00101\u001a\u000200H\u0016¢\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000eH\u0016¢\u0006\u0004\b5\u0010\u0013J\u0019\u00108\u001a\u00020\u000b2\b\u00107\u001a\u0004\u0018\u000106H\u0016¢\u0006\u0004\b8\u00109J\u0019\u0010<\u001a\u00020\u000b2\b\u0010;\u001a\u0004\u0018\u00010:H\u0016¢\u0006\u0004\b<\u0010=J\u0019\u0010@\u001a\u00020\u000b2\b\u0010?\u001a\u0004\u0018\u00010>H\u0017¢\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020 H\u0016¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u000bH\u0016¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u000bH\u0016¢\u0006\u0004\bF\u0010ER\u0017\u0010\u0004\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010G\u001a\u0004\bH\u0010IR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010J\u001a\u0004\bK\u0010LR\u0016\u0010N\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010OR\u0016\u0010Q\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010O¨\u0006R"}, d2 = {"Lcoil/drawable/ScaleDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/drawable/Drawable$Callback;", "Landroid/graphics/drawable/Animatable;", "child", "Lcoil/size/Scale;", "scale", "<init>", "(Landroid/graphics/drawable/Drawable;Lcoil/size/Scale;)V", "Landroid/graphics/Canvas;", "canvas", "Lx6/t0;", "draw", "(Landroid/graphics/Canvas;)V", "", "getAlpha", "()I", "alpha", "setAlpha", "(I)V", "getOpacity", "Landroid/graphics/ColorFilter;", "getColorFilter", "()Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "Landroid/graphics/Rect;", "bounds", "onBoundsChange", "(Landroid/graphics/Rect;)V", "level", "", "onLevelChange", "(I)Z", "", "state", "onStateChange", "([I)Z", "getIntrinsicWidth", "getIntrinsicHeight", "who", "Ljava/lang/Runnable;", "what", "unscheduleDrawable", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Runnable;)V", "invalidateDrawable", "(Landroid/graphics/drawable/Drawable;)V", "", "when", "scheduleDrawable", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Runnable;J)V", "tintColor", "setTint", "Landroid/content/res/ColorStateList;", "tint", "setTintList", "(Landroid/content/res/ColorStateList;)V", "Landroid/graphics/PorterDuff$Mode;", "tintMode", "setTintMode", "(Landroid/graphics/PorterDuff$Mode;)V", "Landroid/graphics/BlendMode;", "blendMode", "setTintBlendMode", "(Landroid/graphics/BlendMode;)V", "isRunning", "()Z", TtmlNode.START, "()V", "stop", "Landroid/graphics/drawable/Drawable;", "getChild", "()Landroid/graphics/drawable/Drawable;", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "", "childDx", "F", "childDy", "childScale", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ScaleDrawable extends Drawable implements Drawable.Callback, Animatable {
    private final Drawable child;
    private float childDx;
    private float childDy;
    private float childScale;
    private final Scale scale;

    /* JADX WARN: Multi-variable type inference failed */
    public ScaleDrawable(Drawable drawable) {
        this(drawable, null, 2, 0 == true ? 1 : 0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        try {
            canvas.translate(this.childDx, this.childDy);
            float f10 = this.childScale;
            canvas.scale(f10, f10);
            this.child.draw(canvas);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.child.getAlpha();
    }

    public final Drawable getChild() {
        return this.child;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.child.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.child.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.child.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    @e
    public int getOpacity() {
        return this.child.getOpacity();
    }

    public final Scale getScale() {
        return this.scale;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Object obj = this.child;
        return (obj instanceof Animatable) && ((Animatable) obj).isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        int intrinsicWidth = this.child.getIntrinsicWidth();
        int intrinsicHeight = this.child.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            this.child.setBounds(bounds);
            this.childDx = 0.0f;
            this.childDy = 0.0f;
            this.childScale = 1.0f;
            return;
        }
        int iWidth = bounds.width();
        int iHeight = bounds.height();
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(intrinsicWidth, intrinsicHeight, iWidth, iHeight, this.scale);
        double d4 = 2;
        int iL = a.L((((double) iWidth) - (((double) intrinsicWidth) * dComputeSizeMultiplier)) / d4);
        int iL2 = a.L((((double) iHeight) - (((double) intrinsicHeight) * dComputeSizeMultiplier)) / d4);
        this.child.setBounds(iL, iL2, intrinsicWidth + iL, intrinsicHeight + iL2);
        this.childDx = bounds.left;
        this.childDy = bounds.top;
        this.childScale = (float) dComputeSizeMultiplier;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int level) {
        return this.child.setLevel(level);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] state) {
        return this.child.setState(state);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.child.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.child.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int tintColor) {
        this.child.setTint(tintColor);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintBlendMode(BlendMode blendMode) {
        this.child.setTintBlendMode(blendMode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList tint) {
        this.child.setTintList(tint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode tintMode) {
        this.child.setTintMode(tintMode);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Object obj = this.child;
        if (obj instanceof Animatable) {
            ((Animatable) obj).start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Object obj = this.child;
        if (obj instanceof Animatable) {
            ((Animatable) obj).stop();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    public /* synthetic */ ScaleDrawable(Drawable drawable, Scale scale, int i10, h hVar) {
        this(drawable, (i10 & 2) != 0 ? Scale.FIT : scale);
    }

    public ScaleDrawable(Drawable drawable, Scale scale) {
        this.child = drawable;
        this.scale = scale;
        this.childScale = 1.0f;
        drawable.setCallback(this);
    }
}
