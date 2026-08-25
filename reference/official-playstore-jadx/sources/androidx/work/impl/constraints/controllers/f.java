package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.impl.model.p;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f6800b = t.f("NetworkNotRoamingCtrlr");

    @Override // androidx.work.impl.constraints.controllers.d
    public final int a() {
        return 7;
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean b(p pVar) {
        return pVar.f6931j.f20188a == 4;
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean c(Object obj) {
        androidx.work.impl.constraints.e eVar = (androidx.work.impl.constraints.e) obj;
        boolean z = eVar.f6801a;
        if (Build.VERSION.SDK_INT >= 24) {
            return (z && eVar.f6804d) ? false : true;
        }
        t.d().a(f6800b, "Not-roaming network constraint is not supported before API 24, only checking for connected state.");
        return !z;
    }
}
