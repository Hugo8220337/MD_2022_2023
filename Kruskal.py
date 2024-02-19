# Kruskal's algorithm in Python


class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = []

    def add_edge(self, u, v, w):
        self.graph.append([u, v, w])

    # Troca os numeros pelos nomes das regioes
    def switchRegioes(i):
        match i:
            case 0:
                return "Aveiro"
            case 1:
                return "Beja"
            case 2:
                return "Braga"
            case 3:
                return "Braganca"
            case 4:
                return "Castelo Branco"
            case 5:
                return "Coimbra"
            case 6:
                return "Evora"
            case 7:
                return "Faro"
            case 8:
                return "Guarda"
            case 9:
                return "Leiria"
            case 10:
                return "Lisboa"
            case 11:
                return "Portalegre"
            case 12:
                return "Porto"
            case 13:
                return "Santarem"
            case 14:
                return "Setubal"
            case 15:
                return "Viana do Castelo"
            case 16:
                return "Vila Real"
            case 17:
                return "Viseu"
            case _:
                return "Erro"

    # Search function
    def find(self, parent, i):
        if parent[i] == i:
            return i
        return self.find(parent, parent[i])

    def apply_union(self, parent, rank, x, y):
        xroot = self.find(parent, x)
        yroot = self.find(parent, y)
        if rank[xroot] < rank[yroot]:
            parent[xroot] = yroot
        elif rank[xroot] > rank[yroot]:
            parent[yroot] = xroot
        else:
            parent[yroot] = xroot
            rank[xroot] += 1

    #  Applying Kruskal algorithm
    def kruskal_algo(self):
        result = []
        i, e = 0, 0
        self.graph = sorted(self.graph, key=lambda item: item[2])
        parent = []
        rank = []
        for node in range(self.V):
            parent.append(node)
            rank.append(0)
        while e < self.V - 1:
            u, v, w = self.graph[i]
            i = i + 1
            x = self.find(parent, u)
            y = self.find(parent, v)
            if x != y:
                e = e + 1
                result.append([u, v, w])
                self.apply_union(parent, rank, x, y)
        for u, v, weight in result:
            print("%s - %s: %d" % (Graph.switchRegioes(u), Graph.switchRegioes(v), weight))


g = Graph(18)

g.add_edge(0, 5, 80)
g.add_edge(0, 12, 70)
g.add_edge(0, 17, 100)
g.add_edge(1, 6, 80)
g.add_edge(1, 7, 170)
g.add_edge(1, 14, 135)
g.add_edge(2, 12, 50)
g.add_edge(2, 15, 50)
g.add_edge(2, 16, 100)
g.add_edge(3, 8, 200)
g.add_edge(3, 16, 140)
g.add_edge(4, 5, 160)
g.add_edge(4, 8, 100)
g.add_edge(4, 11, 80)
g.add_edge(4, 13, 200)
g.add_edge(5, 0, 80)
g.add_edge(5, 4, 160)
g.add_edge(5, 8, 160)
g.add_edge(5, 9, 70)
g.add_edge(5, 17, 80)
g.add_edge(6, 1, 80)
g.add_edge(6, 10, 150)
g.add_edge(6, 11, 100)
g.add_edge(6, 13, 120)
g.add_edge(7, 1, 170)
g.add_edge(7, 14, 260)
g.add_edge(8, 3, 200)
g.add_edge(8, 4, 100)
g.add_edge(8, 5, 160)
g.add_edge(8, 16, 150)
g.add_edge(8, 17, 80)
g.add_edge(9, 5, 70)
g.add_edge(9, 10, 130)
g.add_edge(10, 6, 150)
g.add_edge(10, 9, 130)
g.add_edge(10, 13, 70)
g.add_edge(10, 14, 50)
g.add_edge(11, 4, 80)
g.add_edge(11, 6, 100)
g.add_edge(11, 13, 150)
g.add_edge(12, 0, 70)
g.add_edge(12, 2, 50)
g.add_edge(12, 15, 80)
g.add_edge(12, 16, 120)
g.add_edge(13, 4, 200)
g.add_edge(13, 6, 120)
g.add_edge(13, 10, 70)
g.add_edge(13, 11, 150)
g.add_edge(14, 1, 135)
g.add_edge(14, 7, 260)
g.add_edge(14, 10, 50)
g.add_edge(15, 2, 50)
g.add_edge(15, 12, 80)
g.add_edge(16, 2, 100)
g.add_edge(16, 3, 140)
g.add_edge(16, 8, 150)
g.add_edge(16, 12, 120)
g.add_edge(16, 17, 110)
g.add_edge(17, 0, 100)
g.add_edge(17, 5, 80)
g.add_edge(17, 8, 80)
g.add_edge(17, 16, 110)

g.kruskal_algo()