import java.util.*;

/**
 * DFS 알고리즘 예제 4: 사이클 찾기
 * 
 * 방향 그래프에서 사이클(Cycle)이 존재하는지 판단합니다.
 * 노드의 상태를 3가지로 구분하여 사이클을 감지합니다:
 * - 0: 방문하지 않음
 * - 1: 현재 경로에 포함됨 (방문 중)
 * - 2: 방문 완료
 */
public class CycleDetection {
    
    private Map<Integer, List<Integer>> graph;
    private int[] state;  // 0: 미방문, 1: 방문중, 2: 완료
    private boolean hasCycle;
    
    public CycleDetection(int nodeCount) {
        this.graph = new HashMap<>();
        this.state = new int[nodeCount + 1];
        this.hasCycle = false;
        
        for (int i = 1; i <= nodeCount; i++) {
            graph.put(i, new ArrayList<>());
            state[i] = 0;
        }
    }
    
    /**
     * 방향 간선 추가
     */
    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }
    
    /**
     * 사이클 존재 여부 판단
     * 
     * 시간복잡도: O(V + E)
     * 공간복잡도: O(V)
     */
    public boolean hasCycle(int nodeCount) {
        // 모든 노드에 대해 DFS 수행
        for (int i = 1; i <= nodeCount; i++) {
            if (state[i] == 0) {
                if (dfs(i)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * DFS를 이용한 사이클 감지
     * 
     * state[node] == 1인 노드를 다시 만나면 사이클이 존재
     */
    private boolean dfs(int node) {
        state[node] = 1;  // 현재 경로에 추가
        
        // 인접 노드들 탐색
        for (int neighbor : graph.get(node)) {
            if (state[neighbor] == 1) {
                // 현재 경로의 노드를 만남 -> 사이클 발견
                return true;
            } else if (state[neighbor] == 0) {
                // 미방문 노드 -> 계속 탐색
                if (dfs(neighbor)) {
                    return true;
                }
            }
            // state[neighbor] == 2인 경우는 이미 완료된 경로이므로 무시
        }
        
        state[node] = 2;  // 현재 경로에서 제거
        return false;
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        // 테스트 1: 사이클이 있는 경우
        CycleDetection cd1 = new CycleDetection(4);
        cd1.addEdge(1, 2);
        cd1.addEdge(2, 3);
        cd1.addEdge(3, 4);
        cd1.addEdge(4, 2);  // 2-3-4-2 사이클
        
        System.out.println("테스트 1 - 사이클 있음");
        System.out.println("사이클 존재: " + (cd1.hasCycle(4) ? "Yes" : "No"));
        
        // 테스트 2: 사이클이 없는 경우
        System.out.println("\n테스트 2 - 사이클 없음");
        CycleDetection cd2 = new CycleDetection(4);
        cd2.addEdge(1, 2);
        cd2.addEdge(1, 3);
        cd2.addEdge(2, 4);
        cd2.addEdge(3, 4);
        
        System.out.println("사이클 존재: " + (cd2.hasCycle(4) ? "Yes" : "No"));
    }
}
