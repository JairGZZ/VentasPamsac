package com.jair.ventaspamsac.Aprendizaje

import androidx.room.Index
import java.util.Stack

class Solution {

//    22. Generate Parentheses
//    Easy
//    Topics
//    Companies
//    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//
//
//    Example 1:
//
//    Input: n = 3
//    Output: ["((()))","(()())","(())()","()(())","()()()"]
//    Example 2:
//
//    Input: n = 1
//    Output: ["()"]
//
//
//    Constraints:
//
//    1 <= n <= 8

//    fun generateParenthesis(n: Int): List<String> {
//        for (i;  i<n;i++)
//    }


    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()


        fun backtrack(current: String, open: Int, close: Int) {
            if (current.length == n * 2) {
                result.add(current)
                return
            }

            if (open < n) {
                backtrack("$current(", open + 1, close)
            }

            if (close < open) {
                backtrack("$current)", open, close + 1)
            }
        }

        backtrack("", 0, 0)
        return result
    }

    fun removeDuplicates(nums: IntArray): Int {
        var index = 0
        for (i in 1 until nums.size) {
            if (nums[i] != nums[index]) {
                nums[index] = nums[i]
                index++
            }
        }

        return index +1
    }

    fun removeElement(nums: IntArray, n: Int): Int {

        var contador = 0

        for (i in nums.indices){
            if (nums[i] !=n ){

                nums[contador] = nums[i]
                contador++
            }


        }

        return  contador
    }
    fun substring(haystack: String,startIndex:Int ,endIndex: Int):String{
        var stringBuilder = StringBuilder()
        for (i in startIndex..endIndex){
            stringBuilder.append(haystack[i])
        }
        return stringBuilder.toString()
    }
    fun strStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0

        for (i in 0..haystack.length - needle.length) {
            if (substring(haystack,i,i+needle.length) == needle) {
                return i
            }
        }


        return -1
    }

    fun astrStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0
        for (i in 0 ..  haystack.length - needle.length) {
            var match = true
            for (j in needle.indices) {
                if (haystack[i + j] != needle[j]) {
                    match = false
                    break
                }
            }

            if (match) return i
        }

        return -1
    }

    //mi resolucion

//    fun searchInsert(nums: IntArray, target: Int): Int {
//        if (target < nums[0]){
//            return 0
//        }
//        if (target > nums[nums.size-1]){
//            return nums.size
//        }
//
//        for (i in nums.indices){
//            if(nums[i] == target){
//                return i
//            }
//        }
//
//        if (target < nums[1]){
//            return 1
//        }
//        if(target > nums[nums.size-2]){
//            return nums.size-1
//        }
//        for (i in 1.. nums.size-2){
//            if(target > nums[i-1] && target <nums[i]){
//                return i
//            }
//        }
//
//
//        return 4
//    }


    // solucion con busqueda binaria
    fun searchInsert(nums: IntArray, target: Int): Int {
        var low = 0
        var high = nums.size - 1

        while (low <= high) {
            val mid = low + (high - low) / 2

            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }


        return low
    }


}
fun main() {
    val numeros = IntArray(5) // Crea un arreglo de 5 elementos inicializados en 0
    numeros[0] = 1
    numeros[1] = 2
    numeros[2] = 4
    numeros[3] = 6
    numeros[4] = 7
//    numeros[5] = 6
//    numeros[6] = 8
//    numeros[7] = 9


    val solution = Solution().searchInsert(numeros,3)
//    val str = Solution().strStr("hellallo","llo")

    val mutable = mutableListOf<Char>()

    mutable.add('a')
    mutable.add('b')
    mutable.add('c')

    println(solution)



//    println(str)
//
//    for (item in  solution){
//        println(item)
//    }
}

