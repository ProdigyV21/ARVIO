package com.arflix.tv.core.plugin;

import com.google.android.gms.cast.CredentialsData;
import io.ktor.http.ContentDisposition;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/arflix/tv/core/plugin/PluginSafety;", "", "<init>", "()V", "BLOCKED_PACKAGES", "", "", "ALLOWED_EXTENSIONS", "isSafeToLoad", "", "pluginName", "pluginPackage", ContentDisposition.Parameters.FileName, "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginSafety {
    public static final int $stable = 0;
    public static final PluginSafety INSTANCE = new PluginSafety();
    private static final Set<String> BLOCKED_PACKAGES = r.p0(new String[]{"com.google", CredentialsData.CREDENTIALS_TYPE_ANDROID, "java", "javax", "kotlin", "com.arflix.tv.core"});
    private static final Set<String> ALLOWED_EXTENSIONS = r.p0(new String[]{"cs3", "apk", "dex", "js"});

    private PluginSafety() {
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isSafeToLoad(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L86
            boolean r4 = kotlin.text.o.h0(r4)
            if (r4 == 0) goto Lb
            goto L86
        Lb:
            if (r6 == 0) goto L86
            boolean r4 = kotlin.text.o.h0(r6)
            if (r4 == 0) goto L15
            goto L86
        L15:
            r4 = 46
            java.lang.String r1 = ""
            java.lang.String r4 = kotlin.text.o.E0(r4, r6, r1)
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r4 = r4.toLowerCase(r1)
            java.util.Set<java.lang.String> r1 = com.arflix.tv.core.plugin.PluginSafety.ALLOWED_EXTENSIONS
            boolean r4 = r1.contains(r4)
            if (r4 != 0) goto L2c
            goto L86
        L2c:
            r4 = 1
            if (r5 == 0) goto L58
            java.util.Set<java.lang.String> r1 = com.arflix.tv.core.plugin.PluginSafety.BLOCKED_PACKAGES
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L41
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L41
            goto L58
        L41:
            java.util.Iterator r1 = r1.iterator()
        L45:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L58
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = kotlin.text.u.P(r5, r2, r4)
            if (r2 == 0) goto L45
            goto L86
        L58:
            java.io.File r5 = new java.io.File
            r5.<init>(r6)
            boolean r6 = r5.isAbsolute()
            if (r6 == 0) goto L64
            goto L86
        L64:
            java.io.File r5 = l7.i.q0(r5)
            java.lang.String r5 = r5.getPath()
            java.lang.String r6 = "\\"
            java.lang.String r1 = "/"
            java.lang.String r5 = kotlin.text.u.O(r5, r6, r1, r0)
            java.lang.String r6 = "../"
            boolean r6 = kotlin.text.u.P(r5, r6, r0)
            if (r6 != 0) goto L86
            java.lang.String r6 = ".."
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L85
            goto L86
        L85:
            return r4
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.core.plugin.PluginSafety.isSafeToLoad(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
