package com.arflix.tv.ui.screens.search

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The decisions behind the filter row, checked without a screen: which separator carries E3,
 * what survives a media-type switch, and where the focus goes inside an open panel.
 */
class DiscoverFiltersTest {

    private val action = MOVIE_GENRES.first { it.id == 28 }
    private val sciFi = MOVIE_GENRES.first { it.id == 878 }
    private val comedy = MOVIE_GENRES.first { it.id == 35 }
    private val music = MOVIE_GENRES.first { it.id == 10402 }

    // ── E3: the separator is the whole feature ──────────────────────────

    @Test fun matchingAllGenresUsesTheCommaThatMeansAnd() {
        assertEquals("28,878", genresParam(listOf(action, sciFi), matchAll = true))
    }

    @Test fun matchingAnyGenreUsesThePipeThatMeansOr() {
        assertEquals("28|878", genresParam(listOf(action, sciFi), matchAll = false))
    }

    @Test fun noGenreMeansNoParameterAtAllRatherThanAnEmptyOne() {
        assertNull(genresParam(emptyList(), matchAll = true))
    }

    // ── The type switch: remap, do not simply drop (FUND C) ─────────────

    @Test fun switchingToSeriesKeepsAGenreThatHasACounterpart() {
        val kept = remapGenresForType(listOf(action), DiscoverType.TV_SHOWS)
        assertEquals(listOf(10759), kept.map { it.id })
    }

    @Test fun switchingToSeriesKeepsAGenreThatIsNumberedTheSameInBothLists() {
        val kept = remapGenresForType(listOf(comedy), DiscoverType.TV_SHOWS)
        assertEquals(listOf(35), kept.map { it.id })
    }

    @Test fun aGenreWithNoSeriesCounterpartIsTheOnlyOneDropped() {
        // "Music" exists for films and not for series — carrying it over would return nothing.
        val kept = remapGenresForType(listOf(action, music), DiscoverType.TV_SHOWS)
        assertEquals(listOf(10759), kept.map { it.id })
    }

    @Test fun switchingBackToMoviesUndoesTheRemap() {
        val toSeries = remapGenresForType(listOf(action), DiscoverType.TV_SHOWS)
        val andBack = remapGenresForType(toSeries, DiscoverType.MOVIES)
        assertEquals(listOf(28), andBack.map { it.id })
    }

    @Test fun animeUsesTheMovieNumberingItsOwnListIsWrittenIn() {
        val kept = remapGenresForType(listOf(action), DiscoverType.ANIME)
        assertEquals(listOf(28), kept.map { it.id })
    }

    @Test fun everyOfferedGenreSurvivesItsOwnType() {
        DiscoverType.entries.forEach { type ->
            val offered = genresFor(type)
            assertEquals(
                "$type must not offer a genre it then drops",
                offered.map { it.id },
                remapGenresForType(offered, type).map { it.id }
            )
        }
    }

    // ── Age rating: movies only ─────────────────────────────────────────

    @Test fun onlyMoviesCanBeFilteredByAge() {
        assertTrue(supportsCertification(DiscoverType.MOVIES))
        assertFalse(supportsCertification(DiscoverType.TV_SHOWS))
        assertFalse(supportsCertification(DiscoverType.ANIME))
    }

    @Test fun theAgeListFollowsTheContentCountryAndFallsBackToNothingWhereThereIsNone() {
        assertEquals(listOf("0", "6", "12", "16", "18"), certificationsForLanguage("de-DE"))
        assertEquals(listOf("G", "PG", "PG-13", "R", "NC-17"), certificationsForLanguage("en-US"))
        assertTrue(certificationsForLanguage("ja-JP").isEmpty())
    }

    // ── Panel navigation across sections (Ä4a) ──────────────────────────

    /** The genre panel as the focus sees it: the AND/OR switch, then the tiles three across. */
    private val genreSections = listOf(PanelSectionShape(2, 4), PanelSectionShape(12, 3))

    private fun move(section: Int, entry: Int, dx: Int = 0, dy: Int = 0) =
        movePanelFocus(PanelFocus(section, entry), genreSections, dx, dy)

    @Test fun sidewaysMovementStaysInsideItsRow() {
        // Twelve tiles, three across: index 2 is the end of the first row.
        assertEquals(PanelFocus(1, 2), move(1, 2, dx = 1))
        assertEquals(PanelFocus(1, 3), move(1, 3, dx = -1))
        assertEquals(PanelFocus(1, 1), move(1, 0, dx = 1))
    }

    @Test fun downMovesOneRowAndStopsAtTheLast() {
        assertEquals(PanelFocus(1, 4), move(1, 1, dy = 1))
        assertEquals(PanelFocus(1, 10), move(1, 10, dy = 1))
    }

    @Test fun upFromTheTilesLandsInTheSwitchAboveThemRatherThanLeaving() {
        // Ä4a: this is the whole fix. The switch used to have nowhere to be reached from.
        assertEquals(PanelFocus(0, 1), move(1, 1, dy = -1))
        assertEquals(PanelFocus(1, 1), move(1, 4, dy = -1))
    }

    @Test fun onlyTheSecondPressUpwardsLeavesThePanel() {
        assertTrue(move(0, 0, dy = -1).hasLeft)
    }

    @Test fun downOutOfTheSwitchEntersTheTilesKeepingTheColumn() {
        assertEquals(PanelFocus(1, 1), move(0, 1, dy = 1))
        assertEquals(PanelFocus(1, 0), move(0, 0, dy = 1))
    }

    @Test fun aColumnWithNoCounterpartInTheNextSectionLandsOnTheNearestOne() {
        // The switch is four across but holds two entries; the tiles are three across.
        val wide = listOf(PanelSectionShape(2, 4), PanelSectionShape(2, 2))
        assertEquals(PanelFocus(1, 1), movePanelFocus(PanelFocus(0, 1), wide, dx = 0, dy = 1))
        val narrow = listOf(PanelSectionShape(3, 3), PanelSectionShape(1, 3))
        assertEquals(PanelFocus(1, 0), movePanelFocus(PanelFocus(0, 2), narrow, dx = 0, dy = 1))
    }

    @Test fun upIntoASectionLandsInItsLastRowNotItsFirst() {
        val sections = listOf(PanelSectionShape(6, 3), PanelSectionShape(3, 3))
        assertEquals(PanelFocus(0, 4), movePanelFocus(PanelFocus(1, 1), sections, dx = 0, dy = -1))
    }

    @Test fun downOutOfAFullRowIntoAShorterLastOneLandsOnItsEnd() {
        val sections = listOf(PanelSectionShape(5, 3))
        assertEquals(PanelFocus(0, 4), movePanelFocus(PanelFocus(0, 2), sections, dx = 0, dy = 1))
    }

    @Test fun downFromTheVeryLastRowStaysPutRatherThanClosingThePanel() {
        assertEquals(PanelFocus(1, 11), move(1, 11, dy = 1))
    }

    @Test fun anEmptyPanelCannotTrapTheFocus() {
        assertTrue(movePanelFocus(PanelFocus(0, 0), emptyList(), dx = 0, dy = 1).hasLeft)
        val empty = listOf(PanelSectionShape(0, 3))
        assertTrue(movePanelFocus(PanelFocus(0, 0), empty, dx = 0, dy = 1).hasLeft)
    }

    @Test fun aBrokenColumnCountStillMovesSomewhereSensible() {
        val sections = listOf(PanelSectionShape(5, 0))
        assertEquals(PanelFocus(0, 1), movePanelFocus(PanelFocus(0, 0), sections, dx = 0, dy = 1))
    }

    @Test fun aSectionThatDisappearedTakesTheFocusToOneThatIsStillThere() {
        // The exact-year row goes away with the decade it belonged to.
        val onlyDecades = listOf(PanelSectionShape(7, 4))
        assertEquals(PanelFocus(0, 3), clampPanelFocus(PanelFocus(1, 3), onlyDecades))
        assertEquals(PanelFocus(0, 6), clampPanelFocus(PanelFocus(0, 9), onlyDecades))
        assertNull(clampPanelFocus(PanelFocus(0, 0), listOf(PanelSectionShape(0, 3))))
    }

    // ── The open drop-down list ─────────────────────────────────────────

    @Test fun theDropDownListStopsAtBothEndsInsteadOfWrapping() {
        assertEquals(0, moveDropdownFocus(index = 0, count = 11, dy = -1))
        assertEquals(10, moveDropdownFocus(index = 10, count = 11, dy = 1))
        assertEquals(5, moveDropdownFocus(index = 4, count = 11, dy = 1))
    }

    @Test fun anEmptyDropDownListCannotBeMovedIntoNowhere() {
        assertEquals(0, moveDropdownFocus(index = 3, count = 0, dy = 1))
    }

    // ── Ä3: a chip that is only half in view is not in view ─────────────

    @Test fun aChipHangingOverTheLeftEdgeDoesNotCountAsVisible() {
        // 36 px wide, 18 of them past the content edge — the state the user photographed.
        assertFalse(isChipFullyVisible(itemOffset = -18, itemSize = 36, contentStart = 0, contentEnd = 400))
    }

    @Test fun aChipHangingOverTheRightEdgeDoesNotCountEither() {
        assertFalse(isChipFullyVisible(itemOffset = 380, itemSize = 36, contentStart = 0, contentEnd = 400))
    }

    @Test fun aChipFlushWithEitherEdgeIsVisibleAndTheRowStaysWhereItIs() {
        assertTrue(isChipFullyVisible(itemOffset = 0, itemSize = 36, contentStart = 0, contentEnd = 400))
        assertTrue(isChipFullyVisible(itemOffset = 364, itemSize = 36, contentStart = 0, contentEnd = 400))
    }

    // ── Rating ──────────────────────────────────────────────────────────

    @Test fun aRatingCountsAsSetAsSoonAsAnyOfItsThreeValuesIs() {
        assertFalse(RatingFilter().isSet)
        assertTrue(RatingFilter(min = 7.0).isSet)
        assertTrue(RatingFilter(max = 9.0).isSet)
        assertTrue(RatingFilter(minVotes = 500).isSet)
    }

    @Test fun theVoteFloorStartsAtAnySoFreshReleasesAreNotHiddenByDefault() {
        assertNull(MIN_VOTE_OPTIONS.first())
        assertNull(RatingFilter().minVotes)
    }

    @Test fun bothRatingFieldsOfferWholePointsOnly() {
        assertEquals(10, RATING_VALUES.size)
        assertEquals(listOf(10.0, 9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0), RATING_VALUES)
        assertTrue(RATING_VALUES.all { it % 1.0 == 0.0 })
    }

    @Test fun aFloorAboveTheCeilingDragsTheCeilingAlongInsteadOfEmptyingTheGrid() {
        assertEquals(RatingFilter(min = 8.0, max = 8.0), withRatingFloor(RatingFilter(max = 6.0), 8.0))
    }

    @Test fun aCeilingBelowTheFloorDragsTheFloorAlong() {
        assertEquals(RatingFilter(min = 4.0, max = 4.0), withRatingCeiling(RatingFilter(min = 7.0), 4.0))
    }

    @Test fun aRangeThatMakesSenseIsLeftAlone() {
        assertEquals(RatingFilter(min = 6.0, max = 9.0), withRatingFloor(RatingFilter(max = 9.0), 6.0))
        assertEquals(RatingFilter(min = 6.0, max = 9.0), withRatingCeiling(RatingFilter(min = 6.0), 9.0))
    }

    @Test fun clearingOneEndNeverDragsTheOther() {
        assertEquals(RatingFilter(min = null, max = 6.0), withRatingFloor(RatingFilter(min = 8.0, max = 6.0), null))
        assertEquals(RatingFilter(min = 8.0, max = null), withRatingCeiling(RatingFilter(min = 8.0, max = 6.0), null))
    }

    // ── The year panel: decades instead of seventy-eight years (J1) ──────

    @Test fun theYearPanelOffersSixTilesWhereItUsedToOfferSeventyEight() {
        val decades = decadeOptions(currentYear = 2026)
        assertEquals(6, decades.size)
        assertEquals(listOf(2020, 2010, 2000, 1990, 1980, 1950), decades.map { it.start })
        assertEquals(listOf(2029, 2019, 2009, 1999, 1989, 1979), decades.map { it.end })
    }

    @Test fun onlyTheOldestTileIsTheOpenEndedOne() {
        val decades = decadeOptions(currentYear = 2026)
        assertTrue(decades.last().isTail)
        assertTrue(decades.dropLast(1).none { it.isTail })
    }

    @Test fun theDecadeTilesFollowTheCalendarRatherThanBeingHardCoded() {
        assertEquals(listOf(2030, 2020, 2010, 2000, 1990, 1950), decadeOptions(currentYear = 2031).map { it.start })
    }

    @Test fun theExactYearListNeverReachesPastToday() {
        assertEquals(listOf(2026, 2025, 2024, 2023, 2022, 2021, 2020), yearsIn(Decade(2020, 2029), currentYear = 2026))
        assertEquals(10, yearsIn(Decade(1990, 1999), currentYear = 2026).size)
    }

    @Test fun aDecadeTileSaysTwoThousandTwentiesButNinetiesTheShortWay() {
        assertEquals("2020", decadeLabelNumber(2020))
        assertEquals("90", decadeLabelNumber(1990))
        assertEquals("80", decadeLabelNumber(1980))
    }

    // ── The reset chip: when it is there, and where the frame goes ──────

    @Test fun theResetChipStaysAwayWhileThereIsNothingToReset() {
        assertFalse(showsClearChip(SearchUiState()))
    }

    @Test fun theResetChipAppearsAsSoonAsOneFilterIsSet() {
        assertTrue(showsClearChip(SearchUiState(selectedGenres = listOf(action))))
        assertTrue(showsClearChip(SearchUiState(decade = Decade(2000, 2009))))
        assertTrue(showsClearChip(SearchUiState(year = 2001)))
        assertTrue(showsClearChip(SearchUiState(rating = RatingFilter(min = 7.0))))
        assertTrue(showsClearChip(SearchUiState(certification = "16")))
        assertTrue(showsClearChip(SearchUiState(language = "ja")))
        assertTrue(showsClearChip(SearchUiState(hideWatched = true)))
        assertTrue(showsClearChip(SearchUiState(sortOption = SortOption.TOP_RATED)))
        assertTrue(showsClearChip(SearchUiState(sortOption = SortOption.NEWEST)))
    }

    @Test fun theMediaTypeAndDefaultSortDoNotBringTheResetChip() {
        assertFalse(showsClearChip(SearchUiState(selectedType = DiscoverType.TV_SHOWS)))
        assertFalse(showsClearChip(SearchUiState(sortOption = SortOption.POPULAR)))
    }

    @Test fun pressingTheResetChipMovesTheFrameOntoTheLastRemainingChip() {
        // Eight chips, the frame on the eighth: after the press there are seven, so index 6.
        assertEquals(6, focusAfterClearChip(7))
    }

    @Test fun theFrameNeverStepsOutOfTheRowToTheLeftEither() {
        assertEquals(0, focusAfterClearChip(0))
    }

    // ── The original-language filter ────────────────────────────────────

    @Test fun exactlyTheThreeLanguagesThatUsedToBeInTheRowAreOffered() {
        assertEquals(listOf("ja", "ko", "hi"), DISCOVER_LANGUAGES)
    }

    @Test fun everyOfferedLanguageHasATextOfItsOwnRatherThanAnEnglishFallback() {
        // The English-only table in Constants is the trap here: a language without its own text
        // would read "Japanese" in the German menu.
        DISCOVER_LANGUAGES.forEach { code ->
            assertNotNull("no text for $code", languageNameRes(code))
        }
        assertNull("a code nobody offers has no text either", languageNameRes("de"))
    }

    @Test fun aLanguageSwitchesTheRowsOverToTheGridLikeEveryOtherFilter() {
        assertTrue(SearchUiState(language = "ko").hasDiscoverFilters)
        assertFalse(SearchUiState(language = null).hasDiscoverFilters)
    }

    // ── The release window behind a decade ──────────────────────────────

    private val today = "2026-09-14"

    @Test fun aFinishedDecadeAsksTmdbForExactlyThatDecade() {
        val window = releaseWindowFor(Decade(1990, 1999), year = null, today = today)
        assertEquals("1990-01-01", window.from)
        assertEquals("1999-12-31", window.to)
    }

    @Test fun theDecadeWeLiveInStopsAtTodayInsteadOfShowingUnreleasedFilms() {
        val window = releaseWindowFor(Decade(2020, 2029), year = null, today = today)
        assertEquals("2020-01-01", window.from)
        assertEquals(today, window.to)
    }

    @Test fun anExactYearStepsAsideForNothingAndCarriesTheFilterItself() {
        val window = releaseWindowFor(Decade(2020, 2029), year = 2024, today = today)
        assertNull(window.from)
        assertNull(window.to)
    }

    @Test fun withoutADecadeTheGridKeepsTheCapItAlwaysHad() {
        val window = releaseWindowFor(decade = null, year = null, today = today)
        assertNull(window.from)
        assertEquals(today, window.to)
    }
}
