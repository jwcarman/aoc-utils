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

package org.jwcarman.aoc.utils.graph

interface Graph<V : Any, E : Any> {
    fun addVertex(value: V)
    fun addEdge(from: V, to: V, value: E, weight: Double = 0.0)

    fun vertices(): Set<V>

    fun edge(from: V, to: V): Edge<E>?

    fun edges(from:V): List<Edge<E>>

    fun neighbors(from: V): List<V>

    fun shortestPaths(start: V): ShortestPaths<V> =
        Graphs.shortestPaths(start, vertices(), ::neighbors) { from, to -> edge(from, to)!!.weight }

    fun dfs(start: V, end: V): List<V> = Graphs.dfs(start, end, ::neighbors)
    fun bfs(start: V, end: V): List<V> = Graphs.bfs(start, end, ::neighbors)

    fun reachable(start: V, maxSteps: Int) = Graphs.reachable(start, maxSteps, ::neighbors)

}