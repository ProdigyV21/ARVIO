package androidx.compose.runtime.saveable;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u0084\u0001\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\r\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u000122\u0010\t\u001a.\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0002¢\u0006\u0002\b\b2)\u0010\f\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00018\u00000\n¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Original", "Saveable", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lx6/y;", ContentDisposition.Parameters.Name, "value", "", "Lx6/n;", "save", "Lkotlin/Function1;", "list", "restore", "Landroidx/compose/runtime/saveable/Saver;", "", "listSaver", "(Lr7/p;Lr7/l;)Landroidx/compose/runtime/saveable/Saver;", "runtime-saveable_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListSaverKt {

    /* JADX INFO: Add missing generic type declarations: [Original] */
    /* JADX INFO: renamed from: androidx.compose.runtime.saveable.ListSaverKt$listSaver$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "Original", "Saveable", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "invoke", "(Landroidx/compose/runtime/saveable/SaverScope;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<Original> extends r implements p<SaverScope, Original, Object> {
        final /* synthetic */ p<SaverScope, Original, List<Saveable>> $save;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super SaverScope, ? super Original, ? extends List<? extends Saveable>> pVar) {
            super(2);
            this.$save = pVar;
        }

        @Override // r7.p
        public final Object invoke(SaverScope saverScope, Original original) {
            List list = (List) this.$save.invoke(saverScope, original);
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                Object obj = list.get(i10);
                if (obj != null && !saverScope.canBeSaved(obj)) {
                    throw new IllegalArgumentException("item can't be saved");
                }
            }
            if (list.isEmpty()) {
                return null;
            }
            return new ArrayList(list);
        }
    }

    public static final <Original, Saveable> Saver<Original, Object> listSaver(p<? super SaverScope, ? super Original, ? extends List<? extends Saveable>> pVar, l<? super List<? extends Saveable>, ? extends Original> lVar) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(pVar);
        q0.e(1, lVar);
        return SaverKt.Saver(anonymousClass1, lVar);
    }
}
