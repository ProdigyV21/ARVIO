package l7;

import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f20043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f20044b;

    public a(File file, List list) {
        this.f20043a = file;
        this.f20044b = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f20043a.equals(aVar.f20043a) && this.f20044b.equals(aVar.f20044b);
    }

    public final int hashCode() {
        return this.f20044b.hashCode() + (this.f20043a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("FilePathComponents(root=");
        sb2.append(this.f20043a);
        sb2.append(", segments=");
        return androidx.compose.material3.d.p(sb2, this.f20044b, ')');
    }
}
