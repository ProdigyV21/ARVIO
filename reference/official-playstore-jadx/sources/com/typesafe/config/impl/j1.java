package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public abstract class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w1 f14561a = w1.g("path parameter");

    public static void a(String str, ArrayList arrayList, boolean z) {
        int iIndexOf = z ? -1 : str.indexOf(46);
        i1 i1Var = (i1) a0.c.h(1, arrayList);
        if (iIndexOf >= 0) {
            i1Var.f14553a.append(str.substring(0, iIndexOf));
            arrayList.add(new i1());
            a(str.substring(iIndexOf + 1), arrayList, false);
        } else {
            i1Var.f14553a.append(str);
            if (z && i1Var.f14553a.length() == 0) {
                i1Var.f14554b = true;
            }
        }
    }

    public static g1 b(g1 g1Var, String str, int i10) {
        int iLastIndexOf = str.lastIndexOf(46, i10 - 1);
        g1 g1Var2 = new g1(str.substring(iLastIndexOf + 1, i10), g1Var);
        return iLastIndexOf < 0 ? g1Var2 : b(g1Var2, str, iLastIndexOf);
    }

    public static g1 c(Iterator it, l6.k kVar, String str, ArrayList arrayList) {
        String strA;
        ArrayList<i1> arrayList2 = new ArrayList();
        arrayList2.add(new i1());
        if (!it.hasNext()) {
            throw new ConfigException.BadPath(kVar, str, "Expecting a field name or path here, but got nothing");
        }
        while (it.hasNext()) {
            c2 c2Var = (c2) it.next();
            if (arrayList != null) {
                arrayList.add(c2Var);
            }
            c2 c2Var2 = m2.f14581a;
            if (!(c2Var instanceof g2)) {
                if (m2.c(c2Var)) {
                    a(m2.b(c2Var).M(), arrayList2, true);
                } else if (c2Var != m2.f14582b) {
                    if (c2Var instanceof l2) {
                        g gVarB = m2.b(c2Var);
                        if (arrayList != null) {
                            arrayList.remove(arrayList.size() - 1);
                            arrayList.addAll(d(c2Var));
                        }
                        strA = gVarB.M();
                    } else {
                        if (!(c2Var instanceof k2)) {
                            throw new ConfigException.BadPath(kVar, str, "Token not allowed in path expression: " + c2Var + " (you can double-quote this token if you really want it here)");
                        }
                        if (arrayList != null) {
                            arrayList.remove(arrayList.size() - 1);
                            arrayList.addAll(d(c2Var));
                        }
                        strA = m2.a(c2Var);
                    }
                    a(strA, arrayList2, false);
                } else {
                    continue;
                }
            }
        }
        Stack stack = new Stack();
        for (i1 i1Var : arrayList2) {
            if (i1Var.f14553a.length() == 0 && !i1Var.f14554b) {
                throw new ConfigException.BadPath(kVar, str, "path has a leading, trailing, or two adjacent period '.' (use quoted \"\" empty string if you want an empty element)");
            }
            stack.push(i1Var.f14553a.toString());
        }
        g1 g1Var = null;
        while (!stack.isEmpty()) {
            g1Var = new g1((String) stack.pop(), g1Var);
        }
        return g1Var;
    }

    public static List d(c2 c2Var) {
        String strE = c2Var.e();
        if (strE.equals(".")) {
            return Collections.singletonList(c2Var);
        }
        String[] strArrSplit = strE.split("\\.");
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplit) {
            l6.k kVarD = c2Var.d();
            c2 c2Var2 = m2.f14581a;
            arrayList.add(new k2(kVarD, str));
            arrayList.add(new k2(c2Var.d(), "."));
        }
        if (strE.charAt(strE.length() - 1) != '.') {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }
}
