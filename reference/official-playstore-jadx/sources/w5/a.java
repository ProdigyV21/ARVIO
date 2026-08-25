package w5;

import android.os.Build;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f22377c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q f22378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f22379e;

    public a(String str, String str2, String str3, q qVar, ArrayList arrayList) {
        String str4 = Build.MANUFACTURER;
        this.f22375a = str;
        this.f22376b = str2;
        this.f22377c = str3;
        this.f22378d = qVar;
        this.f22379e = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!this.f22375a.equals(aVar.f22375a) || !kotlin.jvm.internal.p.a(this.f22376b, aVar.f22376b) || !kotlin.jvm.internal.p.a(this.f22377c, aVar.f22377c)) {
            return false;
        }
        String str = Build.MANUFACTURER;
        return kotlin.jvm.internal.p.a(str, str) && this.f22378d.equals(aVar.f22378d) && this.f22379e.equals(aVar.f22379e);
    }

    public final int hashCode() {
        return this.f22379e.hashCode() + ((this.f22378d.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.f22375a.hashCode() * 31, 31, this.f22376b), 31, this.f22377c), 31, Build.MANUFACTURER)) * 31);
    }

    public final String toString() {
        return "AndroidApplicationInfo(packageName=" + this.f22375a + ", versionName=" + this.f22376b + ", appBuildVersion=" + this.f22377c + ", deviceManufacturer=" + Build.MANUFACTURER + ", currentProcessDetails=" + this.f22378d + ", appProcessDetails=" + this.f22379e + ')';
    }
}
