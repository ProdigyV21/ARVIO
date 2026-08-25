package io.sentry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* JADX INFO: loaded from: classes4.dex */
public final class u implements p1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y6 f17941b;

    public /* synthetic */ u(y6 y6Var, int i10) {
        this.f17940a = i10;
        this.f17941b = y6Var;
    }

    public static Boolean c(String str, List list, List list2) {
        if (str == null || str.isEmpty()) {
            return Boolean.TRUE;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (str.startsWith((String) it.next())) {
                return Boolean.TRUE;
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            if (str.startsWith((String) it2.next())) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    @Override // io.sentry.p1
    public boolean a() {
        io.sentry.util.a aVarA;
        switch (this.f17940a) {
            case 0:
                return u5.d().c(this.f17941b.getFatalLogger());
            default:
                if (io.sentry.internal.a.f17361c == null) {
                    aVarA = io.sentry.internal.a.f17362d.a();
                    try {
                        if (io.sentry.internal.a.f17361c == null) {
                            io.sentry.internal.a.f17361c = new io.sentry.internal.a();
                        }
                        aVarA.close();
                    } finally {
                        try {
                            aVarA.close();
                            break;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                    break;
                }
                io.sentry.internal.a aVar = io.sentry.internal.a.f17361c;
                if (!aVar.f17363a) {
                    try {
                        aVarA = aVar.f17364b.a();
                        try {
                            if (!aVar.f17363a) {
                                Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("META-INF/MANIFEST.MF");
                                while (resources.hasMoreElements()) {
                                    try {
                                        Attributes mainAttributes = new Manifest(resources.nextElement().openStream()).getMainAttributes();
                                        if (mainAttributes != null) {
                                            String value = mainAttributes.getValue("Sentry-Opentelemetry-SDK-Name");
                                            String value2 = mainAttributes.getValue("Implementation-Version");
                                            String value3 = mainAttributes.getValue("Sentry-SDK-Name");
                                            String value4 = mainAttributes.getValue("Sentry-SDK-Package-Name");
                                            if (value != null && value2 != null) {
                                                String value5 = mainAttributes.getValue("Sentry-Opentelemetry-Version-Name");
                                                if (value5 != null) {
                                                    u5.d().b("maven:io.opentelemetry:opentelemetry-sdk", value5);
                                                    u5.d().a("OpenTelemetry");
                                                }
                                                String value6 = mainAttributes.getValue("Sentry-Opentelemetry-Javaagent-Version-Name");
                                                if (value6 != null) {
                                                    u5.d().b("maven:io.opentelemetry.javaagent:opentelemetry-javaagent", value6);
                                                    u5.d().a("OpenTelemetry-Agent");
                                                }
                                                if (value.equals("sentry.java.opentelemetry.agentless")) {
                                                    u5.d().a("OpenTelemetry-Agentless");
                                                }
                                                if (value.equals("sentry.java.opentelemetry.agentless-spring")) {
                                                    u5.d().a("OpenTelemetry-Agentless-Spring");
                                                }
                                            }
                                            if (value3 != null && value2 != null && value4 != null && value3.startsWith("sentry.java")) {
                                                u5.d().b(value4, value2);
                                            }
                                        }
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                            aVarA.close();
                        } catch (Throwable th2) {
                            try {
                                break;
                            } catch (Throwable th3) {
                            }
                            throw th2;
                        }
                        break;
                    } catch (IOException unused2) {
                    } catch (Throwable th4) {
                        aVar.f17363a = true;
                        throw th4;
                    }
                    aVar.f17363a = true;
                }
                return u5.d().c(this.f17941b.getFatalLogger());
        }
    }

    public ArrayList b(StackTraceElement[] stackTraceElementArr, boolean z) {
        if (stackTraceElementArr == null || stackTraceElementArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            if (stackTraceElement != null) {
                String className = stackTraceElement.getClassName();
                if (z || !className.startsWith("io.sentry.") || className.startsWith("io.sentry.samples.") || className.startsWith("io.sentry.mobile.")) {
                    io.sentry.protocol.z zVar = new io.sentry.protocol.z();
                    y6 y6Var = this.f17941b;
                    zVar.f17739u = c(className, y6Var.getInAppIncludes(), y6Var.getInAppExcludes());
                    zVar.f17734p = className;
                    zVar.f17733o = stackTraceElement.getMethodName();
                    zVar.f17732n = stackTraceElement.getFileName();
                    if (stackTraceElement.getLineNumber() >= 0) {
                        zVar.f17735q = Integer.valueOf(stackTraceElement.getLineNumber());
                    }
                    zVar.f17741w = Boolean.valueOf(stackTraceElement.isNativeMethod());
                    arrayList.add(zVar);
                    if (arrayList.size() >= 100) {
                        break;
                    }
                }
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }
}
