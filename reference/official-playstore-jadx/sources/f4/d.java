package f4;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.common.api.internal.f1;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f15255c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f15256d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f15257e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f15258f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Boolean f15259g;
    public static l k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static m f15263l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f15264a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ThreadLocal f15260h = new ThreadLocal();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f1 f15261i = new f1(3);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final h f15262j = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final i f15254b = new i();

    public d(Context context) {
        this.f15264a = context;
    }

    public static int a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb2 = new StringBuilder(str.length() + 61);
            sb2.append("com.google.android.gms.dynamite.descriptors.");
            sb2.append(str);
            sb2.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb2.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (t.l(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb3 = new StringBuilder(strValueOf.length() + 50 + str.length() + 1);
            sb3.append("Module descriptor id '");
            sb3.append(strValueOf);
            sb3.append("' didn't match expected id '");
            sb3.append(str);
            sb3.append("'");
            Log.e("DynamiteModule", sb3.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb4 = new StringBuilder(str.length() + 45);
            sb4.append("Local module descriptor class for ");
            sb4.append(str);
            sb4.append(" not found.");
            Log.w("DynamiteModule", sb4.toString());
            return 0;
        } catch (Exception e5) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e5.getMessage())));
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02de A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02e4 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02ed A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b7 A[Catch: all -> 0x00ac, TRY_LEAVE, TryCatch #3 {all -> 0x00ac, blocks: (B:5:0x0042, B:9:0x00a5, B:16:0x00b1, B:19:0x00b7, B:31:0x00e2, B:119:0x027b, B:120:0x0282, B:128:0x0291, B:130:0x02b9, B:132:0x02c8, B:142:0x02f6, B:143:0x02fd, B:123:0x0285, B:124:0x0286, B:125:0x028d, B:144:0x02fe, B:145:0x031e, B:146:0x031f, B:147:0x0364), top: B:161:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00df  */
    /* JADX WARN: Type inference failed for: r30v0, types: [f4.b] */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static f4.d b(android.content.Context r29, f4.b r30) throws com.google.android.gms.dynamite.DynamiteModule$LoadingException {
        /*
            Method dump skipped, instruction units count: 905
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f4.d.b(android.content.Context, f4.b):f4.d");
    }

    public static boolean c(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals(null) || bool.equals(f15259g)) {
            return true;
        }
        boolean z = false;
        if (f15259g == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", Build.VERSION.SDK_INT >= 29 ? 268435456 : 0);
            if (o3.f.f20743b.b(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                z = true;
            }
            f15259g = Boolean.valueOf(z);
            if (z && (applicationInfo = providerInfoResolveContentProvider.applicationInfo) != null && (applicationInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                f15257e = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:124:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x013a A[PHI: r3
      0x013a: PHI (r3v7 boolean) = (r3v6 boolean), (r3v9 boolean) binds: [B:58:0x00f1, B:83:0x0137] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(android.content.Context r14, boolean r15, boolean r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f4.d.d(android.content.Context, boolean, boolean):int");
    }

    public static void e(ClassLoader classLoader) throws DynamiteModule$LoadingException {
        try {
            m mVar = null;
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder != null) {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                mVar = iInterfaceQueryLocalInterface instanceof m ? (m) iInterfaceQueryLocalInterface : new m(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2", 1);
            }
            f15263l = mVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e5) {
            throw new DynamiteModule$LoadingException("Failed to instantiate dynamite loader", e5);
        }
    }

    public static l f(Context context) {
        l lVar;
        synchronized (d.class) {
            l lVar2 = k;
            if (lVar2 != null) {
                return lVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    lVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    lVar = iInterfaceQueryLocalInterface instanceof l ? (l) iInterfaceQueryLocalInterface : new l(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader", 1);
                }
                if (lVar != null) {
                    k = lVar;
                    return lVar;
                }
            } catch (Exception e5) {
                String message = e5.getMessage();
                StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 45);
                sb2.append("Failed to load IDynamiteLoader from GmsCore: ");
                sb2.append(message);
                Log.e("DynamiteModule", sb2.toString());
            }
            return null;
        }
    }
}
