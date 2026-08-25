package io.ktor.http.cio.internals;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", CmcdData.OBJECT_TYPE_MANIFEST, "Lio/ktor/http/HttpMethod;", "idx", "", "invoke", "(Lio/ktor/http/HttpMethod;I)Ljava/lang/Character;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CharsKt$DefaultHttpMethods$2 extends r implements p<HttpMethod, Integer, Character> {
    public static final CharsKt$DefaultHttpMethods$2 INSTANCE = new CharsKt$DefaultHttpMethods$2();

    public CharsKt$DefaultHttpMethods$2() {
        super(2);
    }

    public final Character invoke(HttpMethod httpMethod, int i10) {
        return Character.valueOf(httpMethod.getValue().charAt(i10));
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((HttpMethod) obj, ((Number) obj2).intValue());
    }
}
