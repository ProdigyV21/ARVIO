package j$.time.format;

import j$.time.temporal.WeekFields;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final char f18279g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f18280h;

    @Override // j$.time.format.i, j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        return f(uVar.f18290a.f18200b).m(uVar, charSequence, i10);
    }

    @Override // j$.time.format.i, j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        return f(wVar.f18300b.f18200b).l(wVar, sb2);
    }

    public r(char c10, int i10, int i11, int i12, int i13) {
        super(null, i11, i12, c0.NOT_NEGATIVE, i13);
        this.f18279g = c10;
        this.f18280h = i10;
    }

    @Override // j$.time.format.i
    public final i d() {
        if (this.f18252e == -1) {
            return this;
        }
        return new r(this.f18279g, this.f18280h, this.f18249b, this.f18250c, -1);
    }

    @Override // j$.time.format.i
    public final i e(int i10) {
        return new r(this.f18279g, this.f18280h, this.f18249b, this.f18250c, this.f18252e + i10);
    }

    public final i f(Locale locale) {
        j$.time.temporal.t tVar;
        WeekFields weekFieldsOf = WeekFields.of(locale);
        char c10 = this.f18279g;
        if (c10 == 'W') {
            tVar = weekFieldsOf.f18344d;
        } else {
            if (c10 == 'Y') {
                j$.time.temporal.t tVar2 = weekFieldsOf.f18346f;
                int i10 = this.f18280h;
                if (i10 == 2) {
                    return new o(tVar2, 2, 2, o.f18272h, this.f18252e);
                }
                return new i(tVar2, i10, 19, i10 < 4 ? c0.NORMAL : c0.EXCEEDS_PAD, this.f18252e);
            }
            if (c10 == 'c' || c10 == 'e') {
                tVar = weekFieldsOf.f18343c;
            } else {
                if (c10 != 'w') {
                    throw new IllegalStateException("unreachable");
                }
                tVar = weekFieldsOf.f18345e;
            }
        }
        return new i(tVar, this.f18249b, this.f18250c, c0.NOT_NEGATIVE, this.f18252e);
    }

    @Override // j$.time.format.i
    public final String toString() {
        StringBuilder sb2 = new StringBuilder(30);
        sb2.append("Localized(");
        int i10 = this.f18280h;
        char c10 = this.f18279g;
        if (c10 != 'Y') {
            if (c10 == 'W') {
                sb2.append("WeekOfMonth");
            } else if (c10 == 'c' || c10 == 'e') {
                sb2.append("DayOfWeek");
            } else if (c10 == 'w') {
                sb2.append("WeekOfWeekBasedYear");
            }
            sb2.append(",");
            sb2.append(i10);
        } else if (i10 == 1) {
            sb2.append("WeekBasedYear");
        } else if (i10 == 2) {
            sb2.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
        } else {
            sb2.append("WeekBasedYear,");
            sb2.append(i10);
            sb2.append(",19,");
            sb2.append(i10 < 4 ? c0.NORMAL : c0.EXCEEDS_PAD);
        }
        sb2.append(")");
        return sb2.toString();
    }
}
