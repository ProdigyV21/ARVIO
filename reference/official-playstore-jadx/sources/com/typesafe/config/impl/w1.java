package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class w1 implements l6.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f14636c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f14637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14638e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f14639f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f14640g;

    public w1(String str, int i10, int i11, int i12, String str2, String str3, List list) {
        if (str == null) {
            throw new ConfigException.BugOrBroken(null, "description may not be null");
        }
        this.f14634a = str;
        this.f14635b = i10;
        this.f14636c = i11;
        this.f14637d = i12;
        this.f14638e = str2;
        this.f14639f = str3;
        this.f14640g = list;
    }

    public static l6.k d(ArrayList arrayList) {
        if (arrayList.isEmpty()) {
            throw new ConfigException.BugOrBroken(null, "can't merge empty list of origins");
        }
        if (arrayList.size() == 1) {
            return (l6.k) arrayList.iterator().next();
        }
        if (arrayList.size() == 2) {
            Iterator it = arrayList.iterator();
            return e((w1) it.next(), (w1) it.next());
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add((w1) ((l6.k) it2.next()));
        }
        while (arrayList2.size() > 2) {
            w1 w1Var = (w1) a0.c.h(1, arrayList2);
            arrayList2.remove(arrayList2.size() - 1);
            w1 w1Var2 = (w1) arrayList2.get(arrayList2.size() - 1);
            arrayList2.remove(arrayList2.size() - 1);
            w1 w1Var3 = (w1) arrayList2.get(arrayList2.size() - 1);
            arrayList2.remove(arrayList2.size() - 1);
            arrayList2.add(h(w1Var3, w1Var2) >= h(w1Var2, w1Var) ? e(e(w1Var3, w1Var2), w1Var) : e(w1Var3, e(w1Var2, w1Var)));
        }
        return d(arrayList2);
    }

    public static w1 e(w1 w1Var, w1 w1Var2) {
        int i10;
        int iMax;
        List list;
        int i11 = w1Var.f14637d;
        String str = w1Var.f14639f;
        String str2 = w1Var.f14638e;
        List list2 = w1Var.f14640g;
        int i12 = w1Var2.f14637d;
        List list3 = w1Var2.f14640g;
        int iMin = w1Var2.f14635b;
        if (i11 != i12) {
            i11 = 1;
        }
        int i13 = i11;
        String strN = w1Var.f14634a;
        String strSubstring = w1Var2.f14634a;
        if (strN.startsWith("merge of ")) {
            strN = strN.substring(9);
        }
        if (strSubstring.startsWith("merge of ")) {
            strSubstring = strSubstring.substring(9);
        }
        if (strN.equals(strSubstring)) {
            int i14 = w1Var.f14635b;
            if (i14 >= 0) {
                iMin = iMin < 0 ? i14 : Math.min(i14, iMin);
            }
            iMax = Math.max(w1Var.f14636c, w1Var2.f14636c);
            i10 = iMin;
        } else {
            String strDescription = w1Var.description();
            String strDescription2 = w1Var2.description();
            if (strDescription.startsWith("merge of ")) {
                strDescription = strDescription.substring(9);
            }
            if (strDescription2.startsWith("merge of ")) {
                strDescription2 = strDescription2.substring(9);
            }
            strN = androidx.compose.material3.d.n("merge of ", strDescription, ",", strDescription2);
            i10 = -1;
            iMax = -1;
        }
        String str3 = strN;
        String str4 = y.a(str2, w1Var2.f14638e) ? str2 : null;
        String str5 = y.a(str, w1Var2.f14639f) ? str : null;
        if (y.a(list2, list3)) {
            list = list2;
        } else {
            ArrayList arrayList = new ArrayList();
            if (list2 != null) {
                arrayList.addAll(list2);
            }
            if (list3 != null) {
                arrayList.addAll(list3);
            }
            list = arrayList;
        }
        return new w1(str3, i10, iMax, i13, str4, str5, list);
    }

    public static w1 f(URL url, String str) {
        String string;
        if (url != null) {
            StringBuilder sbP = androidx.fragment.app.a2.p(str, " @ ");
            sbP.append(url.toExternalForm());
            string = sbP.toString();
        } else {
            string = str;
        }
        return new w1(string, -1, -1, 4, url != null ? url.toExternalForm() : null, str, null);
    }

    public static w1 g(String str) {
        return new w1(str, -1, -1, 1, null, null, null);
    }

    public static int h(w1 w1Var, w1 w1Var2) {
        int i10 = w1Var.f14637d == w1Var2.f14637d ? 1 : 0;
        if (!w1Var.f14634a.equals(w1Var2.f14634a)) {
            return i10;
        }
        int i11 = i10 + 1;
        if (w1Var.f14635b == w1Var2.f14635b) {
            i11 = i10 + 2;
        }
        if (w1Var.f14636c == w1Var2.f14636c) {
            i11++;
        }
        if (y.a(w1Var.f14638e, w1Var2.f14638e)) {
            i11++;
        }
        return y.a(w1Var.f14639f, w1Var2.f14639f) ? i11 + 1 : i11;
    }

    @Override // l6.k
    public final int a() {
        return this.f14635b;
    }

    public final w1 c(List list) {
        List list2 = this.f14640g;
        if (y.a(list, list2) || list == null) {
            return this;
        }
        if (list2 == null) {
            return i(list);
        }
        ArrayList arrayList = new ArrayList(list2.size() + list.size());
        arrayList.addAll(list2);
        arrayList.addAll(list);
        return i(arrayList);
    }

    @Override // l6.k
    public final String description() {
        String str = this.f14634a;
        int i10 = this.f14635b;
        if (i10 < 0) {
            return str;
        }
        int i11 = this.f14636c;
        if (i11 == i10) {
            return str + ": " + i10;
        }
        return str + ": " + i10 + "-" + i11;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof w1)) {
            return false;
        }
        w1 w1Var = (w1) obj;
        return this.f14634a.equals(w1Var.f14634a) && this.f14635b == w1Var.f14635b && this.f14636c == w1Var.f14636c && this.f14637d == w1Var.f14637d && y.a(this.f14638e, w1Var.f14638e) && y.a(this.f14639f, w1Var.f14639f);
    }

    public final int hashCode() {
        int iC = (h.f0.c(this.f14637d) + ((((androidx.compose.foundation.c.c(41, 41, this.f14634a) + this.f14635b) * 41) + this.f14636c) * 41)) * 41;
        String str = this.f14638e;
        if (str != null) {
            iC = androidx.compose.foundation.c.c(iC, 41, str);
        }
        String str2 = this.f14639f;
        return str2 != null ? androidx.compose.foundation.c.c(iC, 41, str2) : iC;
    }

    public final w1 i(List list) {
        if (y.a(list, this.f14640g)) {
            return this;
        }
        return new w1(this.f14634a, this.f14635b, this.f14636c, this.f14637d, this.f14638e, this.f14639f, list);
    }

    @Override // l6.k
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final w1 b(int i10) {
        if (i10 == this.f14635b && i10 == this.f14636c) {
            return this;
        }
        return new w1(this.f14634a, i10, i10, this.f14637d, this.f14638e, this.f14639f, this.f14640g);
    }

    public final String toString() {
        return a0.c.p(new StringBuilder("ConfigOrigin("), this.f14634a, ")");
    }
}
