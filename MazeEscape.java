
/**
 * DFS 알고리즘 예제 6: 미로 탈출
 * 
 * 2D 미로가 주어졌을 때, 시작 위치에서 목표 위치까지 갈 수 있는지 판단하고,
 * 갈 수 있다면 최단 경로를 찾습니다.
 * 
 * 미로 표기:
 * - 'S': 시작점
 * - 'E': 목표점 (End)
 * - '.': 이동 가능한 칸
 * - '#': 벽
 */
public class MazeEscape {
    
    private char[][] maze;
    private boolean[][] visited;
    private int rows;
    private int cols;
    private int[] startPos;
    private int[] endPos;
    private int minDistance;
    
    /**
     * 미로 초기화
     * 
     * @param maze 미로 배열 (2D char 배열)
     */
    public MazeEscape(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.visited = new boolean[rows][cols];
        this.minDistance = Integer.MAX_VALUE;
        
        // 시작점과 목표점 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startPos = new int[]{i, j};
                } else if (maze[i][j] == 'E') {
                    endPos = new int[]{i, j};
                }
            }
        }
    }
    
    /**
     * 미로 탈출 경로 찾기
     * 
     * 시간복잡도: O(R × C) - R: 행의 개수, C: 열의 개수
     * 공간복잡도: O(R × C) - 방문 배열과 재귀 스택
     * 
     * @return 경로 존재 여부와 최단 거리 정보
     */
    public MazeResult escape() {
        if (startPos == null || endPos == null) {
            return new MazeResult(false, -1);
        }
        
        // DFS 수행
        dfs(startPos[0], startPos[1], 0);
        
        if (minDistance == Integer.MAX_VALUE) {
            return new MazeResult(false, -1);
        }
        return new MazeResult(true, minDistance);
    }
    
    /**
     * DFS를 이용한 미로 탈출 탐색
     * 
     * @param row 현재 행
     * @param col 현재 열
     * @param distance 현재까지의 이동 거리
     */
    private void dfs(int row, int col, int distance) {
        // 목표점 도달
        if (row == endPos[0] && col == endPos[1]) {
            minDistance = Math.min(minDistance, distance);
            return;
        }
        
        visited[row][col] = true;
        
        // 4방향 탐색 (상, 하, 좌, 우)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            // 범위 확인, 방문 여부 확인, 벽 확인
            if (isValid(newRow, newCol) && !visited[newRow][newCol] && 
                maze[newRow][newCol] != '#') {
                dfs(newRow, newCol, distance + 1);
            }
        }
        
        visited[row][col] = false;  // 백트래킹
    }
    
    /**
     * 좌표가 유효한지 확인
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    /**
     * 미로 탈출 결과 클래스
     */
    public static class MazeResult {
        public boolean found;
        public int distance;
        
        public MazeResult(boolean found, int distance) {
            this.found = found;
            this.distance = distance;
        }
    }
    
    /**
     * 미로 출력
     */
    public void printMaze() {
        System.out.println("미로:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * 메인 메서드 - 테스트
     */
    public static void main(String[] args) {
        // 테스트 1: 경로가 있는 경우
        System.out.println("===== 테스트 1: 경로 있음 =====");
        char[][] maze1 = {
            {'S', '.', '.', '#', '.'},
            {'.', '#', '.', '.', '.'},
            {'.', '.', '#', '.', '.'},
            {'#', '.', '.', '.', 'E'}
        };
        
        MazeEscape escape1 = new MazeEscape(maze1);
        escape1.printMaze();
        
        MazeResult result1 = escape1.escape();
        if (result1.found) {
            System.out.println("경로 존재: Yes");
            System.out.println("최단 거리: " + result1.distance);
        } else {
            System.out.println("경로 존재: No");
        }
        
        // 테스트 2: 경로가 없는 경우
        System.out.println("\n===== 테스트 2: 경로 없음 =====");
        char[][] maze2 = {
            {'S', '.', '#', '.'},
            {'.', '#', '#', '.'},
            {'.', '.', '#', '.'},
            {'.', '.', '.', 'E'}
        };
        
        MazeEscape escape2 = new MazeEscape(maze2);
        escape2.printMaze();
        
        MazeResult result2 = escape2.escape();
        if (result2.found) {
            System.out.println("경로 존재: Yes");
            System.out.println("최단 거리: " + result2.distance);
        } else {
            System.out.println("경로 존재: No");
        }
    }
}
