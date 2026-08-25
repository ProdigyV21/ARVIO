package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d1 {
    private final Map<String, Object> mBagOfTags = new HashMap();
    private final Set<Closeable> mCloseables = new LinkedHashSet();
    private volatile boolean mCleared = false;

    public static void a(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e5) {
                throw new RuntimeException(e5);
            }
        }
    }

    public void addCloseable(Closeable closeable) {
        if (this.mCleared) {
            a(closeable);
            return;
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                this.mCloseables.add(closeable);
            }
        }
    }

    public final void clear() {
        this.mCleared = true;
        Map<String, Object> map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                try {
                    Iterator<Object> it = this.mBagOfTags.values().iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                } finally {
                }
            }
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                try {
                    Iterator<Closeable> it2 = this.mCloseables.iterator();
                    while (it2.hasNext()) {
                        a(it2.next());
                    }
                } finally {
                }
            }
            this.mCloseables.clear();
        }
        onCleared();
    }

    public <T> T getTag(String str) {
        T t2;
        Map<String, Object> map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t2 = (T) this.mBagOfTags.get(str);
        }
        return t2;
    }

    public void onCleared() {
    }

    public <T> T setTagIfAbsent(String str, T t2) {
        Object obj;
        synchronized (this.mBagOfTags) {
            try {
                obj = this.mBagOfTags.get(str);
                if (obj == null) {
                    this.mBagOfTags.put(str, t2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj != null) {
            t2 = (T) obj;
        }
        if (this.mCleared) {
            a(t2);
        }
        return t2;
    }
}
