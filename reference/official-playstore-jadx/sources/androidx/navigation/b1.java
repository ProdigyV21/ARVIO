package androidx.navigation;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f4528b = new LinkedHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f4529a = new LinkedHashMap();

    public final void a(a1 a1Var) {
        String strT = m2.f0.t(a1Var.getClass());
        if (strT.length() <= 0) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        LinkedHashMap linkedHashMap = this.f4529a;
        a1 a1Var2 = (a1) linkedHashMap.get(strT);
        if (kotlin.jvm.internal.p.a(a1Var2, a1Var)) {
            return;
        }
        if (a1Var2 != null && a1Var2.f4520b) {
            throw new IllegalStateException(("Navigator " + a1Var + " is replacing an already attached " + a1Var2).toString());
        }
        if (!a1Var.f4520b) {
            return;
        }
        throw new IllegalStateException(("Navigator " + a1Var + " is already attached to another NavController").toString());
    }

    public final a1 b(String str) {
        if (str == null || str.length() <= 0) {
            throw new IllegalArgumentException("navigator name cannot be an empty string");
        }
        a1 a1Var = (a1) this.f4529a.get(str);
        if (a1Var != null) {
            return a1Var;
        }
        throw new IllegalStateException(a0.c.l("Could not find Navigator with name \"", str, "\". You must call NavController.addNavigator() for each navigation type."));
    }
}
