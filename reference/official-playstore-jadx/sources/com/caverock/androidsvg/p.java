package com.caverock.androidsvg;

import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f12398c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f12396a = d.f12289l;

    public p(int i10) {
        this.f12397b = i10;
    }

    public static int a(ArrayList arrayList, int i10, b1 b1Var) {
        int i11 = 0;
        if (i10 < 0) {
            return 0;
        }
        Object obj = arrayList.get(i10);
        z0 z0Var = b1Var.f12293b;
        if (obj != z0Var) {
            return -1;
        }
        Iterator it = z0Var.getChildren().iterator();
        while (it.hasNext()) {
            if (((d1) it.next()) == b1Var) {
                return i11;
            }
            i11++;
        }
        return -1;
    }

    public static ArrayList c(c cVar) {
        ArrayList arrayList = new ArrayList();
        while (!cVar.j()) {
            String str = (String) cVar.f12381c;
            String strSubstring = null;
            if (!cVar.j()) {
                int i10 = cVar.f12379a;
                char cCharAt = str.charAt(i10);
                if ((cCharAt < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z')) {
                    cVar.f12379a = i10;
                } else {
                    int iD = cVar.d();
                    while (true) {
                        if ((iD < 65 || iD > 90) && (iD < 97 || iD > 122)) {
                            break;
                        }
                        iD = cVar.d();
                    }
                    strSubstring = str.substring(i10, cVar.f12379a);
                }
            }
            if (strSubstring == null) {
                break;
            }
            try {
                arrayList.add(d.valueOf(strSubstring));
            } catch (IllegalArgumentException unused) {
            }
            if (!cVar.u()) {
                break;
            }
        }
        return arrayList;
    }

    public static boolean f(n nVar, int i10, ArrayList arrayList, int i11, b1 b1Var) {
        o oVar = (o) nVar.f12377a.get(i10);
        if (!i(oVar, b1Var)) {
            return false;
        }
        int i12 = oVar.f12392a;
        if (i12 == 1) {
            if (i10 != 0) {
                while (i11 >= 0) {
                    if (!h(nVar, i10 - 1, arrayList, i11)) {
                        i11--;
                    }
                }
                return false;
            }
            return true;
        }
        if (i12 == 2) {
            return h(nVar, i10 - 1, arrayList, i11);
        }
        int iA = a(arrayList, i11, b1Var);
        if (iA <= 0) {
            return false;
        }
        return f(nVar, i10 - 1, arrayList, i11, (b1) b1Var.f12293b.getChildren().get(iA - 1));
    }

    public static boolean g(n nVar, b1 b1Var) {
        ArrayList arrayList = new ArrayList();
        Object obj = b1Var.f12293b;
        while (true) {
            if (obj == null) {
                break;
            }
            arrayList.add(0, obj);
            obj = ((d1) obj).f12293b;
        }
        int size = arrayList.size() - 1;
        ArrayList arrayList2 = nVar.f12377a;
        if ((arrayList2 == null ? 0 : arrayList2.size()) == 1) {
            return i((o) nVar.f12377a.get(0), b1Var);
        }
        ArrayList arrayList3 = nVar.f12377a;
        return f(nVar, (arrayList3 != null ? arrayList3.size() : 0) - 1, arrayList, size, b1Var);
    }

    public static boolean h(n nVar, int i10, ArrayList arrayList, int i11) {
        o oVar = (o) nVar.f12377a.get(i10);
        b1 b1Var = (b1) arrayList.get(i11);
        if (!i(oVar, b1Var)) {
            return false;
        }
        int i12 = oVar.f12392a;
        if (i12 == 1) {
            if (i10 != 0) {
                while (i11 > 0) {
                    i11--;
                    if (h(nVar, i10 - 1, arrayList, i11)) {
                    }
                }
                return false;
            }
            return true;
        }
        if (i12 == 2) {
            return h(nVar, i10 - 1, arrayList, i11 - 1);
        }
        int iA = a(arrayList, i11, b1Var);
        if (iA <= 0) {
            return false;
        }
        return f(nVar, i10 - 1, arrayList, i11, (b1) b1Var.f12293b.getChildren().get(iA - 1));
    }

    public static boolean i(o oVar, b1 b1Var) {
        ArrayList arrayList;
        String str = oVar.f12393b;
        if (str != null && !str.equals(b1Var.n().toLowerCase(Locale.US))) {
            return false;
        }
        ArrayList<b> arrayList2 = oVar.f12394c;
        if (arrayList2 != null) {
            for (b bVar : arrayList2) {
                String str2 = bVar.f12254a;
                String str3 = bVar.f12256c;
                if (str2.equals(TtmlNode.ATTR_ID)) {
                    if (!str3.equals(b1Var.f12261c)) {
                        return false;
                    }
                } else if (!str2.equals("class") || (arrayList = b1Var.f12265g) == null || !arrayList.contains(str3)) {
                    return false;
                }
            }
        }
        ArrayList arrayList3 = oVar.f12395d;
        if (arrayList3 == null) {
            return true;
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            if (!((e) it.next()).a(b1Var)) {
                return false;
            }
        }
        return true;
    }

    public final void b(m mVar, c cVar) throws a {
        int iIntValue;
        char cCharAt;
        int iW;
        String strY = cVar.y();
        cVar.v();
        if (strY == null) {
            throw new a("Invalid '@' rule");
        }
        int i10 = 0;
        if (!this.f12398c && strY.equals(LinkHeader.Parameters.Media)) {
            ArrayList<d> arrayListC = c(cVar);
            if (!cVar.g('{')) {
                throw new a("Invalid @media rule: missing rule set");
            }
            cVar.v();
            for (d dVar : arrayListC) {
                if (dVar == d.f12288i || dVar == this.f12396a) {
                    this.f12398c = true;
                    mVar.b(e(cVar));
                    this.f12398c = false;
                    break;
                }
            }
            e(cVar);
            if (!cVar.j() && !cVar.g('}')) {
                throw new a("Invalid @media rule: expected '}' at end of rule set");
            }
        } else if (this.f12398c || !strY.equals("import")) {
            Log.w("CSSParser", "Ignoring @" + strY + " rule");
            while (!cVar.j() && ((iIntValue = cVar.m().intValue()) != 59 || i10 != 0)) {
                if (iIntValue != 123) {
                    if (iIntValue == 125 && i10 > 0 && i10 - 1 == 0) {
                        break;
                    }
                } else {
                    i10++;
                }
            }
        } else {
            String strX = null;
            if (!cVar.j()) {
                int i11 = cVar.f12379a;
                if (cVar.h("url(")) {
                    cVar.v();
                    String strX2 = cVar.x();
                    if (strX2 == null) {
                        String str = (String) cVar.f12381c;
                        StringBuilder sb2 = new StringBuilder();
                        while (!cVar.j() && (cCharAt = str.charAt(cVar.f12379a)) != '\'' && cCharAt != '\"' && cCharAt != '(' && cCharAt != ')' && !n0.l(cCharAt) && !Character.isISOControl((int) cCharAt)) {
                            cVar.f12379a++;
                            if (cCharAt == '\\') {
                                if (!cVar.j()) {
                                    int i12 = cVar.f12379a;
                                    cVar.f12379a = i12 + 1;
                                    cCharAt = str.charAt(i12);
                                    if (cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\f') {
                                        int iW2 = c.w(cCharAt);
                                        if (iW2 != -1) {
                                            for (int i13 = 1; i13 <= 5 && !cVar.j() && (iW = c.w(str.charAt(cVar.f12379a))) != -1; i13++) {
                                                cVar.f12379a++;
                                                iW2 = (iW2 * 16) + iW;
                                            }
                                            sb2.append((char) iW2);
                                        }
                                    }
                                }
                            }
                            sb2.append(cCharAt);
                        }
                        strX2 = sb2.length() == 0 ? null : sb2.toString();
                    }
                    if (strX2 == null) {
                        cVar.f12379a = i11;
                    } else {
                        cVar.v();
                        if (cVar.j() || cVar.h(")")) {
                            strX = strX2;
                        } else {
                            cVar.f12379a = i11;
                        }
                    }
                }
            }
            if (strX == null) {
                strX = cVar.x();
            }
            if (strX == null) {
                throw new a("Invalid @import rule: expected string or url()");
            }
            cVar.v();
            c(cVar);
            if (!cVar.j() && !cVar.g(';')) {
                throw new a("Invalid @media rule: expected '}' at end of rule set");
            }
        }
        cVar.v();
    }

    public final boolean d(m mVar, c cVar) throws a {
        ArrayList<n> arrayListZ = cVar.z();
        if (arrayListZ == null || arrayListZ.isEmpty()) {
            return false;
        }
        if (!cVar.g('{')) {
            throw new a("Malformed rule block: expected '{'");
        }
        cVar.v();
        v0 v0Var = new v0();
        do {
            String strY = cVar.y();
            cVar.v();
            if (!cVar.g(':')) {
                throw new a("Expected ':'");
            }
            cVar.v();
            String str = (String) cVar.f12381c;
            String strSubstring = null;
            if (!cVar.j()) {
                int i10 = cVar.f12379a;
                int iCharAt = str.charAt(i10);
                int i11 = i10;
                while (iCharAt != -1 && iCharAt != 59 && iCharAt != 125 && iCharAt != 33 && iCharAt != 10 && iCharAt != 13) {
                    if (!n0.l(iCharAt)) {
                        i11 = cVar.f12379a + 1;
                    }
                    iCharAt = cVar.d();
                }
                if (cVar.f12379a > i10) {
                    strSubstring = str.substring(i10, i11);
                } else {
                    cVar.f12379a = i10;
                }
            }
            if (strSubstring == null) {
                throw new a("Expected property value");
            }
            cVar.v();
            if (cVar.g('!')) {
                cVar.v();
                if (!cVar.h("important")) {
                    throw new a("Malformed rule set: found unexpected '!'");
                }
                cVar.v();
            }
            cVar.g(';');
            n2.D(v0Var, strY, strSubstring);
            cVar.v();
            if (cVar.j()) {
                break;
            }
        } while (!cVar.g('}'));
        cVar.v();
        for (n nVar : arrayListZ) {
            l lVar = new l();
            lVar.f12361a = nVar;
            lVar.f12362b = v0Var;
            lVar.f12363c = this.f12397b;
            mVar.a(lVar);
        }
        return true;
    }

    public final m e(c cVar) {
        m mVar = new m();
        while (!cVar.j()) {
            try {
                if (!cVar.h("<!--") && !cVar.h("-->")) {
                    if (!cVar.g('@')) {
                        if (!d(mVar, cVar)) {
                            break;
                        }
                    } else {
                        b(mVar, cVar);
                    }
                }
            } catch (a e5) {
                Log.e("CSSParser", "CSS parser terminated early due to error: " + e5.getMessage());
                return mVar;
            }
        }
        return mVar;
    }
}
