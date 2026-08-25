package j$.time.format;

import j$.time.chrono.Chronology;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ y f18222d;

    public a(y yVar) {
        this.f18222d = yVar;
    }

    @Override // j$.time.format.z
    public final String c(Chronology chronology, j$.time.temporal.o oVar, long j10, TextStyle textStyle, Locale locale) {
        return this.f18222d.a(j10, textStyle);
    }

    @Override // j$.time.format.z
    public final String d(j$.time.temporal.o oVar, long j10, TextStyle textStyle, Locale locale) {
        return this.f18222d.a(j10, textStyle);
    }

    @Override // j$.time.format.z
    public final Iterator e(Chronology chronology, j$.time.temporal.o oVar, TextStyle textStyle, Locale locale) {
        List list = (List) ((HashMap) this.f18222d.f18303b).get(textStyle);
        if (list != null) {
            return list.iterator();
        }
        return null;
    }

    @Override // j$.time.format.z
    public final Iterator f(j$.time.temporal.o oVar, TextStyle textStyle, Locale locale) {
        List list = (List) ((HashMap) this.f18222d.f18303b).get(textStyle);
        if (list != null) {
            return list.iterator();
        }
        return null;
    }
}
