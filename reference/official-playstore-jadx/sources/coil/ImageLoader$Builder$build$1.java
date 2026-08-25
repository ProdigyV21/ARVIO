package coil;

import coil.ImageLoader;
import coil.memory.MemoryCache;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/memory/MemoryCache;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class ImageLoader$Builder$build$1 extends r implements r7.a<MemoryCache> {
    final /* synthetic */ ImageLoader.Builder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageLoader$Builder$build$1(ImageLoader.Builder builder) {
        super(0);
        this.this$0 = builder;
    }

    @Override // r7.a
    public final MemoryCache invoke() {
        return new MemoryCache.Builder(this.this$0.applicationContext).build();
    }
}
