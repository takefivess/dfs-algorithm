# DFS 자바(Java) 구현

이 파일은 DFS 알고리즘의 Java 기반 구현을 설명합니다.

## 파일 구조

```
├── GraphDFS.java              # 예제 1: 그래프 방문 순서 출력
├── ConnectedComponents.java   # 예제 2: 연결 요소의 개수
├── PathFinding.java           # 예제 3: 경로 찾기
├── CycleDetection.java        # 예제 4: 사이클 찾기
├── TreePathSum.java           # 예제 5: 이진 트리 경로의 합
└── MazeEscape.java            # 예제 6: 미로 탈출
```

## 각 예제별 설명

### 1. GraphDFS.java - 그래프 방문 순서 출력

#### 핵심 개념
- 무방향 그래프 표현 (HashMap + ArrayList)
- 재귀를 이용한 DFS 구현
- 방문 배열을 통한 중복 방문 방지

#### 주요 메서드
```java
// DFS 시작
public List<Integer> dfs(int startNode)

// DFS 재귀 함수
private void dfsHelper(int node)
```

#### 실행 방법
```bash
javac GraphDFS.java
java GraphDFS
```

#### 출력 예시
```
방문 순서: [1, 2, 4, 3, 5]
```

---

### 2. ConnectedComponents.java - 연결 요소의 개수

#### 핵심 개념
- 여러 번의 DFS 호출
- 각 DFS 호출마다 새로운 연결 요소 카운팅
- 모든 노드를 한 번 이상 방문 보장

#### 주요 메서드
```java
// 연결 요소 개수 계산
public int countConnectedComponents(int nodeCount)

// 한 연결 요소 탐색
private void dfs(int node)
```

#### 실행 방법
```bash
javac ConnectedComponents.java
java ConnectedComponents
```

#### 출력 예시
```
연결 요소의 개수: 2
```

---

### 3. PathFinding.java - 경로 찾기

#### 핵심 개념
- 방향 그래프에서의 경로 탐색
- 경로 추적 (현재 경로를 스택처럼 관리)
- 목표 노드 발견 시 경로 저장

#### 주요 메서드
```java
// 경로 찾기
public PathResult findPath(int start, int end, int nodeCount)

// DFS 기반 경로 탐색
private boolean dfs(int node, List<Integer> currentPath)
```

#### 내부 클래스
```java
public static class PathResult {
    public boolean found;      // 경로 존재 여부
    public List<Integer> path; // 실제 경로
}
```

#### 실행 방법
```bash
javac PathFinding.java
java PathFinding
```

#### 출력 예시
```
경로 존재: Yes
경로: 1 -> 2 -> 4 -> 5 -> 6
```

---

### 4. CycleDetection.java - 사이클 찾기

#### 핵심 개념
- 노드 상태 3가지 관리
  - 0: 미방문
  - 1: 현재 경로에 포함 (방문 중)
  - 2: 방문 완료
- 현재 경로의 노드를 다시 만나면 사이클 존재

#### 주요 메서드
```java
// 사이클 존재 여부 판단
public boolean hasCycle(int nodeCount)

// DFS를 이용한 사이클 감지
private boolean dfs(int node)
```

#### 실행 방법
```bash
javac CycleDetection.java
java CycleDetection
```

#### 출력 예시
```
테스트 1 - 사이클 있음
사이클 존재: Yes

테스트 2 - 사이클 없음
사이클 존재: No
```

---

### 5. TreePathSum.java - 이진 트리 경로의 합

#### 핵심 개념
- 이진 트리 노드 클래스 정의
- 현재 경로의 합을 누적하며 DFS 수행
- 리프 노드에서만 목표값 비교

#### 주요 클래스
```java
public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
```

#### 주요 메서드
```java
// 경로의 개수 계산
public static int pathSum(TreeNode root, int targetSum)

// DFS를 이용한 경로 합 계산
private static int dfs(TreeNode node, int targetSum, long currentSum)
```

#### 실행 방법
```bash
javac TreePathSum.java
java TreePathSum
```

#### 출력 예시
```
목표 합계: 22
경로의 개수: 2
설명: 5->4->11->2=22, 5->8->4->5=22
```

---

### 6. MazeEscape.java - 미로 탈출

#### 핵심 개념
- 2D 배열에서의 4방향 탐색 (상, 하, 좌, 우)
- 방문 배열을 통한 무한 루프 방지
- 백트래킹을 이용한 모든 경로 탐색

#### 미로 표기
- `S`: 시작점 (Start)
- `E`: 목표점 (End)
- `.`: 이동 가능한 칸
- `#`: 벽

#### 주요 클래스
```java
public static class MazeResult {
    public boolean found;   // 경로 존재 여부
    public int distance;    // 최단 거리
}
```

#### 주요 메서드
```java
// 미로 탈출 경로 찾기
public MazeResult escape()

// DFS를 이용한 탐색
private void dfs(int row, int col, int distance)

// 좌표 유효성 확인
private boolean isValid(int row, int col)
```

#### 실행 방법
```bash
javac MazeEscape.java
java MazeEscape
```

#### 출력 예시
```
===== 테스트 1: 경로 있음 =====
미로:
S . . # . 
. # . . . 
. . # . . 
# . . . E 
경로 존재: Yes
최단 거리: 8
```

---

## Java 특징 및 장점

### HashMap을 이용한 인접 리스트
```java
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(1, new ArrayList<>());
```

**장점:**
- 동적 크기 조절
- 임의의 정점 레이블 사용 가능 (0 이외의 값도 가능)
- 희소 그래프에 메모리 효율적

### 방문 배열 (Visited Array)
```java
boolean[] visited = new boolean[nodeCount + 1];
```

**역할:**
- 같은 노드의 중복 방문 방지
- 재귀 호출의 무한 루프 방지

### 백트래킹 (Backtracking)
```java
currentPath.add(node);
// ... 재귀 호출 ...
currentPath.remove(currentPath.size() - 1);
```

**용도:**
- 모든 가능한 경로 탐색
- 조건을 만족하는 경로 찾기

---

## 실행 순서

### 컴파일 및 실행
```bash
# 개별 파일 실행
javac GraphDFS.java && java GraphDFS
javac ConnectedComponents.java && java ConnectedComponents
javac PathFinding.java && java PathFinding
javac CycleDetection.java && java CycleDetection
javac TreePathSum.java && java TreePathSum
javac MazeEscape.java && java MazeEscape

# 또는 모두 컴파일
javac *.java
```

---

## 시간복잡도 비교

| 문제 | 시간복잡도 | 공간복잡도 | 설명 |
|------|-----------|----------|------|
| GraphDFS | O(V+E) | O(V) | 각 정점과 간선을 한 번씩 방문 |
| ConnectedComponents | O(V+E) | O(V) | 모든 정점에 대해 DFS 수행 |
| PathFinding | O(V+E) | O(V) | 목표 노드 발견 시 종료 (최악의 경우 O(V+E)) |
| CycleDetection | O(V+E) | O(V) | 모든 정점 방문 필요 |
| TreePathSum | O(N) | O(H) | N: 노드 개수, H: 트리 높이 |
| MazeEscape | O(R×C) | O(R×C) | R: 행, C: 열 (최악의 경우 모든 칸 방문) |

---

## 디버깅 팁

### 1. 간단한 입력으로 테스트
```java
// 최소한의 그래프로 시작
GraphDFS dfs = new GraphDFS(2);
dfs.addEdge(1, 2);
System.out.println(dfs.dfs(1));  // [1, 2]
```

### 2. 중간 결과 출력
```java
System.out.println("방문: " + node);
```

### 3. 방문 배열 확인
```java
System.out.println(Arrays.toString(visited));
```

---

## 추가 학습 자료

- **원본 문제**: `DFS_예제.md` 참조
- **Python 구현**: `DFS_풀이.md` 참조
- **개념 설명**: `README.md` 참조
