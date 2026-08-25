package androidx.compose.runtime.snapshots;

import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a?\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a%\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b\b\u0010\t\u001aE\u0010\f\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\nH\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\f\u0010\r\u001aK\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a?\u0010\u0014\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00120\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a?\u0010\u0016\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00120\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u0016\u0010\u0015\u001aW\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00010\u0019\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0017*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001ai\u0010#\u001a\u00020\"\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u001c2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0002H\u0000¢\u0006\u0004\b#\u0010$\u001a\u007f\u0010)\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\f\b\u0001\u0010'*\u00060%j\u0002`&*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010(\u001a\u00028\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u001c2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0002H\u0002¢\u0006\u0004\b)\u0010*\u001a;\u0010,\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u00060%j\u0002`&2\u0006\u0010+\u001a\u00028\u00002\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0002H\u0002¢\u0006\u0004\b,\u0010-\u001aM\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0002H\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b.\u0010\u0011\u001aZ\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012'\u0010\u0013\u001a#\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00120\nH\u0080\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0004\b2\u00103¨\u00064"}, d2 = {"T", "", "Lkotlin/Function1;", "Lx6/t0;", "action", "fastForEach", "(Ljava/util/List;Lr7/l;)V", "", "fastToSet", "(Ljava/util/List;)Ljava/util/Set;", "Lkotlin/Function2;", "", "fastForEachIndexed", "(Ljava/util/List;Lr7/p;)V", "R", "transform", "fastMap", "(Ljava/util/List;Lr7/l;)Ljava/util/List;", "", "predicate", "fastAny", "(Ljava/util/List;Lr7/l;)Z", "fastAll", "K", "keySelector", "", "fastGroupBy", "(Ljava/util/List;Lr7/l;)Ljava/util/Map;", "", "separator", "prefix", "postfix", "limit", "truncated", "", "fastJoinToString", "(Ljava/util/List;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lr7/l;)Ljava/lang/String;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "buffer", "fastJoinTo", "(Ljava/util/List;Ljava/lang/Appendable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lr7/l;)Ljava/lang/Appendable;", "element", "appendElement", "(Ljava/lang/Appendable;Ljava/lang/Object;Lr7/l;)V", "fastMapNotNull", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "fastFilterIndexed", "(Ljava/util/List;Lr7/p;)Ljava/util/List;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void appendElement(Appendable appendable, T t2, l<? super T, ? extends CharSequence> lVar) throws IOException {
        if (lVar != null) {
            appendable.append((CharSequence) lVar.invoke(t2));
            return;
        }
        if (t2 == 0 ? true : t2 instanceof CharSequence) {
            appendable.append((CharSequence) t2);
        } else if (t2 instanceof Character) {
            appendable.append(((Character) t2).charValue());
        } else {
            appendable.append(String.valueOf(t2));
        }
    }

    public static final <T> boolean fastAll(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (!((Boolean) lVar.invoke(list.get(i10))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean fastAny(List<? extends T> list, l<? super T, Boolean> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((Boolean) lVar.invoke(list.get(i10))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final <T> List<T> fastFilterIndexed(List<? extends T> list, p<? super Integer, ? super T, Boolean> pVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            if (((Boolean) pVar.invoke(Integer.valueOf(i10), t2)).booleanValue()) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static final <T> void fastForEach(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            lVar.invoke(list.get(i10));
        }
    }

    public static final <T> void fastForEachIndexed(List<? extends T> list, p<? super Integer, ? super T, t0> pVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            pVar.invoke(Integer.valueOf(i10), list.get(i10));
        }
    }

    public static final <T, K> Map<K, List<T>> fastGroupBy(List<? extends T> list, l<? super T, ? extends K> lVar) {
        HashMap map = new HashMap(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            T t2 = list.get(i10);
            Object objInvoke = lVar.invoke(t2);
            Object arrayList = map.get(objInvoke);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(objInvoke, arrayList);
            }
            ((ArrayList) arrayList).add(t2);
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, A extends Appendable> A fastJoinTo(List<? extends T> list, A a10, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) throws IOException {
        a10.append(charSequence2);
        int size = list.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            T t2 = list.get(i12);
            i11++;
            if (i11 > 1) {
                a10.append(charSequence);
            }
            if (i10 >= 0 && i11 > i10) {
                break;
            }
            appendElement(a10, t2, lVar);
        }
        if (i10 >= 0 && i11 > i10) {
            a10.append(charSequence4);
        }
        a10.append(charSequence3);
        return a10;
    }

    public static final <T> String fastJoinToString(List<? extends T> list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) {
        return ((StringBuilder) fastJoinTo(list, new StringBuilder(), charSequence, charSequence2, charSequence3, i10, charSequence4, lVar)).toString();
    }

    public static /* synthetic */ String fastJoinToString$default(List list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i10, CharSequence charSequence4, l lVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i11 & 2) != 0) {
            charSequence2 = "";
        }
        if ((i11 & 4) != 0) {
            charSequence3 = "";
        }
        if ((i11 & 8) != 0) {
            i10 = -1;
        }
        if ((i11 & 16) != 0) {
            charSequence4 = "...";
        }
        if ((i11 & 32) != 0) {
            lVar = null;
        }
        CharSequence charSequence5 = charSequence4;
        l lVar2 = lVar;
        return fastJoinToString(list, charSequence, charSequence2, charSequence3, i10, charSequence5, lVar2);
    }

    public static final <T, R> List<R> fastMap(List<? extends T> list, l<? super T, ? extends R> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(lVar.invoke(list.get(i10)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapNotNull(List<? extends T> list, l<? super T, ? extends R> lVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object objInvoke = lVar.invoke(list.get(i10));
            if (objInvoke != null) {
                arrayList.add(objInvoke);
            }
        }
        return arrayList;
    }

    public static final <T> Set<T> fastToSet(List<? extends T> list) {
        HashSet hashSet = new HashSet(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            hashSet.add(list.get(i10));
        }
        return hashSet;
    }
}
