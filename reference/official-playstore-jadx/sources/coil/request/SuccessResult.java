package coil.request;

import android.graphics.DataSource;
import android.graphics.drawable.Drawable;
import coil.memory.MemoryCache;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJP\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rJ\u0013\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006!"}, d2 = {"Lcoil/request/SuccessResult;", "Lcoil/request/ImageResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "request", "Lcoil/request/ImageRequest;", "dataSource", "Lcoil/decode/DataSource;", "memoryCacheKey", "Lcoil/memory/MemoryCache$Key;", "diskCacheKey", "", "isSampled", "", "isPlaceholderCached", "(Landroid/graphics/drawable/Drawable;Lcoil/request/ImageRequest;Lcoil/decode/DataSource;Lcoil/memory/MemoryCache$Key;Ljava/lang/String;ZZ)V", "getDataSource", "()Lcoil/decode/DataSource;", "getDiskCacheKey", "()Ljava/lang/String;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "getMemoryCacheKey", "()Lcoil/memory/MemoryCache$Key;", "getRequest", "()Lcoil/request/ImageRequest;", "copy", "equals", "other", "", "hashCode", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SuccessResult extends ImageResult {
    private final DataSource dataSource;
    private final String diskCacheKey;
    private final Drawable drawable;
    private final boolean isPlaceholderCached;
    private final boolean isSampled;
    private final MemoryCache.Key memoryCacheKey;
    private final ImageRequest request;

    public /* synthetic */ SuccessResult(Drawable drawable, ImageRequest imageRequest, DataSource dataSource, MemoryCache.Key key, String str, boolean z, boolean z5, int i10, h hVar) {
        this(drawable, imageRequest, dataSource, (i10 & 8) != 0 ? null : key, (i10 & 16) != 0 ? null : str, (i10 & 32) != 0 ? false : z, (i10 & 64) != 0 ? false : z5);
    }

    public static /* synthetic */ SuccessResult copy$default(SuccessResult successResult, Drawable drawable, ImageRequest imageRequest, DataSource dataSource, MemoryCache.Key key, String str, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            drawable = successResult.getDrawable();
        }
        if ((i10 & 2) != 0) {
            imageRequest = successResult.getRequest();
        }
        if ((i10 & 4) != 0) {
            dataSource = successResult.dataSource;
        }
        if ((i10 & 8) != 0) {
            key = successResult.memoryCacheKey;
        }
        if ((i10 & 16) != 0) {
            str = successResult.diskCacheKey;
        }
        if ((i10 & 32) != 0) {
            z = successResult.isSampled;
        }
        if ((i10 & 64) != 0) {
            z5 = successResult.isPlaceholderCached;
        }
        boolean z10 = z;
        boolean z11 = z5;
        String str2 = str;
        DataSource dataSource2 = dataSource;
        return successResult.copy(drawable, imageRequest, dataSource2, key, str2, z10, z11);
    }

    public final SuccessResult copy(Drawable drawable, ImageRequest request, DataSource dataSource, MemoryCache.Key memoryCacheKey, String diskCacheKey, boolean isSampled, boolean isPlaceholderCached) {
        return new SuccessResult(drawable, request, dataSource, memoryCacheKey, diskCacheKey, isSampled, isPlaceholderCached);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SuccessResult)) {
            return false;
        }
        SuccessResult successResult = (SuccessResult) other;
        return p.a(getDrawable(), successResult.getDrawable()) && p.a(getRequest(), successResult.getRequest()) && this.dataSource == successResult.dataSource && p.a(this.memoryCacheKey, successResult.memoryCacheKey) && p.a(this.diskCacheKey, successResult.diskCacheKey) && this.isSampled == successResult.isSampled && this.isPlaceholderCached == successResult.isPlaceholderCached;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    @Override // coil.request.ImageResult
    public Drawable getDrawable() {
        return this.drawable;
    }

    public final MemoryCache.Key getMemoryCacheKey() {
        return this.memoryCacheKey;
    }

    @Override // coil.request.ImageResult
    public ImageRequest getRequest() {
        return this.request;
    }

    public int hashCode() {
        int iHashCode = (this.dataSource.hashCode() + ((getRequest().hashCode() + (getDrawable().hashCode() * 31)) * 31)) * 31;
        MemoryCache.Key key = this.memoryCacheKey;
        int iHashCode2 = (iHashCode + (key != null ? key.hashCode() : 0)) * 31;
        String str = this.diskCacheKey;
        return ((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + (this.isSampled ? 1231 : 1237)) * 31) + (this.isPlaceholderCached ? 1231 : 1237);
    }

    /* JADX INFO: renamed from: isPlaceholderCached, reason: from getter */
    public final boolean getIsPlaceholderCached() {
        return this.isPlaceholderCached;
    }

    /* JADX INFO: renamed from: isSampled, reason: from getter */
    public final boolean getIsSampled() {
        return this.isSampled;
    }

    public SuccessResult(Drawable drawable, ImageRequest imageRequest, DataSource dataSource, MemoryCache.Key key, String str, boolean z, boolean z5) {
        super(null);
        this.drawable = drawable;
        this.request = imageRequest;
        this.dataSource = dataSource;
        this.memoryCacheKey = key;
        this.diskCacheKey = str;
        this.isSampled = z;
        this.isPlaceholderCached = z5;
    }
}
