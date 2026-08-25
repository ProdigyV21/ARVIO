package androidx.mediarouter.media;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static f f4425c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4427b = new ArrayList();

    static {
        Log.isLoggable("AxMediaRouter", 3);
    }

    public k0(Context context) {
        this.f4426a = context;
    }

    public static void b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static f c() {
        f fVar = f4425c;
        if (fVar != null) {
            return fVar;
        }
        throw new IllegalStateException("getGlobalRouter cannot be called when sGlobal is null");
    }

    public static k0 d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        b();
        if (f4425c == null) {
            f4425c = new f(context.getApplicationContext());
        }
        ArrayList arrayList = f4425c.f4334i;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                k0 k0Var = new k0(context);
                arrayList.add(new WeakReference(k0Var));
                return k0Var;
            }
            k0 k0Var2 = (k0) ((WeakReference) arrayList.get(size)).get();
            if (k0Var2 == null) {
                arrayList.remove(size);
            } else if (k0Var2.f4426a == context) {
                return k0Var2;
            }
        }
    }

    public static void f(v1 v1Var) {
        b();
        l lVar = c().f4343s;
        if (lVar == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        androidx.activity.a.d(lVar.f4429s, v1Var != null ? androidx.activity.a.f(v1Var) : null);
    }

    public static void g(int i10) {
        if (i10 < 0 || i10 > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        b();
        f fVarC = c();
        i0 i0VarC = fVarC.c();
        if (fVarC.g() != i0VarC) {
            fVarC.l(i0VarC, i10, true);
        }
    }

    public final void a(z zVar, a0 a0Var, int i10) {
        b0 b0Var;
        z zVar2;
        if (zVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (a0Var == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        b();
        ArrayList arrayList = this.f4427b;
        int size = arrayList.size();
        boolean z = false;
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                i11 = -1;
                break;
            } else if (((b0) arrayList.get(i11)).f4300b == a0Var) {
                break;
            } else {
                i11++;
            }
        }
        if (i11 < 0) {
            b0Var = new b0(this, a0Var);
            arrayList.add(b0Var);
        } else {
            b0Var = (b0) arrayList.get(i11);
        }
        boolean z5 = true;
        if (i10 != b0Var.f4302d) {
            b0Var.f4302d = i10;
            z = true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if ((i10 & 1) != 0) {
            z = true;
        }
        b0Var.f4303e = jElapsedRealtime;
        z zVar3 = b0Var.f4301c;
        zVar3.a();
        zVar.a();
        if (zVar3.f4518b.containsAll(zVar.f4518b)) {
            z5 = z;
        } else {
            z zVar4 = b0Var.f4301c;
            if (zVar4 == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            zVar4.a();
            ArrayList<String> arrayList2 = !zVar4.f4518b.isEmpty() ? new ArrayList<>(zVar4.f4518b) : null;
            ArrayList<String> arrayListC = zVar.c();
            if (!arrayListC.isEmpty()) {
                for (String str : arrayListC) {
                    if (str == null) {
                        throw new IllegalArgumentException("category must not be null");
                    }
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList<>();
                    }
                    if (!arrayList2.contains(str)) {
                        arrayList2.add(str);
                    }
                }
            }
            if (arrayList2 == null) {
                zVar2 = z.f4516c;
            } else {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("controlCategories", arrayList2);
                zVar2 = new z(bundle, arrayList2);
            }
            b0Var.f4301c = zVar2;
        }
        if (z5) {
            c().n();
        }
    }

    public final void e(a0 a0Var) {
        if (a0Var == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        b();
        ArrayList arrayList = this.f4427b;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                i10 = -1;
                break;
            } else if (((b0) arrayList.get(i10)).f4300b == a0Var) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 >= 0) {
            arrayList.remove(i10);
            c().n();
        }
    }
}
