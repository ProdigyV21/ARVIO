package coil.intercept;

import android.content.Bitmaps;
import android.content.DrawableUtils;
import android.content.Logger;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.DataSource;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.EventListener;
import coil.ImageLoader;
import coil.intercept.Interceptor;
import coil.memory.MemoryCache;
import coil.memory.MemoryCacheService;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.RequestService;
import coil.request.SuccessResult;
import coil.transform.Transformation;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import java.util.List;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 62\u00020\u0001:\u000267B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ3\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J;\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJC\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ-\u0010%\u001a\u00020$2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002¢\u0006\u0004\b%\u0010&J\u001b\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020'H\u0096@ø\u0001\u0000¢\u0006\u0004\b*\u0010+J3\u0010/\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0081@ø\u0001\u0000¢\u0006\u0004\b-\u0010.R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00100R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00101R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lcoil/intercept/EngineInterceptor;", "Lcoil/intercept/Interceptor;", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/request/RequestService;", "requestService", "Lcoil/util/Logger;", "logger", "<init>", "(Lcoil/ImageLoader;Lcoil/request/RequestService;Lcoil/util/Logger;)V", "Lcoil/request/ImageRequest;", "request", "", "mappedData", "Lcoil/request/Options;", "_options", "Lcoil/EventListener;", "eventListener", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "execute", "(Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Ld7/d;)Ljava/lang/Object;", "Lcoil/ComponentRegistry;", "components", "options", "Lcoil/fetch/FetchResult;", "fetch", "(Lcoil/ComponentRegistry;Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Ld7/d;)Ljava/lang/Object;", "Lcoil/fetch/SourceResult;", "fetchResult", "decode", "(Lcoil/fetch/SourceResult;Lcoil/ComponentRegistry;Lcoil/request/ImageRequest;Ljava/lang/Object;Lcoil/request/Options;Lcoil/EventListener;Ld7/d;)Ljava/lang/Object;", "Landroid/graphics/drawable/Drawable;", "drawable", "", "Lcoil/transform/Transformation;", "transformations", "Landroid/graphics/Bitmap;", "convertDrawableToBitmap", "(Landroid/graphics/drawable/Drawable;Lcoil/request/Options;Ljava/util/List;)Landroid/graphics/Bitmap;", "Lcoil/intercept/Interceptor$Chain;", "chain", "Lcoil/request/ImageResult;", "intercept", "(Lcoil/intercept/Interceptor$Chain;Ld7/d;)Ljava/lang/Object;", "result", "transform$coil_base_release", "(Lcoil/intercept/EngineInterceptor$ExecuteResult;Lcoil/request/ImageRequest;Lcoil/request/Options;Lcoil/EventListener;Ld7/d;)Ljava/lang/Object;", "transform", "Lcoil/ImageLoader;", "Lcoil/request/RequestService;", "Lcoil/util/Logger;", "Lcoil/memory/MemoryCacheService;", "memoryCacheService", "Lcoil/memory/MemoryCacheService;", "Companion", "ExecuteResult", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class EngineInterceptor implements Interceptor {
    private static final String TAG = "EngineInterceptor";
    private final ImageLoader imageLoader;
    private final Logger logger;
    private final MemoryCacheService memoryCacheService;
    private final RequestService requestService;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ0\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcoil/intercept/EngineInterceptor$ExecuteResult;", "", "drawable", "Landroid/graphics/drawable/Drawable;", "isSampled", "", "dataSource", "Lcoil/decode/DataSource;", "diskCacheKey", "", "(Landroid/graphics/drawable/Drawable;ZLcoil/decode/DataSource;Ljava/lang/String;)V", "getDataSource", "()Lcoil/decode/DataSource;", "getDiskCacheKey", "()Ljava/lang/String;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "copy", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ExecuteResult {
        private final DataSource dataSource;
        private final String diskCacheKey;
        private final Drawable drawable;
        private final boolean isSampled;

        public ExecuteResult(Drawable drawable, boolean z, DataSource dataSource, String str) {
            this.drawable = drawable;
            this.isSampled = z;
            this.dataSource = dataSource;
            this.diskCacheKey = str;
        }

        public static /* synthetic */ ExecuteResult copy$default(ExecuteResult executeResult, Drawable drawable, boolean z, DataSource dataSource, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                drawable = executeResult.drawable;
            }
            if ((i10 & 2) != 0) {
                z = executeResult.isSampled;
            }
            if ((i10 & 4) != 0) {
                dataSource = executeResult.dataSource;
            }
            if ((i10 & 8) != 0) {
                str = executeResult.diskCacheKey;
            }
            return executeResult.copy(drawable, z, dataSource, str);
        }

        public final ExecuteResult copy(Drawable drawable, boolean isSampled, DataSource dataSource, String diskCacheKey) {
            return new ExecuteResult(drawable, isSampled, dataSource, diskCacheKey);
        }

        public final DataSource getDataSource() {
            return this.dataSource;
        }

        public final String getDiskCacheKey() {
            return this.diskCacheKey;
        }

        public final Drawable getDrawable() {
            return this.drawable;
        }

        /* JADX INFO: renamed from: isSampled, reason: from getter */
        public final boolean getIsSampled() {
            return this.isSampled;
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$decode$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", l = {199}, m = "decode")
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.decode(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$execute$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", l = {122, 126, 144}, m = "execute")
    public static final class C06461 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C06461(d<? super C06461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.execute(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$fetch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", l = {165}, m = "fetch")
    public static final class C06471 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C06471(d<? super C06471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.fetch(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$intercept$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.intercept.EngineInterceptor", f = "EngineInterceptor.kt", l = {73}, m = "intercept")
    public static final class C06481 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06481(d<? super C06481> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EngineInterceptor.this.intercept(null, this);
        }
    }

    /* JADX INFO: renamed from: coil.intercept.EngineInterceptor$intercept$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/request/SuccessResult;", "<anonymous>", "(Lka/k0;)Lcoil/request/SuccessResult;"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.intercept.EngineInterceptor$intercept$2", f = "EngineInterceptor.kt", l = {75}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super SuccessResult>, Object> {
        final /* synthetic */ MemoryCache.Key $cacheKey;
        final /* synthetic */ Interceptor.Chain $chain;
        final /* synthetic */ EventListener $eventListener;
        final /* synthetic */ Object $mappedData;
        final /* synthetic */ Options $options;
        final /* synthetic */ ImageRequest $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ImageRequest imageRequest, Object obj, Options options, EventListener eventListener, MemoryCache.Key key, Interceptor.Chain chain, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$request = imageRequest;
            this.$mappedData = obj;
            this.$options = options;
            this.$eventListener = eventListener;
            this.$cacheKey = key;
            this.$chain = chain;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return EngineInterceptor.this.new AnonymousClass2(this.$request, this.$mappedData, this.$options, this.$eventListener, this.$cacheKey, this.$chain, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objExecute;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                EngineInterceptor engineInterceptor = EngineInterceptor.this;
                ImageRequest imageRequest = this.$request;
                Object obj2 = this.$mappedData;
                Options options = this.$options;
                EventListener eventListener = this.$eventListener;
                this.label = 1;
                objExecute = engineInterceptor.execute(imageRequest, obj2, options, eventListener, this);
                a aVar = a.f15033i;
                if (objExecute == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objExecute = obj;
            }
            ExecuteResult executeResult = (ExecuteResult) objExecute;
            boolean cacheValue = EngineInterceptor.this.memoryCacheService.setCacheValue(this.$cacheKey, this.$request, executeResult);
            Drawable drawable = executeResult.getDrawable();
            ImageRequest imageRequest2 = this.$request;
            DataSource dataSource = executeResult.getDataSource();
            MemoryCache.Key key = this.$cacheKey;
            if (!cacheValue) {
                key = null;
            }
            return new SuccessResult(drawable, imageRequest2, dataSource, key, executeResult.getDiskCacheKey(), executeResult.getIsSampled(), Utils.isPlaceholderCached(this.$chain));
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super SuccessResult> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public EngineInterceptor(ImageLoader imageLoader, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.requestService = requestService;
        this.logger = logger;
        this.memoryCacheService = new MemoryCacheService(imageLoader, requestService, logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap convertDrawableToBitmap(Drawable drawable, Options options, List<? extends Transformation> transformations) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap.Config safeConfig = Bitmaps.getSafeConfig(bitmap);
            if (r.J(Utils.getVALID_TRANSFORMATION_CONFIGS(), safeConfig)) {
                return bitmap;
            }
            Logger logger = this.logger;
            if (logger != null && logger.getLevel() <= 4) {
                logger.log(TAG, 4, "Converting bitmap with config " + safeConfig + " to apply transformations: " + transformations + '.', null);
            }
        } else {
            Logger logger2 = this.logger;
            if (logger2 != null && logger2.getLevel() <= 4) {
                logger2.log(TAG, 4, "Converting drawable of type " + drawable.getClass().getCanonicalName() + " to apply transformations: " + transformations + '.', null);
            }
        }
        return DrawableUtils.INSTANCE.convertToBitmap(drawable, options.getConfig(), options.getSize(), options.getScale(), options.getAllowInexactSize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0091 -> B:21:0x0098). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object decode(coil.fetch.SourceResult r8, coil.ComponentRegistry r9, coil.request.ImageRequest r10, java.lang.Object r11, coil.request.Options r12, coil.EventListener r13, d7.d<? super coil.intercept.EngineInterceptor.ExecuteResult> r14) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.decode(coil.fetch.SourceResult, coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01fd, code lost:
    
        if (r0 == r11) goto L76;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x022a  */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute(coil.request.ImageRequest r33, java.lang.Object r34, coil.request.Options r35, coil.EventListener r36, d7.d<? super coil.intercept.EngineInterceptor.ExecuteResult> r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.execute(coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0096 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x008b -> B:21:0x008f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetch(coil.ComponentRegistry r8, coil.request.ImageRequest r9, java.lang.Object r10, coil.request.Options r11, coil.EventListener r12, d7.d<? super coil.fetch.FetchResult> r13) {
        /*
            r7 = this;
            boolean r0 = r13 instanceof coil.intercept.EngineInterceptor.C06471
            if (r0 == 0) goto L13
            r0 = r13
            coil.intercept.EngineInterceptor$fetch$1 r0 = (coil.intercept.EngineInterceptor.C06471) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.EngineInterceptor$fetch$1 r0 = new coil.intercept.EngineInterceptor$fetch$1
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L53
            if (r1 != r2) goto L4b
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$6
            coil.fetch.Fetcher r9 = (coil.fetch.Fetcher) r9
            java.lang.Object r10 = r0.L$5
            coil.EventListener r10 = (coil.EventListener) r10
            java.lang.Object r11 = r0.L$4
            coil.request.Options r11 = (coil.request.Options) r11
            java.lang.Object r12 = r0.L$3
            java.lang.Object r1 = r0.L$2
            coil.request.ImageRequest r1 = (coil.request.ImageRequest) r1
            java.lang.Object r3 = r0.L$1
            coil.ComponentRegistry r3 = (coil.ComponentRegistry) r3
            java.lang.Object r4 = r0.L$0
            coil.intercept.EngineInterceptor r4 = (coil.intercept.EngineInterceptor) r4
            k2.c.G(r13)
            r6 = r0
            r0 = r8
            r8 = r3
            r3 = r6
            r6 = r1
            r1 = r9
            r9 = r6
            r6 = r12
            r12 = r10
            r10 = r6
            goto L8f
        L4b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L53:
            k2.c.G(r13)
            r13 = 0
            r4 = r7
        L58:
            coil.ImageLoader r1 = r4.imageLoader
            x6.x r13 = r8.newFetcher(r10, r11, r1, r13)
            if (r13 == 0) goto Laf
            java.lang.Object r1 = r13.f22608i
            coil.fetch.Fetcher r1 = (coil.fetch.Fetcher) r1
            java.lang.Object r13 = r13.f22609l
            java.lang.Number r13 = (java.lang.Number) r13
            int r13 = r13.intValue()
            int r13 = r13 + r2
            r12.fetchStart(r9, r1, r11)
            r0.L$0 = r4
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.L$4 = r11
            r0.L$5 = r12
            r0.L$6 = r1
            r0.I$0 = r13
            r0.label = r2
            java.lang.Object r3 = r1.fetch(r0)
            e7.a r5 = e7.a.f15033i
            if (r3 != r5) goto L8b
            return r5
        L8b:
            r6 = r0
            r0 = r13
            r13 = r3
            r3 = r6
        L8f:
            coil.fetch.FetchResult r13 = (coil.fetch.FetchResult) r13
            r12.fetchEnd(r9, r1, r11, r13)     // Catch: java.lang.Throwable -> L9a
            if (r13 == 0) goto L97
            return r13
        L97:
            r13 = r0
            r0 = r3
            goto L58
        L9a:
            r8 = move-exception
            boolean r9 = r13 instanceof coil.fetch.SourceResult
            if (r9 == 0) goto La2
            coil.fetch.SourceResult r13 = (coil.fetch.SourceResult) r13
            goto La3
        La2:
            r13 = 0
        La3:
            if (r13 == 0) goto Lae
            coil.decode.ImageSource r9 = r13.getSource()
            if (r9 == 0) goto Lae
            android.content.Utils.closeQuietly(r9)
        Lae:
            throw r8
        Laf:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Unable to create a fetcher that supports: "
            r8.<init>(r9)
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.fetch(coil.ComponentRegistry, coil.request.ImageRequest, java.lang.Object, coil.request.Options, coil.EventListener, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // coil.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object intercept(coil.intercept.Interceptor.Chain r13, d7.d<? super coil.request.ImageResult> r14) throws java.lang.Throwable {
        /*
            r12 = this;
            boolean r0 = r14 instanceof coil.intercept.EngineInterceptor.C06481
            if (r0 == 0) goto L13
            r0 = r14
            coil.intercept.EngineInterceptor$intercept$1 r0 = (coil.intercept.EngineInterceptor.C06481) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.EngineInterceptor$intercept$1 r0 = new coil.intercept.EngineInterceptor$intercept$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3b
            if (r1 != r2) goto L33
            java.lang.Object r13 = r0.L$1
            coil.intercept.Interceptor$Chain r13 = (coil.intercept.Interceptor.Chain) r13
            java.lang.Object r0 = r0.L$0
            r1 = r0
            coil.intercept.EngineInterceptor r1 = (coil.intercept.EngineInterceptor) r1
            k2.c.G(r14)     // Catch: java.lang.Throwable -> L2e
            return r14
        L2e:
            r0 = move-exception
            r14 = r0
            r4 = r12
            goto Lac
        L33:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L3b:
            k2.c.G(r14)
            coil.request.ImageRequest r5 = r13.getRequest()     // Catch: java.lang.Throwable -> La7
            java.lang.Object r14 = r5.getData()     // Catch: java.lang.Throwable -> La7
            coil.size.Size r1 = r13.getSize()     // Catch: java.lang.Throwable -> La7
            coil.EventListener r8 = android.content.Utils.getEventListener(r13)     // Catch: java.lang.Throwable -> La7
            coil.request.RequestService r3 = r12.requestService     // Catch: java.lang.Throwable -> La7
            coil.request.Options r7 = r3.options(r5, r1)     // Catch: java.lang.Throwable -> La7
            coil.size.Scale r3 = r7.getScale()     // Catch: java.lang.Throwable -> La7
            r8.mapStart(r5, r14)     // Catch: java.lang.Throwable -> La7
            coil.ImageLoader r4 = r12.imageLoader     // Catch: java.lang.Throwable -> La7
            coil.ComponentRegistry r4 = r4.getComponents()     // Catch: java.lang.Throwable -> La7
            java.lang.Object r6 = r4.map(r14, r7)     // Catch: java.lang.Throwable -> La7
            r8.mapEnd(r5, r6)     // Catch: java.lang.Throwable -> La7
            coil.memory.MemoryCacheService r14 = r12.memoryCacheService     // Catch: java.lang.Throwable -> La7
            coil.memory.MemoryCache$Key r9 = r14.newCacheKey(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> La7
            if (r9 == 0) goto L7c
            coil.memory.MemoryCacheService r14 = r12.memoryCacheService     // Catch: java.lang.Throwable -> L77
            coil.memory.MemoryCache$Value r14 = r14.getCacheValue(r5, r9, r1, r3)     // Catch: java.lang.Throwable -> L77
            goto L7d
        L77:
            r0 = move-exception
            r14 = r0
            r1 = r12
            r4 = r1
            goto Lac
        L7c:
            r14 = 0
        L7d:
            if (r14 == 0) goto L86
            coil.memory.MemoryCacheService r0 = r12.memoryCacheService     // Catch: java.lang.Throwable -> L77
            coil.request.SuccessResult r13 = r0.newResult(r13, r5, r9, r14)     // Catch: java.lang.Throwable -> L77
            return r13
        L86:
            ka.f0 r14 = r5.getFetcherDispatcher()     // Catch: java.lang.Throwable -> La7
            coil.intercept.EngineInterceptor$intercept$2 r3 = new coil.intercept.EngineInterceptor$intercept$2     // Catch: java.lang.Throwable -> La7
            r11 = 0
            r4 = r12
            r10 = r13
            r3.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> La2
            r0.L$0 = r4     // Catch: java.lang.Throwable -> La2
            r0.L$1 = r10     // Catch: java.lang.Throwable -> La2
            r0.label = r2     // Catch: java.lang.Throwable -> La2
            java.lang.Object r13 = ka.m0.y(r14, r3, r0)     // Catch: java.lang.Throwable -> La2
            e7.a r14 = e7.a.f15033i
            if (r13 != r14) goto La1
            return r14
        La1:
            return r13
        La2:
            r0 = move-exception
            r14 = r0
            r1 = r4
            r13 = r10
            goto Lac
        La7:
            r0 = move-exception
            r4 = r12
            r10 = r13
            r14 = r0
            r1 = r4
        Lac:
            boolean r0 = r14 instanceof java.util.concurrent.CancellationException
            if (r0 != 0) goto Lbb
            coil.request.RequestService r0 = r1.requestService
            coil.request.ImageRequest r13 = r13.getRequest()
            coil.request.ErrorResult r13 = r0.errorResult(r13, r14)
            return r13
        Lbb:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor.intercept(coil.intercept.Interceptor$Chain, d7.d):java.lang.Object");
    }

    public final Object transform$coil_base_release(ExecuteResult executeResult, ImageRequest imageRequest, Options options, EventListener eventListener, d<? super ExecuteResult> dVar) {
        List<Transformation> transformations = imageRequest.getTransformations();
        if (transformations.isEmpty()) {
            return executeResult;
        }
        if ((executeResult.getDrawable() instanceof BitmapDrawable) || imageRequest.getAllowConversionToBitmap()) {
            return m0.y(imageRequest.getTransformationDispatcher(), new EngineInterceptor$transform$3(this, executeResult, options, transformations, eventListener, imageRequest, null), dVar);
        }
        Logger logger = this.logger;
        if (logger != null && logger.getLevel() <= 4) {
            logger.log(TAG, 4, androidx.compose.material3.d.i('.', "allowConversionToBitmap=false, skipping transformations for type ", executeResult.getDrawable().getClass().getCanonicalName()), null);
        }
        return executeResult;
    }
}
