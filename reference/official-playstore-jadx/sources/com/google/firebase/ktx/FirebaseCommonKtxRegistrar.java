package com.google.firebase.ktx;

import c5.c;
import c5.d;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.a;
import com.google.firebase.components.b;
import com.google.firebase.components.m;
import com.google.firebase.components.s;
import java.util.List;
import java.util.concurrent.Executor;
import ka.f0;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/google/firebase/ktx/FirebaseCommonKtxRegistrar;", "Lcom/google/firebase/components/ComponentRegistrar;", "<init>", "()V", "", "Lcom/google/firebase/components/b;", "getComponents", "()Ljava/util/List;", "com.google.firebase-firebase-common"}, k = 1, mv = {1, 7, 1}, xi = 48)
@e
public final class FirebaseCommonKtxRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<b> getComponents() {
        a aVarA = b.a(new s(c5.a.class, f0.class));
        aVarA.a(new m(new s(c5.a.class, Executor.class), 1, 0));
        aVarA.f14253f = s5.b.f21640l;
        b bVarB = aVarA.b();
        a aVarA2 = b.a(new s(c.class, f0.class));
        aVarA2.a(new m(new s(c.class, Executor.class), 1, 0));
        aVarA2.f14253f = s5.b.f21641m;
        b bVarB2 = aVarA2.b();
        a aVarA3 = b.a(new s(c5.b.class, f0.class));
        aVarA3.a(new m(new s(c5.b.class, Executor.class), 1, 0));
        aVarA3.f14253f = s5.b.f21642n;
        b bVarB3 = aVarA3.b();
        a aVarA4 = b.a(new s(d.class, f0.class));
        aVarA4.a(new m(new s(d.class, Executor.class), 1, 0));
        aVarA4.f14253f = s5.b.f21643o;
        return t7.a.E(bVarB, bVarB2, bVarB3, aVarA4.b());
    }
}
