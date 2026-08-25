package j8;

import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import g8.w;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements w.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v9.v0 f19349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g8.k f19350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f19351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g8.p f19352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g8.w f19353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f19354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List f19355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f19356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g8.s0 f19357i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public g8.s0 f19358j;
    public v9.w k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f9.f f19359l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f19360m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f19361n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f19362o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f19363p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f19364q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public kotlin.collections.z f19365r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h8.h f19366s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f19367t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final LinkedHashMap f19368u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Boolean f19369v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f19370w;
    public final /* synthetic */ w x;

    public v(w wVar, v9.v0 v0Var, g8.k kVar, int i10, g8.p pVar, int i11, List list, List list2, g8.s0 s0Var, v9.w wVar2) {
        if (v0Var == null) {
            s(0);
            throw null;
        }
        if (kVar == null) {
            s(1);
            throw null;
        }
        if (i10 == 0) {
            s(2);
            throw null;
        }
        if (pVar == null) {
            s(3);
            throw null;
        }
        if (i11 == 0) {
            s(4);
            throw null;
        }
        if (list == null) {
            s(5);
            throw null;
        }
        if (list2 == null) {
            s(6);
            throw null;
        }
        if (wVar2 == null) {
            s(7);
            throw null;
        }
        this.x = wVar;
        this.f19353e = null;
        this.f19358j = wVar.f19382t;
        this.f19360m = true;
        this.f19361n = false;
        this.f19362o = false;
        this.f19363p = false;
        this.f19364q = wVar.D;
        this.f19365r = null;
        this.f19366s = null;
        this.f19367t = wVar.E;
        this.f19368u = new LinkedHashMap();
        this.f19369v = null;
        this.f19370w = false;
        this.f19349a = v0Var;
        this.f19350b = kVar;
        this.f19351c = i10;
        this.f19352d = pVar;
        this.f19354f = i11;
        this.f19355g = list;
        this.f19356h = list2;
        this.f19357i = s0Var;
        this.k = wVar2;
        this.f19359l = null;
    }

    public static /* synthetic */ void s(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                i11 = 2;
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "newOwner";
                break;
            case 2:
                objArr[0] = "newModality";
                break;
            case 3:
                objArr[0] = "newVisibility";
                break;
            case 4:
            case 14:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = "newValueParameterDescriptors";
                break;
            case 6:
                objArr[0] = "newContextReceiverParameters";
                break;
            case 7:
                objArr[0] = "newReturnType";
                break;
            case 8:
                objArr[0] = "owner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
            case 10:
                objArr[0] = "modality";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 17:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 19:
            case 21:
                objArr[0] = "parameters";
                break;
            case 23:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 25:
                objArr[0] = "contextReceiverParameters";
                break;
            case 35:
                objArr[0] = "additionalAnnotations";
                break;
            case 37:
            default:
                objArr[0] = "substitution";
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                objArr[0] = "userDataKey";
                break;
        }
        switch (i10) {
            case 9:
                objArr[1] = "setOwner";
                break;
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
            case 11:
                objArr[1] = "setModality";
                break;
            case 13:
                objArr[1] = "setVisibility";
                break;
            case 15:
                objArr[1] = "setKind";
                break;
            case 16:
                objArr[1] = "setCopyOverrides";
                break;
            case 18:
                objArr[1] = "setName";
                break;
            case 20:
                objArr[1] = "setValueParameters";
                break;
            case 22:
                objArr[1] = "setTypeParameters";
                break;
            case 24:
                objArr[1] = "setReturnType";
                break;
            case 26:
                objArr[1] = "setContextReceiverParameters";
                break;
            case 27:
                objArr[1] = "setExtensionReceiverParameter";
                break;
            case 28:
                objArr[1] = "setDispatchReceiverParameter";
                break;
            case 29:
                objArr[1] = "setOriginal";
                break;
            case 30:
                objArr[1] = "setSignatureChange";
                break;
            case 31:
                objArr[1] = "setPreserveSourceElement";
                break;
            case 32:
                objArr[1] = "setDropOriginalInContainingParts";
                break;
            case 33:
                objArr[1] = "setHiddenToOvercomeSignatureClash";
                break;
            case 34:
                objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                break;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                objArr[1] = "setAdditionalAnnotations";
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                objArr[1] = "setSubstitution";
                break;
            case 40:
                objArr[1] = "putUserData";
                break;
            case 41:
                objArr[1] = "getSubstitution";
                break;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                objArr[1] = "setJustForTypeSubstitution";
                break;
        }
        switch (i10) {
            case 8:
                objArr[2] = "setOwner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                break;
            case 10:
                objArr[2] = "setModality";
                break;
            case 12:
                objArr[2] = "setVisibility";
                break;
            case 14:
                objArr[2] = "setKind";
                break;
            case 17:
                objArr[2] = "setName";
                break;
            case 19:
                objArr[2] = "setValueParameters";
                break;
            case 21:
                objArr[2] = "setTypeParameters";
                break;
            case 23:
                objArr[2] = "setReturnType";
                break;
            case 25:
                objArr[2] = "setContextReceiverParameters";
                break;
            case 35:
                objArr[2] = "setAdditionalAnnotations";
                break;
            case 37:
                objArr[2] = "setSubstitution";
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                objArr[2] = "putUserData";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                throw new IllegalStateException(str2);
            case 10:
            case 12:
            case 14:
            case 17:
            case 19:
            case 21:
            case 23:
            case 25:
            case 35:
            case 37:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // g8.w.a
    public final w.a a(List list) {
        this.f19355g = list;
        return this;
    }

    @Override // g8.w.a
    public final w.a b(int i10) {
        if (i10 != 0) {
            this.f19354f = i10;
            return this;
        }
        s(14);
        throw null;
    }

    @Override // g8.w.a
    public final g8.w build() {
        return this.x.J0(this);
    }

    @Override // g8.w.a
    public final w.a c(v9.v0 v0Var) {
        if (v0Var != null) {
            this.f19349a = v0Var;
            return this;
        }
        s(37);
        throw null;
    }

    @Override // g8.w.a
    public final w.a d(g8.e eVar) {
        this.f19353e = eVar;
        return this;
    }

    @Override // g8.w.a
    public final w.a e() {
        this.f19367t = true;
        return this;
    }

    @Override // g8.w.a
    public final w.a f() {
        this.f19360m = false;
        return this;
    }

    @Override // g8.w.a
    public final w.a g() {
        this.f19365r = kotlin.collections.z.f19728i;
        return this;
    }

    @Override // g8.w.a
    public final w.a h() {
        this.f19364q = true;
        return this;
    }

    @Override // g8.w.a
    public final w.a i(g8.s0 s0Var) {
        this.f19358j = s0Var;
        return this;
    }

    @Override // g8.w.a
    public final w.a j(v9.w wVar) {
        if (wVar != null) {
            this.k = wVar;
            return this;
        }
        s(23);
        throw null;
    }

    @Override // g8.w.a
    public final w.a k(int i10) {
        if (i10 != 0) {
            this.f19351c = i10;
            return this;
        }
        s(10);
        throw null;
    }

    @Override // g8.w.a
    public final w.a l() {
        this.f19362o = true;
        return this;
    }

    @Override // g8.w.a
    public final w.a m() {
        this.f19368u.put(r8.g.R, Boolean.TRUE);
        return this;
    }

    @Override // g8.w.a
    public final w.a n(g8.p pVar) {
        if (pVar != null) {
            this.f19352d = pVar;
            return this;
        }
        s(12);
        throw null;
    }

    @Override // g8.w.a
    public final w.a o(h8.h hVar) {
        if (hVar != null) {
            this.f19366s = hVar;
            return this;
        }
        s(35);
        throw null;
    }

    @Override // g8.w.a
    public final w.a p(g8.k kVar) {
        if (kVar != null) {
            this.f19350b = kVar;
            return this;
        }
        s(8);
        throw null;
    }

    @Override // g8.w.a
    public final w.a q(f9.f fVar) {
        if (fVar != null) {
            this.f19359l = fVar;
            return this;
        }
        s(17);
        throw null;
    }

    @Override // g8.w.a
    public final w.a r() {
        this.f19361n = true;
        return this;
    }
}
