package com.google.firebase.crashlytics;

import android.util.Log;
import androidx.work.impl.t;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.b;
import com.google.firebase.components.m;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import x5.c;
import x5.d;
import z4.g;

/* JADX INFO: loaded from: classes4.dex */
public class CrashlyticsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-cls";

    static {
        c cVar = c.f22570a;
        Map map = c.f22571b;
        d dVar = d.f22572i;
        if (map.containsKey(dVar)) {
            Log.d("SessionsDependencies", "Dependency " + dVar + " already added.");
            return;
        }
        map.put(dVar, new x5.a(new ua.d(true)));
        Log.d("SessionsDependencies", "Dependency to " + dVar + " added.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseCrashlytics buildCrashlytics(com.google.firebase.components.c cVar) {
        return FirebaseCrashlytics.init((g) cVar.a(g.class), (com.google.firebase.installations.c) cVar.a(com.google.firebase.installations.c.class), cVar.g(CrashlyticsNativeComponent.class), cVar.g(a5.a.class), cVar.g(u5.a.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<b> getComponents() {
        com.google.firebase.components.a aVarB = b.b(FirebaseCrashlytics.class);
        aVarB.f14248a = LIBRARY_NAME;
        aVarB.a(m.b(g.class));
        aVarB.a(m.b(com.google.firebase.installations.c.class));
        aVarB.a(new m(0, 2, CrashlyticsNativeComponent.class));
        aVarB.a(new m(0, 2, a5.a.class));
        aVarB.a(new m(0, 2, u5.a.class));
        aVarB.f14253f = new a3.b(this, 17);
        if (!(aVarB.f14251d == 0)) {
            throw new IllegalStateException("Instantiation type has already been set.");
        }
        aVarB.f14251d = 2;
        return Arrays.asList(aVarB.b(), t.j(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }
}
