package w5;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f22380a = new a0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a8.e f22381b;

    static {
        i5.d dVar = new i5.d();
        dVar.a(z.class, g.f22432a);
        dVar.a(i0.class, h.f22437a);
        dVar.a(j.class, e.f22412a);
        dVar.a(b.class, d.f22394a);
        dVar.a(a.class, c.f22384a);
        dVar.a(q.class, f.f22420a);
        dVar.f16225d = true;
        f22381b = new a8.e(dVar, 22);
    }

    public static b a(z4.g gVar) {
        gVar.a();
        Context context = gVar.f23184a;
        String packageName = context.getPackageName();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        String strValueOf = Build.VERSION.SDK_INT >= 28 ? String.valueOf(packageInfo.getLongVersionCode()) : String.valueOf(packageInfo.versionCode);
        gVar.a();
        String str = gVar.f23186c.f23200b;
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        String str4 = packageInfo.versionName;
        if (str4 == null) {
            str4 = strValueOf;
        }
        String str5 = Build.MANUFACTURER;
        gVar.a();
        q qVarB = r.b(context);
        gVar.a();
        return new b(str, new a(packageName, str4, strValueOf, qVarB, r.a(context)));
    }
}
