package com.caverock.androidsvg;

import android.graphics.Matrix;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c0 extends a1 implements e0, x0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashSet f12271i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f12272j = null;
    public HashSet k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public HashSet f12273l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashSet f12274m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Matrix f12275n;

    @Override // com.caverock.androidsvg.x0
    public final Set a() {
        return this.k;
    }

    @Override // com.caverock.androidsvg.x0
    public final String b() {
        return this.f12272j;
    }

    @Override // com.caverock.androidsvg.x0
    public final void d(HashSet hashSet) {
        this.f12271i = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set e() {
        return this.f12271i;
    }

    @Override // com.caverock.androidsvg.x0
    public final void f(HashSet hashSet) {
        this.k = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final void h(HashSet hashSet) {
        this.f12274m = hashSet;
    }

    @Override // com.caverock.androidsvg.x0
    public final void i(String str) {
        this.f12272j = str;
    }

    @Override // com.caverock.androidsvg.x0
    public final void j(HashSet hashSet) {
        this.f12273l = hashSet;
    }

    @Override // com.caverock.androidsvg.e0
    public final void k(Matrix matrix) {
        this.f12275n = matrix;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set l() {
        return this.f12273l;
    }

    @Override // com.caverock.androidsvg.x0
    public final Set m() {
        return this.f12274m;
    }
}
