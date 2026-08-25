package io.ktor.http.cio.internals;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "", "", "it", "invoke", "(Ljava/lang/CharSequence;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AsciiCharTree$Companion$build$1<T> extends r implements l<T, Integer> {
    public static final AsciiCharTree$Companion$build$1 INSTANCE = new AsciiCharTree$Companion$build$1();

    public AsciiCharTree$Companion$build$1() {
        super(1);
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/Integer; */
    @Override // r7.l
    public final Integer invoke(CharSequence charSequence) {
        return Integer.valueOf(charSequence.length());
    }
}
