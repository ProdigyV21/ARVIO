package io.github.jan.supabase.postgrest.query.filter;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import io.github.jan.supabase.gotrue.PostgrestFilterDSL;
import io.github.jan.supabase.postgrest.PropertyConversionMethod;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.reflect.o;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010$\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u000f\u0010\u0013J'\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0014\u0010\u0010J\u0015\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0014\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u0017\u0010\u0016J\u001d\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u0018\u0010\u0016J\u001d\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u0019\u0010\u0016J\u001d\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u001a\u0010\u0016J\u001d\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u001b\u0010\u0016J\u001d\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b\u001d\u0010\u001eJ#\u0010 \u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0004\b \u0010!J#\u0010\"\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0004\b\"\u0010!J#\u0010#\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0004\b#\u0010!J#\u0010$\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0004\b$\u0010!J\u001d\u0010%\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b%\u0010\u001eJ\u001d\u0010&\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b&\u0010\u001eJ\u001d\u0010'\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005¢\u0006\u0004\b'\u0010\u001eJ\u001f\u0010)\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010(¢\u0006\u0004\b)\u0010*J#\u0010,\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\b,\u0010!J)\u0010/\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b/\u00100J)\u00101\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b1\u00100J)\u00102\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b2\u00100J)\u00103\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b3\u00100J)\u00104\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b4\u00100J)\u00105\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b5\u00100J)\u00106\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b6\u00100J)\u00107\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b7\u00100J)\u00108\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-¢\u0006\u0004\b8\u00100J;\u0010=\u001a\u00020\u000e2\b\b\u0002\u00109\u001a\u00020(2\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0:¢\u0006\u0002\b;¢\u0006\u0002\b<H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010>J;\u0010?\u001a\u00020\u000e2\b\b\u0002\u00109\u001a\u00020(2\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e0:¢\u0006\u0002\b;¢\u0006\u0002\b<H\u0087\bø\u0001\u0000¢\u0006\u0004\b?\u0010>J1\u0010D\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u00052\u0006\u0010B\u001a\u00020A2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\bD\u0010EJ#\u0010F\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bF\u0010!J#\u0010G\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bG\u0010!J#\u0010H\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bH\u0010!J#\u0010I\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bI\u0010!J#\u0010J\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bJ\u0010!J#\u0010K\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\bK\u0010!J4\u0010\u0015\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u0015\u0010OJ4\u0010\u0017\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u0017\u0010OJ4\u0010\u0018\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u0018\u0010OJ4\u0010\u0019\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u0019\u0010OJ4\u0010\u001b\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u001b\u0010OJ4\u0010\u001a\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\r\u001a\u00028\u0001H\u0086\u0004¢\u0006\u0004\b\u001a\u0010OJ4\u0010\u001d\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\u001c\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b\u001d\u0010PJ4\u0010&\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\u001c\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b&\u0010PJ4\u0010%\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\u001c\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b%\u0010PJ4\u0010'\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0006\u0010\u001c\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b'\u0010PJ6\u0010Q\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\b\u0010\r\u001a\u0004\u0018\u00010(H\u0086\u0004¢\u0006\u0004\bQ\u0010RJ:\u0010,\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\f\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0086\u0004¢\u0006\u0004\b,\u0010TJ@\u00106\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-H\u0086\u0004¢\u0006\u0004\b6\u0010UJ@\u00104\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-H\u0086\u0004¢\u0006\u0004\b4\u0010UJ@\u00107\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-H\u0086\u0004¢\u0006\u0004\b7\u0010UJ@\u00105\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-H\u0086\u0004¢\u0006\u0004\b5\u0010UJ@\u00108\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010-H\u0086\u0004¢\u0006\u0004\b8\u0010UJ:\u0010K\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\u0004¢\u0006\u0004\bK\u0010TJ:\u0010F\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\u0004¢\u0006\u0004\bF\u0010TJ:\u0010G\u001a\u00020\u000e\"\u0004\b\u0000\u0010L\"\u0004\b\u0001\u0010M*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010N2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\u0004¢\u0006\u0004\bG\u0010TR \u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010V\u0012\u0004\bY\u0010Z\u001a\u0004\bW\u0010XR2\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00048\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010[\u0012\u0004\b^\u0010Z\u001a\u0004\b\\\u0010]R#\u0010a\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060_8F¢\u0006\u0006\u001a\u0004\b`\u0010]\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006b"}, d2 = {"Lio/github/jan/supabase/postgrest/query/filter/PostgrestFilterBuilder;", "", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "propertyConversionMethod", "", "", "", "_params", "<init>", "(Lio/github/jan/supabase/postgrest/PropertyConversionMethod;Ljava/util/Map;)V", "column", "Lio/github/jan/supabase/postgrest/query/filter/FilterOperator;", "operator", "value", "Lx6/t0;", "filterNot", "(Ljava/lang/String;Lio/github/jan/supabase/postgrest/query/filter/FilterOperator;Ljava/lang/Object;)V", "Lio/github/jan/supabase/postgrest/query/filter/FilterOperation;", "operation", "(Lio/github/jan/supabase/postgrest/query/filter/FilterOperation;)V", "filter", "eq", "(Ljava/lang/String;Ljava/lang/Object;)V", "neq", "gt", "gte", "lte", "lt", "pattern", "like", "(Ljava/lang/String;Ljava/lang/String;)V", "patterns", "likeAll", "(Ljava/lang/String;Ljava/util/List;)V", "likeAny", "ilikeAll", "ilikeAny", "ilike", "match", "imatch", "", "exact", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "values", "isIn", "Lx6/x;", "range", "sl", "(Ljava/lang/String;Lx6/x;)V", "sr", "nxl", "nxr", "rangeLte", "rangeGte", "rangeLt", "rangeGt", "adjacent", "negate", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/PostgrestFilterDSL;", "Lx6/n;", "or", "(ZLr7/l;)V", "and", "query", "Lio/github/jan/supabase/postgrest/query/filter/TextSearchType;", "textSearchType", "config", "textSearch", "(Ljava/lang/String;Ljava/lang/String;Lio/github/jan/supabase/postgrest/query/filter/TextSearchType;Ljava/lang/String;)Lio/github/jan/supabase/postgrest/query/filter/PostgrestFilterBuilder;", "contains", "contained", "cs", "cd", "ov", "overlaps", "T", "V", "Lkotlin/reflect/o;", "(Lkotlin/reflect/o;Ljava/lang/Object;)V", "(Lkotlin/reflect/o;Ljava/lang/String;)V", "isExact", "(Lkotlin/reflect/o;Ljava/lang/Boolean;)V", "list", "(Lkotlin/reflect/o;Ljava/util/List;)V", "(Lkotlin/reflect/o;Lx6/x;)V", "Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod", "()Lio/github/jan/supabase/postgrest/PropertyConversionMethod;", "getPropertyConversionMethod$annotations", "()V", "Ljava/util/Map;", "get_params", "()Ljava/util/Map;", "get_params$annotations", "", "getParams", "params", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@PostgrestFilterDSL
public final class PostgrestFilterBuilder {
    private final Map<String, List<String>> _params;
    private final PropertyConversionMethod propertyConversionMethod;

    public PostgrestFilterBuilder(PropertyConversionMethod propertyConversionMethod, Map<String, List<String>> map) {
        this.propertyConversionMethod = propertyConversionMethod;
        this._params = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void and$default(PostgrestFilterBuilder postgrestFilterBuilder, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        String str = z ? "not." : "";
        Map<String, List<String>> map = postgrestFilterBuilder.get_params();
        String strConcat = str.concat("and");
        PostgrestFilterBuilder postgrestFilterBuilder2 = new PostgrestFilterBuilder(postgrestFilterBuilder.getPropertyConversionMethod(), null, 2, 0 == true ? 1 : 0);
        lVar.invoke(postgrestFilterBuilder2);
        map.put(strConcat, Collections.singletonList("(" + x.u0(h0.z0(postgrestFilterBuilder2.getParams()), ",", null, null, PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1.INSTANCE, 30) + ')'));
    }

    public static /* synthetic */ void getPropertyConversionMethod$annotations() {
    }

    public static /* synthetic */ void get_params$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void or$default(PostgrestFilterBuilder postgrestFilterBuilder, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        String str = z ? "not." : "";
        Map<String, List<String>> map = postgrestFilterBuilder.get_params();
        String strConcat = str.concat("or");
        PostgrestFilterBuilder postgrestFilterBuilder2 = new PostgrestFilterBuilder(postgrestFilterBuilder.getPropertyConversionMethod(), null, 2, 0 == true ? 1 : 0);
        lVar.invoke(postgrestFilterBuilder2);
        map.put(strConcat, Collections.singletonList("(" + x.u0(h0.z0(postgrestFilterBuilder2.getParams()), ",", null, null, PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1.INSTANCE, 30) + ')'));
    }

    public static /* synthetic */ PostgrestFilterBuilder textSearch$default(PostgrestFilterBuilder postgrestFilterBuilder, String str, String str2, TextSearchType textSearchType, String str3, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return postgrestFilterBuilder.textSearch(str, str2, textSearchType, str3);
    }

    public final void adjacent(String column, x6.x range) {
        FilterOperator filterOperator = FilterOperator.ADJ;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(range.f22608i);
        sb2.append(',');
        filter(column, filterOperator, d.p(sb2, range.f22609l, ')'));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @PostgrestFilterDSL
    public final void and(boolean negate, l<? super PostgrestFilterBuilder, t0> filter) {
        String str = negate ? "not." : "";
        Map<String, List<String>> map = get_params();
        String strConcat = str.concat("and");
        PostgrestFilterBuilder postgrestFilterBuilder = new PostgrestFilterBuilder(getPropertyConversionMethod(), null, 2, 0 == true ? 1 : 0);
        filter.invoke(postgrestFilterBuilder);
        map.put(strConcat, Collections.singletonList("(" + x.u0(h0.z0(postgrestFilterBuilder.getParams()), ",", null, null, PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1.INSTANCE, 30) + ')'));
    }

    public final void cd(String column, List<? extends Object> values) {
        contained(column, values);
    }

    public final void contained(String column, List<? extends Object> values) {
        filter(column, FilterOperator.CD, c.u(new StringBuilder("{"), x.u0(values, ",", null, null, null, 62), '}'));
    }

    public final void contains(String column, List<? extends Object> values) {
        filter(column, FilterOperator.CS, c.u(new StringBuilder("{"), x.u0(values, ",", null, null, null, 62), '}'));
    }

    public final void cs(String column, List<? extends Object> values) {
        contains(column, values);
    }

    public final void eq(String column, Object value) {
        filter(column, FilterOperator.EQ, value);
    }

    public final void exact(String column, Boolean value) {
        filter(column, FilterOperator.IS, value);
    }

    public final void filter(String column, FilterOperator operator, Object value) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList(operator.getIdentifier() + '.' + value)));
    }

    public final void filterNot(String column, FilterOperator operator, Object value) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList("not." + operator.getIdentifier() + '.' + value)));
    }

    public final Map<String, List<String>> getParams() {
        return h0.B0(this._params);
    }

    public final PropertyConversionMethod getPropertyConversionMethod() {
        return this.propertyConversionMethod;
    }

    public final Map<String, List<String>> get_params() {
        return this._params;
    }

    public final void gt(String column, Object value) {
        filter(column, FilterOperator.GT, value);
    }

    public final void gte(String column, Object value) {
        filter(column, FilterOperator.GTE, value);
    }

    public final void ilike(String column, String pattern) {
        filter(column, FilterOperator.ILIKE, pattern);
    }

    public final void ilikeAll(String column, List<String> patterns) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList("ilike(all).{" + x.u0(patterns, ",", null, null, null, 62) + '}')));
    }

    public final void ilikeAny(String column, List<String> patterns) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList("ilike(any).{" + x.u0(patterns, ",", null, null, null, 62) + '}')));
    }

    public final void imatch(String column, String pattern) {
        filter(column, FilterOperator.IMATCH, pattern);
    }

    public final <T, V> void isExact(o<T, ? extends V> oVar, Boolean bool) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.IS, String.valueOf(bool)));
    }

    public final void isIn(String column, List<? extends Object> values) {
        filter(column, FilterOperator.IN, c.u(new StringBuilder("("), x.u0(values, ",", null, null, null, 62), ')'));
    }

    public final void like(String column, String pattern) {
        filter(column, FilterOperator.LIKE, pattern);
    }

    public final void likeAll(String column, List<String> patterns) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList("like(all).{" + x.u0(patterns, ",", null, null, null, 62) + '}')));
    }

    public final void likeAny(String column, List<String> patterns) {
        List<String> list = getParams().get(column);
        if (list == null) {
            list = z.f19728i;
        }
        this._params.put(column, x.I0(list, Collections.singletonList("like(any).{" + x.u0(patterns, ",", null, null, null, 62) + '}')));
    }

    public final void lt(String column, Object value) {
        filter(column, FilterOperator.LT, value);
    }

    public final void lte(String column, Object value) {
        filter(column, FilterOperator.LTE, value);
    }

    public final void match(String column, String pattern) {
        filter(column, FilterOperator.MATCH, pattern);
    }

    public final void neq(String column, Object value) {
        filter(column, FilterOperator.NEQ, value);
    }

    public final void nxl(String column, x6.x range) {
        FilterOperator filterOperator = FilterOperator.NXL;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(range.f22608i);
        sb2.append(',');
        filter(column, filterOperator, d.p(sb2, range.f22609l, ')'));
    }

    public final void nxr(String column, x6.x range) {
        FilterOperator filterOperator = FilterOperator.NXR;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(range.f22608i);
        sb2.append(',');
        filter(column, filterOperator, d.p(sb2, range.f22609l, ')'));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @PostgrestFilterDSL
    public final void or(boolean negate, l<? super PostgrestFilterBuilder, t0> filter) {
        String str = negate ? "not." : "";
        Map<String, List<String>> map = get_params();
        String strConcat = str.concat("or");
        PostgrestFilterBuilder postgrestFilterBuilder = new PostgrestFilterBuilder(getPropertyConversionMethod(), null, 2, 0 == true ? 1 : 0);
        filter.invoke(postgrestFilterBuilder);
        map.put(strConcat, Collections.singletonList("(" + x.u0(h0.z0(postgrestFilterBuilder.getParams()), ",", null, null, PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1.INSTANCE, 30) + ')'));
    }

    public final void ov(String column, List<? extends Object> values) {
        overlaps(column, values);
    }

    public final void overlaps(String column, List<? extends Object> values) {
        filter(column, FilterOperator.OV, c.u(new StringBuilder("{"), x.u0(values, ",", null, null, null, 62), '}'));
    }

    public final void rangeGt(String column, x6.x range) {
        sr(column, range);
    }

    public final void rangeGte(String column, x6.x range) {
        nxl(column, range);
    }

    public final void rangeLt(String column, x6.x range) {
        sl(column, range);
    }

    public final void rangeLte(String column, x6.x range) {
        nxr(column, range);
    }

    public final void sl(String column, x6.x range) {
        FilterOperator filterOperator = FilterOperator.SL;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(range.f22608i);
        sb2.append(',');
        filter(column, filterOperator, d.p(sb2, range.f22609l, ')'));
    }

    public final void sr(String column, x6.x range) {
        FilterOperator filterOperator = FilterOperator.SR;
        StringBuilder sb2 = new StringBuilder("(");
        sb2.append(range.f22608i);
        sb2.append(',');
        filter(column, filterOperator, d.p(sb2, range.f22609l, ')'));
    }

    public final PostgrestFilterBuilder textSearch(String column, String query, TextSearchType textSearchType, String config) {
        String strI = config == null ? "" : d.i(')', "(", config);
        this._params.put(column, Collections.singletonList(textSearchType.getIdentifier() + "fts" + strI + '.' + query));
        return this;
    }

    public final <T, V> void eq(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.EQ, String.valueOf(v2)));
    }

    public final <T, V> void gt(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.GT, String.valueOf(v2)));
    }

    public final <T, V> void gte(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.GTE, String.valueOf(v2)));
    }

    public final <T, V> void ilike(o<T, ? extends V> oVar, String str) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.ILIKE, str));
    }

    public final <T, V> void imatch(o<T, ? extends V> oVar, String str) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.IMATCH, str));
    }

    public final <T, V> void like(o<T, ? extends V> oVar, String str) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.LIKE, str));
    }

    public final <T, V> void lt(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.LT, String.valueOf(v2)));
    }

    public final <T, V> void lte(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.LTE, String.valueOf(v2)));
    }

    public final <T, V> void match(o<T, ? extends V> oVar, String str) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.MATCH, str));
    }

    public final <T, V> void neq(o<T, ? extends V> oVar, V v2) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.NEQ, String.valueOf(v2)));
    }

    public final <T, V> void rangeGt(o<T, ? extends V> oVar, x6.x xVar) {
        rangeGt(this.propertyConversionMethod.invoke(oVar), xVar);
    }

    public final <T, V> void rangeGte(o<T, ? extends V> oVar, x6.x xVar) {
        rangeGte(this.propertyConversionMethod.invoke(oVar), xVar);
    }

    public final <T, V> void rangeLt(o<T, ? extends V> oVar, x6.x xVar) {
        rangeLt(this.propertyConversionMethod.invoke(oVar), xVar);
    }

    public final <T, V> void rangeLte(o<T, ? extends V> oVar, x6.x xVar) {
        rangeLte(this.propertyConversionMethod.invoke(oVar), xVar);
    }

    public /* synthetic */ PostgrestFilterBuilder(PropertyConversionMethod propertyConversionMethod, Map map, int i10, h hVar) {
        this(propertyConversionMethod, (i10 & 2) != 0 ? new LinkedHashMap() : map);
    }

    public final void filter(FilterOperation operation) {
        filter(operation.getColumn(), operation.getOperator(), operation.getValue());
    }

    public final void filterNot(FilterOperation operation) {
        filterNot(operation.getColumn(), operation.getOperator(), operation.getValue());
    }

    public final <T, V> void contained(o<T, ? extends V> oVar, List<? extends Object> list) {
        contained(this.propertyConversionMethod.invoke(oVar), list);
    }

    public final <T, V> void contains(o<T, ? extends V> oVar, List<? extends Object> list) {
        contains(this.propertyConversionMethod.invoke(oVar), list);
    }

    public final <T, V> void isIn(o<T, ? extends V> oVar, List<? extends V> list) {
        filter(new FilterOperation(this.propertyConversionMethod.invoke(oVar), FilterOperator.IN, c.u(new StringBuilder("("), x.u0(list, ",", null, null, null, 62), ')')));
    }

    public final <T, V> void overlaps(o<T, ? extends V> oVar, List<? extends Object> list) {
        overlaps(this.propertyConversionMethod.invoke(oVar), list);
    }

    public final <T, V> void adjacent(o<T, ? extends V> oVar, x6.x xVar) {
        adjacent(this.propertyConversionMethod.invoke(oVar), xVar);
    }
}
