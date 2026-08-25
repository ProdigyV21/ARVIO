package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 implements p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5177c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LayoutDirection f5180f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f5181g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f5182h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f5183i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f5184j;
    public final int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5185l = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f5186m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5187n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5188o;

    public c0(int i10, Object obj, boolean z, int i11, int i12, boolean z5, LayoutDirection layoutDirection, int i13, int i14, List list, long j10, Object obj2) {
        this.f5175a = i10;
        this.f5176b = obj;
        this.f5177c = z;
        this.f5178d = i11;
        this.f5179e = z5;
        this.f5180f = layoutDirection;
        this.f5181g = list;
        this.f5182h = j10;
        this.f5183i = obj2;
        int size = list.size();
        int iMax = 0;
        for (int i15 = 0; i15 < size; i15++) {
            Placeable placeable = (Placeable) list.get(i15);
            iMax = Math.max(iMax, this.f5177c ? placeable.getHeight() : placeable.getWidth());
        }
        this.f5184j = iMax;
        int i16 = i12 + iMax;
        this.k = i16 >= 0 ? i16 : 0;
        if (this.f5177c) {
            IntSizeKt.IntSize(this.f5178d, iMax);
        } else {
            IntSizeKt.IntSize(iMax, this.f5178d);
        }
        this.f5186m = IntOffset.INSTANCE.m5812getZeronOccac();
        this.f5187n = -1;
        this.f5188o = -1;
    }

    public final void a(int i10) {
        ((Placeable) this.f5181g.get(i10)).getParentData();
    }

    public final void b(int i10, int i11, int i12, int i13, int i14, int i15) {
        boolean z = this.f5177c;
        this.f5185l = z ? i13 : i12;
        if (!z) {
            i12 = i13;
        }
        if (z && this.f5180f == LayoutDirection.Rtl) {
            i11 = (i12 - i11) - this.f5178d;
        }
        this.f5186m = z ? IntOffsetKt.IntOffset(i11, i10) : IntOffsetKt.IntOffset(i10, i11);
        this.f5187n = i14;
        this.f5188o = i15;
    }

    @Override // androidx.tv.foundation.lazy.grid.p0
    public final int getColumn() {
        return this.f5188o;
    }

    @Override // androidx.tv.foundation.lazy.grid.p0
    public final int getIndex() {
        return this.f5175a;
    }

    @Override // androidx.tv.foundation.lazy.grid.p0
    public final int getRow() {
        return this.f5187n;
    }
}
