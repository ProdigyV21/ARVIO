package o2;

import a0.c;
import androidx.work.impl.model.g;
import androidx.work.impl.model.h;
import androidx.work.impl.model.j;
import androidx.work.impl.model.l;
import androidx.work.impl.model.p;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.x;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f20724a = t.f("DiagnosticsWrkr");

    public static final String a(l lVar, androidx.work.impl.model.t tVar, h hVar, List list) {
        String str;
        StringBuilder sb2 = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            p pVar = (p) it.next();
            j jVarN = a.a.N(pVar);
            String str2 = pVar.f6922a;
            g gVarD = hVar.d(jVarN);
            Integer numValueOf = gVarD != null ? Integer.valueOf(gVarD.f6912c) : null;
            String strU0 = x.u0(lVar.l(str2), ",", null, null, null, 62);
            String strU02 = x.u0(tVar.f(str2), ",", null, null, null, 62);
            StringBuilder sbT = c.t("\n", str2, "\t ");
            sbT.append(pVar.f6924c);
            sbT.append("\t ");
            sbT.append(numValueOf);
            sbT.append("\t ");
            switch (pVar.f6923b) {
                case 1:
                    str = "ENQUEUED";
                    break;
                case 2:
                    str = "RUNNING";
                    break;
                case 3:
                    str = "SUCCEEDED";
                    break;
                case 4:
                    str = "FAILED";
                    break;
                case 5:
                    str = "BLOCKED";
                    break;
                case 6:
                    str = "CANCELLED";
                    break;
                default:
                    throw null;
            }
            sbT.append(str);
            sbT.append("\t ");
            sbT.append(strU0);
            sbT.append("\t ");
            sbT.append(strU02);
            sbT.append('\t');
            sb2.append(sbT.toString());
        }
        return sb2.toString();
    }
}
