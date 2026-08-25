package a8;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.SystemClock;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.x2;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.unit.Density;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.session.MediaUtils;
import com.google.common.collect.u2;
import io.ktor.http.ContentDisposition;
import io.sentry.h6;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public class e implements g8.m, x2, androidx.core.view.d, androidx.leanback.widget.s, i2.g, com.google.android.gms.common.internal.e, u2, eb.e0, ea.b, com.google.android.gms.common.api.internal.t, h6.c, com.google.firebase.encoders.a, h6, io.sentry.android.replay.util.k, io.sentry.cache.tape.f, io.sentry.clientreport.g, io.sentry.featureflags.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f182i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f183l;

    public /* synthetic */ e(Object obj, int i10) {
        this.f182i = i10;
        this.f183l = obj;
    }

    public static void F(d2.c cVar) {
        cVar.p("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
        cVar.p("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `required_network_type` INTEGER NOT NULL, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
        cVar.p("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
        cVar.p("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        cVar.p("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        cVar.p("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
        cVar.p("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        cVar.p("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        cVar.p("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        cVar.p("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7d73d21f1bd82c9e5268b6dcf9fde2cb')");
    }

    public static int J(Iterator it) {
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                return ((Integer) next).intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    public static io.sentry.android.core.p0 M(d2.c cVar) {
        HashMap map = new HashMap(2);
        map.put("work_spec_id", new a2.a(1, "work_spec_id", "TEXT", null, true, 1));
        map.put("prerequisite_id", new a2.a(2, "prerequisite_id", "TEXT", null, true, 1));
        HashSet hashSet = new HashSet(2);
        hashSet.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        hashSet.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("prerequisite_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        HashSet hashSet2 = new HashSet(2);
        hashSet2.add(new a2.e("index_Dependency_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
        hashSet2.add(new a2.e("index_Dependency_prerequisite_id", false, Arrays.asList("prerequisite_id"), Arrays.asList("ASC")));
        a2.f fVar = new a2.f("Dependency", map, hashSet, hashSet2);
        a2.f fVarA = a2.f.a(cVar, "Dependency");
        if (!fVar.equals(fVarA)) {
            return new io.sentry.android.core.p0(false, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + fVar + "\n Found:\n" + fVarA);
        }
        HashMap map2 = new HashMap(30);
        map2.put(TtmlNode.ATTR_ID, new a2.a(1, TtmlNode.ATTR_ID, "TEXT", null, true, 1));
        map2.put("state", new a2.a(0, "state", "INTEGER", null, true, 1));
        map2.put("worker_class_name", new a2.a(0, "worker_class_name", "TEXT", null, true, 1));
        map2.put("input_merger_class_name", new a2.a(0, "input_merger_class_name", "TEXT", null, true, 1));
        map2.put("input", new a2.a(0, "input", "BLOB", null, true, 1));
        map2.put("output", new a2.a(0, "output", "BLOB", null, true, 1));
        map2.put("initial_delay", new a2.a(0, "initial_delay", "INTEGER", null, true, 1));
        map2.put("interval_duration", new a2.a(0, "interval_duration", "INTEGER", null, true, 1));
        map2.put("flex_duration", new a2.a(0, "flex_duration", "INTEGER", null, true, 1));
        map2.put("run_attempt_count", new a2.a(0, "run_attempt_count", "INTEGER", null, true, 1));
        map2.put("backoff_policy", new a2.a(0, "backoff_policy", "INTEGER", null, true, 1));
        map2.put("backoff_delay_duration", new a2.a(0, "backoff_delay_duration", "INTEGER", null, true, 1));
        map2.put("last_enqueue_time", new a2.a(0, "last_enqueue_time", "INTEGER", "-1", true, 1));
        map2.put("minimum_retention_duration", new a2.a(0, "minimum_retention_duration", "INTEGER", null, true, 1));
        map2.put("schedule_requested_at", new a2.a(0, "schedule_requested_at", "INTEGER", null, true, 1));
        map2.put("run_in_foreground", new a2.a(0, "run_in_foreground", "INTEGER", null, true, 1));
        map2.put("out_of_quota_policy", new a2.a(0, "out_of_quota_policy", "INTEGER", null, true, 1));
        map2.put("period_count", new a2.a(0, "period_count", "INTEGER", "0", true, 1));
        map2.put("generation", new a2.a(0, "generation", "INTEGER", "0", true, 1));
        map2.put("next_schedule_time_override", new a2.a(0, "next_schedule_time_override", "INTEGER", "9223372036854775807", true, 1));
        map2.put("next_schedule_time_override_generation", new a2.a(0, "next_schedule_time_override_generation", "INTEGER", "0", true, 1));
        map2.put(DownloadService.KEY_STOP_REASON, new a2.a(0, DownloadService.KEY_STOP_REASON, "INTEGER", "-256", true, 1));
        map2.put("required_network_type", new a2.a(0, "required_network_type", "INTEGER", null, true, 1));
        map2.put("requires_charging", new a2.a(0, "requires_charging", "INTEGER", null, true, 1));
        map2.put("requires_device_idle", new a2.a(0, "requires_device_idle", "INTEGER", null, true, 1));
        map2.put("requires_battery_not_low", new a2.a(0, "requires_battery_not_low", "INTEGER", null, true, 1));
        map2.put("requires_storage_not_low", new a2.a(0, "requires_storage_not_low", "INTEGER", null, true, 1));
        map2.put("trigger_content_update_delay", new a2.a(0, "trigger_content_update_delay", "INTEGER", null, true, 1));
        map2.put("trigger_max_content_delay", new a2.a(0, "trigger_max_content_delay", "INTEGER", null, true, 1));
        map2.put("content_uri_triggers", new a2.a(0, "content_uri_triggers", "BLOB", null, true, 1));
        HashSet hashSet3 = new HashSet(0);
        HashSet hashSet4 = new HashSet(2);
        hashSet4.add(new a2.e("index_WorkSpec_schedule_requested_at", false, Arrays.asList("schedule_requested_at"), Arrays.asList("ASC")));
        hashSet4.add(new a2.e("index_WorkSpec_last_enqueue_time", false, Arrays.asList("last_enqueue_time"), Arrays.asList("ASC")));
        a2.f fVar2 = new a2.f("WorkSpec", map2, hashSet3, hashSet4);
        a2.f fVarA2 = a2.f.a(cVar, "WorkSpec");
        if (!fVar2.equals(fVarA2)) {
            return new io.sentry.android.core.p0(false, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + fVar2 + "\n Found:\n" + fVarA2);
        }
        HashMap map3 = new HashMap(2);
        map3.put("tag", new a2.a(1, "tag", "TEXT", null, true, 1));
        map3.put("work_spec_id", new a2.a(2, "work_spec_id", "TEXT", null, true, 1));
        HashSet hashSet5 = new HashSet(1);
        hashSet5.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        HashSet hashSet6 = new HashSet(1);
        hashSet6.add(new a2.e("index_WorkTag_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
        a2.f fVar3 = new a2.f("WorkTag", map3, hashSet5, hashSet6);
        a2.f fVarA3 = a2.f.a(cVar, "WorkTag");
        if (!fVar3.equals(fVarA3)) {
            return new io.sentry.android.core.p0(false, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + fVar3 + "\n Found:\n" + fVarA3);
        }
        HashMap map4 = new HashMap(3);
        map4.put("work_spec_id", new a2.a(1, "work_spec_id", "TEXT", null, true, 1));
        map4.put("generation", new a2.a(2, "generation", "INTEGER", "0", true, 1));
        map4.put("system_id", new a2.a(0, "system_id", "INTEGER", null, true, 1));
        HashSet hashSet7 = new HashSet(1);
        hashSet7.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        a2.f fVar4 = new a2.f("SystemIdInfo", map4, hashSet7, new HashSet(0));
        a2.f fVarA4 = a2.f.a(cVar, "SystemIdInfo");
        if (!fVar4.equals(fVarA4)) {
            return new io.sentry.android.core.p0(false, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + fVar4 + "\n Found:\n" + fVarA4);
        }
        HashMap map5 = new HashMap(2);
        map5.put(ContentDisposition.Parameters.Name, new a2.a(1, ContentDisposition.Parameters.Name, "TEXT", null, true, 1));
        map5.put("work_spec_id", new a2.a(2, "work_spec_id", "TEXT", null, true, 1));
        HashSet hashSet8 = new HashSet(1);
        hashSet8.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        HashSet hashSet9 = new HashSet(1);
        hashSet9.add(new a2.e("index_WorkName_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
        a2.f fVar5 = new a2.f("WorkName", map5, hashSet8, hashSet9);
        a2.f fVarA5 = a2.f.a(cVar, "WorkName");
        if (!fVar5.equals(fVarA5)) {
            return new io.sentry.android.core.p0(false, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + fVar5 + "\n Found:\n" + fVarA5);
        }
        HashMap map6 = new HashMap(2);
        map6.put("work_spec_id", new a2.a(1, "work_spec_id", "TEXT", null, true, 1));
        map6.put("progress", new a2.a(0, "progress", "BLOB", null, true, 1));
        HashSet hashSet10 = new HashSet(1);
        hashSet10.add(new a2.c("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList(TtmlNode.ATTR_ID)));
        a2.f fVar6 = new a2.f("WorkProgress", map6, hashSet10, new HashSet(0));
        a2.f fVarA6 = a2.f.a(cVar, "WorkProgress");
        if (!fVar6.equals(fVarA6)) {
            return new io.sentry.android.core.p0(false, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + fVar6 + "\n Found:\n" + fVarA6);
        }
        HashMap map7 = new HashMap(2);
        map7.put("key", new a2.a(1, "key", "TEXT", null, true, 1));
        map7.put("long_value", new a2.a(0, "long_value", "INTEGER", null, false, 1));
        a2.f fVar7 = new a2.f("Preference", map7, new HashSet(0), new HashSet(0));
        a2.f fVarA7 = a2.f.a(cVar, "Preference");
        if (fVar7.equals(fVarA7)) {
            return new io.sentry.android.core.p0(true, null);
        }
        return new io.sentry.android.core.p0(false, "Preference(androidx.work.impl.model.Preference).\n Expected:\n" + fVar7 + "\n Found:\n" + fVarA7);
    }

    public static int N(ArrayList arrayList) {
        if (arrayList.size() <= 0) {
            throw new IllegalArgumentException();
        }
        Object obj = arrayList.get(0);
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (obj.getClass().equals(Integer.class)) {
            return ((Integer) obj).intValue();
        }
        throw new IllegalArgumentException();
    }

    public static int O(ArrayList arrayList, int i10, int i11) {
        Object obj;
        return (arrayList.size() <= i10 || (obj = arrayList.get(i10)) == null) ? i11 : ((Integer) obj).intValue();
    }

    @Override // g8.m
    public Object A(j8.z zVar, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                StringBuilder sb2 = (StringBuilder) obj;
                h9.h hVar = (h9.h) this.f183l;
                hVar.getClass();
                f9.c cVar = zVar.f19398n;
                sb2.append(hVar.H("package"));
                String strO = hVar.o(com.google.common.util.concurrent.p0.A(cVar.i().e()));
                if (strO.length() > 0) {
                    sb2.append(" ");
                    sb2.append(strO);
                }
                if (hVar.f15910a.n()) {
                    sb2.append(" in context of ");
                    hVar.O(zVar.f19397m, sb2, false);
                }
                return x6.t0.f22605a;
        }
    }

    @Override // g8.m
    public Object B(j8.f0 f0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                StringBuilder sb2 = (StringBuilder) obj;
                h9.h hVar = (h9.h) this.f183l;
                hVar.getClass();
                f9.c cVar = f0Var.f19274o;
                sb2.append(hVar.H("package-fragment"));
                String strO = hVar.o(com.google.common.util.concurrent.p0.A(cVar.i().e()));
                if (strO.length() > 0) {
                    sb2.append(" ");
                    sb2.append(strO);
                }
                if (hVar.f15910a.n()) {
                    sb2.append(" in ");
                    hVar.O(f0Var.d(), sb2, false);
                }
                return x6.t0.f22605a;
        }
    }

    @Override // g8.m
    public Object C(j8.k kVar, Object obj) {
        g8.e eVarC;
        switch (this.f182i) {
            case 0:
                return b(kVar, obj);
            default:
                boolean z = kVar.O;
                StringBuilder sb2 = (StringBuilder) obj;
                h9.h hVar = (h9.h) this.f183l;
                hVar.getClass();
                hVar.x(sb2, kVar, null);
                h9.l lVar = hVar.f15910a;
                h9.k kVar2 = lVar.f15940o;
                kotlin.reflect.m[] mVarArr = h9.l.X;
                boolean z5 = (((Boolean) kVar2.getValue(lVar, mVarArr[13])).booleanValue() || kVar.a0().o() != 2) && hVar.e0(kVar.getVisibility(), sb2);
                hVar.I(kVar, sb2);
                boolean z10 = ((Boolean) lVar.P.getValue(lVar, mVarArr[40])).booleanValue() || !z || z5;
                if (z10) {
                    sb2.append(hVar.H("constructor"));
                }
                g8.f fVarS0 = kVar.d();
                if (((Boolean) lVar.A.getValue(lVar, mVarArr[25])).booleanValue()) {
                    if (z10) {
                        sb2.append(" ");
                    }
                    hVar.O(fVarS0, sb2, true);
                    hVar.a0(sb2, kVar.getTypeParameters(), false);
                }
                hVar.d0(sb2, kVar.e(), kVar.f0());
                if (((Boolean) lVar.f15942q.getValue(lVar, mVarArr[15])).booleanValue() && !z && androidx.compose.material3.d.B(fVarS0) && (eVarC = fVarS0.C()) != null) {
                    List listE = eVarC.e();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : listE) {
                        g8.e1 e1Var = (g8.e1) obj2;
                        if (!e1Var.w0() && e1Var.q0() == null) {
                            arrayList.add(obj2);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        sb2.append(" : ");
                        sb2.append(hVar.H("this"));
                        sb2.append(kotlin.collections.x.u0(arrayList, ", ", "(", ")", h9.g.f15902l, 24));
                    }
                }
                if (((Boolean) lVar.A.getValue(lVar, h9.l.X[25])).booleanValue()) {
                    hVar.f0(sb2, kVar.getTypeParameters());
                }
                return x6.t0.f22605a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void D(int r8, java.lang.Object r9, int r10, int r11, int r12) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a8.e.D(int, java.lang.Object, int, int, int):void");
    }

    public void E(io.sentry.android.core.v0 v0Var) {
        ((io.sentry.android.core.r0) this.f183l).getClass();
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 26 || i10 > 28) {
            return;
        }
        String callingPackage = v0Var.getCallingPackage();
        String packageName = v0Var.getContext().getPackageName();
        if (callingPackage == null || !callingPackage.equals(packageName)) {
            throw new SecurityException("Provider does not allow for granting of Uri permissions");
        }
    }

    public int G(int i10, boolean z, Object[] objArr, boolean z5) {
        int i11;
        View viewR;
        androidx.leanback.widget.a0 a0Var = (androidx.leanback.widget.a0) this.f183l;
        View viewY0 = a0Var.Y0(i10 - a0Var.f3073w);
        if (!((androidx.leanback.widget.x) viewY0.getLayoutParams()).f4875a.isRemoved()) {
            if (z5) {
                if (z) {
                    a0Var.a(viewY0, -1, true);
                } else {
                    a0Var.a(viewY0, 0, true);
                }
            } else if (z) {
                a0Var.a(viewY0, -1, false);
            } else {
                a0Var.a(viewY0, 0, false);
            }
            int i12 = a0Var.J;
            if (i12 != -1) {
                viewY0.setVisibility(i12);
            }
            androidx.leanback.widget.y yVar = a0Var.H;
            if (yVar != null) {
                androidx.leanback.widget.a0 a0Var2 = yVar.f3179t;
                if (!yVar.f3177r && (i11 = yVar.f3178s) != 0) {
                    int i13 = i11 > 0 ? a0Var2.F + a0Var2.U : a0Var2.F - a0Var2.U;
                    View view = null;
                    while (yVar.f3178s != 0 && (viewR = yVar.f3153b.x.r(i13)) != null) {
                        if (viewR.getVisibility() == 0 && (!a0Var2.P() || viewR.hasFocusable())) {
                            a0Var2.F = i13;
                            int i14 = yVar.f3178s;
                            if (i14 > 0) {
                                yVar.f3178s = i14 - 1;
                            } else {
                                yVar.f3178s = i14 + 1;
                            }
                            view = viewR;
                        }
                        i13 = yVar.f3178s > 0 ? i13 + a0Var2.U : i13 - a0Var2.U;
                    }
                    if (view != null && a0Var2.P()) {
                        a0Var2.B |= 32;
                        view.requestFocus();
                        a0Var2.B &= -33;
                    }
                }
            }
            if (viewY0.findFocus() != null) {
                ((androidx.leanback.widget.x) viewY0.getLayoutParams()).getClass();
            }
            int i15 = a0Var.B;
            if ((i15 & 3) != 1) {
                if (i10 == a0Var.F && a0Var.H == null) {
                    a0Var.N0();
                }
            } else if ((i15 & 4) == 0) {
                int i16 = i15 & 16;
                if (i16 == 0 && i10 == a0Var.F) {
                    a0Var.N0();
                } else if (i16 != 0 && i10 >= a0Var.F && viewY0.hasFocusable()) {
                    a0Var.F = i10;
                    a0Var.B &= -17;
                    a0Var.N0();
                }
            }
            a0Var.d1(viewY0);
        }
        objArr[0] = viewY0;
        return a0Var.f3069s == 0 ? androidx.leanback.widget.a0.S0(viewY0) : androidx.leanback.widget.a0.R0(viewY0);
    }

    public int H() {
        androidx.leanback.widget.a0 a0Var = (androidx.leanback.widget.a0) this.f183l;
        return a0Var.f3072v.b() + a0Var.f3073w;
    }

    public int I(int i10) {
        androidx.leanback.widget.a0 a0Var = (androidx.leanback.widget.a0) this.f183l;
        View viewR = a0Var.r(i10 - a0Var.f3073w);
        return (a0Var.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? a0Var.f3070t.b(viewR) : a0Var.f3070t.e(viewR);
    }

    public Object K(ab.g gVar, eb.s sVar, r7.a aVar) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f183l;
        Map map = (Map) concurrentHashMap.get(gVar);
        Object obj = map != null ? map.get(sVar) : null;
        Object obj2 = obj != null ? obj : null;
        if (obj2 != null) {
            return obj2;
        }
        Object objInvoke = aVar.invoke();
        Object concurrentHashMap2 = concurrentHashMap.get(gVar);
        if (concurrentHashMap2 == null) {
            concurrentHashMap2 = new ConcurrentHashMap(2);
            concurrentHashMap.put(gVar, concurrentHashMap2);
        }
        ((Map) concurrentHashMap2).put(sVar, objInvoke);
        return objInvoke;
    }

    public int L(int i10) {
        androidx.leanback.widget.a0 a0Var = (androidx.leanback.widget.a0) this.f183l;
        View viewR = a0Var.r(i10 - a0Var.f3073w);
        Rect rect = androidx.leanback.widget.a0.f3059h0;
        a0Var.A(viewR, rect);
        return a0Var.f3069s == 0 ? rect.width() : rect.height();
    }

    public boolean R(ArrayList arrayList) {
        N(arrayList);
        ((Character) arrayList.get(1)).getClass();
        return true;
    }

    public void T(int i10) throws IOException {
        for (int i11 = 0; i11 < i10; i11++) {
            ((OutputStream) this.f183l).write(10);
        }
    }

    public void V(int i10) throws IOException {
        for (int i11 = 0; i11 < i10; i11++) {
            ((OutputStream) this.f183l).write(32);
        }
    }

    @Override // i2.g
    public float a(int i10) {
        androidx.tv.foundation.lazy.list.e0 e0VarC = ((androidx.tv.foundation.lazy.list.m0) this.f183l).c();
        List visibleItemsInfo = e0VarC.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        int size2 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            size2 += ((androidx.tv.foundation.lazy.list.b0) visibleItemsInfo.get(i11)).getSize();
        }
        int mainAxisItemSpacing = e0VarC.getMainAxisItemSpacing() + (size2 / visibleItemsInfo.size());
        return ((mainAxisItemSpacing * (i10 - r0.a())) + Math.min(Math.abs(0), mainAxisItemSpacing)) - r0.b();
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) {
        h4.c cVar = new h4.c((com.google.android.gms.tasks.i) obj2);
        com.google.android.gms.internal.fido.d2 d2Var = (com.google.android.gms.internal.fido.d2) ((com.google.android.gms.internal.fido.b2) obj).getService();
        i4.t tVar = (i4.t) this.f183l;
        d2Var.getClass();
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(d2Var.f13773d);
        int i10 = com.google.android.gms.internal.fido.n.f13798a;
        parcelObtain.writeStrongBinder(cVar);
        parcelObtain.writeInt(1);
        tVar.writeToParcel(parcelObtain, 0);
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            d2Var.f13772c.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain.recycle();
            parcelObtain2.recycle();
        }
    }

    @Override // g8.m
    public Object b(g8.w wVar, Object obj) {
        switch (this.f182i) {
            case 0:
                return new m0((j0) this.f183l, wVar);
            default:
                s0(wVar, (StringBuilder) obj);
                return x6.t0.f22605a;
        }
    }

    @Override // androidx.appcompat.widget.x2
    public void c(androidx.appcompat.view.menu.q qVar, androidx.appcompat.view.menu.t tVar) {
        androidx.appcompat.view.menu.k kVar = (androidx.appcompat.view.menu.k) this.f183l;
        Handler handler = kVar.f1318p;
        handler.removeCallbacksAndMessages(null);
        ArrayList arrayList = kVar.f1320r;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                i10 = -1;
                break;
            } else if (qVar == ((androidx.appcompat.view.menu.i) arrayList.get(i10)).f1311b) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 == -1) {
            return;
        }
        int i11 = i10 + 1;
        handler.postAtTime(new androidx.appcompat.view.menu.h(this, i11 < arrayList.size() ? (androidx.appcompat.view.menu.i) arrayList.get(i11) : null, tVar, qVar, 0), qVar, SystemClock.uptimeMillis() + 200);
    }

    @Override // io.sentry.featureflags.b
    /* JADX INFO: renamed from: clone */
    public io.sentry.featureflags.b m7019clone() {
        return new e(28);
    }

    @Override // com.google.common.collect.u2
    public Object d(Object obj, Object obj2) {
        return ((com.google.common.base.m) this.f183l).apply(obj2);
    }

    @Override // io.sentry.cache.tape.f
    public void e(Object obj, OutputStream outputStream) throws IOException {
        io.sentry.f fVar = (io.sentry.f) obj;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, io.sentry.cache.g.f17160c));
        try {
            ((io.sentry.cache.g) this.f183l).f17161a.getSerializer().a(bufferedWriter, fVar);
            bufferedWriter.close();
        } catch (Throwable th) {
            try {
                bufferedWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.cache.tape.f
    public Object f(byte[] bArr) {
        io.sentry.cache.g gVar = (io.sentry.cache.g) this.f183l;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr), io.sentry.cache.g.f17160c));
            try {
                io.sentry.f fVar = (io.sentry.f) gVar.f17161a.getSerializer().e(bufferedReader, io.sentry.f.class);
                bufferedReader.close();
                return fVar;
            } finally {
            }
        } catch (Throwable th) {
            gVar.f17161a.getLogger().k(w5.ERROR, th, "Error reading entity from scope cache", new Object[0]);
            return null;
        }
    }

    public boolean f0(int i10, ArrayList arrayList) throws IOException {
        if (i10 == 83) {
            O(arrayList, 0, 1);
            return true;
        }
        if (i10 == 84) {
            O(arrayList, 0, 1);
            return true;
        }
        if (i10 != 102) {
            if (i10 != 109) {
                if (i10 == 115) {
                    j0();
                    return true;
                }
                if (i10 == 117) {
                    i0();
                    return true;
                }
                switch (i10) {
                    case 65:
                        Y(O(arrayList, 0, 1));
                        return true;
                    case 66:
                        S(O(arrayList, 0, 1));
                        return true;
                    case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                        V(O(arrayList, 0, 1));
                        return true;
                    case 68:
                        U(O(arrayList, 0, 1));
                        return true;
                    case 69:
                        T(O(arrayList, 0, 1));
                        return true;
                    case 70:
                        Z(O(arrayList, 0, 1));
                        return true;
                    case TsExtractor.TS_SYNC_BYTE /* 71 */:
                        X(N(arrayList));
                        return true;
                    case 72:
                        break;
                    default:
                        switch (i10) {
                            case 74:
                                e0(O(arrayList, 0, 0));
                                return true;
                            case 75:
                                d0(O(arrayList, 0, 0));
                                return true;
                            case 76:
                                g0(O(arrayList, 0, 1));
                                return true;
                            case 77:
                                c0(O(arrayList, 0, 1));
                                return true;
                            default:
                                if ((97 > i10 || i10 > 122) && (65 > i10 || i10 > 90)) {
                                    return false;
                                }
                                break;
                        }
                        break;
                }
            } else {
                for (Object obj : arrayList) {
                    if (obj != null && obj.getClass() != Integer.class) {
                        throw new IllegalArgumentException();
                    }
                }
                Iterator it = arrayList.iterator();
                int i11 = 0;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next != null) {
                        i11++;
                        int iIntValue = ((Integer) next).intValue();
                        if (30 <= iIntValue && iIntValue <= 37) {
                            o0(iIntValue - 30, false);
                        } else if (40 <= iIntValue && iIntValue <= 47) {
                            l0(iIntValue - 40, false);
                        } else if (90 <= iIntValue && iIntValue <= 97) {
                            o0(iIntValue - 90, true);
                        } else if (100 <= iIntValue && iIntValue <= 107) {
                            l0(iIntValue - 100, true);
                        } else if (iIntValue == 38 || iIntValue == 48) {
                            if (it.hasNext()) {
                                int iJ = J(it);
                                if (iJ == 2) {
                                    int iJ2 = J(it);
                                    int iJ3 = J(it);
                                    int iJ4 = J(it);
                                    if (iJ2 < 0 || iJ2 > 255 || iJ3 < 0 || iJ3 > 255 || iJ4 < 0 || iJ4 > 255) {
                                        throw new IllegalArgumentException();
                                    }
                                    if (iIntValue == 38) {
                                        q0(iJ2, iJ3, iJ4);
                                    } else {
                                        n0(iJ2, iJ3, iJ4);
                                    }
                                } else {
                                    if (iJ != 5) {
                                        throw new IllegalArgumentException();
                                    }
                                    int iJ5 = J(it);
                                    if (iJ5 < 0 || iJ5 > 255) {
                                        throw new IllegalArgumentException();
                                    }
                                    if (iIntValue == 38) {
                                        p0(iJ5);
                                    } else {
                                        m0(iJ5);
                                    }
                                }
                            }
                        } else if (iIntValue == 0) {
                            P();
                        } else if (iIntValue == 39) {
                            b0();
                        } else if (iIntValue != 49) {
                            k0(iIntValue);
                        } else {
                            a0();
                        }
                    }
                }
                if (i11 == 0) {
                    P();
                }
            }
            return true;
        }
        W(O(arrayList, 0, 1), O(arrayList, 1, 1));
        return true;
    }

    @Override // h6.d
    public String g(String str) {
        SharedPreferences sharedPreferences = (SharedPreferences) this.f183l;
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getString(str, "");
        }
        return null;
    }

    @Override // i2.g
    public Density getDensity() {
        return ((androidx.tv.foundation.lazy.list.m0) this.f183l).f5434h;
    }

    @Override // i2.g
    public int getFirstVisibleItemIndex() {
        return ((androidx.tv.foundation.lazy.list.m0) this.f183l).a();
    }

    @Override // i2.g
    public int getFirstVisibleItemScrollOffset() {
        return ((androidx.tv.foundation.lazy.list.m0) this.f183l).b();
    }

    @Override // i2.g
    public int getItemCount() {
        return ((androidx.tv.foundation.lazy.list.m0) this.f183l).c().getTotalItemsCount();
    }

    @Override // i2.g
    public int getLastVisibleItemIndex() {
        androidx.tv.foundation.lazy.list.b0 b0Var = (androidx.tv.foundation.lazy.list.b0) kotlin.collections.x.y0(((androidx.tv.foundation.lazy.list.m0) this.f183l).c().getVisibleItemsInfo());
        if (b0Var != null) {
            return b0Var.getIndex();
        }
        return 0;
    }

    @Override // io.sentry.android.replay.util.k
    public int getLineBottom(int i10) {
        return ((Layout) this.f183l).getLineBottom(i10);
    }

    @Override // io.sentry.android.replay.util.k
    public int getLineCount() {
        return ((Layout) this.f183l).getLineCount();
    }

    @Override // io.sentry.android.replay.util.k
    public float getLineLeft(int i10) {
        Layout layout = (Layout) this.f183l;
        if (layout.getEllipsizedWidth() <= 0 || layout.getEllipsizedWidth() >= layout.getWidth()) {
            return layout.getLineLeft(i10);
        }
        return 0.0f;
    }

    @Override // io.sentry.android.replay.util.k
    public float getLineRight(int i10) {
        Layout layout = (Layout) this.f183l;
        return (layout.getEllipsizedWidth() <= 0 || layout.getEllipsizedWidth() >= layout.getWidth()) ? layout.getLineRight(i10) : layout.getEllipsizedWidth();
    }

    @Override // io.sentry.android.replay.util.k
    public int getLineTop(int i10) {
        return ((Layout) this.f183l).getLineTop(i10);
    }

    @Override // g8.m
    public Object h(j8.d dVar, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                ((StringBuilder) obj).append(dVar.getName());
                return x6.t0.f22605a;
        }
    }

    public boolean h0(ArrayList arrayList) {
        int iN = N(arrayList);
        String str = (String) arrayList.get(1);
        try {
            if (iN == 0) {
                Q(str);
                return true;
            }
            if (iN != 2) {
                return true;
            }
            Q(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // io.sentry.android.replay.util.k
    public Integer i() {
        int i10;
        Layout layout = (Layout) this.f183l;
        if (!(layout.getText() instanceof Spanned)) {
            return null;
        }
        int i11 = Integer.MIN_VALUE;
        Integer numValueOf = null;
        for (ForegroundColorSpan foregroundColorSpan : (ForegroundColorSpan[]) ((Spanned) layout.getText()).getSpans(0, layout.getText().length(), ForegroundColorSpan.class)) {
            int spanStart = ((Spanned) layout.getText()).getSpanStart(foregroundColorSpan);
            int spanEnd = ((Spanned) layout.getText()).getSpanEnd(foregroundColorSpan);
            if (spanStart != -1 && spanEnd != -1 && (i10 = spanEnd - spanStart) > i11) {
                numValueOf = Integer.valueOf(foregroundColorSpan.getForegroundColor());
                i11 = i10;
            }
        }
        if (numValueOf != null) {
            return Integer.valueOf(numValueOf.intValue() | (-16777216));
        }
        return null;
    }

    @Override // g8.m
    public Object j(j8.v0 v0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                ((h9.h) this.f183l).c0(v0Var, true, (StringBuilder) obj, true);
                return x6.t0.f22605a;
        }
    }

    @Override // i2.g
    public Integer k(int i10) {
        Object obj;
        List visibleItemsInfo = ((androidx.tv.foundation.lazy.list.m0) this.f183l).c().getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                obj = null;
                break;
            }
            obj = visibleItemsInfo.get(i11);
            if (((androidx.tv.foundation.lazy.list.b0) obj).getIndex() == i10) {
                break;
            }
            i11++;
        }
        androidx.tv.foundation.lazy.list.b0 b0Var = (androidx.tv.foundation.lazy.list.b0) obj;
        if (b0Var != null) {
            return Integer.valueOf(b0Var.getOffset());
        }
        return null;
    }

    @Override // g8.m
    public Object l(j8.b0 b0Var, Object obj) throws IOException {
        g8.e eVarC;
        String str;
        switch (this.f182i) {
            case 0:
                return null;
            default:
                StringBuilder sb2 = (StringBuilder) obj;
                h9.h hVar = (h9.h) this.f183l;
                h9.l lVar = hVar.f15910a;
                boolean z = b0Var.p0() == 4;
                if (!hVar.q()) {
                    hVar.x(sb2, b0Var, null);
                    hVar.B(sb2, b0Var.W());
                    if (!z) {
                        hVar.e0(b0Var.getVisibility(), sb2);
                    }
                    if ((b0Var.p0() != 2 || b0Var.o() != 4) && (!g8.b.c(b0Var.p0()) || b0Var.o() != 1)) {
                        hVar.K(sb2, b0Var.o(), h9.h.u(b0Var));
                    }
                    hVar.J(b0Var, sb2);
                    hVar.M("inner", hVar.p().contains(h9.i.INNER) && b0Var.i(), sb2);
                    hVar.M("data", hVar.p().contains(h9.i.DATA) && b0Var.E0(), sb2);
                    hVar.M("inline", hVar.p().contains(h9.i.INLINE) && b0Var.isInline(), sb2);
                    hVar.M("value", hVar.p().contains(h9.i.VALUE) && b0Var.q(), sb2);
                    hVar.M("fun", hVar.p().contains(h9.i.FUN) && b0Var.c0(), sb2);
                    if (b0Var instanceof g8.a1) {
                        str = "typealias";
                    } else if (b0Var.Y()) {
                        str = "companion object";
                    } else {
                        int iC = h.f0.c(b0Var.p0());
                        if (iC == 0) {
                            str = "class";
                        } else if (iC == 1) {
                            str = "interface";
                        } else if (iC == 2) {
                            str = "enum class";
                        } else if (iC == 3) {
                            str = "enum entry";
                        } else if (iC == 4) {
                            str = "annotation class";
                        } else {
                            if (iC != 5) {
                                throw new NoWhenBranchMatchedException();
                            }
                            str = "object";
                        }
                    }
                    sb2.append(hVar.H(str));
                }
                if (i9.f.l(b0Var)) {
                    if (((Boolean) lVar.G.getValue(lVar, h9.l.X[31])).booleanValue()) {
                        if (hVar.q()) {
                            sb2.append("companion object");
                        }
                        h9.h.U(sb2);
                        g8.k kVarD = b0Var.d();
                        if (kVarD != null) {
                            sb2.append("of ");
                            sb2.append(hVar.N(kVarD.getName(), false));
                        }
                    }
                    if (hVar.t() || !kotlin.jvm.internal.p.a(b0Var.getName(), f9.h.f15373b)) {
                        if (!hVar.q()) {
                            h9.h.U(sb2);
                        }
                        sb2.append(hVar.N(b0Var.getName(), true));
                    }
                } else {
                    if (!hVar.q()) {
                        h9.h.U(sb2);
                    }
                    hVar.O(b0Var, sb2, true);
                }
                if (!z) {
                    List listN = b0Var.n();
                    hVar.a0(sb2, listN, false);
                    hVar.z(b0Var, sb2);
                    if (!g8.b.c(b0Var.p0()) && ((Boolean) lVar.f15935i.getValue(lVar, h9.l.X[7])).booleanValue() && (eVarC = b0Var.C()) != null) {
                        sb2.append(" ");
                        hVar.x(sb2, eVarC, null);
                        hVar.e0(eVarC.getVisibility(), sb2);
                        sb2.append(hVar.H("constructor"));
                        hVar.d0(sb2, eVarC.e(), eVarC.f0());
                    }
                    if (!((Boolean) lVar.x.getValue(lVar, h9.l.X[22])).booleanValue() && !d8.k.E(b0Var.m())) {
                        Collection collectionD = b0Var.g().d();
                        if (!collectionD.isEmpty() && (collectionD.size() != 1 || !d8.k.x((v9.w) collectionD.iterator().next()))) {
                            h9.h.U(sb2);
                            sb2.append(": ");
                            kotlin.collections.x.t0(collectionD, sb2, ", ", null, null, new h9.f(hVar, 1), 60);
                        }
                    }
                    hVar.f0(sb2, listN);
                }
                return x6.t0.f22605a;
        }
    }

    @Override // com.google.android.gms.common.internal.e
    public void m() {
        ((com.google.android.gms.common.api.internal.e0) this.f183l).f12727o.x.post(new androidx.leanback.widget.u(this, 3));
    }

    @Override // io.sentry.featureflags.b
    public io.sentry.protocol.h n() {
        ((io.sentry.util.b) this.f183l).a().close();
        return null;
    }

    @Override // g8.m
    public Object o(j8.n0 n0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                return b(n0Var, obj);
            default:
                t0(n0Var, (StringBuilder) obj, "setter");
                return x6.t0.f22605a;
        }
    }

    @Override // g8.m
    public Object p(j8.m0 m0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                return b(m0Var, obj);
            default:
                t0(m0Var, (StringBuilder) obj, "getter");
                return x6.t0.f22605a;
        }
    }

    @Override // h6.d
    public void putString(String str, String str2) {
        ((SharedPreferences) this.f183l).edit().putString(str, str2).apply();
    }

    @Override // g8.m
    public Object q(j8.l0 l0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                j0 j0Var = (j0) this.f183l;
                int i10 = (l0Var.D != null ? 1 : 0) + (l0Var.E != null ? 1 : 0);
                if (l0Var.f19391p) {
                    if (i10 == 0) {
                        return new o0(j0Var, l0Var);
                    }
                    if (i10 == 1) {
                        return new q0(j0Var, l0Var);
                    }
                    if (i10 == 2) {
                        return new s0(j0Var, l0Var);
                    }
                } else {
                    if (i10 == 0) {
                        return new e1(j0Var, l0Var);
                    }
                    if (i10 == 1) {
                        return new h1(j0Var, l0Var);
                    }
                    if (i10 == 2) {
                        return new k1(j0Var, l0Var);
                    }
                }
                throw new x1("Unsupported property: " + l0Var);
            default:
                h9.h.n((h9.h) this.f183l, l0Var, (StringBuilder) obj);
                return x6.t0.f22605a;
        }
    }

    @Override // ea.b
    public Iterable r(Object obj) {
        f8.n nVar = (f8.n) this.f183l;
        Collection collectionD = ((g8.f) obj).g().d();
        ArrayList arrayList = new ArrayList();
        Iterator it = collectionD.iterator();
        while (it.hasNext()) {
            g8.h hVarE = ((v9.w) it.next()).I0().e();
            g8.f fVarF = null;
            g8.h hVarA = hVarE != null ? hVarE.a() : null;
            g8.f fVar = hVarA instanceof g8.f ? (g8.f) hVarA : null;
            if (fVar != null && (fVarF = nVar.f(fVar)) == null) {
                fVarF = fVar;
            }
            if (fVarF != null) {
                arrayList.add(fVarF);
            }
        }
        return arrayList;
    }

    public void r0(int i10) {
        androidx.leanback.widget.a0 a0Var = (androidx.leanback.widget.a0) this.f183l;
        View viewR = a0Var.r(i10 - a0Var.f3073w);
        if ((a0Var.B & 3) == 1) {
            a0Var.z0(a0Var.A, a0Var.f5113a.Y(viewR), viewR);
        } else {
            a0Var.u0(viewR, a0Var.A);
        }
    }

    @Override // h6.d
    public void remove(String str) {
        ((SharedPreferences) this.f183l).edit().remove(str).apply();
    }

    @Override // io.sentry.h6
    public io.sentry.f s(io.sentry.f fVar, io.sentry.j0 j0Var) {
        h6 h6Var = (h6) this.f183l;
        if (h6Var != null) {
            fVar = h6Var.s(fVar, j0Var);
        }
        if (fVar == null || !(kotlin.jvm.internal.p.a(fVar.f17247o, "http") || kotlin.jvm.internal.p.a(fVar.f17249q, "http"))) {
            return fVar;
        }
        j0Var.b("sentry:replayNetworkDetails");
        return fVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void s0(g8.w r11, java.lang.StringBuilder r12) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a8.e.s0(g8.w, java.lang.StringBuilder):void");
    }

    @Override // g8.m
    public Object t(j8.d0 d0Var, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                ((h9.h) this.f183l).O(d0Var, (StringBuilder) obj, true);
                return x6.t0.f22605a;
        }
    }

    public void t0(j8.j0 j0Var, StringBuilder sb2, String str) {
        h9.h hVar = (h9.h) this.f183l;
        h9.l lVar = hVar.f15910a;
        int iOrdinal = ((h9.q) lVar.H.getValue(lVar, h9.l.X[32])).ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                return;
            }
            s0(j0Var, sb2);
        } else {
            hVar.J(j0Var, sb2);
            sb2.append(str.concat(" for "));
            h9.h.n(hVar, j0Var.T(), sb2);
        }
    }

    @Override // g8.m
    public Object u(j8.f fVar, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                StringBuilder sb2 = (StringBuilder) obj;
                h9.h hVar = (h9.h) this.f183l;
                hVar.getClass();
                hVar.x(sb2, fVar, null);
                hVar.e0(fVar.f19271p, sb2);
                hVar.J(fVar, sb2);
                sb2.append(hVar.H("typealias"));
                sb2.append(" ");
                hVar.O(fVar, sb2, true);
                hVar.a0(sb2, fVar.n(), false);
                hVar.z(fVar, sb2);
                sb2.append(" = ");
                sb2.append(hVar.V(((kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.w) fVar).o0()));
                return x6.t0.f22605a;
        }
    }

    public void u0() throws IOException {
        ArrayDeque arrayDeque = (ArrayDeque) this.f183l;
        if (arrayDeque.isEmpty()) {
            return;
        }
        throw new IOException("data item not completed, stackSize: " + arrayDeque.size() + " scope: " + w0());
    }

    @Override // g8.m
    public Object v(j8.j jVar, Object obj) {
        switch (this.f182i) {
            case 0:
                return null;
            default:
                ((h9.h) this.f183l).Y(jVar, (StringBuilder) obj, true);
                return x6.t0.f22605a;
        }
    }

    public void v0(long j10) throws IOException {
        long jW0 = w0();
        if (jW0 != j10) {
            if (jW0 != -1) {
                if (jW0 != -2) {
                    return;
                } else {
                    jW0 = -2;
                }
            }
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "expected non-string scope or scope ", " but found ");
            sbR.append(jW0);
            throw new IOException(sbR.toString());
        }
    }

    @Override // i2.g
    public void w(int i10) {
        androidx.tv.foundation.lazy.list.m0 m0Var = (androidx.tv.foundation.lazy.list.m0) this.f183l;
        androidx.tv.foundation.lazy.grid.f0 f0Var = m0Var.f5429c;
        f0Var.b(i10, 0);
        f0Var.f5235e = null;
        androidx.tv.foundation.lazy.list.k kVar = m0Var.f5442q;
        ((LinkedHashSet) kVar.f5411e).clear();
        kVar.f5412f = i2.j.f16053a;
        kVar.f5408b = -1;
        Remeasurement remeasurement = m0Var.f5439n;
        if (remeasurement != null) {
            remeasurement.forceRemeasure();
        }
    }

    public long w0() {
        ArrayDeque arrayDeque = (ArrayDeque) this.f183l;
        if (arrayDeque.isEmpty()) {
            return 0L;
        }
        return ((Long) arrayDeque.peek()).longValue();
    }

    @Override // com.google.firebase.encoders.a
    public String x(Object obj) {
        StringWriter stringWriter = new StringWriter();
        try {
            i5.d dVar = (i5.d) this.f183l;
            i5.e eVar = new i5.e(stringWriter, dVar.f16222a, dVar.f16223b, dVar.f16224c, dVar.f16225d);
            eVar.h(obj);
            eVar.j();
            eVar.f16227b.flush();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    @Override // i2.g
    public int y() {
        return 100;
    }

    @Override // androidx.appcompat.widget.x2
    public void z(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        ((androidx.appcompat.view.menu.k) this.f183l).f1318p.removeCallbacksAndMessages(qVar);
    }

    public /* synthetic */ e(Object obj, Object obj2, int i10) {
        this.f182i = i10;
        this.f183l = obj2;
    }

    /* JADX INFO: renamed from: clone, reason: collision with other method in class */
    public Object m1clone() {
        switch (this.f182i) {
            case 28:
                return new e(28);
            default:
                return super.clone();
        }
    }

    public e(int i10) {
        this.f182i = i10;
        switch (i10) {
            case 9:
                this.f183l = new ArrayDeque(16);
                break;
            case 12:
                this.f183l = null;
                break;
            case 16:
                this.f183l = new ConcurrentHashMap(16);
                break;
            case 23:
                this.f183l = new io.sentry.android.core.r0(io.sentry.u2.f17942i);
                break;
            case 27:
                this.f183l = new io.sentry.util.k(new io.sentry.android.core.o0(9));
                break;
            case 28:
                this.f183l = new io.sentry.util.b();
                break;
            case 29:
                this.f183l = new io.sentry.transport.q();
                break;
        }
    }

    public e(TextView textView) {
        this.f182i = 14;
        this.f183l = new e1.g(textView);
    }

    public e(EditText editText) {
        this.f182i = 13;
        this.f183l = new io.sentry.internal.debugmeta.c(editText);
    }

    public e(InputStream inputStream) {
        this.f182i = 17;
        this.f183l = new eb.j(inputStream, kotlin.text.a.f19924a);
    }

    public void P() {
    }

    public void a0() {
    }

    public void b0() {
    }

    public void i0() {
    }

    public void j0() {
    }

    public void Q(String str) {
    }

    public void S(int i10) {
    }

    public void U(int i10) {
    }

    public void X(int i10) {
    }

    public void Y(int i10) {
    }

    public void Z(int i10) {
    }

    public void c0(int i10) {
    }

    public void d0(int i10) {
    }

    public void e0(int i10) {
    }

    public void g0(int i10) {
    }

    public void k0(int i10) {
    }

    public void m0(int i10) {
    }

    public void p0(int i10) {
    }

    public void W(int i10, int i11) {
    }

    public void l0(int i10, boolean z) {
    }

    public void o0(int i10, boolean z) {
    }

    public void n0(int i10, int i11, int i12) {
    }

    public void q0(int i10, int i11, int i12) {
    }
}
