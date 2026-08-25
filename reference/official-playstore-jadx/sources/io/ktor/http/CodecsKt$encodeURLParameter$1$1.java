package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(B)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CodecsKt$encodeURLParameter$1$1 extends r implements l<Byte, t0> {
    final /* synthetic */ boolean $spaceToPlus;
    final /* synthetic */ StringBuilder $this_buildString;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CodecsKt$encodeURLParameter$1$1(StringBuilder sb2, boolean z) {
        super(1);
        this.$this_buildString = sb2;
        this.$spaceToPlus = z;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).byteValue());
        return t0.f22605a;
    }

    public final void invoke(byte b10) {
        if (CodecsKt.URL_ALPHABET.contains(Byte.valueOf(b10)) || CodecsKt.SPECIAL_SYMBOLS.contains(Byte.valueOf(b10))) {
            this.$this_buildString.append((char) b10);
        } else if (this.$spaceToPlus && b10 == 32) {
            this.$this_buildString.append('+');
        } else {
            this.$this_buildString.append(CodecsKt.percentEncode(b10));
        }
    }
}
