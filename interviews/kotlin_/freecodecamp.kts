main()

fun main(){
   variables()
   conditionals()
   inline_function()
   arrays()
   lists_maps()
    classes()
}

fun variables() {
    // Destructuring declaration with nullable types
    var (a: Int?, b: Int?, c: Int?) = listOf(1, 2, 4)

    // Declaring an unused variable of type Short (primitive type)
    var d: Short

    // !! = Not-null assertion operator (will throw NullPointerException if any are null)
    println(a!! + b!! + c!!)

    // Elvis operator (?:) provides fallback default value when variable is null
    println(a ?: 0 + b ?: 0 + c ?: 0)

    // Creating a list of Int from 1 to 3 using range
    var list = (1..3).toList()

    // Safe element access from list (returns null if index out of bounds)
    a = list.getOrNull(0) // 1
    b = list.getOrNull(1) // 2
    c = list.getOrNull(2) // 3

    println("$a,$b,$c")  // Outputs: 1,2,3

    // Creating a reversed list using range with downTo
    list = (-1 downTo -3).toList()  // [-1, -2, -3]

    // Regular indexed access (unsafe if out of bounds, but safe here)
    a = list[0] // -1
    b = list[1] // -2
    c = list[2] // -3

    println("$a,$b,$c")

    // Assigning null to a nullable variable
    a = null

    // Prints null (nullable value)
    println(a)

    // Nullable string
    val name: String? = null

    // Safe call operator ?. returns null instead of throwing
    println(name?.length) // null

    
}


fun conditionals(){

    // Safe call operator
    var greeting: String? = null
    if(greeting == null)println(2)
    else println(3)

    // when expression
    greeting = "Hello"
    when(greeting){
        null -> {
            println("null")
        }
        else -> {
            println("else")
        }
    }

    // Elvis operator
    val length = greeting?.length ?: 0  // ✅ Fallback to 0 if null
    println(length)

    // Inline if-else expression
    var greetingToPrint = if(greeting != null)greeting else "Hi"
    println(greetingToPrint)
    println("/****************************/")
    // inline when expression
    greetingToPrint = when(greeting){null -> "Hi"; else -> greetingToPrint}
    println(greetingToPrint)
    println("/****************************/")

}

inline fun doSomething(action: () -> Unit) {
    println("Before")
    action()
    println("After")
}

fun inline_function(){
    """
    ✅ inline Function in Kotlin
       An inline function is a compiler directive that inlines the function body at the call site — i.e., instead of making a function call at runtime, the compiler copies the function code where it's called.

    📌 Why use inline?
    Mainly for:

        Performance optimization (by removing function call overhead).
        Allowing non-local returns in lambda arguments.
        Avoiding memory allocations in higher-order functions.
    """
        doSomething {
            println("Doing something")
        }
}


fun arrays(){
    var arrays = arrayOf("Java","Kotlin","Groovy")
    println(arrays.random())
    for(array in arrays)println(array)
    println(arrays.size)
    println(arrays.getOrNull(0))
    println(arrays.get(0))
    println(arrays[0])
    arrays.forEach { it -> it.repeat(2) }

    for(array in arrays)println(array)

    arrays.forEach{array -> println(array)}
    arrays.forEachIndexed { index, string ->
        println("$string at index $index") }

    println(arrays.hashCode())
    println(arrays.component3())
    println(arrays.binarySearch("Java",2))

}


fun lists_maps(){
    val lists = listOf("Java","Kotlin","Groovy")
    println(
    lists.withIndex().associate { it.index to it.value }
    )

    println(mapOf(1 to 1, 2 to 2, 3 to 3).let{
        """$it |
        Kotlin class : ${it::class} | 
        Java class : ${it::class.java}
        """
    })
    println(mapOf(Pair(1,1), Pair(2, 2), Pair(3,3)).let{
        """$it |
        Kotlin class : ${it::class} | 
        Java class : ${it::class.java}
        """
    })
    println(hashMapOf("a" to 1, "b" to 2).let{
        """$it |
        Kotlin class : ${it::class} | 
        Java class : ${it::class.java}
        """
    })
    var maps = mapOf(1 to 1, 2 to 2, 3 to 3)
    var mutableMap = maps.toMutableMap()
    println(mutableMap)
    mutableMap = mutableMap
        .map { (k,v) -> (k*2 to v*2) }
        .toMap()
        .toMutableMap()
    mutableMap.forEach { element -> println(element) }
    println("""
        ${mutableMap.map { (k,v) -> (k*2 to v*2) }::class.java} ,
        ${mutableMap.map { (k, v) -> (k * 2 to v * 2) }.toMap()::class.java} ,
        ${mutableMap.map { (k, v) -> (k * 2 to v * 2) }.toMap().toMutableMap()::class.java} ,
        """
    )

    // using spread operator here
    fun printInts(vararg numbers: Int): String {
        return ("Spread numbers: ${numbers.joinToString()}")
    }

    println(
        arrayOf(
            listOf(
                arrayOf(10,20,30)
            )
        ).let {
            """
            ${
                it.joinToString { innerList ->
                    innerList.joinToString { innerArray ->
                        innerArray.joinToString()
                    }
                }
            }
        """.trimIndent()
        }.let { flattenedString ->
            // Second transformation: modify or reuse the string if needed
            """
                Flattened result: $flattenedString , 
                Reversed result: ${
                    flattenedString
                    .split(",").reversed().joinToString(" , ")
                        .split(",").reversed().joinToString(" , ")
                        .split(",").reversed().joinToString(" , ")
                },
                List:${
                    flattenedString.split(",").map { it ->it.trim().toInt() }
                },
                Spread:${
                // Spread operator (*) works only on arrays, not on List, so you must convert it using toIntArray(), toTypedArray(), etc.
                flattenedString.split(",").map { it ->it.trim().toInt() }
                    .toIntArray().joinToString()
                },
                ${printInts(*arrayOf(10,20,30).toIntArray())}
            """
        }
    )


}


fun classes(){
    class Person(_firstName:String, _lastName:String){
        lateinit var firstName: String
        lateinit var lastName: String

        init{
            firstName = _firstName
            lastName = _lastName
        }

        constructor():this("p","s") {
            println("this is secondary constructor")
        }

        var nickname:String? = null
            set(value) {

            }



    }

    //In Kotlin, a constructor is a special function that is used to create and initialize an object of a class.
    // 1. Primary constructor - This is defined in the class header itself.
    var person = Person("test","user")

    // 2. Secondary constructor - You can also define one or more secondary constructors inside the class using the constructor keyword.
    val person2 = Person() // prints: secondary constructor

    // property access syntax - in Kotlin, we can reference properties directly by their name without wrorrying about their getter / setter
    println("""
        ${person.firstName},
        ${person.lastName}
    """)

    println("""......
        ${person2}
        ${person2.firstName},
        ${person2.lastName}
    """)



}