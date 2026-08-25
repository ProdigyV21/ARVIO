package z4;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f23199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f23200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f23201c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f23202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f23203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f23204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f23205g;

    public j(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int i10 = c4.i.f7411a;
        t.k("ApplicationId must be set.", true ^ (str == null || str.trim().isEmpty()));
        this.f23200b = str;
        this.f23199a = str2;
        this.f23201c = str3;
        this.f23202d = str4;
        this.f23203e = str5;
        this.f23204f = str6;
        this.f23205g = str7;
    }

    public static j a(Context context) {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(context, 18);
        String strZ = cVar.z("google_app_id");
        if (TextUtils.isEmpty(strZ)) {
            return null;
        }
        return new j(strZ, cVar.z("google_api_key"), cVar.z("firebase_database_url"), cVar.z("ga_trackingId"), cVar.z("gcm_defaultSenderId"), cVar.z("google_storage_bucket"), cVar.z("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return t.l(this.f23200b, jVar.f23200b) && t.l(this.f23199a, jVar.f23199a) && t.l(this.f23201c, jVar.f23201c) && t.l(this.f23202d, jVar.f23202d) && t.l(this.f23203e, jVar.f23203e) && t.l(this.f23204f, jVar.f23204f) && t.l(this.f23205g, jVar.f23205g);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f23200b, this.f23199a, this.f23201c, this.f23202d, this.f23203e, this.f23204f, this.f23205g});
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(this.f23200b, "applicationId");
        cVar.w(this.f23199a, "apiKey");
        cVar.w(this.f23201c, "databaseUrl");
        cVar.w(this.f23203e, "gcmSenderId");
        cVar.w(this.f23204f, "storageBucket");
        cVar.w(this.f23205g, "projectId");
        return cVar.toString();
    }
}
