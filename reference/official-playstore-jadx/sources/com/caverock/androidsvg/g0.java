package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 implements Cloneable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f12317i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12318l;

    public g0(float f10, int i10) {
        this.f12317i = f10;
        this.f12318l = i10;
    }

    public final float a(e2 e2Var) {
        float fSqrt;
        if (this.f12318l != 9) {
            return d(e2Var);
        }
        c2 c2Var = e2Var.f12299c;
        u uVar = c2Var.f12286g;
        if (uVar == null) {
            uVar = c2Var.f12285f;
        }
        float f10 = this.f12317i;
        if (uVar == null) {
            return f10;
        }
        float f11 = uVar.f12449c;
        if (f11 == uVar.f12450d) {
            fSqrt = f10 * f11;
        } else {
            fSqrt = f10 * ((float) (Math.sqrt((r0 * r0) + (f11 * f11)) / 1.414213562373095d));
        }
        return fSqrt / 100.0f;
    }

    public final float b(e2 e2Var, float f10) {
        return this.f12318l == 9 ? (this.f12317i * f10) / 100.0f : d(e2Var);
    }

    public final float c() {
        float f10;
        float f11;
        int iC = h.f0.c(this.f12318l);
        float f12 = this.f12317i;
        if (iC == 0) {
            return f12;
        }
        if (iC == 3) {
            return f12 * 96.0f;
        }
        if (iC == 4) {
            f10 = f12 * 96.0f;
            f11 = 2.54f;
        } else if (iC == 5) {
            f10 = f12 * 96.0f;
            f11 = 25.4f;
        } else if (iC == 6) {
            f10 = f12 * 96.0f;
            f11 = 72.0f;
        } else {
            if (iC != 7) {
                return f12;
            }
            f10 = f12 * 96.0f;
            f11 = 6.0f;
        }
        return f10 / f11;
    }

    public final float d(e2 e2Var) {
        float textSize;
        int iC = h.f0.c(this.f12318l);
        float f10 = this.f12317i;
        switch (iC) {
            case 1:
                textSize = e2Var.f12299c.f12283d.getTextSize();
                break;
            case 2:
                textSize = e2Var.f12299c.f12283d.getTextSize() / 2.0f;
                break;
            case 3:
                e2Var.getClass();
                return f10 * 96.0f;
            case 4:
                e2Var.getClass();
                return (f10 * 96.0f) / 2.54f;
            case 5:
                e2Var.getClass();
                return (f10 * 96.0f) / 25.4f;
            case 6:
                e2Var.getClass();
                return (f10 * 96.0f) / 72.0f;
            case 7:
                e2Var.getClass();
                return (f10 * 96.0f) / 6.0f;
            case 8:
                c2 c2Var = e2Var.f12299c;
                u uVar = c2Var.f12286g;
                if (uVar == null) {
                    uVar = c2Var.f12285f;
                }
                if (uVar != null) {
                    return (f10 * uVar.f12449c) / 100.0f;
                }
            default:
                return f10;
        }
        return textSize * f10;
    }

    public final float e(e2 e2Var) {
        if (this.f12318l != 9) {
            return d(e2Var);
        }
        c2 c2Var = e2Var.f12299c;
        u uVar = c2Var.f12286g;
        if (uVar == null) {
            uVar = c2Var.f12285f;
        }
        float f10 = this.f12317i;
        return uVar == null ? f10 : (f10 * uVar.f12450d) / 100.0f;
    }

    public final boolean f() {
        return this.f12317i < 0.0f;
    }

    public final boolean g() {
        return this.f12317i == 0.0f;
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(String.valueOf(this.f12317i));
        switch (this.f12318l) {
            case 1:
                str = "px";
                break;
            case 2:
                str = "em";
                break;
            case 3:
                str = "ex";
                break;
            case 4:
                str = "in";
                break;
            case 5:
                str = "cm";
                break;
            case 6:
                str = "mm";
                break;
            case 7:
                str = "pt";
                break;
            case 8:
                str = "pc";
                break;
            case 9:
                str = "percent";
                break;
            default:
                str = "null";
                break;
        }
        sb2.append(str);
        return sb2.toString();
    }

    public g0(float f10) {
        this.f12317i = f10;
        this.f12318l = 1;
    }
}
