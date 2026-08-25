package ac;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f822a = "jansi-" + b.J();

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.startsWith(this.f822a) && !str.endsWith(".lck");
    }
}
