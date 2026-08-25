package i9;

import androidx.media3.exoplayer.upstream.CmcdData;
import v9.o0;
import w9.c;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements c.a {
    public static /* synthetic */ void a(int i10) {
        Object[] objArr = new Object[3];
        if (i10 != 1) {
            objArr[0] = CmcdData.OBJECT_TYPE_AUDIO_ONLY;
        } else {
            objArr[0] = "b";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
        objArr[2] = "equals";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // w9.c.a
    public final boolean b(o0 o0Var, o0 o0Var2) {
        if (o0Var == null) {
            a(0);
            throw null;
        }
        if (o0Var2 != null) {
            return o0Var.equals(o0Var2);
        }
        a(1);
        throw null;
    }
}
