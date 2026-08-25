package e2;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f15020e = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15021a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f15022b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Lock f15023c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FileChannel f15024d;

    public a(String str, File file, boolean z) {
        Lock lock;
        this.f15021a = z;
        File file2 = new File(file, str.concat(".lck"));
        this.f15022b = file2;
        String absolutePath = file2.getAbsolutePath();
        HashMap map = f15020e;
        synchronized (map) {
            try {
                Object reentrantLock = map.get(absolutePath);
                if (reentrantLock == null) {
                    reentrantLock = new ReentrantLock();
                    map.put(absolutePath, reentrantLock);
                }
                lock = (Lock) reentrantLock;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f15023c = lock;
    }

    public final void a(boolean z) {
        File file = this.f15022b;
        this.f15023c.lock();
        if (z) {
            try {
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileChannel channel = new FileOutputStream(file).getChannel();
                channel.lock();
                this.f15024d = channel;
            } catch (IOException e5) {
                this.f15024d = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", e5);
            }
        }
    }

    public final void b() {
        try {
            FileChannel fileChannel = this.f15024d;
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (IOException unused) {
        }
        this.f15023c.unlock();
    }
}
