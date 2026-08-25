package androidx.emoji2.text;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.z0;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2614c;

    public h(z0 z0Var) {
        this.f2612a = Integer.MIN_VALUE;
        this.f2614c = new Rect();
        this.f2613b = z0Var;
    }

    public static h a(z0 z0Var, int i10) {
        if (i10 == 0) {
            return new androidx.recyclerview.widget.g0(z0Var, 0);
        }
        if (i10 == 1) {
            return new androidx.recyclerview.widget.g0(z0Var, 1);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m(View view);

    public abstract int n(View view);

    public abstract void o(int i10);

    public h(l lVar) {
        this.f2612a = 0;
        this.f2614c = new d();
        this.f2613b = lVar;
    }
}
