package a7;

import androidx.compose.foundation.text.selection.SelectionRegistrarImpl;
import androidx.credentials.webauthn.Cbor;
import com.arflix.tv.ui.components.StreamSelectorKt;
import com.arflix.tv.ui.components.f3;
import com.google.common.util.concurrent.r0;
import java.util.Comparator;
import kotlin.jvm.internal.k0;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class a implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f126i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f127l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f126i = i10;
        this.f127l = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f126i) {
            case 0:
                for (l lVar : (l[]) this.f127l) {
                    int iE = r0.e((Comparable) lVar.invoke(obj), (Comparable) lVar.invoke(obj2));
                    if (iE != 0) {
                        return iE;
                    }
                }
                return 0;
            case 1:
                return SelectionRegistrarImpl.sort$lambda$2((p) this.f127l, obj, obj2);
            case 2:
                return StreamSelectorKt.StreamSelector$lambda$33$6((f3) this.f127l, obj, obj2);
            default:
                return Cbor.encode$lambda$0((k0) this.f127l, (byte[]) obj, (byte[]) obj2);
        }
    }
}
