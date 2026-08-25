package com.caverock.androidsvg;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v0 f12280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f12281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f12282c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint f12283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f12284e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public u f12285f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public u f12286g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f12287h;

    public c2() {
        Paint paint = new Paint();
        this.f12283d = paint;
        paint.setFlags(193);
        paint.setHinting(0);
        paint.setStyle(Paint.Style.FILL);
        Typeface typeface = Typeface.DEFAULT;
        paint.setTypeface(typeface);
        Paint paint2 = new Paint();
        this.f12284e = paint2;
        paint2.setFlags(193);
        paint2.setHinting(0);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setTypeface(typeface);
        this.f12280a = v0.a();
    }

    public c2(c2 c2Var) {
        this.f12281b = c2Var.f12281b;
        this.f12282c = c2Var.f12282c;
        this.f12283d = new Paint(c2Var.f12283d);
        this.f12284e = new Paint(c2Var.f12284e);
        u uVar = c2Var.f12285f;
        if (uVar != null) {
            this.f12285f = new u(uVar);
        }
        u uVar2 = c2Var.f12286g;
        if (uVar2 != null) {
            this.f12286g = new u(uVar2);
        }
        this.f12287h = c2Var.f12287h;
        try {
            this.f12280a = (v0) c2Var.f12280a.clone();
        } catch (CloneNotSupportedException e5) {
            Log.e("SVGAndroidRenderer", "Unexpected clone error", e5);
            this.f12280a = v0.a();
        }
    }
}
