package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.w4;
import androidx.appcompat.widget.x1;
import androidx.compose.material3.MenuKt;
import androidx.core.view.b2;
import androidx.core.view.n2;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s0 extends z implements androidx.appcompat.view.menu.o, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public static final t.l0 f1169r0 = new t.l0(0);

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static final int[] f1170s0 = {R.attr.windowBackground};

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public static final boolean f1171t0 = !"robolectric".equals(Build.FINGERPRINT);

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final boolean f1172u0 = true;
    public CharSequence A;
    public x1 B;
    public f0 C;
    public r0 D;
    public n.b E;
    public ActionBarContextView F;
    public PopupWindow G;
    public a0 H;
    public boolean J;
    public ViewGroup K;
    public TextView L;
    public View M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public q0[] V;
    public q0 W;
    public boolean X;
    public boolean Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f1173a0;
    public Configuration b0;
    public final int c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f1174d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f1175e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f1176f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public m0 f1177g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public m0 f1178h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f1179i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f1180j0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f1182l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public Rect f1183m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public Rect f1184n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public w0 f1185o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public OnBackInvokedDispatcher f1186p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public OnBackInvokedCallback f1187q0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f1188t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Context f1189u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Window f1190v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public l0 f1191w;
    public final Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public f f1192y;
    public n.j z;
    public n2 I = null;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final a0 f1181k0 = new a0(this, 0);

    public s0(Context context, Window window, u uVar, Object obj) {
        t tVar = null;
        this.c0 = -100;
        this.f1189u = context;
        this.x = uVar;
        this.f1188t = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof t)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        } else {
                            context = ((ContextWrapper) context).getBaseContext();
                        }
                    } else {
                        tVar = (t) context;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (tVar != null) {
                this.c0 = ((s0) tVar.getDelegate()).c0;
            }
        }
        if (this.c0 == -100) {
            String name = this.f1188t.getClass().getName();
            t.l0 l0Var = f1169r0;
            Integer num = (Integer) l0Var.get(name);
            if (num != null) {
                this.c0 = num.intValue();
                l0Var.remove(this.f1188t.getClass().getName());
            }
        }
        if (window != null) {
            p(window);
        }
        androidx.appcompat.widget.a0.c();
    }

    public static androidx.core.os.m A(Configuration configuration) {
        return Build.VERSION.SDK_INT >= 24 ? i0.b(configuration) : androidx.core.os.m.b(h0.a(configuration.locale));
    }

    public static androidx.core.os.m q(Context context) {
        androidx.core.os.m mVar;
        androidx.core.os.m mVarB;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33 || (mVar = z.f1213m) == null) {
            return null;
        }
        androidx.core.os.m mVarA = A(context.getApplicationContext().getResources().getConfiguration());
        int i11 = 0;
        if (i10 < 24) {
            mVarB = mVar.d() ? androidx.core.os.m.f2126b : androidx.core.os.m.b(mVar.c(0).toString());
        } else if (mVar.d()) {
            mVarB = androidx.core.os.m.f2126b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (i11 < mVarA.e() + mVar.e()) {
                Locale localeC = i11 < mVar.e() ? mVar.c(i11) : mVarA.c(i11 - mVar.e());
                if (localeC != null) {
                    linkedHashSet.add(localeC);
                }
                i11++;
            }
            mVarB = androidx.core.os.m.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
        }
        return mVarB.d() ? mVarA : mVarB;
    }

    public static Configuration u(Context context, int i10, androidx.core.os.m mVar, Configuration configuration, boolean z) {
        int i11 = i10 != 1 ? i10 != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i11 | (configuration2.uiMode & (-49));
        if (mVar != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                i0.d(configuration2, mVar);
                return configuration2;
            }
            g0.b(configuration2, mVar.c(0));
            g0.a(configuration2, mVar.c(0));
        }
        return configuration2;
    }

    public final q0 B(int i10) {
        q0[] q0VarArr = this.V;
        if (q0VarArr == null || q0VarArr.length <= i10) {
            q0[] q0VarArr2 = new q0[i10 + 1];
            if (q0VarArr != null) {
                System.arraycopy(q0VarArr, 0, q0VarArr2, 0, q0VarArr.length);
            }
            this.V = q0VarArr2;
            q0VarArr = q0VarArr2;
        }
        q0 q0Var = q0VarArr[i10];
        if (q0Var != null) {
            return q0Var;
        }
        q0 q0Var2 = new q0();
        q0Var2.f1151a = i10;
        q0Var2.f1163n = false;
        q0VarArr[i10] = q0Var2;
        return q0Var2;
    }

    public final void C() {
        x();
        if (this.P && this.f1192y == null) {
            Object obj = this.f1188t;
            if (obj instanceof Activity) {
                this.f1192y = new l1((Activity) obj, this.Q);
            } else if (obj instanceof Dialog) {
                this.f1192y = new l1((Dialog) obj);
            }
            f fVar = this.f1192y;
            if (fVar != null) {
                fVar.p(this.f1182l0);
            }
        }
    }

    public final void D(int i10) {
        this.f1180j0 = (1 << i10) | this.f1180j0;
        if (this.f1179i0) {
            return;
        }
        View decorView = this.f1190v.getDecorView();
        WeakHashMap weakHashMap = b2.f2200a;
        decorView.postOnAnimation(this.f1181k0);
        this.f1179i0 = true;
    }

    public final int E(Context context, int i10) {
        if (i10 != -100) {
            if (i10 != -1) {
                if (i10 != 0) {
                    if (i10 != 1 && i10 != 2) {
                        if (i10 != 3) {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                        if (this.f1178h0 == null) {
                            this.f1178h0 = new m0(this, context);
                        }
                        return this.f1178h0.g();
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) {
                    return z(context).g();
                }
            }
            return i10;
        }
        return -1;
    }

    public final boolean F() {
        boolean z = this.X;
        this.X = false;
        q0 q0VarB = B(0);
        if (!q0VarB.f1162m) {
            n.b bVar = this.E;
            if (bVar != null) {
                bVar.a();
                return true;
            }
            C();
            f fVar = this.f1192y;
            if (fVar == null || !fVar.f()) {
                return false;
            }
        } else if (!z) {
            t(q0VarB, true);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0176, code lost:
    
        if (r2.f1334p.getCount() > 0) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void G(androidx.appcompat.app.q0 r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.G(androidx.appcompat.app.q0, android.view.KeyEvent):void");
    }

    public final boolean H(q0 q0Var, int i10, KeyEvent keyEvent) {
        androidx.appcompat.view.menu.q qVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((q0Var.k || I(q0Var, keyEvent)) && (qVar = q0Var.f1158h) != null) {
            return qVar.performShortcut(i10, keyEvent, 1);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean I(androidx.appcompat.app.q0 r13, android.view.KeyEvent r14) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.I(androidx.appcompat.app.q0, android.view.KeyEvent):boolean");
    }

    public final void J() {
        if (this.J) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void K() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.f1186p0 != null && (B(0).f1162m || this.E != null)) {
                z = true;
            }
            if (z && this.f1187q0 == null) {
                this.f1187q0 = k0.b(this.f1186p0, this);
            } else {
                if (z || (onBackInvokedCallback = this.f1187q0) == null) {
                    return;
                }
                k0.c(this.f1186p0, onBackInvokedCallback);
            }
        }
    }

    @Override // androidx.appcompat.app.z
    public final void a() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1189u);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof s0) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public final boolean b(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        q0 q0Var;
        Window.Callback callback = this.f1190v.getCallback();
        if (callback != null && !this.f1173a0) {
            androidx.appcompat.view.menu.q qVarK = qVar.k();
            q0[] q0VarArr = this.V;
            int length = q0VarArr != null ? q0VarArr.length : 0;
            int i10 = 0;
            while (true) {
                if (i10 < length) {
                    q0Var = q0VarArr[i10];
                    if (q0Var != null && q0Var.f1158h == qVarK) {
                        break;
                    }
                    i10++;
                } else {
                    q0Var = null;
                    break;
                }
            }
            if (q0Var != null) {
                return callback.onMenuItemSelected(q0Var.f1151a, menuItem);
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.z
    public final void c() {
        if (this.f1192y != null) {
            C();
            if (this.f1192y.j()) {
                return;
            }
            D(0);
        }
    }

    @Override // androidx.appcompat.app.z
    public final void e() throws IllegalAccessException {
        String strW;
        this.Y = true;
        o(false, true);
        y();
        Object obj = this.f1188t;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strW = m2.f0.w(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e5) {
                    throw new IllegalArgumentException(e5);
                }
            } catch (IllegalArgumentException unused) {
                strW = null;
            }
            if (strW != null) {
                f fVar = this.f1192y;
                if (fVar == null) {
                    this.f1182l0 = true;
                } else {
                    fVar.p(true);
                }
            }
            synchronized (z.f1218r) {
                z.h(this);
                z.f1217q.add(new WeakReference(this));
            }
        }
        this.b0 = new Configuration(this.f1189u.getResources().getConfiguration());
        this.Z = true;
    }

    @Override // androidx.appcompat.view.menu.o
    public final void f(androidx.appcompat.view.menu.q qVar) {
        x1 x1Var = this.B;
        if (x1Var == null || !x1Var.a() || (ViewConfiguration.get(this.f1189u).hasPermanentMenuKey() && !this.B.g())) {
            q0 q0VarB = B(0);
            q0VarB.f1163n = true;
            t(q0VarB, false);
            G(q0VarB, null);
            return;
        }
        Window.Callback callback = this.f1190v.getCallback();
        if (this.B.e()) {
            this.B.b();
            if (this.f1173a0) {
                return;
            }
            callback.onPanelClosed(108, B(0).f1158h);
            return;
        }
        if (callback == null || this.f1173a0) {
            return;
        }
        if (this.f1179i0 && (1 & this.f1180j0) != 0) {
            View decorView = this.f1190v.getDecorView();
            a0 a0Var = this.f1181k0;
            decorView.removeCallbacks(a0Var);
            a0Var.run();
        }
        q0 q0VarB2 = B(0);
        androidx.appcompat.view.menu.q qVar2 = q0VarB2.f1158h;
        if (qVar2 == null || q0VarB2.f1164o || !callback.onPreparePanel(0, q0VarB2.f1157g, qVar2)) {
            return;
        }
        callback.onMenuOpened(108, q0VarB2.f1158h);
        this.B.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // androidx.appcompat.app.z
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f1188t
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = androidx.appcompat.app.z.f1218r
            monitor-enter(r0)
            androidx.appcompat.app.z.h(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.f1179i0
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.f1190v
            android.view.View r0 = r0.getDecorView()
            androidx.appcompat.app.a0 r1 = r3.f1181k0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.f1173a0 = r0
            int r0 = r3.c0
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.f1188t
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            t.l0 r0 = androidx.appcompat.app.s0.f1169r0
            java.lang.Object r1 = r3.f1188t
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.c0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            t.l0 r0 = androidx.appcompat.app.s0.f1169r0
            java.lang.Object r1 = r3.f1188t
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            androidx.appcompat.app.f r0 = r3.f1192y
            if (r0 == 0) goto L63
            r0.l()
        L63:
            androidx.appcompat.app.m0 r0 = r3.f1177g0
            if (r0 == 0) goto L6a
            r0.e()
        L6a:
            androidx.appcompat.app.m0 r0 = r3.f1178h0
            if (r0 == 0) goto L71
            r0.e()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.g():void");
    }

    @Override // androidx.appcompat.app.z
    public final boolean i(int i10) {
        if (i10 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i10 = 108;
        } else if (i10 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i10 = 109;
        }
        if (this.T && i10 == 108) {
            return false;
        }
        if (this.P && i10 == 1) {
            this.P = false;
        }
        if (i10 == 1) {
            J();
            this.T = true;
            return true;
        }
        if (i10 == 2) {
            J();
            this.N = true;
            return true;
        }
        if (i10 == 5) {
            J();
            this.O = true;
            return true;
        }
        if (i10 == 10) {
            J();
            this.R = true;
            return true;
        }
        if (i10 == 108) {
            J();
            this.P = true;
            return true;
        }
        if (i10 != 109) {
            return this.f1190v.requestFeature(i10);
        }
        J();
        this.Q = true;
        return true;
    }

    @Override // androidx.appcompat.app.z
    public final void j(int i10) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.K.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f1189u).inflate(i10, viewGroup);
        this.f1191w.a(this.f1190v.getCallback());
    }

    @Override // androidx.appcompat.app.z
    public final void k(View view) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.K.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f1191w.a(this.f1190v.getCallback());
    }

    @Override // androidx.appcompat.app.z
    public final void l(View view, ViewGroup.LayoutParams layoutParams) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.K.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f1191w.a(this.f1190v.getCallback());
    }

    @Override // androidx.appcompat.app.z
    public final void m(CharSequence charSequence) {
        this.A = charSequence;
        x1 x1Var = this.B;
        if (x1Var != null) {
            x1Var.setWindowTitle(charSequence);
            return;
        }
        f fVar = this.f1192y;
        if (fVar != null) {
            fVar.v(charSequence);
            return;
        }
        TextView textView = this.L;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01a4  */
    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.appcompat.app.u, java.lang.Object] */
    @Override // androidx.appcompat.app.z
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final n.b n(n.a r10) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.n(n.a):n.b");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(boolean r18, boolean r19) throws java.lang.IllegalAccessException {
        /*
            Method dump skipped, instruction units count: 636
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.o(boolean, boolean):boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:117:0x01e5
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    @Override // android.view.LayoutInflater.Factory2
    public final android.view.View onCreateView(android.view.View r9, java.lang.String r10, android.content.Context r11, android.util.AttributeSet r12) {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p(android.view.Window r8) {
        /*
            r7 = this;
            java.lang.String r0 = "AppCompat has already installed itself into the Window"
            android.view.Window r1 = r7.f1190v
            if (r1 != 0) goto L80
            android.view.Window$Callback r1 = r8.getCallback()
            boolean r2 = r1 instanceof androidx.appcompat.app.l0
            if (r2 != 0) goto L7a
            androidx.appcompat.app.l0 r0 = new androidx.appcompat.app.l0
            r0.<init>(r7, r1)
            r7.f1191w = r0
            r8.setCallback(r0)
            android.content.Context r0 = r7.f1189u
            int[] r1 = androidx.appcompat.app.s0.f1170s0
            r2 = 0
            android.content.res.TypedArray r1 = r0.obtainStyledAttributes(r2, r1)
            r3 = 0
            boolean r4 = r1.hasValue(r3)
            if (r4 == 0) goto L3f
            int r3 = r1.getResourceId(r3, r3)
            if (r3 == 0) goto L3f
            androidx.appcompat.widget.a0 r4 = androidx.appcompat.widget.a0.a()
            monitor-enter(r4)
            androidx.appcompat.widget.j3 r5 = r4.f1504a     // Catch: java.lang.Throwable -> L3c
            r6 = 1
            android.graphics.drawable.Drawable r0 = r5.f(r0, r3, r6)     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r4)
            goto L40
        L3c:
            r8 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3c
            throw r8
        L3f:
            r0 = r2
        L40:
            if (r0 == 0) goto L45
            r8.setBackgroundDrawable(r0)
        L45:
            r1.recycle()
            r7.f1190v = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r8 < r0) goto L79
            android.window.OnBackInvokedDispatcher r8 = r7.f1186p0
            if (r8 != 0) goto L79
            java.lang.Object r0 = r7.f1188t
            if (r8 == 0) goto L61
            android.window.OnBackInvokedCallback r1 = r7.f1187q0
            if (r1 == 0) goto L61
            androidx.appcompat.app.k0.c(r8, r1)
            r7.f1187q0 = r2
        L61:
            boolean r8 = r0 instanceof android.app.Activity
            if (r8 == 0) goto L74
            android.app.Activity r0 = (android.app.Activity) r0
            android.view.Window r8 = r0.getWindow()
            if (r8 == 0) goto L74
            android.window.OnBackInvokedDispatcher r8 = androidx.appcompat.app.k0.a(r0)
            r7.f1186p0 = r8
            goto L76
        L74:
            r7.f1186p0 = r2
        L76:
            r7.K()
        L79:
            return
        L7a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>(r0)
            throw r8
        L80:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.p(android.view.Window):void");
    }

    public final void r(int i10, q0 q0Var, androidx.appcompat.view.menu.q qVar) {
        if (qVar == null) {
            if (q0Var == null && i10 >= 0) {
                q0[] q0VarArr = this.V;
                if (i10 < q0VarArr.length) {
                    q0Var = q0VarArr[i10];
                }
            }
            if (q0Var != null) {
                qVar = q0Var.f1158h;
            }
        }
        if ((q0Var == null || q0Var.f1162m) && !this.f1173a0) {
            l0 l0Var = this.f1191w;
            Window.Callback callback = this.f1190v.getCallback();
            l0Var.getClass();
            try {
                l0Var.f1090o = true;
                callback.onPanelClosed(i10, qVar);
            } finally {
                l0Var.f1090o = false;
            }
        }
    }

    public final void s(androidx.appcompat.view.menu.q qVar) {
        if (this.U) {
            return;
        }
        this.U = true;
        this.B.i();
        Window.Callback callback = this.f1190v.getCallback();
        if (callback != null && !this.f1173a0) {
            callback.onPanelClosed(108, qVar);
        }
        this.U = false;
    }

    public final void t(q0 q0Var, boolean z) {
        p0 p0Var;
        x1 x1Var;
        if (z && q0Var.f1151a == 0 && (x1Var = this.B) != null && x1Var.e()) {
            s(q0Var.f1158h);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f1189u.getSystemService("window");
        if (windowManager != null && q0Var.f1162m && (p0Var = q0Var.f1155e) != null) {
            windowManager.removeView(p0Var);
            if (z) {
                r(q0Var.f1151a, q0Var, null);
            }
        }
        q0Var.k = false;
        q0Var.f1161l = false;
        q0Var.f1162m = false;
        q0Var.f1156f = null;
        q0Var.f1163n = true;
        if (this.W == q0Var) {
            this.W = null;
        }
        if (q0Var.f1151a == 0) {
            K();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean v(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.s0.v(android.view.KeyEvent):boolean");
    }

    public final void w(int i10) {
        q0 q0VarB = B(i10);
        if (q0VarB.f1158h != null) {
            Bundle bundle = new Bundle();
            q0VarB.f1158h.t(bundle);
            if (bundle.size() > 0) {
                q0VarB.f1165p = bundle;
            }
            q0VarB.f1158h.w();
            q0VarB.f1158h.clear();
        }
        q0VarB.f1164o = true;
        q0VarB.f1163n = true;
        if ((i10 == 108 || i10 == 0) && this.B != null) {
            q0 q0VarB2 = B(0);
            q0VarB2.k = false;
            I(q0VarB2, null);
        }
    }

    public final void x() {
        ViewGroup viewGroup;
        if (this.J) {
            return;
        }
        Context context = this.f1189u;
        int[] iArr = j.a.f19026j;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(117)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(126, false)) {
            i(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(117, false)) {
            i(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(118, false)) {
            i(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(119, false)) {
            i(10);
        }
        this.S = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        y();
        this.f1190v.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.T) {
            viewGroup = this.R ? (ViewGroup) layoutInflaterFrom.inflate(com.arvio.tv.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(com.arvio.tv.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.S) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(com.arvio.tv.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.Q = false;
            this.P = false;
        } else if (this.P) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(com.arvio.tv.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new n.e(context, typedValue.resourceId) : context).inflate(com.arvio.tv.R.layout.abc_screen_toolbar, (ViewGroup) null);
            x1 x1Var = (x1) viewGroup.findViewById(com.arvio.tv.R.id.decor_content_parent);
            this.B = x1Var;
            x1Var.setWindowCallback(this.f1190v.getCallback());
            if (this.Q) {
                this.B.h(109);
            }
            if (this.N) {
                this.B.h(2);
            }
            if (this.O) {
                this.B.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            StringBuilder sb2 = new StringBuilder("AppCompat does not support the current theme features: { windowActionBar: ");
            sb2.append(this.P);
            sb2.append(", windowActionBarOverlay: ");
            sb2.append(this.Q);
            sb2.append(", android:windowIsFloating: ");
            sb2.append(this.S);
            sb2.append(", windowActionModeOverlay: ");
            sb2.append(this.R);
            sb2.append(", windowNoTitle: ");
            throw new IllegalArgumentException(a0.c.m(" }", this.T, sb2));
        }
        b0 b0Var = new b0(this);
        WeakHashMap weakHashMap = b2.f2200a;
        androidx.core.view.k1.u(viewGroup, b0Var);
        if (this.B == null) {
            this.L = (TextView) viewGroup.findViewById(com.arvio.tv.R.id.title);
        }
        Method method = w4.f1826a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", null);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, null);
        } catch (IllegalAccessException e5) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e5);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e6) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e6);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.arvio.tv.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f1190v.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f1190v.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new c0(this));
        this.K = viewGroup;
        Object obj = this.f1188t;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.A;
        if (!TextUtils.isEmpty(title)) {
            x1 x1Var2 = this.B;
            if (x1Var2 != null) {
                x1Var2.setWindowTitle(title);
            } else {
                f fVar = this.f1192y;
                if (fVar != null) {
                    fVar.v(title);
                } else {
                    TextView textView = this.L;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.K.findViewById(R.id.content);
        View decorView = this.f1190v.getDecorView();
        contentFrameLayout2.f1448q.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap weakHashMap2 = b2.f2200a;
        if (contentFrameLayout2.isLaidOut()) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(iArr);
        typedArrayObtainStyledAttributes2.getValue(124, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(125, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(122)) {
            typedArrayObtainStyledAttributes2.getValue(122, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(123)) {
            typedArrayObtainStyledAttributes2.getValue(123, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(MenuKt.InTransitionDuration)) {
            typedArrayObtainStyledAttributes2.getValue(MenuKt.InTransitionDuration, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(121)) {
            typedArrayObtainStyledAttributes2.getValue(121, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.J = true;
        q0 q0VarB = B(0);
        if (this.f1173a0 || q0VarB.f1158h != null) {
            return;
        }
        D(108);
    }

    public final void y() {
        if (this.f1190v == null) {
            Object obj = this.f1188t;
            if (obj instanceof Activity) {
                p(((Activity) obj).getWindow());
            }
        }
        if (this.f1190v == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final o0 z(Context context) {
        if (this.f1177g0 == null) {
            if (i1.f1057o == null) {
                Context applicationContext = context.getApplicationContext();
                i1.f1057o = new i1(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.f1177g0 = new m0(this, i1.f1057o);
        }
        return this.f1177g0;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
