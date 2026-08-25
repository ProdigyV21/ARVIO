package com.arflix.tv.data.repository;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import com.caverock.androidsvg.k2;
import com.google.android.gms.internal.cast.ia;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class g {
    public static final int a(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (((com.typesafe.config.impl.g) it.next()).J() == 1) {
                return 1;
            }
        }
        return 2;
    }

    public static final int b(String str) {
        String str2;
        for (int i10 : h.f0.d(61)) {
            switch (i10) {
                case 1:
                    str2 = "ClientLoginDisabled";
                    break;
                case 2:
                    str2 = "SocketTimeout";
                    break;
                case 3:
                    str2 = "Ok";
                    break;
                case 4:
                    str2 = "UNKNOWN_ERR";
                    break;
                case 5:
                    str2 = "NetworkError";
                    break;
                case 6:
                    str2 = "ServiceUnavailable";
                    break;
                case 7:
                    str2 = "InternalError";
                    break;
                case 8:
                    str2 = "IllegalArgument";
                    break;
                case 9:
                    str2 = "BadAuthentication";
                    break;
                case 10:
                    str2 = "BAD_REQUEST";
                    break;
                case 11:
                    str2 = "EmptyConsumerPackageOrSig";
                    break;
                case 12:
                    str2 = "InvalidSecondFactor";
                    break;
                case 13:
                    str2 = "PostSignInFlowRequired";
                    break;
                case 14:
                    str2 = "NeedsBrowser";
                    break;
                case 15:
                    str2 = "Unknown";
                    break;
                case 16:
                    str2 = "NotVerified";
                    break;
                case 17:
                    str2 = "TermsNotAgreed";
                    break;
                case 18:
                    str2 = "AccountDisabled";
                    break;
                case 19:
                    str2 = "CaptchaRequired";
                    break;
                case 20:
                    str2 = "AccountDeleted";
                    break;
                case 21:
                    str2 = "ServiceDisabled";
                    break;
                case 22:
                    str2 = "ChallengeRequired";
                    break;
                case 23:
                    str2 = "NeedPermission";
                    break;
                case 24:
                    str2 = "NeedRemoteConsent";
                    break;
                case 25:
                    str2 = "INVALID_SCOPE";
                    break;
                case 26:
                    str2 = "UserCancel";
                    break;
                case 27:
                    str2 = "PermissionDenied";
                    break;
                case 28:
                    str2 = "RESTRICTED_CLIENT";
                    break;
                case 29:
                    str2 = "INVALID_AUDIENCE";
                    break;
                case 30:
                    str2 = "UNREGISTERED_ON_API_CONSOLE";
                    break;
                case 31:
                    str2 = "ThirdPartyDeviceManagementRequired";
                    break;
                case 32:
                    str2 = "DeviceManagementInternalError";
                    break;
                case 33:
                    str2 = "DeviceManagementSyncDisabled";
                    break;
                case 34:
                    str2 = "DeviceManagementAdminBlocked";
                    break;
                case 35:
                    str2 = "DeviceManagementAdminPendingApproval";
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    str2 = "DeviceManagementStaleSyncRequired";
                    break;
                case 37:
                    str2 = "DeviceManagementDeactivated";
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    str2 = "DeviceManagementScreenlockRequired";
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    str2 = "DeviceManagementRequired";
                    break;
                case 40:
                    str2 = "DeviceManagementRequiredOrSyncDisabled";
                    break;
                case 41:
                    str2 = "ALREADY_HAS_GMAIL";
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    str2 = "WeakPassword";
                    break;
                case 43:
                    str2 = "BadRequest";
                    break;
                case 44:
                    str2 = "BadUsername";
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    str2 = "DeletedGmail";
                    break;
                case 46:
                    str2 = "ExistingUsername";
                    break;
                case 47:
                    str2 = "LoginFail";
                    break;
                case 48:
                    str2 = "NotLoggedIn";
                    break;
                case 49:
                    str2 = "NoGmail";
                    break;
                case 50:
                    str2 = "RequestDenied";
                    break;
                case 51:
                    str2 = "ServerError";
                    break;
                case 52:
                    str2 = "UsernameUnavailable";
                    break;
                case 53:
                    str2 = "GPlusOther";
                    break;
                case 54:
                    str2 = "GPlusNickname";
                    break;
                case 55:
                    str2 = "GPlusInvalidChar";
                    break;
                case 56:
                    str2 = "GPlusInterstitial";
                    break;
                case 57:
                    str2 = "ProfileUpgradeError";
                    break;
                case 58:
                    str2 = "AuthSecurityError";
                    break;
                case 59:
                    str2 = "AuthBindingError";
                    break;
                case 60:
                    str2 = "AccountNotPresent";
                    break;
                case 61:
                    str2 = "AppSuspended";
                    break;
                default:
                    throw null;
            }
            if (str2.equals(str)) {
                return i10;
            }
        }
        return 15;
    }

    public static /* synthetic */ char c(int i10) {
        if (i10 == 1) {
            return '{';
        }
        if (i10 == 2) {
            return '[';
        }
        if (i10 == 3) {
            return '{';
        }
        if (i10 == 4) {
            return '[';
        }
        throw null;
    }

    public static /* synthetic */ char d(int i10) {
        if (i10 == 1) {
            return '}';
        }
        if (i10 == 2) {
            return ']';
        }
        if (i10 == 3) {
            return '}';
        }
        if (i10 == 4) {
            return ']';
        }
        throw null;
    }

    public static int e(int i10, int i11, int i12) {
        return ia.O(i10) + i11 + i12;
    }

    public static int f(int i10, int i11, int i12, int i13) {
        return ia.O(i10) + i11 + i12 + i13;
    }

    public static int g(Attributes attributes, int i10) {
        return k2.a(attributes.getLocalName(i10)).ordinal();
    }

    public static CompositionScopedCoroutineScopeCanceller h(ka.k0 k0Var, Composer composer) {
        CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(k0Var);
        composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
        return compositionScopedCoroutineScopeCanceller;
    }

    public static Modifier i(float f10, Modifier.Companion companion) {
        return ClipKt.clip(companion, RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
    }

    public static Modifier j(float f10, Modifier.Companion companion, long j10) {
        return BackgroundKt.m178backgroundbw27NRU(companion, j10, RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
    }

    public static Modifier k(float f10, Modifier modifier, float f11, long j10) {
        return BorderKt.m191borderxT4_qwU(modifier, f11, j10, RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
    }

    public static Modifier l(float f10, Modifier modifier, long j10) {
        return BackgroundKt.m178backgroundbw27NRU(modifier, j10, RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
    }

    public static Modifier m(float f10, Modifier modifier, Composer composer, int i10) {
        Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(modifier, Dp.m5678constructorimpl(f10));
        composer.startReplaceableGroup(i10);
        return modifierM529padding3ABfNKs;
    }

    public static Modifier n(Modifier modifier, float f10) {
        return ClipKt.clip(modifier, RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
    }

    public static String o(kotlin.text.m mVar, String str, String str2) {
        return kotlin.text.o.L0(mVar.f(str2, str)).toString();
    }

    public static void p(float f10, Modifier.Companion companion, Composer composer, int i10) {
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composer, i10);
    }

    public static void q(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceGroup();
    }

    public static void r(Composer composer, float f10, Modifier.Companion companion, Composer composer2, int i10) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composer2, i10);
    }

    public static /* synthetic */ boolean s(Applier applier) {
        return applier != null;
    }

    public static /* synthetic */ boolean t(HashSet hashSet) {
        return hashSet != null;
    }

    public static void u(float f10, Modifier.Companion companion, Composer composer, int i10) {
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composer, i10);
        composer.endReplaceGroup();
    }

    public static void v(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceGroup();
    }

    public static void w(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    public static /* synthetic */ String x(int i10) {
        switch (i10) {
            case 1:
                return "BEGIN_ARRAY";
            case 2:
                return "END_ARRAY";
            case 3:
                return "BEGIN_OBJECT";
            case 4:
                return "END_OBJECT";
            case 5:
                return "NAME";
            case 6:
                return "STRING";
            case 7:
                return "NUMBER";
            case 8:
                return "BOOLEAN";
            case 9:
                return "NULL";
            case 10:
                return "END_DOCUMENT";
            default:
                return "null";
        }
    }

    public static /* synthetic */ int y(String str) {
        if (str == null) {
            throw new NullPointerException("Name is null");
        }
        if (str.equals("pad")) {
            return 1;
        }
        if (str.equals("reflect")) {
            return 2;
        }
        if (str.equals("repeat")) {
            return 3;
        }
        throw new IllegalArgumentException("No enum constant com.caverock.androidsvg.SVG.GradientSpread.".concat(str));
    }

    public static /* synthetic */ int z(String str) {
        if (str == null) {
            throw new NullPointerException("Name is null");
        }
        if (str.equals("px")) {
            return 1;
        }
        if (str.equals("em")) {
            return 2;
        }
        if (str.equals("ex")) {
            return 3;
        }
        if (str.equals("in")) {
            return 4;
        }
        if (str.equals("cm")) {
            return 5;
        }
        if (str.equals("mm")) {
            return 6;
        }
        if (str.equals("pt")) {
            return 7;
        }
        if (str.equals("pc")) {
            return 8;
        }
        if (str.equals("percent")) {
            return 9;
        }
        throw new IllegalArgumentException("No enum constant com.caverock.androidsvg.SVG.Unit.".concat(str));
    }
}
