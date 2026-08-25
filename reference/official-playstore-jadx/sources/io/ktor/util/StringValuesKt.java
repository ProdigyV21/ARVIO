package io.ktor.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.s;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u001aO\u0010\b\u001a\u00020\u000726\u0010\u0004\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00010\u0000\"\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\f\u001a-\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00022\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u000e\u001a\r\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\u000f\u001a1\u0010\b\u001a\u00020\u00072\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00110\u00102\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0013\u001a#\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0010*\u00020\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00010\u0003*\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a+\u0010\u001b\u001a\u00020\u0019*\u00020\u00072\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0004\b\u001b\u0010\u001c\u001a5\u0010\u001f\u001a\u00020\u0007*\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00052\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\u0004\b\u001f\u0010 \u001a=\u0010#\u001a\u00020\u0019*\u00020!2\u0006\u0010\"\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00052\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\u0004\b#\u0010$\u001a\u0019\u0010&\u001a\u00020!*\u00020!2\u0006\u0010%\u001a\u00020!¢\u0006\u0004\b&\u0010'\u001a!\u0010(\u001a\u00020!*\u00020!2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b(\u0010)\u001a!\u0010*\u001a\u00020!*\u00020!2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b*\u0010)\u001aO\u0010/\u001a\u00020\u00052\u001e\u0010-\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030,0+2\u001e\u0010.\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030,0+H\u0002¢\u0006\u0004\b/\u00100\u001a7\u00104\u001a\u0002022\u001e\u00101\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030,0+2\u0006\u00103\u001a\u000202H\u0002¢\u0006\u0004\b4\u00105¨\u00066"}, d2 = {"", "Lx6/x;", "", "", "pairs", "", "caseInsensitiveKey", "Lio/ktor/util/StringValues;", "valuesOf", "([Lx6/x;Z)Lio/ktor/util/StringValues;", ContentDisposition.Parameters.Name, "value", "(Ljava/lang/String;Ljava/lang/String;Z)Lio/ktor/util/StringValues;", "values", "(Ljava/lang/String;Ljava/util/List;Z)Lio/ktor/util/StringValues;", "()Lio/ktor/util/StringValues;", "", "", "map", "(Ljava/util/Map;Z)Lio/ktor/util/StringValues;", "toMap", "(Lio/ktor/util/StringValues;)Ljava/util/Map;", "flattenEntries", "(Lio/ktor/util/StringValues;)Ljava/util/List;", "Lkotlin/Function2;", "Lx6/t0;", "block", "flattenForEach", "(Lio/ktor/util/StringValues;Lr7/p;)V", "keepEmpty", "predicate", "filter", "(Lio/ktor/util/StringValues;ZLr7/p;)Lio/ktor/util/StringValues;", "Lio/ktor/util/StringValuesBuilder;", "source", "appendFiltered", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValues;ZLr7/p;)V", "builder", "appendAll", "(Lio/ktor/util/StringValuesBuilder;Lio/ktor/util/StringValuesBuilder;)Lio/ktor/util/StringValuesBuilder;", "appendIfNameAbsent", "(Lio/ktor/util/StringValuesBuilder;Ljava/lang/String;Ljava/lang/String;)Lio/ktor/util/StringValuesBuilder;", "appendIfNameAndValueAbsent", "", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "entriesEquals", "(Ljava/util/Set;Ljava/util/Set;)Z", "entries", "", "seed", "entriesHashCode", "(Ljava/util/Set;I)I", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringValuesKt {

    /* JADX INFO: renamed from: io.ktor.util.StringValuesKt$appendFiltered$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", ContentDisposition.Parameters.Name, "", "value", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<String, List<? extends String>, t0> {
        final /* synthetic */ boolean $keepEmpty;
        final /* synthetic */ p<String, String, Boolean> $predicate;
        final /* synthetic */ StringValuesBuilder $this_appendFiltered;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(boolean z, StringValuesBuilder stringValuesBuilder, p<? super String, ? super String, Boolean> pVar) {
            super(2);
            this.$keepEmpty = z;
            this.$this_appendFiltered = stringValuesBuilder;
            this.$predicate = pVar;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (List<String>) obj2);
            return t0.f22605a;
        }

        public final void invoke(String str, List<String> list) {
            ArrayList arrayList = new ArrayList(list.size());
            p<String, String, Boolean> pVar = this.$predicate;
            for (Object obj : list) {
                if (((Boolean) pVar.invoke(str, (String) obj)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            if (this.$keepEmpty || !arrayList.isEmpty()) {
                this.$this_appendFiltered.appendAll(str, arrayList);
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.StringValuesKt$flattenForEach$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", ContentDisposition.Parameters.Name, "", "items", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19491 extends r implements p<String, List<? extends String>, t0> {
        final /* synthetic */ p<String, String, t0> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19491(p<? super String, ? super String, t0> pVar) {
            super(2);
            this.$block = pVar;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (List<String>) obj2);
            return t0.f22605a;
        }

        public final void invoke(String str, List<String> list) {
            p<String, String, t0> pVar = this.$block;
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                pVar.invoke(str, (String) it.next());
            }
        }
    }

    public static final StringValuesBuilder appendAll(StringValuesBuilder stringValuesBuilder, StringValuesBuilder stringValuesBuilder2) {
        Iterator<T> it = stringValuesBuilder2.entries().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            stringValuesBuilder.appendAll((String) entry.getKey(), (List) entry.getValue());
        }
        return stringValuesBuilder;
    }

    public static final void appendFiltered(StringValuesBuilder stringValuesBuilder, StringValues stringValues, boolean z, p<? super String, ? super String, Boolean> pVar) {
        stringValues.forEach(new AnonymousClass1(z, stringValuesBuilder, pVar));
    }

    public static /* synthetic */ void appendFiltered$default(StringValuesBuilder stringValuesBuilder, StringValues stringValues, boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        appendFiltered(stringValuesBuilder, stringValues, z, pVar);
    }

    public static final StringValuesBuilder appendIfNameAbsent(StringValuesBuilder stringValuesBuilder, String str, String str2) {
        if (!stringValuesBuilder.contains(str)) {
            stringValuesBuilder.append(str, str2);
        }
        return stringValuesBuilder;
    }

    public static final StringValuesBuilder appendIfNameAndValueAbsent(StringValuesBuilder stringValuesBuilder, String str, String str2) {
        if (!stringValuesBuilder.contains(str, str2)) {
            stringValuesBuilder.append(str, str2);
        }
        return stringValuesBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean entriesEquals(Set<? extends Map.Entry<String, ? extends List<String>>> set, Set<? extends Map.Entry<String, ? extends List<String>>> set2) {
        return kotlin.jvm.internal.p.a(set, set2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int entriesHashCode(Set<? extends Map.Entry<String, ? extends List<String>>> set, int i10) {
        return set.hashCode() + (i10 * 31);
    }

    public static final StringValues filter(StringValues stringValues, boolean z, p<? super String, ? super String, Boolean> pVar) {
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        Map mapCaseInsensitiveMap = stringValues.getCaseInsensitiveName() ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(setEntries.size());
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(((List) entry.getValue()).size());
            for (Object obj : iterable) {
                if (((Boolean) pVar.invoke(entry.getKey(), (String) obj)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            if (z || !arrayList.isEmpty()) {
                mapCaseInsensitiveMap.put(entry.getKey(), arrayList);
            }
        }
        return new StringValuesImpl(stringValues.getCaseInsensitiveName(), mapCaseInsensitiveMap);
    }

    public static /* synthetic */ StringValues filter$default(StringValues stringValues, boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return filter(stringValues, z, pVar);
    }

    public static final List<x> flattenEntries(StringValues stringValues) {
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList2 = new ArrayList(s.U(iterable, 10));
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new x(entry.getKey(), (String) it2.next()));
            }
            kotlin.collections.x.b0(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static final void flattenForEach(StringValues stringValues, p<? super String, ? super String, t0> pVar) {
        stringValues.forEach(new C19491(pVar));
    }

    public static final Map<String, List<String>> toMap(StringValues stringValues) {
        Set<Map.Entry<String, List<String>>> setEntries = stringValues.entries();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = setEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put((String) entry.getKey(), kotlin.collections.x.c1((Iterable) entry.getValue()));
        }
        return linkedHashMap;
    }

    public static final StringValues valuesOf(x[] xVarArr, boolean z) {
        return new StringValuesImpl(z, h0.A0(Arrays.asList(xVarArr)));
    }

    public static /* synthetic */ StringValues valuesOf$default(x[] xVarArr, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return valuesOf(xVarArr, z);
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, String str2, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        return valuesOf(str, str2, z);
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, List list, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        return valuesOf(str, (List<String>) list, z);
    }

    public static final StringValues valuesOf(String str, String str2, boolean z) {
        return new StringValuesSingleImpl(z, str, Collections.singletonList(str2));
    }

    public static /* synthetic */ StringValues valuesOf$default(Map map, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return valuesOf((Map<String, ? extends Iterable<String>>) map, z);
    }

    public static final StringValues valuesOf(String str, List<String> list, boolean z) {
        return new StringValuesSingleImpl(z, str, list);
    }

    public static final StringValues valuesOf() {
        return StringValues.INSTANCE.getEmpty();
    }

    public static final StringValues valuesOf(Map<String, ? extends Iterable<String>> map, boolean z) {
        int size = map.size();
        if (size == 1) {
            Map.Entry entry = (Map.Entry) kotlin.collections.x.Q0(map.entrySet());
            return new StringValuesSingleImpl(z, (String) entry.getKey(), kotlin.collections.x.c1((Iterable) entry.getValue()));
        }
        Map mapCaseInsensitiveMap = z ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(size);
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            mapCaseInsensitiveMap.put(entry2.getKey(), kotlin.collections.x.c1((Iterable) entry2.getValue()));
        }
        return new StringValuesImpl(z, mapCaseInsensitiveMap);
    }
}
