package androidx.core.view;

import android.os.Build;
import android.view.View;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2239l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2241n;

    public h1() {
        if (a1.a.f80i == null) {
            a1.a.f80i = new a1.a();
        }
    }

    public int a(int i10) {
        if (i10 < this.f2240m) {
            return ((ByteBuffer) this.f2241n).getShort(this.f2239l + i10);
        }
        return 0;
    }

    public void b() {
        if (((z6.f) this.f2241n).f23231r != this.f2240m) {
            throw new ConcurrentModificationException();
        }
    }

    public abstract Object c(View view);

    public abstract void d(View view, Object obj);

    public void e() {
        while (true) {
            int i10 = this.f2238i;
            z6.f fVar = (z6.f) this.f2241n;
            if (i10 >= fVar.f23229p || fVar.f23226m[i10] >= 0) {
                return;
            } else {
                this.f2238i = i10 + 1;
            }
        }
    }

    public void f(View view, Object obj) {
        Object tag;
        if (Build.VERSION.SDK_INT >= this.f2239l) {
            d(view, obj);
            return;
        }
        if (Build.VERSION.SDK_INT >= this.f2239l) {
            tag = c(view);
        } else {
            tag = view.getTag(this.f2238i);
            if (!((Class) this.f2241n).isInstance(tag)) {
                tag = null;
            }
        }
        if (g(tag, obj)) {
            View.AccessibilityDelegate accessibilityDelegateC = b2.c(view);
            b bVar = accessibilityDelegateC == null ? null : accessibilityDelegateC instanceof a ? ((a) accessibilityDelegateC).f2192a : new b(accessibilityDelegateC);
            if (bVar == null) {
                bVar = new b();
            }
            b2.h(view, bVar);
            view.setTag(this.f2238i, obj);
            b2.e(this.f2240m, view);
        }
    }

    public abstract boolean g(Object obj, Object obj2);

    public boolean hasNext() {
        return this.f2238i < ((z6.f) this.f2241n).f23229p;
    }

    public void remove() {
        z6.f fVar = (z6.f) this.f2241n;
        b();
        if (this.f2239l == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        fVar.e();
        fVar.m(this.f2239l);
        this.f2239l = -1;
        this.f2240m = fVar.f23231r;
    }
}
