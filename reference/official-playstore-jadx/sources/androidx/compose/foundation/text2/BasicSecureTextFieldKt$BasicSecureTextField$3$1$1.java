package androidx.compose.foundation.text2;

import androidx.compose.foundation.text2.input.ImeActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.p;
import x6.o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class BasicSecureTextFieldKt$BasicSecureTextField$3$1$1 implements ImeActionHandler, j {
    final /* synthetic */ ImeActionHandler $tmp0;

    public BasicSecureTextFieldKt$BasicSecureTextField$3$1$1(ImeActionHandler imeActionHandler) {
        this.$tmp0 = imeActionHandler;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof ImeActionHandler) && (obj instanceof j)) {
            return p.a(getFunctionDelegate(), ((j) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.j
    public final o<?> getFunctionDelegate() {
        return new m(1, 0, ImeActionHandler.class, this.$tmp0, "onImeAction", "onImeAction-KlQnJC8(I)Z");
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    @Override // androidx.compose.foundation.text2.input.ImeActionHandler
    /* JADX INFO: renamed from: onImeAction-KlQnJC8, reason: not valid java name */
    public final boolean mo1049onImeActionKlQnJC8(int i10) {
        return this.$tmp0.mo1049onImeActionKlQnJC8(i10);
    }
}
