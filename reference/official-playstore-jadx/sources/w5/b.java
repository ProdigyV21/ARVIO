package w5;

import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f22383b;

    public b(String str, a aVar) {
        String str2 = Build.MODEL;
        String str3 = Build.VERSION.RELEASE;
        this.f22382a = str;
        this.f22383b = aVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!kotlin.jvm.internal.p.a(this.f22382a, bVar.f22382a)) {
            return false;
        }
        String str = Build.MODEL;
        if (!kotlin.jvm.internal.p.a(str, str)) {
            return false;
        }
        String str2 = Build.VERSION.RELEASE;
        return kotlin.jvm.internal.p.a(str2, str2) && this.f22383b.equals(bVar.f22383b);
    }

    public final int hashCode() {
        return this.f22383b.hashCode() + ((p.LOG_ENVIRONMENT_PROD.hashCode() + androidx.compose.foundation.c.c((((Build.MODEL.hashCode() + (this.f22382a.hashCode() * 31)) * 31) + 46672439) * 31, 31, Build.VERSION.RELEASE)) * 31);
    }

    public final String toString() {
        return "ApplicationInfo(appId=" + this.f22382a + ", deviceModel=" + Build.MODEL + ", sessionSdkVersion=1.2.0, osVersion=" + Build.VERSION.RELEASE + ", logEnvironment=" + p.LOG_ENVIRONMENT_PROD + ", androidAppInfo=" + this.f22383b + ')';
    }
}
