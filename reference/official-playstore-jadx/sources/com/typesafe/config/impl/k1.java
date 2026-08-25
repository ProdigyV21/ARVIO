package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class k1 {
    public static v1 a(l6.k kVar, Set set) {
        HashMap map = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            if (key instanceof String) {
                String str = (String) key;
                int iLastIndexOf = str.lastIndexOf(46);
                String strSubstring = iLastIndexOf < 0 ? str : str.substring(iLastIndexOf + 1);
                int iLastIndexOf2 = str.lastIndexOf(46);
                String strSubstring2 = iLastIndexOf2 < 0 ? null : str.substring(0, iLastIndexOf2);
                g1 g1Var = new g1(strSubstring, null);
                while (strSubstring2 != null) {
                    int iLastIndexOf3 = strSubstring2.lastIndexOf(46);
                    String strSubstring3 = iLastIndexOf3 < 0 ? strSubstring2 : strSubstring2.substring(iLastIndexOf3 + 1);
                    int iLastIndexOf4 = strSubstring2.lastIndexOf(46);
                    strSubstring2 = iLastIndexOf4 < 0 ? null : strSubstring2.substring(0, iLastIndexOf4);
                    g1Var = new g1(strSubstring3, g1Var);
                }
                map.put(g1Var, entry.getValue());
            }
        }
        return b(kVar, map, true);
    }

    public static v1 b(l6.k kVar, HashMap map, boolean z) {
        HashSet hashSet = new HashSet();
        HashSet<g1> hashSet2 = new HashSet();
        for (g1 g1Var : map.keySet()) {
            hashSet2.add(g1Var);
            for (g1 g1VarD = g1Var.d(); g1VarD != null; g1VarD = g1VarD.d()) {
                hashSet.add(g1VarD);
            }
        }
        if (z) {
            hashSet2.removeAll(hashSet);
        } else {
            for (g1 g1Var2 : hashSet2) {
                if (hashSet.contains(g1Var2)) {
                    throw new ConfigException.BugOrBroken(null, "In the map, path '" + g1Var2.e() + "' occurs as both the parent object of a value and as a value. Because Map has no defined ordering, this is a broken situation.");
                }
            }
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            map3.put((g1) it.next(), new HashMap());
        }
        for (g1 g1Var3 : hashSet2) {
            g1 g1VarD2 = g1Var3.d();
            Map map4 = g1VarD2 != null ? (Map) map3.get(g1VarD2) : map2;
            g1 g1Var4 = g1Var3;
            while (true) {
                g1 g1Var5 = g1Var4.f14542b;
                if (g1Var5 == null) {
                    break;
                }
                g1Var4 = g1Var5;
            }
            String str = g1Var4.f14541a;
            Object obj = map.get(g1Var3);
            l6.i q0Var = z ? obj instanceof String ? new q0(kVar, (String) obj) : null : x.b(map.get(g1Var3), kVar);
            if (q0Var != null) {
                map4.put(str, q0Var);
            }
        }
        ArrayList<g1> arrayList = new ArrayList();
        arrayList.addAll(hashSet);
        Collections.sort(arrayList, new u0(1));
        for (g1 g1Var6 : arrayList) {
            Map map5 = (Map) map3.get(g1Var6);
            g1 g1VarD3 = g1Var6.d();
            Map map6 = g1VarD3 != null ? (Map) map3.get(g1VarD3) : map2;
            v1 v1Var = new v1(kVar, map5, 2, false);
            while (true) {
                g1 g1Var7 = g1Var6.f14542b;
                if (g1Var7 != null) {
                    g1Var6 = g1Var7;
                }
            }
            map6.put(g1Var6.f14541a, v1Var);
        }
        return new v1(kVar, map2, 2, false);
    }
}
