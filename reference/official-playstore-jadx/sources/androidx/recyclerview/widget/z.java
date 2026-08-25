package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.emoji2.text.h f5108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5110c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5111d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5112e;

    public z() {
        d();
    }

    public final void a() {
        this.f5110c = this.f5111d ? this.f5108a.g() : this.f5108a.k();
    }

    public final void b(int i10, View view) {
        if (this.f5111d) {
            int iB = this.f5108a.b(view);
            androidx.emoji2.text.h hVar = this.f5108a;
            this.f5110c = (Integer.MIN_VALUE == hVar.f2612a ? 0 : hVar.l() - hVar.f2612a) + iB;
        } else {
            this.f5110c = this.f5108a.e(view);
        }
        this.f5109b = i10;
    }

    public final void c(int i10, View view) {
        androidx.emoji2.text.h hVar = this.f5108a;
        int iL = Integer.MIN_VALUE == hVar.f2612a ? 0 : hVar.l() - hVar.f2612a;
        if (iL >= 0) {
            b(i10, view);
            return;
        }
        this.f5109b = i10;
        if (!this.f5111d) {
            int iE = this.f5108a.e(view);
            int iK = iE - this.f5108a.k();
            this.f5110c = iE;
            if (iK > 0) {
                int iG = (this.f5108a.g() - Math.min(0, (this.f5108a.g() - iL) - this.f5108a.b(view))) - (this.f5108a.c(view) + iE);
                if (iG < 0) {
                    this.f5110c -= Math.min(iK, -iG);
                    return;
                }
                return;
            }
            return;
        }
        int iG2 = (this.f5108a.g() - iL) - this.f5108a.b(view);
        this.f5110c = this.f5108a.g() - iG2;
        if (iG2 > 0) {
            int iC = this.f5110c - this.f5108a.c(view);
            int iK2 = this.f5108a.k();
            int iMin = iC - (Math.min(this.f5108a.e(view) - iK2, 0) + iK2);
            if (iMin < 0) {
                this.f5110c = Math.min(iG2, -iMin) + this.f5110c;
            }
        }
    }

    public final void d() {
        this.f5109b = -1;
        this.f5110c = Integer.MIN_VALUE;
        this.f5111d = false;
        this.f5112e = false;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AnchorInfo{mPosition=");
        sb2.append(this.f5109b);
        sb2.append(", mCoordinate=");
        sb2.append(this.f5110c);
        sb2.append(", mLayoutFromEnd=");
        sb2.append(this.f5111d);
        sb2.append(", mValid=");
        return a0.c.r(sb2, this.f5112e, '}');
    }
}
