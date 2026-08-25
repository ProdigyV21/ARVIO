package coil.memory;

import android.content.Bitmaps;
import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import coil.memory.RealStrongMemoryCache;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import t.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000W\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\b\u0005*\u0001\u001f\b\u0000\u0018\u00002\u00020\u0001:\u0001*B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010$\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006+"}, d2 = {"Lcoil/memory/RealStrongMemoryCache;", "Lcoil/memory/StrongMemoryCache;", "", "maxSize", "Lcoil/memory/WeakMemoryCache;", "weakMemoryCache", "<init>", "(ILcoil/memory/WeakMemoryCache;)V", "Lcoil/memory/MemoryCache$Key;", "key", "Lcoil/memory/MemoryCache$Value;", "get", "(Lcoil/memory/MemoryCache$Key;)Lcoil/memory/MemoryCache$Value;", "Landroid/graphics/Bitmap;", "bitmap", "", "", "", "extras", "Lx6/t0;", "set", "(Lcoil/memory/MemoryCache$Key;Landroid/graphics/Bitmap;Ljava/util/Map;)V", "", "remove", "(Lcoil/memory/MemoryCache$Key;)Z", "clearMemory", "()V", "level", "trimMemory", "(I)V", "Lcoil/memory/WeakMemoryCache;", "coil/memory/RealStrongMemoryCache$cache$1", "cache", "Lcoil/memory/RealStrongMemoryCache$cache$1;", "getSize", "()I", ContentDisposition.Parameters.Size, "getMaxSize", "", "getKeys", "()Ljava/util/Set;", UserMetadata.KEYDATA_FILENAME, "InternalValue", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealStrongMemoryCache implements StrongMemoryCache {
    private final RealStrongMemoryCache$cache$1 cache;
    private final WeakMemoryCache weakMemoryCache;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcoil/memory/RealStrongMemoryCache$InternalValue;", "", "bitmap", "Landroid/graphics/Bitmap;", "extras", "", "", ContentDisposition.Parameters.Size, "", "(Landroid/graphics/Bitmap;Ljava/util/Map;I)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getExtras", "()Ljava/util/Map;", "getSize", "()I", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class InternalValue {
        private final Bitmap bitmap;
        private final Map<String, Object> extras;
        private final int size;

        public InternalValue(Bitmap bitmap, Map<String, ? extends Object> map, int i10) {
            this.bitmap = bitmap;
            this.extras = map;
            this.size = i10;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Map<String, Object> getExtras() {
            return this.extras;
        }

        public final int getSize() {
            return this.size;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [coil.memory.RealStrongMemoryCache$cache$1] */
    public RealStrongMemoryCache(final int i10, WeakMemoryCache weakMemoryCache) {
        this.weakMemoryCache = weakMemoryCache;
        this.cache = new r(i10) { // from class: coil.memory.RealStrongMemoryCache$cache$1
            @Override // t.r
            public void entryRemoved(boolean evicted, MemoryCache.Key key, RealStrongMemoryCache.InternalValue oldValue, RealStrongMemoryCache.InternalValue newValue) {
                this.weakMemoryCache.set(key, oldValue.getBitmap(), oldValue.getExtras(), oldValue.getSize());
            }

            @Override // t.r
            public int sizeOf(MemoryCache.Key key, RealStrongMemoryCache.InternalValue value) {
                return value.getSize();
            }
        };
    }

    @Override // coil.memory.StrongMemoryCache
    public void clearMemory() {
        evictAll();
    }

    @Override // coil.memory.StrongMemoryCache
    public MemoryCache.Value get(MemoryCache.Key key) {
        InternalValue internalValue = (InternalValue) get(key);
        if (internalValue != null) {
            return new MemoryCache.Value(internalValue.getBitmap(), internalValue.getExtras());
        }
        return null;
    }

    @Override // coil.memory.StrongMemoryCache
    public Set<MemoryCache.Key> getKeys() {
        return snapshot().keySet();
    }

    @Override // coil.memory.StrongMemoryCache
    public int getMaxSize() {
        return maxSize();
    }

    @Override // coil.memory.StrongMemoryCache
    public int getSize() {
        return size();
    }

    @Override // coil.memory.StrongMemoryCache
    public boolean remove(MemoryCache.Key key) {
        return remove(key) != null;
    }

    @Override // coil.memory.StrongMemoryCache
    public void set(MemoryCache.Key key, Bitmap bitmap, Map<String, ? extends Object> extras) {
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        if (allocationByteCountCompat <= getMaxSize()) {
            put(key, new InternalValue(bitmap, extras, allocationByteCountCompat));
        } else {
            remove(key);
            this.weakMemoryCache.set(key, bitmap, extras, allocationByteCountCompat);
        }
    }

    @Override // coil.memory.StrongMemoryCache
    public void trimMemory(int level) {
        if (level >= 40) {
            clearMemory();
        } else {
            if (10 > level || level >= 20) {
                return;
            }
            trimToSize(getSize() / 2);
        }
    }
}
