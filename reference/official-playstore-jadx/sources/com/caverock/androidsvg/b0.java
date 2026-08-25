package com.caverock.androidsvg;

import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b0 extends b1 implements z0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List f12257h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Boolean f12258i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Matrix f12259j;
    public int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f12260l;

    @Override // com.caverock.androidsvg.z0
    public final void g(d1 d1Var) throws SVGParseException {
        if (d1Var instanceof u0) {
            this.f12257h.add(d1Var);
            return;
        }
        throw new SVGParseException("Gradient elements cannot contain " + d1Var + " elements.");
    }

    @Override // com.caverock.androidsvg.z0
    public final List getChildren() {
        return this.f12257h;
    }
}
