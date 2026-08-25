package io.ktor.server.engine.internal;

import com.google.common.util.concurrent.r0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.file.WatchEvent;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import kotlin.reflect.g;
import r7.l;
import t7.a;
import z7.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a!\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\r\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a-\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0011\"\u0004\b\u0000\u0010\u000f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0010H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0017\u0010\u0014\u001a\u00020\u0002*\u0006\u0012\u0002\b\u00030\u0011H\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001d\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u0006\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\"&\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u001d0\u001c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\" \u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\" \u0010(\u001a\b\u0012\u0004\u0012\u00020'0\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010$\u001a\u0004\b)\u0010&¨\u0006*"}, d2 = {"Lkotlin/reflect/l;", "parameter", "", "isApplicationEnvironment", "(Lkotlin/reflect/l;)Z", "isApplication", "Ljava/lang/ClassLoader;", "", ContentDisposition.Parameters.Name, "Ljava/lang/Class;", "loadClassOrNull", "(Ljava/lang/ClassLoader;Ljava/lang/String;)Ljava/lang/Class;", LinkHeader.Parameters.Type, "isParameterOfType", "(Lkotlin/reflect/l;Ljava/lang/Class;)Z", "R", "", "Lkotlin/reflect/g;", "bestFunction", "(Ljava/util/List;)Lkotlin/reflect/g;", "isApplicableFunction", "(Lkotlin/reflect/g;)Z", "Lkotlin/reflect/d;", "takeIfNotFacade", "(Ljava/lang/Class;)Lkotlin/reflect/d;", "Ljava/nio/file/WatchEvent$Modifier;", "get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH", "()Ljava/nio/file/WatchEvent$Modifier;", "Ljava/lang/ThreadLocal;", "", "currentStartupModules", "Ljava/lang/ThreadLocal;", "getCurrentStartupModules", "()Ljava/lang/ThreadLocal;", "Lio/ktor/server/application/ApplicationEnvironment;", "ApplicationEnvironmentClassInstance", "Ljava/lang/Class;", "getApplicationEnvironmentClassInstance", "()Ljava/lang/Class;", "Lio/ktor/server/application/Application;", "ApplicationClassInstance", "getApplicationClassInstance", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoReloadUtilsKt {
    private static final ThreadLocal<List<String>> currentStartupModules = new ThreadLocal<>();
    private static final Class<ApplicationEnvironment> ApplicationEnvironmentClassInstance = ApplicationEnvironment.class;
    private static final Class<Application> ApplicationClassInstance = Application.class;

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: io.ktor.server.engine.internal.AutoReloadUtilsKt$bestFunction$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0003\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "Lkotlin/reflect/g;", "it", "", "invoke", "(Lkotlin/reflect/g;)Ljava/lang/Comparable;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1<R> extends r implements l<g<? extends R>, Comparable<?>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Comparable<?> invoke(g<? extends R> gVar) {
            boolean z = false;
            if (!gVar.getParameters().isEmpty() && AutoReloadUtilsKt.isApplication((kotlin.reflect.l) gVar.getParameters().get(0))) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: io.ktor.server.engine.internal.AutoReloadUtilsKt$bestFunction$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0003\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "Lkotlin/reflect/g;", "it", "", "invoke", "(Lkotlin/reflect/g;)Ljava/lang/Comparable;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2<R> extends r implements l<g<? extends R>, Comparable<?>> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Comparable<?> invoke(g<? extends R> gVar) {
            List parameters = gVar.getParameters();
            int i10 = 0;
            if (parameters == null || !parameters.isEmpty()) {
                Iterator it = parameters.iterator();
                while (it.hasNext()) {
                    if (!((kotlin.reflect.l) it.next()).s() && (i10 = i10 + 1) < 0) {
                        a.P();
                        throw null;
                    }
                }
            }
            return Integer.valueOf(i10);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: io.ktor.server.engine.internal.AutoReloadUtilsKt$bestFunction$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0003\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "Lkotlin/reflect/g;", "it", "", "invoke", "(Lkotlin/reflect/g;)Ljava/lang/Comparable;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3<R> extends r implements l<g<? extends R>, Comparable<?>> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public final Comparable<?> invoke(g<? extends R> gVar) {
            return Integer.valueOf(gVar.getParameters().size());
        }
    }

    public static final <R> g<R> bestFunction(List<? extends g<? extends R>> list) {
        return (g) x.y0(x.W0(list, r0.d(AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE, AnonymousClass3.INSTANCE)));
    }

    public static final Class<Application> getApplicationClassInstance() {
        return ApplicationClassInstance;
    }

    public static final Class<ApplicationEnvironment> getApplicationEnvironmentClassInstance() {
        return ApplicationEnvironmentClassInstance;
    }

    public static final ThreadLocal<List<String>> getCurrentStartupModules() {
        return currentStartupModules;
    }

    public static final WatchEvent.Modifier get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH() {
        if (System.getenv("ANDROID_DATA") != null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("com.sun.nio.file.SensitivityWatchEventModifier");
            Object obj = cls.getField("HIGH").get(cls);
            if (obj instanceof WatchEvent.Modifier) {
                return (WatchEvent.Modifier) obj;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static final boolean isApplicableFunction(g<?> gVar) {
        Method methodC;
        if (!gVar.isOperator() && !gVar.isInfix() && !gVar.isInline() && !gVar.isAbstract() && !gVar.isSuspend()) {
            Iterator it = gVar.getParameters().iterator();
            Object obj = null;
            boolean z = false;
            Object obj2 = null;
            while (true) {
                if (it.hasNext()) {
                    Object next = it.next();
                    if (((kotlin.reflect.l) next).getKind() == 3) {
                        if (z) {
                            break;
                        }
                        obj2 = next;
                        z = true;
                    }
                } else if (z) {
                    obj = obj2;
                }
            }
            kotlin.reflect.l lVar = (kotlin.reflect.l) obj;
            if ((lVar == null || isApplication(lVar) || isApplicationEnvironment(lVar)) && ((methodC = d.c(gVar)) == null || (!methodC.isSynthetic() && (!Modifier.isStatic(methodC.getModifiers()) || !gVar.getParameters().isEmpty())))) {
                List<kotlin.reflect.l> parameters = gVar.getParameters();
                if (parameters == null || !parameters.isEmpty()) {
                    for (kotlin.reflect.l lVar2 : parameters) {
                        if (isApplication(lVar2) || isApplicationEnvironment(lVar2) || lVar2.getKind() == 1 || lVar2.s()) {
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static final boolean isApplication(kotlin.reflect.l lVar) {
        return isParameterOfType(lVar, ApplicationClassInstance);
    }

    public static final boolean isApplicationEnvironment(kotlin.reflect.l lVar) {
        return isParameterOfType(lVar, ApplicationEnvironmentClassInstance);
    }

    public static final boolean isParameterOfType(kotlin.reflect.l lVar, Class<?> cls) {
        Type typeD = d.d(lVar.getType());
        Class<?> cls2 = typeD instanceof Class ? (Class) typeD : null;
        if (cls2 != null) {
            return cls.isAssignableFrom(cls2);
        }
        return false;
    }

    public static final Class<?> loadClassOrNull(ClassLoader classLoader, String str) {
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static final kotlin.reflect.d<?> takeIfNotFacade(Class<?> cls) {
        Metadata metadata = (Metadata) cls.getAnnotation(Metadata.class);
        if (metadata == null || metadata.k() != 1) {
            metadata = null;
        }
        if (metadata != null) {
            return l0.f19747a.b(cls);
        }
        return null;
    }
}
