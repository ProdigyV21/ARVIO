package io.github.jan.supabase.postgrest;

import io.github.jan.supabase.annotations.SupabaseInternal;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.reflect.o;
import ya.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a/\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "V", "Lkotlin/reflect/o;", "property", "", "getSerialName", "(Lkotlin/reflect/o;)Ljava/lang/String;", "postgrest-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class GetColumnNameKt {
    @SupabaseInternal
    public static final <T, V> String getSerialName(o<T, ? extends V> oVar) {
        T next;
        String strValue;
        Iterator<T> it = oVar.getAnnotations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Annotation) next) instanceof q) {
                break;
            }
        }
        q qVar = (q) next;
        return (qVar == null || (strValue = qVar.value()) == null) ? oVar.getName() : strValue;
    }
}
