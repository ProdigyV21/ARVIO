package l;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.leanback.widget.u;
import j0.g;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Drawable implements g, Drawable.Callback {
    public static final /* synthetic */ int D = 0;
    public boolean C;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f20010i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Rect f20011l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f20012m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f20013n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f20015p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f20017r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public u f20018s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f20019t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f20020u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public androidx.vectordrawable.graphics.drawable.e f20021v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public b f20022w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b f20023y;
    public qb.d z;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20014o = 255;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20016q = -1;
    public int A = -1;
    public int B = -1;

    public e(b bVar, Resources resources) {
        i(new b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x0275, code lost:
    
        r5.onStateChange(r5.getState());
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x027c, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static l.e c(android.content.Context r24, android.content.res.Resources r25, android.content.res.XmlResourceParser r26, android.util.AttributeSet r27, android.content.res.Resources.Theme r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 667
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l.e.c(android.content.Context, android.content.res.Resources, android.content.res.XmlResourceParser, android.util.AttributeSet, android.content.res.Resources$Theme):l.e");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f20015p = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f20012m
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r8 = 0
            if (r3 == 0) goto L36
            long r9 = r13.f20019t
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.f20014o
            r3.setAlpha(r9)
            r13.f20019t = r6
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            l.b r10 = r13.f20010i
            int r10 = r10.f20004y
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.f20014o
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.f20019t = r6
        L38:
            r3 = r8
        L39:
            android.graphics.drawable.Drawable r9 = r13.f20013n
            if (r9 == 0) goto L61
            long r10 = r13.f20020u
            int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r8, r8)
            r0 = 0
            r13.f20013n = r0
            r13.f20020u = r6
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            l.b r4 = r13.f20010i
            int r4 = r4.z
            int r3 = r3 / r4
            int r4 = r13.f20014o
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.f20020u = r6
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            androidx.leanback.widget.u r14 = r13.f20018s
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: l.e.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        b(theme);
        onStateChange(getState());
    }

    public final void b(Resources.Theme theme) {
        b bVar = this.f20010i;
        if (theme == null) {
            bVar.getClass();
            return;
        }
        bVar.c();
        int i10 = bVar.f19989h;
        Drawable[] drawableArr = bVar.f19988g;
        for (int i11 = 0; i11 < i10; i11++) {
            Drawable drawable = drawableArr[i11];
            if (drawable != null && j0.a.b(drawable)) {
                j0.a.a(drawableArr[i11], theme);
                bVar.f19986e |= drawableArr[i11].getChangingConfigurations();
            }
        }
        Resources resources = theme.getResources();
        if (resources != null) {
            bVar.f19983b = resources;
            int i12 = resources.getDisplayMetrics().densityDpi;
            if (i12 == 0) {
                i12 = 160;
            }
            int i13 = bVar.f19984c;
            bVar.f19984c = i12;
            if (i13 != i12) {
                bVar.f19993m = false;
                bVar.f19991j = false;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        return this.f20010i.canApplyTheme();
    }

    public final void d(Drawable drawable) {
        if (this.f20021v == null) {
            this.f20021v = new androidx.vectordrawable.graphics.drawable.e();
        }
        androidx.vectordrawable.graphics.drawable.e eVar = this.f20021v;
        eVar.f6625l = drawable.getCallback();
        drawable.setCallback(eVar);
        try {
            if (this.f20010i.f20004y <= 0 && this.f20015p) {
                drawable.setAlpha(this.f20014o);
            }
            b bVar = this.f20010i;
            if (bVar.C) {
                drawable.setColorFilter(bVar.B);
            } else {
                if (bVar.F) {
                    j0.a.h(drawable, bVar.D);
                }
                b bVar2 = this.f20010i;
                if (bVar2.G) {
                    j0.a.i(drawable, bVar2.E);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f20010i.f20003w);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            j0.b.b(drawable, j0.b.a(this));
            drawable.setAutoMirrored(this.f20010i.A);
            Rect rect = this.f20011l;
            if (rect != null) {
                j0.a.f(drawable, rect.left, rect.top, rect.right, rect.bottom);
            }
            androidx.vectordrawable.graphics.drawable.e eVar2 = this.f20021v;
            Drawable.Callback callback = (Drawable.Callback) eVar2.f6625l;
            eVar2.f6625l = null;
            drawable.setCallback(callback);
        } catch (Throwable th) {
            androidx.vectordrawable.graphics.drawable.e eVar3 = this.f20021v;
            Drawable.Callback callback2 = (Drawable.Callback) eVar3.f6625l;
            eVar3.f6625l = null;
            drawable.setCallback(callback2);
            throw th;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f20013n;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public final void e() {
        boolean z;
        Drawable drawable = this.f20013n;
        boolean z5 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f20013n = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f20012m;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f20015p) {
                this.f20012m.setAlpha(this.f20014o);
            }
        }
        if (this.f20020u != 0) {
            this.f20020u = 0L;
            z = true;
        }
        if (this.f20019t != 0) {
            this.f20019t = 0L;
        } else {
            z5 = z;
        }
        if (z5) {
            invalidateSelf();
        }
    }

    public final Drawable f() {
        if (!this.f20017r && super.mutate() == this) {
            b bVar = new b(this.f20023y, this, null);
            bVar.I = bVar.I.clone();
            bVar.J = bVar.J.clone();
            i(bVar);
            this.f20017r = true;
        }
        return this;
    }

    public final Drawable g() {
        if (!this.x) {
            f();
            b bVar = this.f20022w;
            bVar.I = bVar.I.clone();
            bVar.J = bVar.J.clone();
            this.x = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f20014o;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f20010i.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        boolean z;
        b bVar = this.f20010i;
        if (!bVar.f20001u) {
            bVar.c();
            bVar.f20001u = true;
            int i10 = bVar.f19989h;
            Drawable[] drawableArr = bVar.f19988g;
            int i11 = 0;
            while (true) {
                if (i11 >= i10) {
                    bVar.f20002v = true;
                    z = true;
                    break;
                }
                if (drawableArr[i11].getConstantState() == null) {
                    bVar.f20002v = false;
                    z = false;
                    break;
                }
                i11++;
            }
        } else {
            z = bVar.f20002v;
        }
        if (!z) {
            return null;
        }
        this.f20010i.f19985d = getChangingConfigurations();
        return this.f20010i;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable getCurrent() {
        return this.f20012m;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f20011l;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        b bVar = this.f20010i;
        if (bVar.f19992l) {
            if (!bVar.f19993m) {
                bVar.b();
            }
            return bVar.f19995o;
        }
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        b bVar = this.f20010i;
        if (bVar.f19992l) {
            if (!bVar.f19993m) {
                bVar.b();
            }
            return bVar.f19994n;
        }
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        b bVar = this.f20010i;
        if (bVar.f19992l) {
            if (!bVar.f19993m) {
                bVar.b();
            }
            return bVar.f19997q;
        }
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        b bVar = this.f20010i;
        if (bVar.f19992l) {
            if (!bVar.f19993m) {
                bVar.b();
            }
            return bVar.f19996p;
        }
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f20012m;
        if (drawable != null && drawable.isVisible()) {
            b bVar = this.f20010i;
            if (bVar.f19998r) {
                return bVar.f19999s;
            }
            bVar.c();
            int i10 = bVar.f19989h;
            Drawable[] drawableArr = bVar.f19988g;
            opacity = i10 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i11 = 1; i11 < i10; i11++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i11].getOpacity());
            }
            bVar.f19999s = opacity;
            bVar.f19998r = true;
        }
        return opacity;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        b bVar = this.f20010i;
        Rect rect2 = null;
        boolean padding = false;
        if (!bVar.f19990i) {
            Rect rect3 = bVar.k;
            if (rect3 != null || bVar.f19991j) {
                rect2 = rect3;
            } else {
                bVar.c();
                Rect rect4 = new Rect();
                int i10 = bVar.f19989h;
                Drawable[] drawableArr = bVar.f19988g;
                for (int i11 = 0; i11 < i10; i11++) {
                    if (drawableArr[i11].getPadding(rect4)) {
                        if (rect2 == null) {
                            rect2 = new Rect(0, 0, 0, 0);
                        }
                        int i12 = rect4.left;
                        if (i12 > rect2.left) {
                            rect2.left = i12;
                        }
                        int i13 = rect4.top;
                        if (i13 > rect2.top) {
                            rect2.top = i13;
                        }
                        int i14 = rect4.right;
                        if (i14 > rect2.right) {
                            rect2.right = i14;
                        }
                        int i15 = rect4.bottom;
                        if (i15 > rect2.bottom) {
                            rect2.bottom = i15;
                        }
                    }
                }
                bVar.f19991j = true;
                bVar.k = rect2;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            if ((rect2.left | rect2.top | rect2.bottom | rect2.right) != 0) {
                padding = true;
            }
        } else {
            Drawable drawable = this.f20012m;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (this.f20010i.A && j0.b.a(this) == 1) {
            int i16 = rect.left;
            rect.left = rect.right;
            rect.right = i16;
        }
        return padding;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean h(int r10) {
        /*
            r9 = this;
            int r0 = r9.f20016q
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            l.b r0 = r9.f20010i
            int r0 = r0.z
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.f20013n
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.f20012m
            if (r0 == 0) goto L29
            r9.f20013n = r0
            l.b r0 = r9.f20010i
            int r0 = r0.z
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.f20020u = r0
            goto L35
        L29:
            r9.f20013n = r4
            r9.f20020u = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.f20012m
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            l.b r0 = r9.f20010i
            int r1 = r0.f19989h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.d(r10)
            r9.f20012m = r0
            r9.f20016q = r10
            if (r0 == 0) goto L5a
            l.b r10 = r9.f20010i
            int r10 = r10.f20004y
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.f20019t = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.f20012m = r4
            r10 = -1
            r9.f20016q = r10
        L5a:
            long r0 = r9.f20019t
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.f20020u
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L7b
        L67:
            androidx.leanback.widget.u r10 = r9.f20018s
            if (r10 != 0) goto L75
            androidx.leanback.widget.u r10 = new androidx.leanback.widget.u
            r1 = 14
            r10.<init>(r9, r1)
            r9.f20018s = r10
            goto L78
        L75:
            r9.unscheduleSelf(r10)
        L78:
            r9.a(r0)
        L7b:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: l.e.h(int):boolean");
    }

    public final void i(b bVar) {
        this.f20010i = bVar;
        int i10 = this.f20016q;
        if (i10 >= 0) {
            Drawable drawableD = bVar.d(i10);
            this.f20012m = drawableD;
            if (drawableD != null) {
                d(drawableD);
            }
        }
        this.f20013n = null;
        this.f20022w = bVar;
        this.f20023y = bVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        b bVar = this.f20010i;
        if (bVar != null) {
            bVar.f19998r = false;
            bVar.f20000t = false;
        }
        if (drawable != this.f20012m || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        return this.f20010i.A;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    public final boolean j(boolean z, boolean z5) {
        boolean visible = super.setVisible(z, z5);
        Drawable drawable = this.f20013n;
        if (drawable != null) {
            drawable.setVisible(z, z5);
        }
        Drawable drawable2 = this.f20012m;
        if (drawable2 != null) {
            drawable2.setVisible(z, z5);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        e();
        qb.d dVar = this.z;
        if (dVar != null) {
            dVar.W();
            this.z = null;
            h(this.A);
            this.A = -1;
            this.B = -1;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.C) {
            g();
            b bVar = this.f20023y;
            bVar.I = bVar.I.clone();
            bVar.J = bVar.J.clone();
            this.C = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f20013n;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f20012m;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i10) {
        b bVar = this.f20010i;
        int i11 = this.f20016q;
        int i12 = bVar.f19989h;
        Drawable[] drawableArr = bVar.f19988g;
        boolean z = false;
        for (int i13 = 0; i13 < i12; i13++) {
            Drawable drawable = drawableArr[i13];
            if (drawable != null) {
                boolean zB = j0.b.b(drawable, i10);
                if (i13 == i11) {
                    z = zB;
                }
            }
        }
        bVar.x = i10;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i10) {
        Drawable drawable = this.f20013n;
        if (drawable != null) {
            return drawable.setLevel(i10);
        }
        Drawable drawable2 = this.f20012m;
        if (drawable2 != null) {
            return drawable2.setLevel(i10);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d3  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onStateChange(int[] r15) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l.e.onStateChange(int[]):boolean");
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j10) {
        if (drawable != this.f20012m || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j10);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i10) {
        if (this.f20015p && this.f20014o == i10) {
            return;
        }
        this.f20015p = true;
        this.f20014o = i10;
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            if (this.f20019t == 0) {
                drawable.setAlpha(i10);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        b bVar = this.f20010i;
        if (bVar.A != z) {
            bVar.A = z;
            Drawable drawable = this.f20012m;
            if (drawable != null) {
                drawable.setAutoMirrored(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        b bVar = this.f20010i;
        bVar.C = true;
        if (bVar.B != colorFilter) {
            bVar.B = colorFilter;
            Drawable drawable = this.f20012m;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setDither(boolean z) {
        b bVar = this.f20010i;
        if (bVar.f20003w != z) {
            bVar.f20003w = z;
            Drawable drawable = this.f20012m;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspot(float f10, float f11) {
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            j0.a.e(drawable, f10, f11);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i10, int i11, int i12, int i13) {
        Rect rect = this.f20011l;
        if (rect == null) {
            this.f20011l = new Rect(i10, i11, i12, i13);
        } else {
            rect.set(i10, i11, i12, i13);
        }
        Drawable drawable = this.f20012m;
        if (drawable != null) {
            j0.a.f(drawable, i10, i11, i12, i13);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i10) {
        setTintList(ColorStateList.valueOf(i10));
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        b bVar = this.f20010i;
        bVar.F = true;
        if (bVar.D != colorStateList) {
            bVar.D = colorStateList;
            j0.a.h(this.f20012m, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        b bVar = this.f20010i;
        bVar.G = true;
        if (bVar.E != mode) {
            bVar.E = mode;
            j0.a.i(this.f20012m, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z5) {
        boolean zJ = j(z, z5);
        qb.d dVar = this.z;
        if (dVar != null && (zJ || z5)) {
            if (z) {
                dVar.T();
                return zJ;
            }
            jumpToCurrentState();
        }
        return zJ;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f20012m || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
