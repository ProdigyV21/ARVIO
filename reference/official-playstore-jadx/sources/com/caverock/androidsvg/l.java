package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n f12361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v0 f12362b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f12363c;

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(String.valueOf(this.f12361a));
        sb2.append(" {...} (src=");
        int i10 = this.f12363c;
        sb2.append(i10 != 1 ? i10 != 2 ? "null" : "RenderOptions" : "Document");
        sb2.append(")");
        return sb2.toString();
    }
}
