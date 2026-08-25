package io.ktor.server.config;

import com.google.android.gms.common.api.internal.f1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.a;
import com.typesafe.config.impl.a2;
import com.typesafe.config.impl.c;
import com.typesafe.config.impl.o;
import com.typesafe.config.impl.p;
import com.typesafe.config.impl.p1;
import com.typesafe.config.impl.t;
import com.typesafe.config.impl.w;
import com.typesafe.config.impl.w1;
import com.typesafe.config.impl.x;
import com.typesafe.config.impl.y;
import com.typesafe.config.impl.y0;
import java.io.File;
import kotlin.Metadata;
import kotlin.text.u;
import l6.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/server/config/HoconConfigLoader;", "Lio/ktor/server/config/ConfigLoader;", "()V", "load", "Lio/ktor/server/config/ApplicationConfig;", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HoconConfigLoader implements ConfigLoader {
    @Override // io.ktor.server.config.ConfigLoader
    public ApplicationConfig load(String path) {
        p1 p1VarN;
        p1 p1Var;
        if (path != null) {
            if (u.K(path, ".conf", false) || u.K(path, ".json", false) || u.K(path, ".properties", false)) {
            }
            return null;
        }
        path = "application.conf";
        if (Thread.currentThread().getContextClassLoader().getResource(path) != null) {
            l lVar = new l(0, null, true, null, null);
            a aVar = new a();
            if (Thread.currentThread().getContextClassLoader() == null) {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader == null) {
                    throw new ConfigException.BugOrBroken(null, "Context class loader is not set for the current thread; if Thread.currentThread().getContextClassLoader() returns null, you must pass a ClassLoader explicitly to ConfigFactory.load");
                }
                lVar = new l(0, null, true, null, contextClassLoader);
            }
            w1 w1Var = x.f14641a;
            p1 p1Var2 = ((c) a2.f(new p(0), path, lVar)).f14511l;
            ClassLoader contextClassLoader2 = (ClassLoader) lVar.f20042e;
            if (contextClassLoader2 == null) {
                contextClassLoader2 = Thread.currentThread().getContextClassLoader();
            }
            if (Boolean.parseBoolean(System.getProperties().getProperty("config.override_with_env_vars"))) {
                try {
                    try {
                        p1Var = t.f14619a.f14511l.f14609i.N(w.f14631a.f14511l).f14511l;
                    } catch (ExceptionInInitializerError e5) {
                        throw y.b(e5);
                    }
                } catch (ExceptionInInitializerError e6) {
                    throw y.b(e6);
                }
            } else {
                try {
                    p1Var = w.f14631a.f14511l;
                } catch (ExceptionInInitializerError e10) {
                    throw y.b(e10);
                }
            }
            p1 p1Var3 = p1Var.f14609i.N(p1Var2).f14511l;
            try {
                x.a(contextClassLoader2, "defaultReference", new o(contextClassLoader2, 0));
                p1VarN = p1Var3.f14609i.N(x.a(contextClassLoader2, "unresolvedReference", new o(contextClassLoader2, 1))).f14511l.n(aVar);
            } catch (ConfigException.UnresolvedSubstitution e11) {
                throw new ConfigException.UnresolvedSubstitution(e11, e11.f14502i, a0.c.l("Could not resolve substitution in reference.conf to a value: ", e11.f14503l, ". All reference.conf files are required to be fully, independently resolvable, and should not require the presence of values for substitutions from further up the hierarchy."));
            }
        } else {
            File file = new File(path);
            if (file.exists()) {
                l lVar2 = new l(0, null, true, null, null);
                f1 f1Var = com.typesafe.config.impl.f1.f14531d;
                p1VarN = new y0(file, lVar2).j().f14511l;
            } else {
                p1VarN = null;
            }
        }
        p1 p1VarN2 = p1VarN != null ? p1VarN.n(new a()) : null;
        if (p1VarN2 != null) {
            return new HoconApplicationConfig(p1VarN2);
        }
        return null;
    }
}
