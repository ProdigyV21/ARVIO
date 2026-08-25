package com.caverock.androidsvg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y0 extends a1 implements z0, x0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f12494i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public HashSet f12495j = null;
    public String k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public HashSet f12496l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashSet f12497m = null;

    @Override // com.caverock.androidsvg.x0
    public final Set a() {
        return null;
    }

    @Override // com.caverock.androidsvg.x0
    public final String b() {
        return this.k;
    }

    @Override // com.caverock.androidsvg.x0
    public final void d(HashSet hashSet) {
        this.f12495j = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set e() {
        return this.f12495j;
    }

    public void g(d1 d1Var) {
        this.f12494i.add(d1Var);
    }

    @Override // com.caverock.androidsvg.z0
    public final List getChildren() {
        return this.f12494i;
    }

    @Override // com.caverock.androidsvg.x0
    public final void h(HashSet hashSet) {
        this.f12497m = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final void i(String str) {
        this.k = str;
    }

    @Override // com.caverock.androidsvg.x0
    public final void j(HashSet hashSet) {
        this.f12496l = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set l() {
        return this.f12496l;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set m() {
        return this.f12497m;
    }

    @Override // com.caverock.androidsvg.x0
    public final void f(HashSet hashSet) {
    }
}
