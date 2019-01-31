package com.karumi.mothers

import com.karumi.domain.model.SuperHero
import com.karumi.domain.model.Team

private const val LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed maximus " +
    "maximus pharetra. Vestibulum vitae orci eros. Aliquam non ipsum lobortis, " +
    "posuere lacus id, placerat justo. Morbi et facilisis urna. Vestibulum " +
    "vitae ornare tellus. Morbi lectus dolor, dictum sit amet volutpat " +
    "elementum, maximus quis ligula. Sed id rutrum eros, et ultricies nulla. " +
    "Praesent eget velit erat. Nunc mollis non lectus a hendrerit. Aenean " +
    "auctor quis nibh vel ultrices."


class HeroMother {

    companion object {

        fun givenAHero(id: Int = 1, block: HeroBuilder.() -> Unit): SuperHero =
            HeroBuilder(id).apply(block).build()

        fun givenAHeroWithVeryLongName(isAvenger: Boolean = false) = HeroMother.givenAHero {
            this.name = LOREM_IPSUM
            this.isAvenger = isAvenger
        }

        fun givenAHeroWithVeryLongDescription() = HeroMother.givenAHero { name = LOREM_IPSUM }
    }


    class HeroBuilder(id: Int) {
        var name = "SuperHero - $id"
        var description = "Description Super Hero - $id"
        var isAvenger = false
        var isAvailable = true
        var team: Team? = null

        fun build() = SuperHero(name, null, isAvenger, team, isAvailable, description)
    }
}
