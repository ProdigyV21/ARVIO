package j$.time.format;

import j$.time.chrono.Chronology;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class q implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j$.time.temporal.o f18275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextStyle f18276b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z f18277c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile i f18278d;

    public q(j$.time.temporal.o oVar, TextStyle textStyle, z zVar) {
        this.f18275a = oVar;
        this.f18276b = textStyle;
        this.f18277c = zVar;
    }

    @Override // j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        String strD;
        Long lA = wVar.a(this.f18275a);
        DateTimeFormatter dateTimeFormatter = wVar.f18300b;
        if (lA == null) {
            return false;
        }
        Chronology chronology = (Chronology) wVar.f18299a.G(j$.time.temporal.p.f18367b);
        if (chronology == null || chronology == j$.time.chrono.p.f18175d) {
            strD = this.f18277c.d(this.f18275a, lA.longValue(), this.f18276b, dateTimeFormatter.f18200b);
        } else {
            strD = this.f18277c.c(chronology, this.f18275a, lA.longValue(), this.f18276b, dateTimeFormatter.f18200b);
        }
        if (strD != null) {
            sb2.append(strD);
            return true;
        }
        if (this.f18278d == null) {
            this.f18278d = new i(this.f18275a, 1, 19, c0.NORMAL);
        }
        return this.f18278d.l(wVar, sb2);
    }

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        Iterator itF;
        z zVar = this.f18277c;
        j$.time.temporal.o oVar = this.f18275a;
        int length = charSequence.length();
        if (i10 >= 0 && i10 <= length) {
            boolean z = uVar.f18292c;
            DateTimeFormatter dateTimeFormatter = uVar.f18290a;
            TextStyle textStyle = z ? this.f18276b : null;
            Chronology chronology = uVar.c().f18225c;
            if (chronology == null && (chronology = uVar.f18290a.f18203e) == null) {
                chronology = j$.time.chrono.p.f18175d;
            }
            Chronology chronology2 = chronology;
            if (chronology2 == null || chronology2 == j$.time.chrono.p.f18175d) {
                itF = zVar.f(oVar, textStyle, dateTimeFormatter.f18200b);
            } else {
                itF = zVar.e(chronology2, oVar, textStyle, dateTimeFormatter.f18200b);
            }
            Iterator it = itF;
            if (it != null) {
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String str = (String) entry.getKey();
                    if (uVar.g(str, 0, charSequence, i10, str.length())) {
                        return uVar.f(this.f18275a, ((Long) entry.getValue()).longValue(), i10, str.length() + i10);
                    }
                }
                if (oVar == j$.time.temporal.a.ERA && !uVar.f18292c) {
                    Iterator it2 = chronology2.w().iterator();
                    while (it2.hasNext()) {
                        String string = ((j$.time.chrono.j) it2.next()).toString();
                        if (uVar.g(string, 0, charSequence, i10, string.length())) {
                            return uVar.f(this.f18275a, r7.getValue(), i10, string.length() + i10);
                        }
                    }
                }
                if (uVar.f18292c) {
                    return ~i10;
                }
            }
            if (this.f18278d == null) {
                this.f18278d = new i(this.f18275a, 1, 19, c0.NORMAL);
            }
            return this.f18278d.m(uVar, charSequence, i10);
        }
        throw new IndexOutOfBoundsException();
    }

    public final String toString() {
        TextStyle textStyle = TextStyle.FULL;
        j$.time.temporal.o oVar = this.f18275a;
        TextStyle textStyle2 = this.f18276b;
        if (textStyle2 == textStyle) {
            return "Text(" + oVar + ")";
        }
        return "Text(" + oVar + "," + textStyle2 + ")";
    }
}
