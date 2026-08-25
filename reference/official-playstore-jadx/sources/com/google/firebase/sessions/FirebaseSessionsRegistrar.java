package com.google.firebase.sessions;

import c5.a;
import c5.b;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.s;
import com.google.firebase.installations.c;
import d7.j;
import java.util.List;
import ka.f0;
import kb.d;
import kotlin.Metadata;
import retrofit2.l0;
import w5.b0;
import w5.h0;
import w5.m;
import w5.m0;
import w5.n0;
import w5.o;
import w5.t;
import w5.x;
import z2.f;
import z4.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0001\u0018\u0000 \n2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\b\u001a0\u0012,\u0012*\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0014\u0012\u000e\b\u0001\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u00050\u0004H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessionsRegistrar;", "Lcom/google/firebase/components/ComponentRegistrar;", "<init>", "()V", "", "Lcom/google/firebase/components/b;", "", "kotlin.jvm.PlatformType", "getComponents", "()Ljava/util/List;", "Companion", "w5/o", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {

    @Deprecated
    private static final String LIBRARY_NAME = "fire-sessions";
    private static final o Companion = new o();

    @Deprecated
    private static final s firebaseApp = s.a(g.class);

    @Deprecated
    private static final s firebaseInstallationsApi = s.a(c.class);

    @Deprecated
    private static final s backgroundDispatcher = new s(a.class, f0.class);

    @Deprecated
    private static final s blockingDispatcher = new s(b.class, f0.class);

    @Deprecated
    private static final s transportFactory = s.a(f.class);

    @Deprecated
    private static final s sessionFirelogPublisher = s.a(b0.class);

    @Deprecated
    private static final s sessionGenerator = s.a(h0.class);

    @Deprecated
    private static final s sessionsSettings = s.a(y5.g.class);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-0, reason: not valid java name */
    public static final m m6614getComponents$lambda0(com.google.firebase.components.c cVar) {
        return new m((g) cVar.d(firebaseApp), (y5.g) cVar.d(sessionsSettings), (j) cVar.d(backgroundDispatcher));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-1, reason: not valid java name */
    public static final h0 m6615getComponents$lambda1(com.google.firebase.components.c cVar) {
        return new h0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-2, reason: not valid java name */
    public static final b0 m6616getComponents$lambda2(com.google.firebase.components.c cVar) {
        return new w5.f0((g) cVar.d(firebaseApp), (c) cVar.d(firebaseInstallationsApi), (y5.g) cVar.d(sessionsSettings), new d(cVar.b(transportFactory), 20), (j) cVar.d(backgroundDispatcher));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-3, reason: not valid java name */
    public static final y5.g m6617getComponents$lambda3(com.google.firebase.components.c cVar) {
        return new y5.g((g) cVar.d(firebaseApp), (j) cVar.d(blockingDispatcher), (j) cVar.d(backgroundDispatcher), (c) cVar.d(firebaseInstallationsApi));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-4, reason: not valid java name */
    public static final t m6618getComponents$lambda4(com.google.firebase.components.c cVar) {
        g gVar = (g) cVar.d(firebaseApp);
        gVar.a();
        return new x(gVar.f23184a, (j) cVar.d(backgroundDispatcher));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getComponents$lambda-5, reason: not valid java name */
    public static final m0 m6619getComponents$lambda5(com.google.firebase.components.c cVar) {
        return new n0((g) cVar.d(firebaseApp));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<com.google.firebase.components.b> getComponents() {
        com.google.firebase.components.a aVarB = com.google.firebase.components.b.b(m.class);
        aVarB.f14248a = LIBRARY_NAME;
        s sVar = firebaseApp;
        aVarB.a(com.google.firebase.components.m.a(sVar));
        s sVar2 = sessionsSettings;
        aVarB.a(com.google.firebase.components.m.a(sVar2));
        s sVar3 = backgroundDispatcher;
        aVarB.a(com.google.firebase.components.m.a(sVar3));
        aVarB.f14253f = new l0(5);
        if (!(aVarB.f14251d == 0)) {
            throw new IllegalStateException("Instantiation type has already been set.");
        }
        aVarB.f14251d = 2;
        com.google.firebase.components.b bVarB = aVarB.b();
        com.google.firebase.components.a aVarB2 = com.google.firebase.components.b.b(h0.class);
        aVarB2.f14248a = "session-generator";
        aVarB2.f14253f = new l0(6);
        com.google.firebase.components.b bVarB2 = aVarB2.b();
        com.google.firebase.components.a aVarB3 = com.google.firebase.components.b.b(b0.class);
        aVarB3.f14248a = "session-publisher";
        aVarB3.a(new com.google.firebase.components.m(sVar, 1, 0));
        s sVar4 = firebaseInstallationsApi;
        aVarB3.a(com.google.firebase.components.m.a(sVar4));
        aVarB3.a(new com.google.firebase.components.m(sVar2, 1, 0));
        aVarB3.a(new com.google.firebase.components.m(transportFactory, 1, 1));
        aVarB3.a(new com.google.firebase.components.m(sVar3, 1, 0));
        aVarB3.f14253f = new l0(7);
        com.google.firebase.components.b bVarB3 = aVarB3.b();
        com.google.firebase.components.a aVarB4 = com.google.firebase.components.b.b(y5.g.class);
        aVarB4.f14248a = "sessions-settings";
        aVarB4.a(new com.google.firebase.components.m(sVar, 1, 0));
        aVarB4.a(com.google.firebase.components.m.a(blockingDispatcher));
        aVarB4.a(new com.google.firebase.components.m(sVar3, 1, 0));
        aVarB4.a(new com.google.firebase.components.m(sVar4, 1, 0));
        aVarB4.f14253f = new l0(8);
        com.google.firebase.components.b bVarB4 = aVarB4.b();
        com.google.firebase.components.a aVarB5 = com.google.firebase.components.b.b(t.class);
        aVarB5.f14248a = "sessions-datastore";
        aVarB5.a(new com.google.firebase.components.m(sVar, 1, 0));
        aVarB5.a(new com.google.firebase.components.m(sVar3, 1, 0));
        aVarB5.f14253f = new l0(9);
        com.google.firebase.components.b bVarB5 = aVarB5.b();
        com.google.firebase.components.a aVarB6 = com.google.firebase.components.b.b(m0.class);
        aVarB6.f14248a = "sessions-service-binder";
        aVarB6.a(new com.google.firebase.components.m(sVar, 1, 0));
        aVarB6.f14253f = new l0(10);
        return t7.a.E(bVarB, bVarB2, bVarB3, bVarB4, bVarB5, aVarB6.b(), androidx.work.impl.t.j(LIBRARY_NAME, "1.2.0"));
    }
}
