package androidx.vectordrawable.graphics.drawable;

import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t f6626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AnimatorSet f6627b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f6628c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t.e f6629d;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
