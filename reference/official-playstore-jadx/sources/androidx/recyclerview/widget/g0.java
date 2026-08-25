package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 extends androidx.emoji2.text.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4930d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g0(z0 z0Var, int i10) {
        super(z0Var);
        this.f4930d = i10;
    }

    @Override // androidx.emoji2.text.h
    public final int b(View view) {
        int iE;
        int i10;
        switch (this.f4930d) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                iE = ((z0) this.f2613b).E(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).rightMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                iE = ((z0) this.f2613b).z(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).bottomMargin;
                break;
        }
        return iE + i10;
    }

    @Override // androidx.emoji2.text.h
    public final int c(View view) {
        int iD;
        int i10;
        switch (this.f4930d) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                ((z0) this.f2613b).getClass();
                iD = z0.D(view) + ((ViewGroup.MarginLayoutParams) a1Var).leftMargin;
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).rightMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                ((z0) this.f2613b).getClass();
                iD = z0.C(view) + ((ViewGroup.MarginLayoutParams) a1Var2).topMargin;
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).bottomMargin;
                break;
        }
        return iD + i10;
    }

    @Override // androidx.emoji2.text.h
    public final int d(View view) {
        int iC;
        int i10;
        switch (this.f4930d) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                ((z0) this.f2613b).getClass();
                iC = z0.C(view) + ((ViewGroup.MarginLayoutParams) a1Var).topMargin;
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).bottomMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                ((z0) this.f2613b).getClass();
                iC = z0.D(view) + ((ViewGroup.MarginLayoutParams) a1Var2).leftMargin;
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).rightMargin;
                break;
        }
        return iC + i10;
    }

    @Override // androidx.emoji2.text.h
    public final int e(View view) {
        int iB;
        int i10;
        switch (this.f4930d) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                iB = ((z0) this.f2613b).B(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).leftMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                iB = ((z0) this.f2613b).F(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).topMargin;
                break;
        }
        return iB - i10;
    }

    @Override // androidx.emoji2.text.h
    public final int f() {
        switch (this.f4930d) {
            case 0:
                return ((z0) this.f2613b).f5125n;
            default:
                return ((z0) this.f2613b).f5126o;
        }
    }

    @Override // androidx.emoji2.text.h
    public final int g() {
        int i10;
        int iJ;
        switch (this.f4930d) {
            case 0:
                z0 z0Var = (z0) this.f2613b;
                i10 = z0Var.f5125n;
                iJ = z0Var.J();
                break;
            default:
                z0 z0Var2 = (z0) this.f2613b;
                i10 = z0Var2.f5126o;
                iJ = z0Var2.H();
                break;
        }
        return i10 - iJ;
    }

    @Override // androidx.emoji2.text.h
    public final int h() {
        switch (this.f4930d) {
            case 0:
                return ((z0) this.f2613b).J();
            default:
                return ((z0) this.f2613b).H();
        }
    }

    @Override // androidx.emoji2.text.h
    public final int i() {
        switch (this.f4930d) {
            case 0:
                return ((z0) this.f2613b).f5123l;
            default:
                return ((z0) this.f2613b).f5124m;
        }
    }

    @Override // androidx.emoji2.text.h
    public final int j() {
        switch (this.f4930d) {
            case 0:
                return ((z0) this.f2613b).f5124m;
            default:
                return ((z0) this.f2613b).f5123l;
        }
    }

    @Override // androidx.emoji2.text.h
    public final int k() {
        switch (this.f4930d) {
            case 0:
                return ((z0) this.f2613b).I();
            default:
                return ((z0) this.f2613b).K();
        }
    }

    @Override // androidx.emoji2.text.h
    public final int l() {
        int I;
        int iJ;
        switch (this.f4930d) {
            case 0:
                z0 z0Var = (z0) this.f2613b;
                I = z0Var.f5125n - z0Var.I();
                iJ = z0Var.J();
                break;
            default:
                z0 z0Var2 = (z0) this.f2613b;
                I = z0Var2.f5126o - z0Var2.K();
                iJ = z0Var2.H();
                break;
        }
        return I - iJ;
    }

    @Override // androidx.emoji2.text.h
    public final int m(View view) {
        switch (this.f4930d) {
            case 0:
                z0 z0Var = (z0) this.f2613b;
                Rect rect = (Rect) this.f2614c;
                z0Var.O(view, rect);
                return rect.right;
            default:
                z0 z0Var2 = (z0) this.f2613b;
                Rect rect2 = (Rect) this.f2614c;
                z0Var2.O(view, rect2);
                return rect2.bottom;
        }
    }

    @Override // androidx.emoji2.text.h
    public final int n(View view) {
        switch (this.f4930d) {
            case 0:
                z0 z0Var = (z0) this.f2613b;
                Rect rect = (Rect) this.f2614c;
                z0Var.O(view, rect);
                return rect.left;
            default:
                z0 z0Var2 = (z0) this.f2613b;
                Rect rect2 = (Rect) this.f2614c;
                z0Var2.O(view, rect2);
                return rect2.top;
        }
    }

    @Override // androidx.emoji2.text.h
    public final void o(int i10) {
        switch (this.f4930d) {
            case 0:
                ((z0) this.f2613b).T(i10);
                break;
            default:
                ((z0) this.f2613b).U(i10);
                break;
        }
    }
}
