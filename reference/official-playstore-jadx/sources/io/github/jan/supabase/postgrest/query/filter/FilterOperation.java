package io.github.jan.supabase.postgrest.query.filter;

import androidx.compose.foundation.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lio/github/jan/supabase/postgrest/query/filter/FilterOperation;", "", "column", "", "operator", "Lio/github/jan/supabase/postgrest/query/filter/FilterOperator;", "value", "(Ljava/lang/String;Lio/github/jan/supabase/postgrest/query/filter/FilterOperator;Ljava/lang/String;)V", "getColumn", "()Ljava/lang/String;", "getOperator", "()Lio/github/jan/supabase/postgrest/query/filter/FilterOperator;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FilterOperation {
    private final String column;
    private final FilterOperator operator;
    private final String value;

    public FilterOperation(String str, FilterOperator filterOperator, String str2) {
        this.column = str;
        this.operator = filterOperator;
        this.value = str2;
    }

    public static /* synthetic */ FilterOperation copy$default(FilterOperation filterOperation, String str, FilterOperator filterOperator, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = filterOperation.column;
        }
        if ((i10 & 2) != 0) {
            filterOperator = filterOperation.operator;
        }
        if ((i10 & 4) != 0) {
            str2 = filterOperation.value;
        }
        return filterOperation.copy(str, filterOperator, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getColumn() {
        return this.column;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FilterOperator getOperator() {
        return this.operator;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final FilterOperation copy(String column, FilterOperator operator, String value) {
        return new FilterOperation(column, operator, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterOperation)) {
            return false;
        }
        FilterOperation filterOperation = (FilterOperation) other;
        return p.a(this.column, filterOperation.column) && this.operator == filterOperation.operator && p.a(this.value, filterOperation.value);
    }

    public final String getColumn() {
        return this.column;
    }

    public final FilterOperator getOperator() {
        return this.operator;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + ((this.operator.hashCode() + (this.column.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FilterOperation(column=");
        sb2.append(this.column);
        sb2.append(", operator=");
        sb2.append(this.operator);
        sb2.append(", value=");
        return c.u(sb2, this.value, ')');
    }
}
