package androidx.compose.ui.text.android;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a?\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a_\u0010\f\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0007\"\u0010\b\u0002\u0010\t*\n\u0012\u0006\b\u0000\u0012\u00028\u00010\b*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\n\u001a\u00028\u00022\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b\f\u0010\r\u001aQ\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000eH\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"T", "", "Lkotlin/Function1;", "Lx6/t0;", "action", "fastForEach", "(Ljava/util/List;Lr7/l;)V", "R", "", "C", "destination", "transform", "fastMapTo", "(Ljava/util/List;Ljava/util/Collection;Lr7/l;)Ljava/util/Collection;", "Lkotlin/Function2;", "fastZipWithNext", "(Ljava/util/List;Lr7/p;)Ljava/util/List;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListUtilsKt {
    public static final <T> void fastForEach(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            lVar.invoke(list.get(i10));
        }
    }

    public static final <T, R, C extends Collection<? super R>> C fastMapTo(List<? extends T> list, C c10, l<? super T, ? extends R> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            c10.add(lVar.invoke(list.get(i10)));
        }
        return c10;
    }

    public static final <T, R> List<R> fastZipWithNext(List<? extends T> list, p<? super T, ? super T, ? extends R> pVar) {
        if (list.size() == 0 || list.size() == 1) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        T t2 = list.get(0);
        int iW = t7.a.w(list);
        while (i10 < iW) {
            i10++;
            T t10 = list.get(i10);
            arrayList.add(pVar.invoke(t2, t10));
            t2 = t10;
        }
        return arrayList;
    }
}
