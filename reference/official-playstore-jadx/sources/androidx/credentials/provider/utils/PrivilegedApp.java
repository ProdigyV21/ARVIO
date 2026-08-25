package androidx.credentials.provider.utils;

import android.os.Build;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import q7.n;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/credentials/provider/utils/PrivilegedApp;", "", "packageName", "", "fingerprints", "", "(Ljava/lang/String;Ljava/util/Set;)V", "getFingerprints", "()Ljava/util/Set;", "getPackageName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PrivilegedApp {
    private static final String ANDROID_TYPE_KEY = "android";
    private static final String APPS_KEY = "apps";
    private static final String APP_INFO_KEY = "info";
    private static final String BUILD_KEY = "build";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FINGERPRINT_KEY = "cert_fingerprint_sha256";
    private static final String PACKAGE_NAME_KEY = "package_name";
    private static final String SIGNATURES_KEY = "signatures";
    private static final String TYPE_KEY = "type";
    private static final String USER_BUILD_TYPE = "userdebug";
    private static final String USER_DEBUG_KEY = "userdebug";
    private final Set<String> fingerprints;
    private final String packageName;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/provider/utils/PrivilegedApp$Companion;", "", "()V", "ANDROID_TYPE_KEY", "", "APPS_KEY", "APP_INFO_KEY", "BUILD_KEY", "FINGERPRINT_KEY", "PACKAGE_NAME_KEY", "SIGNATURES_KEY", "TYPE_KEY", "USER_BUILD_TYPE", "USER_DEBUG_KEY", "createFromJSONObject", "Landroidx/credentials/provider/utils/PrivilegedApp;", "appInfoJsonObject", "Lorg/json/JSONObject;", "filterUserDebug", "", "extractPrivilegedApps", "", "jsonObject", "extractPrivilegedApps$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @n
        public final PrivilegedApp createFromJSONObject(JSONObject appInfoJsonObject, boolean filterUserDebug) throws JSONException {
            JSONArray jSONArray = appInfoJsonObject.getJSONArray(PrivilegedApp.SIGNATURES_KEY);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                if (!filterUserDebug || !"userdebug".equals(jSONArray.getJSONObject(i10).optString(PrivilegedApp.BUILD_KEY)) || "userdebug".equals(Build.TYPE)) {
                    linkedHashSet.add(jSONArray.getJSONObject(i10).getString(PrivilegedApp.FINGERPRINT_KEY));
                }
            }
            return new PrivilegedApp(appInfoJsonObject.getString(PrivilegedApp.PACKAGE_NAME_KEY), linkedHashSet);
        }

        @n
        public final List<PrivilegedApp> extractPrivilegedApps$credentials_release(JSONObject jsonObject) throws JSONException {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jsonObject.getJSONArray(PrivilegedApp.APPS_KEY);
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                if (p.a(jSONObject.getString("type"), "android")) {
                    arrayList.add(createFromJSONObject(jSONObject.getJSONObject(PrivilegedApp.APP_INFO_KEY), true));
                }
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    public PrivilegedApp(String str, Set<String> set) {
        this.packageName = str;
        this.fingerprints = set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PrivilegedApp copy$default(PrivilegedApp privilegedApp, String str, Set set, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = privilegedApp.packageName;
        }
        if ((i10 & 2) != 0) {
            set = privilegedApp.fingerprints;
        }
        return privilegedApp.copy(str, set);
    }

    @n
    public static final PrivilegedApp createFromJSONObject(JSONObject jSONObject, boolean z) {
        return INSTANCE.createFromJSONObject(jSONObject, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    public final Set<String> component2() {
        return this.fingerprints;
    }

    public final PrivilegedApp copy(String packageName, Set<String> fingerprints) {
        return new PrivilegedApp(packageName, fingerprints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrivilegedApp)) {
            return false;
        }
        PrivilegedApp privilegedApp = (PrivilegedApp) other;
        return p.a(this.packageName, privilegedApp.packageName) && p.a(this.fingerprints, privilegedApp.fingerprints);
    }

    public final Set<String> getFingerprints() {
        return this.fingerprints;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        return this.fingerprints.hashCode() + (this.packageName.hashCode() * 31);
    }

    public String toString() {
        return "PrivilegedApp(packageName=" + this.packageName + ", fingerprints=" + this.fingerprints + ')';
    }
}
