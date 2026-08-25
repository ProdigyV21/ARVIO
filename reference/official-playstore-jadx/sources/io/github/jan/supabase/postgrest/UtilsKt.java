package io.github.jan.supabase.postgrest;

import io.github.jan.supabase.annotations.SupabaseInternal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0001¢\u0006\u0004\b\u0001\u0010\u0002\u001a7\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0003*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004H\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\"\u0006\b\u0000\u0010\u0003\u0018\u0001H\u0087\b¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"", "camelToSnakeCase", "(Ljava/lang/String;)Ljava/lang/String;", "T", "", "", "mapToFirstValue", "(Ljava/util/Map;)Ljava/util/Map;", "classPropertyNames", "()Ljava/util/List;", "Lkotlin/text/m;", "SNAKE_CASE_REGEX", "Lkotlin/text/m;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt {
    private static final m SNAKE_CASE_REGEX = new m("(?<=.)[A-Z]");

    @SupabaseInternal
    public static final String camelToSnakeCase(String str) {
        return SNAKE_CASE_REGEX.f("_$0", str).toLowerCase(Locale.ROOT);
    }

    @SupabaseInternal
    public static final <T> List<String> classPropertyNames() {
        p.h();
        throw null;
    }

    public static final <T> Map<T, T> mapToFirstValue(Map<T, ? extends List<? extends T>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), x.m0((List) entry.getValue()));
        }
        return linkedHashMap;
    }
}
