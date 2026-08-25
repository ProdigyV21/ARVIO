package io.sentry.internal.modules;

import io.sentry.ILogger;
import io.sentry.w5;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Pattern f17375e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Pattern f17376f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ClassLoader f17377g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ILogger iLogger) {
        super(iLogger);
        ClassLoader classLoader = c.class.getClassLoader();
        this.f17375e = Pattern.compile(".*/(.+)!/META-INF/MANIFEST.MF");
        this.f17376f = Pattern.compile("(.*?)-(\\d+\\.\\d+.*).jar");
        this.f17377g = f4.f.e(classLoader);
    }

    @Override // io.sentry.internal.modules.d
    public final Map b() {
        HashMap map = new HashMap();
        ArrayList<b> arrayList = new ArrayList();
        try {
            Enumeration<URL> resources = this.f17377g.getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                Matcher matcher = this.f17375e.matcher(resources.nextElement().toString());
                b bVar = null;
                String strGroup = (matcher.matches() && matcher.groupCount() == 1) ? matcher.group(1) : null;
                if (strGroup != null) {
                    Matcher matcher2 = this.f17376f.matcher(strGroup);
                    if (matcher2.matches() && matcher2.groupCount() == 2) {
                        bVar = new b(matcher2.group(1), matcher2.group(2));
                    }
                }
                if (bVar != null) {
                    arrayList.add(bVar);
                }
            }
        } catch (Throwable th) {
            this.f17379a.l(w5.ERROR, "Unable to detect modules via manifest files.", th);
        }
        for (b bVar2 : arrayList) {
            map.put(bVar2.f17373a, bVar2.f17374b);
        }
        return map;
    }
}
