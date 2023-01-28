package to.make.habitholic.data.model

data class HabitModel(
    val title: String,
    var description: String?,
    var goal: String?
) {

    //normal constructor
    constructor(title: String) : this(title, null, null)

    //constructor using builder
    constructor(builder: Builder) : this(builder.title, builder.description, builder.goal)

    class Builder(var title: String) {
        var description: String? = null
        var goal: String? = null

        /**
         * builder를 제공하려면 아래와 같이 제공
         * 하지만 그럴 필요는 없어 보임
         * 필수 멤버 변수가 아닌 경우 맴버 변수를 var로 선언하고 그냥 apply 쓰면 될 듯
         *  val habit = HabitModel("title $i").apply {
                description = "description $i"
                goal = "goal $i"
            }
         */
        fun description(description: String) = apply {this.description = description}
        fun goal(goal: String) = apply {this.goal = goal}
        fun build() = HabitModel(this)
    }
}