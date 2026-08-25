package androidx.credentials.provider;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0005\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1 extends r implements l<Byte, CharSequence> {
    public static final CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1 INSTANCE = new CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1();

    public CallingAppInfo$SignatureVerifierApi28$convertToFingerprints$1() {
        super(1);
    }

    public final CharSequence invoke(byte b10) {
        return String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).byteValue());
    }
}
