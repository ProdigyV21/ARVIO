package androidx.compose.ui.text.font;

import android.content.Context;
import android.util.TypedValue;
import d7.d;
import i0.o;
import i0.q;
import ka.l;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/font/ResourceFont;", "Landroid/content/Context;", "context", "Landroid/graphics/Typeface;", "load", "(Landroidx/compose/ui/text/font/ResourceFont;Landroid/content/Context;)Landroid/graphics/Typeface;", "loadAsync", "(Landroidx/compose/ui/text/font/ResourceFont;Landroid/content/Context;Ld7/d;)Ljava/lang/Object;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidFontLoader_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final android.graphics.Typeface load(ResourceFont resourceFont, Context context) {
        return q.b(context, resourceFont.getResId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object loadAsync(final ResourceFont resourceFont, Context context, d<? super android.graphics.Typeface> dVar) {
        final l lVar = new l(1, t7.a.A(dVar));
        lVar.s();
        int resId = resourceFont.getResId();
        o oVar = new o() { // from class: androidx.compose.ui.text.font.AndroidFontLoader_androidKt$loadAsync$2$1
            @Override // i0.o
            public void onFontRetrievalFailed(int reason) {
                lVar.cancel(new IllegalStateException("Unable to load font " + resourceFont + " (reason=" + reason + ')'));
            }

            @Override // i0.o
            public void onFontRetrieved(android.graphics.Typeface typeface) {
                lVar.resumeWith(typeface);
            }
        };
        ThreadLocal threadLocal = q.f16004a;
        if (context.isRestricted()) {
            oVar.callbackFailAsync(-4, null);
        } else {
            q.c(context, resId, new TypedValue(), 0, oVar, false);
        }
        return lVar.r();
    }
}
