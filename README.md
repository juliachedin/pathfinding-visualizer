# pathfinding-visualizer

visual and interactive tool for exploring how pathfinding algorithms work. Built in Java with JavaFX, the visualizer lets you draw walls, set start and end points, and watch step-by-step as BFS, Dijkstra, or A* searches for the shortest path through a grid.

## Features

- **Three pathfinding algorithms** — BFS, Dijkstra, and A*
- **Step-by-step animation** — watch the algorithm search through the grid in real time
- **Draw walls** — click or drag to place walls and create mazes
- **Set start and end points** — choose where the algorithm begins and where it needs to go
- **Shortest path highlight** — the final path is highlighted when the algorithm is done
- **Clean button** — reset the grid and start over
- **Run time display** — see how long the algorithm took in seconds
- **Nodes visited counter** — see how many nodes the algorithm had to visit to find the path

## Algorithms

### Breadth-First Search (BFS)
Explores all nodes level by level, always checking the closest nodes 
first. Guaranteed to find the shortest path in an unweighted grid. 
Uses a queue to keep track of which nodes to visit next.

### Dijkstra
Similar to BFS but designed for weighted graphs. In our unweighted grid 
it behaves like BFS, always expanding the node with the lowest total 
distance from the start. Uses a priority queue to always process the 
closest node first.

### A* (A-Star)
The smartest of the three. Uses a heuristic (Manhattan distance) to 
estimate how far each node is from the goal, which means it prioritizes 
nodes that are both close to the start and close to the end. This makes 
it faster than Dijkstra in practice since it avoids exploring nodes in 
the wrong direction.