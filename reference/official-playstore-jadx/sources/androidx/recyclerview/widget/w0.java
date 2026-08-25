package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class w0 implements a2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5078b;

    public /* synthetic */ w0(Object obj, int i10) {
        this.f5077a = i10;
        this.f5078b = obj;
    }

    @Override // androidx.recyclerview.widget.a2
    public int a(View view) {
        int iB;
        int i10;
        switch (this.f5077a) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                iB = ((z0) this.f5078b).B(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).leftMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                iB = ((z0) this.f5078b).F(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).topMargin;
                break;
        }
        return iB - i10;
    }

    @Override // androidx.recyclerview.widget.a2
    public int b() {
        switch (this.f5077a) {
            case 0:
                return ((z0) this.f5078b).I();
            default:
                return ((z0) this.f5078b).K();
        }
    }

    @Override // androidx.recyclerview.widget.a2
    public int c() {
        int i10;
        int iJ;
        switch (this.f5077a) {
            case 0:
                z0 z0Var = (z0) this.f5078b;
                i10 = z0Var.f5125n;
                iJ = z0Var.J();
                break;
            default:
                z0 z0Var2 = (z0) this.f5078b;
                i10 = z0Var2.f5126o;
                iJ = z0Var2.H();
                break;
        }
        return i10 - iJ;
    }

    @Override // androidx.recyclerview.widget.a2
    public View d(int i10) {
        switch (this.f5077a) {
        }
        return ((z0) this.f5078b).v(i10);
    }

    @Override // androidx.recyclerview.widget.a2
    public int e(View view) {
        int iE;
        int i10;
        switch (this.f5077a) {
            case 0:
                a1 a1Var = (a1) view.getLayoutParams();
                iE = ((z0) this.f5078b).E(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var).rightMargin;
                break;
            default:
                a1 a1Var2 = (a1) view.getLayoutParams();
                iE = ((z0) this.f5078b).z(view);
                i10 = ((ViewGroup.MarginLayoutParams) a1Var2).bottomMargin;
                break;
        }
        return iE + i10;
    }
}
