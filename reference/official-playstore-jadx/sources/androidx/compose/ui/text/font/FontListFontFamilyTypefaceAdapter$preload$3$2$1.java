package androidx.compose.ui.text.font;

import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3$2$1", f = "FontListFontFamilyTypefaceAdapter.kt", l = {107}, m = "invokeSuspend")
public final class FontListFontFamilyTypefaceAdapter$preload$3$2$1 extends j implements p<k0, d<? super Object>, Object> {
    final /* synthetic */ Font $font;
    final /* synthetic */ PlatformFontLoader $resourceLoader;
    int label;
    final /* synthetic */ FontListFontFamilyTypefaceAdapter this$0;

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3$2$1$1", f = "FontListFontFamilyTypefaceAdapter.kt", l = {109}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements l<d<? super Object>, Object> {
        final /* synthetic */ Font $font;
        final /* synthetic */ PlatformFontLoader $resourceLoader;
        int label;

        /* JADX INFO: renamed from: androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3$2$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter$preload$3$2$1$1$1", f = "FontListFontFamilyTypefaceAdapter.kt", l = {MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE}, m = "invokeSuspend")
        public static final class C01221 extends j implements p<k0, d<? super Object>, Object> {
            final /* synthetic */ Font $font;
            final /* synthetic */ PlatformFontLoader $resourceLoader;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01221(PlatformFontLoader platformFontLoader, Font font, d<? super C01221> dVar) {
                super(2, dVar);
                this.$resourceLoader = platformFontLoader;
                this.$font = font;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C01221(this.$resourceLoader, this.$font, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                PlatformFontLoader platformFontLoader = this.$resourceLoader;
                Font font = this.$font;
                this.label = 1;
                Object objAwaitLoad = platformFontLoader.awaitLoad(font, this);
                e7.a aVar = e7.a.f15033i;
                return objAwaitLoad == aVar ? aVar : objAwaitLoad;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<Object> dVar) {
                return ((C01221) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Font font, PlatformFontLoader platformFontLoader, d<? super AnonymousClass1> dVar) {
            super(1, dVar);
            this.$font = font;
            this.$resourceLoader = platformFontLoader;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new AnonymousClass1(this.$font, this.$resourceLoader, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<Object> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    C01221 c01221 = new C01221(this.$resourceLoader, this.$font, null);
                    this.label = 1;
                    obj = m0.z(15000L, c01221, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                if (obj != null) {
                    return obj;
                }
                throw new IllegalStateException("Unable to load font " + this.$font);
            } catch (Exception e5) {
                throw new IllegalStateException("Unable to load font " + this.$font, e5);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FontListFontFamilyTypefaceAdapter$preload$3$2$1(FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter, Font font, PlatformFontLoader platformFontLoader, d<? super FontListFontFamilyTypefaceAdapter$preload$3$2$1> dVar) {
        super(2, dVar);
        this.this$0 = fontListFontFamilyTypefaceAdapter;
        this.$font = font;
        this.$resourceLoader = platformFontLoader;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new FontListFontFamilyTypefaceAdapter$preload$3$2$1(this.this$0, this.$font, this.$resourceLoader, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AsyncTypefaceCache asyncTypefaceCache = this.this$0.asyncTypefaceCache;
        Font font = this.$font;
        PlatformFontLoader platformFontLoader = this.$resourceLoader;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(font, platformFontLoader, null);
        this.label = 1;
        Object objRunCached = asyncTypefaceCache.runCached(font, platformFontLoader, true, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objRunCached == aVar ? aVar : objRunCached;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<Object> dVar) {
        return ((FontListFontFamilyTypefaceAdapter$preload$3$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
