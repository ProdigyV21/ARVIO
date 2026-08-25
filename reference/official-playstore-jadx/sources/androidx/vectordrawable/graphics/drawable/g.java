package androidx.vectordrawable.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable.ConstantState f6630a;

    public g(Drawable.ConstantState constantState) {
        this.f6630a = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        return this.f6630a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f6630a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        h hVar = new h(null);
        Drawable drawableNewDrawable = this.f6630a.newDrawable();
        hVar.f6636i = drawableNewDrawable;
        drawableNewDrawable.setCallback(hVar.f6633n);
        return hVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        h hVar = new h(null);
        Drawable drawableNewDrawable = this.f6630a.newDrawable(resources);
        hVar.f6636i = drawableNewDrawable;
        drawableNewDrawable.setCallback(hVar.f6633n);
        return hVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        h hVar = new h(null);
        Drawable drawableNewDrawable = this.f6630a.newDrawable(resources, theme);
        hVar.f6636i = drawableNewDrawable;
        drawableNewDrawable.setCallback(hVar.f6633n);
        return hVar;
    }
}
