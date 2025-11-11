#!/usr/bin/env kotlin

// Reference: https://leetcode-in-java.github.io/

import kotlin.math.absoluteValue
import java.util.PriorityQueue

class Solution { 

    fun numDistinct(s: String, t: String): Int =IntArray(t.length + 1).apply { this[0] = 1 }.also { dp ->s.forEach { sourceChar ->(t.length downTo 1).forEach { j ->if (sourceChar == t[j - 1]) dp[j] += dp[j - 1]}}}[t.length]

    fun isNumber(s: String) = listOf("Infinity", "f", "D").none(s::contains) && s.toDoubleOrNull() != null

    fun solveNQueens(n: Int): List<List<String>> = (object : Function3<Array<CharArray>, Int, Int, List<List<String>>> { override operator fun invoke(board: Array<CharArray>, row: Int, currentN: Int): List<List<String>> = if (row == currentN) listOf(board.map { it.joinToString("") }) else (0 until currentN).filter { col -> (0 until row).none { i -> board[i][col] == 'Q' || (col - row + i >= 0 && board[i][col - row + i] == 'Q') || (col + row - i < currentN && board[i][col + row - i] == 'Q') } }.flatMap { col -> board.map { it.copyOf() }.toTypedArray().apply { this[row][col] = 'Q' }.let { newBoard -> this(newBoard, row + 1, currentN) } } })(Array(n) { CharArray(n) { '.' } }, 0, n) 

    fun isMatch1(s: String, p: String): Boolean = (s.length to p.length).let { (n, m) -> Array(n + 1) { _ -> BooleanArray(m + 1) }.apply { this.apply { this[0][0] = true }.also { self -> (1..m).forEach { j -> if (p[j - 1] == '*') self[0][j] = self[0][j - 1] } } }.also { dp -> (1..n).forEach { i -> (1..m).forEach { j -> dp[i][j] = if (s[i - 1] == p[j - 1] || p[j - 1] == '?') dp[i - 1][j - 1] else if (p[j - 1] == '*') (dp[i - 1][j] || dp[i][j - 1]) else false } } }[n][m] }

    fun totalNQueens(n: Int): Int = (object : Function4<IntArray, Int, Int, () -> Unit, Unit> { override operator fun invoke(board: IntArray, row: Int, currentN: Int, onSolution: () -> Unit) { if (row == currentN) onSolution() else (0 until currentN).forEach { col -> if ((0 until row).none { i -> board[i] == col || (board[i] - col).absoluteValue == (i - row).absoluteValue }) { board.copyOf().apply { this[row] = col }.let { newBoard -> this(newBoard, row + 1, currentN, onSolution) } } } } }).run { arrayOf(0).also { countRef -> IntArray(n) { -1 }.also { initialBoard -> this(initialBoard, 0, n) { countRef[0]++ } } }.let { it[0] } } 

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double =(nums1 + nums2).sorted().let { if (it.size % 2 == 0) (it[it.size / 2 - 1] + it[it.size / 2]) / 2.0 else it[it.size / 2].toDouble() }

    fun isMatch(s: String, p: String): Boolean {return Regex("^" + p + "$").matches(s)}

    fun longestValidParentheses(s: String): Int =s.foldIndexed(Pair(mutableListOf(-1), 0)) { i, (stack, maxLen), c ->when (c) {'(' -> stack.apply { add(i) } to maxLen else -> stack.apply { if (isNotEmpty()) removeAt(size - 1) }.let { if (it.isEmpty()) it.apply { add(i) } to maxLen else it to maxOf(maxLen, i - it.last()) }}}.second

    fun findSubstring(s: String, words: Array<String>): List<Int> =if (s.isEmpty() || words.isEmpty()) emptyList() else (0..s.length - words.size * words[0].length).mapNotNull { i ->words.groupingBy { it }.eachCount().let { map ->(0 until words.size).map { j ->s.substring(i + j * words[0].length, i + (j + 1) * words[0].length)}.groupingBy { it }.eachCount().takeIf { counts -> counts.all { (w, c) -> c <= map.getOrDefault(w, 0) } }?.let { i }}}

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? = head?.let {generateSequence(it) { it.next }.toList().chunked(k).map { if (it.size == k) it.asReversed() else it }.flatten().let { nodes -> nodes.also { it.zipWithNext().forEach { (a, b) -> a.next = b } }.also { it.last().next = null }.first() }}

    fun mergeKLists(lists: Array<ListNode?>): ListNode? =PriorityQueue<ListNode>(compareBy { it.`val` }).apply {lists.filterNotNull().forEach { add(it) }}.let { queue ->ListNode(0).let { dummy ->generateSequence {if (queue.isEmpty()) null else queue.poll().also { it.next?.let(queue::add) }}.fold(dummy) { current, node -> current.apply { next = node }.next!! }.let{dummy.next}}}

    fun lexicographicallySmallestString(sIn: String): String {return if (sIn.isEmpty()) "" else {Array<String>(sIn.length + 1) { "" }.also { dpArr ->Array(sIn.length) { BooleanArray(sIn.length) }.also { rt ->(2..sIn.length step 2).forEach { len ->(0..sIn.length - len).forEach { i ->rt[i][i + len - 1] = ({ a: Char, b: Char -> kotlin.math.abs(a - b) == 1 || a == 'a' && b == 'z' || a == 'z' && b == 'a' }(sIn[i], sIn[i + len - 1]) && (len == 2 || rt[i + 1][i + len - 1 - 1])) || (i + 1 until (i + len - 1) step 2).any { p -> rt[i][p] && rt[p + 1][i + len - 1] }}}}.also { remTable ->(sIn.length - 1 downTo 0).forEach { i ->(sIn[i] + dpArr[i + 1]).also { defaultVal -> dpArr[i] = defaultVal }.run {(i + 1 until sIn.length).filter { k -> ({ a: Char, b: Char -> kotlin.math.abs(a - b) == 1 || a == 'a' && b == 'z' || a == 'z' && b == 'a' }(sIn[i], sIn[k])) }.filter { k -> k - 1 < i + 1 || remTable[i + 1][k - 1] }.map { k -> dpArr[k + 1] }.minOrNull()?.let { candidate -> if (candidate < dpArr[i]) dpArr[i] = candidate }}}}}[0]}}

    fun solveSudoku(board: Array<CharArray>) {dfs(board, 0)}
    private fun dfs(board: Array<CharArray>, d: Int): Boolean =if (d == 81) true else (d / 9 to d % 9).let { (r, c) ->if (board[r][c] != '.') dfs(board, d + 1) else BooleanArray(255) { true }.apply {(0 until 9).forEach { k ->listOf(board[r][k], board[k][c], board[r / 3 * 3 + k / 3][c / 3 * 3 + k % 3]).filter { it != '.' }.forEach { this[it.code] = false }}}.let { flags ->('1'..'9').any { ch ->flags[ch.code] && dfs(board.apply { this[r][c] = ch }, d + 1).also { if (!it) board[r][c] = '.' }}}}

    fun firstMissingPositive(nums: IntArray): Int = generateSequence(1) { it + 1 }.first { missing -> missing !in nums }

    fun trap(height: IntArray): Int = height.runningReduce(::maxOf).zip(height.reversed().runningReduce(::maxOf).reversed()).mapIndexed { i, (l, r) -> minOf(l, r) - height[i] }.sum()

    fun getPermutation(n: Int, k: Int): String = IntArray(n + 1) { 1 }.also { arr -> (1..n).forEach { i -> arr[i] = arr[i - 1] * i } }.let { fact -> (object : Function5<Int, Int, BooleanArray, CharArray, Int, String> { override operator fun invoke(currentN: Int, currentK: Int, currentNums: BooleanArray, currentStr: CharArray, currentIndex: Int): String = if (currentN == 1) (0 until currentNums.size).first { !currentNums[it] }.let { idx -> currentStr.also { it[currentIndex] = (idx + 1).digitToChar() }.let { String(it) } } else if (currentK == 0) currentStr.also { str -> (0 until currentNums.size).filterNot { currentNums[it] }.fold(currentIndex) { idx, numVal -> str.also { it[idx] = (numVal + 1).digitToChar() }.let { idx + 1 } } }.let { String(it) } else fact[currentN - 1].let { divisor -> (currentK / divisor).let { divResult -> (currentK % divisor).let { modResult -> (0 until currentNums.size).filterNot { currentNums[it] }.drop(divResult).first().let { chosenNumValueIndex -> currentNums.also { it[chosenNumValueIndex] = true }.let { currentStr.also { it[currentIndex] = (chosenNumValueIndex + 1).digitToChar() } }.let { this(currentN - 1, modResult, currentNums, currentStr, currentIndex + 1) } } } } } }).run { BooleanArray(n) { false }.let { initialNums -> CharArray(n).let { initialCharArr -> this(n, k - 1, initialNums, initialCharArr, 0) } } } }

    fun largestRectangleArea(h: IntArray) = run {ArrayDeque<Int>().let { s ->var m = 0
            var i = 0
            while (i < h.size || s.isNotEmpty()) if (i < h.size && (s.isEmpty() || h[i] >= h[s.last()])) s.addLast(i++).also { } else s.removeLast().let { top -> m = m.coerceAtLeast(h[top] * (if (s.isEmpty()) i else i - s.last() - 1))}
            m}}

    fun maximalRectangle(matrix: Array<CharArray>) = if (matrix.isEmpty() || matrix[0].isEmpty()) 0 else {val c = matrix[0].size
            IntArray(c + 1).let { h ->var m = 0
                matrix.forEach { row ->row.forEachIndexed { i, ch -> h[i] = if (ch == '1') h[i] + 1 else 0 }
                    ArrayDeque<Int>().run {h.forEachIndexed { i, v ->while (isNotEmpty() && v < h[last()]) removeLast().also { top ->m = m.coerceAtLeast(h[top] * (if (isEmpty()) i else i - last() - 1))}
                            addLast(i)}}} 
                            m}}

    private val memo = mutableMapOf<String, Boolean>()
    private val freq: (String) -> List<Int> = { s -> MutableList(26) { 0 }.apply { s.forEach { this[it - 'a']++ } } }
    private val isNotAnagram: (String, String) -> Boolean = { a, b -> freq(a).toString() != freq(b).toString() }
    fun isScramble(s1: String, s2: String): Boolean = memo.getOrPut("$s1*$s2") {s1 == s2 || isNotAnagram(s1, s2).not() && (1 until s1.length).any { i ->(isScramble(s1.take(i), s2.take(i)) && isScramble(s1.drop(i), s2.drop(i))) || (isScramble(s1.take(i), s2.takeLast(i)) && isScramble(s1.drop(i), s2.dropLast(i)))}}

}


println("------------------------- 52 -------------------------")
for (n in 1..9) {
        println("n = $n -> ${Solution().totalNQueens(n)}")
}


println("------------------------- 51 -------------------------")
for (n in 1..9) {
        println("n = $n -> ${Solution().solveNQueens(n)}")
}

println("------------------------- 4 -------------------------")
println("${Solution().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))}")
println("${Solution().findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4))}")


println("------------------------- 10 -------------------------")
println("""${Solution().isMatch("aa","a")}""")
println("""${Solution().isMatch("aa","a*")}""")
println("""${Solution().isMatch("ab",".*")}""")


println("------------------------- 3563 -------------------------")
println(Solution().lexicographicallySmallestString("abc"))
println(Solution().lexicographicallySmallestString("bcda"))
println(Solution().lexicographicallySmallestString("zdce"))


println("------------------------- 23 -------------------------")

 class ListNode(var `val`: Int) {
      var next: ListNode? = null
 }

 fun printList(node: ListNode?) {
    var curr = node
    while (curr != null) {
        print("${curr.`val`} -> ")
        curr = curr.next
    }
    println("null")
}

printList(Solution().mergeKLists(arrayOf(
    ListNode(1).apply {
        next = ListNode(4).apply {
            next = ListNode(5)
        }
    },
    ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    },
    ListNode(2).apply {
        next = ListNode(6)
    }
)))

printList(Solution().mergeKLists(arrayOf<ListNode?>()))
printList(Solution().mergeKLists(arrayOf(null)))


println("------------------------- 25 -------------------------")

printList(Solution().reverseKGroup(
    ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
            next = ListNode(4).apply {
            next = ListNode(5)
        }}}
    },2))

printList(Solution().reverseKGroup(
    ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
            next = ListNode(4).apply {
            next = ListNode(5)
        }}}
    },3))


println("------------------------- 30 -------------------------")
println(Solution().findSubstring("barfoothefoobarman", arrayOf("foo", "bar")))
println(Solution().findSubstring("wordgoodgoodgoodbestword", arrayOf("word","good","best","word")))
println(Solution().findSubstring("barfoofoobarthefoobarman", arrayOf("bar","foo","the")))

println("------------------------- 32 -------------------------")
println(Solution().longestValidParentheses("(()"))
println(Solution().longestValidParentheses(")()())"))
println(Solution().longestValidParentheses(""))

println("------------------------- 37 -------------------------")
val board = arrayOf(
    charArrayOf('5','3','.','.','7','.','.','.','.'),
    charArrayOf('6','.','.','1','9','5','.','.','.'),
    charArrayOf('.','9','8','.','.','.','.','6','.'),
    charArrayOf('8','.','.','.','6','.','.','.','3'),
    charArrayOf('4','.','.','8','.','3','.','.','1'),
    charArrayOf('7','.','.','.','2','.','.','.','6'),
    charArrayOf('.','6','.','.','.','.','2','8','.'),
    charArrayOf('.','.','.','4','1','9','.','.','5'),
    charArrayOf('.','.','.','.','8','.','.','7','9')
)
Solution().solveSudoku(board)
board.forEach { println(it.joinToString(",")) }

println("------------------------- 41 -------------------------")
println(Solution().firstMissingPositive(intArrayOf(1, 2, 0)))
println(Solution().firstMissingPositive(intArrayOf(3,4,-1,1)))
println(Solution().firstMissingPositive(intArrayOf(7,8,9,11,12)))

println("------------------------- 42 -------------------------")
println(Solution().trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
println(Solution().trap(intArrayOf(4,2,0,3,2,5)))


println("------------------------- 44 -------------------------")
println(Solution().isMatch1("aa","a"))
println(Solution().isMatch1("aa","*"))
println(Solution().isMatch1("cb","?a"))

println("------------------------- 60 -------------------------")
println(Solution().getPermutation(3,3))
println(Solution().getPermutation(4,9))
println(Solution().getPermutation(3,1))

println("------------------------- 65 -------------------------")
println(Solution().isNumber("0"))
println(Solution().isNumber("e"))
println(Solution().isNumber("."))

println("------------------------- 84 -------------------------")
println(Solution().largestRectangleArea(intArrayOf(2,1,5,6,2,3)))
println(Solution().largestRectangleArea(intArrayOf(2,4)))


println("------------------------- 85 -------------------------")

println(Solution().maximalRectangle(arrayOf(
    charArrayOf('1','0','1','0','0'),
    charArrayOf('1','0','1','1','1'),
    charArrayOf('1','1','1','1','1'),
    charArrayOf('1','0','0','1','0')
)))
println(Solution().maximalRectangle(arrayOf(charArrayOf('0'))))
println(Solution().maximalRectangle(arrayOf(charArrayOf('1'))))

println("------------------------- 87 -------------------------")

private val memo = mutableMapOf<String, Boolean>()
private val freq: (String) -> List<Int> = { s -> MutableList(26) { 0 }.apply { s.forEach { this[it - 'a']++ } } }
private val isNotAnagram: (String, String) -> Boolean = { a, b -> freq(a).toString() != freq(b).toString() }
fun isScramble(s1: String, s2: String): Boolean = memo.getOrPut("$s1*$s2") {s1 == s2 || isNotAnagram(s1, s2).not() && (1 until s1.length).any { i ->(isScramble(s1.take(i), s2.take(i)) && isScramble(s1.drop(i), s2.drop(i))) || (isScramble(s1.take(i), s2.takeLast(i)) && isScramble(s1.drop(i), s2.dropLast(i)))}}

println(Solution().isScramble("great", "rgeat"))
println(Solution().isScramble("abcde", "caebd"))
println(Solution().isScramble("a", "a"))

println("------------------------- 115 -------------------------")
println(Solution().numDistinct("rabbbit", "rabbit"))
println(Solution().numDistinct("babgbag", "bag"))


println("--------------------------------------------------")