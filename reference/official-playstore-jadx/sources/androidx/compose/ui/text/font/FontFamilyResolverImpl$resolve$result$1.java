package androidx.compose.ui.text.font;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/ui/text/font/TypefaceResult;", "Lx6/t0;", "onAsyncCompletion", "invoke", "(Lr7/l;)Landroidx/compose/ui/text/font/TypefaceResult;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class FontFamilyResolverImpl$resolve$result$1 extends r implements l<l<? super TypefaceResult, ? extends t0>, TypefaceResult> {
    final /* synthetic */ TypefaceRequest $typefaceRequest;
    final /* synthetic */ FontFamilyResolverImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FontFamilyResolverImpl$resolve$result$1(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest) {
        super(1);
        this.this$0 = fontFamilyResolverImpl;
        this.$typefaceRequest = typefaceRequest;
    }

    @Override // r7.l
    public final TypefaceResult invoke(l<? super TypefaceResult, t0> lVar) {
        TypefaceResult typefaceResultResolve = this.this$0.fontListFontFamilyTypefaceAdapter.resolve(this.$typefaceRequest, this.this$0.getPlatformFontLoader(), lVar, this.this$0.createDefaultTypeface);
        if (typefaceResultResolve != null) {
            return typefaceResultResolve;
        }
        TypefaceResult typefaceResultResolve2 = this.this$0.platformFamilyTypefaceAdapter.resolve(this.$typefaceRequest, this.this$0.getPlatformFontLoader(), lVar, this.this$0.createDefaultTypeface);
        if (typefaceResultResolve2 != null) {
            return typefaceResultResolve2;
        }
        throw new IllegalStateException("Could not load font");
    }
}
