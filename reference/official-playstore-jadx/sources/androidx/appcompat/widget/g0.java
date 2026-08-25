package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends ImageView implements androidx.core.view.y0, u0.v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1573i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f0 f1574l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1575m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        f4.a(context);
        this.f1575m = false;
        d4.a(getContext(), this);
        u uVar = new u(this);
        this.f1573i = uVar;
        uVar.l(attributeSet, i10);
        f0 f0Var = new f0(this);
        this.f1574l = f0Var;
        f0Var.e(attributeSet, i10);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1573i;
        if (uVar != null) {
            uVar.i();
        }
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            f0Var.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1573i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1573i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        g4 g4Var;
        f0 f0Var = this.f1574l;
        if (f0Var == null || (g4Var = (g4) f0Var.f1568n) == null) {
            return null;
        }
        return (ColorStateList) g4Var.f1591c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        g4 g4Var;
        f0 f0Var = this.f1574l;
        if (f0Var == null || (g4Var = (g4) f0Var.f1568n) == null) {
            return null;
        }
        return (PorterDuff.Mode) g4Var.f1592d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return !(((ImageView) this.f1574l.f1567m).getBackground() instanceof RippleDrawable) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1573i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1573i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            f0Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        f0 f0Var = this.f1574l;
        if (f0Var != null && drawable != null && !this.f1575m) {
            f0Var.f1566l = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (f0Var != null) {
            f0Var.a();
            if (this.f1575m) {
                return;
            }
            ImageView imageView = (ImageView) f0Var.f1567m;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(f0Var.f1566l);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i10) {
        super.setImageLevel(i10);
        this.f1575m = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i10) {
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            ImageView imageView = (ImageView) f0Var.f1567m;
            if (i10 != 0) {
                Drawable drawableO = m2.f0.o(imageView.getContext(), i10);
                if (drawableO != null) {
                    z1.a(drawableO);
                }
                imageView.setImageDrawable(drawableO);
            } else {
                imageView.setImageDrawable(null);
            }
            f0Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            f0Var.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1573i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1573i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            if (((g4) f0Var.f1568n) == null) {
                f0Var.f1568n = new g4();
            }
            g4 g4Var = (g4) f0Var.f1568n;
            g4Var.f1591c = colorStateList;
            g4Var.f1590b = true;
            f0Var.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        f0 f0Var = this.f1574l;
        if (f0Var != null) {
            if (((g4) f0Var.f1568n) == null) {
                f0Var.f1568n = new g4();
            }
            g4 g4Var = (g4) f0Var.f1568n;
            g4Var.f1592d = mode;
            g4Var.f1589a = true;
            f0Var.a();
        }
    }
}
