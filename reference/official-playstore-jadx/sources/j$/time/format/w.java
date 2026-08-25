package j$.time.format;

import j$.time.DateTimeException;
import j$.time.temporal.TemporalAccessor;

/* JADX INFO: loaded from: classes2.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TemporalAccessor f18299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateTimeFormatter f18300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f18301c;

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public w(j$.time.temporal.TemporalAccessor r10, j$.time.format.DateTimeFormatter r11) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.w.<init>(j$.time.temporal.TemporalAccessor, j$.time.format.DateTimeFormatter):void");
    }

    public final Object b(j$.time.c cVar) {
        TemporalAccessor temporalAccessor = this.f18299a;
        Object objG = temporalAccessor.G(cVar);
        if (objG != null || this.f18301c != 0) {
            return objG;
        }
        throw new DateTimeException("Unable to extract " + cVar + " from temporal " + temporalAccessor);
    }

    public final Long a(j$.time.temporal.o oVar) {
        int i10 = this.f18301c;
        TemporalAccessor temporalAccessor = this.f18299a;
        if (i10 <= 0 || temporalAccessor.e(oVar)) {
            return Long.valueOf(temporalAccessor.H(oVar));
        }
        return null;
    }

    public final String toString() {
        return this.f18299a.toString();
    }
}
