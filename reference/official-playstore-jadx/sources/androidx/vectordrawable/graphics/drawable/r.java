package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q f6676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f6677c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f6678d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6679e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bitmap f6680f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f6681g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f6682h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6683i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6684j;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f6685l;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.f6675a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new t(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new t(this);
    }
}
