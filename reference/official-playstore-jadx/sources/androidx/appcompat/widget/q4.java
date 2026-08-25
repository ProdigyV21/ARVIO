package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.d0;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class q4 implements y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Toolbar f1723a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1724b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f1725c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f1726d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1727e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1728f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1729g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f1730h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1731i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final CharSequence f1732j;
    public Window.Callback k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1733l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public m f1734m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1735n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Drawable f1736o;

    public q4(Toolbar toolbar, boolean z) {
        Drawable drawable;
        this.f1735n = 0;
        this.f1723a = toolbar;
        this.f1730h = toolbar.getTitle();
        this.f1731i = toolbar.getSubtitle();
        this.f1729g = this.f1730h != null;
        this.f1728f = toolbar.getNavigationIcon();
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(toolbar.getContext(), null, j.a.f19017a, R.attr.actionBarStyle);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        int i10 = 15;
        this.f1736o = i1VarZ.T(15);
        if (z) {
            CharSequence text = typedArray.getText(27);
            if (!TextUtils.isEmpty(text)) {
                setTitle(text);
            }
            CharSequence text2 = typedArray.getText(25);
            if (!TextUtils.isEmpty(text2)) {
                j(text2);
            }
            Drawable drawableT = i1VarZ.T(20);
            if (drawableT != null) {
                this.f1727e = drawableT;
                v();
            }
            Drawable drawableT2 = i1VarZ.T(17);
            if (drawableT2 != null) {
                setIcon(drawableT2);
            }
            if (this.f1728f == null && (drawable = this.f1736o) != null) {
                this.f1728f = drawable;
                if ((this.f1724b & 4) != 0) {
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            i(typedArray.getInt(10, 0));
            int resourceId = typedArray.getResourceId(9, 0);
            if (resourceId != 0) {
                View viewInflate = LayoutInflater.from(toolbar.getContext()).inflate(resourceId, (ViewGroup) toolbar, false);
                View view = this.f1725c;
                if (view != null && (this.f1724b & 16) != 0) {
                    toolbar.removeView(view);
                }
                this.f1725c = viewInflate;
                if (viewInflate != null && (this.f1724b & 16) != 0) {
                    toolbar.addView(viewInflate);
                }
                i(this.f1724b | 16);
            }
            int layoutDimension = typedArray.getLayoutDimension(13, 0);
            if (layoutDimension > 0) {
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height = layoutDimension;
                toolbar.setLayoutParams(layoutParams);
            }
            int dimensionPixelOffset = typedArray.getDimensionPixelOffset(7, -1);
            int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(3, -1);
            if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
                int iMax = Math.max(dimensionPixelOffset, 0);
                int iMax2 = Math.max(dimensionPixelOffset2, 0);
                toolbar.d();
                toolbar.D.a(iMax, iMax2);
            }
            int resourceId2 = typedArray.getResourceId(28, 0);
            if (resourceId2 != 0) {
                Context context = toolbar.getContext();
                toolbar.f1491v = resourceId2;
                o1 o1Var = toolbar.f1481l;
                if (o1Var != null) {
                    o1Var.setTextAppearance(context, resourceId2);
                }
            }
            int resourceId3 = typedArray.getResourceId(26, 0);
            if (resourceId3 != 0) {
                Context context2 = toolbar.getContext();
                toolbar.f1492w = resourceId3;
                o1 o1Var2 = toolbar.f1482m;
                if (o1Var2 != null) {
                    o1Var2.setTextAppearance(context2, resourceId3);
                }
            }
            int resourceId4 = typedArray.getResourceId(22, 0);
            if (resourceId4 != 0) {
                toolbar.setPopupTheme(resourceId4);
            }
        } else {
            if (toolbar.getNavigationIcon() != null) {
                this.f1736o = toolbar.getNavigationIcon();
            } else {
                i10 = 11;
            }
            this.f1724b = i10;
        }
        i1VarZ.b0();
        if (R.string.abc_action_bar_up_description != this.f1735n) {
            this.f1735n = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                int i11 = this.f1735n;
                this.f1732j = i11 != 0 ? toolbar.getContext().getString(i11) : null;
                u();
            }
        }
        this.f1732j = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new o4(this));
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean a() {
        ActionMenuView actionMenuView;
        Toolbar toolbar = this.f1723a;
        return toolbar.getVisibility() == 0 && (actionMenuView = toolbar.f1480i) != null && actionMenuView.C;
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean b() {
        m mVar;
        ActionMenuView actionMenuView = this.f1723a.f1480i;
        return (actionMenuView == null || (mVar = actionMenuView.D) == null || !mVar.j()) ? false : true;
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean c() {
        return this.f1723a.t();
    }

    @Override // androidx.appcompat.widget.y1
    public final void collapseActionView() {
        l4 l4Var = this.f1723a.W;
        androidx.appcompat.view.menu.t tVar = l4Var == null ? null : l4Var.f1657l;
        if (tVar != null) {
            tVar.collapseActionView();
        }
    }

    @Override // androidx.appcompat.widget.y1
    public final void d(Menu menu, d0.a aVar) {
        m mVar = this.f1734m;
        Toolbar toolbar = this.f1723a;
        if (mVar == null) {
            this.f1734m = new m(toolbar.getContext());
        }
        m mVar2 = this.f1734m;
        mVar2.f1279o = aVar;
        androidx.appcompat.view.menu.q qVar = (androidx.appcompat.view.menu.q) menu;
        if (qVar == null && toolbar.f1480i == null) {
            return;
        }
        toolbar.f();
        androidx.appcompat.view.menu.q qVar2 = toolbar.f1480i.z;
        if (qVar2 == qVar) {
            return;
        }
        if (qVar2 != null) {
            qVar2.r(toolbar.V);
            qVar2.r(toolbar.W);
        }
        if (toolbar.W == null) {
            toolbar.W = new l4(toolbar);
        }
        mVar2.A = true;
        if (qVar != null) {
            qVar.b(mVar2, toolbar.f1489t);
            qVar.b(toolbar.W, toolbar.f1489t);
        } else {
            mVar2.i(toolbar.f1489t, null);
            toolbar.W.i(toolbar.f1489t, null);
            mVar2.e();
            toolbar.W.e();
        }
        toolbar.f1480i.setPopupTheme(toolbar.f1490u);
        toolbar.f1480i.setPresenter(mVar2);
        toolbar.V = mVar2;
        toolbar.u();
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean e() {
        m mVar;
        ActionMenuView actionMenuView = this.f1723a.f1480i;
        return (actionMenuView == null || (mVar = actionMenuView.D) == null || !mVar.k()) ? false : true;
    }

    @Override // androidx.appcompat.widget.y1
    public final void f() {
        this.f1733l = true;
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean g() {
        m mVar;
        ActionMenuView actionMenuView = this.f1723a.f1480i;
        if (actionMenuView == null || (mVar = actionMenuView.D) == null) {
            return false;
        }
        return mVar.E != null || mVar.k();
    }

    @Override // androidx.appcompat.widget.y1
    public final Context getContext() {
        return this.f1723a.getContext();
    }

    @Override // androidx.appcompat.widget.y1
    public final CharSequence getTitle() {
        return this.f1723a.getTitle();
    }

    @Override // androidx.appcompat.widget.y1
    public final boolean h() {
        l4 l4Var = this.f1723a.W;
        return (l4Var == null || l4Var.f1657l == null) ? false : true;
    }

    @Override // androidx.appcompat.widget.y1
    public final void i(int i10) {
        View view;
        int i11 = this.f1724b ^ i10;
        this.f1724b = i10;
        if (i11 != 0) {
            int i12 = i11 & 4;
            Toolbar toolbar = this.f1723a;
            if (i12 != 0) {
                if ((i10 & 4) != 0) {
                    u();
                }
                if ((this.f1724b & 4) != 0) {
                    Drawable drawable = this.f1728f;
                    if (drawable == null) {
                        drawable = this.f1736o;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i11 & 3) != 0) {
                v();
            }
            if ((i11 & 8) != 0) {
                if ((i10 & 8) != 0) {
                    toolbar.setTitle(this.f1730h);
                    toolbar.setSubtitle(this.f1731i);
                } else {
                    toolbar.setTitle((CharSequence) null);
                    toolbar.setSubtitle((CharSequence) null);
                }
            }
            if ((i11 & 16) == 0 || (view = this.f1725c) == null) {
                return;
            }
            if ((i10 & 16) != 0) {
                toolbar.addView(view);
            } else {
                toolbar.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.y1
    public final void j(CharSequence charSequence) {
        this.f1731i = charSequence;
        if ((this.f1724b & 8) != 0) {
            this.f1723a.setSubtitle(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.y1
    public final androidx.core.view.n2 k(int i10, long j10) {
        androidx.core.view.n2 n2VarA = androidx.core.view.b2.a(this.f1723a);
        n2VarA.a(i10 == 0 ? 1.0f : 0.0f);
        n2VarA.c(j10);
        n2VarA.d(new p4(this, i10));
        return n2VarA;
    }

    @Override // androidx.appcompat.widget.y1
    public final void l() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.y1
    public final void m(boolean z) {
        this.f1723a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.y1
    public final void n() {
        m mVar;
        ActionMenuView actionMenuView = this.f1723a.f1480i;
        if (actionMenuView == null || (mVar = actionMenuView.D) == null) {
            return;
        }
        mVar.j();
        g gVar = mVar.D;
        if (gVar == null || !gVar.b()) {
            return;
        }
        gVar.f1273i.dismiss();
    }

    @Override // androidx.appcompat.widget.y1
    public final void p(int i10) {
        this.f1727e = i10 != 0 ? m2.f0.o(this.f1723a.getContext(), i10) : null;
        v();
    }

    @Override // androidx.appcompat.widget.y1
    public final void q(int i10) {
        Toolbar toolbar = this.f1723a;
        Drawable drawableO = i10 != 0 ? m2.f0.o(toolbar.getContext(), i10) : null;
        this.f1728f = drawableO;
        if ((this.f1724b & 4) == 0) {
            toolbar.setNavigationIcon((Drawable) null);
            return;
        }
        if (drawableO == null) {
            drawableO = this.f1736o;
        }
        toolbar.setNavigationIcon(drawableO);
    }

    @Override // androidx.appcompat.widget.y1
    public final void r(int i10) {
        this.f1723a.setVisibility(i10);
    }

    @Override // androidx.appcompat.widget.y1
    public final int s() {
        return this.f1724b;
    }

    @Override // androidx.appcompat.widget.y1
    public final void setIcon(int i10) {
        setIcon(i10 != 0 ? m2.f0.o(this.f1723a.getContext(), i10) : null);
    }

    @Override // androidx.appcompat.widget.y1
    public final void setTitle(CharSequence charSequence) {
        this.f1729g = true;
        this.f1730h = charSequence;
        if ((this.f1724b & 8) != 0) {
            Toolbar toolbar = this.f1723a;
            toolbar.setTitle(charSequence);
            if (this.f1729g) {
                androidx.core.view.b2.i(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // androidx.appcompat.widget.y1
    public final void setWindowCallback(Window.Callback callback) {
        this.k = callback;
    }

    @Override // androidx.appcompat.widget.y1
    public final void setWindowTitle(CharSequence charSequence) {
        if (this.f1729g) {
            return;
        }
        this.f1730h = charSequence;
        if ((this.f1724b & 8) != 0) {
            Toolbar toolbar = this.f1723a;
            toolbar.setTitle(charSequence);
            if (this.f1729g) {
                androidx.core.view.b2.i(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // androidx.appcompat.widget.y1
    public final void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public final void u() {
        if ((this.f1724b & 4) != 0) {
            boolean zIsEmpty = TextUtils.isEmpty(this.f1732j);
            Toolbar toolbar = this.f1723a;
            if (zIsEmpty) {
                toolbar.setNavigationContentDescription(this.f1735n);
            } else {
                toolbar.setNavigationContentDescription(this.f1732j);
            }
        }
    }

    public final void v() {
        Drawable drawable;
        int i10 = this.f1724b;
        if ((i10 & 2) == 0) {
            drawable = null;
        } else if ((i10 & 1) == 0 || (drawable = this.f1727e) == null) {
            drawable = this.f1726d;
        }
        this.f1723a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.y1
    public final void setIcon(Drawable drawable) {
        this.f1726d = drawable;
        v();
    }

    @Override // androidx.appcompat.widget.y1
    public final void o() {
    }
}
