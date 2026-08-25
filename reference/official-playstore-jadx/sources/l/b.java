package l;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.StateSet;
import t.m0;
import t.p;
import t.q;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Drawable.ConstantState {
    public boolean A;
    public ColorFilter B;
    public boolean C;
    public ColorStateList D;
    public PorterDuff.Mode E;
    public boolean F;
    public boolean G;
    public int[][] H;
    public p I;
    public m0 J;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f19982a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources f19983b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f19984c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f19985d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f19986e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray f19987f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable[] f19988g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f19989h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f19990i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f19991j;
    public Rect k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f19992l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f19993m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19994n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f19995o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f19996p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19997q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f19998r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f19999s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f20000t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20001u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f20002v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f20003w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f20004y;
    public int z;

    public b(b bVar, e eVar, Resources resources) {
        this.f19990i = false;
        this.f19992l = false;
        this.f20003w = true;
        this.f20004y = 0;
        this.z = 0;
        this.f19982a = eVar;
        this.f19983b = resources != null ? resources : bVar != null ? bVar.f19983b : null;
        int i10 = bVar != null ? bVar.f19984c : 0;
        int i11 = e.D;
        i10 = resources != null ? resources.getDisplayMetrics().densityDpi : i10;
        i10 = i10 == 0 ? 160 : i10;
        this.f19984c = i10;
        if (bVar != null) {
            this.f19985d = bVar.f19985d;
            this.f19986e = bVar.f19986e;
            this.f20001u = true;
            this.f20002v = true;
            this.f19990i = bVar.f19990i;
            this.f19992l = bVar.f19992l;
            this.f20003w = bVar.f20003w;
            this.x = bVar.x;
            this.f20004y = bVar.f20004y;
            this.z = bVar.z;
            this.A = bVar.A;
            this.B = bVar.B;
            this.C = bVar.C;
            this.D = bVar.D;
            this.E = bVar.E;
            this.F = bVar.F;
            this.G = bVar.G;
            if (bVar.f19984c == i10) {
                if (bVar.f19991j) {
                    this.k = bVar.k != null ? new Rect(bVar.k) : null;
                    this.f19991j = true;
                }
                if (bVar.f19993m) {
                    this.f19994n = bVar.f19994n;
                    this.f19995o = bVar.f19995o;
                    this.f19996p = bVar.f19996p;
                    this.f19997q = bVar.f19997q;
                    this.f19993m = true;
                }
            }
            if (bVar.f19998r) {
                this.f19999s = bVar.f19999s;
                this.f19998r = true;
            }
            if (bVar.f20000t) {
                this.f20000t = true;
            }
            Drawable[] drawableArr = bVar.f19988g;
            this.f19988g = new Drawable[drawableArr.length];
            this.f19989h = bVar.f19989h;
            SparseArray sparseArray = bVar.f19987f;
            if (sparseArray != null) {
                this.f19987f = sparseArray.clone();
            } else {
                this.f19987f = new SparseArray(this.f19989h);
            }
            int i12 = this.f19989h;
            for (int i13 = 0; i13 < i12; i13++) {
                Drawable drawable = drawableArr[i13];
                if (drawable != null) {
                    Drawable.ConstantState constantState = drawable.getConstantState();
                    if (constantState != null) {
                        this.f19987f.put(i13, constantState);
                    } else {
                        this.f19988g[i13] = drawableArr[i13];
                    }
                }
            }
        } else {
            this.f19988g = new Drawable[10];
            this.f19989h = 0;
        }
        if (bVar != null) {
            this.H = bVar.H;
        } else {
            this.H = new int[this.f19988g.length][];
        }
        if (bVar != null) {
            this.I = bVar.I;
            this.J = bVar.J;
        } else {
            this.I = new p((Object) null);
            this.J = new m0(0);
        }
    }

    public final int a(Drawable drawable) {
        int i10 = this.f19989h;
        if (i10 >= this.f19988g.length) {
            int i11 = i10 + 10;
            Drawable[] drawableArr = new Drawable[i11];
            Drawable[] drawableArr2 = this.f19988g;
            if (drawableArr2 != null) {
                System.arraycopy(drawableArr2, 0, drawableArr, 0, i10);
            }
            this.f19988g = drawableArr;
            int[][] iArr = new int[i11][];
            System.arraycopy(this.H, 0, iArr, 0, i10);
            this.H = iArr;
        }
        drawable.mutate();
        drawable.setVisible(false, true);
        drawable.setCallback(this.f19982a);
        this.f19988g[i10] = drawable;
        this.f19989h++;
        this.f19986e = drawable.getChangingConfigurations() | this.f19986e;
        this.f19998r = false;
        this.f20000t = false;
        this.k = null;
        this.f19991j = false;
        this.f19993m = false;
        this.f20001u = false;
        return i10;
    }

    public final void b() {
        this.f19993m = true;
        c();
        int i10 = this.f19989h;
        Drawable[] drawableArr = this.f19988g;
        this.f19995o = -1;
        this.f19994n = -1;
        this.f19997q = 0;
        this.f19996p = 0;
        for (int i11 = 0; i11 < i10; i11++) {
            Drawable drawable = drawableArr[i11];
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (intrinsicWidth > this.f19994n) {
                this.f19994n = intrinsicWidth;
            }
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight > this.f19995o) {
                this.f19995o = intrinsicHeight;
            }
            int minimumWidth = drawable.getMinimumWidth();
            if (minimumWidth > this.f19996p) {
                this.f19996p = minimumWidth;
            }
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumHeight > this.f19997q) {
                this.f19997q = minimumHeight;
            }
        }
    }

    public final void c() {
        SparseArray sparseArray = this.f19987f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i10 = 0; i10 < size; i10++) {
                int iKeyAt = this.f19987f.keyAt(i10);
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f19987f.valueAt(i10);
                Drawable[] drawableArr = this.f19988g;
                Drawable drawableNewDrawable = constantState.newDrawable(this.f19983b);
                j0.b.b(drawableNewDrawable, this.x);
                Drawable drawableMutate = drawableNewDrawable.mutate();
                drawableMutate.setCallback(this.f19982a);
                drawableArr[iKeyAt] = drawableMutate;
            }
            this.f19987f = null;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        int i10 = this.f19989h;
        Drawable[] drawableArr = this.f19988g;
        for (int i11 = 0; i11 < i10; i11++) {
            Drawable drawable = drawableArr[i11];
            if (drawable == null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f19987f.get(i11);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (j0.a.b(drawable)) {
                return true;
            }
        }
        return false;
    }

    public final Drawable d(int i10) {
        int iIndexOfKey;
        Drawable drawable = this.f19988g[i10];
        if (drawable != null) {
            return drawable;
        }
        SparseArray sparseArray = this.f19987f;
        if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i10)) < 0) {
            return null;
        }
        Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f19987f.valueAt(iIndexOfKey)).newDrawable(this.f19983b);
        j0.b.b(drawableNewDrawable, this.x);
        Drawable drawableMutate = drawableNewDrawable.mutate();
        drawableMutate.setCallback(this.f19982a);
        this.f19988g[i10] = drawableMutate;
        this.f19987f.removeAt(iIndexOfKey);
        if (this.f19987f.size() == 0) {
            this.f19987f = null;
        }
        return drawableMutate;
    }

    public final int e(int i10) {
        Object obj;
        if (i10 < 0) {
            return 0;
        }
        m0 m0Var = this.J;
        Object obj2 = 0;
        int iA = u.a.a(m0Var.f21854n, i10, m0Var.f21852l);
        if (iA >= 0 && (obj = m0Var.f21853m[iA]) != q.f21862b) {
            obj2 = obj;
        }
        return ((Integer) obj2).intValue();
    }

    public final int f(int[] iArr) {
        int[][] iArr2 = this.H;
        int i10 = this.f19989h;
        for (int i11 = 0; i11 < i10; i11++) {
            if (StateSet.stateSetMatches(iArr2[i11], iArr)) {
                return i11;
            }
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f19985d | this.f19986e;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new e(this, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new e(this, resources);
    }
}
