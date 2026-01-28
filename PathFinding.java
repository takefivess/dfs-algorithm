import java.util.*;

/**
 * DFS 알고리즘 예제 3: 경로 찾기
 * 
 * 방향 그래프에서 출발 정점에서 도착 정점까지의 경로가 존재하는지 판단하고,
 * 존재한다면 하나의 경로를 찾아 출력합니다.
 */
public class PathFinding {
    
    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private List<Integer> path;
    private int target;
    
    public PathFinding(int nodeCount) {
        this.graph = new HashMap<>();
        this.visited = new boolean[nodeCount + 1];
        this.path = new ArrayList<>();
        
        for (int i = 1; i <= nodeCount; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    
    /**
     * 방향 간선 추가
     */
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }
    
    /**
     * 경로 찾기
     * 
     * 시간복잡도: O(V + E)
     * 공간복잡도: O(V)
     */
    public PathResult findPath(int start, int end, int nodeCount) {
        Arrays.fill(visited, false);
        path.clear();
        target = end;
        
        if (dfs(start, new ArrayList<>())) {
            return new PathResult(true, path);
        }
        return new PathResult(false, new ArrayList<>());
    }
    
    /**
     * DFS를 이용한 경로 탐색
     */
    private boolean dfs(int node, List<Integer> currentPath) {
        // 목표 노드에 도달
        if (node == target) {
            currentPath.add(node);
            path = new ArrayList<>(currentPath);
            return true;
        }
        
        visited[node] = true;
        currentPath.add(node);
        
        // 인접 노드들 탐색
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, currentPath)) {
                    return true;
                }
            }
        }
        
        currentPath.remove(currentPath.size() - 1);
        return false;
    }
    
    /**
     * 경로 탐색 결과 클래스
     */
    public static class PathResult {
        public boolean found;
        public List<Integer> path;
        
        public PathResult(boolean found, List<Integer> path) {
            this.found = found;
            this.path = path;
        }
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        PathFinding pf = new PathFinding(6);
        
        // 간선 추가
        pf.addEdge(1, 2);
        pf.addEdge(1, 3);
        pf.addEdge(2, 4);
        pf.addEdge(3, 2);
        pf.addEdge(3, 5);
        pf.addEdge(4, 5);
        pf.addEdge(5, 6);
        
        PathResult result = pf.findPath(1, 6, 6);
        
        if (result.found) {
            System.out.println("경로 존재: Yes");
            System.out.print("경로: ");
            for (int i = 0; i < result.path.size(); i++) {
                System.out.print(result.path.get(i));
                if (i < result.path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("경로 존재: No");
        }
    }
}
