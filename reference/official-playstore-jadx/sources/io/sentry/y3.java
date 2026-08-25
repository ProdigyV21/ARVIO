package io.sentry;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class y3 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Integer f18063i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f18064l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f18065m;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && y3.class == obj.getClass()) {
            y3 y3Var = (y3) obj;
            if (ac.b.C(this.f18063i, y3Var.f18063i) && ac.b.C(this.f18064l, y3Var.f18064l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f18063i, this.f18064l});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) fVar.f15416i;
        fVar.j();
        if (this.f18063i != null) {
            fVar.S("segment_id");
            fVar.d0(this.f18063i);
        }
        HashMap map = this.f18065m;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f18065m, str, fVar, str, iLogger);
            }
        }
        fVar.K();
        cVar.f18024p = true;
        if (this.f18063i != null) {
            cVar.y();
            cVar.i();
            cVar.f18019i.append((CharSequence) "\n");
        }
        List list = this.f18064l;
        if (list != null) {
            fVar.b0(iLogger, list);
        }
        cVar.f18024p = false;
    }
}
