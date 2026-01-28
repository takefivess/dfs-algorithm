import java.util.*;

/**
 * DFS 알고리즘 예제 2: 연결 요소의 개수 구하기
 * 
 * 무방향 그래프가 주어졌을 때, 그래프에서 연결 요소(Connected Component)의 개수를 구합니다.
 */
public class ConnectedComponents {
    
    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private int componentCount;
    
    public ConnectedComponents(int nodeCount) {
        this.graph = new HashMap<>();
        this.visited = new boolean[nodeCount + 1];
        this.componentCount = 0;
        
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
     * 연결 요소의 개수 구하기
     * 
     * 시간복잡도: O(V + E)
     * 공간복잡도: O(V)
     */
    public int countConnectedComponents(int nodeCount) {
        Arrays.fill(visited, false);
        componentCount = 0;
        
        // 모든 정점에 대해 DFS 수행
        for (int i = 1; i <= nodeCount; i++) {
            if (!visited[i]) {
                dfs(i);
                componentCount++;
            }
        }
        
        return componentCount;
    }
    
    /**
     * DFS 재귀 함수
     */
    private void dfs(int node) {
        visited[node] = true;
        
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        ConnectedComponents cc = new ConnectedComponents(7);
        
        // 간선 추가
        cc.addEdge(1, 2);
        cc.addEdge(2, 3);
        cc.addEdge(4, 5);
        cc.addEdge(5, 6);
        cc.addEdge(6, 7);
        
        int result = cc.countConnectedComponents(7);
        
        System.out.println("연결 요소의 개수: " + result);
        // 출력: 연결 요소의 개수: 2
    }
}
