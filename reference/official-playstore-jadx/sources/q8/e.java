package q8;

import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import d8.p;
import h8.n;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.b0;
import kotlin.collections.h0;
import kotlin.collections.s;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f21343a = h0.t0(new x("PACKAGE", EnumSet.noneOf(n.class)), new x(CredentialProviderBaseController.TYPE_TAG, EnumSet.of(n.CLASS, n.FILE)), new x("ANNOTATION_TYPE", EnumSet.of(n.ANNOTATION_CLASS)), new x("TYPE_PARAMETER", EnumSet.of(n.TYPE_PARAMETER)), new x("FIELD", EnumSet.of(n.FIELD)), new x("LOCAL_VARIABLE", EnumSet.of(n.LOCAL_VARIABLE)), new x("PARAMETER", EnumSet.of(n.VALUE_PARAMETER)), new x("CONSTRUCTOR", EnumSet.of(n.CONSTRUCTOR)), new x("METHOD", EnumSet.of(n.FUNCTION, n.PROPERTY_GETTER, n.PROPERTY_SETTER)), new x("TYPE_USE", EnumSet.of(n.TYPE)));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f21344b = h0.t0(new x("RUNTIME", h8.m.f15873i), new x("CLASS", h8.m.f15874l), new x("SOURCE", h8.m.f15875m));

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.Map] */
    public static k9.b a(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof w8.m) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Iterable iterable = (EnumSet) f21343a.get(((w8.m) it.next()).e().d());
            if (iterable == null) {
                iterable = b0.f19686i;
            }
            kotlin.collections.x.b0(arrayList2, iterable);
        }
        ArrayList arrayList3 = new ArrayList(s.U(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new k9.i(f9.b.j(p.f14752u), f9.f.j(((n) it2.next()).name())));
        }
        return new k9.b(arrayList3, d.f21342i);
    }
}
