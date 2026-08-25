package com.caverock.androidsvg;

import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class v0 implements Cloneable {
    public String A;
    public String B;
    public String C;
    public Boolean D;
    public Boolean E;
    public e1 F;
    public Float G;
    public String H;
    public String I;
    public e1 J;
    public Float K;
    public e1 L;
    public Float M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f12455i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e1 f12456l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Float f12457m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e1 f12458n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Float f12459o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public g0 f12460p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Float f12461q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public g0[] f12462r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g0 f12463s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Float f12464t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public x f12465u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ArrayList f12466v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public g0 f12467w;
    public Integer x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Boolean f12468y;
    public androidx.emoji2.text.e0 z;

    public static v0 a() {
        v0 v0Var = new v0();
        v0Var.f12455i = -1L;
        x xVar = x.f12485l;
        v0Var.f12456l = xVar;
        v0Var.N = 1;
        Float fValueOf = Float.valueOf(1.0f);
        v0Var.f12457m = fValueOf;
        v0Var.f12458n = null;
        v0Var.f12459o = fValueOf;
        v0Var.f12460p = new g0(1.0f);
        v0Var.O = 1;
        v0Var.P = 1;
        v0Var.f12461q = Float.valueOf(4.0f);
        v0Var.f12462r = null;
        v0Var.f12463s = new g0(0.0f);
        v0Var.f12464t = fValueOf;
        v0Var.f12465u = xVar;
        v0Var.f12466v = null;
        v0Var.f12467w = new g0(12.0f, 7);
        v0Var.x = Integer.valueOf(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        v0Var.Q = 1;
        v0Var.R = 1;
        v0Var.S = 1;
        v0Var.T = 1;
        Boolean bool = Boolean.TRUE;
        v0Var.f12468y = bool;
        v0Var.z = null;
        v0Var.A = null;
        v0Var.B = null;
        v0Var.C = null;
        v0Var.D = bool;
        v0Var.E = bool;
        v0Var.F = xVar;
        v0Var.G = fValueOf;
        v0Var.H = null;
        v0Var.U = 1;
        v0Var.I = null;
        v0Var.J = null;
        v0Var.K = fValueOf;
        v0Var.L = null;
        v0Var.M = fValueOf;
        v0Var.V = 1;
        v0Var.W = 1;
        return v0Var;
    }

    public final Object clone() {
        v0 v0Var = (v0) super.clone();
        g0[] g0VarArr = this.f12462r;
        if (g0VarArr != null) {
            v0Var.f12462r = (g0[]) g0VarArr.clone();
        }
        return v0Var;
    }
}
