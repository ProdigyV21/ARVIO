package androidx.compose.ui.res;

import a0.c;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ \u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u0003R<\u0010\u0015\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00130\u0012j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013`\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/res/ImageVectorCache;", "", "<init>", "()V", "Landroidx/compose/ui/res/ImageVectorCache$Key;", "key", "Landroidx/compose/ui/res/ImageVectorCache$ImageVectorEntry;", "get", "(Landroidx/compose/ui/res/ImageVectorCache$Key;)Landroidx/compose/ui/res/ImageVectorCache$ImageVectorEntry;", "", "configChanges", "Lx6/t0;", "prune", "(I)V", "imageVectorEntry", "set", "(Landroidx/compose/ui/res/ImageVectorCache$Key;Landroidx/compose/ui/res/ImageVectorCache$ImageVectorEntry;)V", "clear", "Ljava/util/HashMap;", "Ljava/lang/ref/WeakReference;", "Lkotlin/collections/HashMap;", "map", "Ljava/util/HashMap;", "ImageVectorEntry", "Key", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ImageVectorCache {
    public static final int $stable = 8;
    private final HashMap<Key, WeakReference<ImageVectorEntry>> map = new HashMap<>();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/res/ImageVectorCache$ImageVectorEntry;", "", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "configFlags", "", "(Landroidx/compose/ui/graphics/vector/ImageVector;I)V", "getConfigFlags", "()I", "getImageVector", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class ImageVectorEntry {
        public static final int $stable = 0;
        private final int configFlags;
        private final ImageVector imageVector;

        public ImageVectorEntry(ImageVector imageVector, int i10) {
            this.imageVector = imageVector;
            this.configFlags = i10;
        }

        public static /* synthetic */ ImageVectorEntry copy$default(ImageVectorEntry imageVectorEntry, ImageVector imageVector, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                imageVector = imageVectorEntry.imageVector;
            }
            if ((i11 & 2) != 0) {
                i10 = imageVectorEntry.configFlags;
            }
            return imageVectorEntry.copy(imageVector, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ImageVector getImageVector() {
            return this.imageVector;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getConfigFlags() {
            return this.configFlags;
        }

        public final ImageVectorEntry copy(ImageVector imageVector, int configFlags) {
            return new ImageVectorEntry(imageVector, configFlags);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImageVectorEntry)) {
                return false;
            }
            ImageVectorEntry imageVectorEntry = (ImageVectorEntry) other;
            return p.a(this.imageVector, imageVectorEntry.imageVector) && this.configFlags == imageVectorEntry.configFlags;
        }

        public final int getConfigFlags() {
            return this.configFlags;
        }

        public final ImageVector getImageVector() {
            return this.imageVector;
        }

        public int hashCode() {
            return (this.imageVector.hashCode() * 31) + this.configFlags;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("ImageVectorEntry(imageVector=");
            sb2.append(this.imageVector);
            sb2.append(", configFlags=");
            return c.o(sb2, this.configFlags, ')');
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\f\u001a\u00060\u0003R\u00020\u0004HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003R\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/res/ImageVectorCache$Key;", "", "theme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", TtmlNode.ATTR_ID, "", "(Landroid/content/res/Resources$Theme;I)V", "getId", "()I", "getTheme", "()Landroid/content/res/Resources$Theme;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Key {
        public static final int $stable = 8;
        private final int id;
        private final Resources.Theme theme;

        public Key(Resources.Theme theme, int i10) {
            this.theme = theme;
            this.id = i10;
        }

        public static /* synthetic */ Key copy$default(Key key, Resources.Theme theme, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                theme = key.theme;
            }
            if ((i11 & 2) != 0) {
                i10 = key.id;
            }
            return key.copy(theme, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Resources.Theme getTheme() {
            return this.theme;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final Key copy(Resources.Theme theme, int id) {
            return new Key(theme, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return p.a(this.theme, key.theme) && this.id == key.id;
        }

        public final int getId() {
            return this.id;
        }

        public final Resources.Theme getTheme() {
            return this.theme;
        }

        public int hashCode() {
            return (this.theme.hashCode() * 31) + this.id;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Key(theme=");
            sb2.append(this.theme);
            sb2.append(", id=");
            return c.o(sb2, this.id, ')');
        }
    }

    public final void clear() {
        this.map.clear();
    }

    public final ImageVectorEntry get(Key key) {
        WeakReference<ImageVectorEntry> weakReference = this.map.get(key);
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final void prune(int configChanges) {
        Iterator<Map.Entry<Key, WeakReference<ImageVectorEntry>>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            ImageVectorEntry imageVectorEntry = it.next().getValue().get();
            if (imageVectorEntry == null || Configuration.needNewResources(configChanges, imageVectorEntry.getConfigFlags())) {
                it.remove();
            }
        }
    }

    public final void set(Key key, ImageVectorEntry imageVectorEntry) {
        this.map.put(key, new WeakReference<>(imageVectorEntry));
    }
}
