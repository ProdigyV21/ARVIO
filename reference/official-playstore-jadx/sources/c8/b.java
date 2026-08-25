package c8;

import f9.c;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p8.b0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f7422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.b f7423b;

    static {
        List listE = t7.a.E(b0.f21088a, b0.f21095h, b0.f21096i, b0.f21090c, b0.f21091d, b0.f21093f);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listE.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(f9.b.j((c) it.next()));
        }
        f7422a = linkedHashSet;
        f7423b = f9.b.j(b0.f21094g);
    }
}
