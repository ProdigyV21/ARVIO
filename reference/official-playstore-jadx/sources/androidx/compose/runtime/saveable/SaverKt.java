package androidx.compose.runtime.saveable;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a~\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00012.\u0010\t\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0003¢\u0006\u0002\b\b2#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00000\n¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\f\"\u0004\b\u0000\u0010\u000f¢\u0006\u0004\b\u0010\u0010\u0011\"\"\u0010\u0012\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00010\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Original", "", "Saveable", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lx6/y;", ContentDisposition.Parameters.Name, "value", "Lx6/n;", "save", "Lkotlin/Function1;", "restore", "Landroidx/compose/runtime/saveable/Saver;", "Saver", "(Lr7/p;Lr7/l;)Landroidx/compose/runtime/saveable/Saver;", "T", "autoSaver", "()Landroidx/compose/runtime/saveable/Saver;", "AutoSaver", "Landroidx/compose/runtime/saveable/Saver;", "runtime-saveable_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SaverKt {
    private static final Saver<Object, Object> AutoSaver = Saver(SaverKt$AutoSaver$1.INSTANCE, SaverKt$AutoSaver$2.INSTANCE);

    public static final <Original, Saveable> Saver<Original, Saveable> Saver(final p<? super SaverScope, ? super Original, ? extends Saveable> pVar, final l<? super Saveable, ? extends Original> lVar) {
        return new Saver<Original, Saveable>() { // from class: androidx.compose.runtime.saveable.SaverKt.Saver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return (Original) lVar.invoke(value);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope saverScope, Original original) {
                return (Saveable) pVar.invoke(saverScope, original);
            }
        };
    }

    public static final <T> Saver<T, Object> autoSaver() {
        return (Saver<T, Object>) AutoSaver;
    }
}
