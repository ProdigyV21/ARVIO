package fi.iki.elonen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f15414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final FileOutputStream f15415b;

    public e(File file) throws IOException {
        File fileCreateTempFile = File.createTempFile("NanoHTTPD-", "", file);
        this.f15414a = fileCreateTempFile;
        this.f15415b = new FileOutputStream(fileCreateTempFile);
    }

    @Override // fi.iki.elonen.q
    public final void delete() throws Exception {
        t.b(this.f15415b);
        File file = this.f15414a;
        if (file.delete()) {
            return;
        }
        throw new Exception("could not delete temporary file: " + file.getAbsolutePath());
    }
}
