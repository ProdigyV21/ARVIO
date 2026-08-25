package io.ktor.http.cio.internals;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\r\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "", "T", "", "", CmcdData.STREAMING_FORMAT_SS, "idx", "", "invoke", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AsciiCharTree$Companion$build$2<T> extends r implements p<T, Integer, Character> {
    public static final AsciiCharTree$Companion$build$2 INSTANCE = new AsciiCharTree$Companion$build$2();

    public AsciiCharTree$Companion$build$2() {
        super(2);
    }

    /* JADX WARN: Incorrect types in method signature: (TT;I)Ljava/lang/Character; */
    public final Character invoke(CharSequence charSequence, int i10) {
        return Character.valueOf(charSequence.charAt(i10));
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((CharSequence) obj, ((Number) obj2).intValue());
    }
}
