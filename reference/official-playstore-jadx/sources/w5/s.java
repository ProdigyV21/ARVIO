package w5;

import android.util.Base64;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f22485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f22486b;

    static {
        String strEncodeToString = Base64.encodeToString(r.c().getBytes(kotlin.text.a.f19924a), 10);
        f22485a = a0.c.l("firebase_session_", strEncodeToString, "_data");
        f22486b = a0.c.l("firebase_session_", strEncodeToString, "_settings");
    }
}
