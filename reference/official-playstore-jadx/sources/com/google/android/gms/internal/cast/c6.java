package com.google.android.gms.internal.cast;

import androidx.media3.session.MediaUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class c6 extends pa implements jb {
    private static final c6 zzaz;
    private xa zzA;
    private xa zzB;
    private g8 zzC;
    private int zzD;
    private int zzE;
    private w6 zzF;
    private int zzG;
    private a6 zzH;
    private xa zzI;
    private w6 zzJ;
    private int zzK;
    private int zzL;
    private int zzM;
    private int zzN;
    private int zzO;
    private int zzP;
    private y8 zzQ;
    private w5 zzR;
    private k6 zzS;
    private n5 zzT;
    private k7 zzU;
    private f8 zzV;
    private n7 zzW;
    private xa zzX;
    private m7 zzY;
    private int zzZ;
    private s7 zzaa;
    private xa zzab;
    private boolean zzac;
    private boolean zzad;
    private int zzae;
    private p5 zzaf;
    private w7 zzag;
    private g7 zzah;
    private r6 zzai;
    private r7 zzaj;
    private l8 zzak;
    private s6 zzal;
    private int zzam;
    private int zzan;
    private int zzao;
    private xa zzap;
    private b9 zzaq;
    private m8 zzar;
    private k8 zzas;
    private y5 zzat;
    private r8 zzau;
    private d8 zzav;
    private l7 zzaw;
    private d7 zzax;
    private int zzb;
    private int zzd;
    private long zze;
    private long zzf;
    private int zzg;
    private e7 zzh;
    private v7 zzi;
    private b7 zzj;
    private z6 zzk;
    private z5 zzl;
    private u7 zzm;
    private r5 zzn;
    private u8 zzo;
    private y6 zzq;
    private f5 zzr;
    private int zzu;
    private q7 zzv;
    private xa zzy;
    private xa zzz;
    private byte zzay = 2;
    private String zzp = "";
    private String zzs = "";
    private String zzt = "";
    private String zzw = "";
    private va zzx = qa.f13563o;

    static {
        c6 c6Var = new c6();
        zzaz = c6Var;
        pa.i(c6.class, c6Var);
    }

    public c6() {
        pb pbVar = pb.f13547o;
        this.zzy = pbVar;
        this.zzz = pbVar;
        this.zzA = pbVar;
        this.zzB = pbVar;
        this.zzI = pbVar;
        this.zzX = pbVar;
        this.zzab = pbVar;
        this.zzap = pbVar;
    }

    public static b6 o() {
        return (b6) zzaz.e();
    }

    public static b6 p(c6 c6Var) {
        oa oaVarE = zzaz.e();
        pa paVar = oaVarE.f13529i;
        if (!paVar.equals(c6Var)) {
            if (!oaVarE.f13530l.l()) {
                pa paVar2 = (pa) paVar.k(4, null);
                ob.f13531c.a(paVar2.getClass()).c(paVar2, oaVarE.f13530l);
                oaVarE.f13530l = paVar2;
            }
            pa paVar3 = oaVarE.f13530l;
            ob.f13531c.a(paVar3.getClass()).c(paVar3, c6Var);
        }
        return (b6) oaVarE;
    }

    public final /* synthetic */ void A(p5 p5Var) {
        this.zzaf = p5Var;
        this.zzd |= 8192;
    }

    public final /* synthetic */ void B(k8 k8Var) {
        this.zzas = k8Var;
        this.zzd |= 33554432;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return Byte.valueOf(this.zzay);
        }
        if (i11 == 2) {
            return new qb(zzaz, "\u0001H\u0000\u0002\u0001HH\u0000\t\u0001\u0001ဂ\u0000\u0002ဂ\u0001\u0003᠌\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဈ\u000e\nဉ\b\u000bဉ\t\fဉ\n\rဈ\u000b\u000eဉ\f\u000fဉ\r\u0010ဉ\u0011\u0011ဈ\u0012\u0012\u0016\u0013\u001b\u0014\u001b\u0015\u001b\u0016\u001b\u0017᠌\u0014\u0018ဉ\u0018\u0019\u001b\u001aဉ\u0019\u001b᠌\u001b\u001cင\u001c\u001dင\u001d\u001eင\u001e\u001fဆ\u001f ဉ !ဉ!\"ဉ##᠌\u0015$ဉ\u0016%ᐉ$&ဉ%'ဉ&(\u001b)᠌(*ဉ)+\u001b,᠌\u001a-ဇ*.ဇ+/᠌,0ဉ-1င\u00172ဉ.3ဉ/4ဉ15ဉ26ဉ37᠌48᠌59᠌6:\u001b;ဈ\u000f<ဉ7=ဉ0>ဉ\u0013?ဉ\"@င\u0010Aဉ8Bဉ'Cဉ9Dဉ:Eဉ;Fဉ<Gဉ=Hဉ>", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", k5.f13378n, "zzh", "zzi", "zzj", "zzk", "zzl", "zzs", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzv", "zzw", "zzx", "zzy", f7.class, "zzz", i7.class, "zzA", t6.class, "zzB", h8.class, "zzD", h5.I, "zzH", "zzI", w6.class, "zzJ", "zzL", h5.f13288p, "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzT", "zzE", h5.G, "zzF", "zzU", "zzV", "zzW", "zzX", t5.class, "zzZ", h5.H, "zzaa", "zzab", x5.class, "zzK", h5.f13287o, "zzac", "zzad", "zzae", h5.f13285m, "zzaf", "zzG", "zzag", "zzah", "zzaj", "zzak", "zzal", "zzam", z7.G, "zzan", h5.z, "zzao", z7.F, "zzap", s8.class, "zzt", "zzaq", "zzai", "zzC", "zzS", "zzu", "zzar", "zzY", "zzas", "zzat", "zzau", "zzav", "zzaw", "zzax"});
        }
        if (i11 == 3) {
            return new c6();
        }
        if (i11 == 4) {
            return new b6(zzaz);
        }
        if (i11 == 5) {
            return zzaz;
        }
        this.zzay = paVar == null ? (byte) 0 : (byte) 1;
        return null;
    }

    public final w5 n() {
        w5 w5Var = this.zzR;
        return w5Var == null ? w5.p() : w5Var;
    }

    public final /* synthetic */ void q(long j10) {
        this.zzb |= 2;
        this.zzf = j10;
    }

    public final /* synthetic */ void r(String str) {
        str.getClass();
        this.zzb |= 2048;
        this.zzp = str;
    }

    public final /* synthetic */ void s(String str) {
        str.getClass();
        this.zzb |= 16384;
        this.zzs = str;
    }

    public final /* synthetic */ void t(String str) {
        str.getClass();
        this.zzb |= 32768;
        this.zzt = str;
    }

    public final /* synthetic */ void u(int i10) {
        this.zzb |= 65536;
        this.zzu = i10;
    }

    public final /* synthetic */ void v(String str) {
        this.zzb |= MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES;
        this.zzw = str;
    }

    public final /* synthetic */ void w(int i10) {
        this.zzb |= Integer.MIN_VALUE;
        this.zzP = i10;
    }

    public final /* synthetic */ void x(w5 w5Var) {
        this.zzR = w5Var;
        this.zzd |= 2;
    }

    public final /* synthetic */ void y(k6 k6Var) {
        this.zzS = k6Var;
        this.zzd |= 4;
    }

    public final void z(t5 t5Var) {
        xa xaVar = this.zzX;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzX = xaVar.zzf(size + size);
        }
        this.zzX.add(t5Var);
    }
}
