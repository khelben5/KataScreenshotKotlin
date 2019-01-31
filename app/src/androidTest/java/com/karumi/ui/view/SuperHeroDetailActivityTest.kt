package com.karumi.ui.view

import android.widget.ScrollView
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.R
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.karumi.mothers.HeroMother
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.android.synthetic.main.super_hero_detail_activity.view.*
import org.junit.Test
import org.mockito.Mock

class SuperHeroDetailActivityTest : AcceptanceTest<SuperHeroDetailActivity>(SuperHeroDetailActivity::class.java) {

    @Mock
    private lateinit var repository: SuperHeroRepository


    @Test
    fun showsAvengerDetail() {
        givenAHero(HeroMother.givenAHero { isAvenger = true })

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsRegularHeroDetail() {
        givenAHero(HeroMother.givenAHero { isAvenger = false })

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAHeroWithAVeryLongName() {
        givenAHero(HeroMother.givenAHeroWithVeryLongName())

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsAHeroWithAVeryLongDescription() {
        givenAHero(HeroMother.givenAHeroWithVeryLongDescription())

        val activity = startActivity()

        compareScreenshot(activity)
    }

    private fun givenAHero(hero: SuperHero) {
        doReturn(hero).whenever(repository).getByName(any())
    }

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }

}
