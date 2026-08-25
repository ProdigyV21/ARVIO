package com.caverock.androidsvg;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f12394c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f12395d = null;

    public o(int i10, String str) {
        this.f12392a = 0;
        this.f12393b = null;
        this.f12392a = i10 == 0 ? 1 : i10;
        this.f12393b = str;
    }

    public final void a(int i10, String str, String str2) {
        if (this.f12394c == null) {
            this.f12394c = new ArrayList();
        }
        this.f12394c.add(new b(str, i10, str2));
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        int i10 = this.f12392a;
        if (i10 == 2) {
            sb2.append("> ");
        } else if (i10 == 3) {
            sb2.append("+ ");
        }
        String str = this.f12393b;
        if (str == null) {
            str = "*";
        }
        sb2.append(str);
        ArrayList<b> arrayList = this.f12394c;
        if (arrayList != null) {
            for (b bVar : arrayList) {
                sb2.append('[');
                String str2 = bVar.f12254a;
                String str3 = bVar.f12256c;
                sb2.append(str2);
                int iC = h.f0.c(bVar.f12255b);
                if (iC == 1) {
                    sb2.append('=');
                    sb2.append(str3);
                } else if (iC == 2) {
                    sb2.append("~=");
                    sb2.append(str3);
                } else if (iC == 3) {
                    sb2.append("|=");
                    sb2.append(str3);
                }
                sb2.append(']');
            }
        }
        ArrayList<e> arrayList2 = this.f12395d;
        if (arrayList2 != null) {
            for (e eVar : arrayList2) {
                sb2.append(':');
                sb2.append(eVar);
            }
        }
        return sb2.toString();
    }
}
