package androidx.compose.runtime.saveable;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u007f\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\r\"\u0004\b\u0000\u0010\u00002:\u0010\n\u001a6\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00060\u0001¢\u0006\u0002\b\t2\"\u0010\f\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"T", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lx6/y;", ContentDisposition.Parameters.Name, "value", "", "", "", "Lx6/n;", "save", "Lkotlin/Function1;", "restore", "Landroidx/compose/runtime/saveable/Saver;", "mapSaver", "(Lr7/p;Lr7/l;)Landroidx/compose/runtime/saveable/Saver;", "runtime-saveable_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MapSaverKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.saveable.MapSaverKt$mapSaver$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "", "T", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "invoke", "(Landroidx/compose/runtime/saveable/SaverScope;Ljava/lang/Object;)Ljava/util/List;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<T> extends r implements p<SaverScope, T, List<? extends Object>> {
        final /* synthetic */ p<SaverScope, T, Map<String, Object>> $save;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super SaverScope, ? super T, ? extends Map<String, ? extends Object>> pVar) {
            super(2);
            this.$save = pVar;
        }

        @Override // r7.p
        public final List<Object> invoke(SaverScope saverScope, T t2) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : ((Map) this.$save.invoke(saverScope, t2)).entrySet()) {
                arrayList.add(entry.getKey());
                arrayList.add(entry.getValue());
            }
            return arrayList;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.saveable.MapSaverKt$mapSaver$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "T", "list", "", "", "invoke", "(Ljava/util/List;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2<T> extends r implements l<List<? extends Object>, T> {
        final /* synthetic */ l<Map<String, ? extends Object>, T> $restore;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super Map<String, ? extends Object>, ? extends T> lVar) {
            super(1);
            this.$restore = lVar;
        }

        @Override // r7.l
        public final T invoke(List<? extends Object> list) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (list.size() % 2 != 0) {
                throw new IllegalStateException("non-zero remainder");
            }
            for (int i10 = 0; i10 < list.size(); i10 += 2) {
                linkedHashMap.put((String) list.get(i10), list.get(i10 + 1));
            }
            return (T) this.$restore.invoke(linkedHashMap);
        }
    }

    public static final <T> Saver<T, Object> mapSaver(p<? super SaverScope, ? super T, ? extends Map<String, ? extends Object>> pVar, l<? super Map<String, ? extends Object>, ? extends T> lVar) {
        return ListSaverKt.listSaver(new AnonymousClass1(pVar), new AnonymousClass2(lVar));
    }
}
