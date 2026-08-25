package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.impl.model.p;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f6799b = t.f("NetworkMeteredCtrlr");

    @Override // androidx.work.impl.constraints.controllers.d
    public final int a() {
        return 7;
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean b(p pVar) {
        return pVar.f6931j.f20188a == 5;
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean c(Object obj) {
        androidx.work.impl.constraints.e eVar = (androidx.work.impl.constraints.e) obj;
        boolean z = eVar.f6801a;
        if (Build.VERSION.SDK_INT >= 26) {
            return (z && eVar.f6803c) ? false : true;
        }
        t.d().a(f6799b, "Metered network constraint is not supported before API 26, only checking for connected state.");
        return !z;
    }
}
