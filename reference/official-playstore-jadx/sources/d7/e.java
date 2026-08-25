package d7;

import d7.j;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {
    /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.internal.r, r7.l] */
    public static j.a a(g gVar, j.b bVar) {
        j.a aVar;
        if (!(bVar instanceof b)) {
            if (f.f14687i == bVar) {
                return gVar;
            }
            return null;
        }
        b bVar2 = (b) bVar;
        j.b key = gVar.getKey();
        if ((key == bVar2 || bVar2.f14684l == key) && (aVar = (j.a) bVar2.f14683i.invoke(gVar)) != null) {
            return aVar;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.internal.r, r7.l] */
    public static j b(g gVar, j.b bVar) {
        if (bVar instanceof b) {
            b bVar2 = (b) bVar;
            j.b key = gVar.getKey();
            if ((key != bVar2 && bVar2.f14684l != key) || ((j.a) bVar2.f14683i.invoke(gVar)) == null) {
                return gVar;
            }
        } else if (f.f14687i != bVar) {
            return gVar;
        }
        return k.f14688i;
    }
}
