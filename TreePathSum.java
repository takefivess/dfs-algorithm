/**
 * DFS 알고리즘 예제 5: 이진 트리 경로의 합
 * 
 * 이진 트리가 주어졌을 때, 루트에서 리프 노드까지의 경로의 합이 
 * 특정 값과 같은 경로의 개수를 구합니다.
 */
public class TreePathSum {
    
    /**
     * 이진 트리 노드 클래스
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    /**
     * 경로의 합이 targetSum과 같은 경로의 개수를 구합니다.
     * 
     * 시간복잡도: O(N) - N: 트리의 노드 개수
     * 공간복잡도: O(H) - H: 트리의 높이 (재귀 스택)
     * 
     * @param root 이진 트리의 루트 노드
     * @param targetSum 목표 합계
     * @return 경로의 개수
     */
    public static int pathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum, 0);
    }
    
    /**
     * DFS를 이용한 경로의 합 계산
     * 
     * @param node 현재 노드
     * @param targetSum 목표 합계
     * @param currentSum 현재까지의 경로 합
     * @return 경로의 개수
     */
    private static int dfs(TreeNode node, int targetSum, long currentSum) {
        // 노드가 null이면 0 반환
        if (node == null) {
            return 0;
        }
        
        // 현재 경로의 합 업데이트
        currentSum += node.val;
        
        // 리프 노드에 도달했을 때 합이 일치하는지 확인
        int count = 0;
        if (node.left == null && node.right == null) {
            if (currentSum == targetSum) {
                count = 1;
            }
        } else {
            // 왼쪽과 오른쪽 서브트리 탐색
            count = dfs(node.left, targetSum, currentSum) + 
                    dfs(node.right, targetSum, currentSum);
        }
        
        return count;
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        //        5
        //       / \
        //      4   8
        //     /   / \
        //    11  13  4
        //   / \      \
        //  7   2      1
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        
        int targetSum = 22;
        int result = pathSum(root, targetSum);
        
        System.out.println("목표 합계: " + targetSum);
        System.out.println("경로의 개수: " + result);
        System.out.println("설명: 5->4->11->2=22, 5->8->4->5=22");
        // 출력: 경로의 개수: 2
        
        // 테스트 2
        System.out.println("\n테스트 2:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        int result2 = pathSum(root2, 3);
        System.out.println("경로의 개수: " + result2);
        // 출력: 경로의 개수: 1 (경로: 1->2)
    }
}
