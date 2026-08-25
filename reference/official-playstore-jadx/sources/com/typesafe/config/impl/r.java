package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a2 f14614a = new a2(null);

    public static String a(int i10) {
        return i10 == 10 ? "newline" : i10 == 9 ? "tab" : i10 == -1 ? "end of file" : (i10 < 0 || i10 > 31) ? String.format("%c", Integer.valueOf(i10)) : String.format("control character 0x%x", Integer.valueOf(i10));
    }

    public static g b(g gVar, int i10) {
        int iB = gVar.b();
        w1 w1Var = gVar.f14537i;
        if (iB == 6) {
            String str = (String) gVar.a();
            int iC = h.f0.c(i10);
            if (iC != 2) {
                return iC != 3 ? (iC == 4 && str.equals("null")) ? new m0(w1Var) : gVar : (str.equals("true") || str.equals("yes") || str.equals("on")) ? new i(w1Var, true) : (str.equals("false") || str.equals("no") || str.equals("off")) ? new i(w1Var, false) : gVar;
            }
            try {
                try {
                    return new a0(w1Var, Long.parseLong(str), str);
                } catch (NumberFormatException unused) {
                    return new n(w1Var, Double.parseDouble(str), str);
                }
            } catch (NumberFormatException unused2) {
                return gVar;
            }
        }
        if (i10 == 6) {
            int iC2 = h.f0.c(gVar.b());
            return (iC2 == 2 || iC2 == 3) ? new q0(w1Var, gVar.M()) : gVar;
        }
        if (i10 != 2 || gVar.b() != 1) {
            return gVar;
        }
        c cVar = (c) gVar;
        HashMap map = new HashMap();
        for (String str2 : cVar.keySet()) {
            try {
                int i11 = Integer.parseInt(str2, 10);
                if (i11 >= 0) {
                    map.put(Integer.valueOf(i11), cVar.get(str2));
                }
            } catch (NumberFormatException unused3) {
            }
        }
        if (map.isEmpty()) {
            return gVar;
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new u0(0));
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((Map.Entry) it.next()).getValue());
        }
        return new t1(w1Var, arrayList2, com.arflix.tv.data.repository.g.a(arrayList2));
    }
}
