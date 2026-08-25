package n;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.t;
import androidx.appcompat.view.menu.y;
import androidx.core.view.g0;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    public CharSequence A;
    public CharSequence B;
    public final /* synthetic */ j E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Menu f20391a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f20398h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20399i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20400j;
    public CharSequence k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f20401l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20402m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public char f20403n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20404o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public char f20405p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20406q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f20407r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f20408s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f20409t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20410u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f20411v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f20412w;
    public String x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f20413y;
    public androidx.core.view.e z;
    public ColorStateList C = null;
    public PorterDuff.Mode D = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20392b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f20393c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f20394d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20395e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f20396f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f20397g = true;

    public i(j jVar, Menu menu) {
        this.E = jVar;
        this.f20391a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.E.f20418c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e5) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e5);
            return null;
        }
    }

    public final void b(MenuItem menuItem) {
        j jVar = this.E;
        Context context = jVar.f20418c;
        boolean z = false;
        menuItem.setChecked(this.f20408s).setVisible(this.f20409t).setEnabled(this.f20410u).setCheckable(this.f20407r >= 1).setTitleCondensed(this.f20401l).setIcon(this.f20402m);
        int i10 = this.f20411v;
        if (i10 >= 0) {
            menuItem.setShowAsAction(i10);
        }
        if (this.f20413y != null) {
            if (context.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            if (jVar.f20419d == null) {
                jVar.f20419d = j.a(context);
            }
            Object obj = jVar.f20419d;
            String str = this.f20413y;
            h hVar = new h();
            hVar.f20389a = obj;
            Class<?> cls = obj.getClass();
            try {
                hVar.f20390b = cls.getMethod(str, h.f20388c);
                menuItem.setOnMenuItemClickListener(hVar);
            } catch (Exception e5) {
                StringBuilder sbT = a0.c.t("Couldn't resolve menu item onClick handler ", str, " in class ");
                sbT.append(cls.getName());
                InflateException inflateException = new InflateException(sbT.toString());
                inflateException.initCause(e5);
                throw inflateException;
            }
        }
        if (this.f20407r >= 2) {
            if (menuItem instanceof t) {
                t tVar = (t) menuItem;
                tVar.x = (tVar.x & (-5)) | 4;
            } else if (menuItem instanceof y) {
                y yVar = (y) menuItem;
                k0.b bVar = yVar.f1398e;
                try {
                    if (yVar.f1399f == null) {
                        yVar.f1399f = bVar.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    yVar.f1399f.invoke(bVar, Boolean.TRUE);
                } catch (Exception e6) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e6);
                }
            }
        }
        String str2 = this.x;
        if (str2 != null) {
            menuItem.setActionView((View) a(str2, j.f20414e, jVar.f20416a));
            z = true;
        }
        int i11 = this.f20412w;
        if (i11 > 0) {
            if (z) {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            } else {
                menuItem.setActionView(i11);
            }
        }
        androidx.core.view.e eVar = this.z;
        if (eVar != null) {
            if (menuItem instanceof k0.b) {
                ((k0.b) menuItem).b(eVar);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = this.A;
        boolean z5 = menuItem instanceof k0.b;
        if (z5) {
            ((k0.b) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            g0.h(menuItem, charSequence);
        }
        CharSequence charSequence2 = this.B;
        if (z5) {
            ((k0.b) menuItem).setTooltipText(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            g0.m(menuItem, charSequence2);
        }
        char c10 = this.f20403n;
        int i12 = this.f20404o;
        if (z5) {
            ((k0.b) menuItem).setAlphabeticShortcut(c10, i12);
        } else if (Build.VERSION.SDK_INT >= 26) {
            g0.g(menuItem, c10, i12);
        }
        char c11 = this.f20405p;
        int i13 = this.f20406q;
        if (z5) {
            ((k0.b) menuItem).setNumericShortcut(c11, i13);
        } else if (Build.VERSION.SDK_INT >= 26) {
            g0.k(menuItem, c11, i13);
        }
        PorterDuff.Mode mode = this.D;
        if (mode != null) {
            if (z5) {
                ((k0.b) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                g0.j(menuItem, mode);
            }
        }
        ColorStateList colorStateList = this.C;
        if (colorStateList != null) {
            if (z5) {
                ((k0.b) menuItem).setIconTintList(colorStateList);
            } else if (Build.VERSION.SDK_INT >= 26) {
                g0.i(menuItem, colorStateList);
            }
        }
    }
}
