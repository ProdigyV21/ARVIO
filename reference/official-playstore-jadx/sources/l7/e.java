package l7;

import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f20050b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public File[] f20051c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f20052d;

    @Override // l7.g
    public final File a() {
        boolean z = this.f20050b;
        File file = this.f20055a;
        if (!z) {
            this.f20050b = true;
            return file;
        }
        File[] fileArr = this.f20051c;
        if (fileArr != null && this.f20052d >= fileArr.length) {
            return null;
        }
        if (fileArr == null) {
            File[] fileArrListFiles = file.listFiles();
            this.f20051c = fileArrListFiles;
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                return null;
            }
        }
        File[] fileArr2 = this.f20051c;
        int i10 = this.f20052d;
        this.f20052d = i10 + 1;
        return fileArr2[i10];
    }
}
