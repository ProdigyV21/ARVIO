package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class u implements mb.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1763b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1764c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1765d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1766e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1767f;

    public u(View view) {
        this.f1762a = -1;
        this.f1763b = view;
        this.f1764c = a0.a();
    }

    @Override // mb.d
    public xb.o0 a(gb.p0 p0Var) {
        if (!mb.e.a(p0Var)) {
            return m(0L);
        }
        String strA = p0Var.f15785p.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        if ("chunked".equalsIgnoreCase(strA)) {
            gb.b0 b0Var = p0Var.f15780i.f15730a;
            if (this.f1762a == 4) {
                this.f1762a = 5;
                return new nb.c(this, b0Var);
            }
            throw new IllegalStateException(("state: " + this.f1762a).toString());
        }
        long jK = ib.c.k(p0Var);
        if (jK != -1) {
            return m(jK);
        }
        if (this.f1762a == 4) {
            this.f1762a = 5;
            ((lb.k) this.f1764c).l();
            return new nb.f(this);
        }
        throw new IllegalStateException(("state: " + this.f1762a).toString());
    }

    @Override // mb.d
    public void b() {
        ((xb.k) this.f1766e).flush();
    }

    @Override // mb.d
    public void c(gb.k0 k0Var) {
        Proxy.Type type = ((lb.k) this.f1764c).f20125b.f15817b.type();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(k0Var.f15731b);
        sb2.append(' ');
        gb.b0 b0Var = k0Var.f15730a;
        if (b0Var.f15626j || type != Proxy.Type.HTTP) {
            String strB = b0Var.b();
            String strD = b0Var.d();
            if (strD != null) {
                strB = androidx.compose.foundation.c.m('?', strB, strD);
            }
            sb2.append(strB);
        } else {
            sb2.append(b0Var);
        }
        sb2.append(" HTTP/1.1");
        s(k0Var.f15732c, sb2.toString());
    }

    @Override // mb.d
    public void cancel() {
        Socket socket = ((lb.k) this.f1764c).f20126c;
        if (socket != null) {
            ib.c.e(socket);
        }
    }

    @Override // mb.d
    public long d(gb.p0 p0Var) {
        if (!mb.e.a(p0Var)) {
            return 0L;
        }
        String strA = p0Var.f15785p.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        if ("chunked".equalsIgnoreCase(strA)) {
            return -1L;
        }
        return ib.c.k(p0Var);
    }

    @Override // mb.d
    public lb.k e() {
        return (lb.k) this.f1764c;
    }

    @Override // mb.d
    public gb.o0 f(boolean z) throws IOException {
        androidx.recyclerview.widget.e eVar = (androidx.recyclerview.widget.e) this.f1767f;
        xb.l lVar = (xb.l) eVar.f4915c;
        int i10 = this.f1762a;
        if (i10 != 1 && i10 != 2 && i10 != 3) {
            throw new IllegalStateException(("state: " + this.f1762a).toString());
        }
        try {
            String strB = lVar.B(eVar.f4914b);
            eVar.f4914b -= (long) strB.length();
            f0 f0VarJ = t7.a.J(strB);
            int i11 = f0VarJ.f1566l;
            gb.o0 o0Var = new gb.o0();
            o0Var.f15763b = (gb.i0) f0VarJ.f1567m;
            o0Var.f15764c = i11;
            o0Var.f15765d = (String) f0VarJ.f1568n;
            gb.z zVar = new gb.z();
            while (true) {
                String strB2 = lVar.B(eVar.f4914b);
                eVar.f4914b -= (long) strB2.length();
                if (strB2.length() == 0) {
                    break;
                }
                zVar.b(strB2);
            }
            o0Var.f15767f = zVar.e().c();
            if (z && i11 == 100) {
                return null;
            }
            if (i11 == 100) {
                this.f1762a = 3;
                return o0Var;
            }
            if (102 > i11 || i11 >= 200) {
                this.f1762a = 4;
                return o0Var;
            }
            this.f1762a = 3;
            return o0Var;
        } catch (EOFException e5) {
            throw new IOException("unexpected end of stream on ".concat(((lb.k) this.f1764c).f20125b.f15816a.f15612h.h()), e5);
        }
    }

    @Override // mb.d
    public void g() {
        ((xb.k) this.f1766e).flush();
    }

    @Override // mb.d
    public xb.m0 h(gb.k0 k0Var, long j10) {
        gb.n0 n0Var = k0Var.f15733d;
        if ("chunked".equalsIgnoreCase(k0Var.f15732c.a("Transfer-Encoding"))) {
            if (this.f1762a == 1) {
                this.f1762a = 2;
                return new nb.b(this);
            }
            throw new IllegalStateException(("state: " + this.f1762a).toString());
        }
        if (j10 == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.f1762a == 1) {
            this.f1762a = 2;
            return new nb.e(this);
        }
        throw new IllegalStateException(("state: " + this.f1762a).toString());
    }

    public void i() {
        View view = (View) this.f1763b;
        Drawable background = view.getBackground();
        if (background != null) {
            if (((g4) this.f1765d) != null) {
                if (((g4) this.f1767f) == null) {
                    this.f1767f = new g4();
                }
                g4 g4Var = (g4) this.f1767f;
                g4Var.f1591c = null;
                g4Var.f1590b = false;
                g4Var.f1592d = null;
                g4Var.f1589a = false;
                WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                ColorStateList colorStateListG = androidx.core.view.k1.g(view);
                if (colorStateListG != null) {
                    g4Var.f1590b = true;
                    g4Var.f1591c = colorStateListG;
                }
                PorterDuff.Mode modeH = androidx.core.view.k1.h(view);
                if (modeH != null) {
                    g4Var.f1589a = true;
                    g4Var.f1592d = modeH;
                }
                if (g4Var.f1590b || g4Var.f1589a) {
                    a0.d(background, g4Var, view.getDrawableState());
                    return;
                }
            }
            g4 g4Var2 = (g4) this.f1766e;
            if (g4Var2 != null) {
                a0.d(background, g4Var2, view.getDrawableState());
                return;
            }
            g4 g4Var3 = (g4) this.f1765d;
            if (g4Var3 != null) {
                a0.d(background, g4Var3, view.getDrawableState());
            }
        }
    }

    public ColorStateList j() {
        g4 g4Var = (g4) this.f1766e;
        if (g4Var != null) {
            return (ColorStateList) g4Var.f1591c;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        g4 g4Var = (g4) this.f1766e;
        if (g4Var != null) {
            return (PorterDuff.Mode) g4Var.f1592d;
        }
        return null;
    }

    public void l(AttributeSet attributeSet, int i10) {
        ColorStateList colorStateListH;
        View view = (View) this.f1763b;
        Context context = view.getContext();
        int[] iArr = j.a.z;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, i10);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        View view2 = (View) this.f1763b;
        androidx.core.view.b2.g(view2, view2.getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, i10);
        try {
            if (typedArray.hasValue(0)) {
                this.f1762a = typedArray.getResourceId(0, -1);
                a0 a0Var = (a0) this.f1764c;
                Context context2 = view.getContext();
                int i11 = this.f1762a;
                synchronized (a0Var) {
                    colorStateListH = a0Var.f1504a.h(context2, i11);
                }
                if (colorStateListH != null) {
                    p(colorStateListH);
                }
            }
            if (typedArray.hasValue(1)) {
                androidx.core.view.k1.q(view, i1VarZ.S(1));
            }
            if (typedArray.hasValue(2)) {
                androidx.core.view.k1.r(view, z1.b(typedArray.getInt(2, -1), null));
            }
            i1VarZ.b0();
        } catch (Throwable th) {
            i1VarZ.b0();
            throw th;
        }
    }

    public nb.d m(long j10) {
        if (this.f1762a == 4) {
            this.f1762a = 5;
            return new nb.d(this, j10);
        }
        throw new IllegalStateException(("state: " + this.f1762a).toString());
    }

    public void n() {
        this.f1762a = -1;
        p(null);
        i();
    }

    public void o(int i10) {
        ColorStateList colorStateListH;
        this.f1762a = i10;
        a0 a0Var = (a0) this.f1764c;
        if (a0Var != null) {
            Context context = ((View) this.f1763b).getContext();
            synchronized (a0Var) {
                colorStateListH = a0Var.f1504a.h(context, i10);
            }
        } else {
            colorStateListH = null;
        }
        p(colorStateListH);
        i();
    }

    public void p(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((g4) this.f1765d) == null) {
                this.f1765d = new g4();
            }
            g4 g4Var = (g4) this.f1765d;
            g4Var.f1591c = colorStateList;
            g4Var.f1590b = true;
        } else {
            this.f1765d = null;
        }
        i();
    }

    public void q(ColorStateList colorStateList) {
        if (((g4) this.f1766e) == null) {
            this.f1766e = new g4();
        }
        g4 g4Var = (g4) this.f1766e;
        g4Var.f1591c = colorStateList;
        g4Var.f1590b = true;
        i();
    }

    public void r(PorterDuff.Mode mode) {
        if (((g4) this.f1766e) == null) {
            this.f1766e = new g4();
        }
        g4 g4Var = (g4) this.f1766e;
        g4Var.f1592d = mode;
        g4Var.f1589a = true;
        i();
    }

    public void s(gb.a0 a0Var, String str) {
        xb.k kVar = (xb.k) this.f1766e;
        if (this.f1762a != 0) {
            throw new IllegalStateException(("state: " + this.f1762a).toString());
        }
        kVar.H(str).H("\r\n");
        int size = a0Var.size();
        for (int i10 = 0; i10 < size; i10++) {
            kVar.H(a0Var.b(i10)).H(": ").H(a0Var.e(i10)).H("\r\n");
        }
        kVar.H("\r\n");
        this.f1762a = 1;
    }

    public u(gb.h0 h0Var, lb.k kVar, xb.i0 i0Var, xb.h0 h0Var2) {
        this.f1763b = h0Var;
        this.f1764c = kVar;
        this.f1765d = i0Var;
        this.f1766e = h0Var2;
        this.f1767f = new androidx.recyclerview.widget.e(i0Var);
    }
}
