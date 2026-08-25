package dagger.hilt.android.lifecycle;

import androidx.lifecycle.d1;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import kotlin.Metadata;
import o1.c;
import o1.d;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0005\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a+\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"VMF", "Lo1/c;", "Lkotlin/Function1;", "Landroidx/lifecycle/d1;", "callback", "withCreationCallback", "(Lo1/c;Lr7/l;)Lo1/c;", "Lo1/d;", "addCreationCallback", "(Lo1/d;Lr7/l;)Lo1/c;", "hilt-android_main_java_dagger_hilt_android_lifecycle-hilt_view_model_extensions_internal_kt"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class HiltViewModelExtensions {
    public static final <VMF> c addCreationCallback(d dVar, l<? super VMF, ? extends d1> lVar) {
        dVar.b(HiltViewModelFactory.CREATION_CALLBACK_KEY, new HiltViewModelExtensions$addCreationCallback$1$1(lVar));
        return dVar;
    }

    public static final <VMF> c withCreationCallback(c cVar, l<? super VMF, ? extends d1> lVar) {
        return addCreationCallback(new d(cVar), lVar);
    }
}
