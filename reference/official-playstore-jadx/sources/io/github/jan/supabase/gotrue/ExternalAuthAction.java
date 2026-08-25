package io.github.jan.supabase.gotrue;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lio/github/jan/supabase/gotrue/ExternalAuthAction;", "", "(Ljava/lang/String;I)V", "EXTERNAL_BROWSER", "CUSTOM_TABS", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ExternalAuthAction {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ ExternalAuthAction[] $VALUES;
    public static final ExternalAuthAction EXTERNAL_BROWSER = new ExternalAuthAction("EXTERNAL_BROWSER", 0);
    public static final ExternalAuthAction CUSTOM_TABS = new ExternalAuthAction("CUSTOM_TABS", 1);

    private static final /* synthetic */ ExternalAuthAction[] $values() {
        return new ExternalAuthAction[]{EXTERNAL_BROWSER, CUSTOM_TABS};
    }

    static {
        ExternalAuthAction[] externalAuthActionArr$values = $values();
        $VALUES = externalAuthActionArr$values;
        $ENTRIES = new b(externalAuthActionArr$values);
    }

    private ExternalAuthAction(String str, int i10) {
    }

    public static a<ExternalAuthAction> getEntries() {
        return $ENTRIES;
    }

    public static ExternalAuthAction valueOf(String str) {
        return (ExternalAuthAction) Enum.valueOf(ExternalAuthAction.class, str);
    }

    public static ExternalAuthAction[] values() {
        return (ExternalAuthAction[]) $VALUES.clone();
    }
}
