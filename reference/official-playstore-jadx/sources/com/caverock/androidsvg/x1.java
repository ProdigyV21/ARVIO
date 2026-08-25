package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f12488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f12489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f12490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f12491d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f12492e = false;

    public x1(float f10, float f11, float f12, float f13) {
        this.f12490c = 0.0f;
        this.f12491d = 0.0f;
        this.f12488a = f10;
        this.f12489b = f11;
        double dSqrt = Math.sqrt((f13 * f13) + (f12 * f12));
        if (dSqrt != 0.0d) {
            this.f12490c = (float) (((double) f12) / dSqrt);
            this.f12491d = (float) (((double) f13) / dSqrt);
        }
    }

    public final void a(float f10, float f11) {
        float f12 = f10 - this.f12488a;
        float f13 = f11 - this.f12489b;
        double dSqrt = Math.sqrt((f13 * f13) + (f12 * f12));
        if (dSqrt != 0.0d) {
            f12 = (float) (((double) f12) / dSqrt);
            f13 = (float) (((double) f13) / dSqrt);
        }
        float f14 = this.f12490c;
        if (f12 != (-f14) || f13 != (-this.f12491d)) {
            this.f12490c = f14 + f12;
            this.f12491d += f13;
        } else {
            this.f12492e = true;
            this.f12490c = -f13;
            this.f12491d = f12;
        }
    }

    public final void b(x1 x1Var) {
        float f10 = x1Var.f12490c;
        float f11 = this.f12490c;
        if (f10 == (-f11)) {
            float f12 = x1Var.f12491d;
            if (f12 == (-this.f12491d)) {
                this.f12492e = true;
                this.f12490c = -f12;
                this.f12491d = x1Var.f12490c;
                return;
            }
        }
        this.f12490c = f11 + f10;
        this.f12491d += x1Var.f12491d;
    }

    public final String toString() {
        return "(" + this.f12488a + "," + this.f12489b + " " + this.f12490c + "," + this.f12491d + ")";
    }
}
