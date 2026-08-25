package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionCatalogPage$2$jobs$1$1", f = "MediaRepository.kt", l = {3970, 2050, 2051}, m = "invokeSuspend", v = 2)
public final class MediaRepository$loadCollectionCatalogPage$2$jobs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ LinkedHashMap<x6.x, MediaItem> $itemsByRef;
    final /* synthetic */ ua.f $semaphore;
    final /* synthetic */ int $tmdbId;
    final /* synthetic */ MediaType $type;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
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
    public MediaRepository$loadCollectionCatalogPage$2$jobs$1$1(ua.f fVar, LinkedHashMap<x6.x, MediaItem> linkedHashMap, MediaType mediaType, int i10, MediaRepository mediaRepository, d7.d<? super MediaRepository$loadCollectionCatalogPage$2$jobs$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.$itemsByRef = linkedHashMap;
        this.$type = mediaType;
        this.$tmdbId = i10;
        this.this$0 = mediaRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$loadCollectionCatalogPage$2$jobs$1$1 mediaRepository$loadCollectionCatalogPage$2$jobs$1$1 = new MediaRepository$loadCollectionCatalogPage$2$jobs$1$1(this.$semaphore, this.$itemsByRef, this.$type, this.$tmdbId, this.this$0, dVar);
        mediaRepository$loadCollectionCatalogPage$2$jobs$1$1.L$0 = obj;
        return mediaRepository$loadCollectionCatalogPage$2$jobs$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(1:59)|(1:(1:(1:(9:7|8|29|42|(1:44)(1:45)|46|(1:48)|52|53)(2:12|13))(9:14|15|39|42|(0)(0)|46|(0)|52|53))(1:17))(3:18|(1:21)|37)|56|22|(1:(2:25|(8:28|29|42|(0)(0)|46|(0)|52|53))(2:33|34))(2:35|(8:38|39|42|(0)(0)|46|(0)|52|53))|37|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c0, code lost:
    
        r14 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c1, code lost:
    
        r2 = r10;
        r3 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ea, code lost:
    
        r14 = new x6.c0(r14);
        r0 = r0;
        r1 = r1;
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0109, code lost:
    
        r14 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0111, code lost:
    
        r3.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0114, code lost:
    
        throw r14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fb A[Catch: all -> 0x0109, TRY_LEAVE, TryCatch #1 {all -> 0x0109, blocks: (B:42:0x00f0, B:46:0x00f7, B:48:0x00fb, B:41:0x00ea), top: B:57:0x00ea }] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ka.k0] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Enum, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r8v5 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$loadCollectionCatalogPage$2$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((MediaRepository$loadCollectionCatalogPage$2$jobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
