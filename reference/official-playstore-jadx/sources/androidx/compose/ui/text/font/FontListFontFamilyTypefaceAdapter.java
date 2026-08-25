package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.TypefaceResult;
import androidx.compose.ui.text.platform.DispatcherKt;
import d7.d;
import d7.k;
import f7.e;
import f7.j;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ka.g0;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p2;
import ka.u1;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlinx.coroutines.CoroutineExceptionHandler;
import r7.l;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\r\u0010\u000eJI\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\n2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u00122\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00150\u0012H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/text/font/FontListFontFamilyTypefaceAdapter;", "Landroidx/compose/ui/text/font/FontFamilyTypefaceAdapter;", "Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "asyncTypefaceCache", "Ld7/j;", "injectedContext", "<init>", "(Landroidx/compose/ui/text/font/AsyncTypefaceCache;Ld7/j;)V", "Landroidx/compose/ui/text/font/FontFamily;", "family", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "resourceLoader", "Lx6/t0;", LinkHeader.Rel.PreLoad, "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/PlatformFontLoader;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/text/font/TypefaceRequest;", "typefaceRequest", "platformFontLoader", "Lkotlin/Function1;", "Landroidx/compose/ui/text/font/TypefaceResult$Immutable;", "onAsyncCompletion", "", "createDefaultTypeface", "Landroidx/compose/ui/text/font/TypefaceResult;", "resolve", "(Landroidx/compose/ui/text/font/TypefaceRequest;Landroidx/compose/ui/text/font/PlatformFontLoader;Lr7/l;Lr7/l;)Landroidx/compose/ui/text/font/TypefaceResult;", "Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "Lka/k0;", "asyncLoadScope", "Lka/k0;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FontListFontFamilyTypefaceAdapter implements FontFamilyTypefaceAdapter {
    private k0 asyncLoadScope;
    private final AsyncTypefaceCache asyncTypefaceCache;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final FontMatcher fontMatcher = new FontMatcher();
    private static final CoroutineExceptionHandler DropExceptionHandler = new FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1(g0.f19591i);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/text/font/FontListFontFamilyTypefaceAdapter$Companion;", "", "()V", "DropExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getDropExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "fontMatcher", "Landroidx/compose/ui/text/font/FontMatcher;", "getFontMatcher", "()Landroidx/compose/ui/text/font/FontMatcher;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final CoroutineExceptionHandler getDropExceptionHandler() {
            return FontListFontFamilyTypefaceAdapter.DropExceptionHandler;
        }

        public final FontMatcher getFontMatcher() {
            return FontListFontFamilyTypefaceAdapter.fontMatcher;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3", f = "FontListFontFamilyTypefaceAdapter.kt", l = {117}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ List<Font> $asyncLoads;
        final /* synthetic */ PlatformFontLoader $resourceLoader;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ FontListFontFamilyTypefaceAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(List<Font> list, FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter, PlatformFontLoader platformFontLoader, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$asyncLoads = list;
            this.this$0 = fontListFontFamilyTypefaceAdapter;
            this.$resourceLoader = platformFontLoader;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$asyncLoads, this.this$0, this.$resourceLoader, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                List<Font> list = this.$asyncLoads;
                HashSet hashSet = new HashSet(list.size());
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i11 = 0; i11 < size; i11++) {
                    Font font = list.get(i11);
                    if (hashSet.add(font)) {
                        arrayList.add(font);
                    }
                }
                FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter = this.this$0;
                PlatformFontLoader platformFontLoader = this.$resourceLoader;
                ArrayList arrayList2 = new ArrayList(arrayList.size());
                int size2 = arrayList.size();
                for (int i12 = 0; i12 < size2; i12++) {
                    arrayList2.add(m0.e(3, null, k0Var, new FontListFontFamilyTypefaceAdapter$preload$3$2$1(fontListFontFamilyTypefaceAdapter, (Font) arrayList.get(i12), platformFontLoader, null)));
                }
                this.label = 1;
                Object objN = m0.n(arrayList2, this);
                e7.a aVar = e7.a.f15033i;
                if (objN == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$resolve$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$resolve$1", f = "FontListFontFamilyTypefaceAdapter.kt", l = {153}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ AsyncFontListLoader $asyncLoader;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AsyncFontListLoader asyncFontListLoader, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$asyncLoader = asyncFontListLoader;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$asyncLoader, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AsyncFontListLoader asyncFontListLoader = this.$asyncLoader;
                this.label = 1;
                Object objLoad = asyncFontListLoader.load(this);
                e7.a aVar = e7.a.f15033i;
                if (objLoad == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FontListFontFamilyTypefaceAdapter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final Object preload(FontFamily fontFamily, PlatformFontLoader platformFontLoader, d<? super t0> dVar) {
        FontFamily fontFamily2 = fontFamily;
        boolean z = fontFamily2 instanceof FontListFontFamily;
        t0 t0Var = t0.f22605a;
        if (!z) {
            return t0Var;
        }
        FontListFontFamily fontListFontFamily = (FontListFontFamily) fontFamily2;
        List<Font> fonts = fontListFontFamily.getFonts();
        List<Font> fonts2 = fontListFontFamily.getFonts();
        ArrayList arrayList = new ArrayList(fonts2.size());
        int size = fonts2.size();
        for (int i10 = 0; i10 < size; i10++) {
            Font font = fonts2.get(i10);
            if (FontLoadingStrategy.m5289equalsimpl0(font.getLoadingStrategy(), FontLoadingStrategy.INSTANCE.m5293getAsyncPKNRLFQ())) {
                arrayList.add(font);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i11 = 0; i11 < size2; i11++) {
            Font font2 = (Font) arrayList.get(i11);
            arrayList2.add(new x(font2.getWeight(), FontStyle.m5299boximpl(font2.getStyle())));
        }
        HashSet hashSet = new HashSet(arrayList2.size());
        ArrayList arrayList3 = new ArrayList(arrayList2.size());
        int size3 = arrayList2.size();
        for (int i12 = 0; i12 < size3; i12++) {
            Object obj = arrayList2.get(i12);
            if (hashSet.add((x) obj)) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        int size4 = arrayList3.size();
        int i13 = 0;
        while (i13 < size4) {
            x xVar = (x) arrayList3.get(i13);
            FontWeight fontWeight = (FontWeight) xVar.f22608i;
            int iM5305unboximpl = ((FontStyle) xVar.f22609l).m5305unboximpl();
            List list = (List) FontListFontFamilyTypefaceAdapterKt.firstImmediatelyAvailable(fontMatcher.m5298matchFontRetOiIg(fonts, fontWeight, iM5305unboximpl), new TypefaceRequest(fontFamily2, fontWeight, iM5305unboximpl, FontSynthesis.INSTANCE.m5319getAllGVVA2EU(), platformFontLoader.getCacheKey(), null), this.asyncTypefaceCache, platformFontLoader, FontListFontFamilyTypefaceAdapter$preload$2$1.INSTANCE).f22608i;
            if (list != null) {
                arrayList4.add(kotlin.collections.x.m0(list));
            }
            i13++;
            fontFamily2 = fontFamily;
        }
        Object objC = l0.c(new AnonymousClass3(arrayList4, this, platformFontLoader, null), dVar);
        return objC == e7.a.f15033i ? objC : t0Var;
    }

    @Override // androidx.compose.ui.text.font.FontFamilyTypefaceAdapter
    public TypefaceResult resolve(TypefaceRequest typefaceRequest, PlatformFontLoader platformFontLoader, l<? super TypefaceResult.Immutable, t0> onAsyncCompletion, l<? super TypefaceRequest, ? extends Object> createDefaultTypeface) {
        if (!(typefaceRequest.getFontFamily() instanceof FontListFontFamily)) {
            return null;
        }
        x xVarFirstImmediatelyAvailable = FontListFontFamilyTypefaceAdapterKt.firstImmediatelyAvailable(fontMatcher.m5298matchFontRetOiIg(((FontListFontFamily) typefaceRequest.getFontFamily()).getFonts(), typefaceRequest.getFontWeight(), typefaceRequest.m5345getFontStyle_LCdwA()), typefaceRequest, this.asyncTypefaceCache, platformFontLoader, createDefaultTypeface);
        List list = (List) xVarFirstImmediatelyAvailable.f22608i;
        Object obj = xVarFirstImmediatelyAvailable.f22609l;
        if (list == null) {
            return new TypefaceResult.Immutable(obj, false, 2, null);
        }
        AsyncFontListLoader asyncFontListLoader = new AsyncFontListLoader(list, obj, typefaceRequest, this.asyncTypefaceCache, onAsyncCompletion, platformFontLoader);
        m0.p(this.asyncLoadScope, null, 4, new AnonymousClass1(asyncFontListLoader, null), 1);
        return new TypefaceResult.Async(asyncFontListLoader);
    }

    public FontListFontFamilyTypefaceAdapter(AsyncTypefaceCache asyncTypefaceCache, d7.j jVar) {
        this.asyncTypefaceCache = asyncTypefaceCache;
        this.asyncLoadScope = l0.a(DropExceptionHandler.plus(DispatcherKt.getFontCacheManagementDispatcher()).plus(jVar).plus(new p2((v1) jVar.get(u1.f19642i))));
    }

    public /* synthetic */ FontListFontFamilyTypefaceAdapter(AsyncTypefaceCache asyncTypefaceCache, d7.j jVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? new AsyncTypefaceCache() : asyncTypefaceCache, (i10 & 2) != 0 ? k.f14688i : jVar);
    }
}
