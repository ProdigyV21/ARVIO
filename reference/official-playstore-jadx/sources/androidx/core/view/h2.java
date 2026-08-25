package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class h2 implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2242i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f2243l;

    public h2(ViewGroup viewGroup) {
        this.f2243l = viewGroup;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2242i < this.f2243l.getChildCount();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f2242i;
        this.f2242i = i10 + 1;
        View childAt = this.f2243l.getChildAt(i10);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i10 = this.f2242i - 1;
        this.f2242i = i10;
        this.f2243l.removeViewAt(i10);
    }
}
