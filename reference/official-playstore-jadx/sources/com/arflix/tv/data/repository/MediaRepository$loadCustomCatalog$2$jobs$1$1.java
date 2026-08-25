package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCustomCatalog$2$jobs$1$1", f = "MediaRepository.kt", l = {3970, 1772, 1773}, m = "invokeSuspend", v = 2)
public final class MediaRepository$loadCustomCatalog$2$jobs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ ua.f $semaphore;
    final /* synthetic */ int $tmdbId;
    final /* synthetic */ MediaType $type;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MediaRepository this$0;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$loadCustomCatalog$2$jobs$1$1(ua.f fVar, MediaType mediaType, MediaRepository mediaRepository, int i10, d7.d<? super MediaRepository$loadCustomCatalog$2$jobs$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.$type = mediaType;
        this.this$0 = mediaRepository;
        this.$tmdbId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$loadCustomCatalog$2$jobs$1$1 mediaRepository$loadCustomCatalog$2$jobs$1$1 = new MediaRepository$loadCustomCatalog$2$jobs$1$1(this.$semaphore, this.$type, this.this$0, this.$tmdbId, dVar);
        mediaRepository$loadCustomCatalog$2$jobs$1$1.L$0 = obj;
        return mediaRepository$loadCustomCatalog$2$jobs$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b2, code lost:
    
        if (r12 == r7) goto L33;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c4  */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ka.k0, ua.f] */
    /* JADX WARN: Type inference failed for: r0v2, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$loadCustomCatalog$2$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((MediaRepository$loadCustomCatalog$2$jobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
