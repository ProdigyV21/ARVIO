package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class x implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Alignment.Horizontal f5508d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Alignment.Vertical f5509e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LayoutDirection f5510f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f5511g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5512h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f5513i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f5514j;
    public final Object k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5515l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f5516m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f5517n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f5518o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5519p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int[] f5520q;

    public x(int i10, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z5, int i11, int i12, int i13, long j10, Object obj, Object obj2) {
        this.f5505a = i10;
        this.f5506b = list;
        this.f5507c = z;
        this.f5508d = horizontal;
        this.f5509e = vertical;
        this.f5510f = layoutDirection;
        this.f5511g = z5;
        this.f5512h = i13;
        this.f5513i = j10;
        this.f5514j = obj;
        this.k = obj2;
        int size = list.size();
        int height = 0;
        int iMax = 0;
        for (int i14 = 0; i14 < size; i14++) {
            Placeable placeable = (Placeable) list.get(i14);
            height += this.f5507c ? placeable.getHeight() : placeable.getWidth();
            iMax = Math.max(iMax, !this.f5507c ? placeable.getHeight() : placeable.getWidth());
        }
        this.f5516m = height;
        int i15 = height + this.f5512h;
        this.f5517n = i15 >= 0 ? i15 : 0;
        this.f5518o = iMax;
        this.f5520q = new int[this.f5506b.size() * 2];
    }

    public final void a(int i10) {
        ((Placeable) this.f5506b.get(i10)).getParentData();
    }

    public final void b(Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope placementScope2;
        int iM5803getYimpl;
        if (this.f5519p == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        List list = this.f5506b;
        int size = list.size();
        int i10 = 0;
        while (i10 < size) {
            Placeable placeable = (Placeable) list.get(i10);
            boolean z = this.f5507c;
            if (z) {
                placeable.getHeight();
            } else {
                placeable.getWidth();
            }
            int i11 = i10 * 2;
            int[] iArr = this.f5520q;
            long jIntOffset = IntOffsetKt.IntOffset(iArr[i11], iArr[i11 + 1]);
            a(i10);
            if (this.f5511g) {
                int iM5802getXimpl = IntOffset.m5802getXimpl(jIntOffset);
                if (!z) {
                    iM5802getXimpl = (this.f5519p - iM5802getXimpl) - (z ? placeable.getHeight() : placeable.getWidth());
                }
                if (z) {
                    iM5803getYimpl = (this.f5519p - IntOffset.m5803getYimpl(jIntOffset)) - (z ? placeable.getHeight() : placeable.getWidth());
                } else {
                    iM5803getYimpl = IntOffset.m5803getYimpl(jIntOffset);
                }
                jIntOffset = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
            }
            int iM5802getXimpl2 = IntOffset.m5802getXimpl(jIntOffset);
            long j10 = this.f5513i;
            long jC = a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + iM5802getXimpl2, j10);
            if (z) {
                placementScope2 = placementScope;
                Placeable.PlacementScope.m4672placeWithLayeraW9wM$default(placementScope2, placeable, jC, 0.0f, null, 6, null);
            } else {
                placementScope2 = placementScope;
                Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(placementScope2, placeable, jC, 0.0f, null, 6, null);
            }
            i10++;
            placementScope = placementScope2;
        }
    }

    public final void c(int i10, int i11, int i12) {
        int width;
        this.f5515l = i10;
        boolean z = this.f5507c;
        this.f5519p = z ? i12 : i11;
        List list = this.f5506b;
        int size = list.size();
        for (int i13 = 0; i13 < size; i13++) {
            Placeable placeable = (Placeable) list.get(i13);
            int i14 = i13 * 2;
            int[] iArr = this.f5520q;
            if (z) {
                Alignment.Horizontal horizontal = this.f5508d;
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalAlignment when isVertical == true");
                }
                iArr[i14] = horizontal.align(placeable.getWidth(), i11, this.f5510f);
                iArr[i14 + 1] = i10;
                width = placeable.getHeight();
            } else {
                iArr[i14] = i10;
                int i15 = i14 + 1;
                Alignment.Vertical vertical = this.f5509e;
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalAlignment when isVertical == false");
                }
                iArr[i15] = vertical.align(placeable.getHeight(), i12);
                width = placeable.getWidth();
            }
            i10 = width + i10;
        }
    }

    @Override // androidx.tv.foundation.lazy.list.b0
    public final int getIndex() {
        return this.f5505a;
    }

    @Override // androidx.tv.foundation.lazy.list.b0
    public final int getOffset() {
        return this.f5515l;
    }

    @Override // androidx.tv.foundation.lazy.list.b0
    public final int getSize() {
        return this.f5516m;
    }
}
