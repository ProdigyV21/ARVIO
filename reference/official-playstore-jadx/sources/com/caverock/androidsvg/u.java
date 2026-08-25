package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f12447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f12448b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f12449c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f12450d;

    public u(float f10, float f11, float f12, float f13) {
        this.f12447a = f10;
        this.f12448b = f11;
        this.f12449c = f12;
        this.f12450d = f13;
    }

    public final float a() {
        return this.f12447a + this.f12449c;
    }

    public final float b() {
        return this.f12448b + this.f12450d;
    }

    public final String toString() {
        return "[" + this.f12447a + " " + this.f12448b + " " + this.f12449c + " " + this.f12450d + "]";
    }

    public u(u uVar) {
        this.f12447a = uVar.f12447a;
        this.f12448b = uVar.f12448b;
        this.f12449c = uVar.f12449c;
        this.f12450d = uVar.f12450d;
    }
}
