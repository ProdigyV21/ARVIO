package androidx.compose.ui.text.font;

import android.content.Context;
import androidx.compose.ui.text.font.FontLoadingStrategy;
import d7.d;
import f7.e;
import kotlin.Metadata;
import x6.c0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000b\u0010\fR\u001c\u0010\u0003\u001a\n \r*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/text/font/AndroidFontLoader;", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroidx/compose/ui/text/font/Font;", "font", "Landroid/graphics/Typeface;", "loadBlocking", "(Landroidx/compose/ui/text/font/Font;)Landroid/graphics/Typeface;", "awaitLoad", "(Landroidx/compose/ui/text/font/Font;Ld7/d;)Ljava/lang/Object;", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "", "cacheKey", "Ljava/lang/Object;", "getCacheKey", "()Ljava/lang/Object;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidFontLoader implements PlatformFontLoader {
    public static final int $stable = 8;
    private final Object cacheKey;
    private final Context context;

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.ui.text.font.AndroidFontLoader", f = "AndroidFontLoader.android.kt", l = {57, 58}, m = "awaitLoad")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidFontLoader.this.awaitLoad(null, this);
        }
    }

    public AndroidFontLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object awaitLoad(androidx.compose.ui.text.font.Font r5, d7.d<? super android.graphics.Typeface> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.ui.text.font.AndroidFontLoader.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1 r0 = (androidx.compose.ui.text.font.AndroidFontLoader.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1 r0 = new androidx.compose.ui.text.font.AndroidFontLoader$awaitLoad$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3c
            if (r1 == r3) goto L38
            if (r1 != r2) goto L30
            java.lang.Object r5 = r0.L$1
            androidx.compose.ui.text.font.Font r5 = (androidx.compose.ui.text.font.Font) r5
            java.lang.Object r0 = r0.L$0
            androidx.compose.ui.text.font.AndroidFontLoader r0 = (androidx.compose.ui.text.font.AndroidFontLoader) r0
            k2.c.G(r6)
            goto L6e
        L30:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L38:
            k2.c.G(r6)
            return r6
        L3c:
            k2.c.G(r6)
            boolean r6 = r5 instanceof androidx.compose.ui.text.font.AndroidFont
            e7.a r1 = e7.a.f15033i
            if (r6 == 0) goto L57
            androidx.compose.ui.text.font.AndroidFont r5 = (androidx.compose.ui.text.font.AndroidFont) r5
            androidx.compose.ui.text.font.AndroidFont$TypefaceLoader r6 = r5.getTypefaceLoader()
            android.content.Context r2 = r4.context
            r0.label = r3
            java.lang.Object r5 = r6.awaitLoad(r2, r5, r0)
            if (r5 != r1) goto L56
            goto L6c
        L56:
            return r5
        L57:
            boolean r6 = r5 instanceof androidx.compose.ui.text.font.ResourceFont
            if (r6 == 0) goto L7d
            r6 = r5
            androidx.compose.ui.text.font.ResourceFont r6 = (androidx.compose.ui.text.font.ResourceFont) r6
            android.content.Context r3 = r4.context
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = androidx.compose.ui.text.font.AndroidFontLoader_androidKt.access$loadAsync(r6, r3, r0)
            if (r6 != r1) goto L6d
        L6c:
            return r1
        L6d:
            r0 = r4
        L6e:
            android.graphics.Typeface r6 = (android.graphics.Typeface) r6
            androidx.compose.ui.text.font.ResourceFont r5 = (androidx.compose.ui.text.font.ResourceFont) r5
            androidx.compose.ui.text.font.FontVariation$Settings r5 = r5.getVariationSettings()
            android.content.Context r0 = r0.context
            android.graphics.Typeface r5 = androidx.compose.ui.text.font.PlatformTypefaces_androidKt.setFontVariationSettings(r6, r5, r0)
            return r5
        L7d:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown font type: "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.AndroidFontLoader.awaitLoad(androidx.compose.ui.text.font.Font, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public Object getCacheKey() {
        return this.cacheKey;
    }

    @Override // androidx.compose.ui.text.font.PlatformFontLoader
    public android.graphics.Typeface loadBlocking(Font font) {
        Object c0Var;
        android.graphics.Typeface typefaceLoad;
        if (font instanceof AndroidFont) {
            AndroidFont androidFont = (AndroidFont) font;
            return androidFont.getTypefaceLoader().loadBlocking(this.context, androidFont);
        }
        if (!(font instanceof ResourceFont)) {
            return null;
        }
        int loadingStrategy = font.getLoadingStrategy();
        FontLoadingStrategy.Companion companion = FontLoadingStrategy.INSTANCE;
        if (FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5294getBlockingPKNRLFQ())) {
            typefaceLoad = AndroidFontLoader_androidKt.load((ResourceFont) font, this.context);
        } else {
            if (!FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5295getOptionalLocalPKNRLFQ())) {
                if (FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5293getAsyncPKNRLFQ())) {
                    throw new UnsupportedOperationException("Unsupported Async font load path");
                }
                throw new IllegalArgumentException("Unknown loading type " + ((Object) FontLoadingStrategy.m5291toStringimpl(font.getLoadingStrategy())));
            }
            try {
                c0Var = AndroidFontLoader_androidKt.load((ResourceFont) font, this.context);
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            typefaceLoad = (android.graphics.Typeface) (c0Var instanceof c0 ? null : c0Var);
        }
        return PlatformTypefaces_androidKt.setFontVariationSettings(typefaceLoad, ((ResourceFont) font).getVariationSettings(), this.context);
    }
}
