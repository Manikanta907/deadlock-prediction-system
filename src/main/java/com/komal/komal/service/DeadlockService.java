package com.komal.komal.service;

import com.komal.komal.model.Edge;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DeadlockService {

    public boolean detectDeadlock(List<Edge> edges) {

        Map<String, List<String>> graph = new HashMap<>();

        for (Edge e : edges) {
            graph.putIfAbsent(e.from, new ArrayList<>());
            graph.get(e.from).add(e.to);
        }

        Set<String> visited = new HashSet<>();
        Set<String> stack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (dfs(node, graph, visited, stack)) return true;
        }

        return false;
    }

    private boolean dfs(String node, Map<String, List<String>> graph,
                        Set<String> visited, Set<String> stack) {

        if (stack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        stack.add(node);

        for (String n : graph.getOrDefault(node, new ArrayList<>())) {
            if (dfs(n, graph, visited, stack)) return true;
        }

        stack.remove(node);
        return false;
    }
}