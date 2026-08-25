package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public s f17553i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f17554l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f17555m;

    public static d a(d dVar, y6 y6Var) {
        ArrayList arrayList = new ArrayList();
        if (y6Var.getProguardUuid() != null) {
            DebugImage debugImage = new DebugImage();
            debugImage.setType(DebugImage.PROGUARD);
            debugImage.setUuid(y6Var.getProguardUuid());
            arrayList.add(debugImage);
        }
        for (String str : y6Var.getBundleIds()) {
            DebugImage debugImage2 = new DebugImage();
            debugImage2.setType(DebugImage.JVM);
            debugImage2.setDebugId(str);
            arrayList.add(debugImage2);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (dVar == null) {
            dVar = new d();
        }
        List list = dVar.f17554l;
        if (list == null) {
            dVar.f17554l = new ArrayList(arrayList);
            return dVar;
        }
        list.addAll(arrayList);
        return dVar;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17553i != null) {
            fVar.S("sdk_info");
            fVar.b0(iLogger, this.f17553i);
        }
        if (this.f17554l != null) {
            fVar.S("images");
            fVar.b0(iLogger, this.f17554l);
        }
        HashMap map = this.f17555m;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17555m, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
