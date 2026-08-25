package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.media3.common.C;
import com.arvio.tv.R;
import com.google.android.gms.cast.CredentialsData;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class t4 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static t4 f1750u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static t4 f1751v;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View f1752i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final CharSequence f1753l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1754m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final s4 f1755n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final s4 f1756o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1757p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1758q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public u4 f1759r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1760s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1761t;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.appcompat.widget.s4] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.widget.s4] */
    public t4(View view, CharSequence charSequence) {
        final int i10 = 0;
        this.f1755n = new Runnable(this) { // from class: androidx.appcompat.widget.s4

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ t4 f1742l;

            {
                this.f1742l = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i10) {
                    case 0:
                        this.f1742l.c(false);
                        break;
                    default:
                        this.f1742l.a();
                        break;
                }
            }
        };
        final int i11 = 1;
        this.f1756o = new Runnable(this) { // from class: androidx.appcompat.widget.s4

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ t4 f1742l;

            {
                this.f1742l = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i11) {
                    case 0:
                        this.f1742l.c(false);
                        break;
                    default:
                        this.f1742l.a();
                        break;
                }
            }
        };
        this.f1752i = view;
        this.f1753l = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        Method method = androidx.core.view.f2.f2233a;
        this.f1754m = Build.VERSION.SDK_INT >= 28 ? androidx.core.view.d2.a(viewConfiguration) : viewConfiguration.getScaledTouchSlop() / 2;
        this.f1761t = true;
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void b(t4 t4Var) {
        t4 t4Var2 = f1750u;
        if (t4Var2 != null) {
            t4Var2.f1752i.removeCallbacks(t4Var2.f1755n);
        }
        f1750u = t4Var;
        if (t4Var != null) {
            t4Var.f1752i.postDelayed(t4Var.f1755n, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        t4 t4Var = f1751v;
        View view = this.f1752i;
        if (t4Var == this) {
            f1751v = null;
            u4 u4Var = this.f1759r;
            if (u4Var != null) {
                View view2 = (View) u4Var.f1774b;
                if (view2.getParent() != null) {
                    ((WindowManager) ((Context) u4Var.f1773a).getSystemService("window")).removeView(view2);
                }
                this.f1759r = null;
                this.f1761t = true;
                view.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1750u == this) {
            b(null);
        }
        view.removeCallbacks(this.f1756o);
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [int[], java.io.Serializable] */
    public final void c(boolean z) {
        int height;
        int i10;
        int i11;
        boolean z5;
        int i12;
        int i13;
        long longPressTimeout;
        long j10;
        long j11;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        View view = this.f1752i;
        if (view.isAttachedToWindow()) {
            b(null);
            t4 t4Var = f1751v;
            if (t4Var != null) {
                t4Var.a();
            }
            f1751v = this;
            this.f1760s = z;
            Context context = view.getContext();
            u4 u4Var = new u4();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            u4Var.f1776d = layoutParams;
            u4Var.f1777e = new Rect();
            u4Var.f1778f = new int[2];
            u4Var.f1779g = new int[2];
            u4Var.f1773a = context;
            View viewInflate = LayoutInflater.from(context).inflate(R.layout.abc_tooltip, (ViewGroup) null);
            u4Var.f1774b = viewInflate;
            u4Var.f1775c = (TextView) viewInflate.findViewById(R.id.message);
            layoutParams.setTitle(u4.class.getSimpleName());
            layoutParams.packageName = context.getPackageName();
            layoutParams.type = 1002;
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
            layoutParams.flags = 24;
            View view2 = (View) u4Var.f1774b;
            Context context2 = (Context) u4Var.f1773a;
            this.f1759r = u4Var;
            int width = this.f1757p;
            int i14 = this.f1758q;
            boolean z10 = this.f1760s;
            WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) u4Var.f1776d;
            if (view2.getParent() != null && view2.getParent() != null) {
                ((WindowManager) context2.getSystemService("window")).removeView(view2);
            }
            ((TextView) u4Var.f1775c).setText(this.f1753l);
            int[] iArr = (int[]) u4Var.f1779g;
            int[] iArr2 = (int[]) u4Var.f1778f;
            Rect rect = (Rect) u4Var.f1777e;
            layoutParams2.token = view.getApplicationWindowToken();
            int dimensionPixelOffset = context2.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
            if (view.getWidth() < dimensionPixelOffset) {
                width = view.getWidth() / 2;
            }
            if (view.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = context2.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
                height = i14 + dimensionPixelOffset2;
                i10 = i14 - dimensionPixelOffset2;
            } else {
                height = view.getHeight();
                i10 = 0;
            }
            layoutParams2.gravity = 49;
            int dimensionPixelOffset3 = context2.getResources().getDimensionPixelOffset(z10 ? R.dimen.tooltip_y_offset_touch : R.dimen.tooltip_y_offset_non_touch);
            View rootView = view.getRootView();
            ViewGroup.LayoutParams layoutParams3 = rootView.getLayoutParams();
            int i15 = width;
            if (!(layoutParams3 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams3).type != 2) {
                Context context3 = view.getContext();
                while (true) {
                    if (!(context3 instanceof ContextWrapper)) {
                        break;
                    }
                    if (context3 instanceof Activity) {
                        rootView = ((Activity) context3).getWindow().getDecorView();
                        break;
                    }
                    context3 = ((ContextWrapper) context3).getBaseContext();
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
                i13 = 1;
            } else {
                rootView.getWindowVisibleDisplayFrame(rect);
                if (rect.left >= 0 || rect.top >= 0) {
                    i11 = i10;
                    z5 = z10;
                    i12 = 0;
                    i13 = 1;
                } else {
                    Resources resources = context2.getResources();
                    i13 = 1;
                    i11 = i10;
                    z5 = z10;
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", CredentialsData.CREDENTIALS_TYPE_ANDROID);
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    i12 = 0;
                    rect.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen(iArr);
                view.getLocationOnScreen(iArr2);
                int i16 = iArr2[i12] - iArr[i12];
                iArr2[i12] = i16;
                iArr2[i13] = iArr2[i13] - iArr[i13];
                layoutParams2.x = (i16 + i15) - (rootView.getWidth() / 2);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12, i12);
                view2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredHeight = view2.getMeasuredHeight();
                int i17 = iArr2[i13];
                int i18 = ((i17 + i11) - dimensionPixelOffset3) - measuredHeight;
                int i19 = i17 + height + dimensionPixelOffset3;
                if (z5) {
                    if (i18 >= 0) {
                        layoutParams2.y = i18;
                    } else {
                        layoutParams2.y = i19;
                    }
                } else if (measuredHeight + i19 <= rect.height()) {
                    layoutParams2.y = i19;
                } else {
                    layoutParams2.y = i18;
                }
            }
            ((WindowManager) context2.getSystemService("window")).addView(view2, layoutParams2);
            view.addOnAttachStateChangeListener(this);
            if (this.f1760s) {
                j11 = 2500;
            } else {
                if ((view.getWindowSystemUiVisibility() & 1) == i13) {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j10 = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
                } else {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j10 = 15000;
                }
                j11 = j10 - longPressTimeout;
            }
            s4 s4Var = this.f1756o;
            view.removeCallbacks(s4Var);
            view.postDelayed(s4Var, j11);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    @Override // android.view.View.OnHoverListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onHover(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            androidx.appcompat.widget.u4 r4 = r3.f1759r
            r0 = 0
            if (r4 == 0) goto La
            boolean r4 = r3.f1760s
            if (r4 == 0) goto La
            goto L6f
        La:
            android.view.View r4 = r3.f1752i
            android.content.Context r1 = r4.getContext()
            java.lang.String r2 = "accessibility"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.view.accessibility.AccessibilityManager r1 = (android.view.accessibility.AccessibilityManager) r1
            boolean r2 = r1.isEnabled()
            if (r2 == 0) goto L25
            boolean r1 = r1.isTouchExplorationEnabled()
            if (r1 == 0) goto L25
            goto L6f
        L25:
            int r1 = r5.getAction()
            r2 = 7
            if (r1 == r2) goto L38
            r4 = 10
            if (r1 == r4) goto L31
            goto L6f
        L31:
            r4 = 1
            r3.f1761t = r4
            r3.a()
            return r0
        L38:
            boolean r4 = r4.isEnabled()
            if (r4 == 0) goto L6f
            androidx.appcompat.widget.u4 r4 = r3.f1759r
            if (r4 != 0) goto L6f
            float r4 = r5.getX()
            int r4 = (int) r4
            float r5 = r5.getY()
            int r5 = (int) r5
            boolean r1 = r3.f1761t
            if (r1 != 0) goto L66
            int r1 = r3.f1757p
            int r1 = r4 - r1
            int r1 = java.lang.Math.abs(r1)
            int r2 = r3.f1754m
            if (r1 > r2) goto L66
            int r1 = r3.f1758q
            int r1 = r5 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r1 <= r2) goto L6f
        L66:
            r3.f1757p = r4
            r3.f1758q = r5
            r3.f1761t = r0
            b(r3)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.t4.onHover(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.f1757p = view.getWidth() / 2;
        this.f1758q = view.getHeight() / 2;
        c(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        a();
    }
}
