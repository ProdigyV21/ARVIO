package gb;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f15836a = new ArrayList(20);

    public final void a(String str, String str2) {
        t7.a.g(str);
        t7.a.h(str2, str);
        c(str, str2);
    }

    public final void b(String str) {
        int iE0 = kotlin.text.o.e0(str, ':', 1, false, 4);
        if (iE0 != -1) {
            c(str.substring(0, iE0), str.substring(iE0 + 1));
        } else if (str.charAt(0) == ':') {
            c("", str.substring(1));
        } else {
            c("", str);
        }
    }

    public final void c(String str, String str2) {
        ArrayList arrayList = this.f15836a;
        arrayList.add(str);
        arrayList.add(kotlin.text.o.L0(str2).toString());
    }

    public final void d(String str, String str2) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = str.charAt(i10);
            if ('!' > cCharAt || cCharAt >= 127) {
                throw new IllegalArgumentException(ib.c.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i10), str).toString());
            }
        }
        c(str, str2);
    }

    public final a0 e() {
        return new a0((String[]) this.f15836a.toArray(new String[0]));
    }

    public final String f(String str) {
        ArrayList arrayList = this.f15836a;
        int size = arrayList.size() - 2;
        int iA = i7.n.a(size, 0, -2);
        if (iA > size) {
            return null;
        }
        while (!str.equalsIgnoreCase((String) arrayList.get(size))) {
            if (size == iA) {
                return null;
            }
            size -= 2;
        }
        return (String) arrayList.get(size + 1);
    }

    public final void g(String str) {
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f15836a;
            if (i10 >= arrayList.size()) {
                return;
            }
            if (str.equalsIgnoreCase((String) arrayList.get(i10))) {
                arrayList.remove(i10);
                arrayList.remove(i10);
                i10 -= 2;
            }
            i10 += 2;
        }
    }

    public final void h(String str, String str2) {
        t7.a.g(str);
        t7.a.h(str2, str);
        g(str);
        c(str, str2);
    }
}
