package kotlin.reflect;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\b\u0012\u0004\u0012\u00028\u00010\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004:\u0001\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/o;", "T", "V", "Lkotlin/reflect/m;", "Lkotlin/Function1;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface o<T, V> extends m<V>, r7.l<T, V> {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0006\b\u0003\u0010\u0002 \u00012\b\u0012\u0004\u0012\u00028\u00030\u00032\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/o$a;", "T", "V", "Lkotlin/reflect/m$b;", "Lkotlin/Function1;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface a<T, V> extends m.b<V>, r7.l<T, V> {
    }

    Object get(Object obj);

    Object getDelegate(Object obj);

    @Override // kotlin.reflect.m
    a getGetter();
}
