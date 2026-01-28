import java.util.*;

/**
 * DFS 알고리즘 예제 1: 그래프 방문 순서 출력
 * 
 * 무방향 그래프에서 DFS를 이용하여 특정 노드에서 시작하여 
 * 방문 가능한 모든 노드를 방문 순서대로 출력합니다.
 */
public class GraphDFS {
    
    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private List<Integer> visitOrder;
    
    public GraphDFS(int nodeCount) {
        this.graph = new HashMap<>();
        this.visited = new boolean[nodeCount + 1];
        this.visitOrder = new ArrayList<>();
        
        // 그래프 초기화
        for (int i = 1; i <= nodeCount; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    
    /**
     * 간선 추가 (무방향 그래프)
     */
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    
    /**
     * DFS를 이용한 방문 순서 출력
     * 
     * 시간복잡도: O(V + E) - V: 정점, E: 간선
     * 공간복잡도: O(V) - 방문 배열과 재귀 스택
     */
    public List<Integer> dfs(int startNode) {
        visitOrder.clear();
        Arrays.fill(visited, false);
        
        dfsHelper(startNode);
        return visitOrder;
    }
    
    /**
     * DFS 재귀 함수
     */
    private void dfsHelper(int node) {
        visited[node] = true;
        visitOrder.add(node);
        
        // 인접 노드들을 순서대로 방문
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor);
            }
        }
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        // 그래프 생성
        GraphDFS dfs = new GraphDFS(5);
        
        // 간선 추가
        dfs.addEdge(1, 2);
        dfs.addEdge(1, 3);
        dfs.addEdge(2, 4);
        dfs.addEdge(3, 5);
        
        // DFS 수행
        List<Integer> result = dfs.dfs(1);
        
        System.out.println("방문 순서: " + result);
        // 출력: 방문 순서: [1, 2, 4, 3, 5]
    }
}
