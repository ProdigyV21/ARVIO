package androidx.work.impl.model;

import android.os.Build;
import androidx.work.impl.WorkDatabase;
import h.f0;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends androidx.appcompat.view.menu.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6907e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(WorkDatabase workDatabase, int i10) {
        super(workDatabase);
        this.f6907e = i10;
    }

    @Override // androidx.appcompat.view.menu.e
    public final String d() {
        switch (this.f6907e) {
            case 0:
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            case 1:
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            case 2:
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            case 3:
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            case 4:
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            case 5:
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            default:
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
        }
    }

    public final void i(c2.g gVar, Object obj) throws Throwable {
        int i10;
        int i11;
        byte[] byteArray;
        switch (this.f6907e) {
            case 0:
                a aVar = (a) obj;
                String str = aVar.f6905a;
                if (str == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str);
                }
                String str2 = aVar.f6906b;
                if (str2 == null) {
                    gVar.C0(2);
                    return;
                } else {
                    gVar.e0(2, str2);
                    return;
                }
            case 1:
                d dVar = (d) obj;
                gVar.e0(1, dVar.f6908a);
                gVar.n0(2, dVar.f6909b.longValue());
                return;
            case 2:
                String str3 = ((g) obj).f6910a;
                if (str3 == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str3);
                }
                gVar.n0(2, r13.f6911b);
                gVar.n0(3, r13.f6912c);
                return;
            case 3:
                k kVar = (k) obj;
                String str4 = kVar.f6916a;
                if (str4 == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str4);
                }
                String str5 = kVar.f6917b;
                if (str5 == null) {
                    gVar.C0(2);
                    return;
                } else {
                    gVar.e0(2, str5);
                    return;
                }
            case 4:
                m mVar = (m) obj;
                String str6 = mVar.f6918a;
                if (str6 == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str6);
                }
                byte[] bArrB = m2.h.b(mVar.f6919b);
                if (bArrB == null) {
                    gVar.C0(2);
                    return;
                } else {
                    gVar.o0(2, bArrB);
                    return;
                }
            case 5:
                p pVar = (p) obj;
                String str7 = pVar.f6922a;
                int i12 = 1;
                if (str7 == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str7);
                }
                gVar.n0(2, ac.b.W(pVar.f6923b));
                String str8 = pVar.f6924c;
                if (str8 == null) {
                    gVar.C0(3);
                } else {
                    gVar.e0(3, str8);
                }
                String str9 = pVar.f6925d;
                if (str9 == null) {
                    gVar.C0(4);
                } else {
                    gVar.e0(4, str9);
                }
                byte[] bArrB2 = m2.h.b(pVar.f6926e);
                if (bArrB2 == null) {
                    gVar.C0(5);
                } else {
                    gVar.o0(5, bArrB2);
                }
                byte[] bArrB3 = m2.h.b(pVar.f6927f);
                if (bArrB3 == null) {
                    gVar.C0(6);
                } else {
                    gVar.o0(6, bArrB3);
                }
                gVar.n0(7, pVar.f6928g);
                gVar.n0(8, pVar.f6929h);
                gVar.n0(9, pVar.f6930i);
                gVar.n0(10, pVar.k);
                int iC = f0.c(pVar.f6932l);
                if (iC == 0) {
                    i10 = 0;
                } else {
                    if (iC != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i10 = 1;
                }
                gVar.n0(11, i10);
                gVar.n0(12, pVar.f6933m);
                gVar.n0(13, pVar.f6934n);
                gVar.n0(14, pVar.f6935o);
                gVar.n0(15, pVar.f6936p);
                gVar.n0(16, pVar.f6937q ? 1L : 0L);
                int iC2 = f0.c(pVar.f6938r);
                if (iC2 == 0) {
                    i12 = 0;
                } else if (iC2 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                gVar.n0(17, i12);
                gVar.n0(18, pVar.f6939s);
                gVar.n0(19, pVar.f6940t);
                gVar.n0(20, pVar.f6941u);
                gVar.n0(21, pVar.f6942v);
                gVar.n0(22, pVar.f6943w);
                m2.f fVar = pVar.f6931j;
                if (fVar == null) {
                    gVar.C0(23);
                    gVar.C0(24);
                    gVar.C0(25);
                    gVar.C0(26);
                    gVar.C0(27);
                    gVar.C0(28);
                    gVar.C0(29);
                    gVar.C0(30);
                    return;
                }
                int i13 = fVar.f20188a;
                int iC3 = f0.c(i13);
                if (iC3 != 0) {
                    i11 = 1;
                    if (iC3 != 1) {
                        i11 = 2;
                        if (iC3 != 2) {
                            i11 = 3;
                            if (iC3 != 3) {
                                i11 = 4;
                                if (iC3 != 4) {
                                    if (Build.VERSION.SDK_INT < 30 || i13 != 6) {
                                        throw new IllegalArgumentException("Could not convert " + g8.b.t(i13) + " to int");
                                    }
                                    i11 = 5;
                                }
                            }
                        }
                    }
                } else {
                    i11 = 0;
                }
                gVar.n0(23, i11);
                gVar.n0(24, fVar.f20189b ? 1L : 0L);
                gVar.n0(25, fVar.f20190c ? 1L : 0L);
                gVar.n0(26, fVar.f20191d ? 1L : 0L);
                gVar.n0(27, fVar.f20192e ? 1L : 0L);
                gVar.n0(28, fVar.f20193f);
                gVar.n0(29, fVar.f20194g);
                Set<m2.e> set = fVar.f20195h;
                if (set.isEmpty()) {
                    byteArray = new byte[0];
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        try {
                            objectOutputStream.writeInt(set.size());
                            for (m2.e eVar : set) {
                                objectOutputStream.writeUTF(eVar.f20185a.toString());
                                objectOutputStream.writeBoolean(eVar.f20186b);
                                break;
                            }
                            objectOutputStream.close();
                            byteArrayOutputStream.close();
                            byteArray = byteArrayOutputStream.toByteArray();
                        } finally {
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            xc.d.L(byteArrayOutputStream, th);
                            throw th2;
                        }
                    }
                }
                gVar.o0(30, byteArray);
                return;
            default:
                s sVar = (s) obj;
                String str10 = sVar.f6957a;
                if (str10 == null) {
                    gVar.C0(1);
                } else {
                    gVar.e0(1, str10);
                }
                String str11 = sVar.f6958b;
                if (str11 == null) {
                    gVar.C0(2);
                    return;
                } else {
                    gVar.e0(2, str11);
                    return;
                }
        }
    }

    public final void j(Object obj) {
        c2.g gVarC = c();
        try {
            i(gVarC, obj);
            gVarC.Z();
        } finally {
            h(gVarC);
        }
    }
}
