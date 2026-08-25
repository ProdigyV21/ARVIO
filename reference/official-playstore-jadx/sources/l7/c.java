package l7;

import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f20045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public File[] f20046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f20047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f20048e;

    @Override // l7.g
    public final File a() {
        int i10;
        boolean z = this.f20048e;
        File file = this.f20055a;
        if (!z && this.f20046c == null) {
            File[] fileArrListFiles = file.listFiles();
            this.f20046c = fileArrListFiles;
            if (fileArrListFiles == null) {
                this.f20048e = true;
            }
        }
        File[] fileArr = this.f20046c;
        if (fileArr != null && (i10 = this.f20047d) < fileArr.length) {
            this.f20047d = i10 + 1;
            return fileArr[i10];
        }
        if (this.f20045b) {
            return null;
        }
        this.f20045b = true;
        return file;
    }
}
