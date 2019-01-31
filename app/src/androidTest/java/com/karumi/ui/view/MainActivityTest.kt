package com.karumi.ui.view

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.karumi.mothers.HeroMother
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mock

class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Mock
    private lateinit var repository: SuperHeroRepository

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAListOfSuperHeroes() {
        givenThereAreSomeSuperHeroes(numberOfSuperHeroes = 5, avengers = true)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAListOfMixedSuperHeroes() {
        givenAListOfMixedSuperHeroes(numberOfHeroes = 5)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsJustOneHero() {
        givenThereAreSomeSuperHeroes(numberOfSuperHeroes = 1)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAHeroWithAVeryLongName() {
        givenThereIsAHeroWithAVeryLongName()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAHeroWithAnEmptyName() {
        givenThereIsAHeroWithAnEmptyName()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAHeroWithAnSingleLetterName() {
        givenThereIsAHeroWithASingleLineName()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    private fun givenThereAreSomeSuperHeroes(
        numberOfSuperHeroes: Int = 1,
        avengers: Boolean = false
    ): List<SuperHero> {
        val superHeroes = (0 until numberOfSuperHeroes).map {
            HeroMother.givenAHero(it) { isAvenger = avengers }
        }

        whenever(repository.getAllSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreNoSuperHeroes() {
        whenever(repository.getAllSuperHeroes()).thenReturn(emptyList())
    }

    private fun givenAListOfMixedSuperHeroes(numberOfHeroes: Int) {
        whenever(repository.getAllSuperHeroes()).thenReturn(
            (1..numberOfHeroes).map {
                HeroMother.givenAHero(it) { isAvenger = it % 2 == 0 }
            }
        )
    }

    private fun givenThereIsAHeroWithAVeryLongName() {
        whenever(repository.getAllSuperHeroes()).thenReturn(
            listOf(
                HeroMother.givenAHero {
                    name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed maximus " +
                        "maximus pharetra. Vestibulum vitae orci eros. Aliquam non ipsum lobortis, " +
                        "posuere lacus id, placerat justo. Morbi et facilisis urna. Vestibulum " +
                        "vitae ornare tellus. Morbi lectus dolor, dictum sit amet volutpat " +
                        "elementum, maximus quis ligula. Sed id rutrum eros, et ultricies nulla. " +
                        "Praesent eget velit erat. Nunc mollis non lectus a hendrerit. Aenean " +
                        "auctor quis nibh vel ultrices."
                }
            )
        )
    }

    private fun givenThereIsAHeroWithAnEmptyName() {
        whenever(repository.getAllSuperHeroes()).thenReturn(
            listOf(HeroMother.givenAHero { name = "" })
        )
    }

    private fun givenThereIsAHeroWithASingleLineName() {
        whenever(repository.getAllSuperHeroes()).thenReturn(
            listOf(HeroMother.givenAHero { name = "A" })
        )
    }

    override val testDependencies = Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }

}
