package d7;

import d7.j;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i {
    public static j.a a(j.a aVar, j.b bVar) {
        if (p.a(aVar.getKey(), bVar)) {
            return aVar;
        }
        return null;
    }

    public static j b(j.a aVar, j.b bVar) {
        return p.a(aVar.getKey(), bVar) ? k.f14688i : aVar;
    }
}
