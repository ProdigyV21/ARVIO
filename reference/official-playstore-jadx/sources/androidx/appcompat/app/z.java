package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final z0 f1211i = new z0(new a1(0));

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int f1212l = -100;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static androidx.core.os.m f1213m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static androidx.core.os.m f1214n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static Boolean f1215o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static boolean f1216p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t.f f1217q = new t.f(0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Object f1218r = new Object();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object f1219s = new Object();

    public static boolean d(Context context) {
        if (f1215o == null) {
            try {
                int i10 = y0.f1210i;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) y0.class), Build.VERSION.SDK_INT >= 24 ? x0.a() | 128 : 640).metaData;
                if (bundle != null) {
                    f1215o = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                f1215o = Boolean.FALSE;
            }
        }
        return f1215o.booleanValue();
    }

    public static void h(s0 s0Var) {
        synchronized (f1218r) {
            try {
                t.f fVar = f1217q;
                fVar.getClass();
                t.a aVar = new t.a(fVar);
                while (aVar.hasNext()) {
                    z zVar = (z) ((WeakReference) aVar.next()).get();
                    if (zVar == s0Var || zVar == null) {
                        aVar.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void a();

    public abstract void c();

    public abstract void e();

    public abstract void g();

    public abstract boolean i(int i10);

    public abstract void j(int i10);

    public abstract void k(View view);

    public abstract void l(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void m(CharSequence charSequence);

    public abstract n.b n(n.a aVar);
}
