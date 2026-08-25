package androidx.vectordrawable.graphics.drawable;

import android.graphics.Paint;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i0.d f6637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i0.d f6639f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6640g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6641h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f6642i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f6643j;
    public float k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint.Cap f6644l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint.Join f6645m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f6646n;

    @Override // androidx.vectordrawable.graphics.drawable.o
    public final boolean a() {
        return this.f6639f.b() || this.f6637d.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @Override // androidx.vectordrawable.graphics.drawable.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(int[] r7) {
        /*
            r6 = this;
            i0.d r0 = r6.f6639f
            boolean r1 = r0.b()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1c
            android.content.res.ColorStateList r1 = r0.f15986b
            int r4 = r1.getDefaultColor()
            int r1 = r1.getColorForState(r7, r4)
            int r4 = r0.f15987c
            if (r1 == r4) goto L1c
            r0.f15987c = r1
            r0 = r2
            goto L1d
        L1c:
            r0 = r3
        L1d:
            i0.d r1 = r6.f6637d
            boolean r4 = r1.b()
            if (r4 == 0) goto L36
            android.content.res.ColorStateList r4 = r1.f15986b
            int r5 = r4.getDefaultColor()
            int r7 = r4.getColorForState(r7, r5)
            int r4 = r1.f15987c
            if (r7 == r4) goto L36
            r1.f15987c = r7
            goto L37
        L36:
            r2 = r3
        L37:
            r7 = r0 | r2
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.m.b(int[]):boolean");
    }

    public float getFillAlpha() {
        return this.f6641h;
    }

    public int getFillColor() {
        return this.f6639f.f15987c;
    }

    public float getStrokeAlpha() {
        return this.f6640g;
    }

    public int getStrokeColor() {
        return this.f6637d.f15987c;
    }

    public float getStrokeWidth() {
        return this.f6638e;
    }

    public float getTrimPathEnd() {
        return this.f6643j;
    }

    public float getTrimPathOffset() {
        return this.k;
    }

    public float getTrimPathStart() {
        return this.f6642i;
    }

    public void setFillAlpha(float f10) {
        this.f6641h = f10;
    }

    public void setFillColor(int i10) {
        this.f6639f.f15987c = i10;
    }

    public void setStrokeAlpha(float f10) {
        this.f6640g = f10;
    }

    public void setStrokeColor(int i10) {
        this.f6637d.f15987c = i10;
    }

    public void setStrokeWidth(float f10) {
        this.f6638e = f10;
    }

    public void setTrimPathEnd(float f10) {
        this.f6643j = f10;
    }

    public void setTrimPathOffset(float f10) {
        this.k = f10;
    }

    public void setTrimPathStart(float f10) {
        this.f6642i = f10;
    }
}
