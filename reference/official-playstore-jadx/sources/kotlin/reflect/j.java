package kotlin.reflect;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.reflect.h;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\b\u0012\u0004\u0012\u00028\u00010\u0004:\u0001\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/j;", "T", "V", "Lkotlin/reflect/o;", "Lkotlin/reflect/h;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface j<T, V> extends o<T, V>, h<V> {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\b\u0012\u0004\u0012\u00028\u00030\u00032\u0014\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/j$a;", "T", "V", "Lkotlin/reflect/h$a;", "Lkotlin/Function2;", "Lx6/t0;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface a<T, V> extends h.a<V>, r7.p<T, V, t0> {
    }

    a getSetter();

    void set(Object obj, Object obj2);
}
