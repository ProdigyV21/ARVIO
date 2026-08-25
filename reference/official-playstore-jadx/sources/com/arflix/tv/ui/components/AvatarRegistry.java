package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.R;
import com.arflix.tv.ui.theme.AnimationConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00048\u0006X\u0086D¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR/\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00100\b0\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/components/AvatarRegistry;", "", "<init>", "()V", "", "avatarId", "getDrawableRes", "(I)I", "Lx6/x;", "Landroidx/compose/ui/graphics/Color;", "gradientColors", "(I)Lx6/x;", "totalAvatars", "I", "getTotalAvatars", "()I", "", "", "categories", "Ljava/util/List;", "getCategories", "()Ljava/util/List;", "", "drawableResources", "Ljava/util/Map;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AvatarRegistry {
    public static final int $stable = 0;
    public static final AvatarRegistry INSTANCE = new AvatarRegistry();
    private static final int totalAvatars = 84;
    private static final List<x6.x> categories = t7.a.E(new x6.x("Animals", t7.a.E(1, 2, 3, 4, 5, 6, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39)), new x6.x("Characters", t7.a.E(7, 8, 9, 10, 11, 12, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54)), new x6.x("Media", t7.a.E(13, 14, 15, 16, 17, 18, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69)), new x6.x("Nature", t7.a.E(19, 20, 21, 22, 23, 24, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84)));
    private static final Map<Integer, Integer> drawableResources = kotlin.collections.h0.t0(new x6.x(1, Integer.valueOf(R.drawable.avatar_1)), new x6.x(2, Integer.valueOf(R.drawable.avatar_2)), new x6.x(3, Integer.valueOf(R.drawable.avatar_3)), new x6.x(4, Integer.valueOf(R.drawable.avatar_4)), new x6.x(5, Integer.valueOf(R.drawable.avatar_5)), new x6.x(6, Integer.valueOf(R.drawable.avatar_6)), new x6.x(7, Integer.valueOf(R.drawable.avatar_7)), new x6.x(8, Integer.valueOf(R.drawable.avatar_8)), new x6.x(9, Integer.valueOf(R.drawable.avatar_9)), new x6.x(10, Integer.valueOf(R.drawable.avatar_10)), new x6.x(11, Integer.valueOf(R.drawable.avatar_11)), new x6.x(12, Integer.valueOf(R.drawable.avatar_12)), new x6.x(13, Integer.valueOf(R.drawable.avatar_13)), new x6.x(14, Integer.valueOf(R.drawable.avatar_14)), new x6.x(15, Integer.valueOf(R.drawable.avatar_15)), new x6.x(16, Integer.valueOf(R.drawable.avatar_16)), new x6.x(17, Integer.valueOf(R.drawable.avatar_17)), new x6.x(18, Integer.valueOf(R.drawable.avatar_18)), new x6.x(19, Integer.valueOf(R.drawable.avatar_19)), new x6.x(20, Integer.valueOf(R.drawable.avatar_20)), new x6.x(21, Integer.valueOf(R.drawable.avatar_21)), new x6.x(22, Integer.valueOf(R.drawable.avatar_22)), new x6.x(23, Integer.valueOf(R.drawable.avatar_23)), new x6.x(24, Integer.valueOf(R.drawable.avatar_24)), new x6.x(25, Integer.valueOf(R.drawable.avatar_25)), new x6.x(26, Integer.valueOf(R.drawable.avatar_26)), new x6.x(27, Integer.valueOf(R.drawable.avatar_27)), new x6.x(28, Integer.valueOf(R.drawable.avatar_28)), new x6.x(29, Integer.valueOf(R.drawable.avatar_29)), new x6.x(30, Integer.valueOf(R.drawable.avatar_30)), new x6.x(31, Integer.valueOf(R.drawable.avatar_31)), new x6.x(32, Integer.valueOf(R.drawable.avatar_32)), new x6.x(33, Integer.valueOf(R.drawable.avatar_33)), new x6.x(34, Integer.valueOf(R.drawable.avatar_34)), new x6.x(35, Integer.valueOf(R.drawable.avatar_35)), new x6.x(36, Integer.valueOf(R.drawable.avatar_36)), new x6.x(37, Integer.valueOf(R.drawable.avatar_37)), new x6.x(38, Integer.valueOf(R.drawable.avatar_38)), new x6.x(39, Integer.valueOf(R.drawable.avatar_39)), new x6.x(40, Integer.valueOf(R.drawable.avatar_40)), new x6.x(41, Integer.valueOf(R.drawable.avatar_41)), new x6.x(42, Integer.valueOf(R.drawable.avatar_42)), new x6.x(43, Integer.valueOf(R.drawable.avatar_43)), new x6.x(44, Integer.valueOf(R.drawable.avatar_44)), new x6.x(45, Integer.valueOf(R.drawable.avatar_45)), new x6.x(46, Integer.valueOf(R.drawable.avatar_46)), new x6.x(47, Integer.valueOf(R.drawable.avatar_47)), new x6.x(48, Integer.valueOf(R.drawable.avatar_48)), new x6.x(49, Integer.valueOf(R.drawable.avatar_49)), new x6.x(50, Integer.valueOf(R.drawable.avatar_50)), new x6.x(51, Integer.valueOf(R.drawable.avatar_51)), new x6.x(52, Integer.valueOf(R.drawable.avatar_52)), new x6.x(53, Integer.valueOf(R.drawable.avatar_53)), new x6.x(54, Integer.valueOf(R.drawable.avatar_54)), new x6.x(55, Integer.valueOf(R.drawable.avatar_55)), new x6.x(56, Integer.valueOf(R.drawable.avatar_56)), new x6.x(57, Integer.valueOf(R.drawable.avatar_57)), new x6.x(58, Integer.valueOf(R.drawable.avatar_58)), new x6.x(59, Integer.valueOf(R.drawable.avatar_59)), new x6.x(60, Integer.valueOf(R.drawable.avatar_60)), new x6.x(61, Integer.valueOf(R.drawable.avatar_61)), new x6.x(62, Integer.valueOf(R.drawable.avatar_62)), new x6.x(63, Integer.valueOf(R.drawable.avatar_63)), new x6.x(64, Integer.valueOf(R.drawable.avatar_64)), new x6.x(65, Integer.valueOf(R.drawable.avatar_65)), new x6.x(66, Integer.valueOf(R.drawable.avatar_66)), new x6.x(67, Integer.valueOf(R.drawable.avatar_67)), new x6.x(68, Integer.valueOf(R.drawable.avatar_68)), new x6.x(69, Integer.valueOf(R.drawable.avatar_69)), new x6.x(70, Integer.valueOf(R.drawable.avatar_70)), new x6.x(71, Integer.valueOf(R.drawable.avatar_71)), new x6.x(72, Integer.valueOf(R.drawable.avatar_72)), new x6.x(73, Integer.valueOf(R.drawable.avatar_73)), new x6.x(74, Integer.valueOf(R.drawable.avatar_74)), new x6.x(75, Integer.valueOf(R.drawable.avatar_75)), new x6.x(76, Integer.valueOf(R.drawable.avatar_76)), new x6.x(77, Integer.valueOf(R.drawable.avatar_77)), new x6.x(78, Integer.valueOf(R.drawable.avatar_78)), new x6.x(79, Integer.valueOf(R.drawable.avatar_79)), new x6.x(80, Integer.valueOf(R.drawable.avatar_80)), new x6.x(81, Integer.valueOf(R.drawable.avatar_81)), new x6.x(82, Integer.valueOf(R.drawable.avatar_82)), new x6.x(83, Integer.valueOf(R.drawable.avatar_83)), new x6.x(84, Integer.valueOf(R.drawable.avatar_84)));

    private AvatarRegistry() {
    }

    public final List<x6.x> getCategories() {
        return categories;
    }

    public final int getDrawableRes(int avatarId) {
        Integer num = drawableResources.get(Integer.valueOf(avatarId));
        return num != null ? num.intValue() : R.drawable.avatar_1;
    }

    public final int getTotalAvatars() {
        return totalAvatars;
    }

    public final x6.x gradientColors(int avatarId) {
        switch (avatarId) {
            case 1:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280948736L)), Color.m3462boximpl(ColorKt.Color(4282197256L)));
            case 2:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899400L)), Color.m3462boximpl(ColorKt.Color(4281147408L)));
            case 3:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280947712L)), Color.m3462boximpl(ColorKt.Color(4281999368L)));
            case 4:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900176L)), Color.m3462boximpl(ColorKt.Color(4280952088L)));
            case 5:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850848L)), Color.m3462boximpl(ColorKt.Color(4279575861L)));
            case 6:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279245328L)), Color.m3462boximpl(ColorKt.Color(4279904794L)));
            case 7:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898160L)), Color.m3462boximpl(ColorKt.Color(4280949317L)));
            case 8:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852112L)), Color.m3462boximpl(ColorKt.Color(4279577112L)));
            case 9:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279769130L)), Color.m3462boximpl(ColorKt.Color(4280624448L)));
            case 10:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949256L)), Color.m3462boximpl(ColorKt.Color(4282001424L)));
            case 11:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4280952872L)));
            case 12:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278848032L)), Color.m3462boximpl(ColorKt.Color(4279571770L)));
            case 13:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945176L)), Color.m3462boximpl(ColorKt.Color(4281995301L)));
            case 14:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280953397L)));
            case 15:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279242784L)), Color.m3462boximpl(ColorKt.Color(4279900725L)));
            case 16:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279896608L)), Color.m3462boximpl(ColorKt.Color(4280948021L)));
            case 17:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280950784L)), Color.m3462boximpl(ColorKt.Color(4282003464L)));
            case 18:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945152L)), Color.m3462boximpl(ColorKt.Color(4281996552L)));
            case 19:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852104L)), Color.m3462boximpl(ColorKt.Color(4279577106L)));
            case 20:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279896584L)), Color.m3462boximpl(ColorKt.Color(4280947984L)));
            case 21:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898149L)), Color.m3462boximpl(ColorKt.Color(4280949306L)));
            case 22:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278721048L)), Color.m3462boximpl(ColorKt.Color(4279249448L)));
            case 23:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850832L)), Color.m3462boximpl(ColorKt.Color(4279575834L)));
            case 24:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280950792L)), Color.m3462boximpl(ColorKt.Color(4282003472L)));
            case 25:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898120L)), Color.m3462boximpl(ColorKt.Color(4280949264L)));
            case 26:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280952117L)));
            case 27:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949256L)), Color.m3462boximpl(ColorKt.Color(4282000656L)));
            case 28:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852104L)), Color.m3462boximpl(ColorKt.Color(4279577109L)));
            case 29:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899400L)), Color.m3462boximpl(ColorKt.Color(4280950800L)));
            case 30:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949248L)), Color.m3462boximpl(ColorKt.Color(4282001416L)));
            case 31:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280948736L)), Color.m3462boximpl(ColorKt.Color(4282000648L)));
            case 32:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4280952872L)));
            case 33:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280952112L)));
            case 34:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850848L)), Color.m3462boximpl(ColorKt.Color(4279575864L)));
            case 35:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279896600L)), Color.m3462boximpl(ColorKt.Color(4280948008L)));
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898149L)), Color.m3462boximpl(ColorKt.Color(4280949304L)));
            case 37:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850832L)), Color.m3462boximpl(ColorKt.Color(4279575834L)));
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899408L)), Color.m3462boximpl(ColorKt.Color(4280950808L)));
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899400L)), Color.m3462boximpl(ColorKt.Color(4280950800L)));
            case 40:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898120L)), Color.m3462boximpl(ColorKt.Color(4280949264L)));
            case 41:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280952117L)));
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280946720L)), Color.m3462boximpl(ColorKt.Color(4281997360L)));
            case 43:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899400L)), Color.m3462boximpl(ColorKt.Color(4280950800L)));
            case 44:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898149L)), Color.m3462boximpl(ColorKt.Color(4280949304L)));
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949248L)), Color.m3462boximpl(ColorKt.Color(4282001416L)));
            case 46:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945176L)), Color.m3462boximpl(ColorKt.Color(4281995301L)));
            case 47:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949248L)), Color.m3462boximpl(ColorKt.Color(4282001416L)));
            case 48:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900712L)), Color.m3462boximpl(ColorKt.Color(4280952120L)));
            case 49:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280952112L)));
            case 50:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280948736L)), Color.m3462boximpl(ColorKt.Color(4282000648L)));
            case 51:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945162L)), Color.m3462boximpl(ColorKt.Color(4281996565L)));
            case 52:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898144L)), Color.m3462boximpl(ColorKt.Color(4280948784L)));
            case 53:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850848L)), Color.m3462boximpl(ColorKt.Color(4279575864L)));
            case 54:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949256L)), Color.m3462boximpl(ColorKt.Color(4282000656L)));
            case 55:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4280952872L)));
            case 56:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280953397L)));
            case 57:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280949256L)), Color.m3462boximpl(ColorKt.Color(4282000656L)));
            case 58:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898149L)), Color.m3462boximpl(ColorKt.Color(4280949304L)));
            case 59:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280950784L)), Color.m3462boximpl(ColorKt.Color(4282003464L)));
            case 60:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279896613L)), Color.m3462boximpl(ColorKt.Color(4280948024L)));
            case 61:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4280952872L)));
            case 62:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900704L)), Color.m3462boximpl(ColorKt.Color(4280952112L)));
            case ColorSpace.MaxId /* 63 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4280952872L)));
            case 64:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945176L)), Color.m3462boximpl(ColorKt.Color(4281995301L)));
            case 65:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279899400L)), Color.m3462boximpl(ColorKt.Color(4280950800L)));
            case 66:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850848L)), Color.m3462boximpl(ColorKt.Color(4279575864L)));
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850832L)), Color.m3462boximpl(ColorKt.Color(4279575834L)));
            case 68:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279896584L)), Color.m3462boximpl(ColorKt.Color(4280947984L)));
            case 69:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945162L)), Color.m3462boximpl(ColorKt.Color(4281996565L)));
            case 70:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898149L)), Color.m3462boximpl(ColorKt.Color(4280949304L)));
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850848L)), Color.m3462boximpl(ColorKt.Color(4279575864L)));
            case 72:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280946720L)), Color.m3462boximpl(ColorKt.Color(4281997360L)));
            case 73:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945168L)), Color.m3462boximpl(ColorKt.Color(4281996568L)));
            case 74:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280945176L)), Color.m3462boximpl(ColorKt.Color(4281995301L)));
            case 75:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280946720L)), Color.m3462boximpl(ColorKt.Color(4281997360L)));
            case 76:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852104L)), Color.m3462boximpl(ColorKt.Color(4279577106L)));
            case 77:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278850832L)), Color.m3462boximpl(ColorKt.Color(4279575834L)));
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280946696L)), Color.m3462boximpl(ColorKt.Color(4281997840L)));
            case 79:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278848032L)), Color.m3462boximpl(ColorKt.Color(4279571770L)));
            case AnimationConstants.STAGGER_SECTION /* 80 */:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852104L)), Color.m3462boximpl(ColorKt.Color(4279577109L)));
            case 81:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900712L)), Color.m3462boximpl(ColorKt.Color(4280952120L)));
            case 82:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279898144L)), Color.m3462boximpl(ColorKt.Color(4280948784L)));
            case 83:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4280946696L)), Color.m3462boximpl(ColorKt.Color(4281997840L)));
            case 84:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4278852104L)), Color.m3462boximpl(ColorKt.Color(4279577106L)));
            default:
                return new x6.x(Color.m3462boximpl(ColorKt.Color(4279900698L)), Color.m3462boximpl(ColorKt.Color(4281150765L)));
        }
    }
}
