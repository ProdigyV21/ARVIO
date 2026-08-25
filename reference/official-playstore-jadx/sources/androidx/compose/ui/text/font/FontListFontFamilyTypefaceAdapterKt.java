package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.AsyncTypefaceCache;
import androidx.compose.ui.text.font.FontLoadingStrategy;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.c0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aY\u0010\f\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000\u0012\u0004\u0012\u00020\t0\u000b*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"", "Landroidx/compose/ui/text/font/Font;", "Landroidx/compose/ui/text/font/TypefaceRequest;", "typefaceRequest", "Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "asyncTypefaceCache", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "platformFontLoader", "Lkotlin/Function1;", "", "createDefaultTypeface", "Lx6/x;", "firstImmediatelyAvailable", "(Ljava/util/List;Landroidx/compose/ui/text/font/TypefaceRequest;Landroidx/compose/ui/text/font/AsyncTypefaceCache;Landroidx/compose/ui/text/font/PlatformFontLoader;Lr7/l;)Lx6/x;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontListFontFamilyTypefaceAdapterKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final x firstImmediatelyAvailable(List<? extends Font> list, TypefaceRequest typefaceRequest, AsyncTypefaceCache asyncTypefaceCache, PlatformFontLoader platformFontLoader, l<? super TypefaceRequest, ? extends Object> lVar) {
        Object objM5267unboximpl;
        Object c0Var;
        Object objM5267unboximpl2;
        int size = list.size();
        ArrayList arrayListG = null;
        for (int i10 = 0; i10 < size; i10++) {
            Font font = list.get(i10);
            int loadingStrategy = font.getLoadingStrategy();
            FontLoadingStrategy.Companion companion = FontLoadingStrategy.INSTANCE;
            if (FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5294getBlockingPKNRLFQ())) {
                synchronized (asyncTypefaceCache.cacheLock) {
                    try {
                        AsyncTypefaceCache.Key key = new AsyncTypefaceCache.Key(font, platformFontLoader.getCacheKey());
                        AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResult = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.resultCache.get(key);
                        if (asyncTypefaceResult == null) {
                            asyncTypefaceResult = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.permanentCache.get(key);
                        }
                        if (asyncTypefaceResult != null) {
                            objM5267unboximpl = asyncTypefaceResult.m5267unboximpl();
                        } else {
                            try {
                                Object objLoadBlocking = platformFontLoader.loadBlocking(font);
                                AsyncTypefaceCache.put$default(asyncTypefaceCache, font, platformFontLoader, objLoadBlocking, false, 8, null);
                                objM5267unboximpl = objLoadBlocking;
                            } catch (Exception e5) {
                                throw new IllegalStateException("Unable to load font " + font, e5);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (objM5267unboximpl != null) {
                    return new x(arrayListG, FontSynthesis_androidKt.m5323synthesizeTypefaceFxwP2eA(typefaceRequest.m5346getFontSynthesisGVVA2EU(), objM5267unboximpl, font, typefaceRequest.getFontWeight(), typefaceRequest.m5345getFontStyle_LCdwA()));
                }
                throw new IllegalStateException("Unable to load font " + font);
            }
            if (FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5295getOptionalLocalPKNRLFQ())) {
                synchronized (asyncTypefaceCache.cacheLock) {
                    try {
                        AsyncTypefaceCache.Key key2 = new AsyncTypefaceCache.Key(font, platformFontLoader.getCacheKey());
                        AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResult2 = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.resultCache.get(key2);
                        if (asyncTypefaceResult2 == null) {
                            asyncTypefaceResult2 = (AsyncTypefaceCache.AsyncTypefaceResult) asyncTypefaceCache.permanentCache.get(key2);
                        }
                        if (asyncTypefaceResult2 != null) {
                            objM5267unboximpl2 = asyncTypefaceResult2.m5267unboximpl();
                        } else {
                            try {
                                c0Var = platformFontLoader.loadBlocking(font);
                            } catch (Throwable th2) {
                                c0Var = new c0(th2);
                            }
                            Object obj = c0Var instanceof c0 ? null : c0Var;
                            AsyncTypefaceCache.put$default(asyncTypefaceCache, font, platformFontLoader, obj, false, 8, null);
                            objM5267unboximpl2 = obj;
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                }
                if (objM5267unboximpl2 != null) {
                    return new x(arrayListG, FontSynthesis_androidKt.m5323synthesizeTypefaceFxwP2eA(typefaceRequest.m5346getFontSynthesisGVVA2EU(), objM5267unboximpl2, font, typefaceRequest.getFontWeight(), typefaceRequest.m5345getFontStyle_LCdwA()));
                }
            } else {
                if (!FontLoadingStrategy.m5289equalsimpl0(loadingStrategy, companion.m5293getAsyncPKNRLFQ())) {
                    throw new IllegalStateException("Unknown font type " + font);
                }
                AsyncTypefaceCache.AsyncTypefaceResult asyncTypefaceResultM5259get1ASDuI8 = asyncTypefaceCache.m5259get1ASDuI8(font, platformFontLoader);
                if (asyncTypefaceResultM5259get1ASDuI8 != null) {
                    if (!AsyncTypefaceCache.AsyncTypefaceResult.m5265isPermanentFailureimpl(asyncTypefaceResultM5259get1ASDuI8.m5267unboximpl()) && asyncTypefaceResultM5259get1ASDuI8.m5267unboximpl() != null) {
                        return new x(arrayListG, FontSynthesis_androidKt.m5323synthesizeTypefaceFxwP2eA(typefaceRequest.m5346getFontSynthesisGVVA2EU(), asyncTypefaceResultM5259get1ASDuI8.m5267unboximpl(), font, typefaceRequest.getFontWeight(), typefaceRequest.m5345getFontStyle_LCdwA()));
                    }
                } else if (arrayListG == null) {
                    arrayListG = t7.a.G(font);
                } else {
                    arrayListG.add(font);
                }
            }
        }
        return new x(arrayListG, lVar.invoke(typefaceRequest));
    }
}
