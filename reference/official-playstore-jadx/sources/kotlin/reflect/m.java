package kotlin.reflect;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0002\u0003\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/m;", "V", "Lkotlin/reflect/c;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface m<V> extends c<V> {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lkotlin/reflect/m$a;", "V", "", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface a<V> {
        m c();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/reflect/m$b;", "V", "Lkotlin/reflect/m$a;", "Lkotlin/reflect/g;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface b<V> extends a<V>, g<V> {
    }

    b getGetter();

    boolean isConst();

    boolean isLateinit();
}
