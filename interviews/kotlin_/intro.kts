//import java.util.Scanner
//import java.io.BufferedReader
//import java.io.InputStreamReader

import java.util.*
import java.io.*

main()

fun main() : Unit{
    inputs()
    intro()
}

fun intro() : Unit{
    println(5.toDouble())
}

fun inputs() : Unit{
    println("Hi from intro")
    // Immutable : Primitive data types (Int, Double, Float, Long, Short, Byte, UInt, ULong, UShort, UByte) are all immutable values.
    var a = listOf<Int>()
    println(a::class.java)
    var a_ = a.toMutableList()
    a_.add(2)
    println(a_::class.java)
    println(a_::class) 
    println(a_.toList())
    println(a_::class) 
    println(a_::class.java)
    a_ = a_.toMutableList()

    a_.add(22)
    println(a_)

    // readLine
    print("Enter an input: ")
    var input = readLine()!!.split(" ").map{it.toInt()}
    var number = input[0].toString()
    println("Input repeated is:" + number.repeat(2))
    println(input::class.java)
    println(number::class.java)

    // Scanner
    val scanner = Scanner(System.`in`)
    print("Enter your name: ")
    val name = scanner.nextLine()
    println("Hello, $name! ")
    scanner.close();

}
