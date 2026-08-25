package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import com.google.common.util.concurrent.r0;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends k implements d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f6632m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e f6633n = new e(this, 0);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f f6631l = new f();

    public h(Context context) {
        this.f6632m = context;
    }

    @Override // androidx.vectordrawable.graphics.drawable.k, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return j0.a.b(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        f fVar = this.f6631l;
        fVar.f6626a.draw(canvas);
        if (fVar.f6627b.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getAlpha() : this.f6631l.f6626a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        int changingConfigurations = super.getChangingConfigurations();
        this.f6631l.getClass();
        return changingConfigurations;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f6636i;
        return drawable != null ? j0.a.c(drawable) : this.f6631l.f6626a.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f6636i == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new g(this.f6636i.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f6631l.f6626a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f6631l.f6626a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.getOpacity() : this.f6631l.f6626a.getOpacity();
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x0182, code lost:
    
        if (r8.f6627b != null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0184, code lost:
    
        r8.f6627b = new android.animation.AnimatorSet();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x018b, code lost:
    
        r8.f6627b.playTogether(r8.f6628c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0192, code lost:
    
        return;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void inflate(android.content.res.Resources r22, org.xmlpull.v1.XmlPullParser r23, android.util.AttributeSet r24, android.content.res.Resources.Theme r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.h.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.isAutoMirrored() : this.f6631l.f6626a.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        Drawable drawable = this.f6636i;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f6631l.f6627b.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.isStateful() : this.f6631l.f6626a.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f6631l.f6626a.setBounds(rect);
        }
    }

    @Override // androidx.vectordrawable.graphics.drawable.k, android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i10) {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.setLevel(i10) : this.f6631l.f6626a.setLevel(i10);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f6636i;
        return drawable != null ? drawable.setState(iArr) : this.f6631l.f6626a.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i10) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setAlpha(i10);
        } else {
            this.f6631l.f6626a.setAlpha(i10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.f6631l.f6626a.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f6631l.f6626a.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i10) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            r0.E(drawable, i10);
        } else {
            this.f6631l.f6626a.setTint(i10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.h(drawable, colorStateList);
        } else {
            this.f6631l.f6626a.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            j0.a.i(drawable, mode);
        } else {
            this.f6631l.f6626a.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z5) {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            return drawable.setVisible(z, z5);
        }
        this.f6631l.f6626a.setVisible(z, z5);
        return super.setVisible(z, z5);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
            return;
        }
        f fVar = this.f6631l;
        if (fVar.f6627b.isStarted()) {
            return;
        }
        fVar.f6627b.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        Drawable drawable = this.f6636i;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f6631l.f6627b.end();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws Throwable {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
