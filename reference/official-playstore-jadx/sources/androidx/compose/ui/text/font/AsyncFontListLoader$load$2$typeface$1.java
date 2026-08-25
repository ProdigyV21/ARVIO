package androidx.compose.ui.text.font;

import d7.d;
import f7.e;
import f7.j;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "androidx.compose.ui.text.font.AsyncFontListLoader$load$2$typeface$1", f = "FontListFontFamilyTypefaceAdapter.kt", l = {273}, m = "invokeSuspend")
public final class AsyncFontListLoader$load$2$typeface$1 extends j implements l<d<? super Object>, Object> {
    final /* synthetic */ Font $font;
    int label;
    final /* synthetic */ AsyncFontListLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFontListLoader$load$2$typeface$1(AsyncFontListLoader asyncFontListLoader, Font font, d<? super AsyncFontListLoader$load$2$typeface$1> dVar) {
        super(1, dVar);
        this.this$0 = asyncFontListLoader;
        this.$font = font;
    }

    @Override // f7.a
    public final d<t0> create(d<?> dVar) {
        return new AsyncFontListLoader$load$2$typeface$1(this.this$0, this.$font, dVar);
    }

    @Override // r7.l
    public final Object invoke(d<Object> dVar) {
        return ((AsyncFontListLoader$load$2$typeface$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AsyncFontListLoader asyncFontListLoader = this.this$0;
        Font font = this.$font;
        this.label = 1;
        Object objLoadWithTimeoutOrNull$ui_text_release = asyncFontListLoader.loadWithTimeoutOrNull$ui_text_release(font, this);
        e7.a aVar = e7.a.f15033i;
        return objLoadWithTimeoutOrNull$ui_text_release == aVar ? aVar : objLoadWithTimeoutOrNull$ui_text_release;
    }
}
