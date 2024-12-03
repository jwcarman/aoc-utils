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

fun <T : Any> Array<Array<T>>.toGrid() = ArrayGrid(this)

class ArrayGrid<T : Any>(private val values: Array<Array<T>>) : AbstractGrid<T>() {

    private val width: Int = values.maxOf { it.size }
    private val height: Int = values.size

    init {
        require(!values.any { it.size != width }) { "All elements in values array should be of size $width" }
    }

    override fun getImpl(x: Int, y: Int) = values[y][x]
    override fun setImpl(x: Int, y: Int, value: T) {
        values[y][x] = value
    }

    override fun width() = width
    override fun height() = height
    override fun rowAt(y: Int) = values[y].asSequence()
}