# SynapseLogic - Tarjeta CRC

# Clase - Responsabilidades | Colaboradores 

## Red de Neuronas
1. Almacenar y gestionar las neuronas (nodos). | Neurona 
2. Agregar y eliminar neuronas de la red. | Neurona 
3. Controlar las conexiones (sinapnis) dirigidas entre neuronas | Sinapsis
4. Mostrar la lista de la cercanía para una neuroa específica (vecinos) | Sinapsis

## HashTable 
1. Obtener los datos del diccionario. | Neurotransmisor
2. Calcular el índice mediante una función. 
3. Resolver las colisiones internas. 
4. Insertar y buscar los neurotransmisores en base al O(1).
## Sinapsis 
1. Conocer la neurona destino. | Neurona
2. Almacenar la información del neurotransmisor asociado.
3. Mantener una distancia específica y el coeficiente de eficiencia (k).
4. Dar paso al cálculo del peso efectivo (La fórmula W). | Tabla Hash | Neurotransmisor 
5. Mostrar el factor de deterioro multiplicando k por 1.2. 

## Resiliencia 
1. Emplear recorrido BFS/DFS desde la neurona principal para encontrar zonas aisladas | Grafo | Neurona
2. Determinar si la red sináptica es conexa o fragmentada. | Grafo |
3. Usar el algoritmo Dijkstra para la ruta de activación eléctrica. | Grafo | Sinapsis
4. Demostrar el incremento global por fatiga cognitiva | Grafo | Sinapsis 
   

