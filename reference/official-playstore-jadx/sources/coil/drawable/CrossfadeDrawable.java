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
import android.os.SystemClock;
import android.view.Scale;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.vectordrawable.graphics.drawable.c;
import androidx.vectordrawable.graphics.drawable.d;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t7.a;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010!\n\u0002\b\u000e\u0018\u0000 p2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001pBE\b\u0007\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\bH\u0017¢\u0006\u0004\b\u0019\u0010\u0015J\u0011\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001e\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00112\u0006\u0010!\u001a\u00020 H\u0014¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\n2\u0006\u0010$\u001a\u00020\bH\u0014¢\u0006\u0004\b%\u0010&J\u0017\u0010)\u001a\u00020\n2\u0006\u0010(\u001a\u00020'H\u0014¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\bH\u0016¢\u0006\u0004\b+\u0010\u0015J\u000f\u0010,\u001a\u00020\bH\u0016¢\u0006\u0004\b,\u0010\u0015J\u001f\u00100\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u00012\u0006\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0001H\u0016¢\u0006\u0004\b2\u00103J'\u00106\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u00012\u0006\u0010/\u001a\u00020.2\u0006\u00105\u001a\u000204H\u0016¢\u0006\u0004\b6\u00107J\u0017\u00109\u001a\u00020\u00112\u0006\u00108\u001a\u00020\bH\u0016¢\u0006\u0004\b9\u0010\u0018J\u0019\u0010<\u001a\u00020\u00112\b\u0010;\u001a\u0004\u0018\u00010:H\u0016¢\u0006\u0004\b<\u0010=J\u0019\u0010@\u001a\u00020\u00112\b\u0010?\u001a\u0004\u0018\u00010>H\u0016¢\u0006\u0004\b@\u0010AJ\u0019\u0010D\u001a\u00020\u00112\b\u0010C\u001a\u0004\u0018\u00010BH\u0017¢\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\nH\u0016¢\u0006\u0004\bF\u0010GJ\u000f\u0010\u0004\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0004\u0010HJ\u000f\u0010I\u001a\u00020\u0011H\u0016¢\u0006\u0004\bI\u0010HJ\u0017\u0010L\u001a\u00020\u00112\u0006\u0010K\u001a\u00020JH\u0016¢\u0006\u0004\bL\u0010MJ\u0017\u0010N\u001a\u00020\n2\u0006\u0010K\u001a\u00020JH\u0016¢\u0006\u0004\bN\u0010OJ\u000f\u0010P\u001a\u00020\u0011H\u0016¢\u0006\u0004\bP\u0010HJ\u001f\u0010U\u001a\u00020\u00112\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010R\u001a\u00020 H\u0001¢\u0006\u0004\bS\u0010TJ#\u0010X\u001a\u00020\b2\b\u0010V\u001a\u0004\u0018\u00010\b2\b\u0010W\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bX\u0010YJ\u000f\u0010Z\u001a\u00020\u0011H\u0002¢\u0006\u0004\bZ\u0010HR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010[\u001a\u0004\b\\\u0010]R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010^\u001a\u0004\b_\u0010\u0015R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010`\u001a\u0004\ba\u0010GR\u0017\u0010\f\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\f\u0010`\u001a\u0004\bb\u0010GR\u001a\u0010d\u001a\b\u0012\u0004\u0012\u00020J0c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010^R\u0014\u0010g\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010^R\u0016\u0010h\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010j\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010^R\u0016\u0010(\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010^R(\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\u0010k\u001a\u0004\u0018\u00010\u00018\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0004\u0010l\u001a\u0004\bm\u0010nR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00018\u0006¢\u0006\f\n\u0004\b\u0005\u0010l\u001a\u0004\bo\u0010n¨\u0006q"}, d2 = {"Lcoil/drawable/CrossfadeDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/drawable/Drawable$Callback;", "Landroidx/vectordrawable/graphics/drawable/d;", TtmlNode.START, TtmlNode.END, "Lcoil/size/Scale;", "scale", "", "durationMillis", "", "fadeStart", "preferExactIntrinsicSize", "<init>", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Lcoil/size/Scale;IZZ)V", "Landroid/graphics/Canvas;", "canvas", "Lx6/t0;", "draw", "(Landroid/graphics/Canvas;)V", "getAlpha", "()I", "alpha", "setAlpha", "(I)V", "getOpacity", "Landroid/graphics/ColorFilter;", "getColorFilter", "()Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "Landroid/graphics/Rect;", "bounds", "onBoundsChange", "(Landroid/graphics/Rect;)V", "level", "onLevelChange", "(I)Z", "", "state", "onStateChange", "([I)Z", "getIntrinsicWidth", "getIntrinsicHeight", "who", "Ljava/lang/Runnable;", "what", "unscheduleDrawable", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Runnable;)V", "invalidateDrawable", "(Landroid/graphics/drawable/Drawable;)V", "", "when", "scheduleDrawable", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Runnable;J)V", "tintColor", "setTint", "Landroid/content/res/ColorStateList;", "tint", "setTintList", "(Landroid/content/res/ColorStateList;)V", "Landroid/graphics/PorterDuff$Mode;", "tintMode", "setTintMode", "(Landroid/graphics/PorterDuff$Mode;)V", "Landroid/graphics/BlendMode;", "blendMode", "setTintBlendMode", "(Landroid/graphics/BlendMode;)V", "isRunning", "()Z", "()V", "stop", "Landroidx/vectordrawable/graphics/drawable/c;", "callback", "registerAnimationCallback", "(Landroidx/vectordrawable/graphics/drawable/c;)V", "unregisterAnimationCallback", "(Landroidx/vectordrawable/graphics/drawable/c;)Z", "clearAnimationCallbacks", "drawable", "targetBounds", "updateBounds$coil_base_release", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/Rect;)V", "updateBounds", "startSize", "endSize", "computeIntrinsicDimension", "(Ljava/lang/Integer;Ljava/lang/Integer;)I", "markDone", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "I", "getDurationMillis", "Z", "getFadeStart", "getPreferExactIntrinsicSize", "", "callbacks", "Ljava/util/List;", "intrinsicWidth", "intrinsicHeight", "startTimeMillis", "J", "maxAlpha", "<set-?>", "Landroid/graphics/drawable/Drawable;", "getStart", "()Landroid/graphics/drawable/Drawable;", "getEnd", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CrossfadeDrawable extends Drawable implements Drawable.Callback, d {
    public static final int DEFAULT_DURATION = 100;
    private static final int STATE_DONE = 2;
    private static final int STATE_RUNNING = 1;
    private static final int STATE_START = 0;
    private final List<c> callbacks;
    private final int durationMillis;
    private final Drawable end;
    private final boolean fadeStart;
    private final int intrinsicHeight;
    private final int intrinsicWidth;
    private int maxAlpha;
    private final boolean preferExactIntrinsicSize;
    private final Scale scale;
    private Drawable start;
    private long startTimeMillis;
    private int state;

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2) {
        this(drawable, drawable2, null, 0, false, false, 60, null);
    }

    private final int computeIntrinsicDimension(Integer startSize, Integer endSize) {
        if (this.preferExactIntrinsicSize || ((startSize == null || startSize.intValue() != -1) && (endSize == null || endSize.intValue() != -1))) {
            return Math.max(startSize != null ? startSize.intValue() : -1, endSize != null ? endSize.intValue() : -1);
        }
        return -1;
    }

    private final void markDone() {
        this.state = 2;
        this.start = null;
        List<c> list = this.callbacks;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).onAnimationEnd(this);
        }
    }

    public void clearAnimationCallbacks() {
        this.callbacks.clear();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iSave;
        Drawable drawable;
        int i10 = this.state;
        if (i10 == 0) {
            Drawable drawable2 = this.start;
            if (drawable2 != null) {
                drawable2.setAlpha(this.maxAlpha);
                iSave = canvas.save();
                try {
                    drawable2.draw(canvas);
                    return;
                } finally {
                }
            }
            return;
        }
        if (i10 == 2) {
            Drawable drawable3 = this.end;
            if (drawable3 != null) {
                drawable3.setAlpha(this.maxAlpha);
                iSave = canvas.save();
                try {
                    drawable3.draw(canvas);
                    return;
                } finally {
                }
            }
            return;
        }
        double dUptimeMillis = (SystemClock.uptimeMillis() - this.startTimeMillis) / ((double) this.durationMillis);
        double dL = qb.d.l(dUptimeMillis, 0.0d, 1.0d);
        int i11 = this.maxAlpha;
        int i12 = (int) (dL * ((double) i11));
        if (this.fadeStart) {
            i11 -= i12;
        }
        boolean z = dUptimeMillis >= 1.0d;
        if (!z && (drawable = this.start) != null) {
            drawable.setAlpha(i11);
            iSave = canvas.save();
            try {
                drawable.draw(canvas);
            } finally {
            }
        }
        Drawable drawable4 = this.end;
        if (drawable4 != null) {
            drawable4.setAlpha(i12);
            iSave = canvas.save();
            try {
                drawable4.draw(canvas);
            } finally {
            }
        }
        if (z) {
            markDone();
        } else {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.maxAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        ColorFilter colorFilter;
        Drawable drawable;
        int i10 = this.state;
        if (i10 == 0) {
            Drawable drawable2 = this.start;
            if (drawable2 != null) {
                return drawable2.getColorFilter();
            }
            return null;
        }
        if (i10 != 1) {
            if (i10 == 2 && (drawable = this.end) != null) {
                return drawable.getColorFilter();
            }
            return null;
        }
        Drawable drawable3 = this.end;
        if (drawable3 != null && (colorFilter = drawable3.getColorFilter()) != null) {
            return colorFilter;
        }
        Drawable drawable4 = this.start;
        if (drawable4 != null) {
            return drawable4.getColorFilter();
        }
        return null;
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public final Drawable getEnd() {
        return this.end;
    }

    public final boolean getFadeStart() {
        return this.fadeStart;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.intrinsicHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.intrinsicWidth;
    }

    @Override // android.graphics.drawable.Drawable
    @e
    public int getOpacity() {
        Drawable drawable = this.start;
        Drawable drawable2 = this.end;
        int i10 = this.state;
        if (i10 == 0) {
            if (drawable != null) {
                return drawable.getOpacity();
            }
            return -2;
        }
        if (i10 == 2) {
            if (drawable2 != null) {
                return drawable2.getOpacity();
            }
            return -2;
        }
        if (drawable != null && drawable2 != null) {
            return Drawable.resolveOpacity(drawable.getOpacity(), drawable2.getOpacity());
        }
        if (drawable != null) {
            return drawable.getOpacity();
        }
        if (drawable2 != null) {
            return drawable2.getOpacity();
        }
        return -2;
    }

    public final boolean getPreferExactIntrinsicSize() {
        return this.preferExactIntrinsicSize;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final Drawable getStart() {
        return this.start;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.state == 1;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        Drawable drawable = this.start;
        if (drawable != null) {
            updateBounds$coil_base_release(drawable, bounds);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            updateBounds$coil_base_release(drawable2, bounds);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int level) {
        Drawable drawable = this.start;
        boolean level2 = drawable != null ? drawable.setLevel(level) : false;
        Drawable drawable2 = this.end;
        return level2 || (drawable2 != null ? drawable2.setLevel(level) : false);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] state) {
        Drawable drawable = this.start;
        boolean state2 = drawable != null ? drawable.setState(state) : false;
        Drawable drawable2 = this.end;
        return state2 || (drawable2 != null ? drawable2.setState(state) : false);
    }

    public void registerAnimationCallback(c callback) {
        this.callbacks.add(callback);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (alpha < 0 || alpha >= 256) {
            throw new IllegalArgumentException(a0.c.i(alpha, "Invalid alpha: ").toString());
        }
        this.maxAlpha = alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        Drawable drawable2 = this.end;
        if (drawable2 == null) {
            return;
        }
        drawable2.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int tintColor) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTint(tintColor);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTint(tintColor);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintBlendMode(BlendMode blendMode) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintBlendMode(blendMode);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintBlendMode(blendMode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList tint) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintList(tint);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintList(tint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode tintMode) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintMode(tintMode);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintMode(tintMode);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Object obj = this.start;
        Animatable animatable = obj instanceof Animatable ? (Animatable) obj : null;
        if (animatable != null) {
            animatable.start();
        }
        Object obj2 = this.end;
        Animatable animatable2 = obj2 instanceof Animatable ? (Animatable) obj2 : null;
        if (animatable2 != null) {
            animatable2.start();
        }
        if (this.state != 0) {
            return;
        }
        this.state = 1;
        this.startTimeMillis = SystemClock.uptimeMillis();
        List<c> list = this.callbacks;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).onAnimationStart(this);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Object obj = this.start;
        Animatable animatable = obj instanceof Animatable ? (Animatable) obj : null;
        if (animatable != null) {
            animatable.stop();
        }
        Object obj2 = this.end;
        Animatable animatable2 = obj2 instanceof Animatable ? (Animatable) obj2 : null;
        if (animatable2 != null) {
            animatable2.stop();
        }
        if (this.state != 2) {
            markDone();
        }
    }

    public boolean unregisterAnimationCallback(c callback) {
        return this.callbacks.remove(callback);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    public final void updateBounds$coil_base_release(Drawable drawable, Rect targetBounds) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            drawable.setBounds(targetBounds);
            return;
        }
        int iWidth = targetBounds.width();
        int iHeight = targetBounds.height();
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(intrinsicWidth, intrinsicHeight, iWidth, iHeight, this.scale);
        double d4 = 2;
        int iL = a.L((((double) iWidth) - (((double) intrinsicWidth) * dComputeSizeMultiplier)) / d4);
        int iL2 = a.L((((double) iHeight) - (dComputeSizeMultiplier * ((double) intrinsicHeight))) / d4);
        drawable.setBounds(targetBounds.left + iL, targetBounds.top + iL2, targetBounds.right - iL, targetBounds.bottom - iL2);
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale) {
        this(drawable, drawable2, scale, 0, false, false, 56, null);
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale, int i10) {
        this(drawable, drawable2, scale, i10, false, false, 48, null);
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale, int i10, boolean z) {
        this(drawable, drawable2, scale, i10, z, false, 32, null);
    }

    public /* synthetic */ CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale, int i10, boolean z, boolean z5, int i11, h hVar) {
        this(drawable, drawable2, (i11 & 4) != 0 ? Scale.FIT : scale, (i11 & 8) != 0 ? 100 : i10, (i11 & 16) != 0 ? true : z, (i11 & 32) != 0 ? false : z5);
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale, int i10, boolean z, boolean z5) {
        this.scale = scale;
        this.durationMillis = i10;
        this.fadeStart = z;
        this.preferExactIntrinsicSize = z5;
        this.callbacks = new ArrayList();
        this.intrinsicWidth = computeIntrinsicDimension(drawable != null ? Integer.valueOf(drawable.getIntrinsicWidth()) : null, drawable2 != null ? Integer.valueOf(drawable2.getIntrinsicWidth()) : null);
        this.intrinsicHeight = computeIntrinsicDimension(drawable != null ? Integer.valueOf(drawable.getIntrinsicHeight()) : null, drawable2 != null ? Integer.valueOf(drawable2.getIntrinsicHeight()) : null);
        this.maxAlpha = 255;
        this.start = drawable != null ? drawable.mutate() : null;
        Drawable drawableMutate = drawable2 != null ? drawable2.mutate() : null;
        this.end = drawableMutate;
        if (i10 > 0) {
            Drawable drawable3 = this.start;
            if (drawable3 != null) {
                drawable3.setCallback(this);
            }
            if (drawableMutate == null) {
                return;
            }
            drawableMutate.setCallback(this);
            return;
        }
        throw new IllegalArgumentException("durationMillis must be > 0.");
    }
}
