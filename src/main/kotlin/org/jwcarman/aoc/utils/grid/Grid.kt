/*
 * Copyright (c) 2024 James Carman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jwcarman.aoc.utils.grid

import org.jwcarman.aoc.utils.geom.plane.Point2D


interface Grid<T : Any> {
    operator fun get(coordinate: Point2D) = get(coordinate.x, coordinate.y)

    operator fun set(coordinate:Point2D, value:T) = set(coordinate.x, coordinate.y, value)

    fun values() = sequence {
        yRange().forEach { y ->
            xRange().forEach { x ->
                yield(get(x, y))
            }
        }
    }

    fun coordinates() = sequence {
        yRange().forEach { y ->
            xRange().forEach { x ->
                yield(Point2D(x, y))
            }
        }
    }

    fun neighborsOf(coordinate: Point2D) = sequence {
        coordinate.neighbors().forEach { neighbor ->
            if (neighbor in this@Grid) {
                yield(neighbor)
            }
        }
    }

    operator fun get(x: Int, y: Int): T

    operator fun set(x: Int, y: Int, value: T)

    fun size() = width() * height()

    fun width(): Int

    fun height(): Int

    fun rows() = sequence {
        yRange().forEach { y ->
            yield(rowAt(y))
        }
    }

    fun columns() = sequence {
        xRange().forEach { x ->
            yield(columnAt(x))
        }
    }

    fun rowAt(y: Int) = sequence {
        xRange().forEach { x ->
            yield(get(x, y))
        }
    }

    fun columnAt(x: Int) = sequence {
        yRange().forEach { y ->
            yield(get(x, y))
        }
    }

    fun xRange() = 0 until width()

    fun yRange() = 0 until height()

    operator fun contains(point: Point2D) = point.x in xRange() && point.y in yRange()

    fun coordinatesWithValues() = coordinates().map { Pair(it, get(it)) }

}