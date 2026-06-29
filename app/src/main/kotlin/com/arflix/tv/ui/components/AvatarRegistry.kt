package com.arflix.tv.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R

/**
 * Registry of 84 avatar images using Microsoft Fluent Emoji 3D (MIT License).
 * https://github.com/microsoft/fluentui-emoji
 *
 * Organized in 4 themed categories, each with 21 avatars.
 */
object AvatarRegistry {

    val totalAvatars = 84

    /**
     * Categories with their label and avatar IDs.
     * IDs 1-24 are the original set (backward compatible).
     * IDs 25-84 are the expanded set.
     */
    val categories: List<Pair<String, List<Int>>> = listOf(
        "Animals" to listOf(1, 2, 3, 4, 5, 6, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39),
        "Characters" to listOf(7, 8, 9, 10, 11, 12, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54),
        "Media" to listOf(13, 14, 15, 16, 17, 18, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69),
        "Nature" to listOf(19, 20, 21, 22, 23, 24, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84)
    )

    /** Map avatar ID to drawable resource. */
    private val drawableResources: Map<Int, Int> = mapOf(
        // Original Animals (1-6)
        1 to R.drawable.avatar_1,    // Cat
        2 to R.drawable.avatar_2,    // Dog
        3 to R.drawable.avatar_3,    // Fox
        4 to R.drawable.avatar_4,    // Owl
        5 to R.drawable.avatar_5,    // Penguin
        6 to R.drawable.avatar_6,    // Panda
        // Original Characters (7-12)
        7 to R.drawable.avatar_7,    // Robot
        8 to R.drawable.avatar_8,    // Alien
        9 to R.drawable.avatar_9,    // Ghost
        10 to R.drawable.avatar_10,  // Disguise
        11 to R.drawable.avatar_11,  // Skull
        12 to R.drawable.avatar_12,  // Space Invader
        // Original Media (13-18)
        13 to R.drawable.avatar_13,  // Popcorn
        14 to R.drawable.avatar_14,  // Clapper
        15 to R.drawable.avatar_15,  // Gamepad
        16 to R.drawable.avatar_16,  // Rocket
        17 to R.drawable.avatar_17,  // Star
        18 to R.drawable.avatar_18,  // Fire
        // Original Nature (19-24)
        19 to R.drawable.avatar_19,  // Cactus
        20 to R.drawable.avatar_20,  // Mushroom
        21 to R.drawable.avatar_21,  // Unicorn
        22 to R.drawable.avatar_22,  // Dragon
        23 to R.drawable.avatar_23,  // T-Rex
        24 to R.drawable.avatar_24,  // Sunflower
        // Extended Animals (25-39)
        25 to R.drawable.avatar_25,  // Bear
        26 to R.drawable.avatar_26,  // Rabbit
        27 to R.drawable.avatar_27,  // Hamster
        28 to R.drawable.avatar_28,  // Frog
        29 to R.drawable.avatar_29,  // Monkey
        30 to R.drawable.avatar_30,  // Lion
        31 to R.drawable.avatar_31,  // Tiger
        32 to R.drawable.avatar_32,  // Koala
        33 to R.drawable.avatar_33,  // Mouse
        34 to R.drawable.avatar_34,  // Dolphin
        35 to R.drawable.avatar_35,  // Octopus
        36 to R.drawable.avatar_36,  // Butterfly
        37 to R.drawable.avatar_37,  // Turtle
        38 to R.drawable.avatar_38,  // Raccoon
        39 to R.drawable.avatar_39,  // Hedgehog
        // Extended Characters (40-54)
        40 to R.drawable.avatar_40,  // Sunglasses
        41 to R.drawable.avatar_41,  // Nerd
        42 to R.drawable.avatar_42,  // Clown
        43 to R.drawable.avatar_43,  // Cowboy
        44 to R.drawable.avatar_44,  // Party
        45 to R.drawable.avatar_45,  // Zany
        46 to R.drawable.avatar_46,  // Heart-eyes
        47 to R.drawable.avatar_47,  // Star-struck
        48 to R.drawable.avatar_48,  // Halo
        49 to R.drawable.avatar_49,  // Shushing
        50 to R.drawable.avatar_50,  // Pumpkin
        51 to R.drawable.avatar_51,  // Ogre
        52 to R.drawable.avatar_52,  // Goblin
        53 to R.drawable.avatar_53,  // Cold
        54 to R.drawable.avatar_54,  // Exploding
        // Extended Media (55-69)
        55 to R.drawable.avatar_55,  // Movie Camera
        56 to R.drawable.avatar_56,  // Microphone
        57 to R.drawable.avatar_57,  // Guitar
        58 to R.drawable.avatar_58,  // Headphone
        59 to R.drawable.avatar_59,  // Trophy
        60 to R.drawable.avatar_60,  // Crystal Ball
        61 to R.drawable.avatar_61,  // Joystick
        62 to R.drawable.avatar_62,  // Television
        63 to R.drawable.avatar_63,  // Camera
        64 to R.drawable.avatar_64,  // Theater
        65 to R.drawable.avatar_65,  // Art Palette
        66 to R.drawable.avatar_66,  // Gem
        67 to R.drawable.avatar_67,  // Puzzle
        68 to R.drawable.avatar_68,  // Bowling
        69 to R.drawable.avatar_69,  // Bullseye
        // Extended Nature (70-84)
        70 to R.drawable.avatar_70,  // Rainbow
        71 to R.drawable.avatar_71,  // Snowflake
        72 to R.drawable.avatar_72,  // Cherry Blossom
        73 to R.drawable.avatar_73,  // Rose
        74 to R.drawable.avatar_74,  // Tulip
        75 to R.drawable.avatar_75,  // Hibiscus
        76 to R.drawable.avatar_76,  // Evergreen
        77 to R.drawable.avatar_77,  // Palm Tree
        78 to R.drawable.avatar_78,  // Volcano
        79 to R.drawable.avatar_79,  // Comet
        80 to R.drawable.avatar_80,  // Four Leaf Clover
        81 to R.drawable.avatar_81,  // Crescent Moon
        82 to R.drawable.avatar_82,  // Lotus
        83 to R.drawable.avatar_83,  // Maple Leaf
        84 to R.drawable.avatar_84   // Seedling
    )

    fun getDrawableRes(avatarId: Int): Int =
        drawableResources[avatarId] ?: R.drawable.avatar_1

    /**
     * Gradient background colors per avatar - dark tones that complement each 3D icon.
     */
    fun gradientColors(avatarId: Int): Pair<Color, Color> = when (avatarId) {
        // Original Animals
        1  -> Color(0xFF2A1800) to Color(0xFF3D2508)  // Cat
        2  -> Color(0xFF1A1508) to Color(0xFF2D2010)  // Dog
        3  -> Color(0xFF2A1400) to Color(0xFF3A2008)  // Fox
        4  -> Color(0xFF1A1810) to Color(0xFF2A2518)  // Owl
        5  -> Color(0xFF0A1520) to Color(0xFF152535)  // Penguin
        6  -> Color(0xFF101A10) to Color(0xFF1A2A1A)  // Panda
        // Original Characters
        7  -> Color(0xFF1A1030) to Color(0xFF2A1A45)  // Robot
        8  -> Color(0xFF0A1A10) to Color(0xFF152A18)  // Alien
        9  -> Color(0xFF18182A) to Color(0xFF252540)  // Ghost
        10 -> Color(0xFF2A1A08) to Color(0xFF3A2810)  // Disguise
        11 -> Color(0xFF1A1A1A) to Color(0xFF2A2828)  // Skull
        12 -> Color(0xFF0A0A20) to Color(0xFF15153A)  // Space Invader
        // Original Media
        13 -> Color(0xFF2A0A18) to Color(0xFF3A1025)  // Popcorn
        14 -> Color(0xFF1A1A20) to Color(0xFF2A2A35)  // Clapper
        15 -> Color(0xFF101020) to Color(0xFF1A1A35)  // Gamepad
        16 -> Color(0xFF1A0A20) to Color(0xFF2A1535)  // Rocket
        17 -> Color(0xFF2A2000) to Color(0xFF3A3008)  // Star
        18 -> Color(0xFF2A0A00) to Color(0xFF3A1508)  // Fire
        // Original Nature
        19 -> Color(0xFF0A1A08) to Color(0xFF152A12)  // Cactus
        20 -> Color(0xFF1A0A08) to Color(0xFF2A1510)  // Mushroom
        21 -> Color(0xFF1A1025) to Color(0xFF2A1A3A)  // Unicorn
        22 -> Color(0xFF081A18) to Color(0xFF102A28)  // Dragon
        23 -> Color(0xFF0A1510) to Color(0xFF15251A)  // T-Rex
        24 -> Color(0xFF2A2008) to Color(0xFF3A3010)  // Sunflower
        // Extended Animals (25-39)
        25 -> Color(0xFF1A1008) to Color(0xFF2A1A10)  // Bear
        26 -> Color(0xFF1A1A20) to Color(0xFF2A2535)  // Rabbit
        27 -> Color(0xFF2A1A08) to Color(0xFF3A2510)  // Hamster
        28 -> Color(0xFF0A1A08) to Color(0xFF152A15)  // Frog
        29 -> Color(0xFF1A1508) to Color(0xFF2A2010)  // Monkey
        30 -> Color(0xFF2A1A00) to Color(0xFF3A2808)  // Lion
        31 -> Color(0xFF2A1800) to Color(0xFF3A2508)  // Tiger
        32 -> Color(0xFF1A1A1A) to Color(0xFF2A2828)  // Koala
        33 -> Color(0xFF1A1A20) to Color(0xFF2A2530)  // Mouse
        34 -> Color(0xFF0A1520) to Color(0xFF152538)  // Dolphin
        35 -> Color(0xFF1A0A18) to Color(0xFF2A1528)  // Octopus
        36 -> Color(0xFF1A1025) to Color(0xFF2A1A38)  // Butterfly
        37 -> Color(0xFF0A1510) to Color(0xFF15251A)  // Turtle
        38 -> Color(0xFF1A1510) to Color(0xFF2A2018)  // Raccoon
        39 -> Color(0xFF1A1508) to Color(0xFF2A2010)  // Hedgehog
        // Extended Characters (40-54)
        40 -> Color(0xFF1A1008) to Color(0xFF2A1A10)  // Sunglasses
        41 -> Color(0xFF1A1A20) to Color(0xFF2A2535)  // Nerd
        42 -> Color(0xFF2A1020) to Color(0xFF3A1830)  // Clown
        43 -> Color(0xFF1A1508) to Color(0xFF2A2010)  // Cowboy
        44 -> Color(0xFF1A1025) to Color(0xFF2A1A38)  // Party
        45 -> Color(0xFF2A1A00) to Color(0xFF3A2808)  // Zany
        46 -> Color(0xFF2A0A18) to Color(0xFF3A1025)  // Heart-eyes
        47 -> Color(0xFF2A1A00) to Color(0xFF3A2808)  // Star-struck
        48 -> Color(0xFF1A1A28) to Color(0xFF2A2538)  // Halo
        49 -> Color(0xFF1A1A20) to Color(0xFF2A2530)  // Shushing
        50 -> Color(0xFF2A1800) to Color(0xFF3A2508)  // Pumpkin
        51 -> Color(0xFF2A0A0A) to Color(0xFF3A1515)  // Ogre
        52 -> Color(0xFF1A1020) to Color(0xFF2A1830)  // Goblin
        53 -> Color(0xFF0A1520) to Color(0xFF152538)  // Cold
        54 -> Color(0xFF2A1A08) to Color(0xFF3A2510)  // Exploding
        // Extended Media (55-69)
        55 -> Color(0xFF1A1A1A) to Color(0xFF2A2828)  // Movie Camera
        56 -> Color(0xFF1A1A20) to Color(0xFF2A2A35)  // Microphone
        57 -> Color(0xFF2A1A08) to Color(0xFF3A2510)  // Guitar
        58 -> Color(0xFF1A1025) to Color(0xFF2A1A38)  // Headphone
        59 -> Color(0xFF2A2000) to Color(0xFF3A3008)  // Trophy
        60 -> Color(0xFF1A0A25) to Color(0xFF2A1538)  // Crystal Ball
        61 -> Color(0xFF1A1A1A) to Color(0xFF2A2828)  // Joystick
        62 -> Color(0xFF1A1A20) to Color(0xFF2A2530)  // Television
        63 -> Color(0xFF1A1A1A) to Color(0xFF2A2828)  // Camera
        64 -> Color(0xFF2A0A18) to Color(0xFF3A1025)  // Theater
        65 -> Color(0xFF1A1508) to Color(0xFF2A2010)  // Art Palette
        66 -> Color(0xFF0A1520) to Color(0xFF152538)  // Gem
        67 -> Color(0xFF0A1510) to Color(0xFF15251A)  // Puzzle
        68 -> Color(0xFF1A0A08) to Color(0xFF2A1510)  // Bowling
        69 -> Color(0xFF2A0A0A) to Color(0xFF3A1515)  // Bullseye
        // Extended Nature (70-84)
        70 -> Color(0xFF1A1025) to Color(0xFF2A1A38)  // Rainbow
        71 -> Color(0xFF0A1520) to Color(0xFF152538)  // Snowflake
        72 -> Color(0xFF2A1020) to Color(0xFF3A1830)  // Cherry Blossom
        73 -> Color(0xFF2A0A10) to Color(0xFF3A1518)  // Rose
        74 -> Color(0xFF2A0A18) to Color(0xFF3A1025)  // Tulip
        75 -> Color(0xFF2A1020) to Color(0xFF3A1830)  // Hibiscus
        76 -> Color(0xFF0A1A08) to Color(0xFF152A12)  // Evergreen
        77 -> Color(0xFF0A1510) to Color(0xFF15251A)  // Palm Tree
        78 -> Color(0xFF2A1008) to Color(0xFF3A1A10)  // Volcano
        79 -> Color(0xFF0A0A20) to Color(0xFF15153A)  // Comet
        80 -> Color(0xFF0A1A08) to Color(0xFF152A15)  // Four Leaf Clover
        81 -> Color(0xFF1A1A28) to Color(0xFF2A2538)  // Crescent Moon
        82 -> Color(0xFF1A1020) to Color(0xFF2A1830)  // Lotus
        83 -> Color(0xFF2A1008) to Color(0xFF3A1A10)  // Maple Leaf
        84 -> Color(0xFF0A1A08) to Color(0xFF152A12)  // Seedling
        else -> Color(0xFF1A1A1A) to Color(0xFF2D2D2D)
    }
}

/**
 * Display-only localization of avatar category headers.
 * The Map keys in [AvatarRegistry.categories] stay English (used as logic keys);
 * only the shown header label is translated. Unknown categories pass through.
 */
@Composable
fun avatarCategoryLabel(raw: String): String = when (raw) {
    "Animals" -> stringResource(R.string.avatar_cat_animals)
    "Characters" -> stringResource(R.string.avatar_cat_characters)
    "Media" -> stringResource(R.string.avatar_cat_media)
    "Nature" -> stringResource(R.string.avatar_cat_nature)
    else -> raw
}

/**
 * Renders an avatar image from drawable resources.
 */
@Composable
fun AvatarIcon(avatarId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = AvatarRegistry.getDrawableRes(avatarId)),
        contentDescription = stringResource(R.string.component_avatar),
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}
