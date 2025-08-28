package org.example
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.abs

class Point constructor(val x: Double, val y: Double) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }
    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }
    fun between(other: Point): Double {
        return sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    }

    companion object {
        fun collinear(vararg points: Point): Boolean {
            for (idx in 2..<points.size) {
                val last = points[idx-1] - points[idx-2]
                val now = points[idx] - points[idx-1]
                println("%x: ${(now.x - last.x) % 1}")
                println("%y: ${(now.y - last.y) % 1}")
                if ((now.x % last.x) % 1.0 != 0.0) return false
                if ((now.y % last.y) % 1.0 != 0.0) return false
            }
            return true
        }
    }
}

interface Shape2D {
    fun area(): Double
    fun perimeter(): Double
}

class Triangle constructor(private val points: Array<Point>): Shape2D {

    val a: Double = abs(points[0].between(points[1]))
    val b: Double = abs(points[1].between(points[2]))
    val c: Double = abs(points[2].between(points[0]))

    override fun area(): Double {
        val p = perimeter() / 2
        return sqrt(p * (p-a) * (p-b) * (p-c))
    }

    override fun perimeter(): Double {
        return a + b + c
    }
}

fun main() {
    val triangle = Triangle(arrayOf(Point(-3.0, -1.0), Point(2.0, 3.0), Point(2.0, -1.0)))
    println(triangle.perimeter())
    println(triangle.area())

    val points = arrayOf<Point>(Point(1.0, 0.0), Point(3.0, 2.0), Point(6.0, 5.0), Point(10.0, 9.0))
    println(Point.collinear(*points))
}