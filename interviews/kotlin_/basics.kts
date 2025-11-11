main()

fun main(){
    println("lambdas:")
    lambdas()
    println("sorting:")
    sorting()
    println("maps:")
    maps()
    println("maps1:")
    maps1()
    println("overloading:")
    overloading()
}

fun lambdas(){
    var doubled = readLine()!!.split(" ").map{it.toInt()}.map { it * 2 } 
    println(doubled)
    
    val numbers = readLine()!!.split(" ").map { it.toLong() }
    val evenSquaresSum = numbers
    .filter { it % 2 == 0L }       // keep only even numbers
    .map { it * it }              // square them
    .reduce { acc, i -> acc + i } // sum them
    println("Sum of squares of even numbers: $evenSquaresSum")

}

fun sorting(){
    // no in-place

    // sorted
    val numbers = listOf(5, 2, 9, 1)
    val sorted = numbers.sorted()

    // sortedDescending
    println(sorted)  // Output: [1, 2, 5, 9]
    val sortedDesc = numbers.sortedDescending()
    println(sortedDesc)  // Output: [9, 5, 2, 1]

    // sortedBy
    val words = listOf("apple", "banana", "kiwi")
    val sortedByLength = words.sortedBy { it.length }
    println(sortedByLength)  // Output: [kiwi, apple, banana]

    // in-place

    // sort()
    val mutable = mutableListOf(4, 1, 3)
    mutable.sort()
    println(mutable)  // Output: [1, 3, 4]

    // sortDescending()
    mutable.sortDescending()
    println(mutable)  // Output: [4, 3, 1]
}

fun overloading(){
        // 🔹 Function Overloading
        // Definition: Same function name with different parameter lists (different number or types of parameters) within the same class or scope.
        // Purpose: Provide multiple ways to call a function with different inputs.
        // Resolved at: Compile time (static polymorphism).

        // 🔹 Function Overriding
        //Definition: Subclass provides its own implementation of a function declared in a superclass or interface.
        //Purpose: Modify or extend behavior of a base class function.
        //Requires: open modifier on base function, and override modifier in subclass.
        //Resolved at: Runtime (dynamic polymorphism).

    val calculator = Calculator()
    println(calculator.add(2,3))
}

class Calculator {
    fun add(a: Int, b: Int): Int = a + b
    fun add(a: Int, b: Int, c: Int): Int = a + b + c
    fun add(a: Double, b: Double): Double = a + b
}

fun maps(){
    println("/*******************/")
    var a = mutableListOf(1,2,3)
    var mapped = a.map{listOf(it,-it)}
    println(mapped)
    val flatMapped = a.flatMap { listOf(it, -it) }
    println(flatMapped)
}

fun maps1(){
    // let , also -> use it -> You access the object using it (default name of the lambda argument).
    // apply , run, with -> use this (You don't need to refer to the object explicitly—you can access its members directly.)
        /*
        obj.apply { // configure    }         // returns obj
        obj.also { println(it) }                 // returns obj
        obj.let { it.length }                    // returns lambda result
        obj.run { doSomething(); "result" }      // returns lambda result
        with(obj) { doSomething(); "result" }    // returns lambda result
        */


    // also
    println("/*******************/")
    mutableListOf(1, 2, 30).also { a ->
        a.map { listOf(it, -it) }.also { println(it) } 
        a.flatMap { listOf(it, -it) }.also { println(it) }
    }
    
    println("/*******************/")
    // You can chain here because `a` is in scope
    mutableListOf(1, 2, 30).also{a ->
        a.map { listOf(it, -it) }.also { println(it) }
        .let { 
            a.flatMap { listOf(it, -it) }.also { println(it) } 
        }
    }


}