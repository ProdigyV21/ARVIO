package d8;

import androidx.compose.material3.MenuKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.util.Constants;
import com.google.android.gms.cast.MediaError;
import g8.h0;
import g8.p0;
import g8.r0;
import g8.y;
import io.ktor.http.LinkHeader;
import j8.d0;
import j8.m0;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import v9.b0;
import v9.b1;
import v9.d1;
import v9.o0;
import v9.t0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f9.f f14705e = f9.f.l("<built-ins module>");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d0 f14706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u9.s f14707b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u9.q f14708c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.p f14709d;

    public k(u9.p pVar) {
        this.f14709d = pVar;
        this.f14707b = new u9.l(pVar, new h(this, 0));
        this.f14708c = pVar.g(new i(this, 0));
    }

    public static boolean A(w wVar, f9.e eVar) {
        if (wVar == null) {
            a(98);
            throw null;
        }
        if (eVar != null) {
            return I(wVar.I0(), eVar);
        }
        a(99);
        throw null;
    }

    public static boolean B(w wVar, f9.e eVar) {
        if (eVar != null) {
            return A(wVar, eVar) && !wVar.J0();
        }
        a(TsExtractor.TS_STREAM_TYPE_DTS_HD);
        throw null;
    }

    public static boolean C(g8.w wVar) {
        if (wVar.a().getAnnotations().x(p.f14744m)) {
            return true;
        }
        if (!(wVar instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) wVar;
        boolean zK = p0Var.K();
        m0 getter = p0Var.getGetter();
        r0 setter = p0Var.getSetter();
        if (getter == null || !C(getter)) {
            return false;
        }
        if (zK) {
            return setter != null && C(setter);
        }
        return true;
    }

    public static boolean D(w wVar, f9.e eVar) {
        if (wVar == null) {
            a(106);
            throw null;
        }
        if (eVar != null) {
            return !wVar.J0() && A(wVar, eVar);
        }
        a(107);
        throw null;
    }

    public static boolean E(w wVar) {
        if (wVar == null) {
            a(137);
            throw null;
        }
        if (wVar != null) {
            return A(wVar, p.f14734b) && !b1.e(wVar);
        }
        a(TsExtractor.TS_STREAM_TYPE_DTS_UHD);
        throw null;
    }

    public static boolean F(w wVar) {
        if (wVar != null) {
            g8.h hVarE = wVar.I0().e();
            return (hVarE == null || r(hVarE) == null) ? false : true;
        }
        a(92);
        throw null;
    }

    public static boolean G(w wVar) {
        if (wVar == null) {
            a(95);
            throw null;
        }
        if (wVar.J0()) {
            return false;
        }
        g8.h hVarE = wVar.I0().e();
        return (hVarE instanceof g8.f) && t((g8.f) hVarE) != null;
    }

    public static boolean H(w wVar) {
        return D(wVar, p.f14738f);
    }

    public static boolean I(o0 o0Var, f9.e eVar) {
        if (o0Var == null) {
            a(MediaError.DetailedErrorCode.MEDIA_DECODE);
            throw null;
        }
        if (eVar != null) {
            g8.h hVarE = o0Var.e();
            return (hVarE instanceof g8.f) && b((g8.f) hVarE, eVar);
        }
        a(MediaError.DetailedErrorCode.MEDIA_NETWORK);
        throw null;
    }

    public static boolean J(g8.h hVar) {
        if (hVar == null) {
            a(10);
            throw null;
        }
        for (g8.k kVarD = hVar; kVarD != null; kVarD = kVarD.d()) {
            if (kVarD instanceof h0) {
                return ((h0) kVarD).c().h(q.k);
            }
        }
        return false;
    }

    public static /* synthetic */ void a(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 37:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 44:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 64:
            case 65:
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 47:
            case 54:
            case 68:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
            case 83:
            case 84:
            case 86:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 37:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 44:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 64:
            case 65:
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                i11 = 2;
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 47:
            case 54:
            case 68:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
            case 83:
            case 84:
            case 86:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
            case 73:
                objArr[0] = "module";
                break;
            case 2:
                objArr[0] = "computation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 37:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 44:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 64:
            case 65:
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
            case 9:
            case 10:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
            case Constants.WATCHED_THRESHOLD /* 90 */:
            case 97:
            case MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED /* 104 */:
            case 108:
            case 109:
            case 144:
            case 147:
            case 148:
            case 150:
            case 158:
            case 159:
            case 160:
            case 161:
                objArr[0] = "descriptor";
                break;
            case 12:
            case 99:
            case 101:
            case MediaError.DetailedErrorCode.MEDIA_NETWORK /* 103 */:
            case 105:
            case 107:
            case TsExtractor.TS_STREAM_TYPE_DTS_HD /* 136 */:
                objArr[0] = "fqName";
                break;
            case 14:
                objArr[0] = "simpleName";
                break;
            case 16:
            case 17:
            case 54:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 98:
            case 100:
            case 106:
            case MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE /* 110 */:
            case 111:
            case 112:
            case 114:
            case 115:
            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
            case 117:
            case 118:
            case 119:
            case MenuKt.InTransitionDuration /* 120 */:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
            case 131:
            case 132:
            case 133:
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
            case TsExtractor.TS_STREAM_TYPE_E_AC3 /* 135 */:
            case 137:
            case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
            case TsExtractor.TS_STREAM_TYPE_DTS_UHD /* 139 */:
            case 140:
            case 141:
            case 142:
            case 143:
            case 145:
            case 146:
            case 149:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 163:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 47:
                objArr[0] = "classSimpleName";
                break;
            case 68:
                objArr[0] = "arrayType";
                break;
            case 72:
                objArr[0] = "notNullArrayType";
                break;
            case 74:
                objArr[0] = "primitiveType";
                break;
            case 76:
                objArr[0] = "kotlinType";
                break;
            case 79:
            case 83:
                objArr[0] = "projectionType";
                break;
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 84:
            case 86:
                objArr[0] = "argument";
                break;
            case 81:
                objArr[0] = "annotations";
                break;
            case MediaError.DetailedErrorCode.MEDIA_DECODE /* 102 */:
                objArr[0] = "typeConstructor";
                break;
            case 113:
                objArr[0] = "classDescriptor";
                break;
            case 162:
                objArr[0] = "declarationDescriptor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i10) {
            case 3:
                objArr[1] = "getAdditionalClassPartsProvider";
                break;
            case 4:
                objArr[1] = "getPlatformDependentDeclarationFilter";
                break;
            case 5:
                objArr[1] = "getClassDescriptorFactories";
                break;
            case 6:
                objArr[1] = "getStorageManager";
                break;
            case 7:
                objArr[1] = "getBuiltInsModule";
                break;
            case 8:
                objArr[1] = "getBuiltInPackagesImportedByDefault";
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 47:
            case 54:
            case 68:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
            case 83:
            case 84:
            case 86:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
            case 11:
                objArr[1] = "getBuiltInsPackageScope";
                break;
            case 13:
                objArr[1] = "getBuiltInClassByFqName";
                break;
            case 15:
                objArr[1] = "getBuiltInClassByName";
                break;
            case 18:
                objArr[1] = "getSuspendFunction";
                break;
            case 19:
                objArr[1] = "getKFunction";
                break;
            case 20:
                objArr[1] = "getKSuspendFunction";
                break;
            case 21:
                objArr[1] = "getKClass";
                break;
            case 22:
                objArr[1] = "getKType";
                break;
            case 23:
                objArr[1] = "getKCallable";
                break;
            case 24:
                objArr[1] = "getKProperty";
                break;
            case 25:
                objArr[1] = "getKProperty0";
                break;
            case 26:
                objArr[1] = "getKProperty1";
                break;
            case 27:
                objArr[1] = "getKProperty2";
                break;
            case 28:
                objArr[1] = "getKMutableProperty0";
                break;
            case 29:
                objArr[1] = "getKMutableProperty1";
                break;
            case 30:
                objArr[1] = "getKMutableProperty2";
                break;
            case 31:
                objArr[1] = "getIterator";
                break;
            case 32:
                objArr[1] = "getIterable";
                break;
            case 33:
                objArr[1] = "getMutableIterable";
                break;
            case 34:
                objArr[1] = "getMutableIterator";
                break;
            case 35:
                objArr[1] = "getCollection";
                break;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                objArr[1] = "getMutableCollection";
                break;
            case 37:
                objArr[1] = "getList";
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                objArr[1] = "getMutableList";
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                objArr[1] = "getSet";
                break;
            case 40:
                objArr[1] = "getMutableSet";
                break;
            case 41:
                objArr[1] = "getMap";
                break;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                objArr[1] = "getMutableMap";
                break;
            case 43:
                objArr[1] = "getMapEntry";
                break;
            case 44:
                objArr[1] = "getMutableMapEntry";
                break;
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                objArr[1] = "getListIterator";
                break;
            case 46:
                objArr[1] = "getMutableListIterator";
                break;
            case 48:
                objArr[1] = "getBuiltInTypeByClassName";
                break;
            case 49:
                objArr[1] = "getNothingType";
                break;
            case 50:
                objArr[1] = "getNullableNothingType";
                break;
            case 51:
                objArr[1] = "getAnyType";
                break;
            case 52:
                objArr[1] = "getNullableAnyType";
                break;
            case 53:
                objArr[1] = "getDefaultBound";
                break;
            case 55:
                objArr[1] = "getPrimitiveKotlinType";
                break;
            case 56:
                objArr[1] = "getNumberType";
                break;
            case 57:
                objArr[1] = "getByteType";
                break;
            case 58:
                objArr[1] = "getShortType";
                break;
            case 59:
                objArr[1] = "getIntType";
                break;
            case 60:
                objArr[1] = "getLongType";
                break;
            case 61:
                objArr[1] = "getFloatType";
                break;
            case 62:
                objArr[1] = "getDoubleType";
                break;
            case ColorSpace.MaxId /* 63 */:
                objArr[1] = "getCharType";
                break;
            case 64:
                objArr[1] = "getBooleanType";
                break;
            case 65:
                objArr[1] = "getUnitType";
                break;
            case 66:
                objArr[1] = "getStringType";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                objArr[1] = "getIterableType";
                break;
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                objArr[1] = "getArrayElementType";
                break;
            case 75:
                objArr[1] = "getPrimitiveArrayKotlinType";
                break;
            case 82:
            case 85:
                objArr[1] = "getArrayType";
                break;
            case 87:
                objArr[1] = "getEnumType";
                break;
            case 88:
                objArr[1] = "getAnnotationType";
                break;
        }
        switch (i10) {
            case 1:
                objArr[2] = "setBuiltInsModule";
                break;
            case 2:
                objArr[2] = "setPostponedBuiltinsModuleComputation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 37:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 44:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 64:
            case 65:
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                break;
            case 9:
                objArr[2] = "isBuiltIn";
                break;
            case 10:
                objArr[2] = "isUnderKotlinPackage";
                break;
            case 12:
                objArr[2] = "getBuiltInClassByFqName";
                break;
            case 14:
                objArr[2] = "getBuiltInClassByName";
                break;
            case 16:
                objArr[2] = "getPrimitiveClassDescriptor";
                break;
            case 17:
                objArr[2] = "getPrimitiveArrayClassDescriptor";
                break;
            case 47:
                objArr[2] = "getBuiltInTypeByClassName";
                break;
            case 54:
                objArr[2] = "getPrimitiveKotlinType";
                break;
            case 68:
                objArr[2] = "getArrayElementType";
                break;
            case 72:
            case 73:
                objArr[2] = "getElementTypeForUnsignedArray";
                break;
            case 74:
                objArr[2] = "getPrimitiveArrayKotlinType";
                break;
            case 76:
                objArr[2] = "getPrimitiveArrayKotlinTypeByPrimitiveKotlinType";
                break;
            case 77:
            case 94:
                objArr[2] = "getPrimitiveType";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                objArr[2] = "getPrimitiveArrayType";
                break;
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
            case 83:
            case 84:
                objArr[2] = "getArrayType";
                break;
            case 86:
                objArr[2] = "getEnumType";
                break;
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
                objArr[2] = "isArray";
                break;
            case Constants.WATCHED_THRESHOLD /* 90 */:
            case 91:
                objArr[2] = "isArrayOrPrimitiveArray";
                break;
            case 92:
                objArr[2] = "isPrimitiveArray";
                break;
            case 93:
                objArr[2] = "getPrimitiveArrayElementType";
                break;
            case 95:
                objArr[2] = "isPrimitiveType";
                break;
            case 96:
                objArr[2] = "isPrimitiveTypeOrNullablePrimitiveType";
                break;
            case 97:
                objArr[2] = "isPrimitiveClass";
                break;
            case 98:
            case 99:
            case 100:
            case 101:
                objArr[2] = "isConstructedFromGivenClass";
                break;
            case MediaError.DetailedErrorCode.MEDIA_DECODE /* 102 */:
            case MediaError.DetailedErrorCode.MEDIA_NETWORK /* 103 */:
                objArr[2] = "isTypeConstructorForGivenClass";
                break;
            case MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED /* 104 */:
            case 105:
                objArr[2] = "classFqNameEquals";
                break;
            case 106:
            case 107:
                objArr[2] = "isNotNullConstructedFromGivenClass";
                break;
            case 108:
                objArr[2] = "isSpecialClassWithNoSupertypes";
                break;
            case 109:
            case MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE /* 110 */:
                objArr[2] = "isAny";
                break;
            case 111:
            case 113:
                objArr[2] = "isBoolean";
                break;
            case 112:
                objArr[2] = "isBooleanOrNullableBoolean";
                break;
            case 114:
                objArr[2] = "isNumber";
                break;
            case 115:
                objArr[2] = "isChar";
                break;
            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                objArr[2] = "isCharOrNullableChar";
                break;
            case 117:
                objArr[2] = "isInt";
                break;
            case 118:
                objArr[2] = "isByte";
                break;
            case 119:
                objArr[2] = "isLong";
                break;
            case MenuKt.InTransitionDuration /* 120 */:
                objArr[2] = "isLongOrNullableLong";
                break;
            case 121:
                objArr[2] = "isShort";
                break;
            case 122:
                objArr[2] = "isFloat";
                break;
            case 123:
                objArr[2] = "isFloatOrNullableFloat";
                break;
            case 124:
                objArr[2] = "isDouble";
                break;
            case 125:
                objArr[2] = "isUByte";
                break;
            case 126:
                objArr[2] = "isUShort";
                break;
            case 127:
                objArr[2] = "isUInt";
                break;
            case 128:
                objArr[2] = "isULong";
                break;
            case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                objArr[2] = "isUByteArray";
                break;
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                objArr[2] = "isUShortArray";
                break;
            case 131:
                objArr[2] = "isUIntArray";
                break;
            case 132:
                objArr[2] = "isULongArray";
                break;
            case 133:
                objArr[2] = "isUnsignedArrayType";
                break;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
                objArr[2] = "isDoubleOrNullableDouble";
                break;
            case TsExtractor.TS_STREAM_TYPE_E_AC3 /* 135 */:
            case TsExtractor.TS_STREAM_TYPE_DTS_HD /* 136 */:
                objArr[2] = "isConstructedFromGivenClassAndNotNullable";
                break;
            case 137:
                objArr[2] = "isNothing";
                break;
            case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
                objArr[2] = "isNullableNothing";
                break;
            case TsExtractor.TS_STREAM_TYPE_DTS_UHD /* 139 */:
                objArr[2] = "isNothingOrNullableNothing";
                break;
            case 140:
                objArr[2] = "isAnyOrNullableAny";
                break;
            case 141:
                objArr[2] = "isNullableAny";
                break;
            case 142:
                objArr[2] = "isDefaultBound";
                break;
            case 143:
                objArr[2] = "isUnit";
                break;
            case 144:
                objArr[2] = "mayReturnNonUnitValue";
                break;
            case 145:
                objArr[2] = "isUnitOrNullableUnit";
                break;
            case 146:
                objArr[2] = "isBooleanOrSubtype";
                break;
            case 147:
                objArr[2] = "isMemberOfAny";
                break;
            case 148:
            case 149:
                objArr[2] = "isEnum";
                break;
            case 150:
            case 151:
                objArr[2] = "isComparable";
                break;
            case 152:
                objArr[2] = "isCollectionOrNullableCollection";
                break;
            case 153:
                objArr[2] = "isListOrNullableList";
                break;
            case 154:
                objArr[2] = "isSetOrNullableSet";
                break;
            case 155:
                objArr[2] = "isMapOrNullableMap";
                break;
            case 156:
                objArr[2] = "isIterableOrNullableIterable";
                break;
            case 157:
                objArr[2] = "isThrowableOrNullableThrowable";
                break;
            case 158:
                objArr[2] = "isThrowable";
                break;
            case 159:
                objArr[2] = "isKClass";
                break;
            case 160:
                objArr[2] = "isNonPrimitiveArray";
                break;
            case 161:
                objArr[2] = "isCloneable";
                break;
            case 162:
                objArr[2] = "isDeprecated";
                break;
            case 163:
                objArr[2] = "isNotNullOrNullableFunctionSupertype";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 37:
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 40:
            case 41:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 44:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 64:
            case 65:
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
            case 69:
            case 70:
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
            case 75:
            case 82:
            case 85:
            case 87:
            case 88:
                throw new IllegalStateException(str2);
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 47:
            case 54:
            case 68:
            case 72:
            case 73:
            case 74:
            case 76:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
            case 83:
            case 84:
            case 86:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static boolean b(g8.f fVar, f9.e eVar) {
        if (fVar == null) {
            a(MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED);
            throw null;
        }
        if (eVar != null) {
            return fVar.getName().equals(eVar.f()) && eVar.equals(i9.f.g(fVar));
        }
        a(105);
        throw null;
    }

    public static m r(g8.h hVar) {
        if (hVar == null) {
            a(78);
            throw null;
        }
        if (p.f14733a0.contains(hVar.getName())) {
            return (m) p.c0.get(i9.f.g(hVar));
        }
        return null;
    }

    public static m t(g8.f fVar) {
        if (p.Z.contains(fVar.getName())) {
            return (m) p.b0.get(i9.f.g(fVar));
        }
        return null;
    }

    public static boolean x(w wVar) {
        if (wVar != null) {
            return A(wVar, p.f14732a);
        }
        a(140);
        throw null;
    }

    public static boolean y(w wVar) {
        if (wVar != null) {
            return A(wVar, p.f14739g);
        }
        a(89);
        throw null;
    }

    public static boolean z(g8.k kVar) {
        if (kVar != null) {
            return i9.f.i(kVar, d.class, false) != null;
        }
        a(9);
        throw null;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, x6.s] */
    public final void c(boolean z) {
        f9.f fVar = f14705e;
        u9.p pVar = this.f14709d;
        d0 d0Var = new d0(fVar, pVar, this, 48);
        this.f14706a = d0Var;
        c.f14696a.getClass();
        d0Var.f19257r = ((c) b.f14695b.getValue()).a(pVar, this.f14706a, l(), p(), d(), z);
        d0 d0Var2 = this.f14706a;
        d0Var2.getClass();
        d0Var2.f19256q = new androidx.work.impl.constraints.j(kotlin.collections.r.o0(new d0[]{d0Var2}));
    }

    public i8.b d() {
        return i8.a.f16238b;
    }

    public final b0 e() {
        b0 b0VarM = j("Any").m();
        if (b0VarM != null) {
            return b0VarM;
        }
        a(51);
        throw null;
    }

    public final w f(w wVar) {
        f9.b bVarF;
        f9.b bVar;
        g8.f fVarD;
        b0 b0VarM = null;
        if (wVar == null) {
            a(68);
            throw null;
        }
        if (y(wVar)) {
            if (wVar.G0().size() != 1) {
                throw new IllegalStateException();
            }
            w type = ((v9.r0) wVar.G0().get(0)).getType();
            if (type != null) {
                return type;
            }
            a(69);
            throw null;
        }
        d1 d1VarG = b1.g(wVar, false);
        w wVar2 = (w) ((j) this.f14707b.invoke()).f14704b.get(d1VarG);
        if (wVar2 != null) {
            return wVar2;
        }
        int i10 = i9.f.f16249a;
        g8.h hVarE = d1VarG.I0().e();
        g8.b0 b0VarE = hVarE == null ? null : i9.f.e(hVarE);
        if (b0VarE != null) {
            g8.h hVarE2 = d1VarG.I0().e();
            if (hVarE2 != null) {
                Set set = u.f14783a;
                if (u.f14787e.contains(hVarE2.getName()) && (bVarF = m9.d.f(hVarE2)) != null && (bVar = (f9.b) u.f14785c.get(bVarF)) != null && (fVarD = y.d(b0VarE, bVar)) != null) {
                    b0VarM = fVarD.m();
                }
            }
            if (b0VarM != null) {
                return b0VarM;
            }
        }
        throw new IllegalStateException("not array: " + wVar);
    }

    public final b0 g(int i10, w wVar, h8.h hVar) {
        if (i10 == 0) {
            a(79);
            throw null;
        }
        if (wVar != null) {
            return v9.e.q(v9.c.t(hVar), j("Array"), Collections.singletonList(new t0(i10, wVar)));
        }
        a(80);
        throw null;
    }

    public final b0 h(d1 d1Var) {
        if (d1Var != null) {
            return g(1, d1Var, h8.g.f15863a);
        }
        a(84);
        throw null;
    }

    public final g8.f i(f9.c cVar) {
        if (cVar == null) {
            a(12);
            throw null;
        }
        g8.f fVarI = y.i(k(), cVar);
        if (fVarI != null) {
            return fVarI;
        }
        a(13);
        throw null;
    }

    public final g8.f j(String str) {
        if (str == null) {
            a(14);
            throw null;
        }
        g8.f fVar = (g8.f) this.f14708c.invoke(f9.f.j(str));
        if (fVar != null) {
            return fVar;
        }
        a(15);
        throw null;
    }

    public final d0 k() {
        this.f14706a.getClass();
        d0 d0Var = this.f14706a;
        if (d0Var != null) {
            return d0Var;
        }
        a(7);
        throw null;
    }

    public Iterable l() {
        List listSingletonList = Collections.singletonList(new e8.a(this.f14709d, k()));
        if (listSingletonList != null) {
            return listSingletonList;
        }
        a(5);
        throw null;
    }

    public final b0 m() {
        b0 b0VarO = o();
        if (b0VarO != null) {
            return b0VarO;
        }
        a(53);
        throw null;
    }

    public final b0 n() {
        b0 b0VarM = j("Nothing").m();
        if (b0VarM != null) {
            return b0VarM;
        }
        a(49);
        throw null;
    }

    public final b0 o() {
        b0 b0VarP0 = e().M0(true);
        if (b0VarP0 != null) {
            return b0VarP0;
        }
        a(52);
        throw null;
    }

    public i8.d p() {
        return i8.a.f16240d;
    }

    public final b0 q(m mVar) {
        if (mVar == null) {
            a(74);
            throw null;
        }
        b0 b0Var = (b0) ((j) this.f14707b.invoke()).f14703a.get(mVar);
        if (b0Var != null) {
            return b0Var;
        }
        a(75);
        throw null;
    }

    public final b0 s(m mVar) {
        if (mVar == null) {
            a(54);
            throw null;
        }
        if (mVar == null) {
            a(16);
            throw null;
        }
        b0 b0VarM = j(mVar.f14721i.d()).m();
        if (b0VarM != null) {
            return b0VarM;
        }
        a(55);
        throw null;
    }

    public final b0 u() {
        b0 b0VarM = j("String").m();
        if (b0VarM != null) {
            return b0VarM;
        }
        a(66);
        throw null;
    }

    public final g8.f v(int i10) {
        return i(q.f14761f.c(f9.f.j(e8.i.f15058c.f15060b + i10)));
    }

    public final b0 w() {
        b0 b0VarM = j("Unit").m();
        if (b0VarM != null) {
            return b0VarM;
        }
        a(65);
        throw null;
    }
}
