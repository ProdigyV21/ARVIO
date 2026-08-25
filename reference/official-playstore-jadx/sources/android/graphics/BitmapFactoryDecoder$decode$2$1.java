package android.graphics;

import android.graphics.BitmapFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/decode/DecodeResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class BitmapFactoryDecoder$decode$2$1 extends r implements a<DecodeResult> {
    final /* synthetic */ BitmapFactoryDecoder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapFactoryDecoder$decode$2$1(BitmapFactoryDecoder bitmapFactoryDecoder) {
        super(0);
        this.this$0 = bitmapFactoryDecoder;
    }

    @Override // r7.a
    public final DecodeResult invoke() {
        return this.this$0.decode(new BitmapFactory.Options());
    }
}
