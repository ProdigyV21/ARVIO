package coil;

import android.content.Context;
import android.content.ImageLoaderOptions;
import android.content.Logger;
import android.content.SystemCallbacks;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactoryDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import coil.EventListener;
import coil.ImageLoader;
import coil.disk.DiskCache;
import coil.fetch.AssetUriFetcher;
import coil.fetch.BitmapFetcher;
import coil.fetch.ByteBufferFetcher;
import coil.fetch.ContentUriFetcher;
import coil.fetch.DrawableFetcher;
import coil.fetch.FileFetcher;
import coil.fetch.HttpUriFetcher;
import coil.fetch.ResourceUriFetcher;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.key.FileKeyer;
import coil.key.UriKeyer;
import coil.map.ByteArrayMapper;
import coil.map.FileUriMapper;
import coil.map.HttpUrlMapper;
import coil.map.ResourceIntMapper;
import coil.map.ResourceUriMapper;
import coil.map.StringMapper;
import coil.memory.MemoryCache;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.OneShotDisposable;
import coil.request.RequestService;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.transition.NoneTransition;
import coil.transition.Transition;
import coil.transition.TransitionTarget;
import d7.d;
import d7.h;
import f7.e;
import f7.j;
import gb.b0;
import gb.i;
import io.ktor.http.LinkHeader;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import k2.c;
import ka.g0;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p2;
import ka.q0;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.x;
import pa.q;
import r7.p;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 p2\u00020\u0001:\u0001pBg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0083@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010%\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b%\u0010&J)\u0010(\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020'2\b\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b(\u0010)J\u001f\u0010+\u001a\u00020$2\u0006\u0010*\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b+\u0010,J8\u0010/\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\"2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020$0-H\u0082\b¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u0002012\u0006\u0010*\u001a\u00020\u0017H\u0016¢\u0006\u0004\b2\u00103J\u001b\u00104\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0017H\u0096@ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u0017\u00109\u001a\u00020$2\u0006\u00106\u001a\u00020\u0019H\u0000¢\u0006\u0004\b7\u00108J\u000f\u0010:\u001a\u00020$H\u0016¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010?\u001a\u0004\b@\u0010AR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010B\u001a\u0004\bC\u0010DR\u001f\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010E\u001a\u0004\bF\u0010GR\u001f\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010E\u001a\u0004\bH\u0010GR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00068\u0006¢\u0006\f\n\u0004\b\f\u0010E\u001a\u0004\bI\u0010GR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010J\u001a\u0004\bK\u0010LR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010M\u001a\u0004\bN\u0010OR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010P\u001a\u0004\bQ\u0010RR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010S\u001a\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u001a\u0010_\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b_\u0010M\u001a\u0004\b`\u0010OR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020b0a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010:\u001a\u00020e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010fR\u001d\u0010k\u001a\u0004\u0018\u00010\u00078VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\bg\u0010h*\u0004\bi\u0010jR\u001d\u0010o\u001a\u0004\u0018\u00010\t8VX\u0096\u0084\u0002¢\u0006\f\u001a\u0004\bl\u0010m*\u0004\bn\u0010j\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006q"}, d2 = {"Lcoil/RealImageLoader;", "Lcoil/ImageLoader;", "Landroid/content/Context;", "context", "Lcoil/request/DefaultRequestOptions;", "defaults", "Lx6/s;", "Lcoil/memory/MemoryCache;", "memoryCacheLazy", "Lcoil/disk/DiskCache;", "diskCacheLazy", "Lgb/i$a;", "callFactoryLazy", "Lcoil/EventListener$Factory;", "eventListenerFactory", "Lcoil/ComponentRegistry;", "componentRegistry", "Lcoil/util/ImageLoaderOptions;", "options", "Lcoil/util/Logger;", "logger", "<init>", "(Landroid/content/Context;Lcoil/request/DefaultRequestOptions;Lx6/s;Lx6/s;Lx6/s;Lcoil/EventListener$Factory;Lcoil/ComponentRegistry;Lcoil/util/ImageLoaderOptions;Lcoil/util/Logger;)V", "Lcoil/request/ImageRequest;", "initialRequest", "", LinkHeader.Parameters.Type, "Lcoil/request/ImageResult;", "executeMain", "(Lcoil/request/ImageRequest;ILd7/d;)Ljava/lang/Object;", "Lcoil/request/SuccessResult;", "result", "Lcoil/target/Target;", "target", "Lcoil/EventListener;", "eventListener", "Lx6/t0;", "onSuccess", "(Lcoil/request/SuccessResult;Lcoil/target/Target;Lcoil/EventListener;)V", "Lcoil/request/ErrorResult;", "onError", "(Lcoil/request/ErrorResult;Lcoil/target/Target;Lcoil/EventListener;)V", "request", "onCancel", "(Lcoil/request/ImageRequest;Lcoil/EventListener;)V", "Lkotlin/Function0;", "setDrawable", "transition", "(Lcoil/request/ImageResult;Lcoil/target/Target;Lcoil/EventListener;Lr7/a;)V", "Lcoil/request/Disposable;", "enqueue", "(Lcoil/request/ImageRequest;)Lcoil/request/Disposable;", "execute", "(Lcoil/request/ImageRequest;Ld7/d;)Ljava/lang/Object;", "level", "onTrimMemory$coil_base_release", "(I)V", "onTrimMemory", "shutdown", "()V", "Lcoil/ImageLoader$Builder;", "newBuilder", "()Lcoil/ImageLoader$Builder;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcoil/request/DefaultRequestOptions;", "getDefaults", "()Lcoil/request/DefaultRequestOptions;", "Lx6/s;", "getMemoryCacheLazy", "()Lx6/s;", "getDiskCacheLazy", "getCallFactoryLazy", "Lcoil/EventListener$Factory;", "getEventListenerFactory", "()Lcoil/EventListener$Factory;", "Lcoil/ComponentRegistry;", "getComponentRegistry", "()Lcoil/ComponentRegistry;", "Lcoil/util/ImageLoaderOptions;", "getOptions", "()Lcoil/util/ImageLoaderOptions;", "Lcoil/util/Logger;", "getLogger", "()Lcoil/util/Logger;", "Lka/k0;", "scope", "Lka/k0;", "Lcoil/util/SystemCallbacks;", "systemCallbacks", "Lcoil/util/SystemCallbacks;", "Lcoil/request/RequestService;", "requestService", "Lcoil/request/RequestService;", "components", "getComponents", "", "Lcoil/intercept/Interceptor;", "interceptors", "Ljava/util/List;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getMemoryCache", "()Lcoil/memory/MemoryCache;", "getMemoryCache$delegate", "(Lcoil/RealImageLoader;)Ljava/lang/Object;", "memoryCache", "getDiskCache", "()Lcoil/disk/DiskCache;", "getDiskCache$delegate", "diskCache", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealImageLoader implements ImageLoader {
    private static final int REQUEST_TYPE_ENQUEUE = 0;
    private static final int REQUEST_TYPE_EXECUTE = 1;
    private static final String TAG = "RealImageLoader";
    private final s<i.a> callFactoryLazy;
    private final ComponentRegistry componentRegistry;
    private final ComponentRegistry components;
    private final Context context;
    private final DefaultRequestOptions defaults;
    private final s<DiskCache> diskCacheLazy;
    private final EventListener.Factory eventListenerFactory;
    private final List<Interceptor> interceptors;
    private final Logger logger;
    private final s<MemoryCache> memoryCacheLazy;
    private final ImageLoaderOptions options;
    private final RequestService requestService;
    private final k0 scope;
    private final AtomicBoolean shutdown;
    private final SystemCallbacks systemCallbacks;

    /* JADX INFO: renamed from: coil.RealImageLoader$execute$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/request/ImageResult;", "<anonymous>", "(Lka/k0;)Lcoil/request/ImageResult;"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.RealImageLoader$execute$2", f = "RealImageLoader.kt", l = {140}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super ImageResult>, Object> {
        final /* synthetic */ ImageRequest $request;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ RealImageLoader this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ImageRequest imageRequest, RealImageLoader realImageLoader, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$request = imageRequest;
            this.this$0 = realImageLoader;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$request, this.this$0, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return obj;
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            x0 x0Var = x0.f19652a;
            q0 q0VarE = m0.e(2, ((la.d) q.f21294a).f20077n, k0Var, new RealImageLoader$execute$2$job$1(this.this$0, this.$request, null));
            if (this.$request.getTarget() instanceof ViewTarget) {
                Utils.getRequestManager(((ViewTarget) this.$request.getTarget()).getView()).getDisposable(q0VarE);
            }
            this.label = 1;
            Object objJ = q0VarE.j(this);
            e7.a aVar = e7.a.f15033i;
            return objJ == aVar ? aVar : objJ;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super ImageResult> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: coil.RealImageLoader$executeMain$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "coil.RealImageLoader", f = "RealImageLoader.kt", l = {166, 178, 182}, m = "executeMain")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RealImageLoader.this.executeMain(null, 0, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RealImageLoader(Context context, DefaultRequestOptions defaultRequestOptions, s<? extends MemoryCache> sVar, s<? extends DiskCache> sVar2, s<? extends i.a> sVar3, EventListener.Factory factory, ComponentRegistry componentRegistry, ImageLoaderOptions imageLoaderOptions, Logger logger) {
        this.context = context;
        this.defaults = defaultRequestOptions;
        this.memoryCacheLazy = sVar;
        this.diskCacheLazy = sVar2;
        this.callFactoryLazy = sVar3;
        this.eventListenerFactory = factory;
        this.componentRegistry = componentRegistry;
        this.options = imageLoaderOptions;
        this.logger = logger;
        p2 p2VarD = m0.d();
        x0 x0Var = x0.f19652a;
        this.scope = l0.a(h.a(p2VarD, ((la.d) q.f21294a).f20077n).plus(new RealImageLoader$special$$inlined$CoroutineExceptionHandler$1(g0.f19591i, this)));
        SystemCallbacks systemCallbacks = new SystemCallbacks(this, context, imageLoaderOptions.getNetworkObserverEnabled());
        this.systemCallbacks = systemCallbacks;
        RequestService requestService = new RequestService(this, systemCallbacks, logger);
        this.requestService = requestService;
        this.components = componentRegistry.newBuilder().add(new HttpUrlMapper(), b0.class).add(new StringMapper(), String.class).add(new FileUriMapper(), Uri.class).add(new ResourceUriMapper(), Uri.class).add(new ResourceIntMapper(), Integer.class).add(new ByteArrayMapper(), byte[].class).add(new UriKeyer(), Uri.class).add(new FileKeyer(imageLoaderOptions.getAddLastModifiedToFileCacheKey()), File.class).add(new HttpUriFetcher.Factory(sVar3, sVar2, imageLoaderOptions.getRespectCacheHeaders()), Uri.class).add(new FileFetcher.Factory(), File.class).add(new AssetUriFetcher.Factory(), Uri.class).add(new ContentUriFetcher.Factory(), Uri.class).add(new ResourceUriFetcher.Factory(), Uri.class).add(new DrawableFetcher.Factory(), Drawable.class).add(new BitmapFetcher.Factory(), Bitmap.class).add(new ByteBufferFetcher.Factory(), ByteBuffer.class).add(new BitmapFactoryDecoder.Factory(imageLoaderOptions.getBitmapFactoryMaxParallelism(), imageLoaderOptions.getBitmapFactoryExifOrientationPolicy())).build();
        this.interceptors = x.H0(new EngineInterceptor(this, requestService, logger), getComponents().getInterceptors());
        this.shutdown = new AtomicBoolean(false);
        systemCallbacks.register();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(18:0|2|(2:4|(1:6)(1:7))(0)|8|(1:(4:(1:(7:13|111|14|76|(1:78)(2:79|(1:81))|82|83)(2:18|19))(13:20|103|21|22|23|107|69|70|112|71|72|(5:75|76|(0)(0)|82|83)|74)|110|90|(3:92|93|94)(2:97|98))(4:26|101|27|41))(4:32|109|33|(2:35|(3:37|(2:40|41)|74)(1:44))(2:88|89))|105|45|(11:47|(1:49)(1:52)|(1:54)|(1:57)(1:58)|59|(1:61)|62|(1:64)|65|(9:68|23|107|69|70|112|71|72|(0))|74)|55|(0)(0)|59|(0)|62|(0)|65|(0)|74|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0104, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0105, code lost:
    
        r6 = r2;
        r5 = r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0113 A[Catch: all -> 0x0104, TryCatch #2 {all -> 0x0104, blocks: (B:45:0x00f3, B:47:0x00f9, B:49:0x00ff, B:54:0x010b, B:57:0x0113, B:59:0x0125, B:61:0x012b, B:62:0x012e, B:64:0x0137, B:65:0x013a, B:58:0x0121), top: B:105:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0121 A[Catch: all -> 0x0104, TryCatch #2 {all -> 0x0104, blocks: (B:45:0x00f3, B:47:0x00f9, B:49:0x00ff, B:54:0x010b, B:57:0x0113, B:59:0x0125, B:61:0x012b, B:62:0x012e, B:64:0x0137, B:65:0x013a, B:58:0x0121), top: B:105:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012b A[Catch: all -> 0x0104, TryCatch #2 {all -> 0x0104, blocks: (B:45:0x00f3, B:47:0x00f9, B:49:0x00ff, B:54:0x010b, B:57:0x0113, B:59:0x0125, B:61:0x012b, B:62:0x012e, B:64:0x0137, B:65:0x013a, B:58:0x0121), top: B:105:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0137 A[Catch: all -> 0x0104, TryCatch #2 {all -> 0x0104, blocks: (B:45:0x00f3, B:47:0x00f9, B:49:0x00ff, B:54:0x010b, B:57:0x0113, B:59:0x0125, B:61:0x012b, B:62:0x012e, B:64:0x0137, B:65:0x013a, B:58:0x0121), top: B:105:0x00f3 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0189 A[Catch: all -> 0x0049, TryCatch #6 {all -> 0x0049, blocks: (B:14:0x0044, B:76:0x0183, B:78:0x0189, B:79:0x0194, B:81:0x0198), top: B:111:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0194 A[Catch: all -> 0x0049, TryCatch #6 {all -> 0x0049, blocks: (B:14:0x0044, B:76:0x0183, B:78:0x0189, B:79:0x0194, B:81:0x0198), top: B:111:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b8 A[Catch: all -> 0x01c9, TRY_LEAVE, TryCatch #5 {all -> 0x01c9, blocks: (B:90:0x01b4, B:92:0x01b8, B:97:0x01cb, B:98:0x01ce), top: B:110:0x01b4 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01cb A[Catch: all -> 0x01c9, TRY_ENTER, TryCatch #5 {all -> 0x01c9, blocks: (B:90:0x01b4, B:92:0x01b8, B:97:0x01cb, B:98:0x01ce), top: B:110:0x01b4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeMain(coil.request.ImageRequest r20, int r21, d7.d<? super coil.request.ImageResult> r22) {
        /*
            Method dump skipped, instruction units count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.executeMain(coil.request.ImageRequest, int, d7.d):java.lang.Object");
    }

    private final void onCancel(ImageRequest request, EventListener eventListener) {
        Logger logger = this.logger;
        if (logger != null && logger.getLevel() <= 4) {
            logger.log(TAG, 4, "🏗  Cancelled - " + request.getData(), null);
        }
        eventListener.onCancel(request);
        ImageRequest.Listener listener = request.getListener();
        if (listener != null) {
            listener.onCancel(request);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onError(coil.request.ErrorResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.util.Logger r1 = r6.logger
            if (r1 == 0) goto L34
            int r2 = r1.getLevel()
            r3 = 4
            if (r2 > r3) goto L34
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "🚨 Failed - "
            r2.<init>(r4)
            java.lang.Object r4 = r0.getData()
            r2.append(r4)
            java.lang.String r4 = " - "
            r2.append(r4)
            java.lang.Throwable r4 = r7.getThrowable()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r4 = 0
            java.lang.String r5 = "RealImageLoader"
            r1.log(r5, r3, r2, r4)
        L34:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L3b
            if (r8 == 0) goto L67
            goto L4e
        L3b:
            coil.request.ImageRequest r1 = r7.getRequest()
            coil.transition.Transition$Factory r1 = r1.getTransitionFactory()
            r2 = r8
            coil.transition.TransitionTarget r2 = (coil.transition.TransitionTarget) r2
            coil.transition.Transition r1 = r1.create(r2, r7)
            boolean r2 = r1 instanceof coil.transition.NoneTransition
            if (r2 == 0) goto L56
        L4e:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onError(r1)
            goto L67
        L56:
            coil.request.ImageRequest r8 = r7.getRequest()
            r9.transitionStart(r8, r1)
            r1.transition()
            coil.request.ImageRequest r8 = r7.getRequest()
            r9.transitionEnd(r8, r1)
        L67:
            r9.onError(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L73
            r8.onError(r0, r7)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onError(coil.request.ErrorResult, coil.target.Target, coil.EventListener):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onSuccess(coil.request.SuccessResult r7, coil.target.Target r8, coil.EventListener r9) {
        /*
            r6 = this;
            coil.request.ImageRequest r0 = r7.getRequest()
            coil.decode.DataSource r1 = r7.getDataSource()
            coil.util.Logger r2 = r6.logger
            if (r2 == 0) goto L41
            int r3 = r2.getLevel()
            r4 = 4
            if (r3 > r4) goto L41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = android.content.Utils.getEmoji(r1)
            r3.append(r5)
            java.lang.String r5 = " Successful ("
            r3.append(r5)
            java.lang.String r1 = r1.name()
            r3.append(r1)
            java.lang.String r1 = ") - "
            r3.append(r1)
            java.lang.Object r1 = r0.getData()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r3 = 0
            java.lang.String r5 = "RealImageLoader"
            r2.log(r5, r4, r1, r3)
        L41:
            boolean r1 = r8 instanceof coil.transition.TransitionTarget
            if (r1 != 0) goto L48
            if (r8 == 0) goto L74
            goto L5b
        L48:
            coil.request.ImageRequest r1 = r7.getRequest()
            coil.transition.Transition$Factory r1 = r1.getTransitionFactory()
            r2 = r8
            coil.transition.TransitionTarget r2 = (coil.transition.TransitionTarget) r2
            coil.transition.Transition r1 = r1.create(r2, r7)
            boolean r2 = r1 instanceof coil.transition.NoneTransition
            if (r2 == 0) goto L63
        L5b:
            android.graphics.drawable.Drawable r1 = r7.getDrawable()
            r8.onSuccess(r1)
            goto L74
        L63:
            coil.request.ImageRequest r8 = r7.getRequest()
            r9.transitionStart(r8, r1)
            r1.transition()
            coil.request.ImageRequest r8 = r7.getRequest()
            r9.transitionEnd(r8, r1)
        L74:
            r9.onSuccess(r0, r7)
            coil.request.ImageRequest$Listener r8 = r0.getListener()
            if (r8 == 0) goto L80
            r8.onSuccess(r0, r7)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.onSuccess(coil.request.SuccessResult, coil.target.Target, coil.EventListener):void");
    }

    private final void transition(ImageResult result, Target target, EventListener eventListener, r7.a<t0> setDrawable) {
        if (!(target instanceof TransitionTarget)) {
            setDrawable.invoke();
            return;
        }
        Transition transitionCreate = result.getRequest().getTransitionFactory().create((TransitionTarget) target, result);
        if (transitionCreate instanceof NoneTransition) {
            setDrawable.invoke();
            return;
        }
        eventListener.transitionStart(result.getRequest(), transitionCreate);
        transitionCreate.transition();
        eventListener.transitionEnd(result.getRequest(), transitionCreate);
    }

    @Override // coil.ImageLoader
    public Disposable enqueue(ImageRequest request) {
        q0 q0VarE = m0.e(3, null, this.scope, new RealImageLoader$enqueue$job$1(this, request, null));
        return request.getTarget() instanceof ViewTarget ? Utils.getRequestManager(((ViewTarget) request.getTarget()).getView()).getDisposable(q0VarE) : new OneShotDisposable(q0VarE);
    }

    @Override // coil.ImageLoader
    public Object execute(ImageRequest imageRequest, d<? super ImageResult> dVar) {
        return l0.c(new AnonymousClass2(imageRequest, this, null), dVar);
    }

    public final s<i.a> getCallFactoryLazy() {
        return this.callFactoryLazy;
    }

    public final ComponentRegistry getComponentRegistry() {
        return this.componentRegistry;
    }

    @Override // coil.ImageLoader
    public ComponentRegistry getComponents() {
        return this.components;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // coil.ImageLoader
    public DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    @Override // coil.ImageLoader
    public DiskCache getDiskCache() {
        return (DiskCache) this.diskCacheLazy.getValue();
    }

    public final s<DiskCache> getDiskCacheLazy() {
        return this.diskCacheLazy;
    }

    public final EventListener.Factory getEventListenerFactory() {
        return this.eventListenerFactory;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    @Override // coil.ImageLoader
    public MemoryCache getMemoryCache() {
        return (MemoryCache) this.memoryCacheLazy.getValue();
    }

    public final s<MemoryCache> getMemoryCacheLazy() {
        return this.memoryCacheLazy;
    }

    public final ImageLoaderOptions getOptions() {
        return this.options;
    }

    @Override // coil.ImageLoader
    public ImageLoader.Builder newBuilder() {
        return new ImageLoader.Builder(this);
    }

    public final void onTrimMemory$coil_base_release(int level) {
        MemoryCache memoryCache;
        s<MemoryCache> sVar = this.memoryCacheLazy;
        if (sVar == null || (memoryCache = (MemoryCache) sVar.getValue()) == null) {
            return;
        }
        memoryCache.trimMemory(level);
    }

    @Override // coil.ImageLoader
    public void shutdown() {
        if (this.shutdown.getAndSet(true)) {
            return;
        }
        l0.b(this.scope, null);
        this.systemCallbacks.shutdown();
        MemoryCache memoryCache = getMemoryCache();
        if (memoryCache != null) {
            memoryCache.clear();
        }
    }
}
