package androidx.credentials.provider.utils;

import android.service.credentials.Action;
import androidx.credentials.d;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/Action;", "entry", "Landroid/service/credentials/Action;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BeginGetCredentialUtil$Companion$convertToJetpackResponse$4 extends r implements l<Action, androidx.credentials.provider.Action> {
    public static final BeginGetCredentialUtil$Companion$convertToJetpackResponse$4 INSTANCE = new BeginGetCredentialUtil$Companion$convertToJetpackResponse$4();

    public BeginGetCredentialUtil$Companion$convertToJetpackResponse$4() {
        super(1);
    }

    public final androidx.credentials.provider.Action invoke(Action action) {
        return androidx.credentials.provider.Action.INSTANCE.fromSlice(action.getSlice());
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(d.c(obj));
    }
}
