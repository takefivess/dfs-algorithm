# DFS 풀이 및 구현

## 풀이 1: 그래프 방문 순서 출력

### 알고리즘 설명
1. 그래프를 인접 리스트로 표현
2. 방문 배열을 초기화
3. 시작 노드에서 DFS 시작
4. 각 노드를 방문할 때마다 출력

### 시간복잡도
- O(V + E): V는 정점의 개수, E는 간선의 개수

### Python 코드
```python
def dfs_visit_order(graph, start, n):
    """
    DFS를 이용하여 방문 순서 출력
    
    Args:
        graph: 인접 리스트 형태의 그래프
        start: 시작 정점
        n: 정점의 개수
    
    Returns:
        방문한 정점의 리스트
    """
    visited = [False] * (n + 1)
    result = []
    
    def dfs(node):
        visited[node] = True
        result.append(node)
        
        for neighbor in graph[node]:
            if not visited[neighbor]:
                dfs(neighbor)
    
    dfs(start)
    return result


# 사용 예시
if __name__ == "__main__":
    # 그래프 구성
    graph = {
        1: [2, 3],
        2: [1, 4],
        3: [1, 5],
        4: [2],
        5: [3]
    }
    
    result = dfs_visit_order(graph, 1, 5)
    print(f"방문 순서: {' '.join(map(str, result))}")
    # 출력: 방문 순서: 1 2 4 3 5
```

---

## 풀이 2: 연결 요소의 개수 구하기

### 알고리즘 설명
1. 모든 정점에 대해 방문 여부 확인
2. 방문하지 않은 정점을 찾으면 새로운 DFS 시작
3. DFS가 시작될 때마다 연결 요소 개수 증가

### 시간복잡도
- O(V + E)

### Python 코드
```python
def count_connected_components(graph, n):
    """
    연결 요소의 개수 구하기
    
    Args:
        graph: 인접 리스트 형태의 그래프
        n: 정점의 개수
    
    Returns:
        연결 요소의 개수
    """
    visited = [False] * (n + 1)
    count = 0
    
    def dfs(node):
        visited[node] = True
        for neighbor in graph[node]:
            if not visited[neighbor]:
                dfs(neighbor)
    
    # 모든 정점에 대해 DFS 수행
    for i in range(1, n + 1):
        if not visited[i]:
            dfs(i)
            count += 1
    
    return count


# 사용 예시
if __name__ == "__main__":
    graph = {
        1: [2],
        2: [1, 3],
        3: [2],
        4: [5],
        5: [4, 6],
        6: [5],
        7: []
    }
    
    result = count_connected_components(graph, 7)
    print(f"연결 요소의 개수: {result}")
    # 출력: 연결 요소의 개수: 3
```

---

## 풀이 3: 경로 찾기

### 알고리즘 설명
1. 출발점에서 DFS 시작
2. 도착점을 찾을 때까지 탐색
3. 경로를 추적하며 저장

### 시간복잡도
- O(V + E)

### Python 코드
```python
def find_path(graph, start, end, n):
    """
    두 정점 사이의 경로 찾기
    
    Args:
        graph: 인접 리스트 형태의 그래프
        start: 출발점
        end: 도착점
        n: 정점의 개수
    
    Returns:
        (경로 존재 여부, 경로)
    """
    visited = [False] * (n + 1)
    path = []
    
    def dfs(node, current_path):
        if node == end:
            path.extend(current_path + [node])
            return True
        
        visited[node] = True
        
        for neighbor in graph[node]:
            if not visited[neighbor]:
                if dfs(neighbor, current_path + [node]):
                    return True
        
        return False
    
    found = dfs(start, [])
    return found, path


# 사용 예시
if __name__ == "__main__":
    graph = {
        1: [2, 3],
        2: [4],
        3: [2, 5],
        4: [5],
        5: [6],
        6: []
    }
    
    found, path = find_path(graph, 1, 6, 6)
    if found:
        print(f"경로 존재: Yes")
        print(f"경로: {' -> '.join(map(str, path))}")
    else:
        print("경로 존재: No")
```

---

## 풀이 4: 사이클 찾기

### 알고리즘 설명
1. 각 노드의 상태를 3가지로 구분:
   - 0: 방문하지 않음
   - 1: 현재 경로에 포함됨 (방문 중)
   - 2: 방문 완료
2. 현재 경로에 포함된 노드를 다시 만나면 사이클 존재

### 시간복잡도
- O(V + E)

### Python 코드
```python
def has_cycle(graph, n):
    """
    방향 그래프의 사이클 존재 여부 판단
    
    Args:
        graph: 인접 리스트 형태의 그래프
        n: 정점의 개수
    
    Returns:
        사이클 존재 여부
    """
    # 0: 방문 안 함, 1: 방문 중, 2: 방문 완료
    state = [0] * (n + 1)
    
    def dfs(node):
        state[node] = 1  # 현재 경로에 추가
        
        for neighbor in graph[node]:
            if state[neighbor] == 1:  # 현재 경로의 노드 발견 -> 사이클
                return True
            elif state[neighbor] == 0:  # 아직 방문 안 함
                if dfs(neighbor):
                    return True
        
        state[node] = 2  # 현재 경로에서 제거
        return False
    
    # 모든 노드에 대해 DFS 수행
    for i in range(1, n + 1):
        if state[i] == 0:
            if dfs(i):
                return True
    
    return False


# 사용 예시
if __name__ == "__main__":
    graph = {
        1: [2, 3],
        2: [3],
        3: [4],
        4: [2],  # 2-3-4-2 사이클
    }
    
    result = has_cycle(graph, 4)
    print(f"사이클 존재: {'Yes' if result else 'No'}")
    # 출력: 사이클 존재: Yes
```

---

## 풀이 5: 이진 트리 경로의 합

### 알고리즘 설명
1. 루트에서 시작하여 DFS 수행
2. 각 경로의 합을 추적
3. 리프 노드에 도달했을 때 목표값과 비교
4. 일치하는 경로 개수 증가

### 시간복잡도
- O(N): N은 트리의 노드 개수

### Python 코드
```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def path_sum_count(root, target_sum):
    """
    루트에서 리프까지의 경로 중 합이 target_sum과 같은 경로의 개수
    
    Args:
        root: 이진 트리의 루트 노드
        target_sum: 목표 합계
    
    Returns:
        경로의 개수
    """
    def dfs(node, current_sum):
        if node is None:
            return 0
        
        # 현재 경로의 합 업데이트
        current_sum += node.val
        
        # 리프 노드이고 합이 일치하면 카운트 증가
        if node.left is None and node.right is None:
            return 1 if current_sum == target_sum else 0
        
        # 왼쪽과 오른쪽 서브트리 탐색
        left_count = dfs(node.left, current_sum)
        right_count = dfs(node.right, current_sum)
        
        return left_count + right_count
    
    return dfs(root, 0)


# 사용 예시
if __name__ == "__main__":
    #      5
    #     / \\
    #    4   8
    #   /   / \\
    #  11  13  4
    #  / \\      \\
    # 7   2      1
    
    root = TreeNode(5)
    root.left = TreeNode(4)
    root.right = TreeNode(8)
    root.left.left = TreeNode(11)
    root.left.left.left = TreeNode(7)
    root.left.left.right = TreeNode(2)
    root.right.left = TreeNode(13)
    root.right.right = TreeNode(4)
    root.right.right.right = TreeNode(1)
    
    result = path_sum_count(root, 22)
    print(f"경로의 개수: {result}")
    # 출력: 경로의 개수: 2
```

---

## 풀이 6: 미로 탈출

### 알고리즘 설명
1. 미로를 2D 배열로 표현
2. 시작점에서 DFS 시작
3. 상, 하, 좌, 우 4방향 탐색
4. 도착점에 도달하면 경로 길이 반환
5. 한 번 방문한 칸은 다시 방문하지 않음

### 시간복잡도
- O(R × C): R은 행의 개수, C는 열의 개수

### Python 코드
```python
def maze_escape(maze):
    """
    미로 탈출 경로 찾기
    
    Args:
        maze: 미로 2D 배열 (list of strings)
    
    Returns:
        (경로 존재 여부, 최단 거리)
    """
    if not maze:
        return False, -1
    
    rows, cols = len(maze), len(maze[0])
    start = end = None
    
    # 시작점과 도착점 찾기
    for i in range(rows):
        for j in range(cols):
            if maze[i][j] == 'S':
                start = (i, j)
            elif maze[i][j] == 'E':
                end = (i, j)
    
    visited = set()
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상, 하, 좌, 우
    
    def dfs(x, y, distance):
        # 도착점 도달
        if (x, y) == end:
            return True, distance
        
        visited.add((x, y))
        
        # 4방향 탐색
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            
            # 범위 내, 방문하지 않음, 벽이 아님
            if (0 <= nx < rows and 0 <= ny < cols and 
                (nx, ny) not in visited and maze[nx][ny] != '#'):
                
                found, dist = dfs(nx, ny, distance + 1)
                if found:
                    return True, dist
        
        return False, -1
    
    return dfs(start[0], start[1], 0)


# 사용 예시
if __name__ == "__main__":
    maze = [
        "S..#.",
        ".#...",
        "..#..",
        "#...E"
    ]
    
    found, distance = maze_escape(maze)
    if found:
        print("경로 존재: Yes")
        print(f"최단 거리: {distance}")
    else:
        print("경로 존재: No")
```

---

## 핵심 개념 정리

### DFS의 특징
- **스택(Stack) 기반**: 재귀 또는 명시적 스택 사용
- **깊이 우선**: 한 방향으로 끝까지 탐색 후 백트래킹
- **메모리 효율**: 너비 우선 탐색(BFS)보다 적은 메모리 사용
- **경로 추적**: 경로 찾기, 백트래킹 문제에 적합

### DFS 적용 분야
- 그래프 연결성 판단
- 사이클 감지
- 위상 정렬(Topological Sort)
- 백트래킹 알고리즘
- 조합/순열 생성
- 미로 탈출

### 시간복잡도
- 인접 리스트: O(V + E)
- 인접 행렬: O(V²)

### 공간복잡도
- O(V): 방문 배열과 재귀 스택
