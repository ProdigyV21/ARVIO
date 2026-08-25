package d7;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Ld7/j;", "", "b", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface j {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Ld7/j$a;", "Ld7/j;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface a extends j {
        b getKey();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003¨\u0006\u0004"}, d2 = {"Ld7/j$b;", "Ld7/j$a;", "E", "", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface b<E extends a> {
    }

    Object fold(Object obj, p pVar);

    a get(b bVar);

    j minusKey(b bVar);

    j plus(j jVar);
}
