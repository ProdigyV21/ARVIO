package io.github.jan.supabase.postgrest.query;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Order;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ASCENDING", "DESCENDING", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Order {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ Order[] $VALUES;
    public static final Order ASCENDING = new Order("ASCENDING", 0, "asc");
    public static final Order DESCENDING = new Order("DESCENDING", 1, "desc");
    private final String value;

    private static final /* synthetic */ Order[] $values() {
        return new Order[]{ASCENDING, DESCENDING};
    }

    static {
        Order[] orderArr$values = $values();
        $VALUES = orderArr$values;
        $ENTRIES = new b(orderArr$values);
    }

    private Order(String str, int i10, String str2) {
        this.value = str2;
    }

    public static a<Order> getEntries() {
        return $ENTRIES;
    }

    public static Order valueOf(String str) {
        return (Order) Enum.valueOf(Order.class, str);
    }

    public static Order[] values() {
        return (Order[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
