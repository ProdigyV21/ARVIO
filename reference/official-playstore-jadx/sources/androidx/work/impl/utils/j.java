package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f7042a = m2.t.f("PackageManagerHelper");

    public static void a(Context context, Class cls, boolean z) {
        String str = f7042a;
        try {
            int componentEnabledSetting = context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, cls.getName()));
            boolean z5 = false;
            if (componentEnabledSetting != 0 && componentEnabledSetting == 1) {
                z5 = true;
            }
            if (z == z5) {
                m2.t.d().a(str, "Skipping component enablement for ".concat(cls.getName()));
                return;
            }
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            m2.t tVarD = m2.t.d();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cls.getName());
            sb2.append(" ");
            sb2.append(z ? "enabled" : "disabled");
            tVarD.a(str, sb2.toString());
        } catch (Exception e5) {
            m2.t tVarD2 = m2.t.d();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(cls.getName());
            sb3.append("could not be ");
            sb3.append(z ? "enabled" : "disabled");
            String string = sb3.toString();
            if (tVarD2.f20216a <= 3) {
                Log.d(str, string, e5);
            }
        }
    }
}
