package androidx.compose.ui.text;

import androidx.compose.ui.text.intl.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/intl/Locale;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SaversKt$LocaleSaver$2 extends r implements l<Object, Locale> {
    public static final SaversKt$LocaleSaver$2 INSTANCE = new SaversKt$LocaleSaver$2();

    public SaversKt$LocaleSaver$2() {
        super(1);
    }

    @Override // r7.l
    public final Locale invoke(Object obj) {
        return new Locale((String) obj);
    }
}
