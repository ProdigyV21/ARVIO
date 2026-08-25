package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.arvio.tv.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class j3 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static j3 f1610i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f1612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t.l0 f1613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t.m0 f1614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakHashMap f1615d = new WeakHashMap(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f1616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public z f1618g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final PorterDuff.Mode f1609h = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final f3 f1611j = new f3(6);

    public static synchronized j3 c() {
        try {
            if (f1610i == null) {
                j3 j3Var = new j3();
                f1610i = j3Var;
                i(j3Var);
            }
        } catch (Throwable th) {
            throw th;
        }
        return f1610i;
    }

    public static synchronized PorterDuffColorFilter g(int i10, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        f3 f3Var = f1611j;
        f3Var.getClass();
        int i11 = (31 + i10) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) f3Var.get(Integer.valueOf(mode.hashCode() + i11));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i10, mode);
        }
        return porterDuffColorFilter;
    }

    public static void i(j3 j3Var) {
        if (Build.VERSION.SDK_INT < 24) {
            j3Var.a("vector", new g3(3));
            j3Var.a("animated-vector", new g3(2));
            j3Var.a("animated-selector", new g3(1));
            j3Var.a("drawable", new g3(0));
        }
    }

    public final void a(String str, h3 h3Var) {
        if (this.f1613b == null) {
            this.f1613b = new t.l0(0);
        }
        this.f1613b.put(str, h3Var);
    }

    public final synchronized void b(Context context, long j10, Drawable drawable) {
        try {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                t.p pVar = (t.p) this.f1615d.get(context);
                if (pVar == null) {
                    pVar = new t.p((Object) null);
                    this.f1615d.put(context, pVar);
                }
                pVar.g(j10, new WeakReference(constantState));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized Drawable d(Context context, long j10) {
        t.p pVar = (t.p) this.f1615d.get(context);
        if (pVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) pVar.d(j10);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int iB = u.a.b(pVar.f21858l, pVar.f21860n, j10);
            if (iB >= 0) {
                Object[] objArr = pVar.f21859m;
                Object obj = objArr[iB];
                Object obj2 = t.q.f21861a;
                if (obj != obj2) {
                    objArr[iB] = obj2;
                    pVar.f21857i = true;
                }
            }
        }
        return null;
    }

    public final synchronized Drawable e(Context context, int i10) {
        return f(context, i10, false);
    }

    public final synchronized Drawable f(Context context, int i10, boolean z) {
        Drawable drawableJ;
        try {
            if (!this.f1617f) {
                this.f1617f = true;
                Drawable drawableE = e(context, R.drawable.abc_vector_test);
                if (drawableE == null || (!(drawableE instanceof androidx.vectordrawable.graphics.drawable.t) && !"android.graphics.drawable.VectorDrawable".equals(drawableE.getClass().getName()))) {
                    this.f1617f = false;
                    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                }
            }
            drawableJ = j(context, i10);
            if (drawableJ == null) {
                if (this.f1616e == null) {
                    this.f1616e = new TypedValue();
                }
                TypedValue typedValue = this.f1616e;
                context.getResources().getValue(i10, typedValue, true);
                long j10 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
                Drawable drawableD = d(context, j10);
                if (drawableD != null) {
                    drawableJ = drawableD;
                } else {
                    LayerDrawable layerDrawableE = null;
                    if (this.f1618g != null) {
                        if (i10 == R.drawable.abc_cab_background_top_material) {
                            layerDrawableE = new LayerDrawable(new Drawable[]{e(context, R.drawable.abc_cab_background_internal_bg), e(context, 2131230737)});
                        } else if (i10 == R.drawable.abc_ratingbar_material) {
                            layerDrawableE = z.e(this, context, R.dimen.abc_star_big);
                        } else if (i10 == R.drawable.abc_ratingbar_indicator_material) {
                            layerDrawableE = z.e(this, context, R.dimen.abc_star_medium);
                        } else if (i10 == R.drawable.abc_ratingbar_small_material) {
                            layerDrawableE = z.e(this, context, R.dimen.abc_star_small);
                        }
                    }
                    if (layerDrawableE != null) {
                        layerDrawableE.setChangingConfigurations(typedValue.changingConfigurations);
                        b(context, j10, layerDrawableE);
                    }
                    drawableJ = layerDrawableE;
                }
            }
            if (drawableJ == null) {
                drawableJ = androidx.core.content.a.b(context, i10);
            }
            if (drawableJ != null) {
                drawableJ = m(context, i10, z, drawableJ);
            }
            if (drawableJ != null) {
                z1.a(drawableJ);
            }
        } finally {
        }
        return drawableJ;
    }

    public final synchronized ColorStateList h(Context context, int i10) {
        ColorStateList colorStateList;
        t.m0 m0Var;
        WeakHashMap weakHashMap = this.f1612a;
        ColorStateList colorStateListF = null;
        colorStateList = (weakHashMap == null || (m0Var = (t.m0) weakHashMap.get(context)) == null) ? null : (ColorStateList) m0Var.c(i10);
        if (colorStateList == null) {
            z zVar = this.f1618g;
            if (zVar != null) {
                colorStateListF = zVar.f(context, i10);
            }
            if (colorStateListF != null) {
                if (this.f1612a == null) {
                    this.f1612a = new WeakHashMap();
                }
                t.m0 m0Var2 = (t.m0) this.f1612a.get(context);
                if (m0Var2 == null) {
                    m0Var2 = new t.m0(0);
                    this.f1612a.put(context, m0Var2);
                }
                m0Var2.a(i10, colorStateListF);
            }
            colorStateList = colorStateListF;
        }
        return colorStateList;
    }

    public final Drawable j(Context context, int i10) {
        int next;
        t.l0 l0Var = this.f1613b;
        if (l0Var == null || l0Var.isEmpty()) {
            return null;
        }
        t.m0 m0Var = this.f1614c;
        if (m0Var != null) {
            String str = (String) m0Var.c(i10);
            if ("appcompat_skip_skip".equals(str)) {
                return null;
            }
            if (str != null && this.f1613b.get(str) == null) {
                return null;
            }
        } else {
            this.f1614c = new t.m0(0);
        }
        if (this.f1616e == null) {
            this.f1616e = new TypedValue();
        }
        TypedValue typedValue = this.f1616e;
        Resources resources = context.getResources();
        resources.getValue(i10, typedValue, true);
        long j10 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable drawableD = d(context, j10);
        if (drawableD != null) {
            return drawableD;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i10);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f1614c.a(i10, name);
                h3 h3Var = (h3) this.f1613b.get(name);
                if (h3Var != null) {
                    drawableD = h3Var.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableD != null) {
                    drawableD.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, j10, drawableD);
                }
            } catch (Exception e5) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e5);
            }
        }
        if (drawableD == null) {
            this.f1614c.a(i10, "appcompat_skip_skip");
        }
        return drawableD;
    }

    public final synchronized void k(Context context) {
        t.p pVar = (t.p) this.f1615d.get(context);
        if (pVar != null) {
            pVar.b();
        }
    }

    public final synchronized void l(z zVar) {
        this.f1618g = zVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.drawable.Drawable m(android.content.Context r9, int r10, boolean r11, android.graphics.drawable.Drawable r12) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.j3.m(android.content.Context, int, boolean, android.graphics.drawable.Drawable):android.graphics.drawable.Drawable");
    }
}
