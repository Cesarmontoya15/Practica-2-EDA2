public class AlgoritmoBellmanFord {
    // Clase interna para representar una arista en el grafo
    class Arista {
        char origen, destino;
        int peso;

        // Constructor de la clase Arista
        Arista(char origen, char destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    };

    int numVertices, numAristas;
    Arista aristas[];

    // Constructor de la clase AlgoritmoBellmanFord
    AlgoritmoBellmanFord(int numVertices, int numAristas) {
        this.numVertices = numVertices;
        this.numAristas = numAristas;
        aristas = new Arista[numAristas];
    }

    // Método para añadir una arista al grafo
    void addArista(int index, char origen, char destino, int peso) {
        aristas[index] = new Arista(origen, destino, peso);
    }

    // Método principal que implementa el algoritmo de Bellman-Ford
    void BellmanFord(AlgoritmoBellmanFord grafo, char origen) {
        System.out.println("Vértice de origen: " + origen); // Imprime el vértice de origen
        int[] distancias = inicializarDistancias(grafo.numVertices, origen);
        relajarAristas(grafo, distancias);
        verificarCiclosNegativos(grafo, distancias);
        imprimirDistancias(distancias, grafo.numVertices);
    }

    // Inicializa las distancias desde el origen a todos los vértices como infinito, excepto el origen
    int[] inicializarDistancias(int numVertices, char origen) {
        int[] distancias = new int[numVertices];
        for (int i = 0; i < numVertices; ++i)
            distancias[i] = Integer.MAX_VALUE;
        distancias[origen - 'A'] = 0;
        return distancias;
    }

    // Relaja todas las aristas 'numVertices - 1' veces
    void relajarAristas(AlgoritmoBellmanFord grafo, int[] distancias) {
        for (int i = 1; i < grafo.numVertices; ++i) {
            for (int j = 0; j < grafo.numAristas; ++j) {
                int u = grafo.aristas[j].origen - 'A';
                int v = grafo.aristas[j].destino - 'A';
                int peso = grafo.aristas[j].peso;
                if (distancias[u] != Integer.MAX_VALUE && distancias[u] + peso < distancias[v])
                    distancias[v] = distancias[u] + peso;
            }
        }
    }

    // Verifica si hay ciclos de peso negativo
    void verificarCiclosNegativos(AlgoritmoBellmanFord grafo, int[] distancias) {
        for (int j = 0; j < grafo.numAristas; ++j) {
            int u = grafo.aristas[j].origen - 'A';
            int v = grafo.aristas[j].destino - 'A';
            int peso = grafo.aristas[j].peso;
            if (distancias[u] != Integer.MAX_VALUE && distancias[u] + peso < distancias[v]) {
                System.out.println("El grafo contiene un ciclo de peso negativo");
                return;
            }
        }
    }

    // Imprime las distancias desde el origen a todos los demás vértices
    void imprimirDistancias(int distancias[], int numVertices) {
        System.out.println("Vértice   Distancia del origen");
        for (int i = 0; i < numVertices; ++i)
            System.out.println((char)(i + 'A') + "\t\t" + distancias[i]);
    }

    public static void main(String[] args) {
        int numVertices = 4;  // Número de vértices en el grafo
        int numAristas = 5;  // Número de aristas en el grafo

        AlgoritmoBellmanFord grafo = new AlgoritmoBellmanFord(numVertices, numAristas);

        // Añadir aristas
        grafo.addArista(0, 'A', 'B', 3);
        grafo.addArista(1, 'A', 'D', 6);
        grafo.addArista(2, 'D', 'B', -4);
        grafo.addArista(3, 'B', 'C', 2);
        grafo.addArista(4, 'C', 'D', 3);

        grafo.BellmanFord(grafo, 'A');
    }
}