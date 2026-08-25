package zb;

import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class f implements Appendable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f23282m = f.class.getName().concat(".disable");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f23283n = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final b f23284o = new b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final StringBuilder f23285i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f23286l;

    public f() {
        StringBuilder sb2 = new StringBuilder(80);
        this.f23286l = new ArrayList(5);
        this.f23285i = sb2;
    }

    public f a() {
        int i10;
        switch (1) {
            case 1:
                i10 = 0;
                break;
            case 2:
                i10 = 1;
                break;
            case 3:
                i10 = 2;
                break;
            case 4:
                i10 = 3;
                break;
            case 5:
                i10 = 4;
                break;
            case 6:
                i10 = 5;
                break;
            case 7:
                i10 = 6;
                break;
            case 8:
                i10 = 7;
                break;
            case 9:
                i10 = 8;
                break;
            case 10:
                i10 = 9;
                break;
            case 11:
                i10 = 21;
                break;
            case 12:
                i10 = 22;
                break;
            case 13:
                i10 = 23;
                break;
            case 14:
                i10 = 24;
                break;
            case 15:
                i10 = 25;
                break;
            case 16:
                i10 = 27;
                break;
            case 17:
                i10 = 28;
                break;
            case 18:
                i10 = 29;
                break;
            default:
                throw null;
        }
        this.f23286l.add(Integer.valueOf(i10));
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence) {
        this.f23285i.append(charSequence);
        return this;
    }

    public f b(c cVar) {
        this.f23286l.add(Integer.valueOf(cVar.f23280i + 30));
        return this;
    }

    public final void c() {
        ArrayList arrayList = this.f23286l;
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        StringBuilder sb2 = this.f23285i;
        if (size == 1 && ((Integer) arrayList.get(0)).intValue() == 0) {
            sb2.append((char) 27);
            sb2.append('[');
            sb2.append(GMTDateParser.MINUTES);
        } else {
            Object[] array = arrayList.toArray();
            sb2.append((char) 27);
            sb2.append('[');
            int length = array.length;
            for (int i10 = 0; i10 < length; i10++) {
                if (i10 != 0) {
                    sb2.append(';');
                }
                Object obj = array[i10];
                if (obj != null) {
                    sb2.append(obj);
                }
            }
            sb2.append(GMTDateParser.MINUTES);
        }
        arrayList.clear();
    }

    public f d() {
        return a();
    }

    public final String toString() {
        c();
        return this.f23285i.toString();
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence charSequence, int i10, int i11) {
        this.f23285i.append(charSequence, i10, i11);
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char c10) {
        this.f23285i.append(c10);
        return this;
    }
}
