package io.github.jan.supabase.postgrest.query;

import db.a;
import db.b;
import db.b0;
import db.m;
import db.n;
import db.y;
import io.github.jan.supabase.SupabaseSerializer;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.reflect.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J8\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b\"\u0006\b\u0001\u0010\t\u0018\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00018\u0001H\u0086\f¢\u0006\u0004\b\r\u0010\u000eJ0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u000fH\u0086\u0004¢\u0006\u0004\b\r\u0010\u0010J0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0011H\u0086\u0004¢\u0006\u0004\b\r\u0010\u0012J0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0013H\u0086\u0004¢\u0006\u0004\b\r\u0010\u0014J0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00150\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0015H\u0086\u0004¢\u0006\u0004\b\r\u0010\u0016J0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00170\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0017H\u0086\u0004¢\u0006\u0004\b\r\u0010\u0018J0\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00190\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0019H\u0086\u0004¢\u0006\u0004\b\r\u0010\u001aJ\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u000fH\u0086\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0011H\u0086\u0002¢\u0006\u0004\b\u001c\u0010\u001eJ\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0013H\u0086\u0002¢\u0006\u0004\b\u001c\u0010\u001fJ\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0015H\u0086\u0002¢\u0006\u0004\b\u001c\u0010 J\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0017H\u0086\u0002¢\u0006\u0004\b\u001c\u0010!J\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0019H\u0086\u0002¢\u0006\u0004\b\u001c\u0010\"J\u0015\u0010#\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000f¢\u0006\u0004\b#\u0010$J*\u0010\u001c\u001a\u00020\f\"\u0006\b\u0000\u0010\b\u0018\u00012\u0006\u0010\u001b\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00018\u0000H\u0086\n¢\u0006\u0004\b\u001c\u0010%J\u000f\u0010'\u001a\u00020&H\u0001¢\u0006\u0004\b'\u0010(R \u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010)\u0012\u0004\b,\u0010-\u001a\u0004\b*\u0010+R \u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010.\u0012\u0004\b1\u0010-\u001a\u0004\b/\u00100R,\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u000203028\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b4\u00105\u0012\u0004\b8\u0010-\u001a\u0004\b6\u00107¨\u00069"}, d2 = {"Lio/github/jan/supabase/postgrest/query/PostgrestUpdate;", "", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "propertyConversionMethod", "Lio/github/jan/supabase/SupabaseSerializer;", "serializer", "<init>", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;Lio/github/jan/supabase/SupabaseSerializer;)V", "T", "V", "Lkotlin/reflect/o;", "value", "Lx6/t0;", "setTo", "(Lkotlin/reflect/o;Ljava/lang/Object;)V", "", "(Lkotlin/reflect/o;Ljava/lang/String;)V", "", "(Lkotlin/reflect/o;Ljava/lang/Integer;)V", "", "(Lkotlin/reflect/o;Ljava/lang/Long;)V", "", "(Lkotlin/reflect/o;Ljava/lang/Float;)V", "", "(Lkotlin/reflect/o;Ljava/lang/Double;)V", "", "(Lkotlin/reflect/o;Ljava/lang/Boolean;)V", "column", "set", "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/Integer;)V", "(Ljava/lang/String;Ljava/lang/Long;)V", "(Ljava/lang/String;Ljava/lang/Float;)V", "(Ljava/lang/String;Ljava/lang/Double;)V", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "setToNull", "(Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/Object;)V", "Ldb/b0;", "toJson", "()Ldb/b0;", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod", "()Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod$annotations", "()V", "Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer", "()Lio/github/jan/supabase/SupabaseSerializer;", "getSerializer$annotations", "", "Ldb/m;", "map", "Ljava/util/Map;", "getMap", "()Ljava/util/Map;", "getMap$annotations", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PostgrestUpdate {
    private final Map<String, m> map = new LinkedHashMap();
    private final PropertyConversionMethod propertyConversionMethod;
    private final SupabaseSerializer serializer;

    public PostgrestUpdate(PropertyConversionMethod propertyConversionMethod, SupabaseSerializer supabaseSerializer) {
        this.propertyConversionMethod = propertyConversionMethod;
        this.serializer = supabaseSerializer;
    }

    public static /* synthetic */ void getMap$annotations() {
    }

    public static /* synthetic */ void getPropertyConversionMethod$annotations() {
    }

    public static /* synthetic */ void getSerializer$annotations() {
    }

    public final Map<String, m> getMap() {
        return this.map;
    }

    public final PropertyConversionMethod getPropertyConversionMethod() {
        return this.propertyConversionMethod;
    }

    public final SupabaseSerializer getSerializer() {
        return this.serializer;
    }

    public final void set(String column, String value) {
        this.map.put(column, n.c(value));
    }

    public final <T, V> void setTo(o<T, ? extends V> oVar, V v2) {
        if (v2 == null) {
            setToNull(getPropertyConversionMethod().invoke(oVar));
            return;
        }
        getPropertyConversionMethod().invoke(oVar);
        getMap();
        getSerializer();
        a aVar = b.f14877d;
        p.h();
        throw null;
    }

    public final void setToNull(String column) {
        this.map.put(column, y.INSTANCE);
    }

    public final b0 toJson() {
        return new b0(this.map);
    }

    public final void set(String column, Integer value) {
        this.map.put(column, n.b(value));
    }

    public final void set(String column, Long value) {
        this.map.put(column, n.b(value));
    }

    public final void set(String column, Float value) {
        this.map.put(column, n.b(value));
    }

    public final void set(String column, Double value) {
        this.map.put(column, n.b(value));
    }

    public final void set(String column, Boolean value) {
        this.map.put(column, n.a(value));
    }

    public final <T> void setTo(o<T, String> oVar, String str) {
        set(this.propertyConversionMethod.invoke(oVar), str);
    }

    public final <T> void set(String column, T value) {
        if (value == null) {
            setToNull(column);
            return;
        }
        getMap();
        getSerializer();
        a aVar = b.f14877d;
        p.h();
        throw null;
    }

    public final <T> void setTo(o<T, Integer> oVar, Integer num) {
        set(this.propertyConversionMethod.invoke(oVar), num);
    }

    public final <T> void setTo(o<T, Long> oVar, Long l10) {
        set(this.propertyConversionMethod.invoke(oVar), l10);
    }

    public final <T> void setTo(o<T, Float> oVar, Float f10) {
        set(this.propertyConversionMethod.invoke(oVar), f10);
    }

    public final <T> void setTo(o<T, Double> oVar, Double d4) {
        set(this.propertyConversionMethod.invoke(oVar), d4);
    }

    public final <T> void setTo(o<T, Boolean> oVar, Boolean bool) {
        set(this.propertyConversionMethod.invoke(oVar), bool);
    }
}
