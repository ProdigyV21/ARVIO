package androidx.core.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes3.dex */
public final class e3 extends h3 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f2222e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f2223f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Constructor f2224g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f2225h = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WindowInsets f2226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public androidx.core.graphics.c f2227d;

    public e3() {
        this.f2226c = i();
    }

    private static WindowInsets i() {
        if (!f2223f) {
            try {
                f2222e = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e5);
            }
            f2223f = true;
        }
        Field field = f2222e;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e6) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e6);
            }
        }
        if (!f2225h) {
            try {
                f2224g = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e10) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e10);
            }
            f2225h = true;
        }
        Constructor constructor = f2224g;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e11) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e11);
            }
        }
        return null;
    }

    @Override // androidx.core.view.h3
    public q3 b() {
        a();
        q3 q3VarC = q3.c(null, this.f2226c);
        androidx.core.graphics.c[] cVarArr = this.f2245b;
        n3 n3Var = q3VarC.f2300a;
        n3Var.q(cVarArr);
        n3Var.s(this.f2227d);
        return q3VarC;
    }

    @Override // androidx.core.view.h3
    public void e(androidx.core.graphics.c cVar) {
        this.f2227d = cVar;
    }

    @Override // androidx.core.view.h3
    public void g(androidx.core.graphics.c cVar) {
        WindowInsets windowInsets = this.f2226c;
        if (windowInsets != null) {
            this.f2226c = windowInsets.replaceSystemWindowInsets(cVar.f2086a, cVar.f2087b, cVar.f2088c, cVar.f2089d);
        }
    }

    public e3(q3 q3Var) {
        super(q3Var);
        this.f2226c = q3Var.b();
    }
}
