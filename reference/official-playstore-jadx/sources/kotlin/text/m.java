package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Pattern f19951i;

    public m(Pattern pattern) {
        this.f19951i = pattern;
    }

    public static k b(m mVar, CharSequence charSequence) {
        Matcher matcher = mVar.f19951i.matcher(charSequence);
        if (matcher.find(0)) {
            return new k(matcher, charSequence);
        }
        return null;
    }

    public static ga.l c(String str, m mVar) {
        mVar.getClass();
        if (str.length() >= 0) {
            return new ga.l(new com.arflix.tv.ui.screens.collections.e(mVar, str, 10), l.f19950i, 0);
        }
        StringBuilder sbS = a0.c.s(0, "Start index out of bounds: ", ", input length: ");
        sbS.append(str.length());
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public final boolean a(String str) {
        return this.f19951i.matcher(str).find();
    }

    public final k d(CharSequence charSequence) {
        Matcher matcher = this.f19951i.matcher(charSequence);
        if (matcher.matches()) {
            return new k(matcher, charSequence);
        }
        return null;
    }

    public final boolean e(String str) {
        return this.f19951i.matcher(str).matches();
    }

    public final String f(String str, CharSequence charSequence) {
        return this.f19951i.matcher(charSequence).replaceAll(str);
    }

    public final String g(String str, r7.l lVar) {
        k kVarB = b(this, str);
        if (kVarB == null) {
            return str.toString();
        }
        int length = str.length();
        StringBuilder sb2 = new StringBuilder(length);
        int i10 = 0;
        do {
            sb2.append((CharSequence) str, i10, kVarB.d().f22619i);
            sb2.append((CharSequence) lVar.invoke(kVarB));
            i10 = kVarB.d().f22620l + 1;
            kVarB = kVarB.next();
            if (i10 >= length) {
                break;
            }
        } while (kVarB != null);
        if (i10 < length) {
            sb2.append((CharSequence) str, i10, length);
        }
        return sb2.toString();
    }

    public final List h(String str) {
        int iEnd = 0;
        o.u0(0);
        Matcher matcher = this.f19951i.matcher(str);
        if (!matcher.find()) {
            return Collections.singletonList(str.toString());
        }
        ArrayList arrayList = new ArrayList(10);
        do {
            arrayList.add(str.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
        } while (matcher.find());
        arrayList.add(str.subSequence(iEnd, str.length()).toString());
        return arrayList;
    }

    public final String toString() {
        return this.f19951i.toString();
    }

    public m(String str) {
        this(Pattern.compile(str));
    }

    public m(String str, int i10) {
        this(Pattern.compile(str, 66));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public m(String str, Set set) {
        Iterator it = set.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= ((d) it.next()).getValue();
        }
        this(Pattern.compile(str, (value & 2) != 0 ? value | 64 : value));
    }
}
