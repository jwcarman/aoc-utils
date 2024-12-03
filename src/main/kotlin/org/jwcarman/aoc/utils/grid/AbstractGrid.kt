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

abstract class AbstractGrid<T : Any> : Grid<T> {
    final override fun get(x: Int, y: Int): T {
        verifyPoint(this, x, y)
        return getImpl(x, y)
    }

    override fun set(x: Int, y: Int, value: T) {
        verifyPoint(this, x, y)
        setImpl(x, y, value)
    }

    protected abstract fun setImpl(x: Int, y: Int, value: T):Unit

    protected abstract fun getImpl(x: Int, y: Int): T
}