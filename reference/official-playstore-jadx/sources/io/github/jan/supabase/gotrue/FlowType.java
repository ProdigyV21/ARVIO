package io.github.jan.supabase.gotrue;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lio/github/jan/supabase/gotrue/FlowType;", "", "(Ljava/lang/String;I)V", "IMPLICIT", "PKCE", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FlowType {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ FlowType[] $VALUES;
    public static final FlowType IMPLICIT = new FlowType("IMPLICIT", 0);
    public static final FlowType PKCE = new FlowType("PKCE", 1);

    private static final /* synthetic */ FlowType[] $values() {
        return new FlowType[]{IMPLICIT, PKCE};
    }

    static {
        FlowType[] flowTypeArr$values = $values();
        $VALUES = flowTypeArr$values;
        $ENTRIES = new b(flowTypeArr$values);
    }

    private FlowType(String str, int i10) {
    }

    public static a<FlowType> getEntries() {
        return $ENTRIES;
    }

    public static FlowType valueOf(String str) {
        return (FlowType) Enum.valueOf(FlowType.class, str);
    }

    public static FlowType[] values() {
        return (FlowType[]) $VALUES.clone();
    }
}
