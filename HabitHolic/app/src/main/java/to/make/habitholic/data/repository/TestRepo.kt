package to.make.habitholic.data.repository

import to.make.habitholic.data.model.HabitModel

object TestRepo : RepoInterface {
    override fun loadMyHabits(): List<HabitModel> {
        var habitModels : MutableList<HabitModel> = mutableListOf()

        for(i in 0 until 10) {

            val habit = HabitModel("title $i").apply {
                description = "description $i"
                goal = "goal $i"
            }

            val habitBuilder = HabitModel.Builder("title")
                .description("description $i")
                .goal("goal $i")

            habitModels.add(habit)
            //habitModels.add(habitBuilder.build())
        }

        return habitModels
    }

    override fun updateMyHabits(habits: List<HabitModel>) {
        TODO("Not yet implemented")
    }
}