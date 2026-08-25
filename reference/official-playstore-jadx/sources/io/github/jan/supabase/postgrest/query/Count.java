package io.github.jan.supabase.postgrest.query;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Count;", "", "identifier", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getIdentifier", "()Ljava/lang/String;", "EXACT", "PLANNED", "ESTIMATED", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Count {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ Count[] $VALUES;
    private final String identifier;
    public static final Count EXACT = new Count("EXACT", 0, "exact");
    public static final Count PLANNED = new Count("PLANNED", 1, "planned");
    public static final Count ESTIMATED = new Count("ESTIMATED", 2, "estimated");

    private static final /* synthetic */ Count[] $values() {
        return new Count[]{EXACT, PLANNED, ESTIMATED};
    }

    static {
        Count[] countArr$values = $values();
        $VALUES = countArr$values;
        $ENTRIES = new b(countArr$values);
    }

    private Count(String str, int i10, String str2) {
        this.identifier = str2;
    }

    public static a<Count> getEntries() {
        return $ENTRIES;
    }

    public static Count valueOf(String str) {
        return (Count) Enum.valueOf(Count.class, str);
    }

    public static Count[] values() {
        return (Count[]) $VALUES.clone();
    }

    public final String getIdentifier() {
        return this.identifier;
    }
}
