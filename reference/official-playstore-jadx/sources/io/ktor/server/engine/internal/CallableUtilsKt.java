package io.ktor.server.engine.internal;

import a0.c;
import androidx.media3.common.MimeTypes;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.reflect.g;
import kotlin.reflect.l;
import kotlin.text.o;
import z7.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\r\u001a\u00020\f*\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a9\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f*\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/server/application/ApplicationEnvironment;", "Ljava/lang/ClassLoader;", "classLoader", "", "fqName", "Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "Lx6/t0;", "executeModuleFunction", "(Lio/ktor/server/application/ApplicationEnvironment;Ljava/lang/ClassLoader;Ljava/lang/String;Lio/ktor/server/application/Application;)V", "Lkotlin/reflect/d;", "applicationEntryClass", "", "createModuleContainer", "(Lio/ktor/server/application/ApplicationEnvironment;Lkotlin/reflect/d;Lio/ktor/server/application/Application;)Ljava/lang/Object;", "R", "instance", "Lkotlin/reflect/g;", "entryPoint", "callFunctionWithInjection", "(Lio/ktor/server/application/ApplicationEnvironment;Ljava/lang/Object;Lkotlin/reflect/g;Lio/ktor/server/application/Application;)Ljava/lang/Object;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CallableUtilsKt {
    private static final <R> R callFunctionWithInjection(ApplicationEnvironment applicationEnvironment, Object obj, g<? extends R> gVar, Application application) throws Throwable {
        Object obj2;
        List parameters = gVar.getParameters();
        ArrayList<l> arrayList = new ArrayList();
        for (Object obj3 : parameters) {
            if (!((l) obj3).s()) {
                arrayList.add(obj3);
            }
        }
        int iQ0 = i0.q0(s.U(arrayList, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (l lVar : arrayList) {
            if (lVar.getKind() == 1) {
                obj2 = obj;
            } else if (AutoReloadUtilsKt.isApplicationEnvironment(lVar)) {
                obj2 = applicationEnvironment;
            } else {
                if (!AutoReloadUtilsKt.isApplication(lVar)) {
                    if (!o.T(lVar.getType().toString(), "Application", false)) {
                        StringBuilder sb2 = new StringBuilder("Parameter type '");
                        sb2.append(lVar.getType());
                        sb2.append("' of parameter '");
                        String name = lVar.getName();
                        if (name == null) {
                            name = "<receiver>";
                        }
                        throw new IllegalArgumentException(c.p(sb2, name, "' is not supported"));
                    }
                    Type typeD = d.d(lVar.getType());
                    Class cls = typeD instanceof Class ? (Class) typeD : null;
                    throw new IllegalArgumentException("Parameter type " + lVar.getType() + ":{" + (cls != null ? cls.getClassLoader() : null) + "} is not supported.Application is loaded as " + AutoReloadUtilsKt.getApplicationClassInstance() + ":{" + AutoReloadUtilsKt.getApplicationClassInstance().getClassLoader() + '}');
                }
                obj2 = application;
            }
            linkedHashMap.put(lVar, obj2);
        }
        try {
            return (R) gVar.callBy(linkedHashMap);
        } catch (InvocationTargetException e5) {
            Throwable cause = e5.getCause();
            if (cause == null) {
                throw e5;
            }
            throw cause;
        }
    }

    private static final Object createModuleContainer(ApplicationEnvironment applicationEnvironment, kotlin.reflect.d<?> dVar, Application application) {
        Object objE = dVar.e();
        if (objE != null) {
            return objE;
        }
        Collection collectionH = dVar.h();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionH) {
            List<l> parameters = ((g) obj).getParameters();
            if (parameters == null || !parameters.isEmpty()) {
                for (l lVar : parameters) {
                    if (lVar.s() || AutoReloadUtilsKt.isApplicationEnvironment(lVar) || AutoReloadUtilsKt.isApplication(lVar)) {
                    }
                }
            }
            arrayList.add(obj);
        }
        g gVarBestFunction = AutoReloadUtilsKt.bestFunction(arrayList);
        if (gVarBestFunction != null) {
            return callFunctionWithInjection(applicationEnvironment, null, gVarBestFunction, application);
        }
        throw new RuntimeException("There are no applicable constructors found in class " + dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0125  */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Object, x6.s] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void executeModuleFunction(io.ktor.server.application.ApplicationEnvironment r16, java.lang.ClassLoader r17, java.lang.String r18, io.ktor.server.application.Application r19) {
        /*
            Method dump skipped, instruction units count: 579
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.internal.CallableUtilsKt.executeModuleFunction(io.ktor.server.application.ApplicationEnvironment, java.lang.ClassLoader, java.lang.String, io.ktor.server.application.Application):void");
    }
}
