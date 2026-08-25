package v1;

import j$.util.Objects;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f22147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f22148d;

    public k(int i10, long j10, int i11, long j11) {
        this.f22145a = i10;
        this.f22146b = i11;
        this.f22147c = j10;
        this.f22148d = j11;
    }

    public static k a(File file) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            k kVar = new k(dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readInt(), dataInputStream.readLong());
            dataInputStream.close();
            return kVar;
        } finally {
        }
    }

    public final void b(File file) throws IOException {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f22145a);
            dataOutputStream.writeInt(this.f22146b);
            dataOutputStream.writeLong(this.f22147c);
            dataOutputStream.writeLong(this.f22148d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof k)) {
            k kVar = (k) obj;
            if (this.f22146b == kVar.f22146b && this.f22147c == kVar.f22147c && this.f22145a == kVar.f22145a && this.f22148d == kVar.f22148d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f22146b), Long.valueOf(this.f22147c), Integer.valueOf(this.f22145a), Long.valueOf(this.f22148d));
    }
}
