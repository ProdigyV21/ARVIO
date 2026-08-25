package coil.memory;

import android.content.Context;
import android.content.Utils;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import coil.memory.MemoryCache;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0003\u001d\u001e\u001fJ\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦\u0002¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0004H¦\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\bH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Lcoil/memory/MemoryCache;", "", "Lcoil/memory/MemoryCache$Key;", "key", "Lcoil/memory/MemoryCache$Value;", "get", "(Lcoil/memory/MemoryCache$Key;)Lcoil/memory/MemoryCache$Value;", "value", "Lx6/t0;", "set", "(Lcoil/memory/MemoryCache$Key;Lcoil/memory/MemoryCache$Value;)V", "", "remove", "(Lcoil/memory/MemoryCache$Key;)Z", "clear", "()V", "", "level", "trimMemory", "(I)V", "getSize", "()I", ContentDisposition.Parameters.Size, "getMaxSize", "maxSize", "", "getKeys", "()Ljava/util/Set;", UserMetadata.KEYDATA_FILENAME, "Builder", "Key", "Value", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface MemoryCache {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00002\b\b\u0001\u0010\u000f\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcoil/memory/MemoryCache$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "maxSizeBytes", "", "maxSizePercent", "", "strongReferencesEnabled", "", "weakReferencesEnabled", "build", "Lcoil/memory/MemoryCache;", ContentDisposition.Parameters.Size, "percent", "enable", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private final Context context;
        private int maxSizeBytes;
        private double maxSizePercent;
        private boolean strongReferencesEnabled = true;
        private boolean weakReferencesEnabled = true;

        public Builder(Context context) {
            this.context = context;
            this.maxSizePercent = Utils.defaultMemoryCacheSizePercent(context);
        }

        public final MemoryCache build() {
            StrongMemoryCache emptyStrongMemoryCache;
            WeakMemoryCache realWeakMemoryCache = this.weakReferencesEnabled ? new RealWeakMemoryCache() : new EmptyWeakMemoryCache();
            if (this.strongReferencesEnabled) {
                double d4 = this.maxSizePercent;
                int iCalculateMemoryCacheSize = d4 > 0.0d ? Utils.calculateMemoryCacheSize(this.context, d4) : this.maxSizeBytes;
                emptyStrongMemoryCache = iCalculateMemoryCacheSize > 0 ? new RealStrongMemoryCache(iCalculateMemoryCacheSize, realWeakMemoryCache) : new EmptyStrongMemoryCache(realWeakMemoryCache);
            } else {
                emptyStrongMemoryCache = new EmptyStrongMemoryCache(realWeakMemoryCache);
            }
            return new RealMemoryCache(emptyStrongMemoryCache, realWeakMemoryCache);
        }

        public final Builder maxSizeBytes(int size) {
            if (size < 0) {
                throw new IllegalArgumentException("size must be >= 0.");
            }
            this.maxSizePercent = 0.0d;
            this.maxSizeBytes = size;
            return this;
        }

        public final Builder maxSizePercent(double percent) {
            if (0.0d > percent || percent > 1.0d) {
                throw new IllegalArgumentException("size must be in the range [0.0, 1.0].");
            }
            this.maxSizeBytes = 0;
            this.maxSizePercent = percent;
            return this;
        }

        public final Builder strongReferencesEnabled(boolean enable) {
            this.strongReferencesEnabled = enable;
            return this;
        }

        public final Builder weakReferencesEnabled(boolean enable) {
            this.weakReferencesEnabled = enable;
            return this;
        }
    }

    void clear();

    Value get(Key key);

    Set<Key> getKeys();

    int getMaxSize();

    int getSize();

    boolean remove(Key key);

    void set(Key key, Value value);

    void trimMemory(int level);

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000  2\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0096\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0011J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u0013R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcoil/memory/MemoryCache$Key;", "Landroid/os/Parcelable;", "", "key", "", "extras", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "copy", "(Ljava/lang/String;Ljava/util/Map;)Lcoil/memory/MemoryCache$Key;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "describeContents", "Landroid/os/Parcel;", "parcel", "flags", "Lx6/t0;", "writeToParcel", "(Landroid/os/Parcel;I)V", "Ljava/lang/String;", "getKey", "Ljava/util/Map;", "getExtras", "()Ljava/util/Map;", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Key implements Parcelable {
        private final Map<String, String> extras;
        private final String key;
        private static final Companion Companion = new Companion(null);

        @Deprecated
        public static final Parcelable.Creator<Key> CREATOR = new Parcelable.Creator<Key>() { // from class: coil.memory.MemoryCache$Key$Companion$CREATOR$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MemoryCache.Key createFromParcel(Parcel parcel) {
                String string = parcel.readString();
                int i10 = parcel.readInt();
                LinkedHashMap linkedHashMap = new LinkedHashMap(i10);
                for (int i11 = 0; i11 < i10; i11++) {
                    linkedHashMap.put(parcel.readString(), parcel.readString());
                }
                return new MemoryCache.Key(string, linkedHashMap);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MemoryCache.Key[] newArray(int size) {
                return new MemoryCache.Key[size];
            }
        };

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcoil/memory/MemoryCache$Key$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcoil/memory/MemoryCache$Key;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            private Companion() {
            }
        }

        public Key(String str, Map<String, String> map) {
            this.key = str;
            this.extras = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Key copy$default(Key key, String str, Map map, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = key.key;
            }
            if ((i10 & 2) != 0) {
                map = key.extras;
            }
            return key.copy(str, map);
        }

        public final Key copy(String key, Map<String, String> extras) {
            return new Key(key, extras);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return p.a(this.key, key.key) && p.a(this.extras, key.extras);
        }

        public final Map<String, String> getExtras() {
            return this.extras;
        }

        public final String getKey() {
            return this.key;
        }

        public int hashCode() {
            return this.extras.hashCode() + (this.key.hashCode() * 31);
        }

        public String toString() {
            return "Key(key=" + this.key + ", extras=" + this.extras + ')';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(this.key);
            parcel.writeInt(this.extras.size());
            for (Map.Entry<String, String> entry : this.extras.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                parcel.writeString(key);
                parcel.writeString(value);
            }
        }

        public /* synthetic */ Key(String str, Map map, int i10, h hVar) {
            this(str, (i10 & 2) != 0 ? a0.f19683i : map);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcoil/memory/MemoryCache$Value;", "", "bitmap", "Landroid/graphics/Bitmap;", "extras", "", "", "(Landroid/graphics/Bitmap;Ljava/util/Map;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getExtras", "()Ljava/util/Map;", "copy", "equals", "", "other", "hashCode", "", "toString", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Value {
        private final Bitmap bitmap;
        private final Map<String, Object> extras;

        public Value(Bitmap bitmap, Map<String, ? extends Object> map) {
            this.bitmap = bitmap;
            this.extras = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Value copy$default(Value value, Bitmap bitmap, Map map, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                bitmap = value.bitmap;
            }
            if ((i10 & 2) != 0) {
                map = value.extras;
            }
            return value.copy(bitmap, map);
        }

        public final Value copy(Bitmap bitmap, Map<String, ? extends Object> extras) {
            return new Value(bitmap, extras);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Value)) {
                return false;
            }
            Value value = (Value) other;
            return p.a(this.bitmap, value.bitmap) && p.a(this.extras, value.extras);
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Map<String, Object> getExtras() {
            return this.extras;
        }

        public int hashCode() {
            return this.extras.hashCode() + (this.bitmap.hashCode() * 31);
        }

        public String toString() {
            return "Value(bitmap=" + this.bitmap + ", extras=" + this.extras + ')';
        }

        public /* synthetic */ Value(Bitmap bitmap, Map map, int i10, h hVar) {
            this(bitmap, (i10 & 2) != 0 ? a0.f19683i : map);
        }
    }
}
