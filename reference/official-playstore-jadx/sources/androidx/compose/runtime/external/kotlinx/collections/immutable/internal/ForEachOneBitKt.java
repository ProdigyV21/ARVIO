package androidx.compose.runtime.external.kotlinx.collections.immutable.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aL\u0010\b\u001a\u00020\u0006*\u00020\u000026\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001H\u0080\b¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "mask", "index", "Lx6/t0;", TtmlNode.TAG_BODY, "forEachOneBit", "(ILr7/p;)V", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForEachOneBitKt {
    public static final void forEachOneBit(int i10, p<? super Integer, ? super Integer, t0> pVar) {
        int i11 = 0;
        while (i10 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i10);
            pVar.invoke(Integer.valueOf(iLowestOneBit), Integer.valueOf(i11));
            i11++;
            i10 ^= iLowestOneBit;
        }
    }
}
