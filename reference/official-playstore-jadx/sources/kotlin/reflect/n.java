package kotlin.reflect;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/n;", "V", "Lkotlin/reflect/m;", "Lkotlin/Function0;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface n<V> extends m<V>, r7.a<V> {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/reflect/n$a;", "V", "Lkotlin/reflect/m$b;", "Lkotlin/Function0;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface a<V> extends m.b<V>, r7.a<V> {
    }

    Object get();

    Object getDelegate();

    @Override // kotlin.reflect.m
    a getGetter();
}
