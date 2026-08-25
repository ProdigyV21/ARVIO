package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class p implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocaleList f2131a;

    public p(Object obj) {
        this.f2131a = (LocaleList) obj;
    }

    @Override // androidx.core.os.o
    public final String a() {
        return this.f2131a.toLanguageTags();
    }

    @Override // androidx.core.os.o
    public final Object b() {
        return this.f2131a;
    }

    public final boolean equals(Object obj) {
        return this.f2131a.equals(((o) obj).b());
    }

    @Override // androidx.core.os.o
    public final Locale get(int i10) {
        return this.f2131a.get(i10);
    }

    public final int hashCode() {
        return this.f2131a.hashCode();
    }

    @Override // androidx.core.os.o
    public final boolean isEmpty() {
        return this.f2131a.isEmpty();
    }

    @Override // androidx.core.os.o
    public final int size() {
        return this.f2131a.size();
    }

    public final String toString() {
        return this.f2131a.toString();
    }
}
