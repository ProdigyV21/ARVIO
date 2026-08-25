package androidx.vectordrawable.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable.ConstantState f6686a;

    public s(Drawable.ConstantState constantState) {
        this.f6686a = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        return this.f6686a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.f6686a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        t tVar = new t();
        tVar.f6636i = (VectorDrawable) this.f6686a.newDrawable();
        return tVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        t tVar = new t();
        tVar.f6636i = (VectorDrawable) this.f6686a.newDrawable(resources);
        return tVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        t tVar = new t();
        tVar.f6636i = (VectorDrawable) this.f6686a.newDrawable(resources, theme);
        return tVar;
    }
}
