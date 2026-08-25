package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import t.l0;
import t.r;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2154a = new r(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadPoolExecutor f2155b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2156c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final l0 f2157d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new n());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f2155b = threadPoolExecutor;
        f2156c = new Object();
        f2157d = new l0(0);
    }

    public static h a(String str, Context context, e eVar, int i10) {
        r rVar = f2154a;
        Typeface typeface = (Typeface) rVar.get(str);
        if (typeface != null) {
            return new h(typeface);
        }
        try {
            j jVarA = d.a(context, eVar);
            k[] kVarArr = (k[]) jVarA.f2160c;
            int i11 = jVarA.f2159b;
            int i12 = 1;
            if (i11 != 0) {
                i12 = i11 != 1 ? -3 : -2;
            } else if (kVarArr != null && kVarArr.length != 0) {
                int length = kVarArr.length;
                i12 = 0;
                int i13 = 0;
                while (true) {
                    if (i13 >= length) {
                        break;
                    }
                    int i14 = kVarArr[i13].f2165e;
                    if (i14 == 0) {
                        i13++;
                    } else if (i14 >= 0) {
                        i12 = i14;
                    }
                }
            }
            if (i12 != 0) {
                return new h(i12);
            }
            Typeface typefaceG = androidx.core.graphics.g.f2103a.g(context, kVarArr, i10);
            if (typefaceG == null) {
                return new h(-3);
            }
            rVar.put(str, typefaceG);
            return new h(typefaceG);
        } catch (PackageManager.NameNotFoundException unused) {
            return new h(-1);
        }
    }
}
