package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public abstract class o1 extends y0 {
    @Override // com.caverock.androidsvg.y0, com.caverock.androidsvg.z0
    public final void g(d1 d1Var) throws SVGParseException {
        if (d1Var instanceof n1) {
            this.f12494i.add(d1Var);
            return;
        }
        throw new SVGParseException("Text content elements cannot contain " + d1Var + " elements.");
    }
}
