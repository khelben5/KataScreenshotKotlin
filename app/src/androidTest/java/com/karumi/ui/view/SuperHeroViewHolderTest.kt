package com.karumi.ui.view

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.view.LayoutInflater
import com.karumi.R
import com.karumi.domain.model.Team
import com.karumi.mothers.HeroMother
import com.karumi.ui.presenter.SuperHeroesPresenter
import com.karumi.ui.view.adapter.SuperHeroViewHolder
import org.junit.Test
import org.mockito.Mockito.mock

class SuperHeroViewHolderTest : ScreenshotTest {

    @Test
    fun showsAnySuperHero() {
        val superHero = HeroMother.givenAHero { }
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerWithNoTeam() {
        val avenger = HeroMother.givenAHero { isAvenger = true }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnRegularHero() {
        val avenger = HeroMother.givenAHero { isAvenger = false }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerWithShortName() {
        val avenger = HeroMother.givenAHero {
            isAvenger = true
            name = "A"
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerWithLongName() {
        val avenger = HeroMother.givenAHeroWithVeryLongName(isAvenger = true)
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerWithNoName() {
        val avenger = HeroMother.givenAHero {
            name = ""
            isAvenger = true
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerFromWestCoastTeam() {
        val avenger = HeroMother.givenAHero {
            name = "Any name"
            isAvenger = true
            team = Team.WEST_COAST
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvengerFromEastCoastTeam() {
        val avenger = HeroMother.givenAHero {
            name = "Any name"
            isAvenger = true
            team = Team.EAST_COAST
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnAvailableAvengerFromEastCoastTeam() {
        val avenger = HeroMother.givenAHero {
            name = "Any name"
            isAvenger = true
            team = Team.EAST_COAST
            isAvailable = true
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAnUnAvailableAvengerFromEastCoastTeam() {
        val avenger = HeroMother.givenAHero {
            name = "Any name"
            isAvenger = true
            team = Team.EAST_COAST
            isAvailable = false
        }
        val holder = givenASuperHeroViewHolder()

        holder.render(avenger)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    private fun givenASuperHeroViewHolder(): SuperHeroViewHolder {
        val context = getInstrumentation().targetContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.super_hero_row, null, false)
        return SuperHeroViewHolder(
            view,
            mock<SuperHeroesPresenter>(SuperHeroesPresenter::class.java)
        )
    }
}
