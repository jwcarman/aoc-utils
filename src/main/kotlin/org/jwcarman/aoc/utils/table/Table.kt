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

package org.jwcarman.aoc.utils.table


class Table<K1, K2, V> {
    private val values = mutableMapOf<K1, MutableMap<K2, V>>()
    operator fun get(key1: K1, key2: K2): V? {
        return values[key1]?.get(key2)
    }

    operator fun set(key1: K1, key2: K2, value: V) {
        values.computeIfAbsent(key1) { mutableMapOf() }[key2] = value
    }

    fun contains(key1: K1, key2: K2) = values[key1]?.containsKey(key2) ?: false

    fun isEmpty() = values.isEmpty()
}