package com.google.firebase.installations;

import androidx.work.impl.t;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.m;
import com.google.firebase.components.s;
import com.google.firebase.concurrent.o;
import io.sentry.util.l;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-installations";

    /* JADX INFO: Access modifiers changed from: private */
    public static c lambda$getComponents$0(com.google.firebase.components.c cVar) {
        return new b((z4.g) cVar.a(z4.g.class), cVar.e(l5.e.class), (ExecutorService) cVar.d(new s(c5.a.class, ExecutorService.class)), new o((Executor) cVar.d(new s(c5.b.class, Executor.class))));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<com.google.firebase.components.b> getComponents() {
        com.google.firebase.components.a aVarB = com.google.firebase.components.b.b(c.class);
        aVarB.f14248a = LIBRARY_NAME;
        aVarB.a(m.b(z4.g.class));
        aVarB.a(new m(0, 1, l5.e.class));
        aVarB.a(new m(new s(c5.a.class, ExecutorService.class), 1, 0));
        aVarB.a(new m(new s(c5.b.class, Executor.class), 1, 0));
        aVarB.f14253f = new d(0);
        com.google.firebase.components.b bVarB = aVarB.b();
        l lVar = new l(4);
        com.google.firebase.components.a aVarB2 = com.google.firebase.components.b.b(l5.d.class);
        aVarB2.f14252e = 1;
        aVarB2.f14253f = new a3.b(lVar, 16);
        return Arrays.asList(bVarB, aVarB2.b(), t.j(LIBRARY_NAME, "17.2.0"));
    }
}
