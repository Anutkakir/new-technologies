package com.ivan.newtechnologies.guava.graph;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final MutableGraph<Integer> graph = GraphBuilder.directed().build();

        final Integer node1 = 1;
        final Integer node2 = 2;
        final Integer node3 = 3;
        final Integer node4 = 4;
        final Integer node5 = 5;
        final Integer node6 = 6;

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);

        graph.putEdge(node1, node2);
        graph.putEdge(node2, node3);
        graph.putEdge(node2, node4);
        graph.putEdge(node5, node2);
        graph.putEdge(node4, node6);
        graph.putEdge(node6, node1);

        System.out.println("Adjacent nodes: " + graph.adjacentNodes(node1));
        System.out.println("Successors: " + graph.successors(node1));
        System.out.println("Predecessors" + graph.predecessors(node1));

        System.out.println("Reachable nodes: " + getAllReachableNodes(graph, node1, new HashSet<>()));
    }

    private static Set<Integer> getAllReachableNodes(final MutableGraph<Integer> graph, final Integer initial, final HashSet<Integer> processed) {
        final Set<Integer> successors = graph.successors(initial);

        if (processed.contains(initial)) {
            return Collections.emptySet();
        }

        processed.add(initial);

        System.out.println("Successors nodes of node " + initial + " - " + successors);

        final Set<Integer> collect = successors
                .stream()
                .map(node -> getAllReachableNodes(graph, node, processed))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        collect.addAll(successors);
        collect.add(initial);

        return collect;
    }

}
