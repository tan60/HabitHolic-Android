package to.make.habitholic.data.repository

import to.make.habitholic.data.model.HabitModel

interface RepoInterface {

    //load my habits from repository
    fun loadMyHabits() : List<HabitModel>

    //update my habits to repository
    fun updateMyHabits(habits: List<HabitModel>)
}