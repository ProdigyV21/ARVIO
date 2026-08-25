package com.google.firebase.datatransport;

import a3.a;
import android.content.Context;
import androidx.work.impl.t;
import com.google.android.datatransport.runtime.w;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.b;
import com.google.firebase.components.c;
import com.google.firebase.components.m;
import com.google.firebase.installations.d;
import java.util.Arrays;
import java.util.List;
import z2.f;

/* JADX INFO: loaded from: classes4.dex */
public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ f lambda$getComponents$0(c cVar) {
        w.b((Context) cVar.a(Context.class));
        return w.a().c(a.f108f);
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<b> getComponents() {
        com.google.firebase.components.a aVarB = b.b(f.class);
        aVarB.f14248a = LIBRARY_NAME;
        aVarB.a(m.b(Context.class));
        aVarB.f14253f = new d(1);
        return Arrays.asList(aVarB.b(), t.j(LIBRARY_NAME, "18.1.8"));
    }
}
