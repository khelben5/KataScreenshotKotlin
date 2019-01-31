package com.karumi.mothers

import com.karumi.domain.model.SuperHero

class HeroMother {

    companion object {
        fun givenAHero(id: Int = 1, block: HeroBuilder.() -> Unit): SuperHero =
            HeroBuilder(id).apply(block).build()
    }


    class HeroBuilder(id: Int) {
        var name = "SuperHero - $id"
        var description = "Description Super Hero - $id"
        var isAvenger = false

        fun build() = SuperHero(name, null, isAvenger, description)
    }
}
